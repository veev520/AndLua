package club.veev.andlua;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.LuajavaLib;

/**
 * Created by Veev on 2018/7/3.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    解决luaJavaLib 无法反射 自己写的class的问题
 */
public class LuaAndroidLib extends LuajavaLib {

    static final int INIT           = 0;
    static final int BINDCLASS      = 1;
    static final int NEWINSTANCE	= 2;
    static final int NEW			= 3;
    static final int CREATEPROXY	= 4;
    static final int LOADLIB		= 5;

    static final String[] NAMES = {
            "bindClass",
            "newInstance",
            "new",
            "createProxy",
            "loadLib",
    };

    public LuaAndroidLib() {
    }

    @Override
    public Varargs invoke(Varargs args) {
        if (opcode == INIT) {
            LuaValue env = args.arg(2);
            LuaTable t = new LuaTable();
            bind( t, this.getClass(), NAMES, BINDCLASS );
            env.set("luaandroid", t);
            env.get("package").get("loaded").set("luaandroid", t);
            return t;
        } else {
            return super.invoke(args);
        }
    }

    @Override
    protected Class classForName(String name) throws ClassNotFoundException {
        return Class.forName(name, true, getClass().getClassLoader());
    }
}
