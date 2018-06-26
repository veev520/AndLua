package club.veev.andlua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.CoroutineLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.JseOsLib;
import org.luaj.vm2.lib.jse.LuajavaLib;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: AndLuaPlatform
 */
public class AndLuaPlatform {

    public static Globals customGlobals(LuaValue... libs) {
        Globals globals = new Globals();
        globals.load(new JseBaseLib());
        globals.load(new PackageLib());
        globals.load(new Bit32Lib());
        globals.load(new TableLib());
        globals.load(new StringLib());
        globals.load(new CoroutineLib());
        globals.load(new JseMathLib());
        globals.load(new JseIoLib());
        globals.load(new JseOsLib());
        globals.load(new LuajavaLib());

        // AndLua
        globals.load(new LuaBridge());

        // Custom
        if (libs != null) {
            for (LuaValue function : libs) {
                globals.load(function);
            }
        }
        LoadState.install(globals);
        LuaC.install(globals);
        return globals;
    }
}
