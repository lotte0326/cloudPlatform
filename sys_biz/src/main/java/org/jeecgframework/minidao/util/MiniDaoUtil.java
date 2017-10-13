//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import org.apache.log4j.Logger;

public class MiniDaoUtil {
    public static final String DATABSE_TYPE_MYSQL = "mysql";
    public static final String DATABSE_TYPE_POSTGRE = "postgresql";
    public static final String DATABSE_TYPE_ORACLE = "oracle";
    public static final String DATABSE_TYPE_SQLSERVER = "sqlserver";
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
    public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";
    private static final Logger logger = Logger.getLogger(MiniDaoUtil.class);

    public MiniDaoUtil() {
    }

    public static String createPageSql(String dbType, String sql, int page, int rows) {
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[]{sql, String.valueOf(beginNum), String.valueOf(rows)};
        if(dbType != null && !"".equals(dbType)) {
            if(dbType.indexOf("mysql") != -1) {
                sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {1},{2}", (Object[])sqlParam);
            } else if(dbType.indexOf("postgresql") != -1) {
                sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {2} offset {1}", (Object[])sqlParam);
            } else {
                int beginIndex = (page - 1) * rows;
                int endIndex = beginIndex + rows;
                sqlParam[2] = Integer.toString(beginIndex);
                sqlParam[1] = Integer.toString(endIndex);
                if(dbType.indexOf("oracle") != -1) {
                    sql = MessageFormat.format("select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}", (Object[])sqlParam);
                } else if(dbType.indexOf("sqlserver") != -1) {
                    sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
                    sql = MessageFormat.format("select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}", (Object[])sqlParam);
                }
            }

            return sql;
        } else {
            throw new RuntimeException("org.jeecgframework.minidao.aop.MiniDaoHandler:(数据库类型:dbType)没有设置,请检查配置文件");
        }
    }

    private static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex?15:6);
    }

    public static String getFirstSmall(String name) {
        name = name.trim();
        return name.length() >= 2?name.substring(0, 1).toLowerCase() + name.substring(1):name.toLowerCase();
    }

    public static String getMethodSqlLogicJar(String sqlurl) {
        StringBuffer sb = new StringBuffer();
        InputStream is = MiniDaoUtil.class.getResourceAsStream(sqlurl);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = "";

        try {
            while((s = br.readLine()) != null) {
                sb.append(s + " ");
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean isAbstract(Method method) {
        int mod = method.getModifiers();
        return Modifier.isAbstract(mod);
    }

    public static boolean isWrapClass(Class<?> clz) {
        try {
            return ((Class)clz.getField("TYPE").get((Object)null)).isPrimitive();
        } catch (Exception var2) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        logger.debug(Boolean.valueOf(isWrapClass(Long.class)));
        logger.debug(Boolean.valueOf(isWrapClass(Integer.class)));
        logger.debug(Boolean.valueOf(isWrapClass(String.class)));
    }
}
