//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.view;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.AbstractView;

public abstract class MiniAbstractExcelView extends AbstractView {
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
    protected static final String HSSF = ".xls";
    protected static final String XSSF = ".xlsx";

    public MiniAbstractExcelView() {
        this.setContentType("application/vnd.ms-excel");
    }

    protected boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0;
    }
}
