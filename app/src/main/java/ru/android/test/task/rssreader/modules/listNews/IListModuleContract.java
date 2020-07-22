package ru.android.test.task.rssreader.modules.listNews;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.modules.common.IBasePresenter;
import ru.android.test.task.rssreader.modules.common.IBaseView;

public interface IListModuleContract {
    interface IListView extends IBaseView<IListPresenter> {
        void showNews(final List<News> news);
    }

    interface IListPresenter extends IBasePresenter {
        void onResume();

    }
}
