from __future__ import annotations

import hashlib
import json
import re
import urllib.request
import urllib.error
from dataclasses import dataclass
from pathlib import Path
from typing import Dict, List, Optional, Literal


# =========================================================
# Exceptions
# =========================================================

class DocGenError(RuntimeError):
    pass


class DocGenConnectionError(DocGenError):
    pass


class DocGenResponseError(DocGenError):
    pass


# =========================================================
# Dataclasses
# =========================================================

Lang = Literal["python", "java", "lua"]


@dataclass(slots=True, frozen=True)
class CodeUnit:
    lang: Lang
    signature: str
    body: str
    start: int
    end: int


# =========================================================
# Ollama minimal wrapper
# =========================================================

class OllamaClient:
    def __init__(self, base_url: str, model: str, timeout: float = 60.0):
        self.base_url = base_url.rstrip("/")
        self.model = model
        self.timeout = timeout

    def generate(self, prompt: str) -> str:
        url = f"{self.base_url}/api/generate"
        payload = json.dumps({
            "model": self.model,
            "prompt": prompt,
            "stream": False
        }).encode()

        req = urllib.request.Request(
            url,
            data=payload,
            headers={"Content-Type": "application/json"},
            method="POST"
        )

        try:
            with urllib.request.urlopen(req, timeout=self.timeout) as r:
                raw = r.read()
        except urllib.error.URLError as e:
            raise DocGenConnectionError(str(e))

        try:
            data = json.loads(raw.decode())
        except Exception:
            raise DocGenResponseError("Invalid JSON")

        if "response" not in data:
            raise DocGenResponseError(data)

        return data["response"].strip()


# =========================================================
# Config
# =========================================================

SRC_DIR = Path("src")
CACHE_FILE = Path(".doc_cache.json")

EXCLUDED = {".git", "build", "dist", "target", "__pycache__", "venv"}

PY_RE = re.compile(r"def\s+\w+\s*\(.*?\):")
JAVA_RE = re.compile(r"(public|private|protected).*?\(.*?\)\s*\{")
LUA_RE = re.compile(r"function\s+\w+\s*\(.*?\)")


# =========================================================
# Helpers
# =========================================================

def sha(text: str) -> str:
    return hashlib.sha1(text.encode()).hexdigest()


def load_cache() -> Dict[str, bool]:
    if CACHE_FILE.exists():
        return json.loads(CACHE_FILE.read_text())
    return {}


def save_cache(cache: Dict[str, bool]):
    CACHE_FILE.write_text(json.dumps(cache, indent=2))


def excluded(path: Path) -> bool:
    return any(p in EXCLUDED for p in path.parts)


# =========================================================
# Extraction
# =========================================================

def extract_units(text: str, lang: Lang) -> List[CodeUnit]:
    regex = PY_RE if lang == "python" else JAVA_RE if lang == "java" else LUA_RE
    units: List[CodeUnit] = []

    for m in regex.finditer(text):
        start, end = m.span()
        body = text[start:end + 200]
        units.append(CodeUnit(lang, m.group(), body, start, end))

    return units


# =========================================================
# Prompt
# =========================================================

def build_prompt(unit: CodeUnit) -> str:
    style = {
        "python": "docstring Python style Google",
        "java": "Javadoc clair",
        "lua": "LDoc"
    }[unit.lang]

    return f"""
Génère un {style}.
Pas de markdown.
Pas de répétition de code.

Signature:
{unit.signature}

Code:
{unit.body[:800]}
"""


# =========================================================
# Injection
# =========================================================

def inject(text: str, unit: CodeUnit, doc: str) -> str:
    if unit.lang == "python":
        insert = f'\n    """{doc}"""\n'
        return text[:unit.end] + insert + text[unit.end:]

    if unit.lang == "java":
        block = "/**\n * " + doc.replace("\n", "\n * ") + "\n */\n"
        return text[:unit.start] + block + text[unit.start:]

    if unit.lang == "lua":
        return text[:unit.start] + f"--- {doc}\n" + text[unit.start:]

    return text


# =========================================================
# Core
# =========================================================

def process_file(path: Path, ai: OllamaClient, cache: Dict[str, bool]):
    original = path.read_text(encoding="utf-8")
    text = original

    lang: Lang = (
        "python" if path.suffix == ".py"
        else "java" if path.suffix == ".java"
        else "lua"
    )

    units = extract_units(text, lang)

    for u in units:
        h = sha(u.signature + u.body)
        if h in cache:
            continue

        prompt = build_prompt(u)
        doc = ai.generate(prompt)

        text = inject(text, u, doc)
        cache[h] = True

    if text != original:
        path.write_text(text, encoding="utf-8")
        print("Updated", path)


# =========================================================
# Main
# =========================================================

def main():
    ai = OllamaClient(
        base_url="http://10.22.28.190:11434",
        model="gemma2:latest"
    )

    cache = load_cache()

    for path in SRC_DIR.rglob("*"):
        if not path.is_file():
            continue
        if excluded(path):
            continue
        if path.suffix not in {".py", ".java", ".lua"}:
            continue

        try:
            process_file(path, ai, cache)
        except Exception as e:
            print("Error:", path, e)

    save_cache(cache)


if __name__ == "__main__":
    main()
