//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports.sax.parse;

import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelCollectionParams;
import org.jeecgframework.poi.excel.entity.params.ExcelImportEntity;
import org.jeecgframework.poi.excel.entity.sax.SaxReadCellEntity;
import org.jeecgframework.poi.excel.imports.CellValueServer;
import org.jeecgframework.poi.excel.imports.base.ImportBaseService;
import org.jeecgframework.poi.excel.imports.sax.parse.ISaxRowRead;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.jeecgframework.poi.handler.inter.IExcelReadRowHanlder;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaxRowRead extends ImportBaseService implements ISaxRowRead {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaxRowRead.class);
    private List list = Lists.newArrayList();
    private Class<?> pojoClass;
    private ImportParams params;
    private Map<Integer, String> titlemap = new HashMap();
    private Object object = null;
    private Map<String, ExcelImportEntity> excelParams = new HashMap();
    private List<ExcelCollectionParams> excelCollection = new ArrayList();
    private String targetId;
    private CellValueServer cellValueServer;
    private IExcelReadRowHanlder hanlder;

    public SaxRowRead(Class<?> pojoClass, ImportParams params, IExcelReadRowHanlder hanlder) {
        this.params = params;
        this.pojoClass = pojoClass;
        this.cellValueServer = new CellValueServer();
        this.hanlder = hanlder;
        this.initParams(pojoClass, params);
    }

    private void initParams(Class<?> pojoClass, ImportParams params) {
        try {
            Field[] e = PoiPublicUtil.getClassFields(pojoClass);
            ExcelTarget etarget = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
            if(etarget != null) {
                this.targetId = etarget.value();
            }

            this.getAllExcelField(this.targetId, e, this.excelParams, this.excelCollection, pojoClass, (List)null);
        } catch (Exception var5) {
            LOGGER.error(var5.getMessage(), var5);
            throw new ExcelImportException(var5.getMessage());
        }
    }

    public <T> List<T> getList() {
        return this.list;
    }

    public void parse(int index, List<SaxReadCellEntity> datas) {
        try {
            if(datas != null && datas.size() != 0) {
                if(index >= this.params.getTitleRows()) {
                    if(index < this.params.getTitleRows() + this.params.getHeadRows()) {
                        this.addHeadData(datas);
                    } else {
                        this.addListData(datas);
                    }

                }
            }
        } catch (Exception var4) {
            LOGGER.error(var4.getMessage(), var4);
            throw new ExcelImportException(var4.getMessage());
        }
    }

    private void addListData(List<SaxReadCellEntity> datas) throws Exception {
        if((datas.get(this.params.getKeyIndex()) == null || StringUtils.isEmpty(String.valueOf(((SaxReadCellEntity)datas.get(this.params.getKeyIndex())).getValue()))) && this.object != null) {
            Iterator var6 = this.excelCollection.iterator();

            while(var6.hasNext()) {
                ExcelCollectionParams var8 = (ExcelCollectionParams)var6.next();
                this.addListContinue(this.object, var8, datas, this.titlemap, this.targetId, this.params);
            }
        } else {
            if(this.object != null && this.hanlder != null) {
                this.hanlder.hanlder(this.object);
            }

            this.object = PoiPublicUtil.createObject(this.pojoClass, this.targetId);
            int i$ = 0;

            for(int param = datas.size(); i$ < param; ++i$) {
                SaxReadCellEntity entity = (SaxReadCellEntity)datas.get(i$);
                String titleString = (String)this.titlemap.get(Integer.valueOf(i$));
                if(this.excelParams.containsKey(titleString)) {
                    this.saveFieldValue(this.params, this.object, entity, this.excelParams, titleString);
                }
            }

            Iterator var7 = this.excelCollection.iterator();

            while(var7.hasNext()) {
                ExcelCollectionParams var9 = (ExcelCollectionParams)var7.next();
                this.addListContinue(this.object, var9, datas, this.titlemap, this.targetId, this.params);
            }

            if(this.hanlder == null) {
                this.list.add(this.object);
            }
        }

    }

    private void addListContinue(Object object, ExcelCollectionParams param, List<SaxReadCellEntity> datas, Map<Integer, String> titlemap, String targetId, ImportParams params) throws Exception {
        Collection collection = (Collection)PoiPublicUtil.getMethod(param.getName(), object.getClass()).invoke(object, new Object[0]);
        Object entity = PoiPublicUtil.createObject(param.getType(), targetId);
        boolean isUsed = false;

        for(int i = 0; i < datas.size(); ++i) {
            String titleString = (String)titlemap.get(Integer.valueOf(i));
            if(param.getExcelParams().containsKey(titleString)) {
                this.saveFieldValue(params, entity, (SaxReadCellEntity)datas.get(i), param.getExcelParams(), titleString);
                isUsed = true;
            }
        }

        if(isUsed) {
            collection.add(entity);
        }

    }

    private void saveFieldValue(ImportParams params, Object object, SaxReadCellEntity entity, Map<String, ExcelImportEntity> excelParams, String titleString) throws Exception {
        Object value = this.cellValueServer.getValue(params.getDataHanlder(), object, entity, excelParams, titleString);
        this.setValues((ExcelImportEntity)excelParams.get(titleString), object, value);
    }

    private void addHeadData(List<SaxReadCellEntity> datas) {
        for(int i = 0; i < datas.size(); ++i) {
            if(StringUtils.isNotEmpty(String.valueOf(((SaxReadCellEntity)datas.get(i)).getValue()))) {
                this.titlemap.put(Integer.valueOf(i), String.valueOf(((SaxReadCellEntity)datas.get(i)).getValue()));
            }
        }

    }
}
