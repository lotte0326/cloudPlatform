//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.datasource;

import java.util.Map;
import org.jeecgframework.minidao.datasource.DataSourceContextHolder;
import org.jeecgframework.minidao.datasource.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource() {
    }

    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();
        return dataSourceType;
    }

    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        super.setDataSourceLookup(dataSourceLookup);
    }

    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }
}
