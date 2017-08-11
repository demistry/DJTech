package com.kryptkode.cyberman.djtech.adapters;

import android.content.Context;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kryptkode.cyberman.djtech.R;
import com.kryptkode.cyberman.djtech.models.BlogPosts;
import com.kryptkode.cyberman.djtech.models.Posts;

import java.util.ArrayList;

/**
 * Created by Cyberman on 7/26/2017.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>{

    private ArrayList<BlogPosts> postsArrayList; //for testing  purposes
    private LayoutInflater inflater;
    private Context context;
    private HomeScreenAdapterListener homeScreenAdapterListener;

    public interface HomeScreenAdapterListener{
        void onItemClicked(int position);
        void onAuthorAvatarClicked(int position);
    }

    public void setHomeScreenAdapterListener(HomeScreenAdapterListener homeScreenAdapterListener) {
        this.homeScreenAdapterListener = homeScreenAdapterListener;
    }

    public HomeScreenAdapter(Context context, ArrayList<BlogPosts> postsArrayList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @Override
    public HomeScreenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeScreenViewHolder(inflater.inflate(R.layout.news_feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeScreenViewHolder holder, int position) {
        //data source
        BlogPosts post = postsArrayList.get(position);

        //views
        ImageView postImageView = holder.postImageView;
        TextView postTitleTextView = holder.postTitleTextView;
        TextView postAuthorTextView = holder.postAuthorTextView;
        TextView postTimeTextView = holder.postTimeTextView;
        TextView postContentTextView = holder.postContentTextView;
        TextView postAvatarTextView = holder.postAvatarTextView;

       /* String author = post.getAuthor();
        String firstLetter = author.substring(0, 1).toUpperCase();*/

        //bind the views with data
        postImageView.setImageResource(R.drawable.no_image);

        postTitleTextView.setText(post.getTitle());
        postAuthorTextView.setText(context.getString(R.string.by_author, "Anonymous"));
        postTimeTextView.setText(post.getDate());
        postContentTextView.setText(post.getContent());
       // postAvatarTextView.setText(firstLetter);

       /* postTitleTextView.setText(post.getTitle());
        postAuthorTextView.setText(post.getPostAuthor());
        postTimeTextView.setText(post.getDate());
        postContentTextView.setText(post.getPostContent());
        postAvatarTextView.setText(firstLetter);*/





    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    class HomeScreenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView postImageView;
        TextView postTitleTextView;
        TextView postAuthorTextView;
        TextView postTimeTextView;
        TextView postContentTextView;
        TextView postAvatarTextView;

        public HomeScreenViewHolder(View itemView) {
            super(itemView);
            postImageView = (ImageView) itemView.findViewById(R.id.post_image_view);
            postTitleTextView = (TextView) itemView.findViewById(R.id.post_title_text_view);
            postAuthorTextView = (TextView) itemView.findViewById(R.id.post_author_text_view);
            postTimeTextView = (TextView) itemView.findViewById(R.id.post_time_text_view);
            postContentTextView = (TextView) itemView.findViewById(R.id.post_content_text_view);
            postAvatarTextView = (TextView) itemView.findViewById(R.id.post_author_avatar);

            //set the listener on the views
            itemView.setOnClickListener(this);
            postAvatarTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.post_author_avatar:
                    //action here
                    homeScreenAdapterListener.onAuthorAvatarClicked(getAdapterPosition());
                    break;
                default:
                    //action here
                    homeScreenAdapterListener.onItemClicked(getAdapterPosition());
                    break;
            }
        }
    }
}
