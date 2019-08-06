package com.sahil.weathertask.common.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    @SuppressLint("SimpleDateFormat")
    public static String epochToWeekDay(long date_epoch) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(new Date(date_epoch * 1000));
    }
}
