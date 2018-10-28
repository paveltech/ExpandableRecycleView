package com.api.responce;


import com.server.pojo.ServerResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android on 9/29/2017.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<ServerResponce> getTopRatedMovies(@Query("api_key") String apiKey);
}