---
--- AndLua lua端 扩展
--- 封装基础方法和变量等
---

DBG = true
TAG = 'AndLua'
local Log = luajava.bindClass('android.util.Log')

---
--- 日志
function log(tag, msg)
    if DBG then
        if (msg == nil) then
            msg = tag
            tag = TAG
        end
        if (tag == nil) then
            tag = TAG
        end
        Log:i(tag, tostring(msg))
    end
end

---
--- 获取桥实例
function getBridge()
    return luabridge.bridge()
end

---
--- dp 转 px
function dp2px(dp)
    local display = luaandroid.bindClass('club.veev.andlua.utils.DisplayUtil')
    return display:dp2px(dp)
end

---
--- px 转 dp
function px2dp(dp)
    local display = luaandroid.bindClass('club.veev.andlua.utils.DisplayUtil')
    return display:px2dp(dp)
end

---
--- sp 转 px
function sp2px(dp)
    local display = luaandroid.bindClass('club.veev.andlua.utils.DisplayUtil')
    return display:sp2px(dp)
end

---
--- px 转 sp
function px2sp(dp)
    local display = luaandroid.bindClass('club.veev.andlua.utils.DisplayUtil')
    return display:px2sp(dp)
end



