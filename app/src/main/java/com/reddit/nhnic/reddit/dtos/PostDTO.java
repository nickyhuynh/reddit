package com.reddit.nhnic.reddit.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/7/2018.
 */

public class PostDTO {
    @SerializedName("posts")
    public ArrayList<Post> posts;

    public static class Post {
        @SerializedName("title")
        public String title;

        @SerializedName("link")
        public String link;
    }
}
