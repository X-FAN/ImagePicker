package com.xf.imagepicker.view;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
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

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by X-FAN on 2017/6/6.
 */
public class MainActivity extends AppCompatActivity {
    public static final int SIGN_IMAGE = 2402;
    private final int PERMISSION_READ_EXTERNAL_STORAGE = 2401;

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
        mImageShow.setLayoutManager(new GridLayoutManager(this, 3));//默认三列，其他暂时不支持等间距
        mImageShow.setAdapter(mImageAdapter = new ImageAdapter(this, null, 5));
        mImageShow.addItemDecoration(new ItemSpanDecoration(this, 5));
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
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_STORAGE);
                } else {
                    mImageFolders = ImageUtil.getAllImages(MainActivity.this);
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
                        ((MainActivity) activity).updateImage();
                    }
                    break;
            }
        }
    }
}
