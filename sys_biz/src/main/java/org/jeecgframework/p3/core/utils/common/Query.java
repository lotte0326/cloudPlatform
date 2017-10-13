//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.utils.common;

import java.io.Serializable;

public class Query<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T query;

    public Query() {
    }

    public T getQuery() {
        return this.query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}
