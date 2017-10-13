//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.dao;

import java.util.Map;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

@Repository
public interface TSOperationDao {
    @Sql("SELECT OPERATIONCODE,OPERATIONTYPE FROM T_S_OPERATION WHERE ID = :id")
    Map<String, Object> getOperationCode(@Param("id") String var1);
}
