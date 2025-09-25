package com.web.automation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.web.automation.constants.ApplicationConstants;

public class DateAndTimeUtils {

	public static String getCurrentdateAndTime(String format) {
		String dateAndTime = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime localDateTime = LocalDateTime.now();
		dateAndTime = dtf.format(localDateTime);
		System.out.println(dateAndTime);
		return dateAndTime;

	}

	public static Boolean compareCurrentDateInDateRange(String startDate, String endDate, String currentdate) {
		boolean flag = false;
		Date startDt = null, endDt = null, currentDt = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.STANDARD_DATE_AND_TIME_FORMAT);
		try {
			startDt = dateFormat.parse(startDate);
			endDt = dateFormat.parse(endDate);
			currentDt = dateFormat.parse(currentdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (currentDt.after(startDt) && currentDt.before(endDt) || currentDt.equals(startDt)
				|| currentDt.equals(endDt)) {
			flag = true;
			System.out.println(currentdate + " Date lies between start and end date");
		} else {
			System.err.println(currentdate + " Date does not lies between start and end date");
		}
		return flag;

	}

	public static void main(String args[]) {
		// DateAndTimeUtils.getCurrentdateAndTime(ApplicationConstants.STANDARD_DATE_AND_TIME_FORMAT);

		DateAndTimeUtils.compareCurrentDateInDateRange("28-11-2023 11:37:59", "28-11-2023 11:38:04",
				"27-11-2023 11:38:02");
	}

}
