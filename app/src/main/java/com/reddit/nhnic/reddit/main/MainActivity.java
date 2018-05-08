package com.reddit.nhnic.reddit.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.reddit.nhnic.reddit.R;
import com.reddit.nhnic.reddit.app.Constants;
import com.reddit.nhnic.reddit.app.GenericActivity;
import com.reddit.nhnic.reddit.dtos.PostDTO;
import com.reddit.nhnic.reddit.managers.PostManager;

/**
 * Created by nhnic on 5/7/2018.
 */

public class MainActivity extends GenericActivity {
    private final String TAG = "MainActivity";

    /*
        RecyclerView for displaying posts, as well as a variable rather than hard-coded layout and span count for grid layout.
     */
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType currentLayoutManagerType;
    protected RecyclerView postRecyclerView;
    protected PostAdapter postAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    private ImageView addPost;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        // If the result code was RESULT_OK then that means a topic was successfully filled out.
        if(resultCode == Constants.RESULT_OK) {
            //Create a new post and add its attributes according to the data.
            PostDTO.Post post = new PostDTO.Post();
            post.title = data.getStringExtra("POST_DATA");
            post.upvotes = 0;
            post.downvotes = 0;
            PostManager.INSTANCE.addPost(post);
        } else if(resultCode == Constants.RESULT_BAD){
            Log.d(TAG, "Create post cancelled.");
        } else {
            Log.d(TAG, "Activity was closed unexpectedly.");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            These private functions are meant to add uniformity and classify code into usable chunks.
         */
        assignViews();
        assignVariables(savedInstanceState);
        assignHandlers();
    }

    private void assignViews() {
        postRecyclerView = findViewById(R.id.posts_recycler_view);
        addPost = findViewById(R.id.add_post);
    }

    private void assignVariables(Bundle savedInstanceState) {
        /*
            Since adapters work by accessing the original data structure, any changes made to the original
            are reflected in the UI. That's why you pass the same object and operate on that.
         */
        postAdapter = new PostAdapter(PostManager.INSTANCE.getPosts());
        layoutManager = new LinearLayoutManager(this); //The default layout manager is a LinearLayoutManager, meaning single column list.
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        postRecyclerView.setLayoutManager(layoutManager);
        postRecyclerView.setAdapter(postAdapter);
    }

    private void assignHandlers() {
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreatePost(Constants.ADD_POST);
            }
        });
    }
}
