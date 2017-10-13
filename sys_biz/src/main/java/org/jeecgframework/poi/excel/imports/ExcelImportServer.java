//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelCollectionParams;
import org.jeecgframework.poi.excel.entity.params.ExcelImportEntity;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.excel.imports.CellValueServer;
import org.jeecgframework.poi.excel.imports.base.ImportBaseService;
import org.jeecgframework.poi.excel.imports.verifys.VerifyHandlerServer;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.jeecgframework.poi.exception.excel.enums.ExcelImportEnum;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelImportServer extends ImportBaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportServer.class);
    private CellValueServer cellValueServer = new CellValueServer();
    private VerifyHandlerServer verifyHandlerServer = new VerifyHandlerServer();
    private boolean verfiyFail = false;
    private CellStyle errorCellStyle;

    public ExcelImportServer() {
    }

    private void addListContinue(Object object, ExcelCollectionParams param, Row row, Map<Integer, String> titlemap, String targetId, Map<String, PictureData> pictures, ImportParams params) throws Exception {
        Collection collection = (Collection)PoiPublicUtil.getMethod(param.getName(), object.getClass()).invoke(object, new Object[0]);
        Object entity = PoiPublicUtil.createObject(param.getType(), targetId);
        boolean isUsed = false;

        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); ++i) {
            Cell cell = row.getCell(i);
            String titleString = (String)titlemap.get(Integer.valueOf(i));
            if(param.getExcelParams().containsKey(titleString)) {
                if(((ExcelImportEntity)param.getExcelParams().get(titleString)).getType() == 2) {
                    String picId = row.getRowNum() + "_" + i;
                    this.saveImage(object, picId, param.getExcelParams(), titleString, pictures, params);
                } else {
                    this.saveFieldValue(params, entity, cell, param.getExcelParams(), titleString, row);
                }

                isUsed = true;
            }
        }

        if(isUsed) {
            collection.add(entity);
        }

    }

    private String getKeyValue(Cell cell) {
        Object obj = null;
        switch(cell.getCellType()) {
            case 0:
                obj = Double.valueOf(cell.getNumericCellValue());
                break;
            case 1:
                obj = cell.getStringCellValue();
                break;
            case 2:
                obj = cell.getCellFormula();
            case 3:
            default:
                break;
            case 4:
                obj = Boolean.valueOf(cell.getBooleanCellValue());
        }

        return obj == null?null:obj.toString().trim();
    }

    private String getSaveUrl(ExcelImportEntity excelImportEntity, Object object) throws Exception {
        String url = "";
        if(excelImportEntity.getSaveUrl().equals("upload")) {
            if(excelImportEntity.getMethods() != null && excelImportEntity.getMethods().size() > 0) {
                object = this.getFieldBySomeMethod(excelImportEntity.getMethods(), object);
            }

            url = object.getClass().getName().split("\\.")[object.getClass().getName().split("\\.").length - 1];
            return excelImportEntity.getSaveUrl() + "/" + url.substring(0, url.lastIndexOf("Entity"));
        } else {
            return excelImportEntity.getSaveUrl();
        }
    }

    private <T> List<T> importExcel(Collection<T> result, Sheet sheet, Class<?> pojoClass, ImportParams params, Map<String, PictureData> pictures) throws Exception {
        ArrayList collection = new ArrayList();
        HashMap excelParams = new HashMap();
        ArrayList excelCollection = new ArrayList();
        String targetId = null;
        if(!Map.class.equals(pojoClass)) {
            Field[] rows = PoiPublicUtil.getClassFields(pojoClass);
            ExcelTarget titlemap = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
            if(titlemap != null) {
                targetId = titlemap.value();
            }

            this.getAllExcelField(targetId, rows, excelParams, excelCollection, pojoClass, (List)null);
        }

        Iterator var20 = sheet.rowIterator();

        for(int var21 = 0; var21 < params.getTitleRows(); ++var21) {
            var20.next();
        }

        Map var22 = this.getTitleMap(var20, params, excelCollection);
        Row row = null;
        Object object = null;

        while(var20.hasNext() && (row == null || sheet.getLastRowNum() - row.getRowNum() > params.getLastOfInvalidRow())) {
            row = (Row)var20.next();
            Iterator var23;
            ExcelCollectionParams var24;
            if((row.getCell(params.getKeyIndex()) == null || StringUtils.isEmpty(this.getKeyValue(row.getCell(params.getKeyIndex())))) && object != null) {
                var23 = excelCollection.iterator();

                while(var23.hasNext()) {
                    var24 = (ExcelCollectionParams)var23.next();
                    this.addListContinue(object, var24, row, var22, targetId, pictures, params);
                }
            } else {
                object = PoiPublicUtil.createObject(pojoClass, targetId);

                try {
                    int e = row.getFirstCellNum();

                    for(short param = row.getLastCellNum(); e < param; ++e) {
                        Cell cell = row.getCell(e);
                        String titleString = (String)var22.get(Integer.valueOf(e));
                        if(excelParams.containsKey(titleString) || Map.class.equals(pojoClass)) {
                            if(excelParams.get(titleString) != null && ((ExcelImportEntity)excelParams.get(titleString)).getType() == 2) {
                                String picId = row.getRowNum() + "_" + e;
                                this.saveImage(object, picId, excelParams, titleString, pictures, params);
                            } else {
                                this.saveFieldValue(params, object, cell, excelParams, titleString, row);
                            }
                        }
                    }

                    var23 = excelCollection.iterator();

                    while(var23.hasNext()) {
                        var24 = (ExcelCollectionParams)var23.next();
                        this.addListContinue(object, var24, row, var22, targetId, pictures, params);
                    }

                    collection.add(object);
                } catch (ExcelImportException var19) {
                    if(!var19.getType().equals(ExcelImportEnum.VERIFY_ERROR)) {
                        throw new ExcelImportException(var19.getType(), var19);
                    }
                }
            }
        }

        return collection;
    }

    private Map<Integer, String> getTitleMap(Iterator<Row> rows, ImportParams params, List<ExcelCollectionParams> excelCollection) {
        HashMap titlemap = new HashMap();
        String collectionName = null;
        ExcelCollectionParams collectionParams = null;
        Row row = null;

        label44:
        for(int j = 0; j < params.getHeadRows(); ++j) {
            row = (Row)rows.next();
            if(row != null) {
                Iterator cellTitle = row.cellIterator();

                while(true) {
                    String value;
                    int i;
                    do {
                        if(!cellTitle.hasNext()) {
                            continue label44;
                        }

                        Cell cell = (Cell)cellTitle.next();
                        value = this.getKeyValue(cell);
                        i = cell.getColumnIndex();
                    } while(!StringUtils.isNotEmpty(value));

                    if(titlemap.containsKey(Integer.valueOf(i))) {
                        collectionName = (String)titlemap.get(Integer.valueOf(i));
                        collectionParams = this.getCollectionParams(excelCollection, collectionName);
                        titlemap.put(Integer.valueOf(i), collectionName + "_" + value);
                    } else if(StringUtils.isNotEmpty(collectionName) && collectionParams.getExcelParams().containsKey(collectionName + "_" + value)) {
                        titlemap.put(Integer.valueOf(i), collectionName + "_" + value);
                    } else {
                        collectionName = null;
                        collectionParams = null;
                    }

                    if(StringUtils.isEmpty(collectionName)) {
                        titlemap.put(Integer.valueOf(i), value);
                    }
                }
            }
        }

        return titlemap;
    }

    private ExcelCollectionParams getCollectionParams(List<ExcelCollectionParams> excelCollection, String collectionName) {
        Iterator i$ = excelCollection.iterator();

        ExcelCollectionParams excelCollectionParams;
        do {
            if(!i$.hasNext()) {
                return null;
            }

            excelCollectionParams = (ExcelCollectionParams)i$.next();
        } while(!collectionName.equals(excelCollectionParams.getExcelName()));

        return excelCollectionParams;
    }

    public ExcelImportResult importExcelByIs(InputStream inputstream, Class<?> pojoClass, ImportParams params) throws Exception {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Excel import start ,class is {}", pojoClass);
        }

        ArrayList result = new ArrayList();
        Object book = null;
        boolean isXSSFWorkbook = true;
        if(!((InputStream)inputstream).markSupported()) {
            inputstream = new PushbackInputStream((InputStream)inputstream, 8);
        }

        if(POIFSFileSystem.hasPOIFSHeader((InputStream)inputstream)) {
            book = new HSSFWorkbook((InputStream)inputstream);
            isXSSFWorkbook = false;
        } else if(POIXMLDocument.hasOOXMLHeader((InputStream)inputstream)) {
            book = new XSSFWorkbook(OPCPackage.open((InputStream)inputstream));
        }

        this.createErrorCellStyle((Workbook)book);

        for(int i = 0; i < params.getSheetNum(); ++i) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(" start to read excel by is ,startTime is {}", Long.valueOf((new Date()).getTime()));
            }

            Map pictures;
            if(isXSSFWorkbook) {
                pictures = PoiPublicUtil.getSheetPictrues07((XSSFSheet)((Workbook)book).getSheetAt(i), (XSSFWorkbook)book);
            } else {
                pictures = PoiPublicUtil.getSheetPictrues03((HSSFSheet)((Workbook)book).getSheetAt(i), (HSSFWorkbook)book);
            }

            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(" end to read excel by is ,endTime is {}", Long.valueOf((new Date()).getTime()));
            }

            result.addAll(this.importExcel(result, ((Workbook)book).getSheetAt(i), pojoClass, params, pictures));
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(" end to read excel list by pos ,endTime is {}", Long.valueOf((new Date()).getTime()));
            }
        }

        if(params.isNeedSave()) {
            this.saveThisExcel(params, pojoClass, isXSSFWorkbook, (Workbook)book);
        }

        return new ExcelImportResult(result, this.verfiyFail, (Workbook)book);
    }

    private void saveFieldValue(ImportParams params, Object object, Cell cell, Map<String, ExcelImportEntity> excelParams, String titleString, Row row) throws Exception {
        Object value = this.cellValueServer.getValue(params.getDataHanlder(), object, cell, excelParams, titleString);
        if(object instanceof Map) {
            if(params.getDataHanlder() != null) {
                params.getDataHanlder().setMapValue((Map)object, titleString, value);
            } else {
                ((Map)object).put(titleString, value);
            }
        } else {
            ExcelVerifyHanlderResult verifyResult = this.verifyHandlerServer.verifyData(object, value, titleString, ((ExcelImportEntity)excelParams.get(titleString)).getVerify(), params.getVerifyHanlder());
            if(!verifyResult.isSuccess()) {
                Cell errorCell = row.createCell(row.getLastCellNum());
                errorCell.setCellValue(verifyResult.getMsg());
                errorCell.setCellStyle(this.errorCellStyle);
                this.verfiyFail = true;
                throw new ExcelImportException(ExcelImportEnum.VERIFY_ERROR);
            }

            this.setValues((ExcelImportEntity)excelParams.get(titleString), object, value);
        }

    }

    private void saveImage(Object object, String picId, Map<String, ExcelImportEntity> excelParams, String titleString, Map<String, PictureData> pictures, ImportParams params) throws Exception {
        if(pictures != null) {
            PictureData image = (PictureData)pictures.get(picId);
            byte[] data = image.getData();
            String fileName = "pic" + Math.round(Math.random() * 1.0E11D);
            fileName = fileName + "." + PoiPublicUtil.getFileExtendName(data);
            if(((ExcelImportEntity)excelParams.get(titleString)).getSaveType() == 1) {
                String path = PoiPublicUtil.getWebRootPath(this.getSaveUrl((ExcelImportEntity)excelParams.get(titleString), object));
                File savefile = new File(path);
                if(!savefile.exists()) {
                    savefile.mkdirs();
                }

                savefile = new File(path + "/" + fileName);
                FileOutputStream fos = new FileOutputStream(savefile);
                fos.write(data);
                fos.close();
                this.setValues((ExcelImportEntity)excelParams.get(titleString), object, this.getSaveUrl((ExcelImportEntity)excelParams.get(titleString), object) + "/" + fileName);
            } else {
                this.setValues((ExcelImportEntity)excelParams.get(titleString), object, data);
            }

        }
    }

    private void createErrorCellStyle(Workbook workbook) {
        this.errorCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor((short)10);
        this.errorCellStyle.setFont(font);
    }
}
