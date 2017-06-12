package com.xf.imagepicker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xf.imagepicker.R;

/**
 * Created by X-FAN on 2015/6/12.
 */
public class ImageCropActivity extends AppCompatActivity {
    public static String PARAM_PATH = "PARAM_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop);
        String path = getIntent().getStringExtra(PARAM_PATH);
    }
}
