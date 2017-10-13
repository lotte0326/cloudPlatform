//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.utils.common;

import java.io.Serializable;
import java.util.List;

public class ListResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> values;

    public ListResult() {
    }

    public List<T> getValues() {
        return this.values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
