//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.export.styler;

import org.apache.poi.ss.usermodel.CellStyle;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;

public interface IExcelExportStyler {
    CellStyle getHeaderStyle(short var1);

    CellStyle getTitleStyle(short var1);

    CellStyle getStyles(boolean var1, ExcelExportEntity var2);
}
