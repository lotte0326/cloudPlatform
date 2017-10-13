//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.datasource;

import org.jeecgframework.minidao.datasource.DataSourceType;

public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal();

    public DataSourceContextHolder() {
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static DataSourceType getDataSourceType() {
        return (DataSourceType)contextHolder.get();
    }

    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }
}
