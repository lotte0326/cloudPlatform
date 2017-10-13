//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.page;

import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.page.PaginatedArrayList;
import org.jeecgframework.p3.core.page.PaginatedList;

public class SystemTools {
    public SystemTools() {
    }

    public static PaginatedList convertPaginatedList(MiniDaoPage<?> list) {
        PaginatedArrayList paginatedList = null;
        if(list != null) {
            paginatedList = new PaginatedArrayList(list.getPage(), list.getRows());
            paginatedList.setPageSize(list.getRows());
            paginatedList.setTotalItem(list.getTotal());
            paginatedList.addAll(list.getResults());
        }

        return paginatedList;
    }
}
