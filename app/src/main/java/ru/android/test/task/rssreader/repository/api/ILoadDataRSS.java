package ru.android.test.task.rssreader.repository.api;

import ru.android.test.task.rssreader.model.modelTags.Rss;

public interface ILoadDataRSS {
    void loadNews(final ILoadNewsCallback callback);

    interface ILoadNewsCallback {
        void didLoad(final Rss rssObject);

        void didFailLoad(String message);
    }
}
