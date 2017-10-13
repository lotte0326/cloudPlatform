//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.jeecgframework.poi.excel.entity.params.ExcelImportEntity;
import org.jeecgframework.poi.excel.entity.sax.SaxReadCellEntity;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.jeecgframework.poi.exception.excel.enums.ExcelImportEnum;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellValueServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CellValueServer.class);
    private List<String> hanlderList = null;

    public CellValueServer() {
    }

    private Object getCellValue(String xclass, Cell cell, ExcelImportEntity entity) {
        if(cell == null) {
            return "";
        } else {
            Object result = null;
            if(!"class java.util.Date".equals(xclass) && !"class java.sql.Time".equals(xclass)) {
                if(0 == cell.getCellType()) {
                    result = Double.valueOf(cell.getNumericCellValue());
                } else if(4 == cell.getCellType()) {
                    result = Boolean.valueOf(cell.getBooleanCellValue());
                } else {
                    result = cell.getStringCellValue();
                }
            } else {
                if(0 == cell.getCellType()) {
                    result = cell.getDateCellValue();
                } else {
                    cell.setCellType(1);
                    result = this.getDateData(entity, cell.getStringCellValue());
                }

                if("class java.sql.Time".equals(xclass)) {
                    result = new Time(((Date)result).getTime());
                }
            }

            return result;
        }
    }

    private Date getDateData(ExcelImportEntity entity, String value) {
        if(StringUtils.isNotEmpty(entity.getFormat()) && StringUtils.isNotEmpty(value)) {
            SimpleDateFormat format = new SimpleDateFormat(entity.getFormat());

            try {
                return format.parse(value);
            } catch (ParseException var5) {
                LOGGER.error("时间格式化失败,格式化:{},值:{}", entity.getFormat(), value);
                throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
            }
        } else {
            return null;
        }
    }

    public Object getValue(IExcelDataHandler dataHanlder, Object object, Cell cell, Map<String, ExcelImportEntity> excelParams, String titleString) throws Exception {
        ExcelImportEntity entity = (ExcelImportEntity)excelParams.get(titleString);
        String xclass = "class java.lang.Object";
        if(!(object instanceof Map)) {
            Method result = entity.getMethods() != null && entity.getMethods().size() > 0?(Method)entity.getMethods().get(entity.getMethods().size() - 1):entity.getMethod();
            Type[] ts = result.getGenericParameterTypes();
            xclass = ts[0].toString();
        }

        Object result1 = this.getCellValue(xclass, cell, entity);
        if(entity != null) {
            result1 = this.hanlderSuffix(entity.getSuffix(), result1);
            result1 = this.replaceValue(entity.getReplace(), result1);
        }

        result1 = this.hanlderValue(dataHanlder, object, result1, titleString);
        return this.getValueByType(xclass, result1, entity);
    }

    public Object getValue(IExcelDataHandler dataHanlder, Object object, SaxReadCellEntity cellEntity, Map<String, ExcelImportEntity> excelParams, String titleString) {
        ExcelImportEntity entity = (ExcelImportEntity)excelParams.get(titleString);
        Method setMethod = entity.getMethods() != null && entity.getMethods().size() > 0?(Method)entity.getMethods().get(entity.getMethods().size() - 1):entity.getMethod();
        Type[] ts = setMethod.getGenericParameterTypes();
        String xclass = ts[0].toString();
        Object result = cellEntity.getValue();
        result = this.hanlderSuffix(entity.getSuffix(), result);
        result = this.replaceValue(entity.getReplace(), result);
        result = this.hanlderValue(dataHanlder, object, result, titleString);
        return this.getValueByType(xclass, result, entity);
    }

    private Object hanlderSuffix(String suffix, Object result) {
        if(StringUtils.isNotEmpty(suffix) && result != null && result.toString().endsWith(suffix)) {
            String temp = result.toString();
            return temp.substring(0, temp.length() - suffix.length());
        } else {
            return result;
        }
    }

    private Object getValueByType(String xclass, Object result, ExcelImportEntity entity) {
        try {
            return "class java.util.Date".equals(xclass)?result:(!"class java.lang.Boolean".equals(xclass) && !"boolean".equals(xclass)?(!"class java.lang.Double".equals(xclass) && !"double".equals(xclass)?(!"class java.lang.Long".equals(xclass) && !"long".equals(xclass)?(!"class java.lang.Float".equals(xclass) && !"float".equals(xclass)?(!"class java.lang.Integer".equals(xclass) && !"int".equals(xclass)?("class java.math.BigDecimal".equals(xclass)?new BigDecimal(String.valueOf(result)):("class java.lang.String".equals(xclass)?(result instanceof String?result:(result instanceof Double?PoiPublicUtil.doubleToString((Double)result):String.valueOf(result))):result)):Integer.valueOf(String.valueOf(result))):Float.valueOf(String.valueOf(result))):Long.valueOf(String.valueOf(result))):Double.valueOf(String.valueOf(result))):Boolean.valueOf(String.valueOf(result)));
        } catch (Exception var5) {
            LOGGER.error(var5.getMessage(), var5);
            throw new ExcelImportException(ExcelImportEnum.GET_VALUE_ERROR);
        }
    }

    private Object hanlderValue(IExcelDataHandler dataHanlder, Object object, Object result, String titleString) {
        if(dataHanlder != null && dataHanlder.getNeedHandlerFields() != null && dataHanlder.getNeedHandlerFields().length != 0) {
            if(this.hanlderList == null) {
                this.hanlderList = Arrays.asList(dataHanlder.getNeedHandlerFields());
            }

            return this.hanlderList.contains(titleString)?dataHanlder.importHandler(object, titleString, result):result;
        } else {
            return result;
        }
    }

    private Object replaceValue(String[] replace, Object result) {
        if(replace != null && replace.length > 0) {
            String temp = String.valueOf(result);

            for(int i = 0; i < replace.length; ++i) {
                String[] tempArr = replace[i].split("_");
                if(temp.equals(tempArr[0])) {
                    return tempArr[1];
                }
            }
        }

        return result;
    }
}
