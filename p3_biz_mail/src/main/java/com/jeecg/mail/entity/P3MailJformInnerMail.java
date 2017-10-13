//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.entity;

import java.io.Serializable;
import java.util.Date;

public class P3MailJformInnerMail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String title;
    private String attachment;
    private String content;
    private String status;
    private String receiverNames;
    private String receiverIds;

    public P3MailJformInnerMail() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverNames() {
        return this.receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

    public String getReceiverIds() {
        return this.receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }
}
