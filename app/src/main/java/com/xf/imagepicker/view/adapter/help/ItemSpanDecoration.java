package com.xf.imagepicker.view.adapter.help;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xf.imagepicker.utils.ImageUtil;

/**
 * Created by X-FAN on 2017/6/8.
 */

public class ItemSpanDecoration extends RecyclerView.ItemDecoration {
    private int mPx;
    private int mImageWith;
    private int mCloumnWith;


    /**
     * @param context
     * @param gap     间距 单位 dp
     */
    public ItemSpanDecoration(Context context, int gap) {
        mPx = ImageUtil.dp2px(context, gap);
        mImageWith = ImageUtil.getImageWith(context, gap);
        int mScrrenWith = ImageUtil.getScreenWith(context);
        mCloumnWith = mScrrenWith / 3;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        outRect.top = 0;
        outRect.bottom = mPx;
        if (position < 3) {//第一行
            outRect.top = mPx;
        }
        int tempPosition = position + 1;
        if (tempPosition % 3 == 1) {//第一列
            outRect.left = mPx;
            outRect.right = mCloumnWith - mPx - mImageWith;

        } else if (tempPosition % 3 == 2) {//第二列
            outRect.left = 2 * mPx - mCloumnWith + mImageWith;
            outRect.right = outRect.left;
        } else if (tempPosition % 3 == 0) {//第三列
            outRect.left = mCloumnWith - mPx - mImageWith;
            outRect.right = mPx;
        }
    }
}
