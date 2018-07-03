package club.veev.andlua.view;

import android.content.Context;
import android.view.View;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import club.veev.andlua.AndLuaPlatform;
import club.veev.andlua.script.IScript;
import club.veev.andlua.script.LuaScriptFactory;

/**
 * Created by Veev on 2018/6/27.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    TestView
 */
public class TestView implements ILuaView {

    private View mView;
    private Globals mGlobals;

    TestView(Context context, IScript script) {
        mGlobals = AndLuaPlatform.customGlobals();

        load(LuaScriptFactory.andluaScript()).call();
        load(LuaScriptFactory.debugScript()).call();
        load(script).call(CoerceJavaToLua.coerce(context));

        LuaValue v = mGlobals.get("getView");
        if (v != LuaValue.NIL) {
            mView = (View) CoerceLuaToJava.coerce(v.call(), View.class);
        }
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public LuaValue load(IScript script) {
        return mGlobals.load(script.getInputStream(), script.getName(), script.getMode(), mGlobals);
    }

    @Override
    public Globals getGlobals() {
        return mGlobals;
    }
}
