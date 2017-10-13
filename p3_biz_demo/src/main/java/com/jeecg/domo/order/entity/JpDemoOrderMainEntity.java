//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class JpDemoOrderMainEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDt;
    private String crtuser;
    private String crtuserName;
    private Date delDt;
    private Integer delflag;
    private BigDecimal goAllPrice;
    private String goContactName;
    private String goContent;
    private String goOrderCode;
    private Integer goOrderCount;
    private BigDecimal goReturnPrice;
    private String goTelphone;
    private String goderType;
    private String modifier;
    private String modifierName;
    private Date modifyDt;
    private String usertype;

    public JpDemoOrderMainEntity() {
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

    public BigDecimal getGoAllPrice() {
        return this.goAllPrice;
    }

    public void setGoAllPrice(BigDecimal goAllPrice) {
        this.goAllPrice = goAllPrice;
    }

    public String getGoContactName() {
        return this.goContactName;
    }

    public void setGoContactName(String goContactName) {
        this.goContactName = goContactName;
    }

    public String getGoContent() {
        return this.goContent;
    }

    public void setGoContent(String goContent) {
        this.goContent = goContent;
    }

    public String getGoOrderCode() {
        return this.goOrderCode;
    }

    public void setGoOrderCode(String goOrderCode) {
        this.goOrderCode = goOrderCode;
    }

    public Integer getGoOrderCount() {
        return this.goOrderCount;
    }

    public void setGoOrderCount(Integer goOrderCount) {
        this.goOrderCount = goOrderCount;
    }

    public BigDecimal getGoReturnPrice() {
        return this.goReturnPrice;
    }

    public void setGoReturnPrice(BigDecimal goReturnPrice) {
        this.goReturnPrice = goReturnPrice;
    }

    public String getGoTelphone() {
        return this.goTelphone;
    }

    public void setGoTelphone(String goTelphone) {
        this.goTelphone = goTelphone;
    }

    public String getGoderType() {
        return this.goderType;
    }

    public void setGoderType(String goderType) {
        this.goderType = goderType;
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

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
