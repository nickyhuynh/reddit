package com.reddit.nhnic.reddit.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.reddit.nhnic.reddit.R;
import com.reddit.nhnic.reddit.app.Constants;
import com.reddit.nhnic.reddit.app.GenericActivity;
import com.reddit.nhnic.reddit.dtos.PostAdapter;
import com.reddit.nhnic.reddit.dtos.PostDTO;
import com.reddit.nhnic.reddit.managers.PostManager;

/**
 * Created by nhnic on 5/7/2018.
 */

public class MainActivity extends GenericActivity {
    private final String TAG = "MainActivity";

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
        Log.d(TAG, requestCode + " : " + resultCode);
        if(resultCode == Constants.RESULT_OK) {
            PostDTO.Post post = new PostDTO.Post();
            post.title = data.getStringExtra("POST_DATA");
            post.upvotes = 0;
            post.downvotes = 0;
            PostManager.INSTANCE.addPost(post);
        } else if(resultCode == Constants.RESULT_BAD){
            Log.d(TAG, "Create post cancelled.");
        } else {
            Log.d(TAG, "Error");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
        assignVariables(savedInstanceState);
        assignHandlers();
    }

    private void assignViews() {
        postRecyclerView = findViewById(R.id.posts_recycler_view);
        addPost = findViewById(R.id.add_post);
    }

    private void assignVariables(Bundle savedInstanceState) {
        postAdapter = new PostAdapter(PostManager.INSTANCE.getPosts());
        layoutManager = new LinearLayoutManager(this);
        currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if(savedInstanceState != null) {
            currentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager(currentLayoutManagerType);
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

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (postRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) postRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(this, SPAN_COUNT);
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(this);
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(this);
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        postRecyclerView.setLayoutManager(layoutManager);
        postRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
}
