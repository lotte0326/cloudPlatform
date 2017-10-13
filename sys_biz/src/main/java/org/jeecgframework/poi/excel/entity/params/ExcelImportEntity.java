//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.entity.params;

import java.util.List;
import org.jeecgframework.poi.excel.entity.params.ExcelBaseEntity;
import org.jeecgframework.poi.excel.entity.params.ExcelVerifyEntity;

public class ExcelImportEntity extends ExcelBaseEntity {
    private String collectionName;
    private String saveUrl;
    private int saveType;
    private String classType;
    private ExcelVerifyEntity verify;
    private String suffix;
    private List<ExcelImportEntity> list;

    public ExcelImportEntity() {
    }

    public String getClassType() {
        return this.classType;
    }

    public String getCollectionName() {
        return this.collectionName;
    }

    public List<ExcelImportEntity> getList() {
        return this.list;
    }

    public int getSaveType() {
        return this.saveType;
    }

    public String getSaveUrl() {
        return this.saveUrl;
    }

    public ExcelVerifyEntity getVerify() {
        return this.verify;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setList(List<ExcelImportEntity> list) {
        this.list = list;
    }

    public void setSaveType(int saveType) {
        this.saveType = saveType;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public void setVerify(ExcelVerifyEntity verify) {
        this.verify = verify;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
