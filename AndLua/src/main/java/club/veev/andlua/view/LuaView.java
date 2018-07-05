package club.veev.andlua.view;

import android.content.Context;

import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import club.veev.andlua.script.IScript;
import club.veev.andlua.script.LuaScriptFactory;

/**
 * Created by Veev on 2018/6/30.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    LuaView 工具类
 */
public class LuaView {

    /**
     * 加载脚本
     */
    public static ILuaView load(Context context, String script) {
        IScript script1 = LuaScriptFactory.stringScript(script);
        ILuaView view = new TestView(context, script1);
        return view;
    }

    public static ILuaView loadCustom(Context context, String script) {
        IScript script1 = LuaScriptFactory.stringScript(script);
        CustomLuaView view = new CustomLuaView(context, script1);
        return view;
    }
}
