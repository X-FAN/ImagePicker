package com.xf.imagepicker.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageFolder;
import com.xf.imagepicker.bean.ImageInfo;
import com.xf.imagepicker.view.adapter.ImageFolderAdapter;
import com.xf.imagepicker.view.adapter.help.OnItemTouchListener;

import java.util.List;

/**
 * Created by X-FAN on 2017/6/13.
 */

public class ImageBottomSheetDialog extends BottomSheetDialog {

    private List<ImageFolder> mImageFolders;

    private RecyclerView mImageFolderShow;
    private ImageFolderAdapter mImageFolderAdapter;
    private Context mContext;
    private OnClickListener mOnClickListener;

    public ImageBottomSheetDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet_image, null);
        mImageFolderShow = (RecyclerView) view.findViewById(R.id.image_folder_show);
        mImageFolderShow.setLayoutManager(new GridLayoutManager(mContext, 4));
        mImageFolderShow.setAdapter(mImageFolderAdapter = new ImageFolderAdapter(mImageFolders));
        mImageFolderShow.addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public void onItemClick(int position) {
                ImageFolder imageFolder = mImageFolders.get(position);
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(imageFolder.getImageInfos());
                    dismiss();
                }
            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setData(List<ImageFolder> imageFolders) {
        mImageFolders = imageFolders;
        if (mImageFolderAdapter != null) {
            mImageFolderAdapter.setNewData(mImageFolders);
        }

    }

    public interface OnClickListener {
        public void onClick(List<ImageInfo> imageInfos);
    }
}
