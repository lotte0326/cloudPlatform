//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.exception;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class ActBizException extends RuntimeException {
    private static final long serialVersionUID = -5097768787801034398L;
    protected String id;
    protected String message;
    protected String defineCode;
    protected String realClassName;

    protected ActBizException(String defineCode) {
        this.defineCode = defineCode;
        this.initId();
    }

    protected ActBizException(String defineCode, String message) {
        this.defineCode = defineCode;
        this.message = message;
        this.initId();
    }

    protected ActBizException(String defineCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.defineCode = defineCode;
        this.message = errorMessage;
        this.initId();
    }

    private void initId() {
        this.id = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message, Object... args) {
        this.message = MessageFormat.format(message, args);
    }

    public String getDefineCode() {
        return this.defineCode;
    }

    public static <T extends ActBizException> T newException(T exception, String message, Object... args) {
        if(exception == null) {
            throw new RuntimeException("no exception instance specified");
        } else {
            try {
                Constructor e = exception.getClass().getDeclaredConstructor(new Class[]{String.class});
                e.setAccessible(true);
                ActBizException newException = (ActBizException)e.newInstance(new Object[]{exception.getDefineCode()});
                newException.setMessage(message, args);
                return (T)newException;
            } catch (Throwable var5) {
                throw new RuntimeException("create exception instance fail : " + var5.getMessage(), var5);
            }
        }
    }

    public boolean codeEquals(ActBizException e) {
        return e == null?false:(!e.getClass().equals(this.getClass())?false:e.getDefineCode().equals(this.getDefineCode()));
    }

    public ActBizException upcasting() {
        if(this.getClass().equals(ActBizException.class)) {
            return this;
        } else {
            ActBizException superexception = new ActBizException(this.defineCode);
            superexception.message = this.message;
            superexception.realClassName = this.getClass().getName();
            superexception.id = this.id;
            superexception.setStackTrace(this.getStackTrace());
            return superexception;
        }
    }

    public ActBizException downcasting() {
        if(this.realClassName != null && !ActBizException.class.getName().equals(this.realClassName)) {
            Class clz = null;

            try {
                clz = Class.forName(this.realClassName);
            } catch (Exception var5) {
                ;
            }

            if(clz == null) {
                return this;
            } else {
                try {
                    Constructor e = clz.getDeclaredConstructor(new Class[]{String.class});
                    e.setAccessible(true);
                    ActBizException newException = (ActBizException)e.newInstance(new Object[]{this.defineCode});
                    newException.message = this.message;
                    newException.id = this.id;
                    newException.setStackTrace(this.getStackTrace());
                    return newException;
                } catch (Throwable var4) {
                    return this;
                }
            }
        } else {
            return this;
        }
    }

    public String getRealClassName() {
        return this.realClassName == null?this.getClass().getName():this.realClassName;
    }

    public StackTraceElement[] getCoreStackTrace() {
        ArrayList list = new ArrayList();
        StackTraceElement[] var5;
        int var4 = (var5 = this.getStackTrace()).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            StackTraceElement stackTrace = var5[var3];
            if(stackTrace.getClassName().startsWith("com.qunar")) {
                list.add(stackTrace);
            }
        }

        StackTraceElement[] var6 = new StackTraceElement[list.size()];
        return (StackTraceElement[])list.toArray(var6);
    }

    public String getCoreStackTraceStr() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] var5;
        int var4 = (var5 = this.getCoreStackTrace()).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            StackTraceElement traceEle = var5[var3];
            sb.append("\n" + traceEle.toString());
        }

        return sb.toString();
    }
}
