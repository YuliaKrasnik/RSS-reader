package ru.android.test.task.rssreader.modules.listNews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.android.test.task.rssreader.R;
import ru.android.test.task.rssreader.model.modelDb.News;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private final List<News> listNews;

    public ListAdapter(List<News> listNews) {
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.getTitleNews().setText(listNews.get(position).title);
        holder.getDateNews().setText(listNews.get(position).pubDate);
        Picasso.get().load(listNews.get(position).photoNews.url).into(holder.getImageNews());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
