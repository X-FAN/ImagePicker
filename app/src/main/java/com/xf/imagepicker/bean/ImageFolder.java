package com.xf.imagepicker.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X-FAN on 2017/6/6.
 */

public class ImageFolder {
    private String folderName;//图片所属文件夹名
    private List<ImageInfo> imageInfos;

    public ImageFolder(String folderName) {
        this.folderName = folderName;
        imageInfos = new ArrayList<>();
    }

    public String getFolderName() {
        return folderName;
    }

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    public void addImageInfo(ImageInfo imageInfo) {
        imageInfos.add(imageInfo);
    }
}
