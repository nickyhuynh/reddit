package com.reddit.nhnic.reddit.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.reddit.nhnic.reddit.main.CreatePostActivity;

/**
 * Created by nhnic on 5/7/2018.
 */

/*
    The GenericActivity class serves as the base class so navigation is painless from any Activity.
    Never to be called directly, but to be used as a parent class to new activities that need navigation.
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
