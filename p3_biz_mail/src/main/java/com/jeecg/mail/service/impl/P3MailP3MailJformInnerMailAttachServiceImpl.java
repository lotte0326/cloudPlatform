//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service.impl;

import com.jeecg.mail.dao.P3MailJformInnerMailAttachDao;
import com.jeecg.mail.entity.P3MailJformInnerMailAttach;
import com.jeecg.mail.service.P3MailJformInnerMailAttachService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service("p3MailJformInnerMailAttachService")
public class P3MailP3MailJformInnerMailAttachServiceImpl implements P3MailJformInnerMailAttachService {
    @Resource
    private P3MailJformInnerMailAttachDao p3MailJformInnerMailAttachDao;

    public P3MailP3MailJformInnerMailAttachServiceImpl() {
    }

    public P3MailJformInnerMailAttach get(String id) {
        return this.p3MailJformInnerMailAttachDao.get(id);
    }

    public int update(P3MailJformInnerMailAttach p3MailJformInnerMailAttach) {
        return this.p3MailJformInnerMailAttachDao.update(p3MailJformInnerMailAttach);
    }

    public void insert(P3MailJformInnerMailAttach p3MailJformInnerMailAttach) {
        this.p3MailJformInnerMailAttachDao.insert(p3MailJformInnerMailAttach);
    }

    public MiniDaoPage<P3MailJformInnerMailAttach> getAll(P3MailJformInnerMailAttach p3MailJformInnerMailAttach, int page, int rows) {
        return this.p3MailJformInnerMailAttachDao.getAll(p3MailJformInnerMailAttach, page, rows);
    }

    public void delete(P3MailJformInnerMailAttach p3MailJformInnerMailAttach) {
        this.p3MailJformInnerMailAttachDao.delete(p3MailJformInnerMailAttach);
    }
}
