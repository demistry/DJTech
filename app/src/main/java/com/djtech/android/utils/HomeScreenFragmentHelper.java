package com.djtech.android.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.View;

import com.djtech.android.models.BlogPosts;
import com.djtech.android.ui.fragments.HomeScreenFragment;

/**
 * Created by Cyberman on 8/1/2017.
 */

public class HomeScreenFragmentHelper {
    public static final String TAG = HomeScreenFragmentHelper.class.getSimpleName();

    public static void indicatorsAppear(View [] views, boolean shouldAppear){
        for (View view :views             ) {
            if (shouldAppear){

                view.setVisibility(View.VISIBLE);
            }else{
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static class HomeAsyncTaskLoader extends AsyncTaskLoader<BlogPosts[]>{

        public HomeAsyncTaskLoader(Context context) {
            super(context);
        }
        private BlogPosts[] data;

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            if (data != null){
                deliverResult(data);
            }else{
                forceLoad();
            }
        }

        @Override
        public void deliverResult(BlogPosts[] data) {
            this.data = data;
            super.deliverResult(data);
        }

        @Override
        public BlogPosts[] loadInBackground() {
           try {
               String url = "http://djtech.com.ng/wp-json/wp/v2/posts";
               String rawData = NetworkUtils.getDataFromWeb(url);
               Log.i(TAG, "loadInBackground: " + rawData);
               data = JsonHelper.parsePostsJson(rawData);
               return data;
           }catch (Exception e){
                e.printStackTrace();
               return  null;
           }
        }
    }
}
