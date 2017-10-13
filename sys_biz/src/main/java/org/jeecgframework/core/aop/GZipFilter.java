//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.aop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
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
import org.springframework.util.FileCopyUtils;

public class GZipFilter implements Filter {
    private static final Logger logger = Logger.getLogger(GZipFilter.class);
    private static final String STATIC_TEMPLATE_SOURCE = "online/template";
    private static final String STATIC_TEMPLATE_SOURCE_2 = "clzcontext/template";
    private static final String STATIC_TEMPLATE_SOURCE_3 = "/content/";
    private static final String STATIC_TEMPLATE_SOURCE_4 = "/plug-in-ui/";
    private static final String STATIC_TEMPLATE_SOURCE_5 = "/template/";
    private static final String NO_FILTER = ".do";
    private static final String NO_FILTER_JSP = ".jsp";
    private static final String DIAN = ".";

    public GZipFilter() {
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        String url = req.getRequestURI();
        String path = req.getContextPath();
        (new StringBuilder(String.valueOf(req.getScheme()))).append("://").append(req.getServerName()).append(":").append(req.getServerPort()).append(path).toString();
        if((req.getRequestURI().indexOf("online/template") != -1 || req.getRequestURI().indexOf("clzcontext/template") != -1 || req.getRequestURI().indexOf("/content/") != -1 || req.getRequestURI().indexOf("/plug-in-ui/") != -1 || req.getRequestURI().indexOf("/template/") != -1) && req.getRequestURI().indexOf(".") != -1 && req.getRequestURI().indexOf(".do") == -1 && req.getRequestURI().indexOf(".jsp") == -1) {
            if(url.startsWith(req.getContextPath())) {
                url = url.replaceFirst(req.getContextPath(), "");
            }

            response.reset();
            String wrapper1 = ResMime.get(url.substring(url.lastIndexOf(".")).replace(".", ""));
            if(wrapper1 != null) {
                response.setContentType(wrapper1);
            }

            ServletOutputStream gzipData2 = null;
            InputStream output2 = null;

            try {
                output2 = this.getClass().getResourceAsStream(url);
                if(output2 != null) {
                    this.cacheResource(request, response, chain);
                    if(isGZipEncoding(req)) {
                        byte[] e = input2byte(output2);
                        byte[] gzipData1 = this.gzip(e);
                        resp.addHeader("Content-Encoding", "gzip");
                        resp.setContentLength(gzipData1.length);
                        ServletOutputStream output1 = response.getOutputStream();
                        output1.write(gzipData1);
                        output1.flush();
                    } else {
                        gzipData2 = response.getOutputStream();
                        FileCopyUtils.copy(output2, gzipData2);
                    }
                } else {
                    chain.doFilter(request, response);
                }
            } catch (IOException var27) {
                var27.printStackTrace();
            } finally {
                if(gzipData2 != null) {
                    try {
                        gzipData2.close();
                    } catch (IOException var26) {
                        ;
                    }
                }

                if(output2 != null) {
                    try {
                        output2.close();
                    } catch (IOException var25) {
                        ;
                    }
                }

            }
        } else if(isGZipEncoding(req)) {
            Wrapper wrapper = new Wrapper(resp);
            chain.doFilter(request, wrapper);
            byte[] gzipData = this.gzip(wrapper.getResponseData());
            resp.addHeader("Content-Encoding", "gzip");
            resp.setContentLength(gzipData.length);
            ServletOutputStream output = response.getOutputStream();
            output.write(gzipData);
            output.flush();
        } else {
            chain.doFilter(request, response);
        }

    }

    public void cacheResource(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf(".") + 1);
        long date = 0L;
        if(uri.equalsIgnoreCase("jpg")) {
            date = System.currentTimeMillis() + 18000000L;
        }

        if(uri.equalsIgnoreCase("gif")) {
            date = System.currentTimeMillis() + 18000000L;
        }

        if(uri.equalsIgnoreCase("css")) {
            date = System.currentTimeMillis() + 18000000L;
        }

        if(uri.equalsIgnoreCase("js")) {
            date = System.currentTimeMillis() + 18000000L;
        }

        res.setDateHeader("Expires", date);
    }

    private static boolean isGZipEncoding(HttpServletRequest request) {
        boolean flag = false;
        String encoding = request.getHeader("Accept-Encoding");
        if(encoding != null && encoding.indexOf("gzip") != -1 && request.getRequestURI().indexOf(".do") == -1 && request.getRequestURI().indexOf(".jsp") == -1) {
            flag = true;
        }

        return flag;
    }

    private byte[] gzip(byte[] data) {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);
        GZIPOutputStream output = null;

        try {
            output = new GZIPOutputStream(byteOutput);
            output.write(data);
        } catch (IOException var13) {
            ;
        } finally {
            try {
                output.close();
            } catch (IOException var12) {
                ;
            }

        }

        return byteOutput.toByteArray();
    }

    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        boolean rc = false;

        int rc1;
        while((rc1 = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc1);
        }

        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
