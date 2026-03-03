-- Libs
local Vector2 = require("Arcade/classes/Vector2")
local Color = require("Arcade/classes/Color")
local Spritesheet = require("Arcade/classes/Spritesheet")

local Screen = require("Arcade/libs/Rendering/Screen")
local Renderer = require("Arcade/libs/Rendering/Renderer")
local Controls = require("Arcade/libs/Controls")

-- Settings
local Menu = Screen.new()

-- Objects
local Animation = Spritesheet("assets/spritesheets/IntermissionSpeakers.png", Vector2(320, 256), 2)
Animation.Size = Renderer.ScreenSize
Menu.add(Animation)

-- // Runners
function Menu.open()
    Controls.bind("e", function(e)
        if not e then return end
        print("Stop")
        Animation:stop()
    end)

    Controls.bind("r", function(e)
        if not e then return end
        print("Cancel")
        Animation:cancel()
    end)

    Controls.bind("down", function(e)
        if not e then return end
        print("Duration")
        Animation:setDuration(Animation.Duration - .05)
        print(Animation.Duration)
    end)

    Controls.bind("up", function(e)
        if not e then return end
        print("Duration")
        Animation:setDuration(Animation.Duration + .05)
        print(Animation.Duration)
    end)

    Controls.bind("z", function(e)
        if not e then return end
        print("Play")
        Animation:play()
    end)

    Animation:play()
end

function Menu.update(dt)
    
end

return Menu