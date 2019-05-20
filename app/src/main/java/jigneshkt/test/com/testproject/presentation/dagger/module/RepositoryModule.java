package jigneshkt.test.com.testproject.presentation.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jigneshkt.test.com.testproject.data.api.LiveStreamFailsAPI;
import jigneshkt.test.com.testproject.data.implementation.LiveStreamFailsDataRepository;
import jigneshkt.test.com.testproject.domain.repository.LiveStreamFailsRepository;

@Module
public class RepositoryModule {

    Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    LiveStreamFailsRepository provideLiveStreamFailsRepository(LiveStreamFailsAPI liveStreamFailsAPI) {
        return new LiveStreamFailsDataRepository(liveStreamFailsAPI);
    }

}
