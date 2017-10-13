//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.view;

import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.AbstractView;

@Controller("jeecgTemplateWordView")
public class JeecgTemplateWordView extends AbstractView {
    private static final String CONTENT_TYPE = "application/msword";

    public JeecgTemplateWordView() {
        this.setContentType("application/msword");
    }

    public boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件.docx";
        if(model.containsKey("fileName")) {
            codedFileName = (String)model.get("fileName") + ".docx";
        }

        if(this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        XWPFDocument document = WordExportUtil.exportWord07((String)model.get("url"), (Map)model.get("map"));
        ServletOutputStream out = response.getOutputStream();
        document.write(out);
        out.flush();
    }
}
