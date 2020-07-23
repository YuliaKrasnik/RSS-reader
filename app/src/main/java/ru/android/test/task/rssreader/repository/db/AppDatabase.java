package ru.android.test.task.rssreader.repository.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.android.test.task.rssreader.model.modelDb.News;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract INewsDao newsDao();
}