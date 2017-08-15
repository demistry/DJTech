package com.djtech.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.djtech.android.R;

/**
 * Created by ILENWABOR DAVID on 11/08/2017.
 */

public class SolidColorAdapter extends RecyclerView.Adapter<SolidColorAdapter.SolidColorHolder> {
    private int [] solidColors;
    private SolidColorInterface listener;
    public SolidColorAdapter(int [] solidColors, SolidColorInterface listener){
        this.solidColors = solidColors;
        this.listener = listener;
    }



    public interface SolidColorInterface{
        void solidColorClicked(View v, int position);
    }

    public static class SolidColorHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SolidColorHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.solid_color_image);
        }
        public void solidColorSelected(final SolidColorInterface solidColorInterface, final int position){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    solidColorInterface.solidColorClicked(v, position);
                }
            });
        }
    }

    @Override
    public SolidColorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solid_color_cardview, parent, false);
        return new SolidColorHolder(view);
    }

    @Override
    public void onBindViewHolder(SolidColorHolder holder, int position) {
        holder.imageView.setImageResource(solidColors[position]);
        holder.solidColorSelected(listener, position);
    }

    @Override
    public int getItemCount() {
        return solidColors.length;
    }
}
