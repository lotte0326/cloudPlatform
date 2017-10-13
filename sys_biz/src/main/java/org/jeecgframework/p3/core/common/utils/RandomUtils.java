//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.jeecgframework.p3.core.common.utils.DateUtil;

public class RandomUtils {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";
    public static final String guestsSignKey = "GUESTSDTOB7AAB4A8F1DB1C7B0EDB1FF6F92E97DC";

    public RandomUtils() {
    }

    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateNum(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append('0');
        }

        return sb.toString();
    }

    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if(fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
    }

    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if(fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
    }

    public static synchronized String generateMarketingActivityCode() {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        String datestr = DateUtil.get(8, date);
        sb.append(datestr);
        String randomstr = generateNum(6);
        sb.append(randomstr);
        return sb.toString();
    }

    public static synchronized String generateNo(String prix) {
        StringBuffer sb = new StringBuffer();
        sb.append(prix);
        Date date = new Date();
        String datestr = DateUtil.get(8, date);
        sb.append(datestr);
        String randomstr = generateNum(6);
        sb.append(randomstr);
        return sb.toString();
    }

    public static synchronized String generateID() {
        String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        return randomSeed;
    }
}
