//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class SynchronizationHelper {
    private static Logger logger = Logger.getLogger(SynchronizationHelper.class);
    private static final ThreadLocal<HttpServletRequest> localServletRequest = new ThreadLocal();
    private static final ThreadLocal<HttpServletResponse> localServletResponse = new ThreadLocal();

    private SynchronizationHelper() {
    }

    public static void clear() {
        localServletRequest.remove();
        localServletResponse.remove();
        if(logger.isDebugEnabled()) {
            logger.debug("清理当前线程");
        }

    }

    public static void setCurrentRequest(HttpServletRequest request, HttpServletResponse response) {
        localServletRequest.set(request);
        localServletResponse.set(response);
        if(logger.isDebugEnabled()) {
            logger.debug("把当前请求绑定到当前进程");
        }

    }

    public static HttpServletRequest getCurrentRequest() {
        return (HttpServletRequest)localServletRequest.get();
    }

    public static HttpServletResponse getCurrentResponse() {
        return (HttpServletResponse)localServletResponse.get();
    }
}
