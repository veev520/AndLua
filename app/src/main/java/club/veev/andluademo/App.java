package club.veev.andluademo;

import android.app.Application;

import club.veev.andlua.AndLua;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: App
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndLua.init(this);
    }
}
