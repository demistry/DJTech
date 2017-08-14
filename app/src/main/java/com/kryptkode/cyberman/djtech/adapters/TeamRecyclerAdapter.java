package com.kryptkode.cyberman.djtech.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kryptkode.cyberman.djtech.utils.DummyClass;
import com.kryptkode.cyberman.djtech.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ILENWABOR DAVID on 24/07/2017.
 */

public class TeamRecyclerAdapter extends RecyclerView.Adapter <TeamRecyclerAdapter.TeamViewHolder>{
    private List<DummyClass> list;
    private int [] image;
    TeamListener teamListener;



    public interface TeamListener{
        void onFacebookClicked(View v, int position);
        void onTwitterClicked(View v, int position);
        void onInstagramClicked(View v, int position);
        void onProfilePictureClicked(View v, int position);
    }






    public TeamRecyclerAdapter(List<DummyClass> list, int[] image, TeamListener listener){
        this.teamListener= listener;
        this.list=list;
        this.image= image;
    }
    public class TeamViewHolder extends RecyclerView.ViewHolder{
        public TextView name, profile, number, email, position;
        CircleImageView images,fbImage, twitterImage, instaImage;

        public TeamViewHolder(View view){
            super(view);
            position = (TextView) view.findViewById(R.id.member_position);
            name = (TextView) view.findViewById(R.id.member_name);
            profile = (TextView) view.findViewById(R.id.member_profile);
            number = (TextView) view.findViewById(R.id.team_number);
            email = (TextView) view.findViewById(R.id.team_mail);
            images = (CircleImageView) view.findViewById(R.id.circular_imageview);
            fbImage = (CircleImageView) view.findViewById(R.id.facebook_button);
            twitterImage = (CircleImageView) view.findViewById(R.id.twitter_button);
            instaImage = (CircleImageView) view.findViewById(R.id.instagram_button);

        }

        public void onSocialMediaClick(final TeamListener teamListener, final int position){
            fbImage.setOnClickListener(new CircleImageView.OnClickListener(){
                @Override
                public void onClick(View v) {
                    teamListener.onFacebookClicked(v, position);
                }
            });
            twitterImage.setOnClickListener(
                    new CircleImageView.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            teamListener.onTwitterClicked(v, position);
                        }
                    }
            );
            instaImage.setOnClickListener(
                    new CircleImageView.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            teamListener.onInstagramClicked(v, position);
                        }
                    }
            );
            images.setOnClickListener(
                    new CircleImageView.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            teamListener.onProfilePictureClicked(v, position);
                        }
                    }
            );

        }
    }
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_team_members,parent, false);
        view.setMinimumWidth(parent.getMinimumWidth());
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        DummyClass currentData = list.get(position);
        holder.name.setText(currentData.names);
        holder.position.setText(currentData.position);
        holder.profile.setText(currentData.profile);
        holder.number.setText(currentData.number);
        holder.email.setText(currentData.email);
        holder.images.setImageResource(image[position]);

        holder.onSocialMediaClick(teamListener, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
