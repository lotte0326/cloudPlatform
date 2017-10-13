//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.entity;

import java.io.Serializable;
import java.util.Date;

public class P3MailJformInnerMailReceiverEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDate;
    private Date updateDate;
    private String mailId;
    private String userId;
    private String status;
    private String title;
    private String sender;
    private String senderName;

    public P3MailJformInnerMailReceiverEntity() {
    }

    public static long getSerialVersionUID() {
        return 1L;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMailId() {
        return this.mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
