//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.dao;

import com.jeecg.mail.entity.P3MailJformInnerMail;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MailJformInnerMailDao {
    @Sql("SELECT * FROM jp_inner_mail WHERE ID = :id")
    P3MailJformInnerMail get(@Param("id") String var1);

    int update(@Param("p3MailJformInnerMail") P3MailJformInnerMail var1);

    void insert(@Param("p3MailJformInnerMail") P3MailJformInnerMail var1);

    @ResultType(P3MailJformInnerMail.class)
    MiniDaoPage<P3MailJformInnerMail> getAll(@Param("p3MailJformInnerMail") P3MailJformInnerMail var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_inner_mail WHERE ID = :p3MailJformInnerMail.id")
    void delete(@Param("p3MailJformInnerMail") P3MailJformInnerMail var1);

    @ResultType(P3MailJformInnerMail.class)
    @Sql("select m.* from jp_inner_mail m,jp_inner_mail_receiver r where m.id = r.mail_id and r.id = :id")
    P3MailJformInnerMail getByReceivedId(@Param("id") String var1);

    @ResultType(P3MailJformInnerMail.class)
    @Sql("select * from jp_inner_mail where status = \'00\' and create_by = :createBy")
    MiniDaoPage<P3MailJformInnerMail> getUnSendMailByCreateBy(@Param("createBy") String var1, @Param("p3MailJformInnerMail") P3MailJformInnerMail var2, @Param("page") int var3, @Param("rows") int var4);

    @ResultType(P3MailJformInnerMail.class)
    @Sql("select * from jp_inner_mail where status = \'01\' and create_by = :createBy order by create_date desc")
    MiniDaoPage<P3MailJformInnerMail> getSendedMailByCreateBy(@Param("createBy") String var1, @Param("p3MailJformInnerMail") P3MailJformInnerMail var2, @Param("page") int var3, @Param("rows") int var4);

    @ResultType(String.class)
    @Sql("select count(*) from jp_inner_mail where status = \'01\' and create_by = :createBy")
    String getSendedMailCountByCreateBy(@Param("createBy") String var1);

    @ResultType(String.class)
    @Sql("select count(*) from jp_inner_mail where status = \'00\' and create_by = :createBy")
    String getUnSendedMailCountByCreateBy(@Param("createBy") String var1);
}
