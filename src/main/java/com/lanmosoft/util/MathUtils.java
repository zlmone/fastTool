/**
 * 
 */
package com.lanmosoft.util;

/**
 * @author lanmosoft
 *
 */
public class MathUtils {

	
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 最简单最基本的方法 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */  
	public static Integer[] randomCommon(int min, int max, int n){  
	   if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    Integer[] result = new Integer[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	        	if (result[j] == null) {
	        		break;
	        	}
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
}
}
