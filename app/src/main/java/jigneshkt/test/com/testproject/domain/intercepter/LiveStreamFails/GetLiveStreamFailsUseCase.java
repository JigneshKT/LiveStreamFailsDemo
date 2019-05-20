package jigneshkt.test.com.testproject.domain.intercepter.LiveStreamFails;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.domain.intercepter.UseCase;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;
import jigneshkt.test.com.testproject.domain.repository.LiveStreamFailsRepository;

public class GetLiveStreamFailsUseCase extends UseCase<ArrayList<LiveStreamFail>> {

    private LiveStreamFailsRepository liveStreamFailsRepository;
    @Inject
    public GetLiveStreamFailsUseCase(LiveStreamFailsRepository liveStreamFailsRepository){
        this.liveStreamFailsRepository = liveStreamFailsRepository;
    }


    @Override
    protected Observable<ArrayList<LiveStreamFail>> buildUseCaseObservable() {
        return liveStreamFailsRepository.getLiveStreamsFails();
    }
}
