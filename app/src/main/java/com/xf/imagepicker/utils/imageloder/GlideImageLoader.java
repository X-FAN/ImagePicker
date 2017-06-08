package com.xf.imagepicker.utils.imageloder;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by X-FAN on 2017/3/27.
 */

class GlideImageLoader implements ImageLoader {
    private RequestManager mRequestManager;


    public GlideImageLoader(Context context) {
        mRequestManager = Glide.with(context);
    }


    @Override
    public void displayImage(ImageView iv, String imageUrl) {
        displayImage(iv, imageUrl, null);
    }

    @Override
    public void displayImage(ImageView iv, String imageUrl, DisplayOption option) {
        DrawableTypeRequest<String> drawableTypeRequest = mRequestManager.load(imageUrl);
        if (option != null) {
            if (option.placeHolderResId != DisplayOption.NONE && option.loadErrorResId != DisplayOption.NONE) {
                drawableTypeRequest.placeholder(option.placeHolderResId)
                        .error(option.loadErrorResId)
                        .into(iv);
            } else if (option.placeHolderResId != DisplayOption.NONE) {
                drawableTypeRequest.placeholder(option.placeHolderResId)
                        .into(iv);
            } else if (option.loadErrorResId != DisplayOption.NONE) {
                drawableTypeRequest.error(option.loadErrorResId)
                        .dontAnimate()
                        .into(iv);
            }
        } else {
            drawableTypeRequest.into(iv);
        }
    }
}

/**
 * Picasso 示例
 */
/*public class PicassoImageLoader implements ImageLoader {
    private Picasso mPicasso;

    public PicassoImageLoader(Context context) {
        mPicasso = Picasso.with(context);
    }

    @Override
    public void displayImage(ImageView iv, String imageUrl) {
        mPicasso.load(imageUrl).into(iv);
    }

    @Override
    public void displayImage(ImageView iv, String imageUrl, DisplayOption option) {
        if (option != null) {
            if (option.placeHolderResId != DisplayOption.NONE && option.loadErrorResId != DisplayOption.NONE) {
                mPicasso.load(imageUrl)
                        .placeholder(option.placeHolderResId)
                        .error(option.loadErrorResId)
                        .into(iv);
            } else if (option.placeHolderResId != DisplayOption.NONE) {
                mPicasso.load(imageUrl)
                        .placeholder(option.placeHolderResId)
                        .into(iv);
            } else if (option.loadErrorResId != DisplayOption.NONE) {
                mPicasso.load(imageUrl)
                        .error(option.placeHolderResId)
                        .into(iv);
            }
        }
    }else{
        mPicasso.load(imageUrl).into(iv);
    }
}*/
