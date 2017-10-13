//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.web;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.p3.core.common.utils.DateConvertEditor;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class BaseController {
    public static final Logger log = LoggerFactory.getLogger(BaseController.class);
    protected Logger LOG = LoggerFactory.getLogger(BaseController.class);
    public static final String EXCEL_PATH_PREFIX = "WEB-INF/excel/";

    public BaseController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

    protected void responseJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    protected String getExportExcelPath(String excelTemplateName) {
        return "WEB-INF/excel/" + excelTemplateName;
    }
}
