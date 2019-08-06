package com.sahil.weathertask.presentation.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahil.weathertask.R;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

    private Context context;
    private List<WeatherDisplayData.Forecast> forecastList;

    public WeatherForecastAdapter(Context context, List<WeatherDisplayData.Forecast> forecastList) {
        this.context = context;
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_forecast_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherDisplayData.Forecast forecast = forecastList.get(position);
        holder.textView_day.setText(forecast.getWeekDay());
        holder.textView_temperature.setText(forecast.getTemp());
    }

    @Override
    public int getItemCount() {
        return forecastList != null ? forecastList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView_day, textView_temperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_day = itemView.findViewById(R.id.textView_day);
            textView_temperature = itemView.findViewById(R.id.textView_temperature);
        }
    }
}
