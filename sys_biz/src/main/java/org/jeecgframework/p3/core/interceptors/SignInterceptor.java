//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.interceptors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.SignatureUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SignInterceptor implements HandlerInterceptor {
    public static final Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
    private static final String SIGN_PARAM_NAME = "sign";
    private static final String SESSION_OPENID = "openid";
    private static final String SESSION_NICKNAME = "nickname";
    private List<String> excludeUrls;
    private String signKey;
    private String mode;

    public SignInterceptor() {
    }

    public List<String> getExcludeUrls() {
        return this.excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public String getSignKey() {
        return this.signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if("DEV".equals(this.mode)) {
            return true;
        } else {
            String requestPath = this.getRequestPath(request);
            String requestUrl = this.getRequestUrl(request);
            String basePath = request.getContextPath();
            request.setAttribute("basePath", basePath);
            if(this.excludeUrls.contains(requestPath)) {
                return true;
            } else if(requestPath != null && requestPath.indexOf(".do") > -1) {
                if(requestPath != null && requestPath.indexOf("/back/") > -1) {
                    return true;
                } else {
                    String url;
                    String openid;
                    if(requestUrl != null && requestUrl.indexOf("sign=") != -1) {
                        url = request.getParameter("openid");
                        openid = request.getParameter("nickname");
                        String sign = request.getParameter("sign");
                        if(StringUtil.notEmpty(sign)) {
                            Map paramMap = this.getSignMap(request);
                            boolean check = SignatureUtil.checkSign(paramMap, this.signKey, sign);
                            if(check) {
                                if(StringUtil.isEmpty(url)) {
                                    return false;
                                }

                                request.getSession().setAttribute("openid", url);
                                request.getSession().setAttribute("nickname", openid);
                                response.sendRedirect(this.getRedirectUrl(basePath + "/" + requestPath, paramMap));
                                return false;
                            }
                        }
                    } else {
                        url = (String)request.getSession().getAttribute("openid");
                        if(StringUtil.notEmpty(url)) {
                            openid = request.getParameter("openid");
                            if(!StringUtil.notEmpty(openid)) {
                                return true;
                            }

                            if(url.equals(openid)) {
                                return true;
                            }
                        }
                    }

                    url = basePath + "/system/noAuth.do";
                    response.sendRedirect(url);
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    private Map<String, String> getSignMap(HttpServletRequest request) {
        HashMap paramMap = new HashMap();
        Map tem = request.getParameterMap();
        HashMap map = new HashMap();
        Iterator it = tem.keySet().iterator();

        while(it.hasNext()) {
            String es = (String)it.next();
            map.put(es, tem.get(es));
        }

        Set es1 = map.entrySet();

        String k;
        String v;
        for(it = es1.iterator(); it.hasNext(); paramMap.put(k, v)) {
            Entry entry = (Entry)it.next();
            k = (String)entry.getKey();
            Object ov = entry.getValue();
            v = "";
            if(ov instanceof String[]) {
                String[] value = (String[])ov;
                v = value[0];
            } else {
                v = ov.toString();
            }
        }

        return paramMap;
    }

    private String getRedirectUrl(String requestPath, Map<String, String> paramMap) {
        Set es = paramMap.entrySet();
        Iterator it = es.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append(requestPath + "?");

        while(it.hasNext()) {
            Entry redirectUrl = (Entry)it.next();
            String k = (String)redirectUrl.getKey();
            String v = (String)redirectUrl.getValue();
            if(v != null && !"".equals(v) && !"null".equals(v) && !"sign".equals(k) && !"nickname".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        String redirectUrl1 = sb.toString();
        redirectUrl1 = redirectUrl1.substring(0, redirectUrl1.length() - 1);
        logger.info("---------------redirectUrl--------------" + redirectUrl1);
        return redirectUrl1;
    }

    private String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        requestPath = requestPath.substring(request.getContextPath().length() + 1);
        return requestPath;
    }

    private String getRequestUrl(HttpServletRequest request) {
        String param = request.getQueryString();
        String requestUrl = request.getRequestURI();
        if(param != null) {
            requestUrl = requestUrl + "?" + request.getQueryString();
        }

        if(requestUrl.indexOf("#") != -1) {
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("#"));
        }

        return requestUrl;
    }
}
