package com.xf.imagepicker.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.bean.ImageFolder;
import com.xf.imagepicker.utils.ImageUtil;
import com.xf.imagepicker.utils.imageloder.ImageLoaderFactory;
import com.xf.imagepicker.view.adapter.ImageAdapter;
import com.xf.imagepicker.view.adapter.help.ItemSpanDecoration;
import com.xf.imagepicker.view.adapter.help.OnItemTouchListener;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by X-FAN on 2017/6/6.
 */
public class ImageSelectActivity extends AppCompatActivity {
    public static final int SIGN_IMAGE = 2402;
    private final int PERMISSION_READ_EXTERNAL_STORAGE = 2401;
    private final int REQUEST_CROP = 2403;

    private List<ImageFolder> mImageFolders;

    private ImageAdapter mImageAdapter;
    private RecyclerView mImageShow;
    private MyHandler mMyHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderFactory.init(this);
        mImageShow = (RecyclerView) findViewById(R.id.image_show);
        mImageShow.setLayoutManager(new GridLayoutManager(this, 3));//默认三列，其他暂时不支持等间距,请不要填写其他数值
        mImageShow.setAdapter(mImageAdapter = new ImageAdapter(this, null, 2));
        mImageShow.addItemDecoration(new ItemSpanDecoration(this, 2));
        mImageShow.addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public void onItemClick(int position) {
                if (mImageAdapter.getData().size() > 0) {//跳转到图片裁剪界面
                    String sourceUri = mImageAdapter.getData().get(position).getPath();
                    UCrop.Options options = new UCrop.Options();
                    options.setHideBottomControls(true);
                    UCrop.of(Uri.fromFile(new File(sourceUri)), Uri.fromFile(new File(getCacheDir(), "crop")))
                            .withAspectRatio(1, 1)
                            .withMaxResultSize(500, 500)
                            .withOptions(options)
                            .start(ImageSelectActivity.this);
                }
            }
        });
        getAllImages();
    }

    public void updateImage() {
        if (mImageFolders != null && mImageFolders.size() > 0) {
            mImageAdapter.setNewData(mImageFolders.get(0).getImageInfos());
        }
    }

    /**
     * 获取所有图片
     */
    private void getAllImages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (ContextCompat.checkSelfPermission(ImageSelectActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ImageSelectActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_STORAGE);
                } else {
                    mImageFolders = ImageUtil.getAllImages(ImageSelectActivity.this);
                    mMyHandler.sendEmptyMessage(SIGN_IMAGE);
                }
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllImages();
            } else {
                getAllImages();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    private static class MyHandler extends Handler {
        private WeakReference<Activity> mWeakReference;

        public MyHandler(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SIGN_IMAGE:
                    Activity activity = mWeakReference.get();
                    if (activity != null) {
                        ((ImageSelectActivity) activity).updateImage();
                    }
                    break;
            }
        }
    }
}
