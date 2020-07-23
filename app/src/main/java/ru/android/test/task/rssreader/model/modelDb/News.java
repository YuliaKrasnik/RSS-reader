package ru.android.test.task.rssreader.model.modelDb;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String title;
    public String link;
    public String description;
    public String pubDate;

    @Embedded
    public PhotoNews photoNews;

    @Embedded
    public SourceNews sourceNews;
}
