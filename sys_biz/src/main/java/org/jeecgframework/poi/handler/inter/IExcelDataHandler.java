//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.handler.inter;

import java.util.Map;

public interface IExcelDataHandler {
    Object exportHandler(Object var1, String var2, Object var3);

    String[] getNeedHandlerFields();

    Object importHandler(Object var1, String var2, Object var3);

    void setNeedHandlerFields(String[] var1);

    void setMapValue(Map<String, Object> var1, String var2, Object var3);
}
