package club.veev.andlua.script;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: LuaScriptFactory
 */
public class LuaScriptFactory {

    public static ILuaScript stringLuaScript(String script) {
        StringLuaScript s = new StringLuaScript();
        s.load(script);
        return s;
    }
}
