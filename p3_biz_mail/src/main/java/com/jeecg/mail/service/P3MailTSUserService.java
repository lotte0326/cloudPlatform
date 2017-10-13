//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service;

import com.jeecg.mail.entity.P3MailTSUser;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface P3MailTSUserService {
    P3MailTSUser get(String var1);

    int update(P3MailTSUser var1);

    void insert(P3MailTSUser var1);

    MiniDaoPage<P3MailTSUser> getAll(P3MailTSUser var1, int var2, int var3);

    void delete(P3MailTSUser var1);
}
