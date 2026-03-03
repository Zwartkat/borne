-- ⚠️⚠️⚠️ - L'éxecution de Love2D à une importance pour la sauvegarde des highscores. Si le cd de la console ne point pas vers le fichier du jeu. Le jeu crashera à la sauvegarde. 
-- Conseil: Créer un fichier .bat comme ceci -> cd [CHEMIN VERS LE DOSSIER DU JEU] && "[CHEMIN VERS LOVE2D.exe]" .
-- et executez le fichier pour lancer le jeu.

-- Libs
local Vector2 = require("Arcade/classes/Vector2")
local Color = require("Arcade/classes/Color")
local Renderer = require("Arcade/libs/Rendering/Renderer")
local LogManager = require("Arcade/libs/Debug/LogManager")
local Screen = require("Arcade/libs/Rendering/Screen")

local TweenService = require("Arcade/libs/Tween")
local DelayService = require("Arcade/libs/Delay")

-- Settings
Renderer.ScreenSize = Vector2(1280, 1024)
Renderer.BackgroundColor = Color(.075, .075, .075)
Renderer.CurrentScreen = nil

-- Math lib overwrite
function math.clamp(Origin, Min, Max)
    return math.min(math.max(Origin, Min), Max)
end

-- Functions
function love.load()
    math.randomseed(love.timer.getTime())
    love.window.setMode(Renderer.ScreenSize.X, Renderer.ScreenSize.Y, {resizable=false, vsync=false, borderless=true})

    Renderer.changeScreen(Screen.get("Title")) -- Here you can input a screen's name in [Arcade/screens/...], for example "Title", "Test" or "GAME"
end

function love.update(dt)
    Renderer.update(dt)
    Renderer.CurrentScreen.update(dt)
    TweenService.StaticUpdate(dt)
    DelayService.StaticUpdate(dt)

    -- LOGS

    LogManager.cleanup()
    LogManager.updateLog(love.timer.getFPS() .. " FPS", Color.Green)
end