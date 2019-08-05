package com.sahil.weathertask.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;

public class LocationUtils {

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000L;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2L;
    private static final String TAG = LocationUtils.class.getSimpleName();
    public static final int REQUEST_CHECK_SETTINGS = 101;

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;

    private LocationUpdatesCallback locationUpdatesCallback;

    public interface LocationUpdatesCallback {
        void onlocationUpdated(Location location);
    }

    private Context context;

    public LocationUtils(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);

        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        startLocationUpdates();

        mFusedLocationClient.requestLocationUpdates(
                locationRequest, mLocationCallback, Looper.myLooper()
        );
    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    if (locationUpdatesCallback != null) {
                        locationUpdatesCallback.onlocationUpdated(locationResult.getLastLocation());
                        stopLocationUpdates();
                    }
                }
            }
        };
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(locationSettingsResponse ->
                        mFusedLocationClient.requestLocationUpdates(
                                locationRequest, mLocationCallback, Looper.myLooper()
                        ))
                .addOnFailureListener(e -> {
                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                    "location settings ");
                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the
                                // result in onActivityResult().
                                ResolvableApiException rae = (ResolvableApiException) e;
                                rae.startResolutionForResult((Activity) context, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException sie) {
                                Log.i(TAG, "PendingIntent unable to execute request.");
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            String errorMessage = "Location settings are inadequate, and cannot be " +
                                    "fixed here. Fix in Settings.";
                            Log.e(TAG, errorMessage);
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void createLocationRequest() {
        if (locationRequest == null) {
            locationRequest = new LocationRequest();
            locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    public void setLocationUpdatesCallback(LocationUpdatesCallback locationUpdatesCallback) {
        this.locationUpdatesCallback = locationUpdatesCallback;
    }

    public void onActivityResult() {
        startLocationUpdates();
    }
}
