package org.am.dba.ms.mystudents.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Beschi Antony D on 8/6/2016.
 */
public class ServiceBuilder {

    private static final String API_BASE_URL = "http://10.0.2.2:8080/api/v1/";

    private static HttpLoggingInterceptor lInr = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(lInr).build();


    private static Retrofit rb = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .client(httpClient)
            .build();

    public static <S> S createService(Class<S> sClass){
        return rb.create(sClass);
    }

}
