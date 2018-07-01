---
--- AndLua lua端 扩展
--- 封装基础方法和变量等
---

DBG = true
TAG = 'AndLua'
local Log = luajava.bindClass('android.util.Log')

---
--- 日志
function log(msg)
    if DBG then
        Log:i(TAG, tostring(msg))
    end
end

---
--- 获取桥实例
function getBridge()
    return luabridge.bridge()
end
