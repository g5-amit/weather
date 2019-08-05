package com.sahil.weathertask.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.sahil.weathertask.di.factory.DaggerViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);
}
