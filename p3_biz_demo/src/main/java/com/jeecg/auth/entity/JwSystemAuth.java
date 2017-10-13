//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.auth.entity;

import java.io.Serializable;

public class JwSystemAuth implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String authId;
    private String authName;
    private String authType;
    private String authContr;
    private String parentAuthId;
    private String leafInd;

    public JwSystemAuth() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthId() {
        return this.authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return this.authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthType() {
        return this.authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthContr() {
        return this.authContr;
    }

    public void setAuthContr(String authContr) {
        this.authContr = authContr;
    }

    public String getParentAuthId() {
        return this.parentAuthId;
    }

    public void setParentAuthId(String parentAuthId) {
        this.parentAuthId = parentAuthId;
    }

    public String getLeafInd() {
        return this.leafInd;
    }

    public void setLeafInd(String leafInd) {
        this.leafInd = leafInd;
    }

    public String toString() {
        return "JwSystemAuth [id=" + this.id + ", authId=" + this.authId + ", authName=" + this.authName + ", authType=" + this.authType + ", authContr=" + this.authContr + ", parentAuthId=" + this.parentAuthId + ", leafInd=" + this.leafInd + "]";
    }
}
