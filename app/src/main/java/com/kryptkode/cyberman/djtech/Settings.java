package com.kryptkode.cyberman.djtech;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kryptkode.cyberman.djtech.adapters.SettingsRecyclerAdapter;


public class Settings extends AppCompatActivity {
    String [] settingsTitle = {"Modify Theme","Set Home Wallpaper", "FAQs"};
    int [] settingsImages = {R.drawable.change_theme_icon, R.drawable.change_wallpaper, R.drawable.faq_icon};
    int [] colourTints = {R.color.greenTint, R.color.yellowTint, R.color.redTint};
    SettingsRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Resources resources = this.getResources();
        recyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SettingsRecyclerAdapter(settingsTitle, settingsImages, colourTints, resources);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
