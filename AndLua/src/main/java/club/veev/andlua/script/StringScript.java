package club.veev.andlua.script;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringScript implements IScript {

    private InputStream mInputStream;
    private String mMode;
    private String mName;

    public StringScript(String script) {
        this(script, "StringScript@" + script.hashCode(), "t");
    }

    public StringScript(String script, String name) {
        this(script, name, "t");
    }

    public StringScript(String script, String name, String mode) {
        if (TextUtils.isEmpty(script)) {
            script = ";";
        }
        mInputStream = new ByteArrayInputStream(script.getBytes());
        mMode = mode;
        mName = name;
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
