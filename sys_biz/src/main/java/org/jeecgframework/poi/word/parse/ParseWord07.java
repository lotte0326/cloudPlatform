//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.word.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jeecgframework.poi.cache.WordCache;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.jeecgframework.poi.word.entity.MyXWPFDocument;
import org.jeecgframework.poi.word.entity.WordImageEntity;
import org.jeecgframework.poi.word.entity.params.ExcelListEntity;
import org.jeecgframework.poi.word.parse.excel.ExcelEntityParse;
import org.jeecgframework.poi.word.parse.excel.ExcelMapParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseWord07 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParseWord07.class);

    public ParseWord07() {
    }

    private void addAnImage(WordImageEntity obj, XWPFRun currentRun) throws Exception {
        Object[] isAndType = PoiPublicUtil.getIsAndType(obj);

        try {
            String picId = currentRun.getParagraph().getDocument().addPictureData((byte[])((byte[])isAndType[0]), ((Integer)isAndType[1]).intValue());
            ((MyXWPFDocument)currentRun.getParagraph().getDocument()).createPicture(currentRun, picId, currentRun.getParagraph().getDocument().getNextPicNameNumber(((Integer)isAndType[1]).intValue()), obj.getWidth(), obj.getHeight());
        } catch (Exception var6) {
            LOGGER.error(var6.getMessage(), var6);
        }

    }

    private void changeValues(XWPFParagraph paragraph, XWPFRun currentRun, String currentText, List<Integer> runIndex, Map<String, Object> map) throws Exception {
        Object obj = PoiPublicUtil.getRealValue(currentText, map);
        if(obj instanceof WordImageEntity) {
            currentRun.setText("", 0);
            this.addAnImage((WordImageEntity)obj, currentRun);
        } else {
            currentText = obj.toString();
            currentRun.setText(currentText, 0);
        }

        for(int k = 0; k < runIndex.size(); ++k) {
            ((XWPFRun)paragraph.getRuns().get(((Integer)runIndex.get(k)).intValue())).setText("", 0);
        }

        runIndex.clear();
    }

    private Object checkThisTableIsNeedIterator(XWPFTableCell cell, Map<String, Object> map) throws Exception {
        String text = cell.getText().trim();
        return text.startsWith("{{") && text.endsWith("}}") && text.indexOf("in ") != -1?PoiPublicUtil.getRealValue(text.replace("in ", "").trim(), map):null;
    }

    private void parseAllParagraphic(List<XWPFParagraph> paragraphs, Map<String, Object> map) throws Exception {
        for(int i = 0; i < paragraphs.size(); ++i) {
            XWPFParagraph paragraph = (XWPFParagraph)paragraphs.get(i);
            if(paragraph.getText().indexOf("{{") != -1) {
                this.parseThisParagraph(paragraph, map);
            }
        }

    }

    private void parseThisParagraph(XWPFParagraph paragraph, Map<String, Object> map) throws Exception {
        XWPFRun currentRun = null;
        String currentText = "";
        Boolean isfinde = Boolean.valueOf(false);
        ArrayList runIndex = new ArrayList();

        for(int i = 0; i < paragraph.getRuns().size(); ++i) {
            XWPFRun run = (XWPFRun)paragraph.getRuns().get(i);
            String text = run.getText(0);
            if(!StringUtils.isEmpty(text)) {
                if(isfinde.booleanValue()) {
                    currentText = currentText + text;
                    if(currentText.indexOf("{{") == -1) {
                        isfinde = Boolean.valueOf(false);
                        runIndex.clear();
                    } else {
                        runIndex.add(Integer.valueOf(i));
                    }

                    if(currentText.indexOf("}}") != -1) {
                        this.changeValues(paragraph, currentRun, currentText, runIndex, map);
                        currentText = "";
                        isfinde = Boolean.valueOf(false);
                    }
                } else if(text.indexOf("{") >= 0) {
                    currentText = text;
                    isfinde = Boolean.valueOf(true);
                    currentRun = run;
                } else {
                    currentText = "";
                }

                if(currentText.indexOf("}}") != -1) {
                    this.changeValues(paragraph, currentRun, currentText, runIndex, map);
                    isfinde = Boolean.valueOf(false);
                }
            }
        }

    }

    private void parseThisRow(List<XWPFTableCell> cells, Map<String, Object> map) throws Exception {
        Iterator i$ = cells.iterator();

        while(i$.hasNext()) {
            XWPFTableCell cell = (XWPFTableCell)i$.next();
            this.parseAllParagraphic(cell.getParagraphs(), map);
        }

    }

    private void parseThisTable(XWPFTable table, Map<String, Object> map) throws Exception {
        ExcelEntityParse excelEntityParse = new ExcelEntityParse();

        for(int i = 0; i < table.getNumberOfRows(); ++i) {
            XWPFTableRow row = table.getRow(i);
            List cells = row.getTableCells();
            if(cells.size() == 1) {
                Object listobj = this.checkThisTableIsNeedIterator((XWPFTableCell)cells.get(0), map);
                if(listobj == null) {
                    this.parseThisRow(cells, map);
                } else if(listobj instanceof ExcelListEntity) {
                    table.removeRow(i);
                    excelEntityParse.parseNextRowAndAddRow(table, i, (ExcelListEntity)listobj);
                } else {
                    table.removeRow(i);
                    ExcelMapParse.parseNextRowAndAddRow(table, i, (List)listobj);
                }
            } else {
                this.parseThisRow(cells, map);
            }
        }

    }

    public XWPFDocument parseWord(String url, Map<String, Object> map) throws Exception {
        MyXWPFDocument doc = WordCache.getXWPFDocumen(url);
        this.parseWordSetValue(doc, map);
        return doc;
    }

    public void parseWord(XWPFDocument document, Map<String, Object> map) throws Exception {
        this.parseWordSetValue((MyXWPFDocument)document, map);
    }

    private void parseWordSetValue(MyXWPFDocument doc, Map<String, Object> map) throws Exception {
        this.parseAllParagraphic(doc.getParagraphs(), map);
        this.parseHeaderAndFoot(doc, map);
        Iterator itTable = doc.getTablesIterator();

        while(itTable.hasNext()) {
            XWPFTable table = (XWPFTable)itTable.next();
            if(table.getText().indexOf("{{") != -1) {
                this.parseThisTable(table, map);
            }
        }

    }

    private void parseHeaderAndFoot(MyXWPFDocument doc, Map<String, Object> map) throws Exception {
        List headerList = doc.getHeaderList();
        Iterator footerList = headerList.iterator();

        while(footerList.hasNext()) {
            XWPFHeader i$ = (XWPFHeader)footerList.next();

            for(int xwpfFooter = 0; xwpfFooter < i$.getListParagraph().size(); ++xwpfFooter) {
                this.parseThisParagraph((XWPFParagraph)i$.getListParagraph().get(xwpfFooter), map);
            }
        }

        List var8 = doc.getFooterList();
        Iterator var9 = var8.iterator();

        while(var9.hasNext()) {
            XWPFFooter var10 = (XWPFFooter)var9.next();

            for(int i = 0; i < var10.getListParagraph().size(); ++i) {
                this.parseThisParagraph((XWPFParagraph)var10.getListParagraph().get(i), map);
            }
        }

    }
}
