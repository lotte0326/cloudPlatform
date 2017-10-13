//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.utils;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Logger logger = Logger.getLogger(StringUtil.class.getName());
    private static final String EMAIL = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
    private static final String DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    private static final String NUMBER = "^[0-9]+$";
    private static final String MOBILE = "^(1[0-9])\\d{9}$";
    private static final String SCRIPTREG = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
    private static final String STYLEREG = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
    private static final String HTMLREG = "[<>].*";
    private static final String INTEGER = "^[-\\+]?[\\d]*$";
    private static final String DOUBLE = "^[-+]?(\\d+(\\.\\d*)?|(\\.\\d+))([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";
    private static final String CHINESE = "[Α-￥]+$";
    private static final String CONTROL_CHARACTER = "\\s*|\t|\r|\n";

    public StringUtil() {
    }

    public static String getNullStringVal(String val) {
        return isEmpty(val)?"":val;
    }

    public static boolean isEmpty(String str) {
        return !notEmpty(str);
    }

    public static String retrievePayIdFromSharaData(String payId) {
        if(isEmpty(payId)) {
            return payId;
        } else {
            String[] colons = payId.split(";");

            for(int i = 0; i < colons.length; ++i) {
                String s = colons[i];
                if(!isEmpty(s)) {
                    int commapos = payId.indexOf(",");
                    if(-1 != commapos) {
                        String[] splitedPayId = s.split(",");
                        return splitedPayId[1];
                    }
                }
            }

            return payId;
        }
    }

    public static boolean notEmpty(String str) {
        return str != null && str.trim().length() > 0 && !str.toLowerCase().equals("null");
    }

    public static boolean notEmptyStrnull(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isEmptyStrnull(String str) {
        return !notEmptyStrnull(str);
    }

    public static boolean isEmpty(Object object) {
        return object == null || object.toString().trim().length() == 0;
    }

    public static boolean notEmpty(Object object) {
        return !isEmpty(object);
    }

    public static String trimString(String str) {
        if(str == null) {
            str = "";
        }

        return str.trim();
    }

    public static String toStringAndTrim(Object object) {
        return object == null?"":object.toString().trim();
    }

    public static String formatMobile(String mobile) {
        if(mobile != null && !"".equals(mobile.trim())) {
            String result = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
            return result;
        } else {
            return null;
        }
    }

    public static String[] str2StrArray(String str, String splitRegex) {
        return isEmpty(str)?null:str.split(splitRegex);
    }

    public static String[] str2StrArray(String str) {
        return str2StrArray(str, ",\\s*");
    }

    public static Map<String, String> string2Map(String text, String splitChar) {
        HashMap param = new HashMap();
        if(!isEmpty(text) && !isEmpty(splitChar)) {
            String[] stemps = text.split(splitChar);
            String[] temp = (String[])null;

            for(int i = 0; i < stemps.length; ++i) {
                temp = stemps[i].split("=");
                if(temp.length >= 2) {
                    param.put(temp[0], temp[1]);
                    temp = (String[])null;
                } else {
                    param.put(temp[0], "");
                }
            }

            return param;
        } else {
            return param;
        }
    }

    public static String concatMap(Map<String, String> paramMap) {
        if(paramMap == null) {
            return "";
        } else {
            StringBuilder sbuf = new StringBuilder(512);
            Set set = paramMap.entrySet();
            Iterator it = set.iterator();

            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                sbuf.append((String)entry.getKey()).append("=").append((String)entry.getValue()).append("&");
            }

            if(sbuf.length() > 0) {
                sbuf.deleteCharAt(sbuf.length() - 1);
            }

            return sbuf.toString();
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isNumeric(String string, int len) {
        return string.length() > len?false:isNumeric(string);
    }

    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
        Matcher m = p.matcher(mobiles);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    public static boolean isUrl(String url) {
        return isEmpty(url)?true:url.length() < 4 || !url.trim().substring(0, 4).toLowerCase().equals("http");
    }

    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[一-龥]";

        for(int i = 0; i < value.length(); ++i) {
            String temp = value.substring(i, i + 1);
            if(temp.matches(chinese)) {
                valueLength += 2;
            } else {
                ++valueLength;
            }
        }

        return valueLength;
    }

    public static String subStr(String temp, String beginStr, String endStr) {
        return !isEmpty(temp) && !isEmpty(beginStr) && !isEmpty(endStr)?(temp.contains(beginStr) && temp.contains(endStr)?temp.substring(temp.indexOf(beginStr) + beginStr.length(), temp.indexOf(endStr)):""):"";
    }

    public static String getString(String str, int longth) {
        return str != null && str.trim().length() > 0?(str.length() > longth?str.substring(0, longth):str):"";
    }

    public static String double2String(double d, int fNumber) {
        if(fNumber < 0) {
            fNumber = 0;
        }

        String pattern = null;
        switch(fNumber) {
            case 0:
                pattern = "#0";
                break;
            default:
                pattern = "#0.";
                StringBuffer formatter = new StringBuffer(pattern);

                for(int value = 0; value < fNumber; ++value) {
                    formatter.append('0');
                }

                pattern = formatter.toString();
        }

        DecimalFormat var6 = new DecimalFormat();
        var6.applyPattern(pattern);
        String var7 = var6.format(d);
        return var7;
    }

    public static Map<String, String> convertMap(String responseContent, String splitChar) throws Exception {
        HashMap retMap = new HashMap();
        if(responseContent != null && !"".equals(responseContent)) {
            String[] results = responseContent.split(splitChar);
            String[] var7 = results;
            int var6 = results.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String stemp = var7[var5];
                String[] maps = stemp.split("=");
                if(maps != null && maps.length >= 1) {
                    if(maps.length >= 2) {
                        retMap.put(maps[0], maps[1]);
                    } else {
                        retMap.put(maps[0], "");
                    }
                }
            }

            return retMap;
        } else {
            return null;
        }
    }

    public static String retMerchantString(Map map) {
        String ret = "";
        Iterator entries = map.entrySet().iterator();
        String name = "";
        String value = "";

        while(entries.hasNext()) {
            Entry entry = (Entry)entries.next();
            name = (String)entry.getKey();
            Object valueObj = entry.getValue();
            if(valueObj == null) {
                value = "";
            } else {
                value = valueObj.toString();

                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (Exception var8) {
                    ;
                }
            }

            if(ret.length() <= 0) {
                ret = name + "=" + value;
            } else {
                ret = ret + "&" + name + "=" + value;
            }
        }

        return ret;
    }

    public static String map2String(Map map) {
        String ret = "";
        if(map != null && !map.isEmpty()) {
            Iterator entries = map.entrySet().iterator();
            String name = "";

            for(String value = ""; entries.hasNext(); ret = ret + name + "=" + value + ";") {
                Entry entry = (Entry)entries.next();
                name = (String)entry.getKey();
                Object valueObj = entry.getValue();
                if(valueObj == null) {
                    value = "";
                } else if(!(valueObj instanceof String[])) {
                    value = valueObj.toString();
                } else {
                    String[] values = (String[])valueObj;

                    for(int i = 0; i < values.length; ++i) {
                        value = values[i] + ",";
                    }

                    value = value.substring(0, value.length() - 1);
                }
            }

            return ret;
        } else {
            return ret;
        }
    }

    public static String printMap(Map<String, String> map) {
        String ret = "";
        if(map == null) {
            return ret;
        } else {
            String key;
            String value;
            for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ret = ret + key + "=" + value) {
                key = (String)iterator.next();
                value = (String)map.get(key);
                if(ret.length() > 0) {
                    ret = ret + ";";
                }
            }

            return ret;
        }
    }

    public static String printMap(Map<String, String> map, String splitString) {
        String ret = "";
        if(map == null) {
            return ret;
        } else {
            String key;
            String value;
            for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ret = ret + key + "=" + value) {
                key = (String)iterator.next();
                value = (String)map.get(key);
                if(ret.length() > 0) {
                    ret = ret + splitString;
                }
            }

            return ret;
        }
    }

    public static String showSensitiveParam(Map<String, String> map, boolean safety) {
        StringBuilder sbuf = new StringBuilder(128);
        Set set = map.keySet();
        boolean flag = false;
        sbuf.append("[");

        for(Iterator it = set.iterator(); it.hasNext(); flag = true) {
            String key = (String)it.next();
            String value = (String)map.get(key);
            if(safety) {
                if("passwd".equals(key) || "pass_wd".equals(key) || "cvv2".equals(key) || "cvv".equals(key)) {
                    if(value != null && !"".equals(value)) {
                        value = "******";
                    } else {
                        value = "";
                    }
                }

                if(value == null) {
                    value = "";
                }

                if(("card_id".equals(key) || "cardid".equals(key)) && value.length() > 6) {
                    value = value.substring(0, 6) + "******";
                }
            }

            if(!flag) {
                sbuf.append(key).append("=").append(value);
            } else {
                sbuf.append(",").append(key).append("=").append(value);
            }
        }

        sbuf.append("]");
        return sbuf.toString();
    }

    public static List<String> strToList(String[] obj) {
        ArrayList arrayList;
        if(obj != null && obj.length > 0) {
            arrayList = new ArrayList(Arrays.asList(obj));
            return arrayList;
        } else {
            arrayList = new ArrayList();
            return arrayList;
        }
    }

    public static String convertStr(String str, String oldStr) {
        return str.replace(oldStr, "");
    }

    public static String formatInputParam(String input) {
        if(input != null && !"".equals(input.trim())) {
            String textStr = "";

            try {
                String scriptReg = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
                String styleReg = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
                String htmlReg = "[<>].*";
                Matcher scriptMatcher = Pattern.compile(scriptReg, 2).matcher(input);
                input = scriptMatcher.replaceAll("");
                Matcher styleMatcher = Pattern.compile(styleReg, 2).matcher(input);
                input = styleMatcher.replaceAll("");
                Matcher htmlMatcher = Pattern.compile(htmlReg, 2).matcher(input);
                input = htmlMatcher.replaceAll("");
                textStr = input.replaceAll("&hellip;&hellip;", "……");
                textStr = textStr.replaceAll("&nbsp;", "").trim();
                textStr = textStr.replaceAll("\"", "");
                textStr = textStr.replaceAll("\r", "");
                textStr.replaceAll("\n", "");
            } catch (Exception var8) {
                ;
            }

            return textStr;
        } else {
            return input;
        }
    }

    public static boolean judgeInputParam(String input) {
        if(input != null && !"".equals(input.trim())) {
            try {
                String e = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
                String styleReg = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
                String htmlReg = "[<>].*";
                Matcher charMatcher = Pattern.compile(".*[<>\\?\\*\'\"].*", 2).matcher(input);
                if(charMatcher.matches()) {
                    return false;
                } else {
                    Matcher scriptMatcher = Pattern.compile(e, 2).matcher(input);
                    if(scriptMatcher.matches()) {
                        return false;
                    } else {
                        Matcher styleMatcher = Pattern.compile(styleReg, 2).matcher(input);
                        if(styleMatcher.matches()) {
                            return false;
                        } else {
                            Matcher htmlMatcher = Pattern.compile(htmlReg, 2).matcher(input);
                            return !htmlMatcher.matches();
                        }
                    }
                }
            } catch (Exception var8) {
                logger.warning(" errMsg: " + var8.getMessage());
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean isEmpty1(String str) {
        return !notEmpty1(str);
    }

    public static boolean notEmpty1(String str) {
        return str != null && str.trim().length() > 0 && !str.toLowerCase().equals("null");
    }

    public static boolean isEmptyNull(String str) {
        return !notEmptyNull(str);
    }

    public static boolean notEmptyNull(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isEmpyt1(Object obj) {
        return !notEmpty1(obj);
    }

    public static boolean notEmpty1(Object obj) {
        return obj != null && obj.toString().trim().length() > 0;
    }

    public static String trimString1(String str) {
        return isEmptyNull(str)?"":str.trim();
    }

    public static String trimObject(Object obj) {
        return isEmpyt1(obj)?"":obj.toString().trim();
    }

    public static boolean isEmail1(String email) {
        if(isEmpty1(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
            Matcher mathcer = pattern.matcher(email);
            return mathcer.matches();
        }
    }

    public static boolean isDate1(String date) {
        if(isEmpty1(date)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
            Matcher mathcer = pattern.matcher(date);
            return mathcer.matches();
        }
    }

    public static boolean isNumeric1(String number) {
        if(isEmpty1(number)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[0-9]+$");
            Matcher mathcer = pattern.matcher(number);
            return mathcer.matches();
        }
    }

    public static boolean isMobile1(String mobile) {
        if(isEmpty1(mobile)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^(1[0-9])\\d{9}$");
            Matcher mathcer = pattern.matcher(mobile);
            return mathcer.matches();
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-+]?(\\d+(\\.\\d*)?|(\\.\\d+))([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$");
        return pattern.matcher(str).matches();
    }

    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[Α-￥]+$");
        return pattern.matcher(str).matches();
    }

    public static String getHideMobileNum(String mobile) {
        return !isEmpty1(mobile) && isMobile1(mobile)?mobile.substring(0, 3) + repeat("*", 4) + mobile.subSequence(7, 11):"";
    }

    public static String getHideEmailPrefix(String email) {
        if(email != null) {
            int index = email.lastIndexOf(64);
            if(index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }

        return email;
    }

    public static String repeat(String src, int num) {
        StringBuffer s = new StringBuffer();

        for(int i = 0; i < num; ++i) {
            s.append(src);
        }

        return s.toString();
    }

    public static int getStrLength(String str) {
        if(isEmptyNull(str)) {
            return 0;
        } else {
            int valueLength = 0;
            String chinese = "[一-龥]";

            for(int i = 0; i < str.length(); ++i) {
                String temp = str.substring(i, i + 1);
                if(temp.matches(chinese)) {
                    valueLength += 2;
                } else {
                    ++valueLength;
                }
            }

            return valueLength;
        }
    }

    public static String getSubString(String str, String bstr, String estr) {
        return !isEmptyNull(str) && !isEmptyNull(bstr) && !isEmptyNull(estr)?(str.contains(bstr) && str.contains(estr)?str.substring(str.indexOf(bstr) + bstr.length(), str.indexOf(estr)):""):"";
    }

    public static String getSubString(String str, int begin, int end) {
        return !isEmptyNull(str) && str.length() >= end && begin >= 0 && end >= 0?str.substring(begin, end):"";
    }

    public static String getSubString(String str, int index) {
        return !isEmptyNull(str) && str.length() >= index && index >= 0?str.substring(0, index):"";
    }

    public static List<String> stringToList(String str, String splitregex) {
        if(!isEmptyNull(str) && !isEmptyNull(splitregex)) {
            ArrayList retlist = new ArrayList();
            String[] strs = str.split(splitregex);
            if(notEmpty1((Object)strs)) {
                for(int i = 0; i < strs.length; ++i) {
                    retlist.add(strs[i]);
                }
            }

            return retlist;
        } else {
            return null;
        }
    }

    public static List<String> stringToList(String str) {
        return isEmptyNull(str)?null:stringToList(str, "\\s*,\\s*");
    }

    public static List<String> stringToList(String[] obj) {
        return isEmpyt1(obj)?null:new ArrayList(Arrays.asList(obj));
    }

    public static String[] stringToArray(String str, String spiltregex) {
        return isEmptyNull(str)?null:str.split(spiltregex);
    }

    public static String[] stringToArray(String str) {
        return isEmptyNull(str)?null:stringToArray(str, "\\s*,\\s*");
    }

    public static Map<String, String> stringToMap(String str, String connectors, String splitregex, String defalutchar) {
        if(!isEmptyNull(str) && !isEmptyNull(connectors) && !isEmptyNull(splitregex) && !isEmptyNull(defalutchar)) {
            HashMap returnMap = new HashMap();
            String[] keyValue = str.split(splitregex);
            String[] temp = (String[])null;

            for(int index = 0; index < keyValue.length; ++index) {
                temp = keyValue[index].split(connectors);
                if(temp.length >= 2) {
                    returnMap.put(temp[0].trim(), temp[1].trim());
                    temp = (String[])null;
                } else {
                    returnMap.put(temp[0].trim(), defalutchar);
                }
            }

            return returnMap;
        } else {
            return null;
        }
    }

    public static Map<String, String> stringToMap(String str, String connectors, String splitregex) {
        return !isEmptyNull(str) && !isEmptyNull(splitregex) && !isEmptyNull(connectors)?stringToMap(str, connectors, splitregex, ""):null;
    }

    public static Map<String, String> stringToMap(String str, String connectors) {
        return !isEmptyNull(str) && !isEmptyNull(connectors)?stringToMap(str, connectors, "\\s*;\\s*", ""):null;
    }

    public static Map<String, String> stringToMap(String str) {
        return isEmptyNull(str)?null:stringToMap(str, "\\s*=\\s*", "\\s*;\\s*", "");
    }

    public static String mapToString(Map<String, String> map, String connectors, String splitregex) {
        if(!isEmpyt1(map) && !isEmpty1(connectors) && !isEmpty1(splitregex)) {
            StringBuilder sb = new StringBuilder();
            Set set = map.entrySet();
            Iterator iterator = set.iterator();

            while(iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                sb.append((String)entry.getKey()).append(connectors).append((String)entry.getValue()).append(splitregex);
            }

            if(sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String mapToString(Map<String, String> map, String connectors) {
        return !isEmpyt1(map) && !isEmpty1(connectors)?mapToString(map, connectors, "&"):"";
    }

    public static String mapToString(Map<String, String> map) {
        return isEmpyt1(map)?"":mapToString(map, "=", "&");
    }

    public static String objectMapToString(Map<String, Object> map, String connectors, String splitregex) {
        if(!isEmpyt1(map) && !isEmpty1(connectors) && !isEmpty1(splitregex)) {
            StringBuilder sb = new StringBuilder();
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            String key = "";

            for(String value = ""; iterator.hasNext(); sb.append(key).append(connectors).append(value).append(splitregex)) {
                Entry entry = (Entry)iterator.next();
                key = (String)entry.getKey();
                Object obj = entry.getValue();
                if(obj == null) {
                    value = "";
                } else if(!(obj instanceof String[])) {
                    value = obj.toString().trim();
                } else {
                    String[] values = (String[])obj;

                    for(int i = 0; i < values.length; ++i) {
                        value = values[i] + ",";
                    }

                    value = value.substring(0, value.length() - 1);
                }
            }

            if(sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String objectMapToString(Map<String, Object> map, String connectors) {
        return !isEmpyt1(map) && !isEmpty1(connectors)?objectMapToString(map, connectors, "&"):"";
    }

    public static String objectMapToString(Map<String, Object> map) {
        return isEmpyt1(map)?"":objectMapToString(map, "=", "&");
    }

    public static String replaceString(String str, String replaced, String replace) {
        return isEmpty1(str)?"":str.replaceAll(replaced, replace);
    }

    public static String replaceString(String str, String replaced) {
        return isEmpty1(str)?"":replaceString(str, replaced, "");
    }

    public static String replaceControlCharacter(String str) {
        if(str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }

        return str;
    }

    public static String removeSameString(String str) {
        LinkedHashSet mLinkedSet = new LinkedHashSet();
        String[] strArray = str.split(" ");
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < strArray.length; ++i) {
            if(!mLinkedSet.contains(strArray[i])) {
                mLinkedSet.add(strArray[i]);
                sb.append(strArray[i] + " ");
            }
        }

        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static final String QJToBJChange(String QJStr) {
        char[] chr = QJStr.toCharArray();
        String str = "";

        for(int i = 0; i < chr.length; ++i) {
            chr[i] -= 'ﻠ';
            str = str + chr[i];
        }

        return str;
    }

    public static int stringToInt(String s) {
        if(notEmptyNull(s)) {
            try {
                return Integer.parseInt(s);
            } catch (Exception var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static float stringToFloat(String s) {
        if(notEmptyNull(s)) {
            try {
                return Float.parseFloat(s);
            } catch (Exception var2) {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    public static double stringToDouble(String s) {
        if(notEmptyNull(s)) {
            try {
                return Double.parseDouble(s);
            } catch (Exception var2) {
                return 0.0D;
            }
        } else {
            return 0.0D;
        }
    }

    public static long stringToLong(String s) {
        if(notEmptyNull(s)) {
            try {
                return Long.parseLong(s);
            } catch (Exception var2) {
                return 0L;
            }
        } else {
            return 0L;
        }
    }

    public static String doubleToString(double d, int fNumber) {
        if(fNumber < 0) {
            fNumber = 0;
        }

        String pattern = null;
        switch(fNumber) {
            case 0:
                pattern = "#0";
                break;
            default:
                pattern = "#0.";
                StringBuffer formatter = new StringBuffer(pattern);

                for(int value = 0; value < fNumber; ++value) {
                    formatter.append('0');
                }

                pattern = formatter.toString();
        }

        DecimalFormat var6 = new DecimalFormat();
        var6.applyPattern(pattern);
        String var7 = var6.format(d);
        return var7;
    }

    public static String filterString(String source) {
        return isEmpty(source)?"":source;
    }

    public static String filterInputString(String input) {
        if(isEmpty1(input)) {
            return input;
        } else {
            String textStr = "";

            try {
                Matcher scriptMatcher = Pattern.compile("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", 2).matcher(input);
                input = scriptMatcher.replaceAll("");
                Matcher styleMatcher = Pattern.compile("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", 2).matcher(input);
                input = styleMatcher.replaceAll("");
                Matcher htmlMatcher = Pattern.compile("[<>].*", 2).matcher(input);
                input = htmlMatcher.replaceAll("");
                textStr = input.replaceAll("&hellip;&hellip;", "……");
                textStr = textStr.replaceAll("&nbsp;", "").trim();
                textStr = textStr.replaceAll("\"", "");
                textStr = textStr.replaceAll("\r", "");
                textStr.replaceAll("\n", "");
            } catch (Exception var6) {
                ;
            }

            return textStr;
        }
    }

    public static boolean verifyInputString(String input) {
        if(isEmpty1(input)) {
            return true;
        } else {
            try {
                Matcher charMatcher = Pattern.compile(".*[<>\\?\\*\'\"].*", 2).matcher(input);
                if(charMatcher.matches()) {
                    return false;
                } else {
                    Matcher scriptMatcher = Pattern.compile("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", 2).matcher(input);
                    if(scriptMatcher.matches()) {
                        return false;
                    } else {
                        Matcher styleMatcher = Pattern.compile("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", 2).matcher(input);
                        if(styleMatcher.matches()) {
                            return false;
                        } else {
                            Matcher htmlMatcher = Pattern.compile("[<>].*", 2).matcher(input);
                            return !htmlMatcher.matches();
                        }
                    }
                }
            } catch (Exception var6) {
                logger.warning(" errMsg: " + var6.getMessage());
                return false;
            }
        }
    }

    public static List<String> splitSimpleString(String source, char gap) {
        LinkedList result = new LinkedList();
        if(source == null) {
            return result;
        } else {
            char[] sourceChars = source.toCharArray();
            int startIndex = 0;
            int index = -1;

            while(true) {
                do {
                    if(index++ == sourceChars.length) {
                        return result;
                    }
                } while(index != sourceChars.length && sourceChars[index] != gap);

                char[] section = new char[index - startIndex];
                System.arraycopy(sourceChars, startIndex, section, 0, index - startIndex);
                result.add(String.valueOf(section));
                startIndex = index + 1;
            }
        }
    }

    public static String maskCardName(String cardName) {
        return isEmpty(cardName)?"***":(cardName.length() > 1?cardName.substring(0, 1) + "**":"***");
    }
}
