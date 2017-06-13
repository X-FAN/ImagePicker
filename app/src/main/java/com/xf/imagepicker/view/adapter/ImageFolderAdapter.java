package com.xf.imagepicker.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageFolder;
import com.xf.imagepicker.utils.imageloder.ImageLoaderFactory;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class ImageFolderAdapter  extends RecyclerView.Adapter<ImageFolderAdapter.ViewHolder> {

    private List<ImageFolder> mImageFolders;

    public ImageFolderAdapter(List<ImageFolder> imageFolders) {
        mImageFolders = imageFolders;
    }

    @Override
    public ImageFolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageFolder imageFolder =  mImageFolders.get(position);
        ImageLoaderFactory.getInstance().displayImage(holder.mCoverImage,imageFolder.getCoverImagePath());
    }


    @Override
    public int getItemCount() {
        return mImageFolders==null?0:mImageFolders.size();
    }

    public void setNewData(List<ImageFolder> imageFolders){
        mImageFolders = imageFolders;
        notifyDataSetChanged();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
         public TextView mFolderName;
        public ImageView mCoverImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mFolderName = (TextView) itemView.findViewById(R.id.folder_name);
            mCoverImage = (ImageView) itemView.findViewById(R.id.cover_image);
        }
    }
}
