package com.djtech.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cyberman on 7/26/2017.
 */

public class Posts implements Parcelable {
    private String postTitle;
    private String postTime;
    private String postAuthor;
    private String postContent;
    private String postPosterLink;

    private static String[] postTitles = new String[]{"Hacking Smart Moblie Phones",
            "Graphic / Video Editing (CLoning and Set-ups) for Hackers and Forensic Engineers",
            "Reverse Engineering iOS Apps",
            "Sniffing Data Across public and private Networks",
            "ARP Spoofing, DNS Injection and Cross site Scripting"};

    private static String[] postTimes = new String[]{"2 hours ago, 4:54PM",
            "10 hours ago, 12:54AM",
            "February 30, 1906",
            "2 days ago, March 18",
            "2 years ago, October 15, 2015"};

    private static String[] postAuthors = new String[]{"Cybeman", "Demistry", "Jcool", "StonyStones", "Esias Dutchman"};

    private static String[] postContents = new String[]{
            "Learing to hack mobile phones is not a day's job. It requires weeks of learning the tools of trade. Once mastered, you will be perceived by those who learn about your new skill as an evil genius.",
            "Ever watched the Prison Break? Seen how Lincoln Burrows was set up using a video clip that showed he shot a man when actually did not?",
            "Disclaimer: This post is just for educational purposes. I take no responsibilty for whartever might happen if you try the steps highlighted in this post",
            "Most networks are vulnerable and data can be stolen from the networks, A good way to prevent this is by acknowlginedgf,jdmfdkfjkdkfkdkfjdk",
            "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"
    };

    public static ArrayList<Posts> generateDummyPosts(int count) {
        ArrayList<Posts> postsArrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int num = random.nextInt(5);
            Posts post;
            switch (num) {
                case 1:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;
                case 2:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;
                case 3:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;
                case 4:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;
                case 0:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;
                default:
                    post = new Posts(postTitles[num], postTimes[num], postAuthors[num], postContents[num], "");
                    break;

            }
            postsArrayList.add(post);
        }
        return postsArrayList;
    }

    public Posts(String postTitle, String postTime, String postAuthor, String postContent, String postPosterLink) {
        this.postTitle = postTitle;
        this.postTime = postTime;
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.postPosterLink = postPosterLink;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostPosterLink() {
        return postPosterLink;
    }

    public void setPostPosterLink(String postPosterLink) {
        this.postPosterLink = postPosterLink;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.postTitle);
        dest.writeString(this.postTime);
        dest.writeString(this.postAuthor);
        dest.writeString(this.postContent);
        dest.writeString(this.postPosterLink);
    }

    public Posts() {
    }

    protected Posts(Parcel in) {
        this.postTitle = in.readString();
        this.postTime = in.readString();
        this.postAuthor = in.readString();
        this.postContent = in.readString();
        this.postPosterLink = in.readString();
    }

    public static final Parcelable.Creator<Posts> CREATOR = new Parcelable.Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel source) {
            return new Posts(source);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };
}
