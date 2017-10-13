//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service;

import com.jeecg.mail.entity.P3MailJformInnerMail;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface P3MailJformInnerMailService {
    P3MailJformInnerMail get(String var1);

    int update(P3MailJformInnerMail var1);

    void insert(P3MailJformInnerMail var1);

    MiniDaoPage<P3MailJformInnerMail> getAll(P3MailJformInnerMail var1, int var2, int var3);

    void delete(P3MailJformInnerMail var1);

    P3MailJformInnerMail getByReceivedId(String var1);

    MiniDaoPage<P3MailJformInnerMail> getUnSendMailByCreateBy(String var1, P3MailJformInnerMail var2, int var3, int var4);

    MiniDaoPage<P3MailJformInnerMail> getSendedMailByCreateBy(String var1, P3MailJformInnerMail var2, int var3, int var4);

    String getSendedMailCountByCreateBy(String var1);

    String getUnSendedMailCountByCreateBy(String var1);
}
