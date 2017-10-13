//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.handler.impl;

import java.util.Map;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;

public abstract class ExcelDataHandlerDefaultImpl implements IExcelDataHandler {
    private String[] needHandlerFields;

    public ExcelDataHandlerDefaultImpl() {
    }

    public Object exportHandler(Object obj, String name, Object value) {
        return value;
    }

    public String[] getNeedHandlerFields() {
        return this.needHandlerFields;
    }

    public Object importHandler(Object obj, String name, Object value) {
        return value;
    }

    public void setNeedHandlerFields(String[] needHandlerFields) {
        this.needHandlerFields = needHandlerFields;
    }

    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        map.put(originKey, value);
    }
}
