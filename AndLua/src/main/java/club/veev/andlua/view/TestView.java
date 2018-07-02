package club.veev.andlua.view;

import android.content.Context;
import android.view.View;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import club.veev.andlua.script.ILuaScript;

/**
 * Created by Veev on 2018/6/27.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    TestView
 */
public class TestView implements ILuaView {

    private View mView;

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public void load(Context context, ILuaScript script) {
        LuaValue v = script.getScript().get("getView");
        if (v != LuaValue.NIL) {
            mView = (View) CoerceLuaToJava.coerce(v.call(), View.class);
        }
    }
}
