package com.lanmosoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LanDateUtils {
	private static Log log = LogFactory.getLog(LanDateUtils.class);
	public static String currentTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	
	public static String currentTimeStr(Date d,Integer year,Integer month,Integer day,Integer huors,Integer min){
		if(d==null){
			return null;
		}
		if(year!=null&&year!=0){
			d=org.apache.commons.lang.time.DateUtils.addYears(d, year);
		}
		if(month!=null&&month!=0){
			d=org.apache.commons.lang.time.DateUtils.addMonths(d, month);	
		}
		if(day!=null&&day!=0){
			d=org.apache.commons.lang.time.DateUtils.addDays(d, day);
		}
		if(huors!=null&&huors!=0){
			d=org.apache.commons.lang.time.DateUtils.addHours(d, huors);
		}
		if(min!=null&&min!=0){
			d=org.apache.commons.lang.time.DateUtils.addMinutes(d, min);
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(d);
		return dateStr;
	}
	
	public static Date currentTimeDate(Date d,Integer year,Integer month,Integer day,Integer huors,Integer min){
		if(d==null){
			return null;
		}
		if(year!=null&&year!=0){
			d=org.apache.commons.lang.time.DateUtils.addYears(d, year);
		}
		if(month!=null&&month!=0){
			d=org.apache.commons.lang.time.DateUtils.addMonths(d, month);	
		}
		if(day!=null&&day!=0){
			d=org.apache.commons.lang.time.DateUtils.addDays(d, day);
		}
		if(huors!=null&&huors!=0){
			d=org.apache.commons.lang.time.DateUtils.addHours(d, huors);
		}
		if(min!=null&&min!=0){
			d=org.apache.commons.lang.time.DateUtils.addMinutes(d, min);
		}
		
		return d;
	}
	
	
	/**
	 * currentDateTime: 
	 * 		yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTimeStr_web(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	/**
	 * currentDateTime: 
	 * 		yyyy-MM-dd
	 * @return
	 */
	public static String currentDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	public static String currentDateStr_web(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	
	public static Date getNowDate(){
		return new Date();
	}
	public static Date getBeforeDate_min(int min){
		return org.apache.commons.lang.time.DateUtils.addMinutes(new Date(), min*-1);
	}
	public static Date getNextDate_min(int min){
		return org.apache.commons.lang.time.DateUtils.addMinutes(new Date(), min);
	}
	public static Date getBeforeDate_month(Date date,int month){
		return org.apache.commons.lang.time.DateUtils.addMonths(date, month*-1);
	}
	/**
	 * 
	 * @param a date representation of a date
	 * @param month to be add
	 * @return
	 */
	public static Date getNextDate_month(Date date,int month){
		return org.apache.commons.lang.time.DateUtils.addMonths(date, month);
	}
	
	public static Date getNext_Day(Date date, int day){
		return org.apache.commons.lang.time.DateUtils.addDays(date, day);
	}
	
	public static String format(Date d,String format){
		if(d==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
	
	public static String getFormatDateStr_web(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	 /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            {
        SimpleDateFormat df;
        Date date=null;
        df = new SimpleDateFormat(aMask);

       /* if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }*/

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            
        }

        return (date);
    }
	public static long diffDays(Date date1, Date date2) throws Exception {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
		   java.util.Date begin=dfs.parse(dfs.format(date1));
		   java.util.Date end =dfs.parse(dfs.format(date2));
		   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
		   long day1=between/(24*3600);
		return day1;
	}
	
	public static List<String> getSameDay(String baseDateStr, String endDateStr){
		if(StringUtils.isEmpty(endDateStr)|| StringUtils.isEmpty(baseDateStr)) return null;
		Date baseDate = LanDateUtils.convertStringToDate("yyyy-MM-dd", baseDateStr);
		Date endDate = LanDateUtils.convertStringToDate("yyyy-MM-dd", endDateStr);
		if(baseDate==null ||endDate==null) return null;
		if(endDate.before(baseDate)) return null;
		

		Calendar cal = Calendar.getInstance();
		cal.setTime(baseDate);
		int week = cal.get(Calendar.DAY_OF_WEEK);//获得基准日期的星期数
		int days = (int) ((endDate.getTime()-baseDate.getTime())/(24*60*60*1000));
		List<String> dateLst = new ArrayList<String>();
		
		for(int i=1; i<=days; i++){
			cal.add(Calendar.DATE, 1);
			
			if(cal.get(Calendar.DAY_OF_WEEK)==week){
				System.out.println("date:"+LanDateUtils.format(cal.getTime(), "yyyy-MM-dd"));
				dateLst.add(LanDateUtils.format(cal.getTime(), "yyyy-MM-dd"));
			}
		}
		return dateLst;
	}
	
	/**
     * 获取某月第一天
     * @param calendar the calendar field.
     * @param month the month of date to be added to the field.
     * @return
     */
    public static String getFirstDayOfMonth(Date date, int month) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, month);
    	calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }
    /**
     * 获取某月最后一天
     * @param calendar the calendar field.
     * @param month the month of date to be added to the field.
     * @return
     */
    public static String getLastDayOfMonth(Date date, int month) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, month);
    	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }
    
    /** 
     * 取得某天所在周的第一天 
     *  
     * @param date 
     * @return 
     */ 
    public static Date getFirstDayOfWeek(Date date) { 
        Calendar c = new GregorianCalendar(); 
        c.setFirstDayOfWeek(Calendar.MONDAY); 
        c.setTime(date); 
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); 
        return c.getTime(); 
    } 
   
    /** 
     * 取得某天所在周的最后一天 
     *  
     * @param date 
     * @return 
     */ 
    public static Date getLastDayOfWeek(Date date) { 
        Calendar c = new GregorianCalendar(); 
        c.setFirstDayOfWeek(Calendar.MONDAY); 
        c.setTime(date); 
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); 
        return c.getTime(); 
    }
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfStr(String day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(stringToDate(day));
        if(cal.get(Calendar.DAY_OF_WEEK) - 1==0){
        	return 7;
        }
        return cal.get(Calendar.DAY_OF_WEEK) - 1;    
    }
    
    /**
	 * String 转成Date类型
	 */
	public static Date stringToDate(String dateStr){
		Date date = null;
		try {
			SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
			date = DATE.parse(dateStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return date;
	}
	
	 /** 
     * 取得某天所在周的所有天
     *  
     * @param day 
     * @return 
     */ 
    public static String getAllDayOfWeek(String day) { 
    	String week="";
    	for(int i=0;i<7;i++){
    		Calendar c = new GregorianCalendar(); 
    	    c.setFirstDayOfWeek(Calendar.MONDAY); 
    	    c.setTime(stringToDate(day)); 
    	    c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+i); 
    	    week+="'"+format(c.getTime(), "yyyy-MM-dd")+"',"; 
    	}
    	if(week.length()>1){
    		week=week.substring(0, week.length()-1);
    	}
        return week;
    } 
    
    
    public static int compare_date(String day1, String day2) {
        try {
            Date dt1 = stringToDate(day1);
            Date dt2 = stringToDate(day2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    public static String getNext_Day(String d, int day){
    	Date dt1 = stringToDate(d);
		return format(org.apache.commons.lang.time.DateUtils.addDays(dt1, day), "yyyy-MM-dd");
	}
    
    
    
    
    
    
	public static void main(String[] args) {
//		System.out.println(getNextDate_month(convertStringToDate("yyyy-MM-dd", "2015-12-03"), 1));
//		System.out.println(LanDateUtils.format(getFirstDayOfWeek(convertStringToDate("yyyy-MM-dd", "2015-12-14")), "yyyy-MM-dd"));
//		System.out.println(LanDateUtils.format(getLastDayOfWeek(convertStringToDate("yyyy-MM-dd", "2015-12-14")), "yyyy-MM-dd"));

		System.out.println(compare_date("2016-01-14", "2016-01-11"));
		System.out.println(compare_date("2016-01-14", "2016-01-12"));
		System.out.println(compare_date("2016-01-14", "2016-01-13"));
		System.out.println(compare_date("2016-01-14", "2016-01-14"));
		System.out.println(compare_date("2016-01-14", "2016-01-15"));
		System.out.println(compare_date("2016-01-14", "2016-01-16"));
		System.out.println(compare_date("2016-01-14", "2016-01-17"));
		
        String day="2016-01-01";
		String month=day.substring(5, 6);
		if("0".equals(month)){
			month=day.substring(6, 7);
		}else{
			month=day.substring(5, 7);
		}
		System.out.println(month+"---------"+day);
		String startDate=LanDateUtils.getFirstDayOfMonth(LanDateUtils.stringToDate("2016-01-01"), Integer.valueOf(0));
		String endDate=LanDateUtils.getLastDayOfMonth(LanDateUtils.stringToDate("2016-01-01"), Integer.valueOf(0));
		System.out.println(startDate+"---------"+endDate);
		
//		for(int i=0;i<10;i++){
//			for(int a=0;a<10;a++){
//				for(int b=0;b<10;b++){
//					for(int c=0;c<10;c++){
//						for(int d=0;d<10;d++){
//							for(int e=0;e<10;e++){
//								for(int f=0;f<10;f++){
//									for(int g=0;g<10;g++){
//										for(int h=0;h<10;h++){
//											for(int j=0;j<10;j++){
//												for(int k=0;k<10;k++){
//													for(int l=0;l<10;l++){
//														for(int m=0;m<10;m++){
//															for(int n=0;n<10;n++){
//																for(int o=0;o<10;o++){
//																	System.out.println(i+"-"+a+"-"+b+"-"+d+"-"+e+"-"+f+"-"+g+"-"+h+"-"+j+"-"+k+"-"+l+"-"+m+"-"+n+"-"+o);
//																}
//															}
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
//		System.out.println(getNext_Day("2016-01-14", 1));
//		System.out.println(getFirstDayOfWeek(new Date())+"----"+getLastDayOfWeek(new Date()));
	}
}
