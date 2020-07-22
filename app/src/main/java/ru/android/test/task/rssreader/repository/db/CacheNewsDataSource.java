package ru.android.test.task.rssreader.repository.db;

import ru.android.test.task.rssreader.App;

public class CacheNewsDataSource implements INewsDataSource{
    private AppDatabase db = App.getInstance().getDatabase();
    private INewsDao newsDao = db.newsDao();

}
