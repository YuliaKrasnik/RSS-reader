package ru.android.test.task.rssreader.useCase.common;

public abstract class UseCase<RequestParametersType extends UseCase.IRequestValues,
        ResponseType extends UseCase.IResponseValues> {

    private RequestParametersType requestParameters;
    private IUseCaseCallback<ResponseType> callback;

    protected abstract void execute(final RequestParametersType requestParameters);

    public interface IRequestValues {
    }

    public interface IResponseValues {
    }

    public interface IUseCaseCallback<T> {
        void onSuccess(final T response);

        void onError(final String errorMessage);
    }

    void run() {
        execute(requestParameters);
    }

    public RequestParametersType getRequestParameters() {
        return requestParameters;
    }

    void setRequestParameters(final RequestParametersType requestParameters) {
        this.requestParameters = requestParameters;
    }

    public IUseCaseCallback<ResponseType> getCallback() {
        return callback;
    }

    void setCallback(final IUseCaseCallback<ResponseType> callback) {
        this.callback = callback;
    }
}
