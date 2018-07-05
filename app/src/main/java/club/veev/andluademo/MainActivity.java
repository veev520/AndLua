package club.veev.andluademo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import club.veev.andlua.view.ILuaView;
import club.veev.andlua.view.LuaView;
import club.veev.andluademo.entity.LuaBean;
import club.veev.andluademo.entity.Test1;
import club.veev.andluademo.entity.TestBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LinearLayout mLinearLayout;
    private RecyclerView mRecyclerView;
    private TestAdapter mLuaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

//        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript("print(TAG, DBG)"));

        mLinearLayout = findViewById(R.id.content);
        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new TestItemDecoration());

        mLuaAdapter = new TestAdapter();
        mRecyclerView.setAdapter(mLuaAdapter);
        mLuaAdapter.setData(initData());

//        String f = FileUtil.getTestFolder() + "/lua.lua";
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


//        ILuaView luaView = LuaView.load(this, FileUtil.readFileToString(f));
//        if (luaView.getView() != null) {
//            mLinearLayout.addView(luaView.getView());
//        }

        getLua();

//        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript(FileUtil.readFileToString(f)));
    }

    private List<TestBean> initData() {
        List<TestBean> list = new ArrayList<>();

        Test1 test1 = new Test1("Lua 特性", "轻量级", "它用标准C语言编写并以源代码形式开放，编译后仅仅一百余K，可以很方便的嵌入别的程序里。");
        list.add(new TestBean(TestBean.TYPE_TEST_1, test1));

        Test1 test2 = new Test1("Lua 特性", "可扩展", "Lua提供了非常易于使用的扩展接口和机制：由宿主语言(通常是C或C++)提供这些功能，Lua可以使用它们，就像是本来就内置的功能一样。");
        list.add(new TestBean(TestBean.TYPE_TEST_1, test2));

        return list;
    }

    private void getLua() {
        getLua("http://192.168.137.1:5000/1");
        getLua("http://192.168.137.1:5000/2");
    }

    private void getLua(final String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();

                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        LuaBean luaBean = new LuaBean();
                        luaBean.setScript(content);
                        if (url.endsWith("2")) {
                            luaBean.setType(LuaBean.TYPE_CUSTOM);
                        }

                        mLuaAdapter.add(new TestBean(TestBean.TYPE_LUA, luaBean));
                    }
                });

                Log.i(TAG, "onResponse: " + content);
            }
        });
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
