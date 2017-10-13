//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class oConvertUtils {
    private static final Logger logger = Logger.getLogger(oConvertUtils.class);
    static Map<String, Object> map = new HashMap();

    public oConvertUtils() {
    }

    public static Map<String, Object> getMap() {
        return map;
    }

    public static boolean isEmpty(Object object) {
        return object == null?true:(object.equals("")?true:object.equals("null"));
    }

    public static boolean isNullOrEmpty(Object obj) {
        if(obj == null) {
            return true;
        } else if(obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if(obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else if(obj instanceof Map) {
            return ((Map)obj).isEmpty();
        } else if(!(obj instanceof Object[])) {
            return false;
        } else {
            Object[] object = (Object[])obj;
            if(object.length == 0) {
                return true;
            } else {
                boolean empty = true;

                for(int i = 0; i < object.length; ++i) {
                    if(!isNullOrEmpty(object[i])) {
                        empty = false;
                        break;
                    }
                }

                return empty;
            }
        }
    }

    public static boolean isNotEmpty(Object object) {
        return object != null && !object.equals("") && !object.equals("null");
    }

    public static String decode(String strIn, String sourceCode, String targetCode) {
        String temp = code2code(strIn, sourceCode, targetCode);
        return temp;
    }

    public static String StrToUTF(String strIn, String sourceCode, String targetCode) {
        strIn = "";

        try {
            strIn = new String(strIn.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return strIn;
    }

    private static String code2code(String strIn, String sourceCode, String targetCode) {
        String strOut = null;
        if(strIn != null && !strIn.trim().equals("")) {
            try {
                byte[] e = strIn.getBytes(sourceCode);

                for(int i = 0; i < e.length; ++i) {
                    System.out.print(e[i] + "  ");
                }

                strOut = new String(e, targetCode);
                return strOut;
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        } else {
            return strIn;
        }
    }

    public static int getInt(String s, int defval) {
        if(s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static int getInt(String s) {
        if(s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int getInt(Object s) {
        if(s != null && s != "") {
            try {
                return Integer.parseInt(s.toString());
            } catch (NumberFormatException var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int getInt(String s, Integer df) {
        if(s != null && s != "") {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException var3) {
                return 0;
            }
        } else {
            return df.intValue();
        }
    }

    public static Integer[] getInts(String[] s) {
        Integer[] integer = new Integer[s.length];
        if(s == null) {
            ;
        }

        for(int i = 0; i < s.length; ++i) {
            integer[i] = Integer.valueOf(Integer.parseInt(s[i]));
        }

        return integer;
    }

    public static double getDouble(String s, double defval) {
        if(s != null && s != "") {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException var4) {
                return defval;
            }
        } else {
            return defval;
        }
    }

    public static double getDou(Double s, double defval) {
        return s == null?defval:s.doubleValue();
    }

    public static int getInt(Object object, int defval) {
        if(isEmpty(object)) {
            return defval;
        } else {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException var3) {
                return defval;
            }
        }
    }

    public static int getInt(BigDecimal s, int defval) {
        return s == null?defval:s.intValue();
    }

    public static Integer[] getIntegerArry(String[] object) {
        int len = object.length;
        Integer[] result = new Integer[len];

        try {
            for(int e = 0; e < len; ++e) {
                result[e] = new Integer(object[e].trim());
            }

            return result;
        } catch (NumberFormatException var4) {
            return null;
        }
    }

    public static String getString(String s) {
        return getString(s, "");
    }

    public static String getString(Object object) {
        return isEmpty(object)?"":object.toString().trim();
    }

    public static String getString(int i) {
        return String.valueOf(i);
    }

    public static String getStringByArryString(String[] str) {
        if(str.length == 0) {
            return "";
        } else {
            String s = "";
            String[] var5 = str;
            int var4 = str.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                String c = var5[var3];
                s = s + "," + "\'" + c + "\'";
            }

            return s.substring(1);
        }
    }

    public static String getString(float i) {
        return String.valueOf(i);
    }

    public static String getString(String s, String defval) {
        return isEmpty(s)?defval:s.trim();
    }

    public static String getString(Object s, String defval) {
        return isEmpty(s)?defval:s.toString().trim();
    }

    public static long stringToLong(String str) {
        Long test = new Long(0L);

        try {
            test = Long.valueOf(str);
        } catch (Exception var3) {
            ;
        }

        return test.longValue();
    }

    public static String getIp() {
        String ip = null;

        try {
            InetAddress e = InetAddress.getLocalHost();
            ip = e.getHostAddress();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        }

        return ip;
    }

    public static String getRequestIp(HttpServletRequest request) {
        String ip = null;

        try {
            ip = request.getRemoteAddr();
        } catch (Exception var3) {
            ;
        }

        return ip;
    }

    private static boolean isBaseDataType(Class clazz) throws Exception {
        return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive();
    }

    public static String getIpAddrByRequest(HttpServletRequest request) {
        if(request == null) {
            return null;
        } else {
            String ip = request.getHeader("X-Real-IP");
            String fromSource = "X-Real-IP";
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
                fromSource = "X-Forwarded-For";
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                fromSource = "Proxy-Client-IP";
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                fromSource = "WL-Proxy-Client-IP";
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                fromSource = "HTTP_CLIENT_IP";
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                fromSource = "HTTP_X_FORWARDED_FOR";
            }

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                fromSource = "request.getRemoteAddr";
            }

            return ip;
        }
    }

    public static String getRealIp() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;

        while(netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration address = ni.getInetAddresses();

            while(address.hasMoreElements()) {
                ip = (InetAddress)address.nextElement();
                if(!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                }

                if(ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localip = ip.getHostAddress();
                }
            }
        }

        return netip != null && !"".equals(netip)?netip:localip;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if(str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static boolean isIn(String substring, String[] source) {
        if(source != null && source.length != 0) {
            for(int i = 0; i < source.length; ++i) {
                String aSource = source[i];
                if(aSource.equals(substring)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean isInString(String substring, String source) {
        String[] sources = (String[])null;
        if(source != null) {
            sources = source.split(",");
        }

        return isIn(substring, sources);
    }

    public static Map<Object, Object> getHashMap() {
        return new HashMap();
    }

    public static Map<Object, Object> SetToMap(Set<Object> setobj) {
        Map map = getHashMap();
        Iterator iterator = setobj.iterator();

        while(iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            map.put(entry.getKey().toString(), entry.getValue() == null?"":entry.getValue().toString().trim());
        }

        return map;
    }

    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp = false;
        long ipNum = getIpNum(ipAddress);
        long aBegin = getIpNum("10.0.0.0");
        long aEnd = getIpNum("10.255.255.255");
        long bBegin = getIpNum("172.16.0.0");
        long bEnd = getIpNum("172.31.255.255");
        long cBegin = getIpNum("192.168.0.0");
        long cEnd = getIpNum("192.168.255.255");
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
        return isInnerIp;
    }

    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = (long)Integer.parseInt(ip[0]);
        long b = (long)Integer.parseInt(ip[1]);
        long c = (long)Integer.parseInt(ip[2]);
        long d = (long)Integer.parseInt(ip[3]);
        long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
        return ipNum;
    }

    private static boolean isInner(long userIp, long begin, long end) {
        return userIp >= begin && userIp <= end;
    }
}
