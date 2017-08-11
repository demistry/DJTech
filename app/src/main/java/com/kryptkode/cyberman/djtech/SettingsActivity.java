package com.kryptkode.cyberman.djtech;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class SettingsActivity extends DJTechPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    SettingsActivityFragment settingsFragment;
    public final String PREFS_THEME = "theme_prefs";
    private int themeSelect;
    private int mThemeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //settingsFragment = new SettingsActivityFragment();

//        getFragmentManager().beginTransaction().add(R.id.root_settings, settingsFragment,  null).
//                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();


        addPreferencesFromResource(R.xml.settings_preference);
        PreferenceManager.setDefaultValues(this, R.xml.settings_preference, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).
                registerOnSharedPreferenceChangeListener(this);
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        setListSummary(PREFS_THEME, preference);
        themeSelect = Integer.parseInt(preference.getString(PREFS_THEME, "0"));
        toggleTheme(themeSelect);
    }

    private int setListSummary(String key, SharedPreferences sharedPreferences) {
        Preference preference = findPreference(key);
        int themeSel = Integer.parseInt(sharedPreferences.getString(PREFS_THEME, "0"));
        preference.setSummary(getResources().getStringArray(R.array.theme_options)[themeSel]);
        return themeSel;
    }


    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    public static void toggleTheme(int themeSelect) {
        switch (themeSelect) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;



        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(PREFS_THEME)){
            themeSelect = setListSummary(key, sharedPreferences);
            toggleTheme(themeSelect);
            Toast.makeText(this, R.string.theme_change_toast_texrt, Toast.LENGTH_LONG).show();
        }
    }
}
