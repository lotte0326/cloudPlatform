//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service;

import com.jeecg.domo.order.entity.JpDemoOrderMainEntity;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface JpDemoOrderMainService {
    JpDemoOrderMainEntity get(String var1);

    int update(JpDemoOrderMainEntity var1);

    void insert(JpDemoOrderMainEntity var1);

    MiniDaoPage<JpDemoOrderMainEntity> getAll(JpDemoOrderMainEntity var1, int var2, int var3);

    void delete(JpDemoOrderMainEntity var1);

    JpDemoOrderMainEntity getByOrderCode(String var1);
}
