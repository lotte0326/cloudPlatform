//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.imports.ExcelImportServer;
import org.jeecgframework.poi.excel.imports.sax.SaxReadExcel;
import org.jeecgframework.poi.excel.imports.sax.parse.ISaxRowRead;
import org.jeecgframework.poi.handler.inter.IExcelReadRowHanlder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExcelImportUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    private ExcelImportUtil() {
    }

    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        List result = null;

        try {
            in = new FileInputStream(file);
            result = (new ExcelImportServer()).importExcelByIs(in, pojoClass, params).getList();
        } catch (Exception var14) {
            LOGGER.error(var14.getMessage(), var14);
        } finally {
            try {
                in.close();
            } catch (IOException var13) {
                LOGGER.error(var13.getMessage(), var13);
            }

        }

        return result;
    }

    public static <T> List<T> importExcel(InputStream inputstream, Class<?> pojoClass, ImportParams params) throws Exception {
        return (new ExcelImportServer()).importExcelByIs(inputstream, pojoClass, params).getList();
    }

    public static <T> ExcelImportResult<T> importExcelVerify(InputStream inputstream, Class<?> pojoClass, ImportParams params) throws Exception {
        return (new ExcelImportServer()).importExcelByIs(inputstream, pojoClass, params);
    }

    public static <T> ExcelImportResult<T> importExcelVerify(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            ExcelImportResult e = (new ExcelImportServer()).importExcelByIs(in, pojoClass, params);
            return e;
        } catch (Exception var14) {
            LOGGER.error(var14.getMessage(), var14);
        } finally {
            try {
                in.close();
            } catch (IOException var13) {
                LOGGER.error(var13.getMessage(), var13);
            }

        }

        return null;
    }

    public static <T> List<T> importExcelBySax(InputStream inputstream, Class<?> pojoClass, ImportParams params) {
        return (new SaxReadExcel()).readExcel(inputstream, pojoClass, params, (ISaxRowRead)null, (IExcelReadRowHanlder)null);
    }

    public static void importExcelBySax(InputStream inputstream, Class<?> pojoClass, ImportParams params, IExcelReadRowHanlder hanlder) {
        (new SaxReadExcel()).readExcel(inputstream, pojoClass, params, (ISaxRowRead)null, hanlder);
    }

    public static <T> List<T> importExcelBySax(InputStream inputstream, ISaxRowRead rowRead) {
        return (new SaxReadExcel()).readExcel(inputstream, (Class)null, (ImportParams)null, rowRead, (IExcelReadRowHanlder)null);
    }
}
