package com.sahil.weathertask.presentation.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.sahil.weathertask.R;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.presentation.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private TextView textView_temperature, textView_city;
    private MaterialCardView cardView;
    private RecyclerView recycler_forecast;

    private LinearLayout layout_error;
    private Button button_retry;

    private FrameLayout layout_loading;
    private ImageView imageView_loading;

    private HomeViewModel viewModel;

    private WeatherForecastAdapter forecastAdapter;

    private List<WeatherDisplayData.Forecast> forecastList = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void initializeViews(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel.class);

        initLayouts();
    }

    private void initLayouts() {
        textView_temperature = findViewById(R.id.textView_temperature);
        textView_city = findViewById(R.id.textView_city);
        cardView = findViewById(R.id.cardView);
        recycler_forecast = findViewById(R.id.recycler_forecast);

        layout_error = findViewById(R.id.layout_error);
        button_retry = findViewById(R.id.button_retry);

        layout_loading = findViewById(R.id.layout_loading);
        imageView_loading = findViewById(R.id.imageView_loading);

        forecastAdapter = new WeatherForecastAdapter(this, forecastList);
        recycler_forecast.setAdapter(forecastAdapter);
    }
}
