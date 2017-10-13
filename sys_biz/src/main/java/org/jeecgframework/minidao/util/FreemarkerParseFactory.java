//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.minidao.aop.MiniDaoHandler;

public class FreemarkerParseFactory {
    private static final Logger logger = Logger.getLogger(MiniDaoHandler.class);
    private static final String ENCODE = "utf-8";
    private static final Configuration _tplConfig = new Configuration();
    private static final Configuration _sqlConfig = new Configuration();
    private static StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
    private static final Pattern p = Pattern.compile("(?ms)/\\*.*?\\*/|^\\s*//.*?$");

    static {
        _tplConfig.setClassForTemplateLoading((new FreemarkerParseFactory()).getClass(), "/");
        _tplConfig.setNumberFormat("0.#####################");
        _sqlConfig.setTemplateLoader(stringTemplateLoader);
        _sqlConfig.setNumberFormat("0.#####################");
    }

    public FreemarkerParseFactory() {
    }

    public static boolean isExistTemplate(String tplName) {
        try {
            Template e = _tplConfig.getTemplate(tplName, "UTF-8");
            return e != null;
        } catch (Exception var2) {
            return false;
        }
    }

    public static String parseTemplate(String tplName, Map<String, Object> paras) {
        try {
            StringWriter e = new StringWriter();
            Template mytpl = _tplConfig.getTemplate(tplName, "utf-8");
            mytpl.process(paras, e);
            return getSqlText(e.toString());
        } catch (Exception var4) {
            logger.error(var4.getMessage(), var4.fillInStackTrace());
            logger.error("发送一次的模板key:{ " + tplName + " }");
            System.err.println(var4.getMessage());
            System.err.println("模板名:{ " + tplName + " }");
            throw new RuntimeException("解析SQL模板异常");
        }
    }

    public static String parseTemplateContent(String tplContent, Map<String, Object> paras) {
        try {
            StringWriter e = new StringWriter();
            if(stringTemplateLoader.findTemplateSource("sql_" + tplContent.hashCode()) == null) {
                stringTemplateLoader.putTemplate("sql_" + tplContent.hashCode(), tplContent);
            }

            Template mytpl = _sqlConfig.getTemplate("sql_" + tplContent.hashCode(), "utf-8");
            mytpl.process(paras, e);
            return getSqlText(e.toString());
        } catch (Exception var4) {
            logger.error(var4.getMessage(), var4.fillInStackTrace());
            logger.error("发送一次的模板key:{ " + tplContent + " }");
            System.err.println(var4.getMessage());
            System.err.println("模板内容:{ " + tplContent + " }");
            throw new RuntimeException("解析SQL模板异常");
        }
    }

    private static String getSqlText(String sql) {
        sql = p.matcher(sql).replaceAll("");
        sql = sql.replaceAll("\\n", " ").replaceAll("\\t", " ").replaceAll("\\s{1,}", " ").trim();
        if(sql.endsWith("where") || sql.endsWith("where ")) {
            sql = sql.substring(0, sql.lastIndexOf("where"));
        }

        int index;
        for(index = 0; (index = StringUtils.indexOfIgnoreCase(sql, "where and", index)) != -1; sql = sql.substring(0, index + 5) + sql.substring(index + 9, sql.length())) {
            ;
        }

        for(index = 0; (index = StringUtils.indexOfIgnoreCase(sql, ", where", index)) != -1; sql = sql.substring(0, index) + sql.substring(index + 1, sql.length())) {
            ;
        }

        if(sql.endsWith(",") || sql.endsWith(", ")) {
            sql = sql.substring(0, sql.lastIndexOf(","));
        }

        return sql;
    }
}
