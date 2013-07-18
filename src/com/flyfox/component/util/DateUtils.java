package com.flyfox.component.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * 时间处理类
 * 
 * @author flyfox
 * @since Jul 14, 2013
 *
 */
public class DateUtils {
	/**
	 * 默认的日期格式 .
	 */
	public static final String DEFAULT_REGEX = "yyyy-MM-dd";
	/**
	 * 默认的日期格式 .
	 */
	public static final String DEFAULT_REGEX_YYYYMMDD = "yyyyMMdd";
	/**
	 * 默认的日期格式 .
	 */
	public static final String DEFAULT_REGEX_MMDD = "MMdd";
	/**
	 * 默认的日期格式 .
	 */
	public static final String DEFAULT_REGEX_YYYY = "yyyy";
	/**
	 * 默认的日期格式 .
	 */
	public static final String DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * TIME_FIELD : 年
	 */
	public static final int YEAR = Calendar.YEAR;
	/**
	 * TIME_FIELD : 月
	 */
	public static final int MONTH = Calendar.MONTH;
	/**
	 * TIME_FIELD : 日
	 */
	public static final int DATE = Calendar.DATE;
	/**
	 * TIME_FIELD : 小时
	 */
	public static final int HOUR = Calendar.HOUR;
	/**
	 * TIME_FIELD : 分钟
	 */
	public static final int MINUTE = Calendar.MINUTE;
	/**
	 * TIME_FIELD : 秒
	 */
	public static final int SECOND = Calendar.SECOND;
	/**
	 * TIME_FIELD : 毫秒
	 */
	public static final int MILLISECOND = Calendar.MILLISECOND;
	/**
	 * 默认的DateFormat 实例
	 */
	private static final EPNDateFormat DEFAULT_FORMAT = new EPNDateFormat(DEFAULT_REGEX);
	/**
	 * 默认的DateFormat 实例
	 */
	private static final EPNDateFormat DEFAULT_FORMAT_YYYY = new EPNDateFormat(DEFAULT_REGEX_YYYY);
	/**
	 * 默认的DateFormat 实例
	 */
	private static final EPNDateFormat DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS = new EPNDateFormat(DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
	/**
	 * 默认的DateFormat 实例
	 */
	private static final EPNDateFormat DEFAULT_FORMAT_MMDD = new EPNDateFormat(DEFAULT_REGEX_MMDD);
	/**
	 * 默认的DateFormat 实例
	 */
	private static final EPNDateFormat DEFAULT_FORMAT_YYYYMMDD = new EPNDateFormat(DEFAULT_REGEX_YYYYMMDD);
	private static Map<String, EPNDateFormat> formatMap = new HashMap<String, EPNDateFormat>();
	static {
		formatMap.put(DEFAULT_REGEX, DEFAULT_FORMAT);
		formatMap.put(DEFAULT_REGEX_YYYY, DEFAULT_FORMAT_YYYY);
		formatMap.put(DEFAULT_REGEX_MMDD, DEFAULT_FORMAT_MMDD);
		formatMap.put(DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS, DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS);
		formatMap.put(DEFAULT_REGEX_YYYYMMDD, DEFAULT_FORMAT_YYYYMMDD);
	}

	private DateUtils() {

	}

	/**
	 * 时间对象格式化成String .
	 * 
	 * @param date
	 *            待解析时间对象
	 * @return 传入时间的默认格式的字符串形式 ,直接解析 ,遇到任何错误返回null
	 */
	public static String formatNull(java.util.Date date) {
		return formatWith(date, null);
	}

	/**
	 * 时间对象格式化成String .
	 * 
	 * @param date
	 *            待解析时间对象
	 * @return 传入时间的默认格式的字符串形式 ,直接解析 ,遇到任何错误返回""
	 */
	public static String formatMaskNull(java.util.Date date, String regex) {
		return formatWith(date, regex, "");
	}

	/**
	 * 时间对象格式化成String .
	 * 
	 * @param date
	 *            待解析时间对象
	 * @param replaceWith
	 *            解析遇到错误时需要返回的默认值
	 * @return 传入时间的默认格式的字符串形式 ,直接解析 ,遇到任何错误返回replaceWith
	 */
	public static String formatWith(java.util.Date date, String defaultValue) {
		try {
			return DEFAULT_FORMAT.format(date);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 时间对象格式化成String .
	 * 
	 * @param date
	 *            待解析时间对象
	 * @param replaceWith
	 *            解析遇到错误时需要返回的默认值
	 * @return 传入时间的默认格式的字符串形式 ,直接解析 ,遇到任何错误返回replaceWith
	 */
	public static String formatWith(java.util.Date date, String regex, String defaultValue) {
		try {
			if (date == null)
				return "";
			else
				return format(date, regex);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 时间对象格式化成String ,等同于java.text.DateFormat.format();
	 * 
	 * @param date
	 *            需要格式化的时间对象
	 * @return 转化结果
	 */
	public static String format(java.util.Date date) {
		return DEFAULT_FORMAT.format(date);
	}

	/**
	 * 时间对象格式化成String ,等同于java.text.SimpleDateFormat.format();
	 * 
	 * @param date
	 *            需要格式化的时间对象
	 * @param regex
	 *            定义格式的字符串
	 * @return 转化结果
	 */
	public static String format(java.util.Date date, String regex) {
		EPNDateFormat fmt = getDateFormat(regex);
		return fmt.format(date);
	}

	public static EPNDateFormat getDateFormat(String regex) {
		EPNDateFormat fmt = formatMap.get(regex);
		if (fmt == null) {
			fmt = new EPNDateFormat(regex);
			formatMap.put(regex, fmt);
		}
		return fmt;
	}

	/**
	 * 解析字符串成时间 ,遇到错误返回null不抛异常
	 * 
	 * @param source
	 * @return 解析结果
	 */
	public static java.util.Date parse(String source) {
		try {
			return DEFAULT_FORMAT.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析字符串成时间 ,遇到错误返回null不抛异常
	 * 
	 * @param source
	 * @param 格式表达式
	 * @return 解析结果
	 */
	public static java.util.Date parse(String source, String regex) {
		try {
			EPNDateFormat fmt = getDateFormat(regex);
			return fmt.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得当前时间的Timestamp对象 ;
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 取得time时间的Timestamp对象 ;
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getTimestamp(String time, String pattern) {
		try {
			return getTimestampEx(time, pattern);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析字符串成时间 ,遇到错误抛异常
	 * 
	 * @param source
	 * @param 格式表达式
	 * @return 解析结果
	 * @throws ParseException
	 */
	public static java.util.Date parseEx(String source, String regex) throws ParseException {
		EPNDateFormat fmt = getDateFormat(regex);
		return fmt.parse(source);
	}

	/**
	 * 取得time时间的Timestamp对象 ;
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Timestamp getTimestampEx(String time, String pattern) throws ParseException {
		return new java.sql.Timestamp(parseEx(time, pattern).getTime());
	}

	/**
	 * author Lcy 日期加一天
	 */
	private static SimpleDateFormat sdFormat = null;

	/**
	 * author Lcy 日期加一天
	 */
	public static SimpleDateFormat getsdFormatInstance(String pattern) {
		if (sdFormat == null) {
			sdFormat = new SimpleDateFormat();
		}
		sdFormat.applyPattern(pattern);
		return sdFormat;
	}

	/**
	 * author Lcy 日期加一天
	 */
	public static String Calculate(String timeString, int field, int count, String regex) {
		Calendar calendar = getCalendar(timeString);
		calendar.add(field, count);
		return getsdFormatInstance(regex).format(calendar.getTime());
	}

	public static String Calculate(int ifieldeld, int count) {
		Calendar calendar = getCalendar(getsdFormatInstance("yyyyMMdd").format(Calendar.getInstance().getTime()));
		calendar.add(ifieldeld, count);
		return getsdFormatInstance("yyyy年MM月dd日").format(calendar.getTime());
	}

	/**
	 * author Lcy 日期加一天
	 */
	public static Calendar getCalendar(String timeString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.parse(timeString));
		return calendar;
	}

	/**
	 * 尝试解析时间字符串 ,if failed return null;
	 * 
	 * @author wangp
	 * @since 2008.12.20
	 * @param time
	 * @return
	 */
	public static Timestamp parseTimestamp(String time) {
		Timestamp stamp = null;
		if (time == null || "".equals(time))
			return null;
		Pattern p3 = Pattern.compile("\\b\\d{2}[.-]\\d{1,2}([.-]\\d{1,2}){0,1}\\b");
		if (p3.matcher(time).matches()) {
			time = (time.charAt(0) == '1' || time.charAt(0) == '0' ? "20" : "19") + time;
		}

		stamp = DateUtils.getTimestamp(time, "yyyy-MM-ddHH:mm:ss");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy-MM-dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy.MM.dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy-MM");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy.MM");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy-MM-dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yy-MM-dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy.MM.dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy-MM.dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy.MM-dd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyyMMdd");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyyMM");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy年MM月dd日");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy年MM月");
		if (stamp == null)
			stamp = DateUtils.getTimestamp(time, "yyyy年");
		return stamp;
	}

	/**
	 * 返回指定年份中指定月份的天数
	 * 
	 * @author xiachangsong
	 * @param 年份year
	 * @param 月份month
	 * @return 指定月的总天数
	 */
	public static String getMonthLastDay(int year, int month) {
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return day[1][month] + "";
		} else {
			return day[0][month] + "";
		}
	}

	public static int getAge(Date p_birth) {
		return getAge(p_birth, new Date());
	}

	public static int getAge(Date birth, Date date) {
		int age = 0;
		if (date == null || birth == null)
			return age;
		int year_birth = NumberUtils.parseInt(DEFAULT_FORMAT_YYYY.format(birth));
		int year = NumberUtils.parseInt(DEFAULT_FORMAT_YYYY.format(date));
		int month_birth = NumberUtils.parseInt(DEFAULT_FORMAT_MMDD.format(birth));
		int month = NumberUtils.parseInt(DEFAULT_FORMAT_MMDD.format(birth));
		age = year - year_birth;
		if (month > month_birth)
			age += 1;
		return age;
	}

	/**
	 * 把传入的long时间转成中文
	 * 
	 * @param time
	 * @return
	 */
	public static String parseTime(long time) {
		String str = "";
		long hr = time / (1000 * 60 * 60);
		if (hr > 0) {
			time = time - hr * 1000 * 60 * 60;
			str = hr + "小时";
		}
		long mm = time / (1000 * 60);
		if (mm > 0 || str.length() > 0) {
			time = time - mm * 1000 * 60;
			str += mm + "分";
		}
		double sec = time / 1000.0;
		str += sec + "秒";
		return str;
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int getMonthMargin(String date1, String date2) {
		int margin;
		try {
			margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
			margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-")) - Integer.parseInt(date1.substring(4, 7)
					.replaceAll("-0", "-")));
			return margin;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * date:年月
	 */
	private static java.text.SimpleDateFormat yearMonthformat = new java.text.SimpleDateFormat("yyyyMM");

	/**
	 * date:年月日
	 */
	private static java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * time:时分秒
	 */
	private static java.text.SimpleDateFormat timeformat = new java.text.SimpleDateFormat("HH-mm-ss");

	/**
	 * datetime年月日 时分秒
	 */
	private static java.text.SimpleDateFormat datetimeformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 将 TimeStamp 对象转化成"yyyy-MM-dd"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toDateString(Timestamp t) {
		if (t == null)
			return "";
		else
			return dateformat.format(t);
	}

	/**
	 * 将 TimeStamp 对象转化成"yyyyMM"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toYearMonthString(Timestamp t) {
		if (t == null)
			return "";
		else
			return yearMonthformat.format(t);
	}

	/**
	 * 将 Date 对象转化成"yyyy-MM-dd"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toDateString(Date t) {
		if (t == null)
			return "";
		else
			return dateformat.format(t);
	}

	/**
	 * 将 TimeStamp 对象转化成"hh-mm-ss"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toTimeString(Timestamp t) {
		if (t == null)
			return "";
		else
			return timeformat.format(t);
	}

	/**
	 * 将 Date 对象转化成"hh-mm-ss"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toTimeString(Date t) {
		if (t == null)
			return "";
		else
			return timeformat.format(t);
	}

	/**
	 * 将 TimeStamp 对象转化为"yyyy-MM-dd hh-mm-ss"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toDateTimeString(Timestamp t) {
		if (t == null)
			return "";
		else
			return datetimeformat.format(t);
	}

	/**
	 * 将 Date 对象转化为"yyyy-MM-dd hh-mm-ss"格式字符串
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param t
	 * @return
	 */
	public static String toDateTimeString(Date t) {
		if (t == null)
			return "";
		else
			return datetimeformat.format(t);
	}

	/**
	 * 将 String 对象转换为 Timestamp 对象 格式为"yyyy-MM-dd" 或 "yyyy-MM-dd hh-mm-ss"
	 * 已经处理为String 为null的情况
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @param s
	 * @return
	 */
	public static Timestamp toTimestamp(String s) {
		if (s == null)
			return null;
		else if (s.length() == 10)
			return Timestamp.valueOf(s + " 00:00:00");
		else if (s.length() == 19)
			return Timestamp.valueOf(s);
		else
			return null;
	}

	/**
	 * 返回系统当前时间的 Timestamp 对象
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Timestamp getCurrentTimestamp() {
		Calendar ca = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		Timestamp ts = new Timestamp(ca.getTimeInMillis());
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		ts.setHours(hour);
		return ts;
		// 原方法:这个获取的是GMT+00:00 与北京时间差8个小时
		// return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获得当前年月日
	 * 
	 * @author 张懿勉
	 * @return
	 */
	public static String getCurrentYearMonthDay() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}

	/**
	 * 获得当前年月
	 * 
	 * @author 张懿勉
	 * @return
	 */
	public static String getCurrentYearMonth() {
		return getCurrentYear() + getCurrentMonth();
	}

	/**
	 * 获得当前年月
	 * 
	 * @author 张懿勉
	 * @return
	 */
	public static String getCurrentYearMonth(Date date) {
		return getCurrentYear(date) + getCurrentMonth(date);
	}

	/**
	 * 返回系统当前时间的 Date 对象
	 * 
	 * @author 张懿勉
	 * @since 2008-11-28
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获得当前日期的年份
	 */
	public static String getCurrentYear() {
		return getCurrentYear(getCurrentDate());
	}

	/**
	 * 返回当前的月份
	 */
	public static String getCurrentMonth() {
		return getCurrentMonth(getCurrentDate());
	}

	/**
	 * 返回当前的月份
	 */
	public static String getCurrentDay() {
		return getCurrentDay(getCurrentDate());
	}

	/**
	 * 根据Date 返回当前的年份
	 * 
	 * @return
	 */
	public static String getCurrentMonth(Date date) {
		return toDateString(date).substring(5, 7);
	}

	/**
	 * 根据Date 返回当前的年份
	 * 
	 * @return
	 */
	public static String getCurrentMonth(Timestamp date) {
		return toDateString(date).substring(5, 7);
	}

	/**
	 * 根据Date 返回当前的年份
	 * 
	 * @return
	 */
	public static String getCurrentYear(Date date) {
		return toDateString(date).substring(0, 4);
	}

	/**
	 * 根据Date 返回当前的年份
	 * 
	 * @return
	 */
	public static String getCurrentYear(Timestamp date) {
		return toDateString(date).substring(0, 4);
	}

	/**
	 * 根据Date 返回当前的日
	 * 
	 * @return
	 */
	public static String getCurrentDay(Date date) {
		return toDateString(date).substring(8, 10);
	}

	/**
	 * 根据Date 返回当前的日
	 * 
	 * @return
	 */
	public static String getCurrentDay(Timestamp date) {
		return toDateString(date).substring(8, 10);
	}

	public static String paraseDate(String yyyyMM) {
		if (yyyyMM == null || "".equals(yyyyMM) || yyyyMM.length() != 6) {
			return yyyyMM;
		}
		return yyyyMM.substring(0, 4) + "年" + yyyyMM.substring(4, 6) + "月";
	}

	public static String nowCurrentYMD() {
		return getCurrentYear() + "年" + getCurrentMonth() + "月" + getCurrentDay() + "日";
	}

	/**
	 * 取指定月的上一个月
	 * 
	 * @param ym_begin
	 * @return
	 */
	public static String getPriorMonth(String ym_begin) {
		String prior_month = "";

		String year = ym_begin.substring(0, 4);
		String month = ym_begin.substring(4);
		if (month.equals("01")) {
			year = Integer.toString(Integer.parseInt(year) - 1);
			month = "12";
		} else {
			month = Integer.toString(Integer.parseInt(month) - 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
		}
		prior_month = year + month;
		return prior_month;
	}

	/**
	 * 取指定月的下一个月
	 * 
	 * @param ym_begin
	 * @return
	 */
	public static String getNextMonth(String ym_begin) {
		String next_month = "";
		if (StrUtils.isEmpty(ym_begin))
			return next_month;
		String year = ym_begin.substring(0, 4);
		String month = ym_begin.substring(4);
		if (month.equals("12")) {
			year = Integer.toString(Integer.parseInt(year) + 1);
			month = "01";
		} else {
			month = Integer.toString(Integer.parseInt(month) + 1);
			if (month.length() == 1) {
				month = "0" + month;
			}
		}
		next_month = year + month;
		return next_month;
	}

	/**
	 * 取指定月的季度的第一个月
	 * 
	 * @param ym_begin
	 * @return
	 */
	public static String getFirstQuarterMonth(String ym_begin) {
		String next_month = "";

		String year = ym_begin.substring(0, 4);
		String month = ym_begin.substring(4);
		int monthi = Integer.parseInt(month);
		if (monthi <= 3) {
			monthi = 1;
		} else if (monthi <= 6) {
			monthi = 4;
		} else if (monthi <= 9) {
			monthi = 7;
		} else {
			monthi = 10;
		}
		month = Integer.toString(monthi);
		if (month.length() == 1) {
			month = "0" + month;
		}
		next_month = year + month;
		return next_month;
	}

	/**
	 * 把201009转换成2010年09月
	 * 
	 * @param dateString
	 * @return
	 */
	public static String chineseYearMonth(String dateString) {
		if (StrUtils.isEmpty(dateString) || dateString.length() != 6) {
			return dateString;
		}
		return dateString.substring(0, 4) + "年" + dateString.substring(4, 6) + "月";
	}

	/**
	 * 把20100912转换成2010年09月12月 如果月份超出21日就增加一个月,主要在社会保险协议书中用到。
	 * 
	 * @param dateString
	 * @return
	 */
	public static String YearMonth(String dateString) {
		if (StrUtils.isEmpty(dateString) || dateString.length() != 10) {
			return dateString;
		}
		String dateString2 = dateString.substring(8, dateString.length());
		if (Integer.valueOf(dateString2) > 21) {
			return dateString.substring(0, 4) + "年" + (Integer.valueOf(dateString.substring(5, 7)) + 1) + "月"
					+ dateString.substring(8, dateString.length()) + "日";
		} else {
			return dateString.substring(0, 4) + "年" + dateString.substring(5, 7) + "月"
					+ dateString.substring(8, dateString.length()) + "日";
		}
	}

	/**
	 * 如果当前日期超出21日就增加一个月,主要在社会保险协议书中用到。
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getNextMonth_by_Day(Timestamp begin_date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df1 = new SimpleDateFormat("yyyyMM");
		DateFormat df2 = new SimpleDateFormat("yyyy年MM月");
		String bdate = df.format(begin_date);
		String rs = "";
		try {
			if (begin_date != null && !StrUtils.isEmpty(bdate) && bdate.length() == 8) {
				if (Integer.parseInt(bdate.substring(6, 8)) > 21) {
					bdate = getNextMonth(df1.format(begin_date));
				} else {
					bdate = df1.format(begin_date);
				}
				rs = df2.format(df1.parse(bdate));
			} else {
				rs = df2.format(new Date());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 输出当前日期格式类型为 2001-01-23
	 * 
	 * @param dateString
	 * @return
	 */
	public static String chineseYearMonthDay() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}

	/**
	 * 输出当前日期格式类型为 200101
	 * 
	 * @param dateString
	 * @return
	 */
	public static String chineseYearMonth() {
		return getCurrentYear() + getCurrentMonth();
	}

	/**
	 * 输出当前日期格式类型为 2001年01月23日
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getYearMonthDay() {
		return getCurrentYear() + "年" + getCurrentMonth() + "月" + getCurrentDay() + "日";
	}

	/**
	 * 将制定格式2001-01-23转化为2001年01月23日
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getYearMonthDay(String date) {
		if (date != null && date.length() == 10) {
			return date.substring(0, 4) + "年" + date.substring(5, 7) + "月" + date.substring(8, 10) + "日";
		} else {
			return "";
		}
	}

	/**
	 * 将制定格式2001-01-23转化为200101
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getYearMonth(String date) {
		if (date != null) {
			return date.substring(0, 4) + date.substring(5, 7);
		} else {
			return "";
		}
	}

	/**
	 * 获取结束月份到开始月份的所有月份，返回List
	 * 
	 * @param month_start
	 * @param month_end
	 * @return
	 */
	public static List<String> getAllMonth(String month_start, String month_end) {
		List<String> month = new ArrayList<String>();
		while (!month_end.equals(month_start) && DateUtils.getMonths(month_start, month_end) >= 1) {
			month.add(month_start);
			month_start = DateUtils.getNextMonth(month_start);
		}
		if (month_start.equals(month_end)) {
			month.add(month_start);
		}
		return month;
	}

	/**
	 * 求两日期相隔月份数，日期格式为201101
	 * 
	 * @param month_start
	 * @param month_end
	 * @return
	 */
	public static int getMonths(String month_start, String month_end) {
		if (!StrUtils.isEmpty(month_start) && !StrUtils.isEmpty(month_end)) {
			return ((Integer.parseInt(month_end.substring(0, 4)) - Integer.parseInt(month_start.substring(0, 4))) * 12 + (Integer
					.parseInt(month_end.substring(4, 6)) - Integer.parseInt(month_start.substring(4, 6)))) + 1;
		} else {
			return 0;
		}
	}

	/**
	 * 当前日期增加一天
	 * 
	 * @param dateString
	 * @return
	 */
	public static Timestamp addOneDay(Date now) {
		Date nextDate = new Date(now.getTime() + 24 * 60 * 60 * 1000);
		String strdate = DateUtils.toDateString(nextDate);
		Timestamp date = DateUtils.getTimestamp(strdate, "yyyy-MM-dd");
		return date;
	}

	/**
	 * 取指定月的季度的最后一个月
	 * 
	 * @param ym_begin
	 *            指定月
	 * @param type//=0
	 *            取指定月的季度的最后一个月;=1 如果指定月是季度的最后一个月,取指定月;否则取上个季度的最后一个月
	 * @return
	 */
	public static String getLastQuarterMonth(String ym_begin, int type) {
		String next_month = "";

		String year = ym_begin.substring(0, 4);
		String month = ym_begin.substring(4);
		int monthi = Integer.parseInt(month);
		int quarter_z = monthi / 3;// 季度整数
		int quarter_y = monthi % 3;// 季度余数
		if (quarter_y > 0) {
			if (type == 0) {
				monthi = 3 * (quarter_z + 1);
				if (monthi > 12) {
					year = String.valueOf(Integer.parseInt(year) + 1);
					monthi = monthi - 12;
				}
			} else {
				monthi = 3 * quarter_z;
				if (monthi == 0) {
					year = String.valueOf(Integer.parseInt(year) - 1);
					monthi = 12;
				}
			}
		}
		month = Integer.toString(monthi);
		if (month.length() == 1) {
			month = "0" + month;
		}
		next_month = year + month;
		return next_month;
	}
}

class EPNDateFormat {
	private SimpleDateFormat instance;

	EPNDateFormat() {
		instance = new SimpleDateFormat(DateUtils.DEFAULT_REGEX);
		instance.setLenient(false);
	}

	EPNDateFormat(String regex) {
		instance = new SimpleDateFormat(regex);
		instance.setLenient(false);
	}

	synchronized String format(java.util.Date date) {
		if (date == null)
			return "";
		return instance.format(date);
	}

	synchronized java.util.Date parse(String source) throws ParseException {
		return instance.parse(source);
	}
}
