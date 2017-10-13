//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.mutiLang.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

@Repository
public interface P3MutiLangDao {
    @Sql("select lang_context from t_s_muti_lang where lang_key = :langkey and lang_code = :langcode")
    String getLang(@Param("langkey") String var1, @Param("langcode") String var2);
}
