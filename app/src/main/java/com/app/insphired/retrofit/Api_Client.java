package com.app.insphired.retrofit;

import com.app.insphired.util.LenientGsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Api_Client {
    public static final String BASE_URL = "https://worx.insphired.jobs/api/";
    public static final String BASE_URL_IMAGES = "https://worx.insphired.jobs/public/image/admin/employee/";
    public static final String BASE_URL_IMAGES1 = "https://worx.insphired.jobs/public/image/admin/employer/";
    public static final String BASE_URL_IMAGES2 = "https://worx.insphired.jobs/public/image/admin/corporator/";

/*  public static final String BASE_URL = "https://itdevelopmentservices.com/insphire/api/";
    public static final String BASE_URL_IMAGES = "https://itdevelopmentservices.com/insphire/public/image/admin/employee/";
    public static final String BASE_URL_IMAGES1 = "https://itdevelopmentservices.com/insphire/public/image/admin/employer/";
    public static final String BASE_URL_IMAGES2 = "https://itdevelopmentservices.com/insphire/public/image/admin/corporator/";*/

    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(500, TimeUnit.SECONDS)
                    .readTimeout(500, TimeUnit.SECONDS)
                    .writeTimeout(500, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(LenientGsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
