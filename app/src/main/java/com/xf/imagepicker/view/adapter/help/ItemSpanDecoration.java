package com.xf.imagepicker.view.adapter.help;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by X-FAN on 2017/6/8.
 */

public class ItemSpanDecoration extends RecyclerView.ItemDecoration {
    private int mPx;
    private int mHalfPx;

    public ItemSpanDecoration(Context context, int dp) {
        mPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        mHalfPx = mPx / 2;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position < 3) {
            outRect.top = mPx;
        }
        outRect.bottom = mPx;
        int tempPosition = position + 1;
        if (tempPosition % 3 == 1) {//第一列
            outRect.left = mPx;
            outRect.right = mHalfPx;
        } else if (tempPosition % 3 == 2) {//第二列
            outRect.left = mHalfPx;
            outRect.right = mHalfPx;
        } else if (tempPosition % 3 == 0) {//第三列
            outRect.left = mHalfPx;
            outRect.right = mPx;
        }
    }
}
