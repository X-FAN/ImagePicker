package com.xf.imagepicker.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageInfo;
import com.xf.imagepicker.utils.ImageUtil;
import com.xf.imagepicker.utils.imageloder.ImageLoader;
import com.xf.imagepicker.utils.imageloder.ImageLoaderFactory;

import java.util.List;

/**
 * Created by X-FAN on 2017/6/7.
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mImageWith;
    private List<ImageInfo> mImageInfos;

    private ImageLoader.DisplayOption mDisplayOption;


    public ImageAdapter(Context context, List<ImageInfo> imageInfos, int dp) {
        mImageInfos = imageInfos;
        mDisplayOption = new ImageLoader.DisplayOption();
        mImageWith = ImageUtil.getImageWith(context, dp);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(mImageWith, mImageWith));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageInfo imageInfo = mImageInfos.get(position);
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.image);
        ImageLoaderFactory.getInstance().displayImage(imageView, imageInfo.getPath());
    }

    @Override
    public int getItemCount() {
        return mImageInfos == null ? 0 : mImageInfos.size();
    }

    public void setNewData(List<ImageInfo> imageInfos) {
        mImageInfos = imageInfos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
