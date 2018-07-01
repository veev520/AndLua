package club.veev.andluademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import club.veev.andlua.AndLua;
import club.veev.andlua.script.LuaScriptFactory;
import club.veev.andlua.utils.FileUtil;
import club.veev.andlua.view.ILuaView;
import club.veev.andlua.view.TestView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

//        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript("print(TAG, DBG)"));

        mLinearLayout = findViewById(R.id.content);

        String f = FileUtil.getTestFolder() + "/lua.lua";
//        FileUtil.createNewFile(f, "context = ...\n" +
//                "\n" +
//                "local TextView = luajava.bindClass('android.widget.TextView')\n" +
//                "\n" +
//                "textView = TextView.new(context)\n" +
//                "textView:setText('你好')\n" +
//                "textView:setTextColor(0xFFFF0000)\n" +
//                "\n" +
//                "function getView()\n" +
//                "    return textView\n" +
//                "end");

        ILuaView luaView = new TestView();
        luaView.load(this, LuaScriptFactory.stringLuaScript(FileUtil.readFileToString(f)));
        mLinearLayout.addView(luaView.getView());

//        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript(FileUtil.readFileToString(f)));
    }

    private void requestPermission() {
        // 判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 拒绝?
                finish();
            } else {
                // 申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Log.i(TAG, "requestPower: 有存储权限");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}
