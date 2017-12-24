package com.xf.imagepicker.bean;

import com.xf.imagepicker.view.IMultiChoice;

/**
 * Created by X-FAN on 2017/6/6.
 */

public class ImageInfo implements IMultiChoice {
    private String path;

    public ImageInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean isSelected() {
        return false;
    }
}
