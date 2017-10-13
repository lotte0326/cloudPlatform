//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.exception.excel.enums;

public enum ExcelImportEnum {
    GET_VALUE_ERROR("Excel 值获取失败"),
    VERIFY_ERROR("值校验失败");

    private String msg;

    private ExcelImportEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
