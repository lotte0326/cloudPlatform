//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.word;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.parse.ParseWord07;

public final class WordExportUtil {
    private WordExportUtil() {
    }

    public static XWPFDocument exportWord07(String url, Map<String, Object> map) throws Exception {
        return (new ParseWord07()).parseWord(url, map);
    }

    public static void exportWord07(XWPFDocument document, Map<String, Object> map) throws Exception {
        (new ParseWord07()).parseWord(document, map);
    }
}
