package com.reddit.nhnic.reddit.app;

/**
 * Created by nhnic on 5/7/2018.
 */

/*
    The purpose of overriding the Application class is for future casing when initializing libraries like Firebase and Facebook.
    The Application class gets called before anything else.
 */
public class Application extends android.app.Application{
    private static Application instance;
    public static Application getInstance() {return instance;}

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
