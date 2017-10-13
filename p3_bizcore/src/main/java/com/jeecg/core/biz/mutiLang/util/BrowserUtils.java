//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.mutiLang.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class BrowserUtils {
    private static Map<String, String> langMap = new HashMap();
    private static final String ZH = "zh";
    private static final String ZH_CN = "zh-cn";
    private static final String EN = "en";
    private static final String EN_US = "en";

    static {
        langMap.put("zh", "zh-cn");
        langMap.put("en", "en");
    }

    public BrowserUtils() {
    }

    public static String getBrowserLanguage(HttpServletRequest request) {
        String browserLang = request.getLocale().getLanguage();
        String browserLangCode = (String)langMap.get(browserLang);
        if(browserLangCode == null) {
            browserLangCode = "en";
        }

        if(request.getSession().getAttribute("lang") != null) {
            browserLangCode = (String)request.getSession().getAttribute("lang");
        }

        return browserLangCode;
    }
}
