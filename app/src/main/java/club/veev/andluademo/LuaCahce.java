package club.veev.andluademo;

import android.util.SparseArray;

import club.veev.andluademo.entity.LuaBean;

/**
 * Created by Veev on 2018/7/7.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    LuaCahce
 */
public class LuaCahce {

    private static SparseArray<LuaBean> sLuaViewArray = new SparseArray<>();

    public static void put(int i, LuaBean script) {
        sLuaViewArray.put(i, script);
    }

    public static LuaBean get(int i) {
        return sLuaViewArray.get(i);
    }
}
