//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service;

import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.P3ImTSDepart;
import java.util.List;

public interface ImService {
    List<P3ImTSBaseUser> getUsers();

    List<P3ImTSDepart> getDeparts();

    boolean isInThisOrg(String var1, String var2);

    int updateSign(String var1, String var2);
}
