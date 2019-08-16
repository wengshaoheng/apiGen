package mars.apigen.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * a date utilization class
 * @author Shaoheng.Weng
 *
 */
public abstract class DateUtils {
	
	/**
	 * convert the given date to a string with format 'yyyy-MM-dd'
	 * @param date
	 * @return date string 
	 */
	public static String toDateStr(Date date) {
		return DateFormater.DATE.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyy/MM/dd'
	 * @param date
	 * @return date string 
	 */
	public static String toDateStr1(Date date) {
		return DateFormater.DATE1.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyyMMdd'
	 * @param date
	 * @return date string 
	 */
	public static String toDateStr2(Date date) {
		return DateFormater.DATE2.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyy-MM-dd HH:mm:ss'
	 * @param date
	 * @return date string 
	 */
	public static String toDateTimeStr(Date date) {
		return DateFormater.DATETIME.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyy/MM/dd HH:mm:ss'
	 * @param date
	 * @return date string 
	 */
	public static String toDateTimeStr1(Date date) {
		return DateFormater.DATETIME1.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyyMMddHHmmss'
	 * @param date
	 * @return date string 
	 */
	public static String toDateTimeStr2(Date date) {
		return DateFormater.DATETIME2.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyy-MM-dd HH:mm:ss.SSS'
	 * @param date
	 * @return date string 
	 */
	public static String toTimstampStr(Date date) {
		return DateFormater.TIMESTAMP.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyy/MM/dd HH:mm:ss.SSS'
	 * @param date
	 * @return date string 
	 */
	public static String toTimstampStr1(Date date) {
		return DateFormater.TIMESTAMP1.format(date);
	}
	
	/**
	 * convert the given date to a string with format 'yyyyMMddHHmmss.SSS'
	 * @param date
	 * @return date string 
	 */
	public static String toTimstampStr2(Date date) {
		return DateFormater.TIMESTAMP2.format(date);
	}
	
	/**
	 * convert the given date string to an date object
	 * @param dateStr
	 * @return java.util.Date
	 */
	public static Date toDate(String dateStr) {
		if (StringUtils.isBlank(dateStr)) 
			return null;
		
		return determineFormat(dateStr).parse(dateStr);
	}
	
	private static Pattern p1 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s*$");
	private static Pattern p2 = Pattern.compile("^\\d{4}/\\d{2}/\\d{2}\\s*$");
	private static Pattern p3 = Pattern.compile("^\\d{8}\\s*$");
	private static Pattern p4 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\s*$");
	private static Pattern p5 = Pattern.compile("^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\s*$");
	private static Pattern p6 = Pattern.compile("^\\d{14}\\s*$");
	private static Pattern p7 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\s*$");
	private static Pattern p8 = Pattern.compile("^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\s*$");
	private static Pattern p9 = Pattern.compile("^\\d{14}\\.\\d{3}\\s*$");
	/**
	 * determine date format for the given date string
	 * @param dateStr
	 * @return <code>DateFormater</code>
	 */
	static DateFormater determineFormat(String dateStr) {
		if (p1.matcher(dateStr).matches()) 
			return DateFormater.DATE;
		
		if (p2.matcher(dateStr).matches()) 
			return DateFormater.DATE1;
		
		if (p3.matcher(dateStr).matches()) 
			return DateFormater.DATE2;
		
		if (p4.matcher(dateStr).matches()) 
			return DateFormater.DATETIME;
		
		if (p5.matcher(dateStr).matches()) 
			return DateFormater.DATETIME1;
		
		if (p6.matcher(dateStr).matches()) 
			return DateFormater.DATETIME2;
		
		if (p7.matcher(dateStr).matches()) 
			return DateFormater.TIMESTAMP;
		
		if (p8.matcher(dateStr).matches()) 
			return DateFormater.TIMESTAMP1;
		
		if (p9.matcher(dateStr).matches()) 
			return DateFormater.TIMESTAMP2;
		
		throw new IllegalArgumentException("invalid date string:" + dateStr);
	}
	
	/**
	 * date format enumerator
	 *
	 */
	public static enum DateFormater {
		DATE("yyyy-MM-dd"),
		DATE1("yyyy/MM/dd"),
		DATE2("yyyyMMdd"),
		DATETIME("yyyy-MM-dd HH:mm:ss"),
		DATETIME1("yyyy/MM/dd HH:mm:ss"),
		DATETIME2("yyyyMMddHHmmss"),
		TIMESTAMP("yyyy-MM-dd HH:mm:ss.SSS"),
		TIMESTAMP1("yyyy/MM/dd HH:mm:ss.SSS"),
		TIMESTAMP2("yyyyMMddHHmmss.SSS");
		
		private ThreadLocal<SimpleDateFormat> threadLocalFormat = null;
		private DateFormater(final String formatStr) {
			threadLocalFormat = new ThreadLocal<SimpleDateFormat>() {
				@Override
				protected SimpleDateFormat initialValue() {
					return new SimpleDateFormat(formatStr, Locale.US);
				}
			};
		}
		
		private SimpleDateFormat get() {
			return threadLocalFormat.get();
		}
		
		public String format(Date date) {
			if (date == null)
				return "";
			
			return get().format(date);
		}
		
		public Date parse(String dateStr) {
			if (StringUtils.isBlank(dateStr)) 
				return null;
			
			try {
				return get().parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public static void main(String[] args) {
		String dateStr = "20180101223344.555";
		Date d = toDate(dateStr);
		System.out.println(d);
	}
}
