//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.export;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.base.ExcelExportBase;
import org.jeecgframework.poi.excel.export.styler.IExcelExportStyler;
import org.jeecgframework.poi.exception.excel.ExcelExportException;
import org.jeecgframework.poi.exception.excel.enums.ExcelExportEnum;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelExportServer extends ExcelExportBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportServer.class);
    private int MAX_NUM = '\uea60';

    public ExcelExportServer() {
    }

    private int createHeaderAndTitle(ExportParams entity, Sheet sheet, Workbook workbook, List<ExcelExportEntity> excelParams) {
        int rows = 0;
        int feildWidth = this.getFieldWidth(excelParams);
        if(entity.getTitle() != null) {
            rows += this.createHeaderRow(entity, sheet, workbook, feildWidth);
        }

        rows += this.createTitleRow(entity, sheet, workbook, rows, excelParams);
        sheet.createFreezePane(0, rows, 0, rows);
        return rows;
    }

    public int createHeaderRow(ExportParams entity, Sheet sheet, Workbook workbook, int feildWidth) {
        Row row = sheet.createRow(0);
        row.setHeight(entity.getTitleHeight());
        this.createStringCell(row, 0, entity.getTitle(), this.getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), (ExcelExportEntity)null);

        for(int style = 1; style <= feildWidth; ++style) {
            this.createStringCell(row, style, "", this.getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), (ExcelExportEntity)null);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, feildWidth));
        if(entity.getSecondTitle() == null) {
            return 1;
        } else {
            row = sheet.createRow(1);
            row.setHeight(entity.getSecondTitleHeight());
            CellStyle var8 = workbook.createCellStyle();
            var8.setAlignment((short)3);
            this.createStringCell(row, 0, entity.getSecondTitle(), var8, (ExcelExportEntity)null);

            for(int i = 1; i <= feildWidth; ++i) {
                this.createStringCell(row, i, "", this.getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), (ExcelExportEntity)null);
            }

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, feildWidth));
            return 2;
        }
    }

    public void createSheet(Workbook workbook, ExportParams entity, Class<?> pojoClass, Collection<?> dataSet) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Excel export start ,class is {}", pojoClass);
            LOGGER.debug("Excel version is {}", entity.getType().equals(ExcelType.HSSF)?"03":"07");
        }

        if(workbook != null && entity != null && pojoClass != null && dataSet != null) {
            super.type = entity.getType();
            if(this.type.equals(ExcelType.XSSF)) {
                this.MAX_NUM = 1000000;
            }

            Sheet sheet = null;

            try {
                sheet = workbook.createSheet(entity.getSheetName());
            } catch (Exception var18) {
                sheet = workbook.createSheet();
            }

            try {
                this.dataHanlder = entity.getDataHanlder();
                if(this.dataHanlder != null) {
                    this.needHanlderList = Arrays.asList(this.dataHanlder.getNeedHandlerFields());
                }

                this.setExcelExportStyler((IExcelExportStyler)entity.getStyle().getConstructor(new Class[]{Workbook.class}).newInstance(new Object[]{workbook}));
                Drawing e = sheet.createDrawingPatriarch();
                ArrayList excelParams = new ArrayList();
                if(entity.isAddIndex()) {
                    excelParams.add(this.indexExcelEntity(entity));
                }

                Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
                ExcelTarget etarget = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
                String targetId = etarget == null?null:etarget.value();
                this.getAllExcelField(entity.getExclusions(), targetId, fileds, excelParams, pojoClass, (List)null);
                this.sortAllParams(excelParams);
                int index = entity.isCreateHeadRows()?this.createHeaderAndTitle(entity, sheet, workbook, excelParams):0;
                this.setCellWith(excelParams, sheet);
                short rowHeight = this.getRowHeight(excelParams);
                this.setCurrentIndex(1);
                Iterator its = dataSet.iterator();
                ArrayList tempList = new ArrayList();

                while(its.hasNext()) {
                    Object i = its.next();
                    index += this.createCells(e, index, i, excelParams, sheet, workbook, rowHeight);
                    tempList.add(i);
                    if(index >= this.MAX_NUM) {
                        break;
                    }
                }

                this.mergeCells(sheet, excelParams, index);
                if(entity.getFreezeCol() != 0) {
                    sheet.createFreezePane(entity.getFreezeCol(), 0, entity.getFreezeCol(), 0);
                }

                its = dataSet.iterator();
                int var20 = 0;

                for(int le = tempList.size(); var20 < le; ++var20) {
                    its.next();
                    its.remove();
                }

                this.addStatisticsRow(this.getExcelExportStyler().getStyles(true, (ExcelExportEntity)null), sheet);
                if(dataSet.size() > 0) {
                    this.createSheet(workbook, entity, pojoClass, dataSet);
                }

            } catch (Exception var19) {
                LOGGER.error(var19.getMessage(), var19);
                LOGGER.error(var19.getMessage(), var19);
                throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, var19.getCause());
            }
        } else {
            throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
        }
    }

    public void createSheetForMap(Workbook workbook, ExportParams entity, List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Excel version is {}", entity.getType().equals(ExcelType.HSSF)?"03":"07");
        }

        if(workbook != null && entity != null && entityList != null && dataSet != null) {
            super.type = entity.getType();
            if(this.type.equals(ExcelType.XSSF)) {
                this.MAX_NUM = 1000000;
            }

            Sheet sheet = null;

            try {
                sheet = workbook.createSheet(entity.getSheetName());
            } catch (Exception var15) {
                sheet = workbook.createSheet();
            }

            try {
                this.dataHanlder = entity.getDataHanlder();
                if(this.dataHanlder != null) {
                    this.needHanlderList = Arrays.asList(this.dataHanlder.getNeedHandlerFields());
                }

                this.setExcelExportStyler((IExcelExportStyler)entity.getStyle().getConstructor(new Class[]{Workbook.class}).newInstance(new Object[]{workbook}));
                Drawing e = sheet.createDrawingPatriarch();
                ArrayList excelParams = new ArrayList();
                if(entity.isAddIndex()) {
                    excelParams.add(this.indexExcelEntity(entity));
                }

                excelParams.addAll(entityList);
                this.sortAllParams(excelParams);
                int index = entity.isCreateHeadRows()?this.createHeaderAndTitle(entity, sheet, workbook, excelParams):0;
                this.setCellWith(excelParams, sheet);
                short rowHeight = this.getRowHeight(excelParams);
                this.setCurrentIndex(1);
                Iterator its = dataSet.iterator();
                ArrayList tempList = new ArrayList();

                while(its.hasNext()) {
                    Object i = its.next();
                    index += this.createCells(e, index, i, excelParams, sheet, workbook, rowHeight);
                    tempList.add(i);
                    if(index >= this.MAX_NUM) {
                        break;
                    }
                }

                if(entity.getFreezeCol() != 0) {
                    sheet.createFreezePane(entity.getFreezeCol(), 0, entity.getFreezeCol(), 0);
                }

                this.mergeCells(sheet, excelParams, index);
                its = dataSet.iterator();
                int var17 = 0;

                for(int le = tempList.size(); var17 < le; ++var17) {
                    its.next();
                    its.remove();
                }

                if(dataSet.size() > 0) {
                    this.createSheetForMap(workbook, entity, entityList, dataSet);
                }

            } catch (Exception var16) {
                LOGGER.error(var16.getMessage(), var16);
                throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, var16.getCause());
            }
        } else {
            throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
        }
    }

    private int createTitleRow(ExportParams title, Sheet sheet, Workbook workbook, int index, List<ExcelExportEntity> excelParams) {
        Row row = sheet.createRow(index);
        int rows = this.getRowNums(excelParams);
        row.setHeight((short)450);
        Row listRow = null;
        if(rows == 2) {
            listRow = sheet.createRow(index + 1);
            listRow.setHeight((short)450);
        }

        int cellIndex = 0;
        CellStyle titleStyle = this.getExcelExportStyler().getTitleStyle(title.getColor());
        int i = 0;

        for(int exportFieldTitleSize = excelParams.size(); i < exportFieldTitleSize; ++i) {
            ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(i);
            if(StringUtils.isNotBlank(entity.getName())) {
                this.createStringCell(row, cellIndex, entity.getName(), titleStyle, entity);
            }

            if(entity.getList() == null) {
                if(rows == 2) {
                    this.createStringCell(listRow, cellIndex, "", titleStyle, entity);
                    sheet.addMergedRegion(new CellRangeAddress(index, index + 1, cellIndex, cellIndex));
                }
            } else {
                List sTitel = entity.getList();
                if(StringUtils.isNotBlank(entity.getName())) {
                    sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex, cellIndex + sTitel.size() - 1));
                }

                int j = 0;

                for(int size = sTitel.size(); j < size; ++j) {
                    this.createStringCell(rows == 2?listRow:row, cellIndex, ((ExcelExportEntity)sTitel.get(j)).getName(), titleStyle, entity);
                    ++cellIndex;
                }

                --cellIndex;
            }

            ++cellIndex;
        }

        return rows;
    }

    private int getRowNums(List<ExcelExportEntity> excelParams) {
        for(int i = 0; i < excelParams.size(); ++i) {
            if(((ExcelExportEntity)excelParams.get(i)).getList() != null && StringUtils.isNotBlank(((ExcelExportEntity)excelParams.get(i)).getName())) {
                return 2;
            }
        }

        return 1;
    }

    private ExcelExportEntity indexExcelEntity(ExportParams entity) {
        ExcelExportEntity exportEntity = new ExcelExportEntity();
        exportEntity.setOrderNum(0);
        exportEntity.setName(entity.getIndexName());
        exportEntity.setWidth(10.0D);
        exportEntity.setFormat("isAddIndex");
        return exportEntity;
    }
}
