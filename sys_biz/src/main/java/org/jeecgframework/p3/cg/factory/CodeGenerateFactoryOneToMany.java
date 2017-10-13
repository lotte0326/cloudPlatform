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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.p3.cg.CreateBean;
import org.jeecgframework.p3.cg.def.CodeResourceUtil;
import org.jeecgframework.p3.cg.def.FreemarkerEngine;
import org.jeecgframework.p3.cg.pojo.onetomany.CodeParamEntity;
import org.jeecgframework.p3.cg.pojo.onetomany.SubTableEntity;

public class CodeGenerateFactoryOneToMany {
    private static final Log log = LogFactory.getLog(CodeGenerateFactoryOneToMany.class);
    private static String url;
    private static String username;
    private static String passWord;
    private static Configuration cfg;
    private static Map<String, Object> root;
    private static CreateBean createBean;
    private static String pathSrc;
    private static String author;

    static {
        url = CodeResourceUtil.URL;
        username = CodeResourceUtil.USERNAME;
        passWord = CodeResourceUtil.PASSWORD;
        cfg = new Configuration();
        root = new HashMap();
        createBean = new CreateBean();
        pathSrc = "";
        author = "";
        pathSrc = CodeResourceUtil.getConfigInfo("path_src");
        String source_root_package = CodeResourceUtil.getConfigInfo("source_root_package");
        pathSrc = pathSrc + File.separator + source_root_package.replace(".", File.separator);
        author = CodeResourceUtil.getConfigInfo("author");
    }

    public CodeGenerateFactoryOneToMany() {
    }

    public static void oneToManyCreate(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        try {
            String e1 = CodeResourceUtil.getConfigInfo("project_name");
            codeParamEntityIn.setProjectName(e1);
            String bussiPackage = CodeResourceUtil.getConfigInfo("bussi_package");
            codeParamEntityIn.setEntityPackage(bussiPackage);
            createBean.setMysqlInfo(url, username, passWord);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
            String nowDate = dateformat.format(new Date());
            System.out.println("时间:" + nowDate);
            root.put("author", author);
            root.put("nowDate", nowDate);
            String templateBasePath = getClassPath() + CodeResourceUtil.getConfigInfo("templatepath_ftl") + "/onetomany";
            cfg.setDirectoryForTemplateLoading(new File(templateBasePath));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            createEntity(codeParamEntityIn, subTabParamIn);
            createDao(codeParamEntityIn, subTabParamIn);
            createSql(codeParamEntityIn, subTabParamIn);
            createService(codeParamEntityIn, subTabParamIn);
            createServiceImpl(codeParamEntityIn, subTabParamIn);
            createController(codeParamEntityIn, subTabParamIn);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private static void createController(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        initControllerParam(codeParamEntityIn, subTabParamIn);
        String basePackage = CodeResourceUtil.getConfigInfo("base_package");
        String className = codeParamEntityIn.getEntityName();
        String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        String projectName = codeParamEntityIn.getProjectName();
        String bussiPackage = codeParamEntityIn.getEntityPackage();
        basePackage = basePackage + "." + projectName;
        String pagePackage = basePackage + "." + bussiPackage + ".page";
        String pagePath = pagePackage.replace(".", "\\") + "\\" + className + "Page.java";
        FreemarkerEngine.createFileByFTL(cfg, root, "pageClass.ftl", pathSrc, pagePath);
        String controllerPackage = basePackage + "." + bussiPackage + ".web";
        String controllerPath = controllerPackage.replace(".", "\\") + "\\" + className + "Controller.java";
        FreemarkerEngine.createFileByFTL(cfg, root, "controllerClass.ftl", pathSrc, controllerPath);
        String pageVmPackage = "content." + projectName + "." + bussiPackage;
        String pageIndexPath = pageVmPackage.replace(".", "\\") + "\\" + lowerName + "-list.vm";
        String pageAddPath = pageVmPackage.replace(".", "\\") + "\\" + lowerName + "-add.vm";
        String pageAddJsPath = pageVmPackage.replace(".", "\\") + "\\" + lowerName + "-add.js";
        String pageEditPath = pageVmPackage.replace(".", "\\") + "\\" + lowerName + "-edit.vm";
        String pageDetailPath = pageVmPackage.replace(".", "\\") + "\\" + lowerName + "-detail.vm";
        FreemarkerEngine.createFileByFTL(cfg, root, "pageIndex.ftl", pathSrc, pageIndexPath);
        FreemarkerEngine.createFileByFTL(cfg, root, "pageAdd.ftl", pathSrc, pageAddPath);
        FreemarkerEngine.createFileByFTL(cfg, root, "pageAddJs.ftl", pathSrc, pageAddJsPath);
        FreemarkerEngine.createFileByFTL(cfg, root, "pageDetail.ftl", pathSrc, pageDetailPath);
        FreemarkerEngine.createFileByFTL(cfg, root, "pageEdit.ftl", pathSrc, pageEditPath);
    }

    private static void initControllerParam(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        initControllerMainParam(codeParamEntityIn);
        initControllerSubParam(codeParamEntityIn.getProjectName(), subTabParamIn);
    }

    private static void initControllerMainParam(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String pagePackage = basePackage + "." + bussiPackage + ".page";
            String pageVmPackage = "content." + projectName + "." + bussiPackage;
            String pageAddJsPath = pageVmPackage.replace(".", "/") + "/" + lowerName + "-add.js";
            String controllerPackage = basePackage + "." + bussiPackage + ".web";
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("projectName", projectName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("bussPackage", bussiPackage);
            root.put("servicePackage", servicePackage);
            root.put("pagePackage", pagePackage);
            root.put("pageAddJsPath", pageAddJsPath);
            root.put("controllerPackage", controllerPackage);
            root.put("mainEntity", codeParamEntityIn);
            List columnDatas = createBean.getPageColumnDatas(e);
            root.put("columnDatas", columnDatas);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    private static void initControllerSubParam(String projectName, List<SubTableEntity> subTabParamIn) {
        try {
            Iterator var3 = subTabParamIn.iterator();

            while(var3.hasNext()) {
                SubTableEntity e = (SubTableEntity)var3.next();
                HashMap paramData = new HashMap();
                String tableName = e.getTableName();
                String className = e.getEntityName();
                String codeName = e.getFtlDescription();
                String bussiPackage = e.getEntityPackage();
                String basePackage = CodeResourceUtil.getConfigInfo("base_package");
                basePackage = basePackage + "." + projectName;
                String domainPackage = basePackage + "." + bussiPackage + ".entity";
                String daoPackage = basePackage + "." + bussiPackage + ".dao";
                String servicePackage = basePackage + "." + bussiPackage + ".service";
                String serviceImplPackage = basePackage + "." + bussiPackage + ".service.impl";
                String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
                paramData.put("className", className);
                paramData.put("codeName", codeName);
                paramData.put("tableName", tableName);
                paramData.put("domainPackage", domainPackage);
                paramData.put("lowerName", lowerName);
                paramData.put("daoPackage", daoPackage);
                paramData.put("servicePackage", servicePackage);
                paramData.put("serviceImplPackage", serviceImplPackage);
                String foreignKeyTable = e.getForeignKey().toUpperCase();
                String foreignKeyUpper = createBean.getTablesNameToClassName(foreignKeyTable);
                paramData.put("foreignKeyTable", foreignKeyTable);
                paramData.put("foreignKeyUpper", foreignKeyUpper);
                String foreignKey = foreignKeyUpper.substring(0, 1).toLowerCase() + foreignKeyUpper.substring(1, foreignKeyUpper.length());
                paramData.put("foreignKey", foreignKey);
                String mainForeignKeyTable = e.getMainForeignKey().toUpperCase();
                String mainForeignKeyUpper = createBean.getTablesNameToClassName(mainForeignKeyTable);
                paramData.put("mainForeignKeyTable", mainForeignKeyTable);
                paramData.put("mainForeignKeyUpper", mainForeignKeyUpper);
                String mainForeignKey = mainForeignKeyUpper.substring(0, 1).toLowerCase() + mainForeignKeyUpper.substring(1, mainForeignKeyUpper.length());
                paramData.put("mainForeignKey", mainForeignKey);
                List columnDatas = createBean.getPageColumnDatas(tableName);
                paramData.put("columnDatas", columnDatas);
                e.setParamData(paramData);
            }

            root.put("subEntityList", subTabParamIn);
        } catch (Exception var22) {
            var22.printStackTrace();
        }

    }

    private static void createServiceImpl(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        createServiceImplMain(codeParamEntityIn);
        Iterator var3 = subTabParamIn.iterator();

        while(var3.hasNext()) {
            SubTableEntity sub = (SubTableEntity)var3.next();
            createServiceImplSub(codeParamEntityIn.getProjectName(), sub);
        }

    }

    private static void createServiceImplMain(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String serviceImplPackage = basePackage + "." + bussiPackage + ".service.impl";
            String serviceImplPath = serviceImplPackage.replace(".", "\\") + "\\" + className + "ServiceImpl.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            root.put("servicePackage", servicePackage);
            root.put("serviceImplPackage", serviceImplPackage);
            FreemarkerEngine.createFileByFTL(cfg, root, "serviceImplMainClass.ftl", pathSrc, serviceImplPath);
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    private static void createServiceImplSub(String projectName, SubTableEntity subTabParamIn) {
        try {
            String e = subTabParamIn.getTableName();
            String className = subTabParamIn.getEntityName();
            String codeName = subTabParamIn.getFtlDescription();
            String bussiPackage = subTabParamIn.getEntityPackage();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String serviceImplPackage = basePackage + "." + bussiPackage + ".service.impl";
            String serviceImplPath = serviceImplPackage.replace(".", "\\") + "\\" + className + "ServiceImpl.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            root.put("servicePackage", servicePackage);
            root.put("serviceImplPackage", serviceImplPackage);
            String foreignKeyTable = subTabParamIn.getForeignKey().toUpperCase();
            String foreignKeyUpper = createBean.getTablesNameToClassName(foreignKeyTable);
            root.put("foreignKeyTable", foreignKeyTable);
            root.put("foreignKeyUpper", foreignKeyUpper);
            String foreignKey = foreignKeyUpper.substring(0, 1).toLowerCase() + foreignKeyUpper.substring(1, foreignKeyUpper.length());
            root.put("foreignKey", foreignKey);
            FreemarkerEngine.createFileByFTL(cfg, root, "serviceImplSubClass.ftl", pathSrc, serviceImplPath);
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    private static void createService(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        createServiceMain(codeParamEntityIn);
        Iterator var3 = subTabParamIn.iterator();

        while(var3.hasNext()) {
            SubTableEntity sub = (SubTableEntity)var3.next();
            createServiceSub(codeParamEntityIn.getProjectName(), sub);
        }

    }

    private static void createServiceMain(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String servicePath = servicePackage.replace(".", "\\") + "\\" + className + "Service.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            root.put("servicePackage", servicePackage);
            FreemarkerEngine.createFileByFTL(cfg, root, "serviceMainClass.ftl", pathSrc, servicePath);
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }

    private static void createServiceSub(String projectName, SubTableEntity subTabParamIn) {
        try {
            String e = subTabParamIn.getTableName();
            String className = subTabParamIn.getEntityName();
            String codeName = subTabParamIn.getFtlDescription();
            String bussiPackage = subTabParamIn.getEntityPackage();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String servicePackage = basePackage + "." + bussiPackage + ".service";
            String servicePath = servicePackage.replace(".", "\\") + "\\" + className + "Service.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            root.put("servicePackage", servicePackage);
            String foreignKeyTable = subTabParamIn.getForeignKey().toUpperCase();
            String foreignKeyUpper = createBean.getTablesNameToClassName(foreignKeyTable);
            root.put("foreignKeyTable", foreignKeyTable);
            root.put("foreignKeyUpper", foreignKeyUpper);
            String foreignKey = foreignKeyUpper.substring(0, 1).toLowerCase() + foreignKeyUpper.substring(1, foreignKeyUpper.length());
            root.put("foreignKey", foreignKey);
            FreemarkerEngine.createFileByFTL(cfg, root, "serviceSubClass.ftl", pathSrc, servicePath);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    private static void createSql(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        createSqlMain(codeParamEntityIn);
        Iterator var3 = subTabParamIn.iterator();

        while(var3.hasNext()) {
            SubTableEntity sub = (SubTableEntity)var3.next();
            createSqlSub(codeParamEntityIn.getProjectName(), sub);
        }

    }

    private static void createSqlMain(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String sqlPackage = basePackage + "." + bussiPackage + ".sql";
            String sqlConditionPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_condition.sql";
            String sqlGetAllPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_getAll.sql";
            String sqlInsertPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_insert.sql";
            String sqlUpdatePath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_update.sql";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("lowerName", lowerName);
            List columnDatas = createBean.getColumnDatas(e);
            root.put("columnDatas", columnDatas);
            String tablesAsName = createBean.getTablesASName(e);
            root.put("tablesAsName", tablesAsName);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoConditionSql.ftl", pathSrc, sqlConditionPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoGetAllSql.ftl", pathSrc, sqlGetAllPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoInsertSql.ftl", pathSrc, sqlInsertPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoUpdateSql.ftl", pathSrc, sqlUpdatePath);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    private static void createSqlSub(String projectName, SubTableEntity subTabParamIn) {
        try {
            String e = subTabParamIn.getTableName();
            String className = subTabParamIn.getEntityName();
            String codeName = subTabParamIn.getFtlDescription();
            String bussiPackage = subTabParamIn.getEntityPackage();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String sqlPackage = basePackage + "." + bussiPackage + ".sql";
            String sqlConditionPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_condition.sql";
            String sqlGetAllPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_getAll.sql";
            String sqlInsertPath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_insert.sql";
            String sqlUpdatePath = sqlPackage.replace(".", "\\") + "\\" + className + "Dao_update.sql";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("lowerName", lowerName);
            List columnDatas = createBean.getColumnDatas(e);
            root.put("columnDatas", columnDatas);
            String tablesAsName = createBean.getTablesASName(e);
            root.put("tablesAsName", tablesAsName);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoConditionSql.ftl", pathSrc, sqlConditionPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoGetAllSql.ftl", pathSrc, sqlGetAllPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoInsertSql.ftl", pathSrc, sqlInsertPath);
            FreemarkerEngine.createFileByFTL(cfg, root, "minidaoUpdateSql.ftl", pathSrc, sqlUpdatePath);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    private static void createDao(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        createDaoMain(codeParamEntityIn);
        Iterator var3 = subTabParamIn.iterator();

        while(var3.hasNext()) {
            SubTableEntity sub = (SubTableEntity)var3.next();
            createDaoSub(codeParamEntityIn.getProjectName(), sub);
        }

    }

    private static void createDaoMain(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String daoPath = daoPackage.replace(".", "\\") + "\\" + className + "Dao.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            FreemarkerEngine.createFileByFTL(cfg, root, "daoMainClass.ftl", pathSrc, daoPath);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static void createDaoSub(String projectName, SubTableEntity subTabParamIn) {
        try {
            String e = subTabParamIn.getTableName();
            String className = subTabParamIn.getEntityName();
            String codeName = subTabParamIn.getFtlDescription();
            String bussiPackage = subTabParamIn.getEntityPackage();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String daoPackage = basePackage + "." + bussiPackage + ".dao";
            String daoPath = daoPackage.replace(".", "\\") + "\\" + className + "Dao.java";
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("lowerName", lowerName);
            root.put("daoPackage", daoPackage);
            String foreignKeyTable = subTabParamIn.getForeignKey().toUpperCase();
            String foreignKeyUpper = createBean.getTablesNameToClassName(foreignKeyTable);
            root.put("foreignKeyTable", foreignKeyTable);
            root.put("foreignKeyUpper", foreignKeyUpper);
            String foreignKey = foreignKeyUpper.substring(0, 1).toLowerCase() + foreignKeyUpper.substring(1, foreignKeyUpper.length());
            root.put("foreignKey", foreignKey);
            FreemarkerEngine.createFileByFTL(cfg, root, "daoSubClass.ftl", pathSrc, daoPath);
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    private static void createEntity(CodeParamEntity codeParamEntityIn, List<SubTableEntity> subTabParamIn) {
        createEntityMain(codeParamEntityIn);
        Iterator var3 = subTabParamIn.iterator();

        while(var3.hasNext()) {
            SubTableEntity sub = (SubTableEntity)var3.next();
            createEntitySub(codeParamEntityIn.getProjectName(), sub);
        }

    }

    private static void createEntityMain(CodeParamEntity codeParamEntityIn) {
        try {
            String e = codeParamEntityIn.getTableName();
            String className = codeParamEntityIn.getEntityName();
            String codeName = codeParamEntityIn.getFtlDescription();
            String bussiPackage = codeParamEntityIn.getEntityPackage();
            String projectName = codeParamEntityIn.getProjectName();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String domainPath = domainPackage.replace(".", "\\") + "\\" + className + "Entity.java";
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("feilds", createBean.getBeanFeilds(e, className));
            FreemarkerEngine.createFileByFTL(cfg, root, "domainClass.ftl", pathSrc, domainPath);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    private static void createEntitySub(String projectName, SubTableEntity subTabParamIn) {
        try {
            String e = subTabParamIn.getTableName();
            String className = subTabParamIn.getEntityName();
            String codeName = subTabParamIn.getFtlDescription();
            String bussiPackage = subTabParamIn.getEntityPackage();
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            basePackage = basePackage + "." + projectName;
            String domainPackage = basePackage + "." + bussiPackage + ".entity";
            String domainPath = domainPackage.replace(".", "\\") + "\\" + className + "Entity.java";
            root.put("className", className);
            root.put("codeName", codeName);
            root.put("tableName", e);
            root.put("domainPackage", domainPackage);
            root.put("feilds", createBean.getBeanFeilds(e, className));
            FreemarkerEngine.createFileByFTL(cfg, root, "domainClass.ftl", pathSrc, domainPath);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public static String getClassPath() {
        String path = (new CodeGenerateFactoryOneToMany()).getClass().getResource("/").getPath();
        return path;
    }
}
