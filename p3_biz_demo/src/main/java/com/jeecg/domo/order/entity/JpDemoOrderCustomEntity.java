//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.entity;

import java.io.Serializable;
import java.util.Date;

public class JpDemoOrderCustomEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDt;
    private String crtuser;
    private String crtuserName;
    private Date delDt;
    private Integer delflag;
    private String goOrderCode;
    private String gocBussContent;
    private String gocContent;
    private String gocCusName;
    private String gocIdcard;
    private String gocPassportCode;
    private String gocSex;
    private String modifier;
    private String modifierName;
    private Date modifyDt;

    public JpDemoOrderCustomEntity() {
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

    public String getGocBussContent() {
        return this.gocBussContent;
    }

    public void setGocBussContent(String gocBussContent) {
        this.gocBussContent = gocBussContent;
    }

    public String getGocContent() {
        return this.gocContent;
    }

    public void setGocContent(String gocContent) {
        this.gocContent = gocContent;
    }

    public String getGocCusName() {
        return this.gocCusName;
    }

    public void setGocCusName(String gocCusName) {
        this.gocCusName = gocCusName;
    }

    public String getGocIdcard() {
        return this.gocIdcard;
    }

    public void setGocIdcard(String gocIdcard) {
        this.gocIdcard = gocIdcard;
    }

    public String getGocPassportCode() {
        return this.gocPassportCode;
    }

    public void setGocPassportCode(String gocPassportCode) {
        this.gocPassportCode = gocPassportCode;
    }

    public String getGocSex() {
        return this.gocSex;
    }

    public void setGocSex(String gocSex) {
        this.gocSex = gocSex;
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
