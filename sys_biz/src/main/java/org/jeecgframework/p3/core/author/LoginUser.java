//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.author;

import java.io.Serializable;
import java.util.Date;

public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String signatureFile;
    private String mobilePhone;
    private String officePhone;
    private String email;
    private String userName;
    private String realName;
    private String browser;
    private String userKey;
    private String password;
    private Short activitiSync;
    private Short status;
    private Short deleteFlag;
    private byte[] signature;
    private String departid;
    private Date createDate;
    private String createBy;
    private String createName;
    private Date updateDate;
    private String updateBy;
    private String updateName;
    private String defaultWeixinAccountId;
    private String type;

    public LoginUser() {
    }

    public String getDefaultWeixinAccountId() {
        return this.defaultWeixinAccountId;
    }

    public void setDefaultWeixinAccountId(String defaultWeixinAccountId) {
        this.defaultWeixinAccountId = defaultWeixinAccountId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUserKey() {
        return this.userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getActivitiSync() {
        return this.activitiSync;
    }

    public void setActivitiSync(Short activitiSync) {
        this.activitiSync = activitiSync;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getDepartid() {
        return this.departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getSignatureFile() {
        return this.signatureFile;
    }

    public void setSignatureFile(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficePhone() {
        return this.officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return this.updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}
