package com.example.testgooglesearch.Interfaces;

import com.example.testgooglesearch.Models.ResponseModels.GoogleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс для GET-запроса к API Google Custom Search
 */
public interface APIService {

    @GET("customsearch/v1")
    Call<GoogleResponse> googleResponse(@Query("key") String key,
                                        @Query("cx") String cx,
                                        @Query("q") String text,
                                        @Query("lr") String lang);
}
