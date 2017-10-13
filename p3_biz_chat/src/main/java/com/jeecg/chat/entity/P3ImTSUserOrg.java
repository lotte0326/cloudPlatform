//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.entity;

import java.io.Serializable;

public class P3ImTSUserOrg implements Serializable {
    private String id;
    private String user_id;
    private String org_id;

    public P3ImTSUserOrg() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrg_id() {
        return this.org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }
}
