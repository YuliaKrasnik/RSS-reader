package ru.android.test.task.rssreader.useCase.news;

import android.util.Log;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.repository.NewsRepository;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;
import ru.android.test.task.rssreader.useCase.common.UseCase;

public class RefreshNewsUseCase extends UseCase<RefreshNewsUseCase.RequestValues, RefreshNewsUseCase.ResponseValues> {
    private final NewsRepository newsRepository;

    public RefreshNewsUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected void execute(RequestValues requestValues) {
        newsRepository.refreshNews(new INewsDataSource.IObtainNewsCallback() {
            @Override
            public void didObtain(List<News> news) {
                final ResponseValues responseValues = new ResponseValues(news);
                getCallback().onSuccess(responseValues);
            }

            @Override
            public void didFailObtain(int errorStatusCode) {
                Log.e("REFRESH_NEWS", String.valueOf(errorStatusCode));
            }
        });
    }

    public static final class RequestValues implements UseCase.IRequestValues {
    }

    public static final class ResponseValues implements UseCase.IResponseValues {
        private final List<News> news;

        ResponseValues(final List<News> news) {
            this.news = news;
        }

        public List<News> getNews() {
            return news;
        }
    }
}