package com.djtech.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;



public class AboutActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3500);
        viewFlipper.startFlipping();




    }
}
