//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.entity;

import java.io.Serializable;

public class P3ImTSBaseUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Integer activitisync;
    private String browser;
    private String password;
    private String realname;
    private Object signature;
    private Integer status;
    private String userkey;
    private String username;
    private String departid;
    private String maindepartid;
    private String portrait;
    private String imsign;
    private Integer deleteFlag;

    public P3ImTSBaseUser() {
    }

    public String getImsign() {
        return this.imsign;
    }

    public void setImsign(String imsign) {
        this.imsign = imsign;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getMaindepartid() {
        return this.maindepartid;
    }

    public void setMaindepartid(String maindepartid) {
        this.maindepartid = maindepartid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getActivitisync() {
        return this.activitisync;
    }

    public void setActivitisync(Integer activitisync) {
        this.activitisync = activitisync;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Object getSignature() {
        return this.signature;
    }

    public void setSignature(Object signature) {
        this.signature = signature;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserkey() {
        return this.userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartid() {
        return this.departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public Integer getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
