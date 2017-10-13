//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.params;

import java.util.List;

public class MergeEntity {
    private int startRow;
    private int endRow;
    private String text;
    private List<String> relyList;

    public MergeEntity() {
    }

    public MergeEntity(String text, int startRow, int endRow) {
        this.text = text;
        this.endRow = endRow;
        this.startRow = startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public List<String> getRelyList() {
        return this.relyList;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public String getText() {
        return this.text;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setRelyList(List<String> relyList) {
        this.relyList = relyList;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setText(String text) {
        this.text = text;
    }
}
