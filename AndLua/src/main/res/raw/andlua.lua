---
--- AndLua lua端 扩展
--- 封装基础方法和变量等
---

DBG = true
TAG = 'AndLua'
local Log = luajava.bindClass('android.util.Log')

function log(msg)
    if DBG then
        Log:i(TAG, tostring(msg))
    end
end

log('Hello AndLua.')