package ru.android.test.task.rssreader.repository;

import android.util.Log;

import ru.android.test.task.rssreader.model.modelTags.Rss;
import ru.android.test.task.rssreader.repository.api.ILoadDataRSS;
import ru.android.test.task.rssreader.repository.api.LoaderNews;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;

public class NewsRepository implements INewsDataSource {
    private final INewsDataSource newsDataSource;

    public NewsRepository(INewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void obtainNews(final IObtainNewsCallback callback) {
        if (isEmpty()) {
            ILoadDataRSS loaderNews = new LoaderNews();
            loaderNews.loadNews(new ILoadDataRSS.ILoadNewsCallback() {
                @Override
                public void didLoad(final Rss rssObject) {
                    writeDataStart(rssObject);
                    newsDataSource.obtainNews(callback);
                }

                @Override
                public void didFailLoad(String message) {
                    Log.e("LOAD_NEWS", "fail load");
                }
            });
        }
        newsDataSource.obtainNews(callback);

    }

    @Override
    public void writeDataStart(Rss parsedObject) {
        newsDataSource.writeDataStart(parsedObject);
    }

    @Override
    public boolean isEmpty() {
        return newsDataSource.isEmpty();
    }
}
