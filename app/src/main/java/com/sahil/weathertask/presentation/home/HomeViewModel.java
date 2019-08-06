package com.sahil.weathertask.presentation.home;

import androidx.lifecycle.MutableLiveData;

import com.sahil.weathertask.data.pojo.BaseModel;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.model.WeatherForecastModel;
import com.sahil.weathertask.presentation.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends BaseViewModel {

    private WeatherForecastModel weatherForecastModel;

    @Inject
    public HomeViewModel(WeatherForecastModel weatherForecastModel) {
        this.weatherForecastModel = weatherForecastModel;
    }

    public MutableLiveData<BaseModel<WeatherDisplayData>> weatherForecastData = new MutableLiveData<>();

    public void getWeatherForecast(String location, int days) {
        weatherForecastModel.getWeatherForecast(location, days)
                .subscribe(new SingleObserver<WeatherDisplayData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(WeatherDisplayData weatherDisplayData) {
                        weatherForecastData.setValue(new BaseModel(true, weatherDisplayData));
                    }

                    @Override
                    public void onError(Throwable e) {
                        weatherForecastData.setValue(new BaseModel(false, e));
                    }
                });
    }
}
