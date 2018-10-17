package com.lanmosoft.service.lanmobase;

import java.util.Random;

/**
 *  @author:    su.zhang
 *  @function:  产生随机数字、随机字母、随机数字+字母。
 */
public class RandomUtil {
     
    /**
     * 随机产生几位数字：例：maxLength=3,则结果可能是 012
     */
    public static final int produceNumber(int maxLength){
        Random random = new Random();
        return random.nextInt(maxLength);
    }
     
     
    /**
     * 随机产生区间数字：例：minNumber=1,maxNumber=2,则结果可能是 1、2,包括首尾。
     */
    public static int produceRegionNumber(int minNumber,int maxNumber){
        return minNumber + produceNumber(maxNumber);
    }
 
    /**
     * 随机产生几位字符串：例：maxLength=3,则结果可能是 aAz
     * @param maxLength 传入数必须是正数。
     */
    public static String produceString(int maxLength){
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return doProduce(maxLength, source);
    }
     
    /**
     * 随机产生随机数字+字母：例：maxLength=3,则结果可能是 1Az
     * @param maxLength 传入数必须是正数。
     */
    public static String produceStringAndNumber(int maxLength){
        String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return doProduce(maxLength, source);
    }
 
    /**
     * 自定义随机产生结果
     */
    public static String produceResultByCustom(String customString,int maxLength){
        return doProduce(maxLength, customString);
    }
     
    /**
     * 生产结果
     */
    private static String doProduce(int maxLength, String source) {
        StringBuffer sb = new StringBuffer(100);
        for (int i = 0; i < maxLength; i++) {
            final int number =  produceNumber(source.length());
            sb.append(source.charAt(number));
        }
        return sb.toString();
    }
     
     
     
    public static void main(String[] args) {
        System.out.println(RandomUtil.produceNumber(3));
        System.out.println(RandomUtil.produceRegionNumber(1,2));
        System.out.println(RandomUtil.produceString(3));
        System.out.println(RandomUtil.produceStringAndNumber(3));
        System.out.println(RandomUtil.produceResultByCustom("xushengxiang.", 8));
 
    }
}