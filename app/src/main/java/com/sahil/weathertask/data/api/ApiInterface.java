package com.sahil.weathertask.data.api;

import com.sahil.weathertask.common.values.Constants;
import com.sahil.weathertask.data.pojo.WeatherForecastResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(Constants.WEATHER_FORECAST_URL)
    Single<WeatherForecastResponse> getWeatherForcast(
            @Query("key") String authKey,
            @Query("q") String latlng,
            @Query("days") int noOfDays
    );
}
