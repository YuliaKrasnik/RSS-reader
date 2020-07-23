package ru.android.test.task.rssreader.modules.profileNews;

import android.content.Intent;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.model.modelDb.PhotoNews;
import ru.android.test.task.rssreader.model.modelDb.SourceNews;
import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;

public class ProfileNewsPresenter implements ProfileNewsModuleContract.IProfilePresenter {
    private final ProfileNewsModuleContract.IProfileView view;
    private final UseCaseExecutor useCaseExecutor;
    private boolean isFirstInitialized = true;
    private News news;

    public ProfileNewsPresenter(ProfileNewsModuleContract.IProfileView view, UseCaseExecutor useCaseExecutor) {
        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
    }

    @Override
    public void onClickShareBtn() {

    }

    @Override
    public void onCLickOpenInBrowserBtn() {

    }

    @Override
    public void onResume() {
        if (isFirstInitialized) {
            final Intent intent = view.getIntentFromOtherActivity();
            news = obtainInformation(intent);
            isFirstInitialized = false;
        }
        view.showProfileInformation(news);

    }

    private News obtainInformation(Intent intent) {
        News news = new News();
        long id = intent.getLongExtra("id", 0);
        final String strTitleItemNews = intent.getStringExtra("title");
        String strDescriptionItemNews = intent.getStringExtra("description");
        String strPubDateItemNews = intent.getStringExtra("pubDate");
        final String strLinkItemNews = intent.getStringExtra("link");
        final String strUrlPhotoNews = intent.getStringExtra("urlPhotoNews");
        final String strTitleSource = intent.getStringExtra("titleSource");

        news.id = id;
        news.title = strTitleItemNews;
        news.description = strDescriptionItemNews;
        news.pubDate = strPubDateItemNews;
        news.link = strLinkItemNews;

        PhotoNews photoNews = new PhotoNews();
        photoNews.url = strUrlPhotoNews;
        news.photoNews = photoNews;

        SourceNews sourceNews = new SourceNews();
        sourceNews.titleSource = strTitleSource;
        news.sourceNews = sourceNews;

        return news;
    }
}
