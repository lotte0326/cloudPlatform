package com.jeecg.mail.dao;

import com.jeecg.mail.entity.P3MailTSUser;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MailTSUserDao {
    @Sql("SELECT * FROM t_s_user WHERE ID = :id")
    P3MailTSUser get(@Param("id") String var1);

    int update(@Param("tSUserP3Mail") P3MailTSUser var1);

    void insert(@Param("tSUserP3Mail") P3MailTSUser var1);

    @ResultType(P3MailTSUser.class)
    MiniDaoPage<P3MailTSUser> getAll(@Param("tSUserP3Mail") P3MailTSUser var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from t_s_user WHERE ID = :tSUserP3Mail.id")
    void delete(@Param("tSUserP3Mail") P3MailTSUser var1);
}
