//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ognl.Ognl;
import ognl.OgnlException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.aspect.EmptyInterceptor;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.minidao.spring.rowMapper.MiniColumnMapRowMapper;
import org.jeecgframework.minidao.spring.rowMapper.MiniColumnOriginalMapRowMapper;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.jeecgframework.minidao.util.MiniDaoUtil;
import org.jeecgframework.minidao.util.ParameterNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class MiniDaoHandler implements InvocationHandler {
    private static final Logger logger = Logger.getLogger(MiniDaoHandler.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String UPPER_KEY = "upper";
    private String LOWER_KEY = "lower";
    private String keyType = "origin";
    private boolean formatSql = false;
    private boolean showSql = false;
    private String dbType;
    private EmptyInterceptor emptyInterceptor;

    public MiniDaoHandler() {
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnObj = null;
        String templateSql = null;
        HashMap sqlParamsMap = new HashMap();
        MiniDaoPage pageSetting = new MiniDaoPage();
        templateSql = this.installDaoMetaData(pageSetting, method, sqlParamsMap, args);
        String executeSql = this.parseSqlTemplate(method, templateSql, sqlParamsMap);
        Map sqlMap = this.installPlaceholderSqlParam(executeSql, sqlParamsMap);

        try {
            returnObj = this.getReturnMinidaoResult(this.dbType, pageSetting, method, executeSql, sqlMap);
        } catch (Exception var11) {
            returnObj = null;
            if(!(var11 instanceof EmptyResultDataAccessException)) {
                var11.printStackTrace();
                throw var11;
            }
        }

        if(this.showSql) {
            System.out.println("MiniDao-SQL:\n\n" + executeSql);
            logger.info("MiniDao-SQL:\n\n" + executeSql);
        }

        return returnObj;
    }

    private static boolean checkActiveKey(String methodName) {
        String[] keys = "insert,add,create,update,modify,store,delete,remove".split(",");
        String[] var5 = keys;
        int var4 = keys.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String s = var5[var3];
            if(methodName.startsWith(s)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkActiveSql(String sql) {
        sql = sql.trim().toLowerCase();
        String[] keys = "insert,add,create,update,modify,store,delete,remove".split(",");
        String[] var5 = keys;
        int var4 = keys.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String s = var5[var3];
            if(sql.startsWith(s)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkBatchKey(String methodName) {
        String[] keys = "batch".split(",");
        String[] var5 = keys;
        int var4 = keys.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String s = var5[var3];
            if(methodName.startsWith(s)) {
                return true;
            }
        }

        return false;
    }

    private void addResulArray(int[] result, int index, int[] arr) {
        int length = arr.length;

        for(int i = 0; i < length; ++i) {
            result[index - length + i] = arr[i];
        }

    }

    private int[] batchUpdate(String executeSql) {
        String[] sqls = executeSql.split(";");
        if(sqls.length < 100) {
            return this.jdbcTemplate.batchUpdate(sqls);
        } else {
            int[] result = new int[sqls.length];
            ArrayList sqlList = new ArrayList();

            for(int i = 0; i < sqls.length; ++i) {
                sqlList.add(sqls[i]);
                if(i % 100 == 0) {
                    this.addResulArray(result, i + 1, this.jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
                    sqlList.clear();
                }
            }

            this.addResulArray(result, sqls.length, this.jdbcTemplate.batchUpdate((String[])sqlList.toArray(new String[0])));
            return result;
        }
    }

    private RowMapper<Map<String, Object>> getColumnMapRowMapper() {
        return (RowMapper)(this.getKeyType().equalsIgnoreCase(this.LOWER_KEY)?new MiniColumnMapRowMapper():(this.getKeyType().equalsIgnoreCase(this.UPPER_KEY)?new ColumnMapRowMapper():new MiniColumnOriginalMapRowMapper()));
    }

    private String getCountSql(String sql) {
        sql = this.removeOrderBy(sql);
        return "select count(0) from (" + sql + ") tmp_count";
    }

    public String removeOrderBy(String sql) {
        if(sql == null) {
            return null;
        } else {
            sql = sql.replaceAll("(?i)order by [\\s|\\S]+$", "");
            return sql;
        }
    }

    public String getDbType() {
        return this.dbType;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

    private Object getReturnMinidaoResult(String dbType, MiniDaoPage pageSetting, Method method, String executeSql, Map<String, Object> paramMap) {
        String methodName = method.getName();
        if(!checkActiveKey(methodName) && !checkActiveSql(executeSql)) {
            if(checkBatchKey(methodName)) {
                return this.batchUpdate(executeSql);
            } else {
                Class returnType = method.getReturnType();
                if(returnType.isPrimitive()) {
                    Number rm2 = (Number)this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, BigDecimal.class);
                    return "int".equals(returnType.getCanonicalName())?Integer.valueOf(rm2.intValue()):("long".equals(returnType.getCanonicalName())?Long.valueOf(rm2.longValue()):("double".equals(returnType.getCanonicalName())?Double.valueOf(rm2.doubleValue()):null));
                } else if(!returnType.isAssignableFrom(List.class) && !returnType.isAssignableFrom(MiniDaoPage.class)) {
                    if(returnType.isAssignableFrom(Map.class)) {
                        return paramMap != null?this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, this.getColumnMapRowMapper()):this.jdbcTemplate.queryForObject(executeSql, this.getColumnMapRowMapper());
                    } else if(returnType.isAssignableFrom(String.class)) {
                        return paramMap != null?this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, String.class):this.jdbcTemplate.queryForObject(executeSql, String.class);
                    } else if(MiniDaoUtil.isWrapClass(returnType)) {
                        return paramMap != null?this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, returnType):this.jdbcTemplate.queryForObject(executeSql, returnType);
                    } else {
                        BeanPropertyRowMapper rm1 = BeanPropertyRowMapper.newInstance(returnType);
                        return paramMap != null?this.namedParameterJdbcTemplate.queryForObject(executeSql, paramMap, rm1):this.jdbcTemplate.queryForObject(executeSql, rm1);
                    }
                } else {
                    int rm = pageSetting.getPage();
                    int rows = pageSetting.getRows();
                    if(rm != 0 && rows != 0) {
                        if(returnType.isAssignableFrom(MiniDaoPage.class)) {
                            if(paramMap != null) {
                                pageSetting.setTotal(((Integer)this.namedParameterJdbcTemplate.queryForObject(this.getCountSql(executeSql), paramMap, Integer.class)).intValue());
                            } else {
                                pageSetting.setTotal(((Integer)this.jdbcTemplate.queryForObject(this.getCountSql(executeSql), Integer.class)).intValue());
                            }
                        }

                        executeSql = MiniDaoUtil.createPageSql(dbType, executeSql, rm, rows);
                    }

                    RowMapper resultType = this.getListRealType(method);
                    List list;
                    if(paramMap != null) {
                        list = this.namedParameterJdbcTemplate.query(executeSql, paramMap, resultType);
                    } else {
                        list = this.jdbcTemplate.query(executeSql, resultType);
                    }

                    if(returnType.isAssignableFrom(MiniDaoPage.class)) {
                        pageSetting.setResults(list);
                        return pageSetting;
                    } else {
                        return list;
                    }
                }
            }
        } else {
            return paramMap != null?Integer.valueOf(this.namedParameterJdbcTemplate.update(executeSql, paramMap)):Integer.valueOf(this.jdbcTemplate.update(executeSql));
        }
    }

    private RowMapper<?> getListRealType(Method method) {
        ResultType resultType = (ResultType)method.getAnnotation(ResultType.class);
        if(resultType != null) {
            return (RowMapper)(resultType.value().equals(Map.class)?this.getColumnMapRowMapper():BeanPropertyRowMapper.newInstance(resultType.value()));
        } else {
            String genericReturnType = method.getGenericReturnType().toString();
            String realType = genericReturnType.replace("java.util.List", "").replace("<", "").replace(">", "");
            if(realType.contains("java.util.Map")) {
                return this.getColumnMapRowMapper();
            } else if(realType.length() > 0) {
                try {
                    return BeanPropertyRowMapper.newInstance(Class.forName(realType));
                } catch (ClassNotFoundException var6) {
                    logger.error(var6.getMessage(), var6.fillInStackTrace());
                    throw new RuntimeException("minidao get class error ,class name is:" + realType);
                }
            } else {
                return this.getColumnMapRowMapper();
            }
        }
    }

    private String installDaoMetaData(MiniDaoPage pageSetting, Method method, Map<String, Object> sqlParamsMap, Object[] args) throws Exception {
        String templateSql;
        if(this.emptyInterceptor != null && args != null && args.length == 1) {
            templateSql = method.getName();
            Object arguments_flag = args[0];
            Field[] sql = arguments_flag.getClass().getDeclaredFields();
            if(templateSql.startsWith("insert") && this.emptyInterceptor != null) {
                this.emptyInterceptor.onInsert(sql, arguments_flag);
            }

            if(templateSql.startsWith("update") && this.emptyInterceptor != null) {
                this.emptyInterceptor.onUpdate(sql, arguments_flag);
            }
        }

        templateSql = null;
        boolean var13 = method.isAnnotationPresent(Arguments.class);
        int args_num;
        String v;
        int var10;
        int var11;
        String[] var12;
        if(var13) {
            Arguments var15 = (Arguments)method.getAnnotation(Arguments.class);
            logger.debug("@Arguments------------------------------------------" + Arrays.toString(var15.value()));
            if(var15.value().length != args.length) {
                throw new Exception("注释标签@Arguments参数数目，与方法参数数目不相等~");
            }

            args_num = 0;
            var11 = (var12 = var15.value()).length;

            for(var10 = 0; var10 < var11; ++var10) {
                v = var12[var10];
                if(v.equalsIgnoreCase("page")) {
                    pageSetting.setPage(Integer.parseInt(args[args_num].toString()));
                }

                if(v.equalsIgnoreCase("rows")) {
                    pageSetting.setRows(Integer.parseInt(args[args_num].toString()));
                }

                sqlParamsMap.put(v, args[args_num]);
                ++args_num;
            }
        } else if(args != null && args.length >= 1) {
            String[] var14 = ParameterNameUtils.getMethodParameterNamesByAnnotation(method);
            if(var14 == null || var14.length == 0) {
                throw new Exception("方法参数数目>=2，必须使用：方法标签@Arguments 或  参数标签@param");
            }

            if(var14.length != args.length) {
                throw new Exception("方法参数数目>=2，参数必须使用：标签@param");
            }

            args_num = 0;
            var12 = var14;
            var11 = var14.length;

            for(var10 = 0; var10 < var11; ++var10) {
                v = var12[var10];
                if(v == null) {
                    throw new Exception("Dao接口定义，所有参数必须使用@param标签~");
                }

                if(v.equalsIgnoreCase("page")) {
                    pageSetting.setPage(Integer.parseInt(args[args_num].toString()));
                }

                if(v.equalsIgnoreCase("rows")) {
                    pageSetting.setRows(Integer.parseInt(args[args_num].toString()));
                }

                sqlParamsMap.put(v, args[args_num]);
                ++args_num;
            }
        } else if(args != null && args.length == 1) {
            sqlParamsMap.put("dto", args[0]);
        }

        if(method.isAnnotationPresent(Sql.class)) {
            Sql var16 = (Sql)method.getAnnotation(Sql.class);
            if(StringUtils.isNotEmpty(var16.value())) {
                templateSql = var16.value();
            }

            logger.debug("@Sql------------------------------------------" + var16.value());
        }

        return templateSql;
    }

    private Map<String, Object> installPlaceholderSqlParam(String executeSql, Map sqlParamsMap) throws OgnlException {
        HashMap map = new HashMap();
        String regEx = ":[ tnx0Bfr]*[0-9a-z.A-Z_]+";
        Pattern pat = Pattern.compile(regEx);
        Matcher m = pat.matcher(executeSql);

        while(m.find()) {
            logger.debug(" Match [" + m.group() + "] at positions " + m.start() + "-" + (m.end() - 1));
            String ognl_key = m.group().replace(":", "").trim();
            logger.debug(" --- minidao --- 解析参数 --- " + ognl_key);
            map.put(ognl_key, Ognl.getValue(ognl_key, sqlParamsMap));
        }

        return map;
    }

    public boolean isFormatSql() {
        return this.formatSql;
    }

    private String parseSqlTemplate(Method method, String templateSql, Map<String, Object> sqlParamsMap) {
        String executeSql = null;
        if(StringUtils.isNotEmpty(templateSql)) {
            executeSql = FreemarkerParseFactory.parseTemplateContent(templateSql, sqlParamsMap);
        } else {
            String sqlTempletPath = method.getDeclaringClass().getName().replace(".", "/").replace("/dao/", "/sql/") + "_" + method.getName() + ".sql";
            if(!FreemarkerParseFactory.isExistTemplate(sqlTempletPath)) {
                sqlTempletPath = method.getDeclaringClass().getName().replace(".", "/") + "_" + method.getName() + ".sql";
            }

            logger.debug("MiniDao-SQL-Path:" + sqlTempletPath);
            executeSql = FreemarkerParseFactory.parseTemplate(sqlTempletPath, sqlParamsMap);
        }

        return executeSql;
    }

    public List<Object> procedureParamsList(Method method, Object[] args) throws Exception {
        Object procedureParamsList = new ArrayList();
        boolean arguments_flag = method.isAnnotationPresent(Arguments.class);
        if(arguments_flag) {
            Arguments arguments = (Arguments)method.getAnnotation(Arguments.class);
            logger.debug("@Arguments------------------------------------------" + Arrays.toString(arguments.value()));
            if(arguments.value().length > args.length) {
                throw new Exception("[注释标签]参数数目，不能大于[方法参数]参数数目");
            }

            for(int i = 0; i < arguments.value().length; ++i) {
                ((List)procedureParamsList).add(args[i]);
            }
        } else {
            procedureParamsList = Arrays.asList(args);
        }

        return (List)procedureParamsList;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setFormatSql(boolean formatSql) {
        this.formatSql = formatSql;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public EmptyInterceptor getEmptyInterceptor() {
        return this.emptyInterceptor;
    }

    public void setEmptyInterceptor(EmptyInterceptor emptyInterceptor) {
        this.emptyInterceptor = emptyInterceptor;
    }
}
