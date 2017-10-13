//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service.impl;

import com.jeecg.mail.dao.P3MailJformInnerMailDao;
import com.jeecg.mail.entity.P3MailJformInnerMail;
import com.jeecg.mail.service.P3MailJformInnerMailService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service("p3MailJformInnerMailService")
public class P3MailP3MailJformInnerMailServiceImpl implements P3MailJformInnerMailService {
    @Resource
    private P3MailJformInnerMailDao p3MailJformInnerMailDao;

    public P3MailP3MailJformInnerMailServiceImpl() {
    }

    public P3MailJformInnerMail get(String id) {
        return this.p3MailJformInnerMailDao.get(id);
    }

    public int update(P3MailJformInnerMail p3MailJformInnerMail) {
        return this.p3MailJformInnerMailDao.update(p3MailJformInnerMail);
    }

    public void insert(P3MailJformInnerMail p3MailJformInnerMail) {
        this.p3MailJformInnerMailDao.insert(p3MailJformInnerMail);
    }

    public MiniDaoPage<P3MailJformInnerMail> getAll(P3MailJformInnerMail p3MailJformInnerMail, int page, int rows) {
        return this.p3MailJformInnerMailDao.getAll(p3MailJformInnerMail, page, rows);
    }

    public void delete(P3MailJformInnerMail p3MailJformInnerMail) {
        this.p3MailJformInnerMailDao.delete(p3MailJformInnerMail);
    }

    public P3MailJformInnerMail getByReceivedId(String id) {
        return this.p3MailJformInnerMailDao.getByReceivedId(id);
    }

    public MiniDaoPage<P3MailJformInnerMail> getUnSendMailByCreateBy(String createBy, P3MailJformInnerMail query, int pageNo, int pageSize) {
        return this.p3MailJformInnerMailDao.getUnSendMailByCreateBy(createBy, query, pageNo, pageSize);
    }

    public MiniDaoPage<P3MailJformInnerMail> getSendedMailByCreateBy(String createBy, P3MailJformInnerMail query, int pageNo, int pageSize) {
        return this.p3MailJformInnerMailDao.getSendedMailByCreateBy(createBy, query, pageNo, pageSize);
    }

    public String getSendedMailCountByCreateBy(String createBy) {
        return this.p3MailJformInnerMailDao.getSendedMailCountByCreateBy(createBy);
    }

    public String getUnSendedMailCountByCreateBy(String createBy) {
        return this.p3MailJformInnerMailDao.getUnSendedMailCountByCreateBy(createBy);
    }
}
