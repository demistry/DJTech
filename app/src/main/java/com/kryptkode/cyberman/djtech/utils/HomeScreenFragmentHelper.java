package com.kryptkode.cyberman.djtech.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;

import com.kryptkode.cyberman.djtech.ui.fragments.HomeScreenFragment;

/**
 * Created by Cyberman on 8/1/2017.
 */

public class HomeScreenFragmentHelper {


    public static void indicatorsAppear(View [] views, boolean shouldAppear){
        for (View view :views             ) {
            if (shouldAppear){

                view.setVisibility(View.VISIBLE);
            }else{
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static class HomeAsyncTaskLoader extends AsyncTaskLoader<String>{

        public HomeAsyncTaskLoader(Context context) {
            super(context);
        }
        private String data;

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
        public void deliverResult(String data) {
            this.data = data;
            super.deliverResult(data);
        }

        @Override
        public String loadInBackground() {
           try {
               String url = "http://djtech.com.ng/wp-json/wp/v2/posts";
               data = NetworkUtils.getDataFromWeb(url);
               return data;
           }catch (Exception e){
                e.printStackTrace();
               return  null;
           }
        }
    }
}
