//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.params;

import java.util.List;
import org.jeecgframework.poi.excel.entity.params.ExcelBaseEntity;

public class ExcelExportEntity extends ExcelBaseEntity implements Comparable<ExcelExportEntity> {
    private Object key;
    private double width = 10.0D;
    private double height = 10.0D;
    private int exportImageType = 0;
    private int orderNum = 0;
    private boolean isWrap;
    private boolean needMerge;
    private boolean mergeVertical;
    private int[] mergeRely;
    private String suffix;
    private boolean isStatistics;
    private List<ExcelExportEntity> list;

    public ExcelExportEntity() {
    }

    public ExcelExportEntity(String name) {
        super.name = name;
    }

    public ExcelExportEntity(String name, Object key) {
        super.name = name;
        this.key = key;
    }

    public ExcelExportEntity(String name, Object key, int width) {
        super.name = name;
        this.width = (double)width;
        this.key = key;
    }

    public int getExportImageType() {
        return this.exportImageType;
    }

    public double getHeight() {
        return this.height;
    }

    public Object getKey() {
        return this.key;
    }

    public List<ExcelExportEntity> getList() {
        return this.list;
    }

    public int[] getMergeRely() {
        return this.mergeRely == null?new int[0]:this.mergeRely;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public double getWidth() {
        return this.width;
    }

    public boolean isMergeVertical() {
        return this.mergeVertical;
    }

    public boolean isNeedMerge() {
        return this.needMerge;
    }

    public boolean isWrap() {
        return this.isWrap;
    }

    public void setExportImageType(int exportImageType) {
        this.exportImageType = exportImageType;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setList(List<ExcelExportEntity> list) {
        this.list = list;
    }

    public void setMergeRely(int[] mergeRely) {
        this.mergeRely = mergeRely;
    }

    public void setMergeVertical(boolean mergeVertical) {
        this.mergeVertical = mergeVertical;
    }

    public void setNeedMerge(boolean needMerge) {
        this.needMerge = needMerge;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setWrap(boolean isWrap) {
        this.isWrap = isWrap;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isStatistics() {
        return this.isStatistics;
    }

    public void setStatistics(boolean isStatistics) {
        this.isStatistics = isStatistics;
    }

    public int compareTo(ExcelExportEntity prev) {
        return this.getOrderNum() - prev.getOrderNum();
    }
}
