package club.veev.andlua.script;

import java.io.InputStream;

public interface IScript {

    InputStream getInputStream();

    String getName();

    String getMode();
}
