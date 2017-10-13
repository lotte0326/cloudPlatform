//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.entity;

import java.io.Serializable;

public class P3ImTSDepart implements Serializable {
    private String id;
    private String departname;
    private String description;
    private String orgCode;
    private String orgType;
    private String mobile;
    private String fax;
    private String address;
    private String parentdepartid;

    public P3ImTSDepart() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartname() {
        return this.departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return this.orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParentdepartid() {
        return this.parentdepartid;
    }

    public void setParentdepartid(String parentdepartid) {
        this.parentdepartid = parentdepartid;
    }
}
