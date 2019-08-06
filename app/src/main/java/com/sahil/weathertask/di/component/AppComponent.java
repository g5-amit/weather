package com.sahil.weathertask.di.component;

import android.content.Context;

import com.sahil.weathertask.di.modules.ActivityModule;
import com.sahil.weathertask.di.modules.ApiModule;
import com.sahil.weathertask.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityModule.class,
                ViewModelModule.class,
                ApiModule.class
        }
)
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Context context);

        AppComponent build();
    }
}
