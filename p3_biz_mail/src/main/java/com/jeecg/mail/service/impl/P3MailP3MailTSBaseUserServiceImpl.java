//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.service.impl;

import com.jeecg.mail.dao.P3MailTSBaseUserDao;
import com.jeecg.mail.entity.P3MailTSBaseUser;
import com.jeecg.mail.service.P3MailTSBaseUserService;
import javax.annotation.Resource;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Service;

@Service("p3MailTSBaseUserService")
public class P3MailP3MailTSBaseUserServiceImpl implements P3MailTSBaseUserService {
    @Resource
    private P3MailTSBaseUserDao tSBaseUserDaoP3Mail;

    public P3MailP3MailTSBaseUserServiceImpl() {
    }

    public P3MailTSBaseUser get(String id) {
        return this.tSBaseUserDaoP3Mail.get(id);
    }

    public int update(P3MailTSBaseUser tSBaseUserP3Mail) {
        return this.tSBaseUserDaoP3Mail.update(tSBaseUserP3Mail);
    }

    public void insert(P3MailTSBaseUser tSBaseUserP3Mail) {
        this.tSBaseUserDaoP3Mail.insert(tSBaseUserP3Mail);
    }

    public MiniDaoPage<P3MailTSBaseUser> getAll(P3MailTSBaseUser tSBaseUserP3Mail, int page, int rows) {
        return this.tSBaseUserDaoP3Mail.getAll(tSBaseUserP3Mail, page, rows);
    }

    public void delete(P3MailTSBaseUser tSBaseUserP3Mail) {
        this.tSBaseUserDaoP3Mail.delete(tSBaseUserP3Mail);
    }

    public P3MailTSBaseUser getByUsername(String createBy) {
        return this.tSBaseUserDaoP3Mail.getByUsername(createBy);
    }
}
