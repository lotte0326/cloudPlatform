//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.page;

import java.util.ArrayList;
import org.jeecgframework.p3.core.page.PaginatedList;

public class PaginatedArrayList<T> extends ArrayList<T> implements PaginatedList<T> {
    public static final int PAGESIZE_DEFAULT = 20;
    private int pageSize;
    private int index;
    private int totalItem;
    private int totalPage;
    private int startRow;
    private int endRow;

    public PaginatedArrayList() {
        this.repaginate();
    }

    public PaginatedArrayList(int index, int pageSize) {
        this.index = index;
        this.pageSize = pageSize;
        this.repaginate();
    }

    public boolean isFirstPage() {
        return this.index <= 1;
    }

    public boolean isMiddlePage() {
        return !this.isFirstPage() && !this.isLastPage();
    }

    public boolean isLastPage() {
        return this.index >= this.totalPage;
    }

    public boolean isNextPageAvailable() {
        return !this.isLastPage();
    }

    public boolean isPreviousPageAvailable() {
        return !this.isFirstPage();
    }

    public int getNextPage() {
        return this.isLastPage()?this.totalItem:this.index + 1;
    }

    public int getPreviousPage() {
        return this.isFirstPage()?1:this.index - 1;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.repaginate();
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.repaginate();
    }

    public int getTotalItem() {
        return this.totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        this.repaginate();
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    private void repaginate() {
        if(this.pageSize < 1) {
            this.pageSize = 20;
        }

        if(this.index < 1) {
            this.index = 1;
        }

        if(this.totalItem > 0) {
            this.totalPage = this.totalItem / this.pageSize + (this.totalItem % this.pageSize > 0?1:0);
            if(this.index > this.totalPage) {
                this.index = this.totalPage;
            }

            this.endRow = this.index * this.pageSize;
            this.startRow = this.endRow - this.pageSize + 1;
            if(this.endRow > this.totalItem) {
                this.endRow = this.totalItem;
            }
        }

    }
}
