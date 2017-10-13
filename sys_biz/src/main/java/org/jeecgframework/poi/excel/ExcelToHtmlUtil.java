//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.html.ExcelToHtmlServer;

public final class ExcelToHtmlUtil {
    private ExcelToHtmlUtil() {
    }

    public static String toTableHtml(Workbook wb) {
        return (new ExcelToHtmlServer(wb, false, 0)).printPage();
    }

    public static String toTableHtml(Workbook wb, int sheetNum) {
        return (new ExcelToHtmlServer(wb, false, sheetNum)).printPage();
    }

    public static String toAllHtml(Workbook wb) {
        return (new ExcelToHtmlServer(wb, true, 0)).printPage();
    }

    public static String toAllHtml(Workbook wb, int sheetNum) {
        return (new ExcelToHtmlServer(wb, true, sheetNum)).printPage();
    }
}
