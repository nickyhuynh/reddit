package com.reddit.nhnic.reddit.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.reddit.nhnic.reddit.dtos.CreatePostActivity;

/**
 * Created by nhnic on 5/7/2018.
 */

/*
    The GenericActivity class serves as the base class so navigation is painless from any Activity.
 */
public class GenericActivity extends AppCompatActivity {

    /*
        Creation of posts and returns the result (title) to the calling activity.
     */
    public void openCreatePost(int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, CreatePostActivity.class);
        startActivityForResult(intent, requestCode);
    }
}
