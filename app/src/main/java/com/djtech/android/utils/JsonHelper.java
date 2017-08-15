package com.djtech.android.utils;

import com.djtech.android.models.BlogPosts;

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
    public static final String EXCERPT = "excerpt";
    public static final String GUID = "guid";
    public static final String AUTHOR = "author";
    private static final String RENDERED = "rendered";
    public static final String _LINKS  = "_links";
    public static final String SELF = "self";
    public static final String MEDIA = "wp:featuredmedia";
    public static final String HREF = "href";

    public static BlogPosts[] parsePostsJson(String jsonData) {
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

                    String excerpt = blogPostJsonObject.getJSONObject(EXCERPT).
                            getString(RENDERED);
                    blogPost.setExcerpt(excerpt);

                    JSONArray mediaUrlArray = blogPostJsonObject.getJSONObject(_LINKS).
                            getJSONArray(MEDIA);
                    JSONObject mediaUrlJsonObject = mediaUrlArray.getJSONObject(0);
                    String mediaUrl = mediaUrlJsonObject.getString(HREF);
                    blogPost.setMediaUrl(mediaUrl);

                    String posterUrl = getPosterUrlFromJson(NetworkUtils.getDataFromWeb(blogPost.getMediaUrl()));
                    blogPost.setPosterUrl(posterUrl);


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


    public static String getPosterUrlFromJson(String json ){
        try {
            JSONObject mediaJsonObject = new JSONObject(json);
            JSONObject posterJsonObject = mediaJsonObject.getJSONObject(GUID);
            return posterJsonObject.getString(RENDERED);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
