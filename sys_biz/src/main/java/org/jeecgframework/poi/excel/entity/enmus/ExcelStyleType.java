//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.enmus;

import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerBorderImpl;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerColorImpl;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerDefaultImpl;

public enum ExcelStyleType {
    NONE("默认样式", ExcelExportStylerDefaultImpl.class),
    BORDER("边框样式", ExcelExportStylerBorderImpl.class),
    COLOR("间隔行样式", ExcelExportStylerColorImpl.class);

    private String name;
    private Class<?> clazz;

    private ExcelStyleType(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public String getName() {
        return this.name;
    }
}
