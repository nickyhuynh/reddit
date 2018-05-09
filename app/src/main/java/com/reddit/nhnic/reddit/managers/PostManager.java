package com.reddit.nhnic.reddit.managers;

import com.reddit.nhnic.reddit.dtos.PostDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by nhnic on 5/7/2018.
 */

/*
    The job of a Manager is to manage a certain aspect of the app: posts displayed, API calls, user data, etc.
    They are singletons where you can access it throughout the app.
 */
public enum PostManager {
    INSTANCE;
    private final String TAG = "PostManager";

    /*
        This is the ArrayList that always keeps track of the posts being displayed to the user.
     */
    private ArrayList<PostDTO.Post> posts;

    PostManager() {
        posts = new ArrayList<>();
    }

    /*
        Since we need to keep track of the order of the posts, ordering posts should be done here, whenever we add a new post.
        The reason we want to do the ordering here is because it would make more sense to order after a new post is added
        so in case the user wants to upvote something to the top.
    */
    public void addPost(PostDTO.Post post) {
        posts.add(post);
        Collections.sort(posts, new Comparator<PostDTO.Post>() {
            @Override
            public int compare(PostDTO.Post o1, PostDTO.Post o2) {
                return (o2.upvotes-o2.downvotes) - (o1.upvotes-o1.downvotes);
            }
        });
    }

    public ArrayList<PostDTO.Post> getPosts() {
        return this.posts;
    }
}
