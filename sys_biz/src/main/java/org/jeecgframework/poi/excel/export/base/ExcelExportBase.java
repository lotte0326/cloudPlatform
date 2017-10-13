//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.export.base;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.base.ExportBase;
import org.jeecgframework.poi.excel.export.styler.IExcelExportStyler;
import org.jeecgframework.poi.util.PoiMergeCellUtil;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExcelExportBase extends ExportBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportBase.class);
    private int currentIndex = 0;
    protected ExcelType type;
    private Map<Integer, Double> statistics;
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");
    private IExcelExportStyler excelExportStyler;

    public ExcelExportBase() {
        this.type = ExcelType.HSSF;
        this.statistics = new HashMap();
    }

    public int createCells(Drawing patriarch, int index, Object t, List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook, short rowHeight) throws Exception {
        Row row = sheet.createRow(index);
        row.setHeight(rowHeight);
        int maxHeight = 1;
        byte cellNum = 0;
        int indexKey = this.createIndexCell(row, index, (ExcelExportEntity)excelParams.get(0));
        int var19 = cellNum + indexKey;
        int k = indexKey;

        ExcelExportEntity entity;
        int paramSize;
        for(paramSize = excelParams.size(); k < paramSize; ++k) {
            entity = (ExcelExportEntity)excelParams.get(k);
            if(entity.getList() == null) {
                Object var20 = this.getCellValue(entity, t);
                if(entity.getType() == 1) {
                    this.createStringCell(row, var19++, var20 == null?"":var20.toString(), index % 2 == 0?this.getStyles(false, entity):this.getStyles(true, entity), entity);
                } else {
                    this.createImageCell(patriarch, entity, row, var19++, var20 == null?"":var20.toString(), t);
                }
            } else {
                Collection i = this.getListCellValue(entity, t);
                int listC = 0;

                for(Iterator i$ = i.iterator(); i$.hasNext(); ++listC) {
                    Object obj = i$.next();
                    this.createListCells(patriarch, index + listC, var19, obj, entity.getList(), sheet, workbook);
                }

                var19 += entity.getList().size();
                if(i != null && i.size() > maxHeight) {
                    maxHeight = i.size();
                }
            }
        }

        var19 = 0;
        k = indexKey;

        for(paramSize = excelParams.size(); k < paramSize; ++k) {
            entity = (ExcelExportEntity)excelParams.get(k);
            if(entity.getList() != null) {
                var19 += entity.getList().size();
            } else if(entity.isNeedMerge()) {
                for(int var21 = index + 1; var21 < index + maxHeight; ++var21) {
                    sheet.getRow(var21).createCell(var19);
                    sheet.getRow(var21).getCell(var19).setCellStyle(this.getStyles(false, entity));
                }

                sheet.addMergedRegion(new CellRangeAddress(index, index + maxHeight - 1, var19, var19));
                ++var19;
            }
        }

        return maxHeight;
    }

    public void createImageCell(Drawing patriarch, ExcelExportEntity entity, Row row, int i, String imagePath, Object obj) throws Exception {
        row.setHeight((short)((int)(50.0D * entity.getHeight())));
        row.createCell(i);
        Object anchor;
        if(this.type.equals(ExcelType.HSSF)) {
            anchor = new HSSFClientAnchor(0, 0, 0, 0, (short)i, row.getRowNum(), (short)(i + 1), row.getRowNum() + 1);
        } else {
            anchor = new XSSFClientAnchor(0, 0, 0, 0, (short)i, row.getRowNum(), (short)(i + 1), row.getRowNum() + 1);
        }

        if(!StringUtils.isEmpty(imagePath)) {
            if(entity.getExportImageType() == 1) {
                ByteArrayOutputStream value = new ByteArrayOutputStream();

                try {
                    String e = PoiPublicUtil.getWebRootPath(imagePath);
                    e = e.replace("WEB-INF/classes/", "");
                    e = e.replace("file:/", "");
                    BufferedImage bufferImg = ImageIO.read(new File(e));
                    ImageIO.write(bufferImg, imagePath.substring(imagePath.indexOf(".") + 1, imagePath.length()), value);
                    byte[] value1 = value.toByteArray();
                    patriarch.createPicture((ClientAnchor)anchor, row.getSheet().getWorkbook().addPicture(value1, this.getImageType(value1)));
                } catch (IOException var12) {
                    LOGGER.error(var12.getMessage(), var12);
                }
            } else {
                byte[] value2 = (byte[])((byte[])(entity.getMethods() != null?this.getFieldBySomeMethod(entity.getMethods(), obj):entity.getMethod().invoke(obj, new Object[0])));
                if(value2 != null) {
                    patriarch.createPicture((ClientAnchor)anchor, row.getSheet().getWorkbook().addPicture(value2, this.getImageType(value2)));
                }
            }

        }
    }

    private int createIndexCell(Row row, int index, ExcelExportEntity excelExportEntity) {
        if(excelExportEntity.getName().equals("序号") && excelExportEntity.getFormat().equals("isAddIndex")) {
            this.createStringCell(row, 0, this.currentIndex + "", index % 2 == 0?this.getStyles(false, (ExcelExportEntity)null):this.getStyles(true, (ExcelExportEntity)null), (ExcelExportEntity)null);
            ++this.currentIndex;
            return 1;
        } else {
            return 0;
        }
    }

    public void createListCells(Drawing patriarch, int index, int cellNum, Object obj, List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook) throws Exception {
        Row row;
        if(sheet.getRow(index) == null) {
            row = sheet.createRow(index);
            row.setHeight(this.getRowHeight(excelParams));
        } else {
            row = sheet.getRow(index);
        }

        int k = 0;

        for(int paramSize = excelParams.size(); k < paramSize; ++k) {
            ExcelExportEntity entity = (ExcelExportEntity)excelParams.get(k);
            Object value = this.getCellValue(entity, obj);
            if(entity.getType() == 1) {
                this.createStringCell(row, cellNum++, value == null?"":value.toString(), row.getRowNum() % 2 == 0?this.getStyles(false, entity):this.getStyles(true, entity), entity);
            } else {
                this.createImageCell(patriarch, entity, row, cellNum++, value == null?"":value.toString(), obj);
            }
        }

    }

    public void createStringCell(Row row, int index, String text, CellStyle style, ExcelExportEntity entity) {
        Cell cell = row.createCell(index);
        if(style != null && style.getDataFormat() > 0 && style.getDataFormat() < 12) {
            cell.setCellValue(Double.parseDouble(text));
            cell.setCellType(0);
        } else {
            Object Rtext;
            if(this.type.equals(ExcelType.HSSF)) {
                Rtext = new HSSFRichTextString(text);
            } else {
                Rtext = new XSSFRichTextString(text);
            }

            cell.setCellValue((RichTextString)Rtext);
        }

        if(style != null) {
            cell.setCellStyle(style);
        }

        this.addStatisticsData(Integer.valueOf(index), text, entity);
    }

    public void addStatisticsRow(CellStyle styles, Sheet sheet) {
        if(this.statistics.size() > 0) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            Set keys = this.statistics.keySet();
            this.createStringCell(row, 0, "合计", styles, (ExcelExportEntity)null);
            Iterator i$ = keys.iterator();

            while(i$.hasNext()) {
                Integer key = (Integer)i$.next();
                this.createStringCell(row, key.intValue(), DOUBLE_FORMAT.format(this.statistics.get(key)), styles, (ExcelExportEntity)null);
            }

            this.statistics.clear();
        }

    }

    private void addStatisticsData(Integer index, String text, ExcelExportEntity entity) {
        if(entity != null && entity.isStatistics()) {
            Double temp = Double.valueOf(0.0D);
            if(!this.statistics.containsKey(index)) {
                this.statistics.put(index, temp);
            }

            try {
                temp = Double.valueOf(text);
            } catch (NumberFormatException var6) {
                ;
            }

            this.statistics.put(index, Double.valueOf(((Double)this.statistics.get(index)).doubleValue() + temp.doubleValue()));
        }

    }

    public int getFieldWidth(List<ExcelExportEntity> excelParams) {
        int length = -1;

        ExcelExportEntity entity;
        for(Iterator i$ = excelParams.iterator(); i$.hasNext(); length += entity.getList() != null?entity.getList().size():1) {
            entity = (ExcelExportEntity)i$.next();
        }

        return length;
    }

    public int getImageType(byte[] value) {
        String type = PoiPublicUtil.getFileExtendName(value);
        return type.equalsIgnoreCase("JPG")?5:(type.equalsIgnoreCase("PNG")?6:5);
    }

    private Map<Integer, int[]> getMergeDataMap(List<ExcelExportEntity> excelParams) {
        HashMap mergeMap = new HashMap();
        int i = 0;
        Iterator i$ = excelParams.iterator();

        while(true) {
            while(i$.hasNext()) {
                ExcelExportEntity entity = (ExcelExportEntity)i$.next();
                if(entity.isMergeVertical()) {
                    mergeMap.put(Integer.valueOf(i), entity.getMergeRely());
                }

                if(entity.getList() != null) {
                    for(Iterator i$1 = entity.getList().iterator(); i$1.hasNext(); ++i) {
                        ExcelExportEntity inner = (ExcelExportEntity)i$1.next();
                        if(inner.isMergeVertical()) {
                            mergeMap.put(Integer.valueOf(i), inner.getMergeRely());
                        }
                    }
                } else {
                    ++i;
                }
            }

            return mergeMap;
        }
    }

    public CellStyle getStyles(boolean needOne, ExcelExportEntity entity) {
        return this.excelExportStyler.getStyles(needOne, entity);
    }

    public void mergeCells(Sheet sheet, List<ExcelExportEntity> excelParams, int titleHeight) {
        Map mergeMap = this.getMergeDataMap(excelParams);
        PoiMergeCellUtil.mergeCells(sheet, mergeMap, titleHeight);
    }

    public void setCellWith(List<ExcelExportEntity> excelParams, Sheet sheet) {
        int index = 0;

        for(int i = 0; i < excelParams.size(); ++i) {
            if(((ExcelExportEntity)excelParams.get(i)).getList() != null) {
                List list = ((ExcelExportEntity)excelParams.get(i)).getList();

                for(int j = 0; j < list.size(); ++j) {
                    sheet.setColumnWidth(index, (int)(256.0D * ((ExcelExportEntity)list.get(j)).getWidth()));
                    ++index;
                }
            } else {
                sheet.setColumnWidth(index, (int)(256.0D * ((ExcelExportEntity)excelParams.get(i)).getWidth()));
                ++index;
            }
        }

    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setExcelExportStyler(IExcelExportStyler excelExportStyler) {
        this.excelExportStyler = excelExportStyler;
    }

    public IExcelExportStyler getExcelExportStyler() {
        return this.excelExportStyler;
    }
}
