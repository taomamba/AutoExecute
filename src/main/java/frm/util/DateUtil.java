package frm.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class DateUtil {

	public static final long SECOND = 1000;

	public static final long MINUTE = SECOND * 60;

	public static final long HOUR = MINUTE * 60;

	public static final long DAY = HOUR * 24;
	
	public static final long WEEK = DAY * 7;

	public static final long YEAR = DAY * 365; // or 366 ???

	public static final long GMT_VIETNAM_TIME_OFFSET = HOUR * 7;

	private static long SERVER_TIME_OFFSET = 0;

	private static DateFormat serialFormat = new SimpleDateFormat("yyyyMMdd");

	private static DateFormat serialFullFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	private static DateFormat ddMMyyyyFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	private static DateFormat yyyyMMddFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private static DateFormat MMddFormat = new SimpleDateFormat(
			"MM-dd");

	private static DateFormat yyyyMMddFormatCN = new SimpleDateFormat(
			"yyyy年MM月dd日");
	
	private static DateFormat MMddFormatCN = new SimpleDateFormat(
			"MM月dd日");

	private static DateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static DateFormat yyyyMMddHHmmssFormatCN = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

	private static DateFormat MMddHHmmFormat = new SimpleDateFormat(
			"MM月dd日 HH时mm分");

	private static DateFormat yyyyMMddHHmmFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	private static DateFormat yyyyMMddHHmmFormatCN = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分");
	
	private static DateFormat yyyyMMFormat = new SimpleDateFormat(
			"yyyyMM");
	
	private static DateFormat yyyyMMddHHmissFormat = new SimpleDateFormat(
	"yyyyMMddHHmmss");
	
	private static DateFormat yyyyFormat = new SimpleDateFormat(
			"yyyy");
	
	private static DateFormat hhmmssFormat = new SimpleDateFormat(
			"HH:mm:ss");
	
	private static DateFormat dateFormat = SimpleDateFormat
			.getDateInstance(SimpleDateFormat.DEFAULT);

	private static DateFormat datetimeFormat = SimpleDateFormat
			.getDateTimeInstance(SimpleDateFormat.DEFAULT,
					SimpleDateFormat.DEFAULT);

	private DateUtil() {
	}

	public static synchronized String getDateDDMMYYYY(Date date) {
		if(null == date) return null;
		return ddMMyyyyFormat.format(date);
	}

	public static synchronized Date getDDMMYYYYDate(String ddMMyyyyDate)
			throws ParseException {
		return ddMMyyyyFormat.parse(ddMMyyyyDate);
	}

	public static synchronized String getDateYYYYMMDD(Date date) {
		if(null == date) return null;
		return yyyyMMddFormat.format(date);
	}

	public static synchronized Date getYYYYMMDDDate(String yyyyMMddDate)
			throws ParseException {
		return yyyyMMddFormat.parse(yyyyMMddDate);
	}
	
	public static synchronized String getDateMMDD(Date date) {
		if(null == date) return null;
		return MMddFormat.format(date);
	}

	public static synchronized Date getMMDDDate(String MMddDate)
			throws ParseException {
		return MMddFormat.parse(MMddDate);
	}

	public static synchronized String getDateYYYYMMDD_CN(Date date) {
		if(null == date) return null;
		return yyyyMMddFormatCN.format(date);
	}

	public static synchronized Date getYYYYMMDD_CN_Date(String yyyyMMddCN_Date)
			throws ParseException {
		return yyyyMMddFormatCN.parse(yyyyMMddCN_Date);
	}
	
	public static synchronized String getDateMMDD_CN(Date date) {
		if(null == date) return null;
		return MMddFormatCN.format(date);
	}

	public static synchronized Date getMMDD_CN_Date(String MMddCN_Date)
			throws ParseException {
		return MMddFormatCN.parse(MMddCN_Date);
	}
	
	public static synchronized String getDateyyyyMMddHHmm(Date date) {
		if(null == date) return null;
		return yyyyMMddHHmmFormat.format(date);
	} 

	public static synchronized Date getyyyyMMddHHmmDate(String yyyyMMddHHmmDate)
			throws ParseException {
		return yyyyMMddHHmmFormat.parse(yyyyMMddHHmmDate);
	}

	public static synchronized String getDateyyyyMMddHHmmss_CN(Date date) {
		if(null == date) return null;
		return yyyyMMddHHmmssFormatCN.format(date);
	}
	
	public static synchronized String getDateyyyyMMddHHmmss(Date date) {
		if(null == date) return null;
		return yyyyMMddHHmmssFormat.format(date);
	}

	public static synchronized Date getyyyyMMddHHmmss_CN_Date(
			String yyyyMMddHHmmssCN_Date) throws ParseException {
		return yyyyMMddHHmmssFormatCN.parse(yyyyMMddHHmmssCN_Date);
	}

	public static synchronized Date getyyyyMMddHHmmssDate(
			String yyyyMMddHHmmss) throws ParseException {
		return yyyyMMddHHmmssFormat.parse(yyyyMMddHHmmss);
	}

	public static synchronized String getDateindex(Date date) {
		if(null == date) return null;
		return yyyyMMddHHmmssFormat.format(date);
	}

	public static synchronized String getDateSearch(Date date) {
		if(null == date) return null;
		return MMddHHmmFormat.format(date);
	}

	public static synchronized String getDateNoSencond(Date date) {
		if(null == date) return null;
		return yyyyMMddHHmmFormat.format(date);
	}

	public static synchronized String formatDate(Date date) {
		if(null == date) return null;
		return dateFormat.format(date);
	}

	public static synchronized String formatDateTime(Date date) {
		if(null == date) return null;
		return datetimeFormat.format(date);
	}

	public static Timestamp getCurrentGMTTimestamp() {
		return new Timestamp(System.currentTimeMillis() + SERVER_TIME_OFFSET);
	}

	public static void updateCurrentGMTTimestamp(Timestamp timeToUpdate) {
		timeToUpdate.setTime(System.currentTimeMillis() + SERVER_TIME_OFFSET);
	}

	public static Date getVietnamDateFromGMTDate(Date date) {
		return new Date(date.getTime() + GMT_VIETNAM_TIME_OFFSET);
	}

	public static Date convertGMTDate(Date gmtDate, int hourOffset) {
		return new Date(gmtDate.getTime() + hourOffset * HOUR);
	}

	public static Timestamp convertGMTTimestamp(Timestamp gmtTimestamp,
			int hourOffset) {
		return new Timestamp(gmtTimestamp.getTime() + hourOffset * HOUR);
	}

	public static synchronized String getSerialDate() {
		GregorianCalendar now = new GregorianCalendar();
		return serialFormat.format(now.getTime());
	}

	public static synchronized String getSerialFullDate() {
		GregorianCalendar now = new GregorianCalendar();
		return serialFullFormat.format(now.getTime());
	}

	public static synchronized Date getNow() {
		GregorianCalendar now = new GregorianCalendar();
		return now.getTime();
	}

	public static synchronized String getNowyyyyMMdd() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddFormat.format(now.getTime());
	}
	
	public static synchronized String getNowMMdd() {
		GregorianCalendar now = new GregorianCalendar();
		return MMddFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddCN() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddFormatCN.format(now.getTime());
	}
	
	public static synchronized String getNowMMddCN() {
		GregorianCalendar now = new GregorianCalendar();
		return MMddFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddHHmm() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmmFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddHHmmCN() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmmFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddHHmmss() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmmssFormat.format(now.getTime());
	}

	public static synchronized String getNowyyyyMMddHHmmssCN() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmmssFormatCN.format(now.getTime());
	}

	public static synchronized String getNowyyyyMM() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMFormat.format(now.getTime());
	}
	
	public static synchronized String getNowyyyy() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyFormat.format(now.getTime());
	}
	
	public static synchronized String getNowyyyyMMddHHmiss() {
		GregorianCalendar now = new GregorianCalendar();
		return yyyyMMddHHmissFormat.format(now.getTime());
	}
	
	public static synchronized String getNowHHmmss() {
		GregorianCalendar now = new GregorianCalendar();
		return hhmmssFormat.format(now.getTime());
	}
	
	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDiffDays(Date nextDate, Date prevDate) {
		if (null == nextDate || null == prevDate) {
			return 0;
		}

		long day = (nextDate.getTime() - prevDate.getTime()) / DAY;
		return day;
	}
	
	/**
	 * 根据入参获取日期，返回Date类型。
	 * 
	 * @param date
	 * @param dw  传入的 时 天 周 月 年
	 * d-天   h-小时 w-周 m-月 y-年
	 * @param num   int可正可负
	 * @return
	 */
	public static synchronized Date getCalcDate(Date date, String dw, int num) {
		Calendar newdate = Calendar.getInstance();
		newdate.setTime(date);
		if("d".equals(dw)){ //天
			newdate.set(Calendar.DAY_OF_YEAR, newdate.get(Calendar.DAY_OF_YEAR) + num);
		} else if("h".equals(dw)){ //时
			newdate.set(Calendar.HOUR_OF_DAY, newdate.get(Calendar.HOUR_OF_DAY) + num);
		} else if("w".equals(dw)){ //周
			newdate.set(Calendar.WEEK_OF_YEAR, newdate.get(Calendar.WEEK_OF_YEAR) + num);
		} else if("y".equals(dw)){ //年
			newdate.set(Calendar.YEAR, newdate.get(Calendar.YEAR) + num);
		} else if("m".equals(dw)){ //月
			newdate.set(Calendar.MONTH, newdate.get(Calendar.MONTH) + num);
		}
		return newdate.getTime();
	}
	
	/**
	 * 从生日计算年龄
	 * year: 年龄超过1年的，计算满几年
	 * monthO: 年龄不满1年的，计算满几个月
	 * day: 年龄不满1月的，计算满几天
	 * @param birthday
	 * @return 返回的Map中的key包含：year, month, day
	 */
	public static Map<String, Integer> getAge(Date birthday) {
	    Map<String, Integer> result = new HashMap<String, Integer>();
	    int yearOfAge = 0;
	    int monthOfAge = 0;
	    int dayOfAge = 0;

	    Calendar cal = Calendar.getInstance();
	    long nowMillis = cal.getTimeInMillis();
	    long birthdayMillis = birthday.getTime();
	    if(nowMillis < birthdayMillis) {
	        result.put("year", yearOfAge);
	        result.put("month", monthOfAge);
	        result.put("day", dayOfAge);
	        return result;
	    }

	    int yearNow = cal.get(Calendar.YEAR);
	    int monthNow = cal.get(Calendar.MONTH) + 1;
	    int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

	    cal.setTime(birthday);
	    int yearBirth = cal.get(Calendar.YEAR);
	    int monthBirth = cal.get(Calendar.MONTH) + 1;
	    int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	    if(yearNow == yearBirth) {
	        monthOfAge = monthNow - monthBirth;
	        if(monthNow == monthBirth) {
	            dayOfAge = dayOfMonthNow - dayOfMonthBirth;
	        } else {
	            if(dayOfMonthNow < dayOfMonthBirth) {
	                monthOfAge--;
	            }
	            if(monthOfAge == 0) {
	                dayOfAge = (int)TimeUnit.MILLISECONDS.toDays(nowMillis - birthdayMillis);
	            }
	        }
	    } else {
	        yearOfAge = yearNow - yearBirth;
	        if(monthNow < monthBirth) {
	            yearOfAge--;
	            if(yearOfAge == 0) {
	                monthOfAge = 12 - monthBirth + monthNow;
	                if(dayOfMonthNow >= dayOfMonthBirth) {
	                    monthOfAge++;
	                }
	            }
	        }else if(monthNow == monthBirth) {
	            if(dayOfMonthNow < dayOfMonthBirth) {
	                yearOfAge--;
	                if(yearOfAge == 0) {
	                    monthOfAge = 11;
	                }
	            }
	        }
	    }

	    result.put("year", yearOfAge);
	    result.put("month", monthOfAge);
	    result.put("day", dayOfAge);
	    return result;
	}
}