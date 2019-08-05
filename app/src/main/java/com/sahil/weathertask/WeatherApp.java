package com.sahil.weathertask;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

import com.sahil.weathertask.di.component.DaggerAppComponent;

public class WeatherApp extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
