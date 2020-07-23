package ru.android.test.task.rssreader.useCase.common;

public final class UseCaseExecutor {

    private static UseCaseExecutor sharedInstance;

    public static UseCaseExecutor getInstance() {
        if (null == sharedInstance) {
            sharedInstance = new UseCaseExecutor();
        }
        return sharedInstance;
    }

    private UseCaseExecutor() {

    }

    public <T extends UseCase.IRequestValues, R extends UseCase.IResponseValues> void execute(
            final UseCase<T, R> useCase, final T requestValues, final UseCase.IUseCaseCallback<R> callback) {

        useCase.setRequestParameters(requestValues);
        useCase.setCallback(callback);

        useCase.run();
    }
}