//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service;

import com.jeecg.chat.entity.SocketUser;

public interface IUserManager {
    boolean addUser(SocketUser var1);

    boolean removeUser(SocketUser var1);

    int getOnlineCount();

    SocketUser getUser(String var1);
}
