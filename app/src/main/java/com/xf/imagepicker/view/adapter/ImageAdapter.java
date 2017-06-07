package com.xf.imagepicker.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageInfo;
import com.xf.imagepicker.utils.imageloder.ImageLoader;
import com.xf.imagepicker.utils.imageloder.ImageLoaderFactory;

import java.util.List;

/**
 * Created by X-FAN on 2017/6/7.
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ImageInfo> mImageInfos;
    private ImageLoader.DisplayOption mDisplayOption;

    public ImageAdapter(List<ImageInfo> imageInfos) {
        mImageInfos = imageInfos;
        mDisplayOption = new ImageLoader.DisplayOption();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
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
