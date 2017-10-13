//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.sax;

import org.jeecgframework.poi.excel.entity.enmus.CellValueType;

public class SaxReadCellEntity {
    private CellValueType cellType;
    private Object value;

    public SaxReadCellEntity(CellValueType cellType, Object value) {
        this.cellType = cellType;
        this.value = value;
    }

    public CellValueType getCellType() {
        return this.cellType;
    }

    public void setCellType(CellValueType cellType) {
        this.cellType = cellType;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        return "[type=" + this.cellType.toString() + ",value=" + this.value + "]";
    }
}
