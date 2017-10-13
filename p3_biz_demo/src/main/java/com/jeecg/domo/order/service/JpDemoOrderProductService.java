//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service;

import com.jeecg.domo.order.entity.JpDemoOrderProductEntity;
import java.util.List;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface JpDemoOrderProductService {
    JpDemoOrderProductEntity get(String var1);

    int update(JpDemoOrderProductEntity var1);

    void insert(JpDemoOrderProductEntity var1);

    MiniDaoPage<JpDemoOrderProductEntity> getAll(JpDemoOrderProductEntity var1, int var2, int var3);

    void delete(JpDemoOrderProductEntity var1);

    List<JpDemoOrderProductEntity> getByOrderCode(String var1);

    void delbyOrderCode(String var1);

    int getCountByOrderCode(String var1);

    void deleteByOrderCode(String var1);
}
