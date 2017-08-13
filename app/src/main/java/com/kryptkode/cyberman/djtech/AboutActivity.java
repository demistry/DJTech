package com.kryptkode.cyberman.djtech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class AboutActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private TextView bannerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        bannerText = (TextView) findViewById(R.id.about_image_description_text);

        if (viewFlipper.getDisplayedChild() == 1) bannerText.setText(R.string.mobile_banner);
        if (viewFlipper.getDisplayedChild() == 2) bannerText.setText(R.string.graphics_banner);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
    }
}
