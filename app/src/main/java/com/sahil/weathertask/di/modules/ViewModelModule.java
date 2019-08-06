package com.sahil.weathertask.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sahil.weathertask.di.factory.DaggerViewModelFactory;
import com.sahil.weathertask.di.scopes.ViewModelKey;
import com.sahil.weathertask.presentation.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel provideWeatherViewModel(HomeViewModel homeViewModel);
}
