package ru.android.test.task.rssreader.repository.db;

import java.util.ArrayList;
import java.util.List;

import ru.android.test.task.rssreader.App;
import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.model.modelDb.PhotoNews;
import ru.android.test.task.rssreader.model.modelDb.SourceNews;
import ru.android.test.task.rssreader.model.modelTags.Channel;
import ru.android.test.task.rssreader.model.modelTags.Item;
import ru.android.test.task.rssreader.model.modelTags.Rss;

public class CacheNewsDataSource implements INewsDataSource {
    private AppDatabase db = App.getInstance().getDatabase();
    private INewsDao newsDao = db.newsDao();
    private static List<News> list = new ArrayList<>();

    @Override
    public void obtainNews(IObtainNewsCallback callback) {
        //newsDao.delete();
        list = newsDao.getNews();
        callback.didObtain(list);
    }

    @Override
    public void writeDataStart(Rss parsedObject) {
        List<Item> listItem = parsedObject.getChannel().getItem();
        Channel informationSource = parsedObject.getChannel();
        for (int i = listItem.size() - 1; i >= 0; i--) {
            writeNewsToCache(listItem.get(i), informationSource);
        }
    }

    private void writeNewsToCache(Item item, Channel informationSource) {
        News news = new News();
        news.title = item.getTitle();
        news.link = item.getLink();
        news.description = item.getDescription();
        news.pubDate = item.getPubDate();

        PhotoNews photoNews = new PhotoNews();
        photoNews.url = item.getEnclosure().getUrl();
        photoNews.type = item.getEnclosure().getType();
        photoNews.length = item.getEnclosure().getLength();
        news.photoNews = photoNews;

        SourceNews sourceNews = new SourceNews();
        sourceNews.titleSource = informationSource.getTitle();
        news.sourceNews = sourceNews;
        newsDao.insert(news);
    }

    @Override
    public boolean isEmpty() {
        boolean flag;
        News news = newsDao.getAnyNews();
        flag = news == null;
        return flag;
    }
}
