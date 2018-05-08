package com.reddit.nhnic.reddit.managers;

import android.content.Context;

import com.reddit.nhnic.reddit.app.Application;
import com.reddit.nhnic.reddit.dtos.PostDTO;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/7/2018.
 */

public enum PostManager {
    INSTANCE;

    private Context context;
    private ArrayList<PostDTO.Post> posts;

    PostManager() {
        this.context = Application.getInstance();
        posts = new ArrayList<>();
    }

    public void addPost(PostDTO.Post post) {
        posts.add(post);
    }

    public ArrayList<PostDTO.Post> getPosts() {
        return this.posts;
    }
}
