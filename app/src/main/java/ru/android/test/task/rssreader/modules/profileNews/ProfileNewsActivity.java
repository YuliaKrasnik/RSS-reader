package ru.android.test.task.rssreader.modules.profileNews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.android.test.task.rssreader.R;
import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.repository.NewsRepository;
import ru.android.test.task.rssreader.repository.db.CacheNewsDataSource;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;

public class ProfileNewsActivity extends AppCompatActivity implements ProfileNewsModuleContract.IProfileView {
    private ProfileNewsModuleContract.IProfilePresenter presenter;
    private ImageView photoNews;
    private ImageButton openInBrowser;
    private ImageButton shareEmail;
    private TextView nameSource;
    private TextView publicationDate;
    private TextView headingNews;
    private TextView descriptionNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_news);

        final INewsDataSource newsDataSource = new CacheNewsDataSource();
        final NewsRepository newsRepository = new NewsRepository(newsDataSource);

        final UseCaseExecutor useCaseExecutor = UseCaseExecutor.getInstance();
        final ProfileNewsModuleContract.IProfileView view = this;
        final ProfileNewsModuleContract.IProfilePresenter presenter = new ProfileNewsPresenter(view, useCaseExecutor);
        view.setPresenter(presenter);

        findView();
        setListenerOnButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    void findView() {
        photoNews = findViewById(R.id.iv_photo_news);
        openInBrowser = findViewById(R.id.btn_open_in_browser);
        shareEmail = findViewById(R.id.btn_share_email);
        nameSource = findViewById(R.id.tv_name_source);
        publicationDate = findViewById(R.id.tv_pub_date);
        headingNews = findViewById(R.id.tv_heading_news);
        descriptionNews = findViewById(R.id.tv_description_news);
    }

    void setListenerOnButton() {
        openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCLickOpenInBrowserBtn();
            }
        });

        shareEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickShareBtn();
            }
        });

    }

    @Override
    public void setPresenter(ProfileNewsModuleContract.IProfilePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProfileInformation(News news) {
        Picasso.get().load(news.photoNews.url).into(photoNews);
        nameSource.setText(news.sourceNews.titleSource);
        publicationDate.setText(news.pubDate);
        headingNews.setText(news.title);
        descriptionNews.setText(news.description);
    }

    @Override
    public Intent getIntentFromOtherActivity() {
        return getIntent();
    }

    @Override
    public void setShareIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share with"));
        }
    }

    @Override
    public void setOpenInBrowserIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Open with browser"));
        }
    }
}
