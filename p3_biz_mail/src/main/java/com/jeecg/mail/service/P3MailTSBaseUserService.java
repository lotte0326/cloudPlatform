//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service;

import com.jeecg.mail.entity.P3MailTSBaseUser;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface P3MailTSBaseUserService {
    P3MailTSBaseUser get(String var1);

    int update(P3MailTSBaseUser var1);

    void insert(P3MailTSBaseUser var1);

    MiniDaoPage<P3MailTSBaseUser> getAll(P3MailTSBaseUser var1, int var2, int var3);

    void delete(P3MailTSBaseUser var1);

    P3MailTSBaseUser getByUsername(String var1);
}
