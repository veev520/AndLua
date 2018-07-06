package club.veev.andlua.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import org.luaj.vm2.Globals;
import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import club.veev.andlua.AndLua;
import club.veev.andlua.AndLuaPlatform;
import club.veev.andlua.script.IScript;
import club.veev.andlua.script.LuaScriptFactory;
import club.veev.andlua.utils.DisplayUtil;

/**
 * 自定义View
 */
public class CustomLuaView extends View implements ILuaView {
    private static final String TAG = "CustomLuaView";

    private Globals mGlobals;
    private Context mContext;

    private LuaValue mLuaOnDraw, mLuaOnMeasure, mLuaOnSizeChanged,
            mLuaOnLayout, mLuaOnTouchEvent;

    public CustomLuaView(Context context, IScript script) {
        super(context);

        mContext = context;
        init(script);
    }

    public CustomLuaView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLuaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomLuaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public LuaValue load(IScript script) {
        return mGlobals.load(script.getInputStream(), script.getName(), script.getMode(), mGlobals);
    }

    @Override
    public Globals getGlobals() {
        return mGlobals;
    }

    private void init(IScript script) {
        mGlobals = AndLuaPlatform.customGlobals();

        load(LuaScriptFactory.andluaScript()).call();
        load(LuaScriptFactory.debugScript()).call();

        load(script).call(CoerceJavaToLua.coerce(mContext), CoerceJavaToLua.coerce(this));

        // 需要自己处理高度
        setMinimumHeight(DisplayUtil.dp2px(100));

        mLuaOnDraw = mGlobals.get("onDraw");
        mLuaOnLayout = mGlobals.get("onLayout");
        mLuaOnMeasure = mGlobals.get("onMeasure");
        mLuaOnTouchEvent = mGlobals.get("onTouchEvent");
        mLuaOnSizeChanged = mGlobals.get("onSizeChanged");

        LuaValue f = mGlobals.get("init");
        if (!f.isnil()) {
            try {
                f.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!mLuaOnMeasure.isnil()) {
            try {
                mLuaOnMeasure.call(CoerceJavaToLua.coerce(widthMeasureSpec),
                        CoerceJavaToLua.coerce(heightMeasureSpec));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        if (!mLuaOnSizeChanged.isnil()) {
            AndLuaPlatform.call(mLuaOnSizeChanged, w, h, oldw, oldh);
        } else {
            super.onSizeChanged(w, h, oldw, oldh);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        if (!mLuaOnLayout.isnil()) {
            AndLuaPlatform.call(mLuaOnLayout, changed, left, top, right, bottom);
        } else {
            super.onLayout(changed, left, top, right, bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!mLuaOnDraw.isnil()) {
            try {
                mLuaOnDraw.call(CoerceJavaToLua.coerce(canvas));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.onDraw(canvas);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LuaValue f = mGlobals.get("onKeyDown");
        if (!f.isnil()) {
            try {
                return f.call(CoerceJavaToLua.coerce(keyCode), CoerceJavaToLua.coerce(event)).toboolean();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        LuaValue f = mGlobals.get("onKeyUp");
        if (!f.isnil()) {
            try {
                return f.call(CoerceJavaToLua.coerce(keyCode), CoerceJavaToLua.coerce(event)).toboolean();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!mLuaOnTouchEvent.isnil()) {
            try {
                return mLuaOnTouchEvent.call(CoerceJavaToLua.coerce(event)).toboolean();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {

        LuaValue f = mGlobals.get("onTrackballEvent");
        if (!f.isnil()) {
            try {
                return f.call(CoerceJavaToLua.coerce(event)).toboolean();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onTrackballEvent(event);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        LuaValue f = mGlobals.get("onWindowVisibilityChanged");
        if (!f.isnil()) {
            try {
                f.call(CoerceJavaToLua.coerce(visibility));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.onWindowVisibilityChanged(visibility);
        }
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {

        LuaValue f = mGlobals.get("onWindowSystemUiVisibilityChanged");
        if (!f.isnil()) {
            try {
                f.call(CoerceJavaToLua.coerce(visible));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.onWindowSystemUiVisibilityChanged(visible);
        }
    }
}
