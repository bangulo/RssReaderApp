package com.example.borjaangulo.rssreader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by borja.angulo on 21/06/2017.
 */

public class DateUtils {

    public static long getTimeInMillis(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        Date dateFormatted = null;
        try {
            dateFormatted = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormatted.getTime();

    }
}
