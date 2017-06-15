package com.xf.imagepicker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xf.imagepicker.R;
import com.xf.imagepicker.utils.StatusBarUtil;
import com.xf.imagepicker.utils.imageloder.ImageLoaderFactory;

/**
 * Created by X-FAN on 2017/6/13.
 */
public class MainActivity extends AppCompatActivity {

    private final int REQ_CHOOSE_IMAGE = 2401;

   private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        ImageLoaderFactory.init(this);
        mImage = (ImageView) findViewById(R.id.image);
        findViewById(R.id.choose_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ImageSelectActivity.class);
                startActivityForResult(intent, REQ_CHOOSE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(ImageSelectActivity.RESULT_PATH);
            ImageLoaderFactory.getInstance().displayImage(mImage, path);
        }
    }
}
