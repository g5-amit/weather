package com.sahil.weathertask.data.pojo;

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
    }
}
