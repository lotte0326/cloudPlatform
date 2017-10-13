//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.html;

import java.util.Formatter;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.html.helper.CellValueHelper;
import org.jeecgframework.poi.excel.html.helper.MergedRegionHelper;
import org.jeecgframework.poi.excel.html.helper.StylerHelper;

public class ExcelToHtmlServer {
    private Workbook wb;
    private int sheetNum;
    private int cssRandom;
    private boolean completeHTML;
    private Formatter out;
    private boolean gotBounds;
    private int firstColumn;
    private int endColumn;
    private static final String COL_HEAD_CLASS = "colHeader";
    private static final String DEFAULTS_CLASS = "excelDefaults";

    public ExcelToHtmlServer(Workbook wb, boolean completeHTML, int sheetNum) {
        this.wb = wb;
        this.completeHTML = completeHTML;
        this.sheetNum = sheetNum;
        this.cssRandom = (int)Math.ceil(Math.random() * 1000.0D);
    }

    public String printPage() {
        String var1;
        try {
            this.ensureOut();
            if(this.completeHTML) {
                this.out.format("<!DOCTYPE HTML>%n", new Object[0]);
                this.out.format("<html>%n", new Object[0]);
                this.out.format("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">%n", new Object[0]);
                this.out.format("<head>%n", new Object[0]);
            }

            new StylerHelper(this.wb, this.out, this.sheetNum, this.cssRandom);
            if(this.completeHTML) {
                this.out.format("</head>%n", new Object[0]);
                this.out.format("<body>%n", new Object[0]);
            }

            this.print();
            if(this.completeHTML) {
                this.out.format("</body>%n", new Object[0]);
                this.out.format("</html>%n", new Object[0]);
            }

            var1 = this.out.toString();
        } finally {
            if(this.out != null) {
                this.out.close();
            }

        }

        return var1;
    }

    private void print() {
        this.printSheets();
    }

    private void ensureOut() {
        if(this.out == null) {
            this.out = new Formatter(new StringBuilder());
        }

    }

    private void printSheets() {
        Sheet sheet = this.wb.getSheetAt(this.sheetNum);
        this.printSheet(sheet);
    }

    private void printSheet(Sheet sheet) {
        this.out.format("<table class=\'%s\' width=\'%s\'>%n", new Object[]{"excelDefaults", Integer.valueOf(this.getTableWidth(sheet))});
        this.printCols(sheet);
        this.printSheetContent(sheet);
        this.out.format("</table>%n", new Object[0]);
    }

    private void printCols(Sheet sheet) {
        this.ensureColumnBounds(sheet);

        for(int i = this.firstColumn; i < this.endColumn; ++i) {
            this.out.format("<col style=\'width:%spx;\' />%n", new Object[]{Integer.valueOf(sheet.getColumnWidth(i) / 32)});
        }

    }

    private int getTableWidth(Sheet sheet) {
        this.ensureColumnBounds(sheet);
        int width = 0;

        for(int i = this.firstColumn; i < this.endColumn; ++i) {
            width += sheet.getColumnWidth(i) / 32;
        }

        return width;
    }

    private void ensureColumnBounds(Sheet sheet) {
        if(!this.gotBounds) {
            Iterator iter = sheet.rowIterator();
            this.firstColumn = iter.hasNext()?2147483647:0;
            this.endColumn = 0;

            while(iter.hasNext()) {
                Row row = (Row)iter.next();
                short firstCell = row.getFirstCellNum();
                if(firstCell >= 0) {
                    this.firstColumn = Math.min(this.firstColumn, firstCell);
                    this.endColumn = Math.max(this.endColumn, row.getLastCellNum());
                }
            }

            this.gotBounds = true;
        }
    }

    private void printColumnHeads(Sheet sheet) {
        this.out.format("<thead>%n", new Object[0]);
        this.out.format("  <tr class=%s>%n", new Object[]{"colHeader"});
        this.out.format("    <th class=%s>&#x25CA;</th>%n", new Object[]{"colHeader"});
        StringBuilder colName = new StringBuilder();

        for(int i = this.firstColumn; i < this.endColumn; ++i) {
            colName.setLength(0);
            int cnum = i;

            do {
                colName.insert(0, (char)(65 + cnum % 26));
                cnum /= 26;
            } while(cnum > 0);

            this.out.format("    <th class=%s>%s</th>%n", new Object[]{"colHeader", colName});
        }

        this.out.format("  </tr>%n", new Object[0]);
        this.out.format("</thead>%n", new Object[0]);
    }

    private void printSheetContent(Sheet sheet) {
        MergedRegionHelper mergedRegionHelper = new MergedRegionHelper(sheet);
        CellValueHelper cellValueHelper = new CellValueHelper(this.wb, this.cssRandom);
        this.out.format("<tbody>%n", new Object[0]);
        Iterator rows = sheet.rowIterator();

        for(int rowIndex = 1; rows.hasNext(); ++rowIndex) {
            Row row = (Row)rows.next();
            this.out.format("  <tr style=\'height:%spx;\'>%n", new Object[]{Integer.valueOf(row.getHeight() / 15)});

            for(int i = this.firstColumn; i < this.endColumn; ++i) {
                if(mergedRegionHelper.isNeedCreate(rowIndex, i)) {
                    String content = "&nbsp;";
                    CellStyle style = null;
                    if(i >= row.getFirstCellNum() && i < row.getLastCellNum()) {
                        Cell rowAndColSpan = row.getCell(i);
                        if(rowAndColSpan != null) {
                            style = rowAndColSpan.getCellStyle();
                            content = cellValueHelper.getHtmlValue(rowAndColSpan);
                        }
                    }

                    if(mergedRegionHelper.isMergedRegion(rowIndex, i)) {
                        Integer[] var11 = mergedRegionHelper.getRowAndColSpan(rowIndex, i);
                        this.out.format("    <td rowspan=\'%s\' colspan=\'%s\' class=\'%s\' >%s</td>%n", new Object[]{var11[0], var11[1], this.styleName(style), content});
                    } else {
                        this.out.format("    <td class=\'%s\'>%s</td>%n", new Object[]{this.styleName(style), content});
                    }
                }
            }

            this.out.format("  </tr>%n", new Object[0]);
        }

        this.out.format("</tbody>%n", new Object[0]);
    }

    private String styleName(CellStyle style) {
        return style == null?"":String.format("style_%02x_%s font_%s_%s", new Object[]{Short.valueOf(style.getIndex()), Integer.valueOf(this.cssRandom), Short.valueOf(style.getFontIndex()), Integer.valueOf(this.cssRandom)});
    }
}
