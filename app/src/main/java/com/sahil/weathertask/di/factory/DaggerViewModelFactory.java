package com.sahil.weathertask.di.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class DaggerViewModelFactory implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelsMap;

    @Inject
    public DaggerViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelsMap) {
        this.viewModelsMap = viewModelsMap;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> provider = viewModelsMap.get(modelClass);
        if (provider == null) {
            Iterator<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> iterator =
                    viewModelsMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry =
                        iterator.next();
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    provider = entry.getValue();
                }
            }
        }
        if (provider == null) {
            throw new IllegalArgumentException("unknown model class $modelClass");
        }
        try {
            return (T) provider.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
