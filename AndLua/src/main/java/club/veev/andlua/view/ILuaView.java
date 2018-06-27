package club.veev.andlua.view;

import android.content.Context;
import android.view.View;

import club.veev.andlua.script.ILuaScript;

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
    void load(Context context, ILuaScript script);
}
