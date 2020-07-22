package ru.android.test.task.rssreader.repository.db;

import ru.android.test.task.rssreader.App;
import ru.android.test.task.rssreader.model.modelTags.Rss;

public class CacheNewsDataSource implements INewsDataSource{
    private AppDatabase db = App.getInstance().getDatabase();
    private INewsDao newsDao = db.newsDao();

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
