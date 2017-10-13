//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.mutiLang.service.impl;

import com.jeecg.core.biz.mutiLang.dao.P3MutiLangDao;
import com.jeecg.core.biz.mutiLang.service.P3MutiLangServiceI;
import com.jeecg.core.biz.mutiLang.util.BrowserUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("p3MutiLangService")
public class P3MutiLangServiceImpl implements P3MutiLangServiceI {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private P3MutiLangDao p3MutiLangDao;

    public P3MutiLangServiceImpl() {
    }

    public String getLang(String langKey) {
        if(checkIsChinese(langKey)) {
            return langKey;
        } else {
            String language = BrowserUtils.getBrowserLanguage(this.request);
            String res = this.p3MutiLangDao.getLang(langKey, language);
            return StringUtils.isNotEmpty(res)?res:langKey;
        }
    }

    public static void main(String[] args) {
        checkIsChinese("please.select.department");
    }

    private static boolean checkIsChinese(String str) {
        return str.getBytes().length != str.length();
    }
}
