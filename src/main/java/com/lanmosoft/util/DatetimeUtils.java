/**
 * 
 */
package com.lanmosoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author lanmosoft
 *
 */
public class DatetimeUtils {
	public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATR2 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat DATR2s = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE3 = new SimpleDateFormat("dd/MM/yy");
	public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	
	
	public static final SimpleDateFormat DATE4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 日期相加的天数
	 * @param dateStr
	 * @param day
	 * @return
	 */
	public static String getTipDate(String dateStr, int day){
		try{
			Date myDate = DATE.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(myDate);
		    c.add(Calendar.DAY_OF_MONTH, day);
		    myDate = c.getTime();
		    return DATE.format(myDate);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public static Date getTipDate(Date date, int day){
		try{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
		    c.add(Calendar.DAY_OF_MONTH, day);
		    c.getTime();
		    return c.getTime();
		}catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public static String getYear(){
		String str = YEAR.format(new Date());
        return str;
	}
	
	public static String getYear(Date date){
		String str = "";
		if(date != null){
			str = YEAR.format(date);
		}
		return str;
	}
	/**
	 * date 转成Date类型 
	 */
	public static Date dateToDate(Date d){
		Date date = null;
		if (d != null){
			String dateStr = DATE.format(d);
			try {
				date = DATE.parse(dateStr);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
		return date;
	}
	
	/**
	 * date 转成Date类型 
	 */
	public static Date dateToDate4(Date d){
		Date date = null;
		if (d != null){
			String dateStr = DATE4.format(d);
			try {
				date = DATE4.parse(dateStr);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
		return date;
	}
	/**
	 * String 转成Date类型
	 */
	public static Date stringToDate(String dateStr){
		Date date = null;
		try {
			date = DATE.parse(dateStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return date;
	}
	
	/**
	 * String 转成Date类型
	 */
	public static Date stringToDate(String dateStr,int month){
		Date date = null;
		try {
			date = DATE.parse(dateStr);
			date.setMonth(date.getMonth()+month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return date;
	}
	/**
	 * String 转成Date类型
	 */
	public static Date stringToDate3(String dateStr){
		Date date = null;
		try {
			if (StringUtils.isNotEmpty(dateStr)){
				date = DATE3.parse(dateStr);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return date;
	}
	/**
	 * String 转成Date类型
	 */
	public static Date string2ToDate(String dateStr){
		Date date = null;
		try {
			date = DATR2.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return date;
	}
	/**
	 * String 转成Date类型
	 */
	public static Date string4ToDate(String dateStr){
		Date date = null;
		try {
			if (StringUtils.isNotEmpty(dateStr)&&!StringUtils.equals("null", dateStr)){
				date = DATE4.parse(dateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return date;
	}
	
	
	/**
	 * Date 转成String类型
	 */
	public static String dateToString(Date date){
		String dateStr = DATE.format(date);
		return dateStr;
	}
	
	public static String stringtoString2(String value){
		String dateStr = "";
		try {
			Date date = DATE3.parse(value);
			dateStr = DATE.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;		
	}
	
	public static String stringtoString3(String value){
		String dateStr = "";
		try {
			Date date = DATE3.parse(value);
			dateStr = DATE.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;		
	}
	
	public static String stringtoString5(String value){
		String dateStr = "";
		try {
			Date date = DATE4.parse(value);
			dateStr = DATE.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;		
	}
	
	public static String stringtoString4(Date date){
		if(date==null){
			return null;
		}
		String dateStr = DATE4.format(date);
		return dateStr;	
	}
	
	public static String stringtoString2(Date date){
		if(date==null){
			return null;
		}
		String dateStr = DATR2.format(date);
		return dateStr;	
	}
	public static String stringtoString2s(Date date){
		if(date==null){
			return null;
		}
		String dateStr = DATR2s.format(date);
		return dateStr;	
	}
	public static String format(Date d,String format){
		if(d==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
	
	public static String stringtoString(String value){
		String newValue = "";
		if (StringUtils.isNotEmpty(value.trim())){
			StringBuffer sb = new StringBuffer();
			String[] newString = value.split("\\/");
			String s1 = newString[0];
			String s2 = newString[1];
			String s3 = newString[2];
			if (s1.length()==1){
				s1 = "0"+s1;
			}
			newValue = sb.append(s2).append("/").append(s1).append("/").append(s3).toString();
		}
		return newValue;
	}	
}

