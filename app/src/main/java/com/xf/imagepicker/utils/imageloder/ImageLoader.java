package com.xf.imagepicker.utils.imageloder;

import android.widget.ImageView;

/**
 * Created by X-FAN on 2017/3/27.
 */

public interface ImageLoader {

    public void displayImage(ImageView iv, String imageUrl);

    public void displayImage(ImageView iv, String imageUrl, DisplayOption option);

    public static class DisplayOption {
        public static final int NONE = -1;

        /**
         * 加载失败的资源id
         */
        public int loadErrorResId = NONE;
        /**
         * 占位资源id
         */
        public int placeHolderResId = NONE;
    }
}
