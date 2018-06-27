package club.veev.andlua.script;

import android.app.Application;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import club.veev.andlua.AndLua;
import club.veev.andlua.AndLuaPlatform;
import club.veev.andlua.R;

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

            mGlobals.load(
                    AndLua.getContext().getResources().openRawResource(R.raw.andlua),
                    "AndLua",
                    "t",
                    mGlobals).call();

            mGlobals.load(";DBG=" + AndLua.isDebug() + ";TAG='" + AndLua.getTAG()  + "';").call();

            mGlobals.load(mScript).call(CoerceJavaToLua.coerce(AndLua.getContext()));
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
