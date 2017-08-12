package com.kryptkode.cyberman.djtech.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Cyberman on 8/1/2017.
 */

public class BlogPosts implements Parcelable {
    private int id;
    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    private String excerpt;
    private String date;
    private String link;
    private String title;
    private String content;
    private String author;
    private String mediaUrl;
    private String posterUrl;

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }



    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }


    public BlogPosts() {
    }

    public static String formatDate(String string) {
        if (TextUtils.isEmpty(string)) return "N/A";
        String[] dateTime = string.split("T");
        String [] date = dateTime[0].split("-");
        int year = Integer.parseInt(date[0]),
                month = Integer.parseInt(date[1]),
                day = Integer.parseInt(date[2]);
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        return String.format(Locale.US, "%1$tB %1$te, %1$tY", calendar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.authorId);
        dest.writeString(this.excerpt);
        dest.writeString(this.date);
        dest.writeString(this.link);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.author);
        dest.writeString(this.mediaUrl);
        dest.writeString(this.posterUrl);
    }

    protected BlogPosts(Parcel in) {
        this.id = in.readInt();
        this.authorId = in.readInt();
        this.excerpt = in.readString();
        this.date = in.readString();
        this.link = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.author = in.readString();
        this.mediaUrl = in.readString();
        this.posterUrl = in.readString();
    }

    public static final Creator<BlogPosts> CREATOR = new Creator<BlogPosts>() {
        @Override
        public BlogPosts createFromParcel(Parcel source) {
            return new BlogPosts(source);
        }

        @Override
        public BlogPosts[] newArray(int size) {
            return new BlogPosts[size];
        }
    };
}
