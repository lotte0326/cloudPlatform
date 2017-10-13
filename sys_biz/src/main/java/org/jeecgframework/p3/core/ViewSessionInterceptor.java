//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.p3.core.util.plugin.SynchronizationHelper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ViewSessionInterceptor implements HandlerInterceptor {
    public ViewSessionInterceptor() {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        SynchronizationHelper.clear();
        SynchronizationHelper.setCurrentRequest(request, response);
        return true;
    }
}
