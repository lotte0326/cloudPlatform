//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.handler.inter;

import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;

public interface IExcelVerifyHandler {
    String[] getNeedVerifyFields();

    void setNeedVerifyFields(String[] var1);

    ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3);
}
