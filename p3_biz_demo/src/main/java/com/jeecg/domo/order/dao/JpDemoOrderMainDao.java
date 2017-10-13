//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.dao;

import com.jeecg.domo.order.entity.JpDemoOrderMainEntity;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface JpDemoOrderMainDao {
    @Sql("SELECT * FROM jp_demo_order_main WHERE ID = :id")
    JpDemoOrderMainEntity get(@Param("id") String var1);

    int update(@Param("jpDemoOrderMain") JpDemoOrderMainEntity var1);

    void insert(@Param("jpDemoOrderMain") JpDemoOrderMainEntity var1);

    @ResultType(JpDemoOrderMainEntity.class)
    MiniDaoPage<JpDemoOrderMainEntity> getAll(@Param("jpDemoOrderMain") JpDemoOrderMainEntity var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_demo_order_main WHERE ID = :jpDemoOrderMain.id")
    void delete(@Param("jpDemoOrderMain") JpDemoOrderMainEntity var1);

    @Sql("select * from jp_demo_order_main WHERE GO_ORDER_CODE = :orderCode")
    JpDemoOrderMainEntity getByOrderCode(@Param("orderCode") String var1);
}
