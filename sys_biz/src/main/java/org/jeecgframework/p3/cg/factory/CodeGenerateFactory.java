//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg.factory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.cg.CommonPageParser;
import org.jeecgframework.p3.cg.CreateBean;
import org.jeecgframework.p3.cg.def.CodeResourceUtil;
import org.jeecgframework.p3.cg.def.FreemarkerEngine;

public class CodeGenerateFactory {
    private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
    private static String url;
    private static String username;
    private static String passWord;
    private static String buss_package;

    static {
        url = CodeResourceUtil.URL;
        username = CodeResourceUtil.USERNAME;
        passWord = CodeResourceUtil.PASSWORD;
        buss_package = CodeResourceUtil.bussiPackage;
    }

    public CodeGenerateFactory() {
    }

    public static void codeGenerateByVM(String tableName, String codeName, String keyType) {
        try {
            CreateBean e1 = new CreateBean();
            e1.setMysqlInfo(url, username, passWord);
            String className = e1.getTablesNameToClassName(tableName);
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            String tableNameUpper = tableName.toUpperCase();
            String tablesAsName = e1.getTablesASName(tableName);
            if(StringUtils.isEmpty(codeName)) {
                Map sqlmapPathSrc = e1.getTableCommentMap();
                codeName = (String)sqlmapPathSrc.get(tableNameUpper);
            }

            String sqlmapPathSrc1 = CodeResourceUtil.getConfigInfo("sqlmap_path_src");
            String domainPathSrc = CodeResourceUtil.getConfigInfo("domain_path_src");
            String daoPathSrc = CodeResourceUtil.getConfigInfo("dao_path_src");
            String daoImplPathSrc = CodeResourceUtil.getConfigInfo("dao_impl_path_src");
            String managerPathSrc = CodeResourceUtil.getConfigInfo("manager_path_src");
            String managerImplPathSrc = CodeResourceUtil.getConfigInfo("manager_impl_path_src");
            String servicePathSrc = CodeResourceUtil.getConfigInfo("service_path_src");
            String serviceImplPathSrc = CodeResourceUtil.getConfigInfo("service_impl_path_src");
            String sqlmapPackage = CodeResourceUtil.getConfigInfo("sqlmap_path");
            String domainPackage = CodeResourceUtil.getConfigInfo("domain_path");
            String daoPackage = CodeResourceUtil.getConfigInfo("dao_path");
            String daoImplPackage = CodeResourceUtil.getConfigInfo("dao_impl_path");
            String managerPackage = CodeResourceUtil.getConfigInfo("manager_path");
            String managerImplPackage = CodeResourceUtil.getConfigInfo("manager_impl_path");
            String servicePackage = CodeResourceUtil.getConfigInfo("service_path");
            String serviceImplPackage = CodeResourceUtil.getConfigInfo("service_impl_path");
            String sqlMapperPath = sqlmapPackage.replace(".", "\\") + "\\" + tableNameUpper + ".xml";
            String domainPath = domainPackage.replace(".", "\\") + "\\" + className + ".java";
            String daoPath = daoPackage.replace(".", "\\") + "\\" + className + "Dao.java";
            String daoImplPath = daoImplPackage.replace(".", "\\") + "\\" + className + "DaoImpl.java";
            String managerPath = managerPackage.replace(".", "\\") + "\\" + className + "Manager.java";
            String managerImplPath = managerImplPackage.replace(".", "\\") + "\\" + className + "ManagerImpl.java";
            String servicePath = servicePackage.replace(".", "\\") + "\\" + className + "Service.java";
            String serviceImplPath = serviceImplPackage.replace(".", "\\") + "\\" + className + "ServiceImpl.java";
            String sqlMapperFlag = CodeResourceUtil.getConfigInfo("sqlmap_flag");
            String domainFlag = CodeResourceUtil.getConfigInfo("domain_flag");
            String daoFlag = CodeResourceUtil.getConfigInfo("dao_flag");
            String daoImplFlag = CodeResourceUtil.getConfigInfo("dao_impl_flag");
            String managerFlag = CodeResourceUtil.getConfigInfo("manager_flag");
            String managerImplFlag = CodeResourceUtil.getConfigInfo("manager_impl_flag");
            String serviceFlag = CodeResourceUtil.getConfigInfo("service_flag");
            String serviceImplFlag = CodeResourceUtil.getConfigInfo("service_impl_flag");
            VelocityContext context = new VelocityContext();
            context.put("className", className);
            context.put("lowerName", lowerName);
            context.put("codeName", codeName);
            context.put("tableName", tableName);
            context.put("tableNameUpper", tableNameUpper);
            context.put("tablesAsName", tablesAsName);
            context.put("bussPackage", buss_package);
            context.put("domainPackage", domainPackage);
            context.put("daoPackage", daoPackage);
            context.put("daoImplPackage", daoImplPackage);
            context.put("managerPackage", managerPackage);
            context.put("managerImplPackage", managerImplPackage);
            context.put("servicePackage", servicePackage);
            context.put("serviceImplPackage", serviceImplPackage);
            context.put("keyType", keyType);
            context.put("feilds", e1.getBeanFeilds(tableName, className));
            Map sqlMap = e1.getAutoCreateSql(tableName);
            List columnDatas = e1.getColumnDatas(tableName);
            context.put("columnDatas", columnDatas);
            List columnKeyDatas = e1.getColumnKeyDatas(columnDatas);
            context.put("columnKeyDatas", columnKeyDatas);
            context.put("SQL", sqlMap);
            if("Y".equals(sqlMapperFlag)) {
                CommonPageParser.WriterPage(context, "sqlmap.vm", sqlmapPathSrc1, sqlMapperPath);
            }

            if("Y".equals(domainFlag)) {
                CommonPageParser.WriterPage(context, "domainClass.vm", domainPathSrc, domainPath);
            }

            if("Y".equals(daoFlag)) {
                CommonPageParser.WriterPage(context, "daoClass.vm", daoPathSrc, daoPath);
            }

            if("Y".equals(daoImplFlag)) {
                CommonPageParser.WriterPage(context, "daoImplClass.vm", daoImplPathSrc, daoImplPath);
            }

            if("Y".equals(managerFlag)) {
                CommonPageParser.WriterPage(context, "managerClass.vm", managerPathSrc, managerPath);
            }

            if("Y".equals(managerImplFlag)) {
                CommonPageParser.WriterPage(context, "managerImplClass.vm", managerImplPathSrc, managerImplPath);
            }

            if("Y".equals(serviceFlag)) {
                CommonPageParser.WriterPage(context, "serviceClass.vm", servicePathSrc, servicePath);
            }

            if("Y".equals(serviceImplFlag)) {
                CommonPageParser.WriterPage(context, "serviceImplClass.vm", serviceImplPathSrc, serviceImplPath);
            }

            log.info("----------------------------代码生成完毕---------------------------");
            System.out.println("----------------------------代码生成完毕---------------------------");
        } catch (Exception var44) {
            var44.printStackTrace();
        }

    }

    public static void codeGenerateByFTL(String tableName, String codeName, String keyType) {
        try {
            CreateBean e1 = new CreateBean();
            e1.setMysqlInfo(url, username, passWord);
            String className = e1.getTablesNameToClassName(tableName);
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            String tableNameUpper = tableName.toUpperCase();
            String tablesAsName = e1.getTablesASName(tableName);
            if(StringUtils.isEmpty(codeName)) {
                Map pathSrc = e1.getTableCommentMap();
                codeName = (String)pathSrc.get(tableNameUpper);
            }

            String pathSrc1 = CodeResourceUtil.getConfigInfo("path_src");
            String source_root_package = CodeResourceUtil.getConfigInfo("source_root_package");
            pathSrc1 = pathSrc1 + File.separator + source_root_package.replace(".", File.separator);
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            String projectName = CodeResourceUtil.getConfigInfo("project_name");
            basePackage = basePackage + "." + projectName;
            String bussiPackage = CodeResourceUtil.getConfigInfo("bussi_package");
            String author = CodeResourceUtil.getConfigInfo("author");
            String baseDao = CodeResourceUtil.getConfigInfo("baseDao");
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String daoImplPackage = basePackage + "." + bussiPackage + ".dao.impl";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String serviceImplPackage = basePackage + "." + bussiPackage + ".service.impl";
            String controllerPackage = basePackage + "." + bussiPackage + ".web";
            String domainQueryPackage = basePackage + "." + bussiPackage + ".vo";
            String pagePackage = "content." + projectName + "." + bussiPackage;
            String sqlPackage = basePackage + "." + bussiPackage + ".sql";
            String domainPath = domainPackage.replace(".", "\\") + "\\" + className + "Entity.java";
            String daoPath = daoPackage.replace(".", "\\") + "\\" + className + "Dao.java";
            String daoImplPath = daoImplPackage.replace(".", "\\") + "\\" + className + "DaoImpl.java";
            String servicePath = servicePackage.replace(".", "\\") + "\\" + className + "Service.java";
            String serviceImplPath = serviceImplPackage.replace(".", "\\") + "\\" + className + "ServiceImpl.java";
            String controllerPath = controllerPackage.replace(".", "\\") + "\\" + className + "Controller.java";
            String domainQueryPath = domainQueryPackage.replace(".", "\\") + "\\" + className + "Vo.java";
            String pageIndexPath = pagePackage.replace(".", "\\") + "\\" + lowerName + "-list.vm";
            String pageAddPath = pagePackage.replace(".", "\\") + "\\" + lowerName + "-add.vm";
            String pageEditPath = pagePackage.replace(".", "\\") + "\\" + lowerName + "-edit.vm";
            String pageDetailPath = pagePackage.replace(".", "\\") + "\\" + lowerName + "-detail.vm";
            String sqlConditionPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_condition.sql";
            String sqlGetAllPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_getAll.sql";
            String sqlInsertPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_insert.sql";
            String sqlUpdatePath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_update.sql";
            String sqlMapperFlag = CodeResourceUtil.getConfigInfo("sqlmap_flag");
            String domainFlag = CodeResourceUtil.getConfigInfo("domain_flag");
            String daoFlag = CodeResourceUtil.getConfigInfo("dao_flag");
            String daoImplFlag = CodeResourceUtil.getConfigInfo("dao_impl_flag");
            String serviceFlag = CodeResourceUtil.getConfigInfo("service_flag");
            String serviceImplFlag = CodeResourceUtil.getConfigInfo("service_impl_flag");
            String controllerFlag = CodeResourceUtil.getConfigInfo("controller_flag");
            String domainQueryFlag = CodeResourceUtil.getConfigInfo("domain_query_flag");
            String pageFlag = CodeResourceUtil.getConfigInfo("page_flag");
            String sqlFlag = CodeResourceUtil.getConfigInfo("sql_flag");
            Map sqlMap = e1.getAutoCreateSql(tableName);
            List columnDatas = e1.getColumnDatas(tableName);
            List columnKeyDatas = e1.getColumnKeyDatas(columnDatas);
            String columnKeyParam = e1.getColumnKeyParam(columnKeyDatas);
            String columnKeyUseParam = e1.getColumnKeyUseParam(columnKeyDatas);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
            String nowDate = dateformat.format(new Date());
            System.out.println("时间:" + nowDate);
            HashMap root = new HashMap();
            root.put("author", author);
            root.put("baseDao", baseDao);
            root.put("className", className);
            root.put("lowerName", lowerName);
            root.put("codeName", codeName);
            root.put("tableName", tableName);
            root.put("tableNameUpper", tableNameUpper);
            root.put("tablesAsName", tablesAsName);
            root.put("bussPackage", bussiPackage);
            root.put("projectName", projectName);
            root.put("domainPackage", domainPackage);
            root.put("domainQueryPackage", domainQueryPackage);
            root.put("daoPackage", daoPackage);
            root.put("daoImplPackage", daoImplPackage);
            root.put("servicePackage", servicePackage);
            root.put("serviceImplPackage", serviceImplPackage);
            root.put("controllerPackage", controllerPackage);
            root.put("keyType", keyType);
            root.put("nowDate", nowDate);
            root.put("feilds", e1.getBeanFeilds(tableName, className));
            root.put("queryfeilds", e1.getQueryBeanFeilds(tableName, className));
            root.put("columnDatas", columnDatas);
            root.put("columnKeyDatas", columnKeyDatas);
            root.put("columnKeyParam", columnKeyParam);
            root.put("columnKeyUseParam", columnKeyUseParam);
            root.put("SQL", sqlMap);
            Configuration cfg = new Configuration();
            String templateBasePath = getClassPath() + CodeResourceUtil.getConfigInfo("templatepath_ftl");
            cfg.setDirectoryForTemplateLoading(new File(templateBasePath));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            if("Y".equals(domainFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "domainClass.ftl", pathSrc1, domainPath);
            }

            if("Y".equals(daoFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "daoClass.ftl", pathSrc1, daoPath);
            }

            if("Y".equals(daoImplFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "daoImplClass.ftl", pathSrc1, daoImplPath);
            }

            if("Y".equals(serviceFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "serviceClass.ftl", pathSrc1, servicePath);
            }

            if("Y".equals(serviceImplFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "serviceImplClass.ftl", pathSrc1, serviceImplPath);
            }

            if("Y".equals(controllerFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "controllerClass.ftl", pathSrc1, controllerPath);
            }

            if("Y".equals(domainQueryFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "domainQueryClass.ftl", pathSrc1, domainQueryPath);
            }

            if("Y".equals(sqlFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "minidaoConditionSql.ftl", pathSrc1, sqlConditionPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "minidaoGetAllSql.ftl", pathSrc1, sqlGetAllPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "minidaoInsertSql.ftl", pathSrc1, sqlInsertPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "minidaoUpdateSql.ftl", pathSrc1, sqlUpdatePath);
            }

            if("Y".equals(pageFlag)) {
                columnDatas = e1.getPageColumnDatas(tableName);
                root.put("columnDatas", columnDatas);
                FreemarkerEngine.createFileByFTL(cfg, root, "pageIndex.ftl", pathSrc1, pageIndexPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "pageAdd.ftl", pathSrc1, pageAddPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "pageDetail.ftl", pathSrc1, pageDetailPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "pageEdit.ftl", pathSrc1, pageEditPath);
            }

            log.info("----------------------------代码生成完毕---------------------------");
            System.out.println("----------------------------代码生成完毕---------------------------");
        } catch (Exception var59) {
            var59.printStackTrace();
        }

    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public static String getClassPath() {
        String path = (new CodeGenerateFactory()).getClass().getResource("/").getPath();
        return path;
    }
}
