package com.jeecg.actInvite.dao;

import com.jeecg.actInvite.entity.WxActInvite;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface WxActInviteDao {
    @Sql("SELECT * FROM jp_demo_activity WHERE ID = :id")
    WxActInvite get(@Param("id") String var1);

    int update(@Param("act") WxActInvite var1);

    void insert(@Param("act") WxActInvite var1);

    @ResultType(WxActInvite.class)
    MiniDaoPage<WxActInvite> getAll(@Param("act") WxActInvite var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_demo_activity WHERE ID = :act.id")
    void delete(@Param("act") WxActInvite var1);
}
