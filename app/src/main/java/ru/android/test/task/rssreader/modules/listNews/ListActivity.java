package ru.android.test.task.rssreader.modules.listNews;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import ru.android.test.task.rssreader.R;
import ru.android.test.task.rssreader.modules.common.SingleFragmentActivity;

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

        view = (IListModuleContract.IListView) fragment;
        final IListModuleContract.IListPresenter presenter = new ListPresenter(view);
        view.setPresenter(presenter);
    }
}
