//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.word.entity.params;

import java.util.List;
import org.jeecgframework.poi.excel.entity.ExcelBaseParams;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;

public class ExcelListEntity extends ExcelBaseParams {
    private List<?> list;
    private Class<?> clazz;
    private int headRows = 1;

    public ExcelListEntity() {
    }

    public ExcelListEntity(List<?> list, Class<?> clazz) {
        this.list = list;
        this.clazz = clazz;
    }

    public ExcelListEntity(List<?> list, Class<?> clazz, IExcelDataHandler dataHanlder) {
        this.list = list;
        this.clazz = clazz;
        this.setDataHanlder(dataHanlder);
    }

    public ExcelListEntity(List<?> list, Class<?> clazz, IExcelDataHandler dataHanlder, int headRows) {
        this.list = list;
        this.clazz = clazz;
        this.headRows = headRows;
        this.setDataHanlder(dataHanlder);
    }

    public ExcelListEntity(List<?> list, Class<?> clazz, int headRows) {
        this.list = list;
        this.clazz = clazz;
        this.headRows = headRows;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public int getHeadRows() {
        return this.headRows;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void setHeadRows(int headRows) {
        this.headRows = headRows;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
