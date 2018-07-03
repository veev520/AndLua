package club.veev.andluademo;

import android.app.Application;

import club.veev.andlua.AndLua;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: App
 */
public class App extends Application {

    private static App sApp = null;
    public static App get() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        AndLua.init(this);
    }
}
