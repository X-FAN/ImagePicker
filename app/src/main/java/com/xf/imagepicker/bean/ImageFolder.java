package com.xf.imagepicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X-FAN on 2017/6/6.
 */

public class ImageFolder  implements Parcelable{
    private String folderName;//图片所属文件夹名
    private List<ImageInfo> imageInfos;

    public ImageFolder(String folderName) {
        this.folderName = folderName;
        imageInfos = new ArrayList<>();
    }

    protected ImageFolder(Parcel in) {
        folderName = in.readString();
    }

    public static final Creator<ImageFolder> CREATOR = new Creator<ImageFolder>() {
        @Override
        public ImageFolder createFromParcel(Parcel in) {
            return new ImageFolder(in);
        }

        @Override
        public ImageFolder[] newArray(int size) {
            return new ImageFolder[size];
        }
    };

    public String getFolderName() {
        return folderName;
    }

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    public void addImageInfo(ImageInfo imageInfo) {
        imageInfos.add(imageInfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(folderName);
    }
}
