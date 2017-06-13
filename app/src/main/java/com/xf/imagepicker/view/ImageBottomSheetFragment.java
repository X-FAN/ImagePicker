package com.xf.imagepicker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageFolder;

import java.util.List;

/**
 * Created by X-FAN on 2017/6/13.
 */

public class ImageBottomSheetFragment extends BottomSheetDialogFragment {

    private List<ImageFolder> mImageFolders;

    private RecyclerView mImageFolderShow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_image, container, false);
        mImageFolderShow = (RecyclerView) view.findViewById(R.id.image_folder_show);
        mImageFolderShow.setLayoutManager(new GridLayoutManager(container.getContext(), 4));
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        return view;
    }

    public  void setData(List<ImageFolder> imageFolders){
        mImageFolders = imageFolders;
    }
}
