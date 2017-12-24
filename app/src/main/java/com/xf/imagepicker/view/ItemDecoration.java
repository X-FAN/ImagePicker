package com.xf.imagepicker.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by X-FAN on 2017/8/20.
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {
    private boolean mShowMulti = false;
    private int mSelectedWidth;
    private int mSelectedHeight;
    private int mUnSelectedWidth;
    private int mUnSelectedHeight;
    private List<? extends IMultiChoice> mDatas;
    private Drawable mSelected;
    private Drawable mUnSelected;

    public ItemDecoration(Drawable selected, Drawable unSelected, List<? extends IMultiChoice> datas, boolean showMulti) {
        mSelected = selected;
        mUnSelected = unSelected;
        mDatas = datas;
        mShowMulti = showMulti;
        mSelectedWidth = mSelected.getIntrinsicWidth();
        mSelectedHeight = mSelected.getIntrinsicHeight();
        mUnSelectedWidth = mUnSelected.getIntrinsicWidth();
        mUnSelectedHeight = mUnSelected.getIntrinsicHeight();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int width = child.getWidth();
            int height = child.getHeight();
            int centerY = child.getTop() + height / 2;
            int centerX = child.getLeft() + width / 2;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = layoutParams.getViewLayoutPosition();
            if (mShowMulti && mDatas != null && mDatas.size() != 0) {
                IMultiChoice multiChoice = mDatas.get(position);
                if (multiChoice.isSelected()) {
                    int left = centerX - mSelectedWidth / 2;
                    int top = centerY - mSelectedHeight / 2;
                    int right = centerX + mSelectedWidth / 2;
                    int bottom = centerY + mSelectedHeight / 2;
                    mSelected.setBounds(left, top, right, bottom);
                    mSelected.draw(c);
                } else {
                    int left = centerX - mUnSelectedWidth / 2;
                    int top = centerY - mUnSelectedHeight / 2;
                    int right = centerX + mUnSelectedWidth / 2;
                    int bottom = centerY + mUnSelectedHeight / 2;
                    mSelected.setBounds(left, top, right, bottom);
                    mSelected.draw(c);
                }
            }
        }
        c.restore();
    }

    public void setShowMulti(boolean mShowMulti) {
        this.mShowMulti = mShowMulti;
    }

    public void setData(List<? extends IMultiChoice> datas) {
        mDatas = datas;
    }
}
