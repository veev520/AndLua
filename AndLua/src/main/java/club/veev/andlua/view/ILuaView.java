package club.veev.andlua.view;

import android.view.View;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

import club.veev.andlua.script.IScript;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: ILuaView
 */
public interface ILuaView {

    /**
     * 获取 View
     */
    View getView();

    /**
     * 装载脚本
     */
    LuaValue load(IScript script);

    Globals getGlobals();
}
