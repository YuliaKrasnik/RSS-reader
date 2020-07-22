package ru.android.test.task.rssreader.modules.listNews;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.useCase.common.UseCase;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;
import ru.android.test.task.rssreader.useCase.news.ObtainNewsUseCase;

public class ListPresenter implements IListModuleContract.IListPresenter{
    private final IListModuleContract.IListView view;
    private final UseCaseExecutor useCaseExecutor;
    private final ObtainNewsUseCase obtainNewsUseCase;

    public ListPresenter(IListModuleContract.IListView view, UseCaseExecutor useCaseExecutor, ObtainNewsUseCase obtainNewsUseCase) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.obtainNewsUseCase=obtainNewsUseCase;
    }

    @Override
    public void onResume() {
        obtainNews();
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
