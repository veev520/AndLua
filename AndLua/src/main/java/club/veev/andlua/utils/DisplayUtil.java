package club.veev.andlua.utils;

import club.veev.andlua.AndLua;

public class DisplayUtil {

    public static int dp2px(int dp) {
        final float scale = AndLua.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
