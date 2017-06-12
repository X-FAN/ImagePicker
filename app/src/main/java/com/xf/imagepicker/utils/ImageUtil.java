package com.xf.imagepicker.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.xf.imagepicker.bean.ImageFolder;
import com.xf.imagepicker.bean.ImageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by X-FAN on 2017/6/6.
 */

public class ImageUtil {

    private static final String TAG = "ImageUtil";

    private ImageUtil() {
    }

    /**
     * 获取所有图片及所属目录信息
     *
     * @param context
     * @return
     */
    public static List<ImageFolder> getAllImages(Context context) {
        Map<String, ImageFolder> imageFolderRecord = new HashMap<>();
        context = context.getApplicationContext();
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Thumbnails.DATA
        };
        List<ImageFolder> imageFolders = new ArrayList<>();
        ImageFolder allImageFolder = new ImageFolder("全部");
        imageFolders.add(allImageFolder);
        Cursor cursor = null;
        try {
            cursor = MediaStore.Images.Media.query(context.getContentResolver(),
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, "", null,
                    MediaStore.Images.Media.DATE_TAKEN + " DESC");//按时间倒序获取图片
            int bucketNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);//获得图片目录名称在“表”中的位置
            while (cursor.moveToNext()) {
                String bucketName = cursor.getString(bucketNameColumn);
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String path = cursor.getString(dataColumn);
                File file = new File(path);
                if (file.exists() && file.length() > 0) {
                    ImageInfo imageInfo = new ImageInfo(path);
                    allImageFolder.addImageInfo(imageInfo);//放入“全部”目录下
                    ImageFolder imageFolder = imageFolderRecord.get(bucketName);
                    if (imageFolder == null) {//如若还没有该“目录”则新建一个
                        imageFolder = new ImageFolder(bucketName);
                        imageFolders.add(imageFolder);
                        imageFolderRecord.put(bucketName, imageFolder);//将该“目录”记录下来
                    }
                    imageFolder.addImageInfo(imageInfo);//图片信息放入该目录内
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return imageFolders;
    }

    /**
     * 根据间距算出图片的宽度,默认三列
     *
     * @param context
     * @param dp      间距单位为 dp
     * @return
     */
    public static int getImageWith(Context context, int dp) {
        int px = dp2px(context, dp);
        int screenWith = getScreenWith(context);
        return (screenWith - 4 * px) / 3;
    }

    /**
     * dp 转换成 px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWith(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
