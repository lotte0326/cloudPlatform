//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg.pojo.onetomany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.p3.cg.ColumnData;

public class SubTableEntity {
    private String entityPackage;
    private String tableName;
    private String entityName;
    private String primaryKeyPolicy;
    private String sequenceCode;
    private String ftlDescription;
    private String foreignKey;
    private String mainForeignKey;
    private List<ColumnData> subColums;
    private Map<String, Object> paramData = new HashMap();

    public SubTableEntity() {
    }

    public String getEntityPackage() {
        return this.entityPackage;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String getFtlDescription() {
        return this.ftlDescription;
    }

    public List<ColumnData> getSubColums() {
        return this.subColums;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setFtlDescription(String ftlDescription) {
        this.ftlDescription = ftlDescription;
    }

    public void setSubColums(List<ColumnData> subColums) {
        this.subColums = subColums;
    }

    public String getForeignKey() {
        return this.foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getMainForeignKey() {
        return this.mainForeignKey;
    }

    public void setMainForeignKey(String mainForeignKey) {
        this.mainForeignKey = mainForeignKey;
    }

    public String getPrimaryKeyPolicy() {
        return this.primaryKeyPolicy;
    }

    public String getSequenceCode() {
        return this.sequenceCode;
    }

    public void setPrimaryKeyPolicy(String primaryKeyPolicy) {
        this.primaryKeyPolicy = primaryKeyPolicy;
    }

    public void setSequenceCode(String sequenceCode) {
        this.sequenceCode = sequenceCode;
    }

    public Map<String, Object> getParamData() {
        return this.paramData;
    }

    public void setParamData(Map<String, Object> paramData) {
        this.paramData = paramData;
    }
}
