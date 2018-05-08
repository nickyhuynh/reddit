package com.reddit.nhnic.reddit.app;

/**
 * Created by nhnic on 5/7/2018.
 */

public class Application extends android.app.Application{
    private static Application instance;
    public static Application getInstance() {return instance;}

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
