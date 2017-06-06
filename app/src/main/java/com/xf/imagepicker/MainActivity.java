package com.xf.imagepicker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xf.imagepicker.bean.ImageFolder;
import com.xf.imagepicker.utils.ImageUtil;

import java.util.List;


/**
 * Created by X-FAN on 2017/6/6.
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final int PERMISSION_READ_EXTERNAL_STORAGE = 2401;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                List<ImageFolder> imageFolders = null;
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_STORAGE);
                } else {
                    imageFolders = ImageUtil.getAllImages(MainActivity.this);
                }

                long end = System.currentTimeMillis();
                Log.d(TAG, (end - start) / 1000 + "");
                Log.d(TAG, imageFolders.toString());

            }
        }).start();
    }
}
