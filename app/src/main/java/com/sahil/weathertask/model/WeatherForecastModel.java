package com.sahil.weathertask.model;

import com.sahil.weathertask.common.utils.MapperUtils;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.data.repo.WeatherForecastRepo;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherForecastModel {

    private WeatherForecastRepo weatherForecastRepo;

    @Inject
    public WeatherForecastModel(WeatherForecastRepo weatherForecastRepo) {
        this.weatherForecastRepo = weatherForecastRepo;
    }

    public Single<WeatherDisplayData> getWeatherForecast(String latlng, int days) {
        return weatherForecastRepo.getWeatherForecast(latlng, days)
                .map(MapperUtils::mapWeatherResponseToDisplay);

    }
}
