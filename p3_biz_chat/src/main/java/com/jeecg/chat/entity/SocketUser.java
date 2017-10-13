//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.entity;

import javax.websocket.Session;

public class SocketUser {
    private Session session;
    private String userId;

    public SocketUser() {
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserId() {
        return this.userId;
    }

    public boolean isExist() {
        return this.userId != null;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return this.session.getId() + "_" + this.userId;
    }
}
