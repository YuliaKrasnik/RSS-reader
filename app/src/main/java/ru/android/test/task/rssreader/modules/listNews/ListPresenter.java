package ru.android.test.task.rssreader.modules.listNews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.modules.profileNews.ProfileNewsActivity;
import ru.android.test.task.rssreader.useCase.common.UseCase;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;
import ru.android.test.task.rssreader.useCase.news.GetCountNewsInDbUseCase;
import ru.android.test.task.rssreader.useCase.news.ObtainNewsUseCase;
import ru.android.test.task.rssreader.useCase.news.RefreshNewsUseCase;

public class ListPresenter implements IListModuleContract.IListPresenter {
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;
    private final ObtainNewsUseCase obtainNewsUseCase;
    private final RefreshNewsUseCase refreshNewsUseCase;
    private final GetCountNewsInDbUseCase getCountNewsInDbUseCase;

    private boolean isLoading = false;
    private int pageIndex = 0;
    private final static int COUNT_NEWS_IN_PART = 10;
    private int lastPage;
    private int countNewsInDb;

    ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor, ObtainNewsUseCase obtainNewsUseCase, RefreshNewsUseCase refreshNewsUseCase, GetCountNewsInDbUseCase getCountNewsInDbUseCase) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.obtainNewsUseCase = obtainNewsUseCase;
        this.refreshNewsUseCase = refreshNewsUseCase;
        this.getCountNewsInDbUseCase = getCountNewsInDbUseCase;
    }

    @Override
    public void onResume() {
        obtainNews(recountStartingPosition(), COUNT_NEWS_IN_PART);
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
        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        final String TAG = "PAGING";
        Log.i(TAG, "visibleItemCount  " + visibleItemCount);
        Log.i(TAG, "totalItemCount  " + totalItemCount);
        Log.i(TAG, "firstVisibleItemPosition  " + firstVisibleItemPosition);

        if (!isLoading) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && pageIndex < lastPage) {
                isLoading = true;
                pageIndex++;

                obtainNews(recountStartingPosition(), COUNT_NEWS_IN_PART);
            }
        }
    }

    private int recountStartingPosition() {
        return pageIndex * COUNT_NEWS_IN_PART;
    }

    private void sendNewsInOtherActivity(Context context, News news) {
        Intent intent = new Intent(context, ProfileNewsActivity.class);
        intent.putExtra("id", news.id);
        intent.putExtra("title", news.title);
        intent.putExtra("description", news.description);
        intent.putExtra("pubDate", news.pubDate);
        intent.putExtra("link", news.link);
        intent.putExtra("urlPhotoNews", news.photoNews.url);
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
                setNumberLastPage();
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
                setLoading(false);
                setNumberLastPage();
            }

            @Override
            public void onError(String errorMessage) {

            }

        });
    }

    private void setNumberLastPage() {
        getNumberLastPage();
    }

    private void setLoading(boolean loading) {
        this.isLoading = loading;
    }

    private void getNumberLastPage() {
        final GetCountNewsInDbUseCase.RequestValues requestValues = new GetCountNewsInDbUseCase.RequestValues();
        useCaseExecutor.execute(getCountNewsInDbUseCase, requestValues, new UseCase.IUseCaseCallback<GetCountNewsInDbUseCase.ResponseValues>() {
            @Override
            public void onSuccess(GetCountNewsInDbUseCase.ResponseValues response) {
                countNewsInDb = response.getCount();
                countNumberLastPage(countNewsInDb);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }

    private void countNumberLastPage(int countNews) {
        if (countNews % COUNT_NEWS_IN_PART != 0) {
            lastPage = countNews / COUNT_NEWS_IN_PART + 1;
        } else {
            lastPage = countNews / COUNT_NEWS_IN_PART;
        }
    }

}
