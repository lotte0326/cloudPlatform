//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.cache;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jeecgframework.poi.cache.manager.POICacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExcelCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelCache.class);

    public ExcelCache() {
    }

    public static Workbook getWorkbook(String url, Integer[] sheetNums, boolean needAll) {
        InputStream is = null;
        List sheetList = Arrays.asList(sheetNums);

        try {
            is = POICacheManager.getFile(url);
            Workbook e = WorkbookFactory.create(is);
            if(!needAll) {
                for(int i = e.getNumberOfSheets() - 1; i >= 0; --i) {
                    if(!sheetList.contains(Integer.valueOf(i))) {
                        e.removeSheetAt(i);
                    }
                }
            }

            Workbook var21 = e;
            return var21;
        } catch (InvalidFormatException var18) {
            LOGGER.error(var18.getMessage(), var18);
        } catch (Exception var19) {
            LOGGER.error(var19.getMessage(), var19);
        } finally {
            try {
                is.close();
            } catch (Exception var17) {
                LOGGER.error(var17.getMessage(), var17);
            }

        }

        return null;
    }
}
