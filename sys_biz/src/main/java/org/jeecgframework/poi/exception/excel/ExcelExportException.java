//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.exception.excel;

import org.jeecgframework.poi.exception.excel.enums.ExcelExportEnum;

public class ExcelExportException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ExcelExportEnum type;

    public ExcelExportException() {
    }

    public ExcelExportException(ExcelExportEnum type) {
        super(type.getMsg());
        this.type = type;
    }

    public ExcelExportException(ExcelExportEnum type, Throwable cause) {
        super(type.getMsg(), cause);
    }

    public ExcelExportException(String message) {
        super(message);
    }

    public ExcelExportException(String message, ExcelExportEnum type) {
        super(message);
        this.type = type;
    }

    public ExcelExportEnum getType() {
        return this.type;
    }

    public void setType(ExcelExportEnum type) {
        this.type = type;
    }
}
