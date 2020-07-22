package ru.android.test.task.rssreader.repository.db;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;

@Dao
public interface INewsDao {
    @Query("SELECT * FROM news")
    List<News> getNews();

    @Query("SELECT * FROM news LIMIT 1")
    @Nullable
    News getAnyNews();

    @Insert
    void insert(News news);
}

