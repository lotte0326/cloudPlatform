//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.entity;

import java.io.Serializable;

public class P3MailTSBaseUser implements Serializable {
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
    private Integer deleteFlag;

    public P3MailTSBaseUser() {
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
