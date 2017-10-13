//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util.plugin;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jeecgframework.p3.core.author.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ContextHolderUtils {
    public static final String LOCAL_CLINET_USER = "LOCAL_CLINET_USER";
    public static final String OPERATION_CODES = "operationCodes";
    public static final String DATA_RULE_CODES = "dataRulecodes";
    public static final String MENU_DATA_AUTHOR_RULE_SQL = "MENU_DATA_AUTHOR_RULE_SQL";

    public ContextHolderUtils() {
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }

    public static LoginUser getLoginSessionUser() {
        LoginUser u = null;

        try {
            HttpSession session = getSession();
            u = new LoginUser();
            Object c = session.getAttribute("LOCAL_CLINET_USER");
            if(c != null) {
                BeanUtils.copyProperties(c, u);
            }
        } catch (Exception var3) {
            ;
        }

        return u;
    }

    public static Set<String> getPageSelectOperationCodes() {
        Set operationCodes = (Set)getRequest().getAttribute("operationCodes");
        return operationCodes;
    }

    public static String getPageDataAuthorSQL() {
        String pageDataAuthorSQL = (String)getRequest().getAttribute("MENU_DATA_AUTHOR_RULE_SQL");
        return pageDataAuthorSQL;
    }

    public static Set<String> getPageDataAuthorCodes() {
        Set operationCodes = (Set)getRequest().getAttribute("dataRulecodes");
        return operationCodes;
    }
}
