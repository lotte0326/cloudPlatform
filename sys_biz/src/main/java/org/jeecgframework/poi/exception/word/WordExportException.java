//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.exception.word;

import org.jeecgframework.poi.exception.word.enmus.WordExportEnum;

public class WordExportException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WordExportException() {
    }

    public WordExportException(String msg) {
        super(msg);
    }

    public WordExportException(WordExportEnum exception) {
        super(exception.getMsg());
    }
}
