package ru.android.test.task.rssreader.modules.listNews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ru.android.test.task.rssreader.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView titleNews;
    private TextView dateNews;
    private ImageView imageNews;

    ListViewHolder(@NonNull View itemView) {
        super(itemView);
        titleNews = itemView.findViewById(R.id.txtTitle);
        dateNews = itemView.findViewById(R.id.txtPubDate);
        imageNews = itemView.findViewById(R.id.imageView);
    }

    TextView getTitleNews() {
        return titleNews;
    }

    TextView getDateNews() {
        return dateNews;
    }

    ImageView getImageNews() {
        return imageNews;
    }

}