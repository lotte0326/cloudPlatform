//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service;

import com.jeecg.domo.order.entity.JpDemoOrderCustomEntity;
import java.util.List;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface JpDemoOrderCustomService {
    JpDemoOrderCustomEntity get(String var1);

    int update(JpDemoOrderCustomEntity var1);

    void insert(JpDemoOrderCustomEntity var1);

    MiniDaoPage<JpDemoOrderCustomEntity> getAll(JpDemoOrderCustomEntity var1, int var2, int var3);

    void delete(JpDemoOrderCustomEntity var1);

    List<JpDemoOrderCustomEntity> getByOrderCode(String var1);

    void delByOrderCode(String var1);

    void deleteByOrderCode(String var1);

    Integer getCountByOrderCode(String var1);
}
