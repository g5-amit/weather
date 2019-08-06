package com.sahil.weathertask.di.modules;

import com.sahil.weathertask.di.scopes.ActivityScope;
import com.sahil.weathertask.presentation.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();
}
