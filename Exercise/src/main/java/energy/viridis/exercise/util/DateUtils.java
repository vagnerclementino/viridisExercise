package energy.viridis.exercise.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

	/**
	 * ISO 8601 Complete Date: yyyy-MM-dd.
	 */
	public static final String ISO8601_COMPLETE_DATE = "yyyy-MM-dd";
	public static final String ISO8601_COMPLETE_DATE_TIME = "yyyy-MM-dd HH:mm";
	public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy HH:mm";


	private DateUtils() {
	}

	public static Date stringToDefaultDate(String date){
		return stringToDate(date, DEFAULT_DATE_FORMAT);
	}

	public static Date stringToDate(String date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String dateTimeToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static LocalDate dateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date localTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getCurrentTime() {
		return localTimeToDate(LocalDateTime.now());
	}

}
