//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service.impl;

import com.jeecg.chat.entity.SocketUser;
import com.jeecg.chat.service.IUserManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserManager implements IUserManager {
    Log log = LogFactory.getLog(this.getClass());
    private static Map<String, SocketUser> socketUserMap;
    private static UserManager manager = new UserManager();

    private UserManager() {
        socketUserMap = new ConcurrentHashMap();
    }

    public static IUserManager getInstance() {
        return manager;
    }

    public boolean addUser(SocketUser user) {
        this.removeUser(user.getUserId());
        socketUserMap.put(user.getUserId(), user);
        return true;
    }

    public boolean removeUser(SocketUser user) {
        return user != null?this.removeUser(user.getUserId()):false;
    }

    public int getOnlineCount() {
        return socketUserMap.size();
    }

    public SocketUser getUser(String userId) {
        return socketUserMap.containsKey(userId)?(SocketUser)socketUserMap.get(userId):new SocketUser();
    }

    private boolean removeUser(String sessionUserId) {
        try {
            socketUserMap.remove(sessionUserId);
        } catch (Exception var3) {
            this.log.info("id为" + sessionUserId + "用户已经被移除了");
        }

        return true;
    }
}
