package com.sahil.weathertask.data.pojo;

import java.util.List;

public class WeatherForecastResponse {
    private WeatherLocation location;
    private CurrentWeather current;
    private WeatherForecast forecast;

    public WeatherForecastResponse(WeatherLocation location, CurrentWeather current, WeatherForecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public WeatherLocation getLocation() {
        return location;
    }

    public void setLocation(WeatherLocation location) {
        this.location = location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public WeatherForecast getForecast() {
        return forecast;
    }

    public void setForecast(WeatherForecast forecast) {
        this.forecast = forecast;
    }

    public static class WeatherLocation {
        private String name;

        public WeatherLocation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class CurrentWeather {
        private float temp_c;

        public CurrentWeather(float temp_c) {
            this.temp_c = temp_c;
        }

        public float getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(float temp_c) {
            this.temp_c = temp_c;
        }
    }

    public static class WeatherForecast {
        private List<ForecastDay> forecastday;

        public WeatherForecast(List<ForecastDay> forecastday) {
            this.forecastday = forecastday;
        }

        public List<ForecastDay> getForecastday() {
            return forecastday;
        }

        public void setForecastday(List<ForecastDay> forecastday) {
            this.forecastday = forecastday;
        }
    }

    public static class ForecastDay {
        private String date;
        private long date_epoch;
        private Day day;

        public ForecastDay(String date, long date_epoch, Day day) {
            this.date = date;
            this.date_epoch = date_epoch;
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public long getDate_epoch() {
            return date_epoch;
        }

        public void setDate_epoch(long date_epoch) {
            this.date_epoch = date_epoch;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }
    }

    public static class Day {
        private float avgtemp_c;

        public Day(float avgtemp_c) {
            this.avgtemp_c = avgtemp_c;
        }

        public float getAvgtemp_c() {
            return avgtemp_c;
        }

        public void setAvgtemp_c(float avgtemp_c) {
            this.avgtemp_c = avgtemp_c;
        }
    }
}
