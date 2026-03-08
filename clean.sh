#!/bin/bash

find . -name "*.class" -delete
find . -name "*~" -delete
find . -name "*.pyc" -delete
find . -type d -name "__pycache__" -exec rm -rf {} + 2>/dev/null

echo "✓ Nettoyage terminé"