package ru.android.test.task.rssreader.modules.profileNews;

import ru.android.test.task.rssreader.modules.common.IBasePresenter;
import ru.android.test.task.rssreader.modules.common.IBaseView;

public interface ProfileNewsModuleContract {
    interface IProfileView extends IBaseView<IProfilePresenter> {
    }

    interface IProfilePresenter extends IBasePresenter {
        void onClickShareBtn();

        void onCLickOpenInBrowserBtn();

        void onResume();
    }
}
