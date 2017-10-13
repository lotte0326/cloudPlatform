//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jeecgframework.p3.cg.def.CodeResourceUtil;
import org.jeecgframework.p3.cg.factory.CodeGenerateFactory;

public class CommonPageParser {
    private static VelocityEngine ve;
    private static final String CONTENT_ENCODING = "UTF-8";
    private static final Log log = LogFactory.getLog(CommonPageParser.class);
    private static boolean isReplace = true;

    static {
        try {
            String e = CodeGenerateFactory.getProjectPath() + CodeResourceUtil.getConfigInfo("templatepath_vm");
            Properties properties = new Properties();
            properties.setProperty("resource.loader", "file");
            properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
            properties.setProperty("file.resource.loader.path", e);
            properties.setProperty("file.resource.loader.cache", "true");
            properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
            properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
            properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
            properties.setProperty("directive.set.null.allowed", "true");
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init(properties);
            ve = velocityEngine;
        } catch (Exception var3) {
            log.error(var3);
        }

    }

    public CommonPageParser() {
    }

    public static void WriterPage(VelocityContext context, String templateName, String fileDirPath, String targetFile) {
        try {
            String e = CodeResourceUtil.getConfigInfo("workspace_path");
            File file = new File(e + File.separator + fileDirPath + File.separator + targetFile);
            if(!file.exists()) {
                (new File(file.getParent())).mkdirs();
            } else if(isReplace) {
                log.info("替换文件:" + file.getAbsolutePath());
            }

            Template template = ve.getTemplate(templateName, "UTF-8");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
            fos.close();
            log.info("生成文件：" + file.getAbsolutePath());
        } catch (Exception var9) {
            log.error(var9);
        }

    }
}
