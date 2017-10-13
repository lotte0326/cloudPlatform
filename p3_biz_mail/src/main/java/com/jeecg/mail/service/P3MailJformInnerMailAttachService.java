//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service;

import com.jeecg.mail.entity.P3MailJformInnerMailAttach;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface P3MailJformInnerMailAttachService {
    P3MailJformInnerMailAttach get(String var1);

    int update(P3MailJformInnerMailAttach var1);

    void insert(P3MailJformInnerMailAttach var1);

    MiniDaoPage<P3MailJformInnerMailAttach> getAll(P3MailJformInnerMailAttach var1, int var2, int var3);

    void delete(P3MailJformInnerMailAttach var1);
}
