package com.sahil.weathertask.home;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.sahil.weathertask.data.pojo.BaseModel;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.model.WeatherForecastModel;
import com.sahil.weathertask.presentation.home.HomeViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class HomeViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void fetching_data_from_network_succeeds() {
        //Setup
        WeatherForecastModel forecastModel = Mockito.mock(WeatherForecastModel.class);
        int days = 5;
        String city = "Gurugram";
        int currentTemperature = 30;
        List<WeatherDisplayData.Forecast> forecastList = new ArrayList<>();

        Mockito.when(forecastModel.getWeatherForecast(city, days))
                .thenReturn(
                        Single.just(new WeatherDisplayData(
                                city,
                                currentTemperature,
                                forecastList
                        ))
                );

        //Act
        HomeViewModel homeViewModel = new HomeViewModel(forecastModel);

        Observer<BaseModel<WeatherDisplayData>> observer = Mockito.mock(Observer.class);
        homeViewModel.weatherForecastData.observeForever(observer);

        homeViewModel.getWeatherForecast(city, days);

        //Assert
        Mockito.verify(observer)
                .onChanged(new BaseModel<WeatherDisplayData>(true, new WeatherDisplayData(
                        city,
                        currentTemperature,
                        forecastList
                )));
    }

    @Test
    public void fetching_data_from_network_failed() {
        //Setup
        WeatherForecastModel forecastModel = Mockito.mock(WeatherForecastModel.class);
        int days = 5;
        String city = "Gurugram";
        Throwable throwable = new Throwable("error");

        Mockito.when(forecastModel.getWeatherForecast(city, days))
                .thenReturn(
                        Single.error(throwable)
                );

        //Act
        HomeViewModel homeViewModel = new HomeViewModel(forecastModel);

        Observer<BaseModel<WeatherDisplayData>> observer = Mockito.mock(Observer.class);
        homeViewModel.weatherForecastData.observeForever(observer);

        homeViewModel.getWeatherForecast(city, days);

        //Assert
        Mockito.verify(observer)
                .onChanged(new BaseModel<>(false, throwable));
    }
}