//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.cg.def;

public class TableConvert {
    public TableConvert() {
    }

    public static String getNullAble(String nullable) {
        return !"YES".equals(nullable) && !"yes".equals(nullable) && !"y".equals(nullable) && !"Y".equals(nullable)?(!"NO".equals(nullable) && !"N".equals(nullable) && !"no".equals(nullable) && !"n".equals(nullable)?null:"N"):"Y";
    }
}
