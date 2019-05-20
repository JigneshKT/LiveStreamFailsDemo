package jigneshkt.test.com.testproject.presentation.ui.home;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import jigneshkt.test.com.testproject.base.BaseActivityPresenter;
import jigneshkt.test.com.testproject.domain.intercepter.LiveStreamFails.GetLiveStreamFailsUseCase;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;


public class HomeActivityPresenter extends BaseActivityPresenter<HomeActivityView> {

    private static final String TAG = HomeActivityPresenter.class.getSimpleName();
    private final GetLiveStreamFailsUseCase getLiveStreamFailsUseCase;



    @Override
    protected void updateViewState() {
        super.updateViewState();
        getLiveStreamFails();
    }

    @Inject
    public HomeActivityPresenter(GetLiveStreamFailsUseCase getLiveStreamFailsUseCase) {
        this.getLiveStreamFailsUseCase= getLiveStreamFailsUseCase;
    }


    private void getLiveStreamFails(){

        getLiveStreamFailsUseCase.execute(new DisposableObserver<ArrayList<LiveStreamFail>>() {
            @Override
            public void onNext(ArrayList<LiveStreamFail> liveStreams) {
                view.onLiveStreamSuccess(liveStreams);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }





}
