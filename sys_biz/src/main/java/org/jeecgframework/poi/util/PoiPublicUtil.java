//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelIgnore;
import org.jeecgframework.poi.word.entity.WordImageEntity;
import org.jeecgframework.poi.word.entity.params.ExcelListEntity;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PoiPublicUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoiPublicUtil.class);

    private PoiPublicUtil() {
    }

    public static <K, V> Map<K, V> mapFor(Object... mapping) {
        HashMap map = new HashMap();

        for(int i = 0; i < mapping.length; i += 2) {
            map.put(mapping[i], mapping[i + 1]);
        }

        return map;
    }

    public static Object createObject(Class<?> clazz, String targetId) {
        Object obj = null;

        try {
            if(clazz.equals(Map.class)) {
                return new HashMap();
            } else {
                obj = clazz.newInstance();
                Field[] e = getClassFields(clazz);
                Field[] arr$ = e;
                int len$ = e.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Field field = arr$[i$];
                    if(!isNotUserExcelUserThis((List)null, field, targetId)) {
                        Method setMethod;
                        if(isCollection(field.getType())) {
                            ExcelCollection collection = (ExcelCollection)field.getAnnotation(ExcelCollection.class);
                            setMethod = getMethod(field.getName(), clazz, field.getType());
                            setMethod.invoke(obj, new Object[]{collection.type().newInstance()});
                        } else if(!isJavaClass(field)) {
                            setMethod = getMethod(field.getName(), clazz, field.getType());
                            setMethod.invoke(obj, new Object[]{createObject(field.getType(), targetId)});
                        }
                    }
                }

                return obj;
            }
        } catch (Exception var10) {
            LOGGER.error(var10.getMessage(), var10);
            throw new RuntimeException("创建对象异常");
        }
    }

    public static Field[] getClassFields(Class<?> clazz) {
        ArrayList list = new ArrayList();

        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();

            for(int i = 0; i < fields.length; ++i) {
                list.add(fields[i]);
            }

            clazz = clazz.getSuperclass();
        } while(clazz != Object.class && clazz != null);

        return (Field[])list.toArray(fields);
    }

    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "JPG";
        if(photoByte[0] == 71 && photoByte[1] == 73 && photoByte[2] == 70 && photoByte[3] == 56 && (photoByte[4] == 55 || photoByte[4] == 57) && photoByte[5] == 97) {
            strFileExtendName = "GIF";
        } else if(photoByte[6] == 74 && photoByte[7] == 70 && photoByte[8] == 73 && photoByte[9] == 70) {
            strFileExtendName = "JPG";
        } else if(photoByte[0] == 66 && photoByte[1] == 77) {
            strFileExtendName = "BMP";
        } else if(photoByte[1] == 80 && photoByte[2] == 78 && photoByte[3] == 71) {
            strFileExtendName = "PNG";
        }

        return strFileExtendName;
    }

    public static Method getMethod(String name, Class<?> pojoClass) throws Exception {
        StringBuffer getMethodName = new StringBuffer("get");
        getMethodName.append(name.substring(0, 1).toUpperCase());
        getMethodName.append(name.substring(1));
        Method method = null;

        try {
            method = pojoClass.getMethod(getMethodName.toString(), new Class[0]);
        } catch (Exception var5) {
            method = pojoClass.getMethod(getMethodName.toString().replace("get", "is"), new Class[0]);
        }

        return method;
    }

    public static Method getMethod(String name, Class<?> pojoClass, Class<?> type) throws Exception {
        StringBuffer getMethodName = new StringBuffer("set");
        getMethodName.append(name.substring(0, 1).toUpperCase());
        getMethodName.append(name.substring(1));
        return pojoClass.getMethod(getMethodName.toString(), new Class[]{type});
    }

    public static Map<String, PictureData> getSheetPictrues03(HSSFSheet sheet, HSSFWorkbook workbook) {
        HashMap sheetIndexPicMap = new HashMap();
        List pictures = workbook.getAllPictures();
        if(!pictures.isEmpty()) {
            Iterator i$ = sheet.getDrawingPatriarch().getChildren().iterator();

            while(i$.hasNext()) {
                HSSFShape shape = (HSSFShape)i$.next();
                HSSFClientAnchor anchor = (HSSFClientAnchor)shape.getAnchor();
                if(shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture)shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = (HSSFPictureData)pictures.get(pictureIndex);
                    String picIndex = anchor.getRow1() + "_" + anchor.getCol1();
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }

            return sheetIndexPicMap;
        } else {
            return null;
        }
    }

    public static Map<String, PictureData> getSheetPictrues07(XSSFSheet sheet, XSSFWorkbook workbook) {
        HashMap sheetIndexPicMap = new HashMap();
        Iterator i$ = sheet.getRelations().iterator();

        while(true) {
            POIXMLDocumentPart dr;
            do {
                if(!i$.hasNext()) {
                    return sheetIndexPicMap;
                }

                dr = (POIXMLDocumentPart)i$.next();
            } while(!(dr instanceof XSSFDrawing));

            XSSFDrawing drawing = (XSSFDrawing)dr;
            List shapes = drawing.getShapes();
            Iterator i$1 = shapes.iterator();

            while(i$1.hasNext()) {
                XSSFShape shape = (XSSFShape)i$1.next();
                XSSFPicture pic = (XSSFPicture)shape;
                XSSFClientAnchor anchor = pic.getPreferredSize();
                CTMarker ctMarker = anchor.getFrom();
                String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
                sheetIndexPicMap.put(picIndex, pic.getPictureData());
            }
        }
    }

    public static String getWebRootPath(String filePath) {
        try {
            String e = PoiPublicUtil.class.getClassLoader().getResource("").toURI().getPath();
            e = e.replace("WEB-INF/classes/", "");
            e = e.replace("file:/", "");
            return e + filePath;
        } catch (URISyntaxException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    public static boolean isJavaClass(Field field) {
        Class fieldType = field.getType();
        boolean isBaseClass = false;
        if(fieldType.isArray()) {
            isBaseClass = false;
        } else if(fieldType.isPrimitive() || fieldType.getPackage() == null || fieldType.getPackage().getName().equals("java.lang") || fieldType.getPackage().getName().equals("java.math") || fieldType.getPackage().getName().equals("java.sql") || fieldType.getPackage().getName().equals("java.util")) {
            isBaseClass = true;
        }

        return isBaseClass;
    }

    public static boolean isNotUserExcelUserThis(List<String> exclusionsList, Field field, String targetId) {
        boolean boo = true;
        if(field.getAnnotation(ExcelIgnore.class) != null) {
            boo = true;
        } else if(!boo || field.getAnnotation(ExcelCollection.class) == null || !isUseInThis(((ExcelCollection)field.getAnnotation(ExcelCollection.class)).name(), targetId) || exclusionsList != null && exclusionsList.contains(((ExcelCollection)field.getAnnotation(ExcelCollection.class)).name())) {
            if(!boo || field.getAnnotation(Excel.class) == null || !isUseInThis(((Excel)field.getAnnotation(Excel.class)).name(), targetId) || exclusionsList != null && exclusionsList.contains(((Excel)field.getAnnotation(Excel.class)).name())) {
                if(boo && field.getAnnotation(ExcelEntity.class) != null && isUseInThis(((ExcelEntity)field.getAnnotation(ExcelEntity.class)).name(), targetId) && (exclusionsList == null || !exclusionsList.contains(((ExcelEntity)field.getAnnotation(ExcelEntity.class)).name()))) {
                    boo = false;
                }
            } else {
                boo = false;
            }
        } else {
            boo = false;
        }

        return boo;
    }

    private static boolean isUseInThis(String exportName, String targetId) {
        return targetId == null || exportName.equals("") || exportName.indexOf("_") < 0 || exportName.indexOf(targetId) != -1;
    }

    private static Integer getImageType(String type) {
        return !type.equalsIgnoreCase("JPG") && !type.equalsIgnoreCase("JPEG")?(type.equalsIgnoreCase("GIF")?Integer.valueOf(8):(type.equalsIgnoreCase("BMP")?Integer.valueOf(8):(type.equalsIgnoreCase("PNG")?Integer.valueOf(6):Integer.valueOf(5)))):Integer.valueOf(5);
    }

    public static Object[] getIsAndType(WordImageEntity entity) throws Exception {
        Object[] result = new Object[2];
        String type;
        if(entity.getType().equals(WordImageEntity.URL)) {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            String path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + entity.getUrl();
            path = path.replace("WEB-INF/classes/", "");
            path = path.replace("file:/", "");
            BufferedImage bufferImg = ImageIO.read(new File(path));
            ImageIO.write(bufferImg, entity.getUrl().substring(entity.getUrl().indexOf(".") + 1, entity.getUrl().length()), byteArrayOut);
            result[0] = byteArrayOut.toByteArray();
            type = entity.getUrl().split("/.")[entity.getUrl().split("/.").length - 1];
        } else {
            result[0] = entity.getData();
            type = getFileExtendName(entity.getData());
        }

        result[1] = getImageType(type);
        return result;
    }

    public static Object getParamsValue(String params, Object object) throws Exception {
        if(params.indexOf(".") != -1) {
            String[] paramsArr = params.split("\\.");
            return getValueDoWhile(object, paramsArr, 0);
        } else {
            return object instanceof Map?((Map)object).get(params):getMethod(params, object.getClass()).invoke(object, new Object[0]);
        }
    }

    public static Object getRealValue(String currentText, Map<String, Object> map) throws Exception {
        Object obj;
        for(String params = ""; currentText.indexOf("{{") != -1; currentText = currentText.replace("{{" + params + "}}", obj.toString())) {
            params = currentText.substring(currentText.indexOf("{{") + 2, currentText.indexOf("}}"));
            obj = getParamsValue(params.trim(), map);
            if(obj instanceof WordImageEntity || obj instanceof List || obj instanceof ExcelListEntity) {
                return obj;
            }
        }

        return currentText;
    }

    public static Object getValueDoWhile(Object object, String[] paramsArr, int index) throws Exception {
        if(object == null) {
            return "";
        } else if(object instanceof WordImageEntity) {
            return object;
        } else {
            if(object instanceof Map) {
                object = ((Map)object).get(paramsArr[index]);
            } else {
                object = getMethod(paramsArr[index], object.getClass()).invoke(object, new Object[0]);
            }

            Object var10000;
            if(index == paramsArr.length - 1) {
                var10000 = object == null?"":object;
            } else {
                ++index;
                var10000 = getValueDoWhile(object, paramsArr, index);
            }

            return var10000;
        }
    }

    public static String doubleToString(Double value) {
        String temp = value.toString();
        if(temp.contains("E")) {
            BigDecimal bigDecimal = new BigDecimal(temp);
            temp = bigDecimal.toPlainString();
        }

        return temp;
    }
}
