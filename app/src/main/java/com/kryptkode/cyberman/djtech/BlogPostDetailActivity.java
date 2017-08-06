package com.kryptkode.cyberman.djtech;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kryptkode.cyberman.djtech.models.BlogPosts;

import java.util.ArrayList;

import static com.kryptkode.cyberman.djtech.DJTechHomeActivity.EXTRA;
import static com.kryptkode.cyberman.djtech.ui.fragments.HomeScreenFragment.POSTS;

public class BlogPostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getBundleExtra(EXTRA);
        BlogPosts blogPosts = bundle.getParcelable(POSTS);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.detail_root, BlogPostDetailActivityFragment.getInstance(blogPosts));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}
