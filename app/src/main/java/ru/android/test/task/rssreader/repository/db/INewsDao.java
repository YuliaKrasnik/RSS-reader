package ru.android.test.task.rssreader.repository.db;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;

@Dao
public interface INewsDao {
    @Query("SELECT * FROM news ORDER BY id DESC LIMIT :countNews OFFSET :startingPosition")
    List<News> getNews(int startingPosition, int countNews);

    @Query("SELECT * FROM news LIMIT 1")
    @Nullable
    News getAnyNews();

    @Query("DELETE FROM news")
    void delete();

    @Insert
    void insert(News news);

    @Query("SELECT pubDate FROM news WHERE id = (SELECT MAX(id) FROM news)")
    @Nullable
    String getLastDate();

    @Query("SELECT COUNT(*) FROM news")
    int getCountNewsInDb();
}

