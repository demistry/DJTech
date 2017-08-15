package com.djtech.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.djtech.android.adapters.SolidColorAdapter;

public class SolidColorActivity extends AppCompatActivity {


    private int [] images = {R.drawable.dark_brown, R.drawable.dark_red,R.drawable.darkblue_background, R.drawable.light_green,
     R.drawable.dark_violet, R.drawable.bluee, R.drawable.yellow, R.drawable.solid, R.drawable.dark_green, R.drawable.orange};


    private final Context context = this;
    private RecyclerView rv;
    private SolidColorAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solid_colour_layout);
        gridLayoutManager = new GridLayoutManager(this,2);
        rv = (RecyclerView) findViewById(R.id.solid_color_rv);
        rv.setLayoutManager(gridLayoutManager);
        sharedPreferences = this.getSharedPreferences(OptionsActivity.MYPRE,MODE_PRIVATE);
        adapter =  new SolidColorAdapter(images, new SolidColorAdapter.SolidColorInterface() {
            @Override
            public void solidColorClicked(View v, int position) {
                switch (position){
                    case 0:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,1).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,2).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,3).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,4).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();break;
                    case 4:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,5).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();break;
                    case 5:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,6).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,7).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,8).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,9).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        sharedPreferences.edit().putInt(OptionsActivity.WALLPAPER_FLAGS,11).commit();
                        Toast.makeText(context,"Wallpaper Set", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        rv.setAdapter(adapter);
    }
}
