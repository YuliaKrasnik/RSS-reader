package ru.android.test.task.rssreader.modules.listNews;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.useCase.common.UseCase;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;
import ru.android.test.task.rssreader.useCase.news.ObtainNewsUseCase;
import ru.android.test.task.rssreader.useCase.news.RefreshNewsUseCase;

public class ListPresenter implements IListModuleContract.IListPresenter {
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;
    private final ObtainNewsUseCase obtainNewsUseCase;
    private final RefreshNewsUseCase refreshNewsUseCase;

    public ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor, ObtainNewsUseCase obtainNewsUseCase, RefreshNewsUseCase refreshNewsUseCase) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.obtainNewsUseCase = obtainNewsUseCase;
        this.refreshNewsUseCase = refreshNewsUseCase;
    }

    @Override
    public void onResume() {
        obtainNews();
    }

    @Override
    public void onRefresh() {
        refreshNews();
    }

    private void refreshNews() {
        final RefreshNewsUseCase.RequestValues requestValues = new RefreshNewsUseCase.RequestValues();
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

    private void obtainNews() {
        final ObtainNewsUseCase.RequestValues requestValues = new ObtainNewsUseCase.RequestValues();
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
