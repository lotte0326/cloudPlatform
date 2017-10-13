//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service.impl;

import com.jeecg.mail.dao.P3MailTSUserDao;
import com.jeecg.mail.entity.P3MailTSUser;
import com.jeecg.mail.service.P3MailTSUserService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service("p3MailTSUserService")
public class P3MailP3MailTSUserServiceImpl implements P3MailTSUserService {
    @Resource
    private P3MailTSUserDao tSUserDaoP3Mail;

    public P3MailP3MailTSUserServiceImpl() {
    }

    public P3MailTSUser get(String id) {
        return this.tSUserDaoP3Mail.get(id);
    }

    public int update(P3MailTSUser tSUserP3Mail) {
        return this.tSUserDaoP3Mail.update(tSUserP3Mail);
    }

    public void insert(P3MailTSUser tSUserP3Mail) {
        this.tSUserDaoP3Mail.insert(tSUserP3Mail);
    }

    public MiniDaoPage<P3MailTSUser> getAll(P3MailTSUser tSUserP3Mail, int page, int rows) {
        return this.tSUserDaoP3Mail.getAll(tSUserP3Mail, page, rows);
    }

    public void delete(P3MailTSUser tSUserP3Mail) {
        this.tSUserDaoP3Mail.delete(tSUserP3Mail);
    }
}
