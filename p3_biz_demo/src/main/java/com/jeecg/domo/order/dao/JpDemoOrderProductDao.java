//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.dao;

import com.jeecg.domo.order.entity.JpDemoOrderProductEntity;
import java.util.List;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface JpDemoOrderProductDao {
    @Sql("SELECT * FROM jp_demo_order_product WHERE ID = :id")
    JpDemoOrderProductEntity get(@Param("id") String var1);

    int update(@Param("jpDemoOrderProduct") JpDemoOrderProductEntity var1);

    void insert(@Param("jpDemoOrderProduct") JpDemoOrderProductEntity var1);

    @ResultType(JpDemoOrderProductEntity.class)
    MiniDaoPage<JpDemoOrderProductEntity> getAll(@Param("jpDemoOrderProduct") JpDemoOrderProductEntity var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_demo_order_product WHERE ID = :jpDemoOrderProduct.id")
    void delete(@Param("jpDemoOrderProduct") JpDemoOrderProductEntity var1);

    @Sql("select * from jp_demo_order_product WHERE GO_ORDER_CODE = :goOrderCode and delflag = 0")
    List<JpDemoOrderProductEntity> getByOrderCode(@Param("goOrderCode") String var1);

    @Sql("update jp_demo_order_product set DELFLAG = 1, DEL_DT = now() where GO_ORDER_CODE = :goOrderCode")
    void delByOrderCode(@Param("goOrderCode") String var1);

    @Sql("select count(id) from jp_demo_order_product WHERE GO_ORDER_CODE = :goOrderCode")
    int getCountByOrderCode(@Param("goOrderCode") String var1);

    @Sql("delete from jp_demo_order_product WHERE GO_ORDER_CODE = :goOrderCode")
    void deleteByOrderCode(@Param("goOrderCode") String var1);
}
