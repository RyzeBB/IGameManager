package com.igame.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static String DAY_FORMAT_STRING = "yyyy-MM-dd";
	public static String DAY_HOUR_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 “分钟：秒”形式
	 */
	public static long dateMinDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return min;
	}

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 “分钟：秒”形式
	 */
	public static long dateSecDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return s;
	}

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 小时
	 */

	public static long dateHourDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		// long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return hour;
	}

	public static long dateDayDifference(long a, long b) {
		long between = (a - b) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		return day;

	}

	/**
	 * 按时间形式格式化指定的毫秒数
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDate(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		return ca.getTime();
	}

	public static Date getDate() {
		return new Date();
	}

	public static long getCurrDateTimeInMillis() {
		Calendar ca = Calendar.getInstance();
		return ca.getTimeInMillis();
	}

	/**
	 * 
	 * @return 返回当前时间的UNIX形式
	 */
	public static long getTimeStamp() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTimeInMillis() / 1000;
	}

	/**
	 * UNIX 时间转换为日期
	 * 
	 * @param timestampString
	 * @return
	 */
	public static Date TimeStamp2Date(long timestampString) {
		Long timestamp = timestampString * 1000;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(timestamp);
		return ca.getTime();
	}

	/**
	 * 比较两个时间是否为同一天
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareToDay(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		int aYear = ca.get(Calendar.YEAR);
		int aDay = ca.get(Calendar.DAY_OF_YEAR);
		int bYear = cb.get(Calendar.YEAR);
		int bDay = cb.get(Calendar.DAY_OF_YEAR);
		if (aYear == bYear && aDay == bDay) {
			return true;
		}
		return false;
	}

	/**
	 * 今天0点
	 * 
	 * @return
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	public static int sub(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		int aYear = ca.get(Calendar.YEAR);
		int aDay = ca.get(Calendar.DAY_OF_YEAR);
		int bYear = cb.get(Calendar.YEAR);
		int bDay = cb.get(Calendar.DAY_OF_YEAR);
		return aDay - bDay + (aYear - bYear) * 365;
	}

	public static Date add(Date date, int amount, int field) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, amount);
		return ca.getTime();
	}

	public static long getTimeMillis(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.getTimeInMillis();
	}

	public static long subMillisSecond(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		return ca.getTimeInMillis() - cb.getTimeInMillis();
	}

	public static long subMin(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		return (ca.getTimeInMillis() - cb.getTimeInMillis()) / 1000 / 60;
	}

	/**
	 * 指定时间和当前时间差（）
	 * 
	 * @param time
	 * @return
	 */
	public static long subCurrSec(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		long t = ca.getTimeInMillis() - time;
		t = t < 0 ? time - ca.getTimeInMillis() : t;
		return t / 1000;
	}

	/**
	 * 指定时间和当前时间差（指定时间在当前时间之后）
	 * 
	 * @param time
	 * @return 秒
	 */
	public static long subCurrSec_A(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		long t = time - ca.getTimeInMillis();
		return t / 1000;
	}

	public static String formatDate() {
		return formatDate(new Date(), DAY_HOUR_FORMAT_STRING);
	}

	public static String formatDate(Date date) {
		return formatDate(date, DAY_HOUR_FORMAT_STRING);
	}

	public static String formatDate(String format) {
		return formatDate(new Date(), format);
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateformat1 = new SimpleDateFormat(format);
		return dateformat1.format(date);
	}

	public static Date str2Date(String date) throws ParseException {

		return str2Date(date, DAY_HOUR_FORMAT_STRING);

	}

	public static Date str2Date(String date, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	/**
	 * @return 今天指定时间
	 */
	@SuppressWarnings("deprecation")
	public static Date getTodayByHMS(String str) {
		Date d = new Date();
		String[] times = str.split("[:]");
		int ch = Integer.parseInt(times[0]);
		int cm = Integer.parseInt(times[1]);
		int cs = Integer.parseInt(times[2]);
		d.setHours(ch);
		d.setMinutes(cm);
		d.setSeconds(cs);
		return d;

	}

	public static Date getTomorrow() {
		Date d = new Date();
		Calendar time = Calendar.getInstance();
		time.setTime(d);
		GregorianCalendar ca = new GregorianCalendar();
		ca.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		ca.add(Calendar.DAY_OF_YEAR, 1);
		return ca.getTime();
	}

	public static String getCurrentDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat(str);
		return format.format(new java.util.Date());
	}

	/**
	 * 返回当前日期的字符串 yyMMdd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyMMdd");
	}

	public static String getCurrentDateForLog() {
		return getCurrentDate("MM-dd-HH-mm-ss");
	}

	public static String getCurrentDateForTestLog() {
		return getCurrentDate("MM-dd   HH:mm:ss   SSS");
	}
}
