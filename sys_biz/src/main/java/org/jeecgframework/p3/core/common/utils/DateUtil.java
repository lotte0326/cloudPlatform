//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.jeecgframework.p3.core.common.utils.StringUtil;

public class DateUtil {
    private static final Logger logger = Logger.getLogger(DateUtil.class.getName());
    private static final SimpleDateFormat SHOW_DATE = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private static final SimpleDateFormat TIGHT_FORM_DATE = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat SIMPLE_FORM_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SIMPLE_FORM_DATE2 = new SimpleDateFormat("yyyyMMdd");
    private static final String FORMATTYPE1 = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMATTYPE2 = "yyyy/MM/dd HH:mm:ss";
    private static final String FORMATTYPE3 = "yyyy年MM月dd日 HH:mm:ss";
    private static final String FORMATTYPE4 = "yyyy年MM月dd日 HH时mm分ss秒";
    private static final String FORMATTYPE5 = "yyyy年MM月dd日 E HH:mm:ss";
    private static final String FORMATTYPE6 = "yyyy年MM月dd日 E HH时mm分ss秒";
    private static final String FORMATTYPE7 = "yyyyMMdd";
    private static final String FORMATTYPE8 = "yyyyMMddHHmmss";
    private static final String FORMATTYPE9 = "yyyy-MM-dd";
    private static final String FORMATTYPE10 = "yyyy_MM";
    private static final String FORMATTYPE11 = "yyyy";
    private static final String FORMATTYPE12 = "yyyyMM";
    private static final String FORMATTYPE13 = "yyyy-MM";
    private static final String FORMATTYPE14 = "yyyy年MM月dd日 HH:mm";

    private DateUtil() {
    }

    public static Date str2date(String dateStr, String pattern) {
        if(!StringUtil.isEmpty(dateStr) && !StringUtil.isEmpty(pattern)) {
            try {
                SimpleDateFormat ex = new SimpleDateFormat(pattern);
                return ex.parse(dateStr);
            } catch (Exception var3) {
                logger.warning("[DATE] " + var3.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public static String convertToShowTime(String dateStr) {
        if(StringUtil.isEmptyStrnull(dateStr)) {
            return "";
        } else {
            String ret = dateStr;

            try {
                TIGHT_FORM_DATE.setLenient(false);
                Date e = TIGHT_FORM_DATE.parse(dateStr);
                SHOW_DATE.setLenient(false);
                ret = SHOW_DATE.format(e);
            } catch (ParseException var3) {
                logger.warning("[DATE] " + var3.getMessage());
            }

            return ret;
        }
    }

    public static String convertToShowTime(Date date) {
        if(date == null) {
            return "";
        } else {
            String ret = "";

            try {
                SHOW_DATE.setLenient(false);
                ret = SHOW_DATE.format(date);
            } catch (Exception var3) {
                logger.warning("[DATE] " + var3.getMessage());
            }

            return ret;
        }
    }

    public static String date2str(Date date, String pattern) {
        if(date != null && !StringUtil.isEmpty(pattern)) {
            try {
                SimpleDateFormat ex = new SimpleDateFormat(pattern);
                return ex.format(date);
            } catch (Exception var3) {
                logger.warning("[DATE] " + var3.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public static String convert(Date date) {
        if(date == null) {
            return null;
        } else {
            try {
                return TIGHT_FORM_DATE.format(date);
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static String dateToString(Object date) {
        if(date == null) {
            return "";
        } else {
            try {
                Date date1 = (Date)date;
                return SIMPLE_FORM_DATE2.format(date1);
            } catch (Exception var2) {
                logger.warning("[DATE]" + var2.getMessage());
                return "";
            }
        }
    }

    public static String formatDateTime(Date date, String pattern) {
        if(date == null) {
            return null;
        } else {
            try {
                SimpleDateFormat e = new SimpleDateFormat(pattern);
                return e.format(date);
            } catch (Exception var3) {
                logger.warning("[DATE]" + var3.getMessage());
                return null;
            }
        }
    }

    public static String get(int formatType, Date date) {
        if(date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = null;
            switch(formatType) {
                case 1:
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 2:
                    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    break;
                case 3:
                    sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    break;
                case 4:
                    sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    break;
                case 5:
                    sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH:mm:ss");
                    break;
                case 6:
                    sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒");
                    break;
                case 7:
                    sdf = new SimpleDateFormat("yyyyMMdd");
                    break;
                case 8:
                    sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    break;
                case 9:
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    break;
                case 10:
                    sdf = new SimpleDateFormat("yyyy_MM");
                    break;
                case 11:
                    sdf = new SimpleDateFormat("yyyy");
                    break;
                case 12:
                    sdf = new SimpleDateFormat("yyyyMM");
                    break;
                case 13:
                    sdf = new SimpleDateFormat("yyyy-MM");
                    break;
                default:
                    sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            }

            return sdf.format(date);
        }
    }

    public static Date stringToDate(String strDate) {
        if(strDate != null && !"".equals(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        } else {
            return null;
        }
    }

    public static Date convertStr2DateForQuery(String source) throws ParseException {
        if(source != null && source.trim().length() == 14) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            date = sdf.parse(source);
            return date;
        } else {
            return null;
        }
    }

    public static Date convertStr2Date(String source) throws ParseException {
        if(source != null && source.trim().length() == 14) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            date = sdf.parse(source);
            long ltemp = date.getTime() - (new Date()).getTime();
            return ltemp <= 43200000L && ltemp >= -43200000L?date:null;
        } else {
            return null;
        }
    }

    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String date2Str(Date date, String format) {
        if(date != null && !StringUtil.isEmptyStrnull(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public static String formatDate2Str(Date date) {
        if(date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            return sdf.format(date);
        }
    }

    public static Date str2Date(String date) {
        if(StringUtil.notEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setLenient(false);

            try {
                return sdf.parse(date);
            } catch (ParseException var3) {
                return new Date();
            }
        } else {
            return null;
        }
    }

    public static Date str2Date(String date, String format) {
        if(StringUtil.notEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);

            try {
                return sdf.parse(date);
            } catch (ParseException var4) {
                return new Date();
            }
        } else {
            return null;
        }
    }

    public static Date convert(String date) {
        try {
            return TIGHT_FORM_DATE.parse(date);
        } catch (ParseException var4) {
            try {
                return SIMPLE_FORM_DATE.parse(date);
            } catch (ParseException var3) {
                throw new IllegalArgumentException("无法识别的日期, " + date);
            }
        }
    }

    public static String strConvertDateStr(String strDate, String format1, String format2) {
        if(strDate != null && !"".equals(strDate)) {
            SimpleDateFormat df1 = new SimpleDateFormat(format1);
            SimpleDateFormat df2 = new SimpleDateFormat(format2);
            df1.setLenient(false);
            df2.setLenient(false);
            String resultDate = "";

            try {
                resultDate = df2.format(df1.parse(strDate));
            } catch (Exception var7) {
                ;
            }

            return resultDate;
        } else {
            return strDate;
        }
    }

    public static String nDayDate(int n, String format1) {
        Calendar cal = Calendar.getInstance();
        cal.add(5, n);
        SimpleDateFormat format = new SimpleDateFormat(format1);
        format.setLenient(false);
        return format.format(cal.getTime());
    }

    public static String formatDate(String day, int formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date e = sdf.parse(day);
            if(formatType == 1) {
                sdf = new SimpleDateFormat("yyyy年MM月dd日");
            }

            if(formatType == 2) {
                sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            }

            return sdf.format(e);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Map<String, String> dateStrToStr(String dateString) {
        if(dateString != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(dateString.substring(0, 4));
            stringBuffer.append("-");
            stringBuffer.append(dateString.substring(4, 6));
            stringBuffer.append("-");
            stringBuffer.append(dateString.substring(6, 8));
            HashMap map = new HashMap();
            map.put("beginTime", stringBuffer + " 00:00:00:00");
            map.put("endTime", stringBuffer + " 23:59:59:58");
            return map;
        } else {
            return null;
        }
    }

    public static Date strToDate(String dateString) {
        String lineOneString = dateString.substring(0, dateString.length());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(lineOneString.substring(0, 4));
        stringBuffer.append("-");
        stringBuffer.append(lineOneString.substring(4, 6));
        stringBuffer.append("-");
        stringBuffer.append(lineOneString.substring(6, 8));
        stringBuffer.append(" ");
        stringBuffer.append(dateString.substring(8, 10));
        stringBuffer.append(":");
        stringBuffer.append(dateString.substring(10, 12));
        stringBuffer.append(":");
        stringBuffer.append(dateString.substring(12, 14));
        return str2Date(stringBuffer.toString());
    }

    public static Date strToDate(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            return sdf.parse(dateString);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static String convertStrTimestamp(String str) {
        if(StringUtil.isEmpty(str)) {
            return "";
        } else {
            String timeStamp = str.replace("-", "");
            timeStamp = timeStamp.replace(":", "");
            timeStamp = timeStamp.replace(" ", "");
            return timeStamp;
        }
    }

    public static String TimestampToStr(String str) {
        if(StringUtil.isEmpty(str)) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(0, 4));
            stringBuffer.append("-");
            stringBuffer.append(str.substring(4, 6));
            stringBuffer.append("-");
            stringBuffer.append(str.substring(6, 8));
            return stringBuffer.toString();
        }
    }

    public static Long changeDateToLong(String dateString, String dateFormt) {
        if(!StringUtil.isEmpty(dateString) && !StringUtil.isEmpty(dateFormt)) {
            SimpleDateFormat df = new SimpleDateFormat(dateFormt);
            Date date = new Date();

            try {
                date = df.parse(dateString);
            } catch (ParseException var5) {
                ;
            }

            Long dateLong = Long.valueOf(date.getTime());
            return Long.valueOf(dateLong.longValue() / 1000L);
        } else {
            return null;
        }
    }

    public static String getMonthOne(Date currentTime) {
        if(currentTime == null) {
            return "";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTime);
            calendar.add(2, -1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String mDateTime = formatter.format(calendar.getTime());
            return mDateTime;
        }
    }

    public static String getOneDay(Date currentTime) {
        if(currentTime == null) {
            return "";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTime);
            calendar.add(5, -1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String mDateTime = formatter.format(calendar.getTime());
            return mDateTime;
        }
    }

    public static String getDate(Date date, String dateString) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat(dateString);
        sd.setLenient(false);
        String to_date = sd.format(date);
        return to_date;
    }

    public static Date getDate(String strDate) throws ParseException {
        SimpleDateFormat format = null;
        if(strDate.contains("-")) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            format = new SimpleDateFormat("yyyyMMddHHmmss");
        }

        Date date = null;

        try {
            format.setLenient(false);
            date = format.parse(strDate);
        } catch (Exception var4) {
            logger.info(var4.getMessage());
        }

        return date;
    }

    public static Date convert2Date(String strDate, String dateString) throws ParseException {
        if(!StringUtil.isEmpty(strDate) && !StringUtil.isEmpty(dateString)) {
            SimpleDateFormat format = new SimpleDateFormat(dateString);
            Date date = null;
            format.setLenient(false);
            date = format.parse(strDate);
            return date;
        } else {
            return null;
        }
    }

    public static Date convertToDate(String strDate, String dateString) {
        if(!StringUtil.isEmpty(strDate) && !StringUtil.isEmpty(dateString)) {
            SimpleDateFormat format = new SimpleDateFormat(dateString);
            Date date = null;
            format.setLenient(false);

            try {
                date = format.parse(strDate);
            } catch (ParseException var5) {
                var5.printStackTrace();
            }

            return date;
        } else {
            return null;
        }
    }

    public static String getTimestamp(String strDate) {
        return StringUtil.notEmpty(strDate)?strDate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").trim():"";
    }

    public static Date addDays(Date date, int ndays) {
        if(date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(6, ndays);
            return calendar.getTime();
        }
    }

    public static Date calStringToDate(Object date) {
        String dateStr = StringUtil.toStringAndTrim(date);
        if(StringUtil.isEmpty(dateStr)) {
            return null;
        } else {
            dateStr = dateStr.replaceAll("-", "");
            return new Date(bocmDateToCal(dateStr).getTimeInMillis());
        }
    }

    public static String rolDate(String inputDate, long days) {
        Date inday = bocmDateToCal(inputDate).getTime();
        long l = inday.getTime();
        long rol = l - days * 24L * 60L * 60L * 1000L;
        Date rolDay = new Date(rol);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rolDay);
        int i = calendar.get(1);
        int j = calendar.get(2) + 1;
        int k = calendar.get(5);
        return i + (j >= 10?"" + j:"0" + j) + (k >= 10?"" + k:"0" + k);
    }

    public static Date rolDate(Date inputDate, long days) {
        SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyyMMdd");
        String ret = rolDate(DATE_FORMATE.format(inputDate), days);
        return new Date(bocmDateToCal(ret).getTimeInMillis());
    }

    public static Calendar bocmDateToCal(String s) {
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6)) - 1;
        int k = Integer.parseInt(s.substring(6, 8));
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(i, j, k, 0, 0, 0);
        return calendar;
    }

    private static Date alterDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date alterYear(Date date, int amount) {
        return alterDate(date, 1, amount);
    }

    public static Date alterMonty(Date date, int amount) {
        return alterDate(date, 2, amount);
    }

    public static Date alterDay(Date date, int amount) {
        return alterDate(date, 5, amount);
    }

    public static Date alterHour(Date date, int amount) {
        return alterDate(date, 11, amount);
    }

    public static Date alterMinute(Date date, int amount) {
        return alterDate(date, 12, amount);
    }

    public static Date alterSecond(Date date, int amount) {
        return alterDate(date, 13, amount);
    }

    public static Date getStartTime(String date) throws ParseException {
        if(StringUtil.isEmpty(date)) {
            return null;
        } else {
            String start = date + "000000";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            return sdf.parse(start);
        }
    }

    public static Date getStartTimeOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date date = calendar.getTime();
        return date;
    }

    public static Date convert2StartDate(Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date convert2EndDate(Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public static String getPolicyStartTime(Date source) {
        return get(1, source);
    }

    public static String getPolicyEndTime(Date source) {
        return get(9, source) + " 24:00:00";
    }

    public static String getTransferedPolicyStartTime(Date source) {
        return get(8, convert2StartDate(source));
    }

    public static String getTransferedPolicyEndTime(Date source) {
        return get(8, alterSecond(source, 1));
    }

    public static String getStartTime(Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date date = calendar.getTime();
        return get(1, date);
    }

    public static String getStartTime(int format, Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date date = calendar.getTime();
        return get(format, date);
    }

    public static String getEndTime(Date source) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        Date date = calendar.getTime();
        return get(1, date);
    }

    public static Date getEndTime(String date) throws ParseException {
        String end = date + "235959";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setLenient(false);
        return sdf.parse(end);
    }

    public static java.sql.Date getCurrDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static Date getTodayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyyMMddHHmmss");
        return DATE_FORMATE.format(new Date(System.currentTimeMillis()));
    }

    public static String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[0];
    }

    public static String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[1];
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[2].split(" ")[0];
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split(" ")[0];
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split(" ")[1];
    }

    public static String getCurrentFullTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date());
    }

    public static String getHours() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split(" ")[1].split(":")[0];
    }

    public static String getMinutes() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split(" ")[1].split(":")[1];
    }

    public static String getSeconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split(" ")[1].split(":")[2];
    }

    public static Date stringToDate(String str, String format) {
        if(!StringUtil.isEmpty1(str) && !StringUtil.isEmpty1(format)) {
            try {
                SimpleDateFormat e = new SimpleDateFormat(format);
                e.setLenient(false);
                return e.parse(str);
            } catch (ParseException var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date stringToDate1(String str) {
        return StringUtil.isEmpty1(str)?null:stringToDate(str, "yyyy-MM-dd");
    }

    public static Date stringToDateForQuery(String str) {
        return !StringUtil.isEmpty1(str) && str.length() == 14?stringToDate(str, "yyyyMMddHHmmss"):null;
    }

    public static Date stringToDateCompareNow(String str, long time) {
        if(!StringUtil.isEmpty1(str) && time >= 0L) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);

            try {
                date = sdf.parse(str);
            } catch (ParseException var7) {
                return null;
            }

            long timediff = date.getTime() - (new Date()).getTime();
            return timediff <= time && timediff >= -time?date:null;
        } else {
            return null;
        }
    }

    public static Date stringToDateCompareNow(String str) {
        return StringUtil.isEmpty1(str)?null:stringToDateCompareNow(str, 43200000L);
    }

    public static Date stringToDate(int formattype, String str) {
        if(StringUtil.isEmpty1(str)) {
            return null;
        } else {
            String type = "";
            switch(formattype) {
                case 1:
                    type = "yyyy-MM-dd HH:mm:ss";
                    break;
                case 2:
                    type = "yyyy/MM/dd HH:mm:ss";
                    break;
                case 3:
                    type = "yyyy年MM月dd日 HH:mm:ss";
                    break;
                case 4:
                    type = "yyyy年MM月dd日 HH时mm分ss秒";
                    break;
                case 5:
                    type = "yyyy年MM月dd日 E HH:mm:ss";
                    break;
                case 6:
                    type = "yyyy年MM月dd日 E HH时mm分ss秒";
                    break;
                case 7:
                    type = "yyyyMMdd";
                    break;
                case 8:
                    type = "yyyyMMddHHmmss";
                    break;
                case 9:
                    type = "yyyy-MM-dd";
                    break;
                case 10:
                    type = "yyyy_MM";
                    break;
                case 11:
                    type = "yyyy";
                    break;
                case 12:
                    type = "yyyyMM";
                    break;
                case 13:
                    type = "yyyy-MM";
                    break;
                default:
                    type = "yyyyMMddHHmmss";
            }

            return stringToDate(str, type);
        }
    }

    public static String stringToTimestamp(String strtime) {
        return StringUtil.isEmpty1(strtime)?"":strtime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
    }

    public static String dateToString(Date date, String format) {
        if(date != null && !StringUtil.isEmpty1(format)) {
            String retstr = "";

            try {
                SimpleDateFormat e = new SimpleDateFormat(format);
                e.setLenient(false);
                retstr = e.format(date);
                return retstr;
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String dateToString(Date date) {
        return date == null?"":dateToString(date, "yyyyMMddHHmmss");
    }

    public static String dateToString(Object obj, String format) {
        if(!StringUtil.isEmpyt1(obj) && !StringUtil.isEmpty1(format)) {
            String retstr = "";

            try {
                Date e = (Date)obj;
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                retstr = sdf.format(e);
                return retstr;
            } catch (Exception var5) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String dateToString1(Object obj) {
        return StringUtil.isEmpyt1(obj)?"":dateToString(obj, "yyyyMMdd");
    }

    public static Long dateToSeconds(String datestr, String format) {
        if(!StringUtil.isEmpty1(datestr) && !StringUtil.isEmpty1(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = null;

            try {
                date = sdf.parse(datestr);
            } catch (ParseException var5) {
                return null;
            }

            if(date != null) {
                Long ret = Long.valueOf(date.getTime());
                return Long.valueOf(ret.longValue() / 1000L);
            } else {
                return Long.valueOf(0L);
            }
        } else {
            return null;
        }
    }

    public static String timestampToString(String timestamp) {
        if(!StringUtil.isEmpty1(timestamp) && StringUtil.isNumeric(timestamp)) {
            String str = timestamp.replaceAll(" ", "");
            StringBuffer stringBuffer = new StringBuffer();
            int length = str.length();
            if(length == 8) {
                stringBuffer.append(str.substring(0, 4));
                stringBuffer.append("-");
                stringBuffer.append(str.substring(4, 6));
                stringBuffer.append("-");
                stringBuffer.append(str.substring(6, 8));
                return stringBuffer.toString();
            } else if(length == 14) {
                stringBuffer.append(str.substring(0, 4));
                stringBuffer.append("-");
                stringBuffer.append(str.substring(4, 6));
                stringBuffer.append("-");
                stringBuffer.append(str.substring(6, 8));
                stringBuffer.append(" ");
                stringBuffer.append(str.substring(8, 10));
                stringBuffer.append(":");
                stringBuffer.append(str.substring(10, 12));
                stringBuffer.append(":");
                stringBuffer.append(str.substring(12, 14));
                return stringBuffer.toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String convertToShowTime1(String str, String format) {
        if(!StringUtil.isEmpty1(str) && !StringUtil.isEmpty1(format)) {
            String retstr = "";

            try {
                SimpleDateFormat e = new SimpleDateFormat("yyyyMMddHHmmss");
                e.setLenient(false);
                Date date = e.parse(str);
                e = new SimpleDateFormat(format);
                e.setLenient(false);
                retstr = e.format(date);
                return retstr;
            } catch (Exception var5) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String convertToShowTime1(String str) {
        return StringUtil.isEmpty1(str)?"":convertToShowTime1(str, "yyyy年MM月dd日 HH:mm");
    }

    public static String convertToShowTime1(Date date, String format) {
        if(date != null && !StringUtil.isEmpty1(format)) {
            String retstr = "";

            try {
                SimpleDateFormat e = new SimpleDateFormat(format);
                e.setLenient(false);
                retstr = e.format(date);
                return retstr;
            } catch (Exception var4) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String convertToShowTime1(Date date) {
        return date == null?"":convertToShowTime1(date, "yyyy年MM月dd日 HH:mm");
    }

    public static Map<String, String> dateStrToMap(String date, String beginTime, String endTime) {
        if(!StringUtil.isEmpty1(date) && date.length() >= 8) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(date.substring(0, 4));
            stringBuffer.append("-");
            stringBuffer.append(date.substring(4, 6));
            stringBuffer.append("-");
            stringBuffer.append(date.substring(6, 8));
            HashMap map = new HashMap();
            map.put(beginTime, stringBuffer.toString().trim() + " 00:00:00:00");
            map.put(endTime, stringBuffer.toString().trim() + " 23:59:59:58");
            return map;
        } else {
            return null;
        }
    }

    public static int getDiffMonth(Date beginDate, Date endDate) {
        Calendar calbegin = Calendar.getInstance();
        Calendar calend = Calendar.getInstance();
        calbegin.setTime(beginDate);
        calend.setTime(endDate);
        int m_begin = calbegin.get(2) + 1;
        int m_end = calend.get(2) + 1;
        int checkmonth = m_end - m_begin + (calend.get(1) - calbegin.get(1)) * 12;
        return checkmonth;
    }

    public static int getDiffDay(Date beginDate, Date endDate) {
        int days = (int)((endDate.getTime() - beginDate.getTime()) / 86400000L);
        return days <= 0?1:days;
    }

    public static int getDiffYear(Date beginDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        if(cal.before(endDate)) {
            return 0;
        } else {
            int yearNow = cal.get(1);
            int monthNow = cal.get(2);
            int dayOfMonthNow = cal.get(5);
            cal.setTime(endDate);
            int yearOfEndDate = cal.get(1);
            int monthOfEndDate = cal.get(2);
            int dayOfEndDate = cal.get(5);
            int diffYear = yearNow - yearOfEndDate;
            if(monthNow <= monthOfEndDate) {
                if(monthNow == monthOfEndDate) {
                    if(dayOfMonthNow < dayOfEndDate) {
                        --diffYear;
                    }
                } else {
                    --diffYear;
                }
            }

            return diffYear;
        }
    }

    public static int getAge(Date birthday) {
        return getDiffYear(new Date(), birthday);
    }

    public static int getDiffMins(Date beginDate, Date endDate) {
        return (int)((endDate.getTime() - beginDate.getTime()) / 60000L);
    }

    public static boolean isOneDay(Date one, Date other) {
        return get(7, one).equals(get(7, other));
    }

    public static boolean isSameTime(Date one, Date other) {
        return get(1, one).equals(get(1, other));
    }

    public static Date compactInsuranceEndDate(Date beginDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int endDay = calendar.get(5);
        Calendar calendar0 = Calendar.getInstance();
        calendar0.setTime(beginDate);
        int startDay = calendar0.get(5);
        return startDay <= endDay?alterDay(endDate, -1):endDate;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }
}
