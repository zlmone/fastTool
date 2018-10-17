package com.lanmosoft.service.lanmobase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LanDateUtils {
	private static Log log = LogFactory.getLog(LanDateUtils.class);
	public static String currentTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	public static String currentDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		return dateStr;
	}
	
	public static Date getNowDate(){
		return new Date();
	}
	public static Date getBeforeDate_min(int min){
		return org.apache.commons.lang.time.DateUtils.addMinutes(new Date(), min*-1);
	}
	public static Date getBeforeDate_month(Date date,int month){
		return org.apache.commons.lang.time.DateUtils.addMonths(date, month*-1);
	}
	
	public static Date getNext_Day(int day){
		return org.apache.commons.lang.time.DateUtils.addDays(new Date(), day);
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

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

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
}
