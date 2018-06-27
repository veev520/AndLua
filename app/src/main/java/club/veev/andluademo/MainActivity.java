package club.veev.andluademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import club.veev.andlua.AndLua;
import club.veev.andlua.script.LuaScriptFactory;
import club.veev.andlua.view.ILuaView;
import club.veev.andlua.view.TestView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript("print(TAG, DBG)"));

        mLinearLayout = findViewById(R.id.content);

        ILuaView luaView = new TestView();
        luaView.load(this, LuaScriptFactory.stringLuaScript("context = ...\n" +
                "\n" +
                "local TextView = luajava.bindClass('android.widget.TextView')\n" +
                "\n" +
                "textView = TextView.new(context)\n" +
                "textView:setText('你好')\n" +
                "\n" +
                "function getView()\n" +
                "    return textView\n" +
                "end"));
        mLinearLayout.addView(luaView.getView());
    }
}
