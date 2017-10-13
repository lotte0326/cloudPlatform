//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.page;

import java.util.List;

public interface PaginatedList<T> extends List<T> {
    boolean isMiddlePage();

    boolean isLastPage();

    boolean isNextPageAvailable();

    boolean isPreviousPageAvailable();

    int getPageSize();

    void setPageSize(int var1);

    int getIndex();

    void setIndex(int var1);

    int getTotalItem();

    void setTotalItem(int var1);

    int getTotalPage();

    int getStartRow();

    int getEndRow();

    int getNextPage();

    int getPreviousPage();

    boolean isFirstPage();
}
