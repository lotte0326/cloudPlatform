//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.view;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.view.MiniAbstractExcelView;
import org.springframework.stereotype.Controller;

@Controller("jeecgTemplateExcelView")
public class JeecgTemplateExcelView extends MiniAbstractExcelView {
    public JeecgTemplateExcelView() {
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = ExcelExportUtil.exportExcel((TemplateExportParams)model.get("params"), (Class)model.get("entity"), (List)model.get("list"), (Map)model.get("map"));
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
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
}
