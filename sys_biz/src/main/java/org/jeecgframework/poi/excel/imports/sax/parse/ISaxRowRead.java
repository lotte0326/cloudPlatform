//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.imports.sax.parse;

import java.util.List;
import org.jeecgframework.poi.excel.entity.sax.SaxReadCellEntity;

public interface ISaxRowRead {
    <T> List<T> getList();

    void parse(int var1, List<SaxReadCellEntity> var2);
}
