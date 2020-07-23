package ru.android.test.task.rssreader.modules.listNews;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.modules.profileNews.ProfileNewsActivity;
import ru.android.test.task.rssreader.useCase.common.UseCase;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;
import ru.android.test.task.rssreader.useCase.news.ObtainNewsUseCase;
import ru.android.test.task.rssreader.useCase.news.RefreshNewsUseCase;

public class ListPresenter implements IListModuleContract.IListPresenter {
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;
    private final ObtainNewsUseCase obtainNewsUseCase;
    private final RefreshNewsUseCase refreshNewsUseCase;

    private final static int COUNT_NEWS_IN_PART = 10;

    public ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor, ObtainNewsUseCase obtainNewsUseCase, RefreshNewsUseCase refreshNewsUseCase) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.obtainNewsUseCase = obtainNewsUseCase;
        this.refreshNewsUseCase = refreshNewsUseCase;
    }

    @Override
    public void onResume() {
        obtainNews(0,COUNT_NEWS_IN_PART);
    }

    @Override
    public void onRefresh() {
        refreshNews(COUNT_NEWS_IN_PART);
    }

    @Override
    public void onItemClicked(Context context, News news) {
        sendNewsInOtherActivity(context, news);
    }

    @Override
    public void onScrolled(LinearLayoutManager linearLayoutManager) {

    }

    private void sendNewsInOtherActivity(Context context, News news) {
        Intent intent = new Intent(context, ProfileNewsActivity.class);
        intent.putExtra("id", news.id);
        intent.putExtra("title", news.title);
        intent.putExtra("description", news.description);
        intent.putExtra("pubDate", news.pubDate);
        intent.putExtra("link", news.link);
        intent.putExtra("urlPhotoNews",news.photoNews.url);
        intent.putExtra("titleSource", news.sourceNews.titleSource);
        context.startActivity(intent);
    }

    private void refreshNews(int countNews) {
        final RefreshNewsUseCase.RequestValues requestValues = new RefreshNewsUseCase.RequestValues(countNews);
        useCaseExecutor.execute(refreshNewsUseCase, requestValues, new UseCase.IUseCaseCallback<RefreshNewsUseCase.ResponseValues>() {
            @Override
            public void onSuccess(RefreshNewsUseCase.ResponseValues response) {
                final List<News> news = response.getNews();
                view.showNews(news);
                view.setRefreshing(false);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    private void obtainNews(int startingPosition, int countNews) {
        final ObtainNewsUseCase.RequestValues requestValues = new ObtainNewsUseCase.RequestValues(startingPosition, countNews);
        useCaseExecutor.execute(obtainNewsUseCase, requestValues, new UseCase.IUseCaseCallback<ObtainNewsUseCase.ResponseValues>() {
            @Override
            public void onSuccess(ObtainNewsUseCase.ResponseValues response) {
                final List<News> news = response.getNews();
                view.showNews(news);
            }

            @Override
            public void onError(String errorMessage) {

            }

        });
    }

}
