package com.vcare.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date returnStringInDateFormat(String stringDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(stringDate);
			return date;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Exception while converting " + stringDate + " in Dateutil.java");
			return null;
		}
	}

	public static Date convertStringToDate(String stringDate, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date;
		try {
			date = simpleDateFormat.parse(stringDate);
			return date;
		} catch (Exception e) {
			System.out.println(e);
			System.out
					.println("Exception while converting " + stringDate + " to format " + format + " in Dateutil.java");
			return null;
		}
	}

	public static Date customiseDateTime(Date date, int hour, int min, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getFormatedCleanDate(Date date, String format) {
		if (format == null || format.equals(""))
			format = "yyyy/MM/dd";
		DateFormat dateFormat = new SimpleDateFormat(format);
		String dateString = dateFormat.format(date);
		Date formatedDate = date;
		try {
			formatedDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatedDate;
	}

	public static Date addDaysToDate(Date d1, long days) {
		long ltime = d1.getTime() + days * 24 * 60 * 60 * 1000;
		Date newdate = new Date(ltime);
		return newdate;
	}

	public static String dateToStringCustomFormat(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

}
