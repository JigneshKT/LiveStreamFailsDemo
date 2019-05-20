package jigneshkt.test.com.testproject.domain.repository;

import java.util.ArrayList;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;

public interface LiveStreamFailsRepository {

    Observable<ArrayList<LiveStreamFail>> getLiveStreamsFails();

}
