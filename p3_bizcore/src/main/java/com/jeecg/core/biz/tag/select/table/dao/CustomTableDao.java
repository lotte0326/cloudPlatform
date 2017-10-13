//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.tag.select.table.dao;

import java.util.Map;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomTableDao {
    @ResultType(Map.class)
    MiniDaoPage<Map<String, String>> getCustomTable(@Param("customTable") String var1);
}
