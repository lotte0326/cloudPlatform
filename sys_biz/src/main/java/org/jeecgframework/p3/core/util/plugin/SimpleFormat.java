//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util.plugin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleFormat {
    public SimpleFormat() {
    }

    public String number(Object obj) {
        obj = obj != null && obj.toString().length() != 0?obj:Integer.valueOf(0);
        return obj.toString().equalsIgnoreCase("NaN")?"NaN":(new DecimalFormat("0.00")).format(Double.parseDouble(obj.toString()));
    }

    public String number(Object obj, String pattern) {
        obj = obj != null && obj.toString().length() != 0?obj:Integer.valueOf(0);
        return obj.toString().equalsIgnoreCase("NaN")?"NaN":(new DecimalFormat(pattern)).format(Double.parseDouble(obj.toString()));
    }

    public String round(Object obj) {
        obj = obj != null && obj.toString().length() != 0?obj:Integer.valueOf(0);
        return obj.toString().equalsIgnoreCase("NaN")?"NaN":(new DecimalFormat("0")).format(Double.parseDouble(obj.toString()));
    }

    public String currency(Object obj) {
        obj = obj != null && obj.toString().length() != 0?obj:Integer.valueOf(0);
        return NumberFormat.getCurrencyInstance(Locale.CHINA).format(obj);
    }

    public String timestampToString(Object obj, String pattern) {
        if(obj == null) {
            return "";
        } else {
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM月 -yy");
            SimpleDateFormat format2 = new SimpleDateFormat(pattern);
            Date date = null;

            try {
                date = format1.parse(obj.toString());
            } catch (ParseException var7) {
                var7.printStackTrace();
                return "error";
            }

            return format2.format(date);
        }
    }

    public String percent(Object obj) {
        obj = obj != null && obj.toString().length() != 0?obj:Integer.valueOf(0);
        return obj.toString().equalsIgnoreCase("NaN")?"":NumberFormat.getPercentInstance(Locale.CHINA).format(obj);
    }

    public String date(Object obj, String pattern) {
        return obj == null?"":(new SimpleDateFormat(pattern)).format(obj);
    }

    public String date(Object obj) {
        return obj == null?"":DateFormat.getDateInstance(1, Locale.CHINA).format(obj);
    }

    public String time(Object obj) {
        return obj == null?"":DateFormat.getTimeInstance(3, Locale.CHINA).format(obj);
    }

    public String datetime(Object obj) {
        return obj == null?"":DateFormat.getDateTimeInstance(1, 3, Locale.CHINA).format(obj);
    }
}
