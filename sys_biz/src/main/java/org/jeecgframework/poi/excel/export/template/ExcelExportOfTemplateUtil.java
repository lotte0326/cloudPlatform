//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.export.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.cache.ExcelCache;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.entity.params.ExcelTemplateParams;
import org.jeecgframework.poi.excel.export.base.ExcelExportBase;
import org.jeecgframework.poi.excel.export.styler.IExcelExportStyler;
import org.jeecgframework.poi.exception.excel.ExcelExportException;
import org.jeecgframework.poi.exception.excel.enums.ExcelExportEnum;
import org.jeecgframework.poi.util.PoiElUtil;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.jeecgframework.poi.util.PoiSheetUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExcelExportOfTemplateUtil extends ExcelExportBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportOfTemplateUtil.class);
    private Set<String> tempCreateCellSet = new HashSet();
    private TemplateExportParams teplateParams;

    public ExcelExportOfTemplateUtil() {
    }

    private void addDataToSheet(Class<?> pojoClass, Collection<?> dataSet, Sheet sheet, Workbook workbook) throws Exception {
        if(workbook instanceof XSSFWorkbook) {
            super.type = ExcelType.XSSF;
        }

        Map titlemap = this.getTitleMap(sheet);
        Drawing patriarch = sheet.createDrawingPatriarch();
        Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
        ExcelTarget etarget = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
        String targetId = null;
        if(etarget != null) {
            targetId = etarget.value();
        }

        ArrayList excelParams = new ArrayList();
        this.getAllExcelField((String[])null, targetId, fileds, excelParams, pojoClass, (List)null);
        this.sortAndFilterExportField(excelParams, titlemap);
        short rowHeight = this.getRowHeight(excelParams);
        int index = this.teplateParams.getHeadingRows() + this.teplateParams.getHeadingStartRow();
        sheet.shiftRows(this.teplateParams.getHeadingRows() + this.teplateParams.getHeadingStartRow(), sheet.getLastRowNum(), this.getShiftRows(dataSet, excelParams), true, true);
        if(excelParams.size() != 0) {
            Object t;
            for(Iterator its = dataSet.iterator(); its.hasNext(); index += this.createCells(patriarch, index, t, excelParams, sheet, workbook, rowHeight)) {
                t = its.next();
            }

            this.mergeCells(sheet, excelParams, index);
        }
    }

    private int getShiftRows(Collection<?> dataSet, List<ExcelExportEntity> excelParams) throws Exception {
        int size = 0;

        Object t;
        for(Iterator its = dataSet.iterator(); its.hasNext(); size += this.getOneObjectSize(t, excelParams)) {
            t = its.next();
        }

        return size;
    }

    public int getOneObjectSize(Object t, List<ExcelExportEntity> excelParams) throws Exception {
        int maxHeight = 1;
        int k = 0;

        for(int paramSize = excelParams.size(); k < paramSize; ++k) {
            ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(k);
            if(entity.getList() != null) {
                Collection list = (Collection)entity.getMethod().invoke(t, new Object[0]);
                if(list != null && list.size() > maxHeight) {
                    maxHeight = list.size();
                }
            }
        }

        return maxHeight;
    }

    public Workbook createExcleByTemplate(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
        if(params != null && map != null && !StringUtils.isEmpty(params.getTemplateUrl())) {
            Workbook wb = null;

            try {
                this.teplateParams = params;
                wb = this.getCloneWorkBook();
                this.setExcelExportStyler((IExcelExportStyler)this.teplateParams.getStyle().getConstructor(new Class[]{Workbook.class}).newInstance(new Object[]{wb}));
                int e = 0;

                for(int le = params.isScanAllsheet()?wb.getNumberOfSheets():params.getSheetNum().length; e < le; ++e) {
                    if(params.getSheetName() != null && params.getSheetName().length > e && StringUtils.isNotEmpty(params.getSheetName()[e])) {
                        wb.setSheetName(e, params.getSheetName()[e]);
                    }

                    this.tempCreateCellSet.clear();
                    this.parseTemplate(wb.getSheetAt(e), map);
                }

                if(dataSet != null) {
                    this.dataHanlder = params.getDataHanlder();
                    if(this.dataHanlder != null) {
                        this.needHanlderList = Arrays.asList(this.dataHanlder.getNeedHandlerFields());
                    }

                    this.addDataToSheet(pojoClass, dataSet, wb.getSheetAt(params.getDataSheetNum()), wb);
                }

                return wb;
            } catch (Exception var8) {
                LOGGER.error(var8.getMessage(), var8);
                return null;
            }
        } else {
            throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
        }
    }

    private Workbook getCloneWorkBook() throws Exception {
        return ExcelCache.getWorkbook(this.teplateParams.getTemplateUrl(), this.teplateParams.getSheetNum(), this.teplateParams.isScanAllsheet());
    }

    private Map<String, Integer> getTitleMap(Sheet sheet) {
        Row row = null;
        HashMap titlemap = new HashMap();

        for(int j = 0; j < this.teplateParams.getHeadingRows(); ++j) {
            row = sheet.getRow(j + this.teplateParams.getHeadingStartRow());
            Iterator cellTitle = row.cellIterator();

            for(int i = row.getFirstCellNum(); cellTitle.hasNext(); ++i) {
                Cell cell = (Cell)cellTitle.next();
                String value = cell.getStringCellValue();
                if(!StringUtils.isEmpty(value)) {
                    titlemap.put(value, Integer.valueOf(i));
                }
            }
        }

        return titlemap;
    }

    private void parseTemplate(Sheet sheet, Map<String, Object> map) throws Exception {
        this.deleteCell(sheet, map);
        Row row = null;
        int index = 0;

        while(true) {
            do {
                if(index > sheet.getLastRowNum()) {
                    return;
                }

                row = sheet.getRow(index++);
            } while(row == null);

            for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); ++i) {
                if(row.getCell(i) != null && !this.tempCreateCellSet.contains(row.getRowNum() + "_" + row.getCell(i).getColumnIndex())) {
                    this.setValueForCellByMap(row.getCell(i), map);
                }
            }
        }
    }

    private void deleteCell(Sheet sheet, Map<String, Object> map) throws Exception {
        Row row = null;
        Cell cell = null;
        int index = 0;

        while(true) {
            do {
                if(index > sheet.getLastRowNum()) {
                    return;
                }

                row = sheet.getRow(index++);
            } while(row == null);

            for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); ++i) {
                cell = row.getCell(i);
                if(row.getCell(i) != null && (cell.getCellType() == 1 || cell.getCellType() == 0)) {
                    cell.setCellType(1);
                    String text = cell.getStringCellValue();
                    if(text.contains("!if:")) {
                        if(Boolean.valueOf(PoiElUtil.eval(text.substring(text.indexOf("{{") + 2, text.indexOf("}}")).trim(), map).toString()).booleanValue()) {
                            PoiSheetUtility.deleteColumn(sheet, i);
                        }

                        cell.setCellValue("");
                    }
                }
            }
        }
    }

    private void setValueForCellByMap(Cell cell, Map<String, Object> map) throws Exception {
        int cellType = cell.getCellType();
        if(cellType == 1 || cellType == 0) {
            cell.setCellType(1);
            String oldString = cell.getStringCellValue();
            if(oldString != null && oldString.indexOf("{{") != -1 && !oldString.contains("fe:")) {
                String params = null;
                boolean isNumber = false;
                if(this.isNumber(oldString)) {
                    isNumber = true;
                    oldString = oldString.replace("n:", "");
                }

                while(oldString.indexOf("{{") != -1) {
                    params = oldString.substring(oldString.indexOf("{{") + 2, oldString.indexOf("}}"));
                    oldString = oldString.replace("{{" + params + "}}", PoiElUtil.eval(params, map).toString());
                }

                if(isNumber && StringUtils.isNotBlank(oldString)) {
                    cell.setCellValue(Double.parseDouble(oldString));
                    cell.setCellType(0);
                } else {
                    cell.setCellValue(oldString);
                }
            }

            if(oldString != null && oldString.contains("fe:")) {
                this.addListDataToExcel(cell, map, oldString.trim());
            }

        }
    }

    private boolean isNumber(String text) {
        return text.startsWith("n:") || text.contains("{n:") || text.contains(" n:");
    }

    private void addListDataToExcel(Cell cell, Map<String, Object> map, String name) throws Exception {
        boolean isCreate = !name.contains("!fe:");
        boolean isShift = name.contains("$fe:");
        name = name.replace("!fe:", "").replace("$fe:", "").replace("fe:", "").replace("{{", "");
        String[] keys = name.replaceAll("\\s{1,}", " ").trim().split(" ");
        Collection datas = (Collection)PoiPublicUtil.getParamsValue(keys[0], map);
        List columns = this.getAllDataColumns(cell, name.replace(keys[0], ""));
        if(datas != null) {
            Iterator its = datas.iterator();
            int rowIndex = cell.getRow().getRowNum() + 1;
            Object t;
            if(its.hasNext()) {
                t = its.next();
                cell.getRow().setHeight(((ExcelTemplateParams)columns.get(0)).getHeight());
                this.setForEeachCellValue(isCreate, cell.getRow(), cell.getColumnIndex(), t, columns, map);
            }

            if(isShift) {
                cell.getRow().getSheet().shiftRows(cell.getRowIndex() + 1, cell.getRow().getSheet().getLastRowNum(), datas.size() - 1, true, true);
            }

            while(its.hasNext()) {
                t = its.next();
                Row row;
                if(isCreate) {
                    row = cell.getRow().getSheet().createRow(rowIndex++);
                } else {
                    row = cell.getRow().getSheet().getRow(rowIndex++);
                    if(row == null) {
                        row = cell.getRow().getSheet().createRow(rowIndex - 1);
                    }
                }

                row.setHeight(((ExcelTemplateParams)columns.get(0)).getHeight());
                this.setForEeachCellValue(isCreate, row, cell.getColumnIndex(), t, columns, map);
            }

        }
    }

    private void setForEeachCellValue(boolean isCreate, Row row, int columnIndex, Object t, List<ExcelTemplateParams> columns, Map<String, Object> map) throws Exception {
        int i = 0;

        int max;
        for(max = columnIndex + columns.size(); i < max; ++i) {
            if(row.getCell(i) == null) {
                row.createCell(i);
            }
        }

        i = 0;

        for(max = columns.size(); i < max; ++i) {
            boolean isNumber = false;
            String tempStr = new String(((ExcelTemplateParams)columns.get(i)).getName());
            if(this.isNumber(tempStr)) {
                isNumber = true;
                tempStr = tempStr.replace("n:", "");
            }

            map.put(this.teplateParams.getTempParams(), t);
            String val = PoiElUtil.eval(tempStr, map).toString();
            if(isNumber && StringUtils.isNotEmpty(val)) {
                row.getCell(i + columnIndex).setCellValue(Double.parseDouble(val));
                row.getCell(i + columnIndex).setCellType(0);
            } else {
                row.getCell(i + columnIndex).setCellValue(val);
            }

            row.getCell(i + columnIndex).setCellStyle(((ExcelTemplateParams)columns.get(i)).getCellStyle());
            this.tempCreateCellSet.add(row.getRowNum() + "_" + (i + columnIndex));
        }

    }

    private List<ExcelTemplateParams> getAllDataColumns(Cell cell, String name) {
        ArrayList columns = new ArrayList();
        cell.setCellValue("");
        if(name.contains("}}")) {
            columns.add(new ExcelTemplateParams(name.replace("}}", "").trim(), cell.getCellStyle(), cell.getRow().getHeight()));
            return columns;
        } else {
            columns.add(new ExcelTemplateParams(name.trim(), cell.getCellStyle(), cell.getRow().getHeight()));
            int index = cell.getColumnIndex();

            while(true) {
                Row var10000 = cell.getRow();
                ++index;
                Cell tempCell = var10000.getCell(index);
                if(tempCell == null) {
                    break;
                }

                String cellStringString;
                try {
                    cellStringString = tempCell.getStringCellValue();
                    if(StringUtils.isBlank(cellStringString)) {
                        break;
                    }
                } catch (Exception var8) {
                    throw new ExcelExportException("for each 当中存在空字符串,请检查模板");
                }

                tempCell.setCellValue("");
                if(cellStringString.contains("}}")) {
                    columns.add(new ExcelTemplateParams(cellStringString.trim().replace("}}", ""), tempCell.getCellStyle(), tempCell.getRow().getHeight()));
                    break;
                }

                if(!cellStringString.trim().contains(this.teplateParams.getTempParams())) {
                    break;
                }

                columns.add(new ExcelTemplateParams(cellStringString.trim(), tempCell.getCellStyle(), tempCell.getRow().getHeight()));
            }

            return columns;
        }
    }

    private void sortAndFilterExportField(List<ExcelExportEntity> excelParams, Map<String, Integer> titlemap) {
        for(int i = excelParams.size() - 1; i >= 0; --i) {
            if(((ExcelExportEntity)excelParams.get(i)).getList() != null && ((ExcelExportEntity)excelParams.get(i)).getList().size() > 0) {
                this.sortAndFilterExportField(((ExcelExportEntity)excelParams.get(i)).getList(), titlemap);
                if(((ExcelExportEntity)excelParams.get(i)).getList().size() == 0) {
                    excelParams.remove(i);
                } else {
                    ((ExcelExportEntity)excelParams.get(i)).setOrderNum(i);
                }
            } else if(titlemap.containsKey(((ExcelExportEntity)excelParams.get(i)).getName())) {
                ((ExcelExportEntity)excelParams.get(i)).setOrderNum(i);
            } else {
                excelParams.remove(i);
            }
        }

        this.sortAllParams(excelParams);
    }
}
