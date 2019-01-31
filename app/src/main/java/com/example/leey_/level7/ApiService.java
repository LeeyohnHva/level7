package com.example.leey_.level7;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "https://www.food2fork.com/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/search?key=4e4142ff5bb9b4fd88da38e65226fe25&rId=35382&sort=r&count=3")
    Call<Recipes> getRecipe();
}
