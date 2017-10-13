//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.exception;

import org.jeecgframework.p3.core.common.exception.ActBizException;
import org.jeecgframework.p3.core.common.exception.ExceptionEnum;

public class BargainException extends ActBizException {
    private static final long serialVersionUID = 7787204828254391226L;
    private String chineseMsg;

    public BargainException(Throwable cause, String defineCode, String errorMessage) {
        super(defineCode, errorMessage, cause);
    }

    public BargainException(Throwable cause, ExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg(), cause);
        this.chineseMsg = exp.getErrChineseMsg();
    }

    public BargainException(String defineCode) {
        super(defineCode);
    }

    public BargainException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public BargainException(ExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg());
        this.chineseMsg = exp.getErrChineseMsg();
    }

    public BargainException(ExceptionEnum exp, String errMsg) {
        super(exp.getErrCode(), errMsg);
    }

    public BargainException(ExceptionEnum exp, String errMsg, String chineseMsg) {
        super(exp.getErrCode(), errMsg);
        this.chineseMsg = chineseMsg;
    }

    public BargainException newInstance(String message, Object... args) {
        BargainException ex = new BargainException(this.defineCode);
        ex.setMessage(message, args);
        return ex;
    }

    public String getChineseMsg() {
        return this.getMessage();
    }

    public String getMessage() {
        return this.chineseMsg == null?this.message:this.chineseMsg;
    }
}
