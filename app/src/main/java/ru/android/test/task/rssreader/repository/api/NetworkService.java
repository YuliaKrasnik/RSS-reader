package ru.android.test.task.rssreader.repository.api;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

class NetworkService {
    private static NetworkService instance;
    private static final String BASE_URL = "https://lenta.ru";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    IRSSApiService getRSSApi() {
        return retrofit.create(IRSSApiService.class);
    }
}
