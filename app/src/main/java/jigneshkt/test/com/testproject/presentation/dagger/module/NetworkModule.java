package jigneshkt.test.com.testproject.presentation.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import jigneshkt.test.com.testproject.data.api.LiveStreamFailsAPI;
import jigneshkt.test.com.testproject.presentation.dagger.qualifier.ApiOkHttpClient;
import jigneshkt.test.com.testproject.presentation.dagger.qualifier.AuthOkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static jigneshkt.test.com.testproject.BuildConfig.BASE_URL;
import static jigneshkt.test.com.testproject.BuildConfig.DEBUG;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        Gson gson = new GsonBuilder().setLenient().create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApiOkHttpClient
    OkHttpClient provideApiOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(generateHttpLoggingInterceptor());
        return builder.build();
    }


    private HttpLoggingInterceptor generateHttpLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);
        return logger;
    }


    //////// Get Live Stream API     ///////////////////////////
    @Provides
    @Singleton
    LiveStreamFailsAPI provideLiveStreamFailsAPI(Converter.Factory factory, @AuthOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(factory)
                .build().create(LiveStreamFailsAPI.class);
    }


    @Provides @AuthOkHttpClient
    OkHttpClient provideAuthOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(generateHttpLoggingInterceptor());
        return builder.build();
    }


}
