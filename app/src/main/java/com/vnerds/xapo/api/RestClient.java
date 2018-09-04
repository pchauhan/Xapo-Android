package com.vnerds.xapo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient  {
    private RestServices restServices;
    public final static String DOMAIN_URL = " https://api.github.com";
    public final static String BASE_URL = DOMAIN_URL  ;


    public final static String search = "/search/repositories?sort=stars&order=desc&q=language:java&q=created:";

    public RestClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
             // .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restServices = retrofit.create(RestServices.class);
    }
    public RestServices getApplicationServices()
    {
        return restServices;
    }


    public static final String get_categories = BASE_URL +"get_categories";
}
