package jigneshkt.test.com.testproject.presentation.ui.home;

import java.util.ArrayList;

import jigneshkt.test.com.testproject.base.BaseActivityView;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;


public interface HomeActivityView extends BaseActivityView {


    void onLiveStreamSuccess(ArrayList<LiveStreamFail> liveStreams);
    void onLiveStreamFailure();
}
