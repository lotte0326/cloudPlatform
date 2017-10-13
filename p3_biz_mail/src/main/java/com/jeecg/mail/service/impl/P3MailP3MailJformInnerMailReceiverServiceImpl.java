//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service.impl;

import com.jeecg.mail.dao.P3MailJformInnerMailReceiverDao;
import com.jeecg.mail.entity.P3MailJformInnerMail;
import com.jeecg.mail.entity.P3MailJformInnerMailReceiver;
import com.jeecg.mail.service.P3MailJformInnerMailReceiverService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service("p3MailJformInnerMailReceiverService")
public class P3MailP3MailJformInnerMailReceiverServiceImpl implements P3MailJformInnerMailReceiverService {
    @Resource
    private P3MailJformInnerMailReceiverDao p3MailJformInnerMailReceiverDao;

    public P3MailP3MailJformInnerMailReceiverServiceImpl() {
    }

    public P3MailJformInnerMailReceiver get(String id) {
        return this.p3MailJformInnerMailReceiverDao.get(id);
    }

    public int update(P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver) {
        return this.p3MailJformInnerMailReceiverDao.update(p3MailJformInnerMailReceiver);
    }

    public void insert(P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver) {
        this.p3MailJformInnerMailReceiverDao.insert(p3MailJformInnerMailReceiver);
    }

    public MiniDaoPage<P3MailJformInnerMailReceiver> getAll(P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver, int page, int rows) {
        return this.p3MailJformInnerMailReceiverDao.getAll(p3MailJformInnerMailReceiver, page, rows);
    }

    public void delete(P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver) {
        this.p3MailJformInnerMailReceiverDao.delete(p3MailJformInnerMailReceiver);
    }

    public void deleteByMailId(P3MailJformInnerMail p3MailJformInnerMail) {
        this.p3MailJformInnerMailReceiverDao.deleteByMainId(p3MailJformInnerMail.getId());
    }

    public MiniDaoPage<P3MailJformInnerMailReceiver> getByUserid(String userid, P3MailJformInnerMailReceiver query, int pageNo, int pageSize) {
        return this.p3MailJformInnerMailReceiverDao.getByUserid(userid, query, pageNo, pageSize);
    }

    public String getReceivedMailCountByUserid(String userid) {
        return this.p3MailJformInnerMailReceiverDao.getReceivedMailCountByUserid(userid);
    }

    public String getDeletedMailCountByUserid(String userid) {
        return this.p3MailJformInnerMailReceiverDao.getDeletedMailCountByUserid(userid);
    }

    public MiniDaoPage<P3MailJformInnerMailReceiver> getDeletedByUserid(String userid, P3MailJformInnerMailReceiver query, int pageNo, int pageSize) {
        return this.p3MailJformInnerMailReceiverDao.getDeletedMailByUserid(userid, query, pageNo, pageSize);
    }
}
