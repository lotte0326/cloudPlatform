//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.export.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.easypoi.service.EasypoiDictServiceI;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;
import org.jeecgframework.poi.util.PoiPublicUtil;

public class ExportBase {
    protected IExcelDataHandler dataHanlder;
    protected List<String> needHanlderList;

    public ExportBase() {
    }

    private ExcelExportEntity createExcelExportEntity(Field field, String targetId, Class<?> pojoClass, List<Method> getMethods) throws Exception {
        Excel excel = (Excel)field.getAnnotation(Excel.class);
        ExcelExportEntity excelEntity = new ExcelExportEntity();
        excelEntity.setType(excel.type());
        this.getExcelField(targetId, field, excelEntity, excel, pojoClass);
        if(getMethods != null) {
            ArrayList newMethods = new ArrayList();
            newMethods.addAll(getMethods);
            newMethods.add(excelEntity.getMethod());
            excelEntity.setMethods(newMethods);
        }

        return excelEntity;
    }

    private Object formatValue(Object value, ExcelExportEntity entity) throws Exception {
        Date temp = null;
        SimpleDateFormat format;
        if(value instanceof String) {
            format = new SimpleDateFormat(entity.getDatabaseFormat());
            temp = format.parse(value.toString());
        } else if(value instanceof Date) {
            temp = (Date)value;
        }

        if(temp != null) {
            format = new SimpleDateFormat(entity.getFormat());
            value = format.format(temp);
        }

        return value;
    }

    public void getAllExcelField(String[] exclusions, String targetId, Field[] fields, List<ExcelExportEntity> excelParams, Class<?> pojoClass, List<Method> getMethods) throws Exception {
        List exclusionsList = exclusions != null?Arrays.asList(exclusions):null;

        for(int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            if(!PoiPublicUtil.isNotUserExcelUserThis(exclusionsList, field, targetId)) {
                if(field.getAnnotation(Excel.class) != null) {
                    excelParams.add(this.createExcelExportEntity(field, targetId, pojoClass, getMethods));
                } else if(PoiPublicUtil.isCollection(field.getType())) {
                    ExcelCollection newMethods = (ExcelCollection)field.getAnnotation(ExcelCollection.class);
                    ParameterizedType excel = (ParameterizedType)field.getGenericType();
                    Class clz = (Class)excel.getActualTypeArguments()[0];
                    ArrayList list = new ArrayList();
                    this.getAllExcelField(exclusions, StringUtils.isNotEmpty(newMethods.id())?newMethods.id():targetId, PoiPublicUtil.getClassFields(clz), list, clz, (List)null);
                    ExcelExportEntity excelEntity = new ExcelExportEntity();
                    excelEntity.setName(this.getExcelName(newMethods.name(), targetId));
                    excelEntity.setOrderNum(this.getCellOrder(newMethods.orderNum(), targetId));
                    excelEntity.setMethod(PoiPublicUtil.getMethod(field.getName(), pojoClass));
                    excelEntity.setList(list);
                    excelParams.add(excelEntity);
                } else {
                    ArrayList var15 = new ArrayList();
                    if(getMethods != null) {
                        var15.addAll(getMethods);
                    }

                    var15.add(PoiPublicUtil.getMethod(field.getName(), pojoClass));
                    ExcelEntity var16 = (ExcelEntity)field.getAnnotation(ExcelEntity.class);
                    this.getAllExcelField(exclusions, StringUtils.isNotEmpty(var16.id())?var16.id():targetId, PoiPublicUtil.getClassFields(field.getType()), excelParams, field.getType(), var15);
                }
            }
        }

    }

    public int getCellOrder(String orderNum, String targetId) {
        if(!this.isInteger(orderNum) && targetId != null) {
            String[] arr = orderNum.split(",");
            String[] arr$ = arr;
            int len$ = arr.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String str = arr$[i$];
                String[] temp = str.split("_");
                if(targetId.equals(temp[1])) {
                    return Integer.valueOf(temp[0]).intValue();
                }
            }

            return 0;
        } else {
            return Integer.valueOf(orderNum).intValue();
        }
    }

    public Object getCellValue(ExcelExportEntity entity, Object obj) throws Exception {
        Object value;
        if(obj instanceof Map) {
            value = ((Map)obj).get(entity.getKey());
        } else {
            value = entity.getMethods() != null?this.getFieldBySomeMethod(entity.getMethods(), obj):entity.getMethod().invoke(obj, new Object[0]);
        }

        if(StringUtils.isNotEmpty(entity.getFormat())) {
            value = this.formatValue(value, entity);
        }

        if(entity.getReplace() != null && entity.getReplace().length > 0) {
            value = this.replaceValue(entity.getReplace(), String.valueOf(value));
        }

        if(this.needHanlderList != null && this.needHanlderList.contains(entity.getName())) {
            value = this.dataHanlder.exportHandler(obj, entity.getName(), value);
        }

        if(StringUtils.isNotEmpty(entity.getSuffix()) && value != null) {
            value = value + entity.getSuffix();
        }

        return value == null?"":value.toString();
    }

    public Collection<?> getListCellValue(ExcelExportEntity entity, Object obj) throws Exception {
        Object value;
        if(obj instanceof Map) {
            value = ((Map)obj).get(entity.getKey());
        } else {
            value = (Collection)entity.getMethod().invoke(obj, new Object[0]);
        }

        return (Collection)value;
    }

    private void getExcelField(String targetId, Field field, ExcelExportEntity excelEntity, Excel excel, Class<?> pojoClass) throws Exception {
        excelEntity.setName(this.getExcelName(excel.name(), targetId));
        excelEntity.setWidth(excel.width());
        excelEntity.setHeight(excel.height());
        excelEntity.setNeedMerge(excel.needMerge());
        excelEntity.setMergeVertical(excel.mergeVertical());
        excelEntity.setMergeRely(excel.mergeRely());
        excelEntity.setReplace(excel.replace());
        if(StringUtils.isNotEmpty(excel.dicCode())) {
            EasypoiDictServiceI fieldname = null;

            try {
                fieldname = (EasypoiDictServiceI)ApplicationContextUtil.getContext().getBean(EasypoiDictServiceI.class);
            } catch (Exception var8) {
                ;
            }

            if(fieldname != null) {
                String[] dictReplace = fieldname.queryDict(excel.dictTable(), excel.dicCode(), excel.dicText());
                if(excelEntity.getReplace() != null && dictReplace != null && dictReplace.length != 0) {
                    excelEntity.setReplace(dictReplace);
                }
            }
        }

        excelEntity.setOrderNum(this.getCellOrder(excel.orderNum(), targetId));
        excelEntity.setWrap(excel.isWrap());
        excelEntity.setExportImageType(excel.imageType());
        excelEntity.setSuffix(excel.suffix());
        excelEntity.setDatabaseFormat(excel.databaseFormat());
        excelEntity.setFormat(StringUtils.isNotEmpty(excel.exportFormat())?excel.exportFormat():excel.format());
        excelEntity.setStatistics(excel.isStatistics());
        String fieldname1 = field.getName();
        excelEntity.setMethod(PoiPublicUtil.getMethod(fieldname1, pojoClass));
    }

    public String getExcelName(String exportName, String targetId) {
        if(exportName.indexOf(",") < 0) {
            return exportName;
        } else {
            String[] arr = exportName.split(",");
            String[] arr$ = arr;
            int len$ = arr.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String str = arr$[i$];
                if(str.indexOf(targetId) != -1) {
                    return str.split("_")[0];
                }
            }

            return null;
        }
    }

    public Object getFieldBySomeMethod(List<Method> list, Object t) throws Exception {
        Method m;
        for(Iterator i$ = list.iterator(); i$.hasNext(); t = m.invoke(t, new Object[0])) {
            m = (Method)i$.next();
            if(t == null) {
                t = "";
                break;
            }
        }

        return t;
    }

    public short getRowHeight(List<ExcelExportEntity> excelParams) {
        double maxHeight = 0.0D;

        for(int i = 0; i < excelParams.size(); ++i) {
            maxHeight = maxHeight > ((ExcelExportEntity)excelParams.get(i)).getHeight()?maxHeight:((ExcelExportEntity)excelParams.get(i)).getHeight();
            if(((ExcelExportEntity)excelParams.get(i)).getList() != null) {
                for(int j = 0; j < ((ExcelExportEntity)excelParams.get(i)).getList().size(); ++j) {
                    maxHeight = maxHeight > ((ExcelExportEntity)((ExcelExportEntity)excelParams.get(i)).getList().get(j)).getHeight()?maxHeight:((ExcelExportEntity)((ExcelExportEntity)excelParams.get(i)).getList().get(j)).getHeight();
                }
            }
        }

        return (short)((int)(maxHeight * 50.0D));
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }

    private Object replaceValue(String[] replace, String value) {
        String[] arr$ = replace;
        int len$ = replace.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String str = arr$[i$];
            String[] temp = str.split("_");
            if(value.equals(temp[1])) {
                value = temp[0];
                break;
            }
        }

        return value;
    }

    public void sortAllParams(List<ExcelExportEntity> excelParams) {
        Collections.sort(excelParams);
        Iterator i$ = excelParams.iterator();

        while(i$.hasNext()) {
            ExcelExportEntity entity = (ExcelExportEntity)i$.next();
            if(entity.getList() != null) {
                Collections.sort(entity.getList());
            }
        }

    }
}
