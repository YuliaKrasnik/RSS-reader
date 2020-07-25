package ru.android.test.task.rssreader.useCase.news;

import ru.android.test.task.rssreader.repository.NewsRepository;
import ru.android.test.task.rssreader.repository.db.INewsDataSource;
import ru.android.test.task.rssreader.useCase.common.UseCase;

public class GetCountNewsInDbUseCase extends UseCase<GetCountNewsInDbUseCase.RequestValues, GetCountNewsInDbUseCase.ResponseValues> {
    private final NewsRepository newsRepository;

    public GetCountNewsInDbUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    protected void execute (RequestValues requestValues) {
        newsRepository.getCountNewsInDb( new INewsDataSource.IGetCountNewsInDbCallback() {
            @Override
            public void didGet(int countNews) {
                final GetCountNewsInDbUseCase.ResponseValues responseValues = new GetCountNewsInDbUseCase.ResponseValues(countNews);
                getCallback().onSuccess(responseValues);
            }
        });
    }

    public static final class RequestValues implements UseCase.IRequestValues {
    }

    public static final class ResponseValues implements UseCase.IResponseValues {
        private final int count;

        public ResponseValues(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }
}
