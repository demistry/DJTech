package com.djtech.android;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.djtech.android.models.BlogPosts;


import static com.djtech.android.ui.fragments.HomeScreenFragment.POSTS;

public class BlogPostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post_detail);
        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getBundleExtra(DJTechHomeActivity.EXTRA);
        BlogPosts blogPosts = bundle.getParcelable(POSTS);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.detail_root, BlogPostDetailActivityFragment.getInstance(blogPosts));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}
