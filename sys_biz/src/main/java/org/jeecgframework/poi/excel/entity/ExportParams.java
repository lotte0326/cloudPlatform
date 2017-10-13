//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity;

import org.jeecgframework.poi.excel.entity.ExcelBaseParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerDefaultImpl;

public class ExportParams extends ExcelBaseParams {
    private String title;
    private short titleHeight = 10;
    private String secondTitle;
    private short secondTitleHeight = 8;
    private String sheetName;
    private String[] exclusions;
    private boolean addIndex;
    private String indexName = "序号";
    private int freezeCol;
    private short color = 9;
    private short headerColor = 40;
    private ExcelType type;
    private Class<?> style;
    private boolean isCreateHeadRows;

    public ExportParams() {
        this.type = ExcelType.HSSF;
        this.style = ExcelExportStylerDefaultImpl.class;
        this.isCreateHeadRows = true;
    }

    public ExportParams(String title, String sheetName) {
        this.type = ExcelType.HSSF;
        this.style = ExcelExportStylerDefaultImpl.class;
        this.isCreateHeadRows = true;
        this.title = title;
        this.sheetName = sheetName;
    }

    public ExportParams(String title, String sheetName, ExcelType type) {
        this.type = ExcelType.HSSF;
        this.style = ExcelExportStylerDefaultImpl.class;
        this.isCreateHeadRows = true;
        this.title = title;
        this.sheetName = sheetName;
        this.type = type;
    }

    public ExportParams(String title, String secondTitle, String sheetName) {
        this.type = ExcelType.HSSF;
        this.style = ExcelExportStylerDefaultImpl.class;
        this.isCreateHeadRows = true;
        this.title = title;
        this.secondTitle = secondTitle;
        this.sheetName = sheetName;
    }

    public short getColor() {
        return this.color;
    }

    public String[] getExclusions() {
        return this.exclusions;
    }

    public short getHeaderColor() {
        return this.headerColor;
    }

    public String getSecondTitle() {
        return this.secondTitle;
    }

    public short getSecondTitleHeight() {
        return (short)(this.secondTitleHeight * 50);
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public String getTitle() {
        return this.title;
    }

    public short getTitleHeight() {
        return (short)(this.titleHeight * 50);
    }

    public boolean isAddIndex() {
        return this.addIndex;
    }

    public void setAddIndex(boolean addIndex) {
        this.addIndex = addIndex;
    }

    public void setColor(short color) {
        this.color = color;
    }

    public void setExclusions(String[] exclusions) {
        this.exclusions = exclusions;
    }

    public void setHeaderColor(short headerColor) {
        this.headerColor = headerColor;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public void setSecondTitleHeight(short secondTitleHeight) {
        this.secondTitleHeight = secondTitleHeight;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleHeight(short titleHeight) {
        this.titleHeight = titleHeight;
    }

    public ExcelType getType() {
        return this.type;
    }

    public void setType(ExcelType type) {
        this.type = type;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Class<?> getStyle() {
        return this.style;
    }

    public void setStyle(Class<?> style) {
        this.style = style;
    }

    public int getFreezeCol() {
        return this.freezeCol;
    }

    public void setFreezeCol(int freezeCol) {
        this.freezeCol = freezeCol;
    }

    public boolean isCreateHeadRows() {
        return this.isCreateHeadRows;
    }

    public void setCreateHeadRows(boolean isCreateHeadRows) {
        this.isCreateHeadRows = isCreateHeadRows;
    }
}
