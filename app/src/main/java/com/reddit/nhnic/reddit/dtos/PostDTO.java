package com.reddit.nhnic.reddit.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/7/2018.
 */

/*
    DTOs are Data Transfer Objects and they're used for converting from JSON objects in the future for connection to an API.
    The SerializedName is so that when converting to this class, it'll recognize the JSON attributes and convert them properly.
 */
public class PostDTO {
    @SerializedName("posts")
    public ArrayList<Post> posts;

    public static class Post {
        @SerializedName("title")
        public String title;

        @SerializedName("upvotes")
        public int upvotes;

        @SerializedName("downvotes")
        public int downvotes;
    }
}
