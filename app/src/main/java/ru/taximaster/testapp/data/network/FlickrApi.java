package ru.taximaster.testapp.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.taximaster.testapp.data.network.service.FlickrApiService;

public class FlickrApi {
    private static final long REQUEST_TIMEOUT = 30;

    private static FlickrApi instance;

    private final String serverUrl;

    private FlickrApi(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public static void init(String serverUrl) {
        instance = new FlickrApi(serverUrl);
    }

    public static FlickrApiService newCall() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(instance.serverUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();
        return retrofit.create(FlickrApiService.class);
    }
}
