//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.service.impl;

import com.jeecg.domo.order.dao.JpDemoOrderCustomDao;
import com.jeecg.domo.order.entity.JpDemoOrderCustomEntity;
import com.jeecg.domo.order.service.JpDemoOrderCustomService;
import java.util.List;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service
public class JpDemoOrderCustomServiceImpl implements JpDemoOrderCustomService {
    @Resource
    private JpDemoOrderCustomDao jpDemoOrderCustomDao;

    public JpDemoOrderCustomServiceImpl() {
    }

    public JpDemoOrderCustomEntity get(String id) {
        return this.jpDemoOrderCustomDao.get(id);
    }

    public int update(JpDemoOrderCustomEntity jpDemoOrderCustom) {
        return this.jpDemoOrderCustomDao.update(jpDemoOrderCustom);
    }

    public void insert(JpDemoOrderCustomEntity jpDemoOrderCustom) {
        this.jpDemoOrderCustomDao.insert(jpDemoOrderCustom);
    }

    public MiniDaoPage<JpDemoOrderCustomEntity> getAll(JpDemoOrderCustomEntity jpDemoOrderCustom, int page, int rows) {
        return this.jpDemoOrderCustomDao.getAll(jpDemoOrderCustom, page, rows);
    }

    public void delete(JpDemoOrderCustomEntity jpDemoOrderCustom) {
        this.jpDemoOrderCustomDao.delete(jpDemoOrderCustom);
    }

    public List<JpDemoOrderCustomEntity> getByOrderCode(String goOrderCode) {
        return this.jpDemoOrderCustomDao.getByOrderCode(goOrderCode);
    }

    public void delByOrderCode(String goOrderCode) {
        this.jpDemoOrderCustomDao.delByOrderCode(goOrderCode);
    }

    public void deleteByOrderCode(String goOrderCode) {
        this.jpDemoOrderCustomDao.deleteByOrderCode(goOrderCode);
    }

    public Integer getCountByOrderCode(String goOrderCode) {
        return this.jpDemoOrderCustomDao.getCountByOrderCode(goOrderCode);
    }
}
