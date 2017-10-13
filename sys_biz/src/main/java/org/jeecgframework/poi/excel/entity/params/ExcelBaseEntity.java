//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.params;

import java.lang.reflect.Method;
import java.util.List;

public class ExcelBaseEntity {
    protected String name;
    private int type = 1;
    private String databaseFormat;
    private String format;
    private String[] replace;
    private Method method;
    private List<Method> methods;

    public ExcelBaseEntity() {
    }

    public String getDatabaseFormat() {
        return this.databaseFormat;
    }

    public String getFormat() {
        return this.format;
    }

    public Method getMethod() {
        return this.method;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    public String getName() {
        return this.name;
    }

    public String[] getReplace() {
        return this.replace;
    }

    public int getType() {
        return this.type;
    }

    public void setDatabaseFormat(String databaseFormat) {
        this.databaseFormat = databaseFormat;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReplace(String[] replace) {
        this.replace = replace;
    }

    public void setType(int type) {
        this.type = type;
    }
}
