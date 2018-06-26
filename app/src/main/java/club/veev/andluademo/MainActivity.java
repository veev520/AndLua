package club.veev.andluademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import club.veev.andlua.AndLua;
import club.veev.andlua.script.LuaScriptFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndLua.runner().runScript(LuaScriptFactory.stringLuaScript(""));
    }
}
