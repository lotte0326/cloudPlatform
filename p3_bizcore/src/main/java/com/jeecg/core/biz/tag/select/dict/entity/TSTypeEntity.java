//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.tag.select.dict.entity;

import java.io.Serializable;

public class TSTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String typecode;
    private String typename;
    private String typepid;
    private String typegroupid;

    public TSTypeEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypecode() {
        return this.typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getTypename() {
        return this.typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypepid() {
        return this.typepid;
    }

    public void setTypepid(String typepid) {
        this.typepid = typepid;
    }

    public String getTypegroupid() {
        return this.typegroupid;
    }

    public void setTypegroupid(String typegroupid) {
        this.typegroupid = typegroupid;
    }
}
