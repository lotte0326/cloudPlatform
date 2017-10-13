//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util.plugin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.SynchronizationHelper;
import org.springframework.beans.factory.InitializingBean;

public class ViewFreemarker implements InitializingBean {
    static final Logger logger = Logger.getLogger(ViewFreemarker.class);
    private static Configuration _tplConfig = new Configuration();

    static {
        _tplConfig.setClassForTemplateLoading(ViewFreemarker.class, "/content");
    }

    public ViewFreemarker() {
    }

    private static String parseTemplate(String tplName, String encoding, Map<String, Object> paras) {
        try {
            StringWriter e = new StringWriter();
            Template mytpl = null;
            mytpl = _tplConfig.getTemplate(tplName, encoding);
            mytpl.process(paras, e);
            return e.toString();
        } catch (Exception var5) {
            var5.printStackTrace();
            return var5.toString();
        }
    }

    public static void view(HttpServletResponse response, String template) throws Exception {
        view(response, template, (Map)null);
    }

    public static void view(HttpServletResponse rep, String template, Map<String, Object> paras) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("Freemarker loading：" + template);
        }

        HttpServletRequest request = ContextHolderUtils.getRequest();
        HttpServletResponse response = SynchronizationHelper.getCurrentResponse();
        if(paras == null) {
            paras = new HashMap();
        }

        ((Map)paras).put("request", request);
        ((Map)paras).put("response", response);
        String content = parseTemplate(template, "UTF-8", (Map)paras);
        outputToPage(request, response, content);
    }

    private static void outputToPage(HttpServletRequest request, HttpServletResponse response, String content) throws Exception {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");

        try {
            PrintWriter writer = response.getWriter();
            writer.println(content);
            writer.flush();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void afterPropertiesSet() throws Exception {
    }
}
