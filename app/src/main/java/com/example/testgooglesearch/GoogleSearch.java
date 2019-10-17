package com.example.testgooglesearch;

import com.example.testgooglesearch.Interfaces.APIService;
import com.example.testgooglesearch.Interfaces.OnGoogleFetchedListener;
import com.example.testgooglesearch.Models.ResponseModels.GoogleResponse;
import com.example.testgooglesearch.Models.GoogleSearchResultBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс для взаимодействия с API Google Custom Search
 */
public class GoogleSearch {

    private String url = "https://www.googleapis.com/";
    private APIService mApi;
    private Retrofit mRetrofit;
    private OnGoogleFetchedListener mListener;
    private GoogleSearchResultBody mGoogleSearchResultBody;

    /**
     * Свойство для задания тела запроса
     * @param googleSearchResultBody тело запроса
     */
    public void setGoogleSearchResultBody(GoogleSearchResultBody googleSearchResultBody) {
        this.mGoogleSearchResultBody = googleSearchResultBody;
    }
    /**
     * Свойство для задания слушателя
     * @param listener слушатель
     */
    public void setListener(OnGoogleFetchedListener listener) {
        this.mListener = listener;
    }

    /**
     * Метод получения ответа от API Google Custom Search
     * Через слушателя ответ от API передается в MainActivity, где обрабатывается дальше.
     * Используется библиотека retrofit2
     */
    public void getResponse(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "application/json; charset=utf-8")
                        .build();
                return chain.proceed(request);
            }
        });

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(httpClient.build())
                .build();

        mApi = mRetrofit.create(APIService.class);

        Call<GoogleResponse> call = mApi.googleResponse(mGoogleSearchResultBody.getKey(),
                                                        mGoogleSearchResultBody.getCx(),
                                                        mGoogleSearchResultBody.getText(),
                                                        mGoogleSearchResultBody.getLang());

        call.enqueue(new Callback<GoogleResponse>() {
            @Override
            public void onResponse(Call<GoogleResponse> call, retrofit2.Response<GoogleResponse> response) {
                if (response.isSuccessful()) {
                    if(mListener!=null) {
                        mListener.getResult(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<GoogleResponse> call, Throwable t) {
                //TODO заглушка
            }
        });
    }
}
