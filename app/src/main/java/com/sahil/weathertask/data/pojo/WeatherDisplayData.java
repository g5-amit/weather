package com.sahil.weathertask.data.pojo;

import androidx.annotation.Nullable;

import java.util.List;

public class WeatherDisplayData {
    private String place;
    private int currentTemp;
    private List<Forecast> forecastList;

    public WeatherDisplayData(String place, int currentTemp, List<Forecast> forecastList) {
        this.place = place;
        this.currentTemp = currentTemp;
        this.forecastList = forecastList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof WeatherDisplayData) {
            WeatherDisplayData model = (WeatherDisplayData) obj;
            return (place.equals(model.place) && currentTemp == model.currentTemp &&
                    forecastList.equals(model.getForecastList()));
        } else {
            return false;
        }
    }

    public static class Forecast {
        private String date;
        private String weekDay;
        private String temp;

        public Forecast(String date, String weekDay, String temp) {
            this.date = date;
            this.weekDay = weekDay;
            this.temp = temp;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof Forecast) {
                Forecast forecast = (Forecast) obj;
                return (date.equals(forecast.date) && weekDay.equals(forecast.weekDay) &&
                        temp.equals(forecast.temp));
            } else {
                return false;
            }
        }
    }
}
