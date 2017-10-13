//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports.verifys;

import java.util.regex.Pattern;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;

public class BaseVerifyHandler {
    private static String NOT_NULL = "不允许为空";
    private static String IS_MOBILE = "不是手机号";
    private static String IS_TEL = "不是电话号码";
    private static String IS_EMAIL = "不是邮箱地址";
    private static String MIN_LENGHT = "小于规定长度";
    private static String MAX_LENGHT = "超过规定长度";
    private static Pattern mobilePattern = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$");
    private static Pattern telPattern = Pattern.compile("^([0][1-9]{2,3}-)?[0-9]{5,10}$");
    private static Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");

    public BaseVerifyHandler() {
    }

    public static ExcelVerifyHanlderResult isEmail(String name, Object val) {
        return !emailPattern.matcher(String.valueOf(val)).matches()?new ExcelVerifyHanlderResult(false, name + IS_EMAIL):new ExcelVerifyHanlderResult(true);
    }

    public static ExcelVerifyHanlderResult isMobile(String name, Object val) {
        return !mobilePattern.matcher(String.valueOf(val)).matches()?new ExcelVerifyHanlderResult(false, name + IS_MOBILE):new ExcelVerifyHanlderResult(true);
    }

    public static ExcelVerifyHanlderResult isTel(String name, Object val) {
        return !telPattern.matcher(String.valueOf(val)).matches()?new ExcelVerifyHanlderResult(false, name + IS_TEL):new ExcelVerifyHanlderResult(true);
    }

    public static ExcelVerifyHanlderResult maxLength(String name, Object val, int maxLength) {
        return notNull(name, val).isSuccess() && String.valueOf(val).length() > maxLength?new ExcelVerifyHanlderResult(false, name + MAX_LENGHT):new ExcelVerifyHanlderResult(true);
    }

    public static ExcelVerifyHanlderResult minLength(String name, Object val, int minLength) {
        return notNull(name, val).isSuccess() && String.valueOf(val).length() < minLength?new ExcelVerifyHanlderResult(false, name + MIN_LENGHT):new ExcelVerifyHanlderResult(true);
    }

    public static ExcelVerifyHanlderResult notNull(String name, Object val) {
        return val != null && !val.toString().equals("")?new ExcelVerifyHanlderResult(true):new ExcelVerifyHanlderResult(false, name + NOT_NULL);
    }

    public static ExcelVerifyHanlderResult regex(String name, Object val, String regex, String regexTip) {
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(String.valueOf(val)).matches()?new ExcelVerifyHanlderResult(false, name + regexTip):new ExcelVerifyHanlderResult(true);
    }
}
