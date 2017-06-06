/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.hadoop;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author thaonguyen
 */
public class TimeUtils {

    private static final String TAG = TimeUtils.class.getSimpleName();
    public static final SimpleDateFormat yyyy_MM_dd_HH = new SimpleDateFormat("yyyy-MM-dd-HH");
    public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat yyyyMMddHH = new SimpleDateFormat("yyyy/MM/dd/HH");
    public static final SimpleDateFormat yyyyMMdd_HHmmss = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static ConcurrentHashMap<Integer, NumberFormat> LEADING_DATA = new ConcurrentHashMap<>();

    static {
//        NumberFormat zero_formatter = new DecimalFormat("#0");
//        FLOATING_POINT_DATA.put(0, zero_formatter);
        NumberFormat one_formatter = new DecimalFormat("0");
        LEADING_DATA.put(1, one_formatter);
    }

    public static String toString(Date date, SimpleDateFormat formatter) {
        return formatter.format(date);
    }

    public static String toString(Date date) {
        return yyyy_MM_dd_HH.format(date);
    }

    public static Date toTime(String str, SimpleDateFormat formatter) throws ParseException {
        return formatter.parse(str);
    }

    public static Date toTime(String str) throws ParseException {
        return yyyy_MM_dd_HH.parse(str);
    }

    public static String getHour(Date date) {
        String hour = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            hour = leadingZeroFill(calendar.get(Calendar.HOUR_OF_DAY), 2);
        } catch (Exception ex) {
//            Logger.error(TAG, ex);
        }
        return hour;
    }

    public static String asPath(String time) {
        String path = null;
        try {
            Date date = TimeUtils.toTime(time);
            path = new StringBuilder("day=")
                    .append(TimeUtils.toString(date, TimeUtils.yyyy_MM_dd))
                    .append("/hour=").append(TimeUtils.getHour(date))
                    .toString();
        } catch (Exception ex) {
//            Logger.error(TAG, ex);
        }
        return path;
    }

    public static String leadingZeroFill(long x, int number) {
        String result = null;
        try {
            if (number > 0) {
                NumberFormat formatter = LEADING_DATA.get(number);
                if (null == formatter) {
                    StringBuffer pattern = new StringBuffer();
                    for (int i = 0; i < number; i++) {
                        pattern.append("0");
                    }
                    formatter = new DecimalFormat(pattern.toString());
                }
                result = formatter.format(x);
            } else {
//                Logger.error(TAG, "Can not fill with number of leading less than zero.");
                result = Long.toString(x);
            }
        } catch (Exception ex) {
//            Logger.error(TAG, ex);
        }
        return result;
    }

}
