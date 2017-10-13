//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.view;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.jeecgframework.poi.excel.view.MiniAbstractExcelView;
import org.springframework.stereotype.Controller;

@Controller("jeecgExcelView")
public class JeecgSingleExcelView extends MiniAbstractExcelView {
    public JeecgSingleExcelView() {
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = null;
        if(model.containsKey("mapList")) {
            List out = (List)model.get("mapList");
            if(out.size() == 0) {
                throw new RuntimeException("MAP_LIST IS NULL");
            }

            workbook = ExcelExportUtil.exportExcel((ExportParams)((Map)out.get(0)).get("params"), (Class)((Map)out.get(0)).get("entity"), (Collection)((Map)out.get(0)).get("data"));

            for(int i = 1; i < out.size(); ++i) {
                (new ExcelExportServer()).createSheet(workbook, (ExportParams)((Map)out.get(i)).get("params"), (Class)((Map)out.get(i)).get("entity"), (Collection)((Map)out.get(i)).get("data"));
            }
        } else {
            workbook = ExcelExportUtil.exportExcel((ExportParams)model.get("params"), (Class)model.get("entity"), (Collection)model.get("data"));
        }

        if(model.containsKey("fileName")) {
            codedFileName = (String)model.get("fileName");
        }

        if(workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if(this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream var8 = response.getOutputStream();
        workbook.write(var8);
        var8.flush();
    }
}
