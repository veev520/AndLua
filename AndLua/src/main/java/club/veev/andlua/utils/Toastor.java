package club.veev.andlua.utils;

import android.annotation.SuppressLint;
import android.widget.Toast;

import club.veev.andlua.AndLua;

public class Toastor {

    private static Toast sToast;

    /**
     * 弹提示
     *
     * 密集地调用时, 不会新建新的Toast实例
     */
    @SuppressLint("ShowToast")
    public static void single(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(AndLua.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }

        sToast.show();
    }

    public static void show(String msg) {
        Toast.makeText(AndLua.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
