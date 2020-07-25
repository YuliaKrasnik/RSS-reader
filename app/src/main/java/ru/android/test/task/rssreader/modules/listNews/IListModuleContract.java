package ru.android.test.task.rssreader.modules.listNews;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.modules.common.IBasePresenter;
import ru.android.test.task.rssreader.modules.common.IBaseView;

public interface IListModuleContract {
    interface IListView extends IBaseView<IListPresenter> {
        void showNews(final List<News> news);

        void setRefreshing(boolean flag);
    }


    interface IListPresenter extends IBasePresenter {
        void onResume();

        void onRefresh();

        void onItemClicked(Context context, News news);

        void onScrolled(LinearLayoutManager linearLayoutManager);

        void restore(int count);
    }
}
