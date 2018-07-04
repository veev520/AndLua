---
--- AndLua lua端 扩展
--- 封装基础方法和变量等
---

DBG = true
TAG = 'AndLua'
local Log = luajava.bindClass('android.util.Log')
local Toastor = luaandroid.bindClass('club.veev.andlua.utils.Toastor')
local DisplayUtil = luaandroid.bindClass('club.veev.andlua.utils.DisplayUtil')

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
--- toast
function toast(msg)
    Toastor:show(msg)
end

---
--- 获取桥实例
function getBridge()
    return luabridge.bridge()
end

---
--- dp 转 px
function dp2px(dp)
    return DisplayUtil:dp2px(dp)
end

---
--- px 转 dp
function px2dp(dp)
    return DisplayUtil:px2dp(dp)
end

---
--- sp 转 px
function sp2px(dp)
    return DisplayUtil:sp2px(dp)
end

---
--- px 转 sp
function px2sp(dp)
    return DisplayUtil:px2sp(dp)
end



