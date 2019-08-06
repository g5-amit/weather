package com.sahil.weathertask.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.data.pojo.WeatherForecastResponse;

public class MapperUtils {

    public static WeatherDisplayData mapWeatherResponseToDisplay(WeatherForecastResponse response) {
        WeatherDisplayData weatherDisplayData = new WeatherDisplayData(
                response.getLocation().getName(),
                (int) response.getCurrent().getTemp_c(),
                new ArrayList<>()
        );

        List<WeatherForecastResponse.ForecastDay> forecastday =
                response.getForecast().getForecastday();
        for (int i = 1; i < forecastday.size(); i++) { //Ignoring the first element as it is for today.
            WeatherForecastResponse.ForecastDay forecast = forecastday.get(i);

            WeatherDisplayData.Forecast displayForecast = new WeatherDisplayData.Forecast(
                    forecast.getDate(),
                    DateTimeUtils.epochToWeekDay(forecast.getDate_epoch()),
                    ((int) forecast.getDay().getAvgtemp_c()) + " C"
            );

            weatherDisplayData.getForecastList().add(displayForecast);
        }

        return weatherDisplayData;
    }
}
