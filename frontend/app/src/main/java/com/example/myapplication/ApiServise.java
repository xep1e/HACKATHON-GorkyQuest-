package com.example.myapplication;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
public interface ApiServise {

    @POST("api/registration/register")
    Call<RegisterResponseJSON> registerUser(@Body Users registerRequest);
}
