package com.kryptkode.cyberman.djtech;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kryptkode.cyberman.djtech.adapters.SettingsRecyclerAdapter;
import com.kryptkode.cyberman.djtech.ui.fragments.HomeScreenFragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import de.hdodenhof.circleimageview.CircleImageView;


public class Settings extends AppCompatActivity {
    String [] settingsTitle = {"Modify Theme","Set Home Wallpaper", "FAQs"};
    int [] settingsImages = {R.drawable.change_theme_icon, R.drawable.change_wallpaper, R.drawable.faq_icon};
    int [] colourTints = {R.color.greenTint, R.color.yellowTint, R.color.redTint};


    public static final String MYPRE = "MyPre";
    public static final String IMAGE_PATH = "Path";
    public static final String WALLPAPER_FLAGS = "WallpaperValue";
    SettingsRecyclerAdapter adapter;
    private static final int REQUEST_CODE= 1000;
    RecyclerView recyclerView;
    Bitmap bitmap;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPref;
    public static int WALLPAPER_FLAG ;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        //RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.settings_layout_file);
        Resources resources = this.getResources();
        recyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SettingsRecyclerAdapter(settingsTitle, settingsImages, colourTints, resources, new SettingsRecyclerAdapter.SettingsClicked() {
            Intent intent;
            @Override
            public void onSettingsClicked(View v, int position) {

                switch(position){
                    case 0: break;
                    case 1:
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        dialog.setTitle("Choose Wallpaper from");
                        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_dialog_layout, null);
                        dialog.setView(view);
                        dialog.show();
                        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.gallery);
                        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.solid_color);
                        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.remove);
                        linearLayout1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sharedPref = context.getSharedPreferences(MYPRE,MODE_PRIVATE);
                                sharedPref.edit().putInt(WALLPAPER_FLAGS, 15).commit();
                                intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                Intent chooser = Intent.createChooser(intent, "Gallery");
                                startActivityForResult(chooser, REQUEST_CODE);
                            }
                        });
                        linearLayout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                intent = new Intent(Settings.this, SolidColorActivity.class);
                                startActivity(intent);
                            }
                        });
                        linearLayout3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sharedPref = context.getSharedPreferences(MYPRE,MODE_PRIVATE);
                                sharedPref.edit().putInt(WALLPAPER_FLAGS, 10).commit();
                                //WALLPAPER_FLAG = 10;
                                Toast.makeText(context, "Wallpaper Removed", Toast.LENGTH_LONG).show();
                                dialog.setCancelable(true);
                            }
                        });
                        //WALLPAPER_FLAG = 0;
                        break;
                    case 2:
                        intent = new Intent(Settings.this, FAQsActivity.class);
                        startActivity(intent); break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        /*SharedPreferences shrep = this.getSharedPreferences(MYPRE, MODE_PRIVATE);
        Bitmap image = StringToBitMap(shrep.getString(IMAGE_PATH,""));
        Log.d("TAG", "retrieved string" + shrep.getString(IMAGE_PATH, ""));
        relativeLayout.setBackground(new BitmapDrawable(resources, image));*/

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode==RESULT_OK)&&(requestCode == REQUEST_CODE)){
            Uri selectedImagePath = data.getData();
            Log.d("TAG", selectedImagePath.getPath());
            try {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.settings_layout_file);
                InputStream fileInputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(fileInputStream);
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, relativeLayout.getWidth(), relativeLayout.getHeight(),true);

                relativeLayout.setBackground(new BitmapDrawable(getResources(), bitmap1));
                sharedPreferences = getSharedPreferences(MYPRE, MODE_PRIVATE);
                sharedPreferences.edit().putString(IMAGE_PATH,BitMapToString(bitmap1)).commit();
                fileInputStream.close();

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("TAG", "encoded string" + temp);
        return temp;

    }

    public static Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
