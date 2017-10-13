//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.tag.select.dict.dao;

import com.jeecg.core.biz.tag.select.dict.entity.TSTypeEntity;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface TSTypeDao {
    @ResultType(TSTypeEntity.class)
    @Sql("select * from t_s_type where typegroupid in (select id from t_s_typegroup where typegroupcode = :typeGroupCode)")
    MiniDaoPage<TSTypeEntity> getTypeByTypeGroupCode(@Param("typeGroupCode") String var1);
}
