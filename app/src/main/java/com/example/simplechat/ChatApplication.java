package com.example.simplechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.applicationId)) // provided in Lab instructions
                .clientKey(getString(R.string.clientKey)) // provided in Lab instructions
                .clientBuilder(builder)
                .server(getString(R.string.server)).build());  // provided in Lab instructions
    }


}
