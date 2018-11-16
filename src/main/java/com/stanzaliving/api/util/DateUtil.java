package com.stanzaliving.api.util;

import java.text.SimpleDateFormat;
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

}
