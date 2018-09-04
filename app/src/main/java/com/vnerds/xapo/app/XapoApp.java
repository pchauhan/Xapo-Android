package com.vnerds.xapo.app;

import android.app.Application;

import com.vnerds.xapo.api.RestClient;


public class XapoApp extends Application
{
    private static RestClient restClient;

    @Override
    public void onCreate()
    {
        super.onCreate();

        restClient = new RestClient();

    }

    public static RestClient getRestClient()
    {
        return restClient;
    }
}
