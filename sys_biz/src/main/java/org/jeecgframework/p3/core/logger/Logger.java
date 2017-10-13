//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.logger;

import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.p3.core.utils.common.HttpUtil;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Marker;

public class Logger implements org.slf4j.Logger {
    private static final String LEFT_SQUARE_BRACKET = "[";
    private static final String RIGHT_SQUARE_BRACKET = "] ";
    private static final String LINE = "-";
    private final org.slf4j.Logger raw;
    private String prefix;

    public Logger(String prefix, org.slf4j.Logger rawLogger) {
        this.raw = rawLogger;
        this.prefix = prefix;
    }

    private String combineFormat(String format) {
        if(StringUtils.isBlank(this.prefix)) {
            return format;
        } else {
            if(format == null) {
                format = "";
            }

            return "[" + this.prefix + "] " + format;
        }
    }

    private String combineFormat(String func, String format) {
        if(StringUtils.isBlank(this.prefix)) {
            return "[" + func + "] " + format;
        } else {
            if(format == null) {
                format = "";
            }

            return "[" + this.prefix + "-" + func + "] " + format;
        }
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public org.slf4j.Logger getRawLogger() {
        return this.raw;
    }

    public String getName() {
        return this.raw.getName();
    }

    public boolean isTraceEnabled() {
        return this.raw.isTraceEnabled();
    }

    public void trace(String msg) {
        this.raw.trace(this.combineFormat(msg));
    }

    public void trace(String format, Object arg) {
        this.raw.trace(this.combineFormat(format), arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        this.raw.trace(this.combineFormat(format), arg1, arg2);
    }

    public void trace(String format, Object[] argArray) {
        this.raw.trace(this.combineFormat(format), argArray);
    }

    public void trace(String msg, Throwable t) {
        this.raw.trace(this.combineFormat(msg), t);
    }

    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    public void trace(Marker marker, String msg) {
        this.raw.trace(marker, this.combineFormat(msg));
    }

    public void trace(Marker marker, String format, Object arg) {
        this.raw.trace(marker, this.combineFormat(format), arg);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        this.raw.trace(marker, this.combineFormat(format), arg1, arg2);
    }

    public void trace(Marker marker, String format, Object[] argArray) {
        this.raw.trace(marker, this.combineFormat(format), argArray);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        this.raw.trace(marker, this.combineFormat(msg), t);
    }

    public boolean isDebugEnabled() {
        return this.raw.isDebugEnabled();
    }

    public void debug(String func, String msg) {
        this.raw.debug(this.combineFormat(func, msg));
    }

    public void debug(String func, String format, Object arg) {
        this.raw.debug(this.combineFormat(func, format), arg);
    }

    public void debug(String func, String format, Object arg1, Object arg2) {
        this.raw.debug(this.combineFormat(func, format), arg1, arg2);
    }

    public void debug(String func, String format, Object[] argArray) {
        this.raw.debug(this.combineFormat(func, format), argArray);
    }

    public void debug(String func, String msg, Throwable t) {
        this.raw.debug(this.combineFormat(func, msg), t);
    }

    public void debug(String msg) {
        this.raw.debug(this.combineFormat(msg));
    }

    public void debug(String format, Object arg) {
        this.raw.debug(this.combineFormat(format), arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        this.raw.debug(this.combineFormat(format), arg1, arg2);
    }

    public void debug(String format, Object[] argArray) {
        this.raw.debug(this.combineFormat(format), argArray);
    }

    public void debug(String msg, Throwable t) {
        this.raw.debug(this.combineFormat(msg), t);
    }

    public boolean isDebugEnabled(Marker marker) {
        return this.raw.isDebugEnabled(marker);
    }

    public void debug(Marker marker, String msg) {
        this.raw.debug(marker, this.combineFormat(msg));
    }

    public void debug(Marker marker, String format, Object arg) {
        this.raw.debug(marker, this.combineFormat(format), arg);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        this.raw.debug(marker, this.combineFormat(format), arg1, arg2);
    }

    public void debug(Marker marker, String format, Object[] argArray) {
        this.raw.debug(marker, this.combineFormat(format), argArray);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        this.raw.debug(marker, this.combineFormat(msg), t);
    }

    public boolean isInfoEnabled() {
        return this.raw.isInfoEnabled();
    }

    public void info(HttpServletRequest request, String func, String msg) {
        this.info(func + "-" + HttpUtil.getRemoteIp(request), msg);
    }

    public void info(HttpServletRequest request, String format, Object[] argArray) {
        this.info(HttpUtil.getRemoteIp(request), format, argArray);
    }

    public void info(HttpServletRequest request, String func, String format, Object[] argArray) {
        this.info(HttpUtil.getRemoteIp(request), format, argArray);
    }

    public void info(HttpServletRequest request, String func, String format, Object arg) {
        this.info(HttpUtil.getRemoteIp(request), format, arg);
    }

    public void info(HttpServletRequest request, String func, String format, Object arg1, Object arg2) {
        this.info(HttpUtil.getRemoteIp(request), format, arg1, arg2);
    }

    public void info(HttpServletRequest request, String msg) {
        this.info(HttpUtil.getRemoteIp(request), msg);
    }

    public void info(String func, String msg) {
        this.raw.info(this.combineFormat(func, msg));
    }

    public void info(String func, String format, Object arg) {
        this.raw.info(this.combineFormat(func, format), arg);
    }

    public void info(String func, String format, Object arg1, Object arg2) {
        this.raw.info(this.combineFormat(func, format), arg1, arg2);
    }

    public void info(String func, String format, Object[] argArray) {
        this.raw.info(this.combineFormat(func, format), argArray);
    }

    public void info(String msg) {
        this.raw.info(this.combineFormat(msg));
    }

    public void info(String format, Object arg) {
        this.raw.info(this.combineFormat(format), arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        this.raw.info(this.combineFormat(format), arg1, arg2);
    }

    public void info(String format, Object[] argArray) {
        this.raw.info(this.combineFormat(format), argArray);
    }

    public void info(String msg, Throwable t) {
        this.raw.info(this.combineFormat(msg), t);
    }

    public boolean isInfoEnabled(Marker marker) {
        return this.raw.isInfoEnabled(marker);
    }

    public void info(Marker marker, String msg) {
        this.raw.info(marker, this.combineFormat(msg));
    }

    public void info(Marker marker, String format, Object arg) {
        this.raw.info(marker, this.combineFormat(format), arg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        this.raw.info(marker, this.combineFormat(format), arg1, arg2);
    }

    public void info(Marker marker, String format, Object[] argArray) {
        this.raw.info(marker, this.combineFormat(format), argArray);
    }

    public void info(Marker marker, String msg, Throwable t) {
        this.raw.info(marker, this.combineFormat(msg), t);
    }

    public void warn(String func, String msg) {
        this.raw.warn(this.combineFormat(func, msg));
    }

    public void warn(String func, String format, Object arg) {
        this.raw.warn(this.combineFormat(func, format), arg);
    }

    public void warn(String func, String format, Object arg1, Object arg2) {
        this.raw.warn(this.combineFormat(func, format), arg1, arg2);
    }

    public void warn(String func, String format, Object[] argArray) {
        this.raw.warn(this.combineFormat(func, format), argArray);
    }

    public void warn(String func, String msg, Throwable t) {
        this.raw.warn(this.combineFormat(func, msg), t);
    }

    public boolean isWarnEnabled() {
        return this.raw.isWarnEnabled();
    }

    public void warn(String msg) {
        this.raw.warn(this.combineFormat(msg));
    }

    public void warn(String format, Object arg) {
        this.raw.warn(this.combineFormat(format), arg);
    }

    public void warn(String format, Object arg1, Object arg2) {
        this.raw.warn(this.combineFormat(format), arg1, arg2);
    }

    public void warn(String format, Object[] argArray) {
        this.raw.warn(this.combineFormat(format), argArray);
    }

    public void warn(String msg, Throwable t) {
        this.raw.warn(this.combineFormat(msg), t);
    }

    public boolean isWarnEnabled(Marker marker) {
        return this.raw.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
        this.raw.warn(marker, this.combineFormat(msg));
    }

    public void warn(Marker marker, String format, Object arg) {
        this.raw.warn(marker, this.combineFormat(format), arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        this.raw.warn(marker, this.combineFormat(format), arg1, arg2);
    }

    public void warn(Marker marker, String format, Object[] argArray) {
        this.raw.warn(marker, this.combineFormat(format), argArray);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        this.raw.warn(marker, this.combineFormat(msg), t);
    }

    public boolean isErrorEnabled() {
        return this.raw.isErrorEnabled();
    }

    public void error(String func, String msg) {
        this.raw.error(this.combineFormat(func, msg));
    }

    public void error(String func, String format, Object arg) {
        this.raw.error(this.combineFormat(func, format), arg);
    }

    public void error(String func, String format, Object arg1, Object arg2) {
        this.raw.error(this.combineFormat(func, format), arg1, arg2);
    }

    public void error(String func, String format, Object[] argArray) {
        this.raw.error(this.combineFormat(func, format), argArray);
    }

    public void error(String func, String msg, Throwable t) {
        this.raw.error(this.combineFormat(func, msg), t);
    }

    public void error(String msg) {
        this.raw.error(this.combineFormat(msg));
    }

    public void error(String format, Object arg) {
        this.raw.error(this.combineFormat(format), arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        this.raw.error(this.combineFormat(format), arg1, arg2);
    }

    public void error(String format, Object[] argArray) {
        this.raw.error(this.combineFormat(format), argArray);
    }

    public void error(String msg, Throwable t) {
        this.raw.error(this.combineFormat(msg), t);
    }

    public boolean isErrorEnabled(Marker marker) {
        return this.raw.isErrorEnabled(marker);
    }

    public void error(Marker marker, String msg) {
        this.raw.error(marker, this.combineFormat(msg));
    }

    public void error(Marker marker, String format, Object arg) {
        this.raw.error(marker, this.combineFormat(format), arg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        this.raw.error(marker, this.combineFormat(format), arg1, arg2);
    }

    public void error(Marker marker, String format, Object[] argArray) {
        this.raw.error(marker, this.combineFormat(format), argArray);
    }

    public void error(Marker marker, String msg, Throwable t) {
        this.raw.error(marker, this.combineFormat(msg), t);
    }
}
