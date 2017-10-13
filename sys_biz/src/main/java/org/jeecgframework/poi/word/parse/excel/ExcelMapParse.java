//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.word.parse.excel;

import java.util.Iterator;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jeecgframework.poi.util.PoiPublicUtil;

public final class ExcelMapParse {
    public ExcelMapParse() {
    }

    private static String[] parseCurrentRowGetParams(XWPFTableRow currentRow) {
        List cells = currentRow.getTableCells();
        String[] params = new String[cells.size()];

        for(int i = 0; i < cells.size(); ++i) {
            String text = ((XWPFTableCell)cells.get(i)).getText();
            params[i] = text == null?"":text.trim().replace("{{", "").replace("}}", "");
        }

        return params;
    }

    public static void parseNextRowAndAddRow(XWPFTable table, int index, List<Object> list) throws Exception {
        XWPFTableRow currentRow = table.getRow(index);
        String[] params = parseCurrentRowGetParams(currentRow);
        table.removeRow(index);
        boolean cellIndex = false;
        Iterator i$ = list.iterator();

        while(i$.hasNext()) {
            Object obj = i$.next();
            currentRow = table.createRow();

            int var8;
            for(var8 = 0; var8 < currentRow.getTableCells().size(); ++var8) {
                ((XWPFTableCell)currentRow.getTableCells().get(var8)).setText(PoiPublicUtil.getValueDoWhile(obj, params[var8].split("\\."), 0).toString());
            }

            while(var8 < params.length) {
                currentRow.createCell().setText(PoiPublicUtil.getValueDoWhile(obj, params[var8].split("\\."), 0).toString());
                ++var8;
            }
        }

    }
}
