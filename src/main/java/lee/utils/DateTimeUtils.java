package lee.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtils {
	
	/**
	 * 返回每天结束时间
	 * @param date
	 * @return
	 */
	public static Date endTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}
	
	/**
	 * 返回每天结束时间
	 * @param date
	 * @return
	 */
	public static Date endTime(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd mm:ss");
		
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}
	
	/**
	 * 返回每天开始时间
	 * @param date
	 * @return
	 */
	public static Date startTime(Date date) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			return calendar.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date startTime(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Date getYesterdayStartTime() {
		Calendar yesterdayCalendar = Calendar.getInstance();
		yesterdayCalendar.add(Calendar.DATE, -1);
		yesterdayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		yesterdayCalendar.set(Calendar.MINUTE, 0);
		yesterdayCalendar.set(Calendar.SECOND, 0);
		yesterdayCalendar.set(Calendar.MILLISECOND, 0);
		return yesterdayCalendar.getTime();
	}

	/**
	 * 获得7天前开始日期
	 * @return
	 */
	public static Date getWeekBeforeStartTime() {
		Calendar yesterdayCalendar = Calendar.getInstance();
		yesterdayCalendar.add(Calendar.DATE, -7);
		yesterdayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		yesterdayCalendar.set(Calendar.MINUTE, 0);
		yesterdayCalendar.set(Calendar.SECOND, 0);
		yesterdayCalendar.set(Calendar.MILLISECOND, 0);
		return yesterdayCalendar.getTime();
	}
	/**
	 * 获取当前时间（格式自传）
	 * @param dateFormat 要返回的时间格式，例如yyyy/MM/dd HH:mm:ss
	 * @return
	 */
	public static String getDate(String dateFormat){
		Date date = new Date();
		SimpleDateFormat dateF = new SimpleDateFormat(dateFormat);//可以方便地修改日期格式
		String retu = dateF.format(date);
		return retu;
	}
	public static Date getYesterdayEndTime() {
		Calendar yesterdayCalendar = Calendar.getInstance();
		yesterdayCalendar.add(Calendar.DATE, -1);
		yesterdayCalendar.set(Calendar.HOUR_OF_DAY, 23);
		yesterdayCalendar.set(Calendar.MINUTE, 59);
		yesterdayCalendar.set(Calendar.SECOND, 59);
		yesterdayCalendar.set(Calendar.MILLISECOND, 999);
		return yesterdayCalendar.getTime();
	}

	/**
	 * 获得从今日起-past的日期
	 * @param past
	 * @return
	 */
	public static Date getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		calendar.set(Calendar.HOUR_OF_DAY,calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		Date today = calendar.getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String result = format.format(today);
		return today;
	}
	/**
	 * 	获取当前时间24小时后的时间
	 */
	public static String getNextDayTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
	}
	public static void main(String[] args) throws Exception{
		DateTimeUtils.startTime(new Date());
		DateTimeUtils.endTime(new Date());
		System.out.println("zzzzzzzzz");
		System.out.println(getPastDate(1));
	}
}
