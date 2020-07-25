package ru.android.test.task.rssreader.repository.db;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.model.modelTags.Rss;

public interface INewsDataSource {
    void obtainNews(final int startingPosition, final int countNews, final IObtainNewsCallback callback);

    void writeDataStart(Rss parsedObject);

    boolean isEmpty();

    void refreshNews(final int countNews, final IObtainNewsCallback callback);

    void writeDataRefresh(Rss parsedObject);

    void getCountNewsInDb(IGetCountNewsInDbCallback callback);

    interface IObtainNewsCallback {
        void didObtain(final List<News> news);

        void didFailObtain(final int errorStatusCode);
    }

    interface IGetCountNewsInDbCallback{
        void didGet(final int countNews);
    }
}
