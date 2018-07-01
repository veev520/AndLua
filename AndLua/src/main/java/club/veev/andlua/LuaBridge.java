package club.veev.andlua;

import android.util.Log;

import org.luaj.vm2.*;
import org.luaj.vm2.ast.Str;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: LuaBridge 桥
 */
public class LuaBridge extends VarArgFunction {
    private static final String TAG = "LuaBridge";

    static final int INIT           = 0;
    static final int REGISTER       = 1;
    static final int CALL           = 2;
    static final int REMOVE         = 3;
    static final int BRIDGE         = 4;

    static final String[] NAMES = {
            "register",
            "call",
            "remove",
            "bridge"
    };

    public LuaBridge() {

    }

    /**
     * 获取 Globals 中的桥实例
     * @param globals
     * @return
     */
    public static LuaBridge get(Globals globals) {
        try {
            return (LuaBridge) CoerceLuaToJava.coerce(
                    globals.get("getBridge").invoke().arg1(), LuaBridge.class);
        } catch (Exception e) {
            Log.e(TAG, "get: 获取 LuaBridge 实例异常", e);
        }
        return  null;
    }

    @Override
    public Varargs invoke(Varargs args) {
        try {
            switch (opcode) {
                case INIT:
                    // LuaValue modname = args.arg1();
                    LuaValue env = args.arg(2);
                    LuaTable t = new LuaTable();
                    bind( t, this.getClass(), NAMES, REGISTER );
                    env.set("luabridge", t);
                    env.get("package").get("loaded").set("luabridge", t);
                    break;
                case REGISTER:
                    luaRegister(args);
                    break;
                case CALL:
                    luaCall(args);
                    break;
                case REMOVE:
                    break;
                case BRIDGE:
                    return CoerceJavaToLua.coerce(this);
                default:
                    System.out.println();
                    System.out.println("====== Start ======");

                    int narg = args.narg();
                    for (int i = 1; i <= narg; i++) {
                        System.out.println("index: " + i + "\targ: " + args.arg(i) + " " + args.arg(i).getClass().getSimpleName());
                    }

                    System.out.println("======  End  ======");
                    System.out.println();
                    break;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return NIL;
    }

    /**
     * lua 端 callHandler
     * @param args
     */
    private void luaCall(Varargs args) {
        LuaValue callback = NIL, data = NIL;
        switch (args.narg()) {
            case 3:
                callback = args.arg(3);
            case 2:
                data = args.arg(2);
            case 1:
                String name = args.arg(1).tojstring();
                Set<LuaHandler> set = mJavaHandler.get(name);
                if (set != null) {
                    for (LuaHandler h : set) {
                        h.handler(data, callback);
                    }
                }
            default:
                break;
        }
    }

    /**
     * lua 端 registerHandler
     * @param args
     */
    private void luaRegister(Varargs args) throws IllegalArgumentException {
        LuaFunction luaFunction = null;
        switch (args.narg()) {
            case 2:
                if (args.arg(2).isfunction()) {
                    luaFunction = args.arg(2).checkfunction();
                } else {
                    // 必须传入 Function
                    throw new IllegalArgumentException("The second arg must be function");
                }
            case 1:
                String name = args.arg(1).tojstring();
                if (luaFunction != null) {
                    Set<LuaFunction> valueSet = mLuaHandler.get(name);
                    if (valueSet != null) {
                        valueSet.add(luaFunction);
                    } else {
                        valueSet = new HashSet<>();
                        valueSet.add(luaFunction);
                        mLuaHandler.put(name, valueSet);
                    }
                }
            default:
                break;
        }
    }

    private static Map<String, Set<LuaHandler>> mJavaHandler = new HashMap<>();
    private static Map<String, Set<LuaFunction>> mLuaHandler = new HashMap<>();

    /**
     * java 端 registerHandler
     * @param name          桥名称
     * @param handler       回调
     */
    public static void registerHandler(String name, LuaHandler handler) {
        Set<LuaHandler> set = mJavaHandler.get(name);
        if (set == null) {
            set = new HashSet<>();
            set.add(handler);
            mJavaHandler.put(name, set);
        } else {
            set.add(handler);
        }
    }

    public static void callHandler(String name) {
        callHandler(name, null);
    }

    public static void callHandler(String name, String data) {
        callHandler(name, data, null);
    }

    /**
     * java 端 callHandler
     * @param name          桥名称
     * @param data          数据
     * @param callback      回调
     */
    public static void callHandler(String name, String data, LuaCallback callback) {
        Set<LuaFunction> set = mLuaHandler.get(name);
        if (set != null) {
            for (LuaFunction f : set) {
                f.invoke(CoerceJavaToLua.coerce(data), CoerceJavaToLua.coerce(callback));
            }
        }
    }

    /**
     * 移除 名称为name的所有 handler
     */
    public static void removeHandler(String name) {
        mJavaHandler.remove(name);
    }

    /**
     * 移除 handler
     */
    public static void removeHandle(String name, LuaHandler handler) {
        if (handler == null) {
            return;
        }

        Set<LuaHandler> set = mJavaHandler.get(name);
        if (set == null) {
            return;
        }

        set.remove(handler);
    }

    /**
     * 移除handler
     * 不建议使用
     */
    public static void removeHandler(LuaHandler handler) {
        for (Set<LuaHandler> h : mJavaHandler.values()) {
            h.remove(handler);
        }
    }

    public interface LuaHandler{
        void handler(LuaValue data, LuaValue callback);
    }

    public interface LuaCallback {
        void call(Object data);
    }
}
