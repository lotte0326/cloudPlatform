//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service;

import com.jeecg.mail.entity.P3MailJformInnerMail;
import com.jeecg.mail.entity.P3MailJformInnerMailReceiver;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface P3MailJformInnerMailReceiverService {
    P3MailJformInnerMailReceiver get(String var1);

    int update(P3MailJformInnerMailReceiver var1);

    void insert(P3MailJformInnerMailReceiver var1);

    MiniDaoPage<P3MailJformInnerMailReceiver> getAll(P3MailJformInnerMailReceiver var1, int var2, int var3);

    void delete(P3MailJformInnerMailReceiver var1);

    void deleteByMailId(P3MailJformInnerMail var1);

    MiniDaoPage<P3MailJformInnerMailReceiver> getByUserid(String var1, P3MailJformInnerMailReceiver var2, int var3, int var4);

    String getReceivedMailCountByUserid(String var1);

    String getDeletedMailCountByUserid(String var1);

    MiniDaoPage<P3MailJformInnerMailReceiver> getDeletedByUserid(String var1, P3MailJformInnerMailReceiver var2, int var3, int var4);
}
