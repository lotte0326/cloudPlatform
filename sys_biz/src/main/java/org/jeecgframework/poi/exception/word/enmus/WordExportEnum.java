//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.exception.word.enmus;

public enum WordExportEnum {
    EXCEL_PARAMS_ERROR("Excel 导出 参数错误"),
    EXCEL_HEAD_HAVA_NULL("Excel 表头 有的字段为空"),
    EXCEL_NO_HEAD("Excel 没有表头");

    private String msg;

    private WordExportEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
