package com.jeecg.mail.dao;

import com.jeecg.mail.entity.P3MailTSBaseUser;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MailTSBaseUserDao {
    @Sql("SELECT * FROM t_s_base_user WHERE ID = :id")
    P3MailTSBaseUser get(@Param("id") String var1);

    int update(@Param("tSBaseUserP3Mail") P3MailTSBaseUser var1);

    void insert(@Param("tSBaseUserP3Mail") P3MailTSBaseUser var1);

    @ResultType(P3MailTSBaseUser.class)
    MiniDaoPage<P3MailTSBaseUser> getAll(@Param("tSBaseUserP3Mail") P3MailTSBaseUser var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from t_s_base_user WHERE ID = :tSBaseUserP3Mail.id")
    void delete(@Param("tSBaseUserP3Mail") P3MailTSBaseUser var1);

    @Sql("SELECT * FROM t_s_base_user WHERE username = :username")
    P3MailTSBaseUser getByUsername(@Param("username") String var1);
}
