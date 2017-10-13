//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.dao;

import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.P3ImTSDepart;
import com.jeecg.chat.entity.P3ImTSUserOrg;
import java.util.List;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

@Repository
public interface ImDao {
    @Sql("select a.id,a.realname,d.imsign,a.userkey,a.username ,c.org_code as departid ,SUBSTR(c.org_code,1,3) as maindepartid ,d.portrait from t_s_base_user a , t_s_user_org b ,t_s_depart  c ,t_s_user d where a.ID =b.user_id and b.org_id=c.id and a.ID=d.id")
    List<P3ImTSBaseUser> getUsers();

    @Sql("select * from t_s_depart where org_type = 1")
    List<P3ImTSDepart> getDeparts();

    @Sql("select * from t_s_depart where id = :uid")
    P3ImTSDepart getDepart(@Param("uid") String var1);

    @Sql("select id from t_s_depart where org_code = :orgcode")
    P3ImTSDepart getDepartByOrgCode(@Param("orgcode") String var1);

    @Sql("select * from t_s_user_org where user_id = :uid")
    List<P3ImTSUserOrg> getOrgsByUserId(@Param("uid") String var1);

    int update(@Param("imsign") String var1, @Param("uid") String var2);
}
