package com.djtech.android;


import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatDelegate;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Toast;


import com.djtech.android.models.BlogPosts;
import com.djtech.android.ui.fragments.HomeScreenFragment;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.djtech.android.OptionsActivity.MYPRE;
import static com.djtech.android.OptionsActivity.THEME;
import static com.djtech.android.ui.fragments.HomeScreenFragment.POSTS;



public class DJTechHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeScreenFragment.HomeScreenFragmentListener {

    public static final String EXTRA = "posts";
    HomeScreenFragment homeScreenFragment;
    public Context context = this;

    public static final String PREFS_THEME = "theme_prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djtech_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences preference = getSharedPreferences(MYPRE, MODE_PRIVATE);
        int themeSelect = preference.getInt(THEME, 0);
        toggleTheme(themeSelect);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        homeScreenFragment = new HomeScreenFragment();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayFragment(homeScreenFragment);


    }

    private void toggleTheme(int themeSelect) {
        switch (themeSelect) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }

    private void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_root, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    SharedPreferences.OnSharedPreferenceChangeListener preferenceListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals(PREFS_THEME)){
                int themeSelect = Integer.parseInt(sharedPreferences.getString(PREFS_THEME, "0"));
                toggleTheme(themeSelect);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preference = getSharedPreferences(MYPRE, MODE_PRIVATE);
        int themeSelect = preference.getInt(THEME, 0);
        toggleTheme(themeSelect);


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preference = getSharedPreferences(MYPRE, MODE_PRIVATE);
        int themeSelect = preference.getInt(THEME, 0);
        toggleTheme(themeSelect);

    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(preferenceListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.djtech_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings){

            Intent intent = new Intent(DJTechHomeActivity.this, OptionsActivity.class);


            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            displayFragment(homeScreenFragment);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(DJTechHomeActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contact_us) {
            Intent intent = new Intent(DJTechHomeActivity.this, ContactUsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this Application");
            intent.putExtra(Intent.EXTRA_TEXT, "Want cool apps and  website for your business? \nDownload DJTech app \nhttps://play.google.com/store/apps/details?id=" + getPackageName());
            Intent chooserIntent = Intent.createChooser(intent, "Share with...");
            startActivity(chooserIntent);


        } else if (id == R.id.nav_rate) {
            final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View v = layoutInflater.inflate(R.layout.rating_layout, null);
            MaterialRatingBar ratingBar = (MaterialRatingBar) v.findViewById(R.id.ratingBar);
            if (ratingBar.getParent() != null){
                ((ViewGroup) ratingBar.getParent()).removeView(ratingBar);
            }
            alertBuilder.setView(ratingBar);
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle("Rate this App");
            alertBuilder.setPositiveButton("Rate", new AlertDialog.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(context,"Thanks for Rating", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        }
        else if (id == R.id.team){
            Intent intent = new Intent(DJTechHomeActivity.this, TeamMembersActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onItemClicked(int position, BlogPosts postsArrayList) {
        Intent intent  = new Intent (this, BlogPostDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(POSTS, postsArrayList);
        intent.putExtra(EXTRA, bundle);
        startActivity(intent);
    }

    @Override
    public void onAuthorAvatarClicked(int position) {

    }
}
