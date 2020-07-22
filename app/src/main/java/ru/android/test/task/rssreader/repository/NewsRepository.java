package ru.android.test.task.rssreader.repository;

import ru.android.test.task.rssreader.model.modelTags.Rss;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;

public class NewsRepository  implements INewsDataSource {
    private final INewsDataSource newsDataSource;

    public NewsRepository(INewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void obtainNews(IObtainNewsCallback callback) {

    }

    @Override
    public void writeDataStart(Rss parsedObject) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
