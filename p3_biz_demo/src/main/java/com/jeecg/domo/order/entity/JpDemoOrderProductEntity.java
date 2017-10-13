//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JpDemoOrderProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDt;
    private String crtuser;
    private String crtuserName;
    private Date delDt;
    private Integer delflag;
    private String goOrderCode;
    private String gopContent;
    private Integer gopCount;
    private BigDecimal gopOnePrice;
    private String gopProductName;
    private String gopProductType;
    private BigDecimal gopSumPrice;
    private String modifier;
    private String modifierName;
    private Date modifyDt;

    public JpDemoOrderProductEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDt() {
        return this.createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCrtuser() {
        return this.crtuser;
    }

    public void setCrtuser(String crtuser) {
        this.crtuser = crtuser;
    }

    public String getCrtuserName() {
        return this.crtuserName;
    }

    public void setCrtuserName(String crtuserName) {
        this.crtuserName = crtuserName;
    }

    public Date getDelDt() {
        return this.delDt;
    }

    public void setDelDt(Date delDt) {
        this.delDt = delDt;
    }

    public Integer getDelflag() {
        return this.delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getGoOrderCode() {
        return this.goOrderCode;
    }

    public void setGoOrderCode(String goOrderCode) {
        this.goOrderCode = goOrderCode;
    }

    public String getGopContent() {
        return this.gopContent;
    }

    public void setGopContent(String gopContent) {
        this.gopContent = gopContent;
    }

    public Integer getGopCount() {
        return this.gopCount;
    }

    public void setGopCount(Integer gopCount) {
        this.gopCount = gopCount;
    }

    public BigDecimal getGopOnePrice() {
        return this.gopOnePrice;
    }

    public void setGopOnePrice(BigDecimal gopOnePrice) {
        this.gopOnePrice = gopOnePrice;
    }

    public String getGopProductName() {
        return this.gopProductName;
    }

    public void setGopProductName(String gopProductName) {
        this.gopProductName = gopProductName;
    }

    public String getGopProductType() {
        return this.gopProductType;
    }

    public void setGopProductType(String gopProductType) {
        this.gopProductType = gopProductType;
    }

    public BigDecimal getGopSumPrice() {
        return this.gopSumPrice;
    }

    public void setGopSumPrice(BigDecimal gopSumPrice) {
        this.gopSumPrice = gopSumPrice;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierName() {
        return this.modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Date getModifyDt() {
        return this.modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }
}
