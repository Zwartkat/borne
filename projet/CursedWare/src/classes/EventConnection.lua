-- LIBS
local Object = require("Arcade/libs/Classic")

-- CLASS
local class = Object:extend()

function class:new(Callback, DisconnectFunction)
    self._type = "EventConnection"

    self.Callback = Callback
    self.DisconnectFunction = DisconnectFunction
    return self
end

-- METHODS
function class:fire(...)
    return self.Callback(...)
end

function class:disconnect()
    self:DisconnectFunction()
    self = nil
end

return class