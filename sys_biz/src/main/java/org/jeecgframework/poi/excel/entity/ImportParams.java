//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity;

import org.jeecgframework.poi.excel.entity.ExcelBaseParams;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;

public class ImportParams extends ExcelBaseParams {
    private int titleRows = 0;
    private int headRows = 1;
    private int startRows = 0;
    private int keyIndex = 0;
    private int sheetNum = 1;
    private boolean needSave = false;
    private String saveUrl = "upload/excelUpload";
    private IExcelVerifyHandler verifyHanlder;
    private int lastOfInvalidRow = 0;

    public ImportParams() {
    }

    public int getHeadRows() {
        return this.headRows;
    }

    public int getKeyIndex() {
        return this.keyIndex;
    }

    public String getSaveUrl() {
        return this.saveUrl;
    }

    public int getSheetNum() {
        return this.sheetNum;
    }

    public int getStartRows() {
        return this.startRows;
    }

    public int getTitleRows() {
        return this.titleRows;
    }

    public IExcelVerifyHandler getVerifyHanlder() {
        return this.verifyHanlder;
    }

    public boolean isNeedSave() {
        return this.needSave;
    }

    public void setHeadRows(int headRows) {
        this.headRows = headRows;
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public void setNeedSave(boolean needSave) {
        this.needSave = needSave;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public void setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
    }

    public void setStartRows(int startRows) {
        this.startRows = startRows;
    }

    public void setTitleRows(int titleRows) {
        this.titleRows = titleRows;
    }

    public void setVerifyHanlder(IExcelVerifyHandler verifyHanlder) {
        this.verifyHanlder = verifyHanlder;
    }

    public int getLastOfInvalidRow() {
        return this.lastOfInvalidRow;
    }

    public void setLastOfInvalidRow(int lastOfInvalidRow) {
        this.lastOfInvalidRow = lastOfInvalidRow;
    }
}
