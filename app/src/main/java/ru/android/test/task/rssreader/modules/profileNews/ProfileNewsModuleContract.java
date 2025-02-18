package ru.android.test.task.rssreader.modules.profileNews;

import android.content.Intent;

import ru.android.test.task.rssreader.model.modelDb.News;
import ru.android.test.task.rssreader.modules.common.IBasePresenter;
import ru.android.test.task.rssreader.modules.common.IBaseView;

public interface ProfileNewsModuleContract {
    interface IProfileView extends IBaseView<IProfilePresenter> {
        void showProfileInformation(News news);

        Intent getIntentFromOtherActivity();

        void setShareIntent(Intent intent);

        void setOpenInBrowserIntent(Intent intent);
    }

    interface IProfilePresenter extends IBasePresenter {
        void onClickShareBtn();

        void onCLickOpenInBrowserBtn();

        void onResume();
    }
}
