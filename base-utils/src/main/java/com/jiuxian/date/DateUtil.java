package com.jiuxian.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public final static String DEFAULT_DATE = "yyyy-MM-dd";
    public final static String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static DateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String format(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            date = getDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getNextDay(Date date, int next) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, next);
        return calendar.getTime();
    }

    public static Date getNextMonth(Date date, int next) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, next);
        return calendar.getTime();
    }

    public static int getMonthSpace(Date date1, Date date2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
        return month + result;
    }

    public static void main(String[] args) {
        int monthSpace = getMonthSpace(parseDate("2018-11-1", "yyyy-MM-dd"),
                parseDate("2019-04-01", "yyyy-MM-dd"));
        System.out.println(monthSpace);
    }
}
