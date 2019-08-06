package com.sahil.weathertask.model;

import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.data.pojo.WeatherForecastResponse;
import com.sahil.weathertask.data.repo.WeatherForecastRepo;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

public class WeatherForecastModelTest {

    @Test
    public void fetching_data_from_network_succeeds() {
        // Setup
        WeatherForecastRepo forecastRepo = Mockito.mock(WeatherForecastRepo.class);
        String location = "Gurugram";
        int days = 5;
        float currentTemperature = 34.5f;
        String date = "05-08-2019";
        long date_epoch = 1564999962L;

        List<WeatherDisplayData.Forecast> list = new ArrayList<>();
        list.add(new WeatherDisplayData.Forecast(date, "Monday", "34 C"));

        List<WeatherForecastResponse.ForecastDay> forecast = new ArrayList<>();
        forecast.add(null);
        forecast.add(new WeatherForecastResponse.ForecastDay(date, date_epoch,
                new WeatherForecastResponse.Day(currentTemperature)));

        Mockito.when(forecastRepo.getWeatherForecast(location, days))
                .thenReturn(
                        Single.just(
                                new WeatherForecastResponse(
                                        new WeatherForecastResponse.WeatherLocation(location),
                                        new WeatherForecastResponse.CurrentWeather(currentTemperature),
                                        new WeatherForecastResponse.WeatherForecast(forecast)
                                )
                        )
                );

        // Act
        TestObserver<WeatherDisplayData> observer = new WeatherForecastModel(forecastRepo)
                .getWeatherForecast(location, days)
                .test();

        // Assert
        observer
                .assertNoErrors()
                .assertValues(
                        new WeatherDisplayData(location, 34, list)
                )
                .assertTerminated()
                .dispose();
    }

    @Test
    public void fetching_data_from_network_failed() {
        // Setup
        WeatherForecastRepo forecastRepo = Mockito.mock(WeatherForecastRepo.class);
        String location = "Gurugram";
        int days = 5;
        Throwable throwable = new Throwable("custom error");

        Mockito.when(forecastRepo.getWeatherForecast(location, days))
                .thenReturn(
                        Single.error(throwable)
                );

        //Act
        TestObserver<WeatherDisplayData> observer = new WeatherForecastModel(forecastRepo)
                .getWeatherForecast(location, days)
                .test();

        //Assert
        observer
                .assertError(throwable)
                .assertTerminated()
                .dispose();
    }
}