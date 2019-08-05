package com.sahil.weathertask.presentation;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    public CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
