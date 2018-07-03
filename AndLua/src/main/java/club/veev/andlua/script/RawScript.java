package club.veev.andlua.script;

import android.support.annotation.RawRes;

import java.io.InputStream;

import club.veev.andlua.AndLua;

public class RawScript implements IScript {

    private InputStream mInputStream;
    private String mMode;
    private String mName;

    public RawScript(@RawRes int id) {
        mInputStream = AndLua.getContext().getResources().openRawResource(id);
        mMode = "t";
        mName = AndLua.getContext().getResources().getResourceName(id);
    }
    @Override
    public InputStream getInputStream() {
        return mInputStream;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getMode() {
        return mMode;
    }
}
