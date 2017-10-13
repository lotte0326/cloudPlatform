//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service.impl;

import com.jeecg.domo.order.dao.JpDemoOrderProductDao;
import com.jeecg.domo.order.entity.JpDemoOrderProductEntity;
import com.jeecg.domo.order.service.JpDemoOrderProductService;
import java.util.List;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service
public class JpDemoOrderProductServiceImpl implements JpDemoOrderProductService {
    @Resource
    private JpDemoOrderProductDao jpDemoOrderProductDao;

    public JpDemoOrderProductServiceImpl() {
    }

    public JpDemoOrderProductEntity get(String id) {
        return this.jpDemoOrderProductDao.get(id);
    }

    public int update(JpDemoOrderProductEntity jpDemoOrderProduct) {
        return this.jpDemoOrderProductDao.update(jpDemoOrderProduct);
    }

    public void insert(JpDemoOrderProductEntity jpDemoOrderProduct) {
        this.jpDemoOrderProductDao.insert(jpDemoOrderProduct);
    }

    public MiniDaoPage<JpDemoOrderProductEntity> getAll(JpDemoOrderProductEntity jpDemoOrderProduct, int page, int rows) {
        return this.jpDemoOrderProductDao.getAll(jpDemoOrderProduct, page, rows);
    }

    public void delete(JpDemoOrderProductEntity jpDemoOrderProduct) {
        this.jpDemoOrderProductDao.delete(jpDemoOrderProduct);
    }

    public List<JpDemoOrderProductEntity> getByOrderCode(String goOrderCode) {
        return this.jpDemoOrderProductDao.getByOrderCode(goOrderCode);
    }

    public void delbyOrderCode(String goOrderCode) {
        this.jpDemoOrderProductDao.delByOrderCode(goOrderCode);
    }

    public int getCountByOrderCode(String goOrderCode) {
        return this.jpDemoOrderProductDao.getCountByOrderCode(goOrderCode);
    }

    public void deleteByOrderCode(String goOrderCode) {
        this.jpDemoOrderProductDao.deleteByOrderCode(goOrderCode);
    }
}
