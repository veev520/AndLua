package club.veev.andlua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: Lua 脚本运行器
 */
public class LuaRunner {

    public LuaValue runScript(String script) {
        return runScript(script, false);
    }

    private LuaValue runScript(String script, boolean isSync) {
        Globals globals = AndLuaPlatform.customGlobals();

        globals.load(
                AndLua.getContext().getResources().openRawResource(R.raw.andlua),
                "AndLua",
                "t",
                globals).call();

        globals.load(";DBG=" + AndLua.isDebug() + ";TAG='" + AndLua.getTAG()  + "';").call();

        globals.load(script).call();

        return globals;
    }
}
