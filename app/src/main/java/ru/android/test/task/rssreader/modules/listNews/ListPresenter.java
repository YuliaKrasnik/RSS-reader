package ru.android.test.task.rssreader.modules.listNews;

public class ListPresenter implements IListModuleContract.IListPresenter{
    private final IListModuleContract.IListView view;

    public ListPresenter(IListModuleContract.IListView view) {
        this.view = view;
    }
}
