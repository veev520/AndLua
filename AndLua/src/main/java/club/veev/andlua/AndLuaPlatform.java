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
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
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
        globals.load(new LuaAndroidLib());

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

    /**
     * 扩展 call方法
     * 使其可以传多个参数
     * @param fun       需要执行的方法
     * @param values    参数
     */
    public static LuaValue call(LuaValue fun, Object ... values) {
        try {
            // 参考 LuaValue的call方法
            LuaValue[] args = new LuaValue[values.length];
            int i = 0;
            for (Object obj : values) {
                args[i++] = CoerceJavaToLua.coerce(obj);
            }
            return fun.invoke(args).arg1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LuaValue.NIL;
    }
}
