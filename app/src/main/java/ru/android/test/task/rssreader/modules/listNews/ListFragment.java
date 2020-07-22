package ru.android.test.task.rssreader.modules.listNews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import ru.android.test.task.rssreader.R;
import ru.android.test.task.rssreader.model.modelDb.News;

public class ListFragment extends Fragment implements IListModuleContract.IListView{
    private IListModuleContract.IListPresenter presenter;
    private ListAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private boolean isFirstInitialized = true;

    @Override
    public void setPresenter(IListModuleContract.IListPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstInitialized) {
            presenter.onResume();
            isFirstInitialized = false;
        }
    }

    @Override
    public void showNews(List<News> news) {
        adapter = new ListAdapter(news);
        recyclerView.setAdapter(adapter);
    }
}
