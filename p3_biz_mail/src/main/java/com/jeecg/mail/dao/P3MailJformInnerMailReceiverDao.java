//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.dao;

import com.jeecg.mail.entity.P3MailJformInnerMailReceiver;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MailJformInnerMailReceiverDao {
    @Sql("SELECT * FROM jp_inner_mail_receiver WHERE ID = :id")
    P3MailJformInnerMailReceiver get(@Param("id") String var1);

    int update(@Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var1);

    void insert(@Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var1);

    @ResultType(P3MailJformInnerMailReceiver.class)
    MiniDaoPage<P3MailJformInnerMailReceiver> getAll(@Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_inner_mail_receiver WHERE ID = :p3MailJformInnerMailReceiver.id")
    void delete(@Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var1);

    @Sql("DELETE from jp_inner_mail_receiver WHERE mail_id = :id")
    void deleteByMainId(@Param("id") String var1);

    @ResultType(P3MailJformInnerMailReceiver.class)
    @Sql("select r.* from jp_inner_mail_receiver r,jp_inner_mail m where r.user_id = :userid and r.mail_id = m.id and r.isdelete = \'0\' and m.status != \'00\' order by m.create_date desc")
    MiniDaoPage<P3MailJformInnerMailReceiver> getByUserid(@Param("userid") String var1, @Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var2, @Param("page") int var3, @Param("rows") int var4);

    @ResultType(String.class)
    @Sql("select count(*) from jp_inner_mail_receiver r,jp_inner_mail m where r.user_id = :userid and r.mail_id = m.id and m.status != \'00\' and r.isdelete = \'0\'")
    String getReceivedMailCountByUserid(@Param("userid") String var1);

    @ResultType(String.class)
    @Sql("select count(*) from jp_inner_mail_receiver r,jp_inner_mail m where r.user_id = :userid and r.mail_id = m.id and m.status != \'00\' and r.isdelete = \'1\'")
    String getDeletedMailCountByUserid(@Param("userid") String var1);

    @ResultType(P3MailJformInnerMailReceiver.class)
    @Sql("select r.* from jp_inner_mail_receiver r,jp_inner_mail m where r.user_id = :userid and r.mail_id = m.id and r.isdelete = \'1\' and m.status != \'00\' order by m.create_date desc")
    MiniDaoPage<P3MailJformInnerMailReceiver> getDeletedMailByUserid(@Param("userid") String var1, @Param("p3MailJformInnerMailReceiver") P3MailJformInnerMailReceiver var2, @Param("page") int var3, @Param("rows") int var4);
}
