package ru.android.test.task.rssreader.modules.listNews;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import ru.android.test.task.rssreader.R;
import ru.android.test.task.rssreader.modules.common.SingleFragmentActivity;
import ru.android.test.task.rssreader.repository.NewsRepository;
import ru.android.test.task.rssreader.repository.db.CacheNewsDataSource;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;
import ru.android.test.task.rssreader.useCase.news.ObtainNewsUseCase;
import ru.android.test.task.rssreader.useCase.news.RefreshNewsUseCase;

public class ListActivity extends SingleFragmentActivity {
    IListModuleContract.IListView view;

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final INewsDataSource newsDataSource = new CacheNewsDataSource();
        final UseCaseExecutor useCaseExecutor = UseCaseExecutor.getInstance();
        final NewsRepository newsRepository = new NewsRepository(newsDataSource);

        final ObtainNewsUseCase obtainNewsUseCase = new ObtainNewsUseCase(newsRepository);
        final RefreshNewsUseCase refreshNewsUseCase = new RefreshNewsUseCase(newsRepository);

        view = (IListModuleContract.IListView) fragment;
        final IListModuleContract.IListPresenter presenter = new ListPresenter(view, useCaseExecutor, obtainNewsUseCase, refreshNewsUseCase);
        view.setPresenter(presenter);
    }
}
