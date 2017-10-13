//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports.verifys;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.entity.params.ExcelVerifyEntity;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.excel.imports.verifys.BaseVerifyHandler;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;

public class VerifyHandlerServer {
    private static final ExcelVerifyHanlderResult DEFAULT_RESULT = new ExcelVerifyHanlderResult(true);

    public VerifyHandlerServer() {
    }

    private void addVerifyResult(ExcelVerifyHanlderResult hanlderResult, ExcelVerifyHanlderResult result) {
        if(!hanlderResult.isSuccess()) {
            result.setSuccess(false);
            result.setMsg((StringUtils.isEmpty(result.getMsg())?"":result.getMsg() + " , ") + hanlderResult.getMsg());
        }

    }

    public ExcelVerifyHanlderResult verifyData(Object object, Object value, String name, ExcelVerifyEntity verify, IExcelVerifyHandler excelVerifyHandler) {
        if(verify == null) {
            return DEFAULT_RESULT;
        } else {
            ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
            if(verify.isNotNull()) {
                this.addVerifyResult(BaseVerifyHandler.notNull(name, value), result);
            }

            if(verify.isEmail()) {
                this.addVerifyResult(BaseVerifyHandler.isEmail(name, value), result);
            }

            if(verify.isMobile()) {
                this.addVerifyResult(BaseVerifyHandler.isMobile(name, value), result);
            }

            if(verify.isTel()) {
                this.addVerifyResult(BaseVerifyHandler.isTel(name, value), result);
            }

            if(verify.getMaxLength() != -1) {
                this.addVerifyResult(BaseVerifyHandler.maxLength(name, value, verify.getMaxLength()), result);
            }

            if(verify.getMinLength() != -1) {
                this.addVerifyResult(BaseVerifyHandler.minLength(name, value, verify.getMinLength()), result);
            }

            if(StringUtils.isNotEmpty(verify.getRegex())) {
                this.addVerifyResult(BaseVerifyHandler.regex(name, value, verify.getRegex(), verify.getRegexTip()), result);
            }

            if(verify.isInterHandler()) {
                this.addVerifyResult(excelVerifyHandler.verifyHandler(object, name, value), result);
            }

            return result;
        }
    }
}
