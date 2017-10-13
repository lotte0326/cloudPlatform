//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.auth.dao;

import com.jeecg.auth.entity.JwSystemAuth;
import java.util.List;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface JwSystemAuthDao {
    @Sql("SELECT * FROM jp_demo_auth WHERE ID = :id")
    JwSystemAuth get(@Param("id") Long var1);

    int update(@Param("auth") JwSystemAuth var1);

    void insert(@Param("auth") JwSystemAuth var1);

    @ResultType(JwSystemAuth.class)
    MiniDaoPage<JwSystemAuth> getAll(@Param("auth") JwSystemAuth var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_demo_auth WHERE ID = :auth.id")
    void delete(@Param("auth") JwSystemAuth var1);

    @ResultType(JwSystemAuth.class)
    List<JwSystemAuth> queryMenuAndFuncAuth();
}
