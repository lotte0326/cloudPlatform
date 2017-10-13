//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util.plugin;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Properties;

public class ViewVelocity implements InitializingBean {
    private static final String PAGE_ROOT = "content/";
    static final Logger logger = Logger.getLogger(ViewVelocity.class);
    private String propertiesFile;
    private Properties prop;

    private ViewVelocity() {
    }

    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public void setProperties(Properties prop) {
        this.prop = prop;
    }

    public void afterPropertiesSet() throws Exception {
        try {
            Properties e = new Properties();
            if(this.propertiesFile != null) {
                e.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.propertiesFile));
            }

            if(this.prop != null) {
                e.putAll(this.prop);
            }

            Velocity.init(e);
            if(logger.isInfoEnabled()) {
                logger.debug("Loading VelocityEngine....");
                Enumeration en = e.propertyNames();

                while(en.hasMoreElements()) {
                    String key = en.nextElement().toString();
                    logger.debug("\t" + key + "=" + e.getProperty(key));
                }
            }

        } catch (Exception var4) {
            throw new Exception("启动Velocity失败", var4);
        }
    }

    public static void view(HttpServletResponse response, String template) throws Exception {
        view(response, template, (VelocityContext)null);
    }

    /** @deprecated */
    @Deprecated
    public static void view(HttpServletResponse response, String template, VelocityContext velocityContext) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("Velocity loading：" + template);
        }

        HttpServletRequest request = ContextHolderUtils.getRequest();
        if(velocityContext == null) {
            velocityContext = new VelocityContext();
        }

        velocityContext.put("Format", new SimpleFormat());
        velocityContext.put("dateTool", new DateTool());
        String basePath = request.getContextPath();
        logger.debug("---------------basePath--------------" + basePath);
        velocityContext.put("basePath", basePath);
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("content/" + template, "UTF-8", velocityContext, writer);
        outputToPage(request, response, writer.toString());
    }

    public static void view(HttpServletRequest request, HttpServletResponse response, String template, VelocityContext velocityContext) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("Velocity loading：" + template);
        }

        if(velocityContext == null) {
            velocityContext = new VelocityContext();
        }

        velocityContext.put("Format", new SimpleFormat());
        velocityContext.put("dateTool", new DateTool());
        String basePath = request.getContextPath();
        logger.debug("---------------basePath--------------" + basePath);
        velocityContext.put("basePath", basePath);
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("content/" + template, "UTF-8", velocityContext, writer);
        outputToPage(request, response, writer.toString());
    }

    public static String getViewContent(HttpServletRequest request, HttpServletResponse response, String template, VelocityContext velocityContext) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("Velocity loading：" + template);
        }

        if(velocityContext == null) {
            velocityContext = new VelocityContext();
        }

        velocityContext.put("Format", new SimpleFormat());
        velocityContext.put("dateTool", new DateTool());
        String basePath = request.getContextPath();
        logger.debug("---------------basePath--------------" + basePath);
        velocityContext.put("basePath", basePath);
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate("content/" + template, "UTF-8", velocityContext, writer);
        return writer.toString();
    }

    private static void outputToPage(HttpServletRequest request, HttpServletResponse response, String content) throws Exception {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.println(content);
            writer.flush();
            writer.close();
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            } catch (Exception var12) {
                ;
            }

        }

    }
}
