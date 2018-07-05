package club.veev.andluademo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;

import club.veev.andlua.utils.DisplayUtil;

public class TestItemDecoration extends ItemDecoration {

    private static final String TAG = "TestItemDecoration";

    private Paint mPaint;

    {
        Log.i(TAG, "instance initializer: ");

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public TestItemDecoration() {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = DisplayUtil.dp2px(8);
    }
}
