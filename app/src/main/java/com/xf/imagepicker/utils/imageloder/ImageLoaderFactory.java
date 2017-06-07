package com.xf.imagepicker.utils.imageloder;

import android.content.Context;

/**
 * Created by X-FAN on 2017/3/28.
 */

public class ImageLoaderFactory {
    private static volatile ImageLoader mImageLoader;

    public static void init(Context context) {
        if (mImageLoader == null) {
            synchronized (ImageLoaderFactory.class) {
                if (mImageLoader == null) {
                    mImageLoader = new GlideImageLoader(context.getApplicationContext());
                }
            }
        }
    }

    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            throw new NullPointerException("you should call method init() first ");
        }
        return mImageLoader;
    }

}
