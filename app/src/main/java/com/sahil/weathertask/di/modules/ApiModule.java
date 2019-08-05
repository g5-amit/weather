package com.sahil.weathertask.di.modules;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.sahil.weathertask.BuildConfig;
import com.sahil.weathertask.common.values.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    public String provideBaseUrl() {
        return "";
    }

    @Provides
    @Singleton
    public Converter.Factory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    public Interceptor provideRequestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                /** Use this interceptor to add any header to request like jwt authToken, a global param */

                return chain.proceed(builder.build());
            }
        };
    }

    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                          Interceptor requestInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(Constants.NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        //show logs if app is in Debug mode
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(loggingInterceptor);
        }

        return okHttpClient.build();
    }

    @Provides
    @Singleton
    public Retrofit provideAuthenticatedRetrofit(
            @Named("baseUrl") String baseUrl, Converter.Factory converterFactory,
            CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }


}
