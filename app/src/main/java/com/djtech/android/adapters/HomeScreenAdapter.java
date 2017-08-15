package com.djtech.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.djtech.android.R;
import com.djtech.android.models.BlogPosts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Cyberman on 7/26/2017.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>{
    public static final String TAG = "HomeScreenAdapter";
    private ArrayList<BlogPosts> postsArrayList; //for testing  purposes
    private LayoutInflater inflater;
    Context context;
    HomeScreenAdapterListener homeScreenAdapterListener;

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
        postAuthorTextView.setText(context.getString(R.string.by_author, context.getString(R.string.isaiah)));
        postTimeTextView.setText(post.getDate());
        postContentTextView.setText(stripHtml(post.getExcerpt()));

        Log.i(TAG, "onBindViewHolder: " +post.getPosterUrl() );
        Log.i(TAG, "onBindViewHolder: " + post.getMediaUrl());

        Picasso.with(context).load(post.getPosterUrl())
                .placeholder(R.drawable.loading).
                error(R.drawable.no_image).into(postImageView);
        postAvatarTextView.setText("I");

    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    public String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return String.valueOf(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            return String.valueOf(Html.fromHtml(html));
        }
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
