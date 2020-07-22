package ru.android.test.task.rssreader.repository.api;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.android.test.task.rssreader.model.modelTags.Rss;

public interface IRSSApiService {
    @GET("/rss/news")
    Call<Rss> getNews();
}
