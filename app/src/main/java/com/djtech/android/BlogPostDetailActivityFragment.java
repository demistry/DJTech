package com.djtech.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;


import com.djtech.android.models.BlogPosts;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class BlogPostDetailActivityFragment extends Fragment {
    public static final String POST = "Posts";
    public final String PREFS_THEME = "theme_prefs";
    private WebView detailWebView;
    private String content;
    private ImageView detailImageView;
    private BlogPosts blogPosts;

    public BlogPostDetailActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blogPosts = getArguments().getParcelable(POST);
    }
    public static Fragment getInstance(BlogPosts blogPosts) {
        BlogPostDetailActivityFragment fragment = new BlogPostDetailActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(POST, blogPosts);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_blog_post_detail, container, false);
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getContext());
        int themeSelect = Integer.parseInt(preference.getString(PREFS_THEME, "0"));
        //dark theme
        if (themeSelect == 1){
            content = "<html> <head><style type=\"text/css\" rel=\"stylesheet\"> body{ background-color:#555; color:#fff; text-align: justify; } </style></head> <body> "+ blogPosts.getContent() + "</body> </html>";

        }else{ //light theme
            content = "<html> <head><style type=\"text/css\" rel=\"stylesheet\"> body{ background-color:#fff; color:#000; text-align: justify; } </style></head> <body> "+ blogPosts.getContent() + "</body> </html>";
        }

        detailWebView = (WebView) view.findViewById(R.id.detail_webview);
        detailImageView = (ImageView) view.findViewById(R.id.detail_image_view);
        detailWebView.loadData(content, "text/html", null);

        Picasso.with(getContext()).load(blogPosts.getPosterUrl())
                .placeholder(R.drawable.loading).
                error(R.drawable.no_image).into(detailImageView);
        return view;

    }
}
