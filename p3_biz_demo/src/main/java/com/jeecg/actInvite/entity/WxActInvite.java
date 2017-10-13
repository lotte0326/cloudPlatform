//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.actInvite.entity;

import java.io.Serializable;
import java.util.Date;

public class WxActInvite implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Date beginTime;
    private Date endTime;
    private String hdurl;

    public WxActInvite() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getHdurl() {
        return this.hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }
}
