//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.dao;

import com.jeecg.mail.entity.P3MailJformInnerMailAttach;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MailJformInnerMailAttachDao {
    @Sql("SELECT * FROM jp_inner_mail_attach WHERE ID = :id")
    P3MailJformInnerMailAttach get(@Param("id") String var1);

    int update(@Param("p3MailJformInnerMailAttach") P3MailJformInnerMailAttach var1);

    void insert(@Param("p3MailJformInnerMailAttach") P3MailJformInnerMailAttach var1);

    @ResultType(P3MailJformInnerMailAttach.class)
    MiniDaoPage<P3MailJformInnerMailAttach> getAll(@Param("p3MailJformInnerMailAttach") P3MailJformInnerMailAttach var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_inner_mail_attach WHERE ID = :p3MailJformInnerMailAttach.id")
    void delete(@Param("p3MailJformInnerMailAttach") P3MailJformInnerMailAttach var1);
}
