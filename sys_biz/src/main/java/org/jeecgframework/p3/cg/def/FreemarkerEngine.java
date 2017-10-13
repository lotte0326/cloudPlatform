//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg.def;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.p3.cg.def.CodeResourceUtil;

public class FreemarkerEngine {
    private static final Log log = LogFactory.getLog(FreemarkerEngine.class);

    public FreemarkerEngine() {
    }

    public static void createFileByFTL(Configuration cfg, Map<String, Object> root, String ftlName, String fileDirPath, String targetFile) {
        String encoding = CodeResourceUtil.getSYSTEM_ENCODING();
        String workspasePath = CodeResourceUtil.getConfigInfo("workspace_path");
        boolean isReplace = (new Boolean(CodeResourceUtil.getConfigInfo("isReplace"))).booleanValue();

        try {
            Template temp = cfg.getTemplate(ftlName, encoding);
            String e = workspasePath + File.separator + fileDirPath + File.separator + targetFile;
            if(e.indexOf(".vm") != -1 || e.indexOf(".js") != -1) {
                e = e.replace(File.separator + "java" + File.separator, File.separator + "resources" + File.separator);
            }

            File file = new File(e);
            if(!file.exists()) {
                (new File(file.getParent())).mkdirs();
                System.out.println("创建文件:" + file.getAbsolutePath());
            } else if(isReplace) {
                System.out.println("替换文件:" + file.getAbsolutePath());
            }

            FileOutputStream os = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(os, encoding);
            temp.process(root, out);
            out.flush();
        } catch (IOException var13) {
            var13.printStackTrace();
        } catch (TemplateException var14) {
            var14.printStackTrace();
        }

    }
}
