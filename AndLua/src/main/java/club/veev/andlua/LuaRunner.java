package club.veev.andlua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

import club.veev.andlua.script.ILuaScript;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: Lua 脚本运行器
 */
public class LuaRunner {

    public LuaValue runScript(ILuaScript script) {
        return runScript(script, false);
    }

    private LuaValue runScript(ILuaScript script, boolean isSync) {
        Globals globals = script.getScript();

        return globals;
    }
}
