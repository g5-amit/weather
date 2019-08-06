package com.sahil.weathertask.presentation.home;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.sahil.weathertask.R;
import com.sahil.weathertask.common.utils.LocationUtils;
import com.sahil.weathertask.common.utils.NetworkUtils;
import com.sahil.weathertask.common.values.Constants;
import com.sahil.weathertask.data.pojo.WeatherDisplayData;
import com.sahil.weathertask.presentation.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements LocationUtils.LocationUpdatesCallback {

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

    private Location currentLocation;

    private LocationUtils locationUtils;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void initializeViews(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel.class);

        locationUtils = new LocationUtils(this);
        locationUtils.setLocationUpdatesCallback(this);

        initLayouts();

        showLoader();

        if (!permissionsNeeded(Constants.PERMISSION_REQUEST, Manifest.permission.ACCESS_FINE_LOCATION)) {
            getForecastData();
        }

        viewModel.weatherForecastData.observe(this, data -> {
            hideLoader();
            if (data.isSuccess()) {
                //If api call gives success
                inflateData(data.getData());
            } else {
                //If api call gives error
                layout_error.setVisibility(View.VISIBLE);
            }
        });
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

        button_retry.setOnClickListener(v -> {
            layout_error.setVisibility(View.GONE);
            getForecastData();
        });
     }

    private void getForecastData() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            if (currentLocation != null) {
                String location = currentLocation.getLatitude() + "," + currentLocation.getLongitude();
                viewModel.getWeatherForecast(location, Constants.FORECAST_DAYS);
            } else {
                locationUtils.init();
            }
        } else {
            Snackbar.make(layout_loading, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, v -> getForecastData()).show();
        }
    }

    private void inflateData(WeatherDisplayData data) {
        textView_city.setText(data.getPlace());
        textView_temperature.setText(getString(R.string.temp_degree, data.getCurrentTemp()));
        forecastList.clear();
        forecastList.addAll(data.getForecastList());
        forecastAdapter.notifyDataSetChanged();

        cardView.setVisibility(View.VISIBLE);
        cardView.setTranslationY(cardView.getHeight());

        cardView.animate()
                .translationY(0F)
                .setInterpolator(new DecelerateInterpolator(4F))
                .setDuration(1500)
                .setStartDelay(800)
                .start();
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationUtils.init();
            } else {
                Snackbar.make(layout_loading, R.string.permission_needed, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok, v -> ActivityCompat.requestPermissions(
                                HomeActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                requestCode
                        )).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationUtils.REQUEST_CHECK_SETTINGS) {
            locationUtils.onActivityResult();
        }
    }

    @Override
    public void onlocationUpdated(Location location) {
        currentLocation = location;
        getForecastData();
    }

    private void showLoader() {
        layout_loading.setVisibility(View.VISIBLE);

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.rotate);
        animator.setTarget(imageView_loading);
        animator.start();
    }

    private void hideLoader() {
        layout_loading.setVisibility(View.GONE);
        imageView_loading.clearAnimation();
    }
}
