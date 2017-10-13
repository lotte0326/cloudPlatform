//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service.impl;

import com.jeecg.domo.order.dao.JpDemoOrderMainDao;
import com.jeecg.domo.order.entity.JpDemoOrderMainEntity;
import com.jeecg.domo.order.service.JpDemoOrderMainService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service
public class JpDemoOrderMainServiceImpl implements JpDemoOrderMainService {
    @Resource
    private JpDemoOrderMainDao jpDemoOrderMainDao;

    public JpDemoOrderMainServiceImpl() {
    }

    public JpDemoOrderMainEntity get(String id) {
        return this.jpDemoOrderMainDao.get(id);
    }

    public int update(JpDemoOrderMainEntity jpDemoOrderMain) {
        return this.jpDemoOrderMainDao.update(jpDemoOrderMain);
    }

    public void insert(JpDemoOrderMainEntity jpDemoOrderMain) {
        this.jpDemoOrderMainDao.insert(jpDemoOrderMain);
    }

    public MiniDaoPage<JpDemoOrderMainEntity> getAll(JpDemoOrderMainEntity jpDemoOrderMain, int page, int rows) {
        return this.jpDemoOrderMainDao.getAll(jpDemoOrderMain, page, rows);
    }

    public void delete(JpDemoOrderMainEntity jpDemoOrderMain) {
        this.jpDemoOrderMainDao.delete(jpDemoOrderMain);
    }

    public JpDemoOrderMainEntity getByOrderCode(String orderCode) {
        return this.jpDemoOrderMainDao.getByOrderCode(orderCode);
    }
}
