//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service.impl;

import com.jeecg.chat.dao.ImDao;
import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.P3ImTSDepart;
import com.jeecg.chat.service.ImService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("imService")
public class ImServiceImpl implements ImService {
    @Resource
    private ImDao imDao;

    public ImServiceImpl() {
    }

    public List<P3ImTSBaseUser> getUsers() {
        return this.imDao.getUsers();
    }

    public List<P3ImTSDepart> getDeparts() {
        return this.imDao.getDeparts();
    }

    public boolean isInThisOrg(String deptid, String oid) {
        String orgcode = this.imDao.getDepart(deptid).getOrgCode();
        boolean reslut = orgcode.substring(0, 3).equals(oid);
        return reslut;
    }

    public int updateSign(String sign, String uid) {
        return this.imDao.update(sign, uid);
    }
}
