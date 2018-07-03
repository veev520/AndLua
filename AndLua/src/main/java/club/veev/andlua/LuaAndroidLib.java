package club.veev.andlua;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.LibFunction;
import org.luaj.vm2.lib.VarArgFunction;

public class LuaAndroidLib extends VarArgFunction {

    static final int INIT           = 0;
    static final int BINDCLASS      = 1;
    static final int NEWINSTANCE	= 2;
    static final int NEW			= 3;

    static final String[] NAMES = {
            "bindClass",
            "newInstance",
            "new"
    };

    public LuaAndroidLib() {
    }

    public Varargs invoke(Varargs args) {
        try {
            switch ( opcode ) {
                case INIT: {
                    // LuaValue modname = args.arg1();
                    LuaValue env = args.arg(2);
                    LuaTable t = new LuaTable();
                    bind( t, this.getClass(), NAMES, BINDCLASS );
                    env.set("luajava", t);
                    env.get("package").get("loaded").set("luajava", t);
                    return t;
                }
                case BINDCLASS: {
                    final Class clazz = classForName(args.checkjstring(1));
                    return bindClass(args.checkjstring(1));
                }
                case NEWINSTANCE:
                case NEW: {
                    // get constructor
                    final LuaValue c = args.checkvalue(1);
                    final Class clazz = (opcode==NEWINSTANCE? classForName(c.tojstring()): (Class) c.checkuserdata(Class.class));
                    final Varargs consargs = args.subargs(2);
                    return NIL;
                }
                default:
                    throw new LuaError("not yet supported: "+this);
            }
        } catch (LuaError e) {
            throw e;
        } catch (Exception e) {
            throw new LuaError(e);
        }
    }

    // load classes using app loader to allow luaj to be used as an extension
    protected Class classForName(String name) throws ClassNotFoundException {
        return Class.forName(name, true, ClassLoader.getSystemClassLoader());
    }

    private LuaValue bindClass(String name) {
        try {
            Class clazz = Class.forName(name, true, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return NIL;
    }

    private LuaValue newInstance(String name) {
        return NIL;
    }
}
