//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.pojo;

import java.util.List;

public class MiniDaoPage<T> {
    private int page;
    private int rows;
    private int total;
    private int pages;
    private List<T> results;

    public MiniDaoPage() {
    }

    public int getPage() {
        return this.page;
    }

    public int getPages() {
        return this.pages;
    }

    public List<T> getResults() {
        return this.results;
    }

    public int getRows() {
        return this.rows;
    }

    public int getTotal() {
        return this.total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setTotal(int total) {
        this.total = total;
        this.pages = total / this.rows + (total % this.rows > 0?1:0);
    }
}
