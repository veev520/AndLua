package club.veev.andlua.script;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

import club.veev.andlua.AndLuaPlatform;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: StringLuaScript
 */
public class StringLuaScript implements ILuaScript {

    private String mScript;
    private Globals mGlobals;

    @Override
    public void load(Object... args) {
        if (args.length > 0) {
            mScript = args[0].toString();

            mGlobals = AndLuaPlatform.customGlobals();
            mGlobals.load(mScript);
        }
    }

    @Override
    public Globals getScript() {
        return mGlobals;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getVersion() {
        return null;
    }
}
