package ru.android.test.task.rssreader.modules.listNews;

import ru.android.test.task.rssreader.model.modelDb.News;

public interface IListClickListener {
    void onItemClicked(News news);
}
