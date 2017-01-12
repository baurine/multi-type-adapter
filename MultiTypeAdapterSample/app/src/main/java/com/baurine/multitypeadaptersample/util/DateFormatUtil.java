package com.baurine.multitypeadaptersample.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static String formatTime(Date date) {
        return sdf.format(date);
    }

}
