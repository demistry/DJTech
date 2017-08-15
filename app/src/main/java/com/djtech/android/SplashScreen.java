package com.djtech.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    private static  final int SPLASH_DISPLAY_LENGTH = 2500;

    // Declare the Handler as a member variable
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        delay();

    }

    private void delay() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(this, DJTechHomeActivity.class);
        startActivity(mainIntent);
        finish();
    }









}

