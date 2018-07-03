package club.veev.andlua.utils;

import club.veev.andlua.AndLua;

public class DisplayUtil {

    public static int dp2px(int dp) {
        final float scale = AndLua.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(final float pxValue) {
        final float scale = AndLua.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    
    public static int sp2px(final float spValue) {
        final float fontScale = AndLua.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    
    public static int px2sp(final float pxValue) {
        final float fontScale = AndLua.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
