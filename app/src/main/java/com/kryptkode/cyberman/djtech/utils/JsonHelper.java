package com.kryptkode.cyberman.djtech.utils;

import android.util.Log;

import com.kryptkode.cyberman.djtech.models.BlogPosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cyberman on 8/5/2017.
 */

public class JsonHelper {
    public static final String TAG = JsonHelper.class.getSimpleName();
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String LINK = "link";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String AUTHOR = "author";
    private static final String RENDERED = "rendered";

    public static BlogPosts[] parseJson(String jsonData) {
        if (jsonData != null) {

            try {
                JSONArray postJasonArray = new JSONArray(jsonData);
                BlogPosts[] aBlogPosts = new BlogPosts[postJasonArray.length()];
                for (int i = 0; i < postJasonArray.length(); i++) {
                    JSONObject blogPostJsonObject = postJasonArray.getJSONObject(i);
                    BlogPosts blogPost = new BlogPosts();

                    //get and set the id
                    int id = blogPostJsonObject.getInt(ID);
                    blogPost.setId(id);

                    //get and set the date
                    String date = BlogPosts.formatDate(blogPostJsonObject.getString(DATE));
                    //TODO Format the time if neccessary
                    blogPost.setDate(date);

                    //get and set the link
                    String link = blogPostJsonObject.getString(LINK);
                    blogPost.setLink(link);

                    String title = blogPostJsonObject.getJSONObject(TITLE).
                            getString(RENDERED);
                    blogPost.setTitle(title);

                    String content = blogPostJsonObject.getJSONObject(CONTENT)
                            .getString(RENDERED);
                    blogPost.setContent(content);

                    int authorId = blogPostJsonObject.getInt(AUTHOR);
                    blogPost.setAuthorId(authorId);

                    aBlogPosts[i] = blogPost;

                }
                return aBlogPosts;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }
}
