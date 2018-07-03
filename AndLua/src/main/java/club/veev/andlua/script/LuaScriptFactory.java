package club.veev.andlua.script;

import club.veev.andlua.AndLua;
import club.veev.andlua.R;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: LuaScriptFactory
 */
public class LuaScriptFactory {

    /**
     * 字符串脚本
     * @param script    脚本
     */
    public static IScript stringScript(String script) {
        return new StringScript(script);
    }

    /**
     * luaScript 工具类
     */
    public static IScript andluaScript() {
        return new RawScript(R.raw.andlua);
    }

    /**
     * 设置 debug
     */
    public static IScript debugScript() {
        return new StringScript(";DBG=" + AndLua.isDebug() + ";TAG='" + AndLua.getTAG()  + "';", "debug");
    }
}
