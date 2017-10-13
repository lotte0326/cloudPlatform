//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.cache;

import org.jeecgframework.poi.cache.manager.POICacheManager;
import org.jeecgframework.poi.word.entity.MyXWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class WordCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordCache.class);

    public WordCache() {
    }

    public static MyXWPFDocument getXWPFDocumen(String url) {
        InputStream is = null;

        try {
            is = POICacheManager.getFile(url);
            MyXWPFDocument e = new MyXWPFDocument(is);
            MyXWPFDocument var3 = e;
            return var3;
        } catch (Exception var13) {
            LOGGER.error(var13.getMessage(), var13);
        } finally {
            try {
                is.close();
            } catch (Exception var12) {
                LOGGER.error(var12.getMessage(), var12);
            }

        }

        return null;
    }
}
