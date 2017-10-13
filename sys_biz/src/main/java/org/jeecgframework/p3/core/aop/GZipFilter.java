//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.aop;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.p3.core.aop.ResMime;
import org.springframework.util.FileCopyUtils;

public class GZipFilter implements Filter {
    private static final Logger logger = Logger.getLogger(GZipFilter.class);
    private static final String STATIC_SOURCE_ROOT = "/content/";
    private static final String DIAN = ".";
    private static final String NO_FILTER = ".do";

    public GZipFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)res;
        HttpServletRequest req = (HttpServletRequest)request;
        String url = req.getRequestURI();
        if(req.getRequestURI().indexOf("/content/") != -1 && req.getRequestURI().indexOf(".") != -1 && req.getRequestURI().indexOf(".do") == -1) {
            url = url.replaceFirst(req.getContextPath(), "");
            response.reset();
            String s = ResMime.get(url.substring(url.lastIndexOf(".")).replace(".", ""));
            if(s != null) {
                response.setContentType(s);
            }

            ServletOutputStream os = null;
            InputStream is = null;

            try {
                url = url.replaceFirst(req.getContextPath(), "");
                is = this.getClass().getResourceAsStream(url);
                if(is != null) {
                    os = response.getOutputStream();
                    FileCopyUtils.copy(is, os);
                } else {
                    chain.doFilter(request, response);
                }
            } catch (IOException var23) {
                var23.printStackTrace();
            } finally {
                if(os != null) {
                    try {
                        os.close();
                    } catch (IOException var22) {
                        var22.printStackTrace();
                    }
                }

                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException var21) {
                        var21.printStackTrace();
                    }
                }

            }
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
