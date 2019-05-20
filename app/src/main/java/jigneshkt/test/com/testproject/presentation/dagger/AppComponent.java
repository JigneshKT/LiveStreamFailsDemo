package jigneshkt.test.com.testproject.presentation.dagger;

import javax.inject.Singleton;

import dagger.Component;
import jigneshkt.test.com.testproject.presentation.dagger.module.ApplicationModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.NetworkModule;
import jigneshkt.test.com.testproject.presentation.dagger.module.RepositoryModule;
import jigneshkt.test.com.testproject.presentation.ui.home.HomeActivity;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject (jigneshkt.test.com.testproject.TestProjectApplication testProject);
    void inject (HomeActivity homeActivity);
}
