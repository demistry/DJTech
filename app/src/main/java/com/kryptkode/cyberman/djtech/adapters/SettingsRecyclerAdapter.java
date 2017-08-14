package com.kryptkode.cyberman.djtech.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kryptkode.cyberman.djtech.R;

/**
 * Created by ILENWABOR DAVID on 05/08/2017.
 */

public class SettingsRecyclerAdapter extends RecyclerView.Adapter<SettingsRecyclerAdapter.SettingsViewHolder> {
    String [] settingsTitle;
    int [] settingsImages;
    int [] colourTints;
    Resources resources;
    private SettingsClicked settingsClicked;

    public interface SettingsClicked{
        void onSettingsClicked(View v, int position);
    }


    public SettingsRecyclerAdapter(String [] settingsTitle, int [] settingsImages, int [] colourTints, Resources resources, SettingsClicked settingsClicked){
        this.settingsTitle = settingsTitle;
        this.settingsImages = settingsImages;
        this.colourTints = colourTints;
        this.resources = resources;
        this.settingsClicked = settingsClicked;
    }




    public static  class SettingsViewHolder extends RecyclerView.ViewHolder{
        private ImageView settingImage;
        private TextView settingTitle;
        private RelativeLayout cardview;

        public SettingsViewHolder(View v){
            super(v);
            cardview = (RelativeLayout) v.findViewById(R.id.settings_cardview);
            settingImage = (ImageView) v.findViewById(R.id.settings_icon);
            settingTitle = (TextView) v.findViewById(R.id.settings_title);
        }
        public void clickedCard(final SettingsClicked settingsClicked, final int position){
            settingImage.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            settingsClicked.onSettingsClicked(v, position);
                        }
                    }
            );
        }
    }


    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_cardview, parent, false);
        return new SettingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SettingsViewHolder holder, int position) {
        holder.settingImage.setImageResource(settingsImages[position]);
        holder.settingTitle.setText(settingsTitle[position]);
        holder.cardview.setBackgroundColor(resources.getColor(colourTints[position]));
        holder.clickedCard(settingsClicked, position);
    }

    @Override
    public int getItemCount() {
        return settingsTitle.length;
    }
}
