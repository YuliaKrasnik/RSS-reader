package ru.android.test.task.rssreader.repository.api;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.android.test.task.rssreader.model.modelTags.Rss;

public class LoaderNews implements ILoadDataRSS {
    @Override
    public void loadNews(final ILoadNewsCallback callback) {
        NetworkService.getInstance().getRSSApi().getNews().enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(@NonNull Call<Rss> call, @NonNull Response<Rss> response) {
                if (response.isSuccessful()) {
                    final Rss apiResponse = response.body();
                    callback.didLoad(apiResponse);
                } else {
                    callback.didFailLoad("Request Error :: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Rss> call, @NonNull Throwable t) {
                if (call.isCanceled()) {
                    callback.didFailLoad("Call was cancelled forcefully");
                } else {
                    callback.didFailLoad("Network Error :: " + t.getLocalizedMessage());
                }
            }
        });
    }
}
