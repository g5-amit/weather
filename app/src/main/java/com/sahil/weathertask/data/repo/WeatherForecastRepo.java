package com.sahil.weathertask.data.repo;

import javax.inject.Inject;

import com.sahil.weathertask.BuildConfig;
import com.sahil.weathertask.data.api.ApiInterface;
import com.sahil.weathertask.data.pojo.WeatherForecastResponse;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherForecastRepo {

    private ApiInterface apiInterface;

    @Inject
    public WeatherForecastRepo(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<WeatherForecastResponse> getWeatherForecast(String latlng, int days) {
        return apiInterface.getWeatherForcast(BuildConfig.API_KEY, latlng, days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
