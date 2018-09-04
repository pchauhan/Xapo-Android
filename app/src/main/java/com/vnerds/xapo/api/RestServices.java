package com.vnerds.xapo.api;

import com.vnerds.xapo.objects.Git_Object;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RestServices {

    @GET
    Call<Git_Object> getTopRepository(@Url String url);

}
