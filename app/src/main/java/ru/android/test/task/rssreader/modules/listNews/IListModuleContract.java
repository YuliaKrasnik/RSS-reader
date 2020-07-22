package ru.android.test.task.rssreader.modules.listNews;

import ru.android.test.task.rssreader.modules.common.IBasePresenter;
import ru.android.test.task.rssreader.modules.common.IBaseView;

public interface IListModuleContract {
    interface IListView extends IBaseView<IListPresenter> {

    }

    interface IListPresenter extends IBasePresenter {

    }
}
