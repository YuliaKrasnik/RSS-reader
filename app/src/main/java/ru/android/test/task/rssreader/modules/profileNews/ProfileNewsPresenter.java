package ru.android.test.task.rssreader.modules.profileNews;

import ru.android.test.task.rssreader.useCase.common.UseCaseExecutor;

public class ProfileNewsPresenter implements ProfileNewsModuleContract.IProfilePresenter {
    private final ProfileNewsModuleContract.IProfileView view;
    private final UseCaseExecutor useCaseExecutor;


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

    }
}
