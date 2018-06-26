package club.veev.andlua;

import android.app.Application;
import android.content.Context;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: AndLua
 */
public class AndLua {

    private static Application sApp;

    private static boolean DBG = true;
    private static String TAG = "AndLua";

    public static void init(Application application) {
        sApp = application;
    }

    public static void checkInit() throws ExceptionInInitializerError {
        if (sApp == null) {
            throw new ExceptionInInitializerError("AndLua must be init");
        }
    }

    public static boolean isDebug() {
        return DBG;
    }

    public static String getTAG() {
        return TAG;
    }

    public static Context getContext() {
        checkInit();
        return sApp;
    }

    public static LuaRunner runner() {
        return new LuaRunner();
    }


}
