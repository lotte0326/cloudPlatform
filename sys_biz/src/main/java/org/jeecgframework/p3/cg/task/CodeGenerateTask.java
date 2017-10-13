//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg.task;

import java.util.concurrent.Callable;
import org.jeecgframework.p3.cg.TableInfo;
import org.jeecgframework.p3.cg.def.FtlDef;
import org.jeecgframework.p3.cg.factory.CodeGenerateFactory;

public class CodeGenerateTask implements Callable<Boolean> {
    private TableInfo tableInfo;

    public CodeGenerateTask(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public Boolean call() throws Exception {
        boolean flag = false;

        try {
            CodeGenerateFactory.codeGenerateByFTL(this.tableInfo.getTableName(), this.tableInfo.getTableComment(), FtlDef.KEY_TYPE_02);
            flag = true;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return Boolean.valueOf(flag);
    }
}
