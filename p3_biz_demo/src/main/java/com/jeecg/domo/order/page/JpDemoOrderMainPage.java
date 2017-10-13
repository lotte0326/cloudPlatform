//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.page;

import com.jeecg.domo.order.entity.JpDemoOrderCustomEntity;
import com.jeecg.domo.order.entity.JpDemoOrderProductEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JpDemoOrderMainPage implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<JpDemoOrderCustomEntity> jpDemoOrderCustomEntities = new ArrayList();
    private List<JpDemoOrderProductEntity> jpDemoOrderProductEntities = new ArrayList();

    public JpDemoOrderMainPage() {
    }

    public List<JpDemoOrderCustomEntity> getJpDemoOrderCustomEntities() {
        return this.jpDemoOrderCustomEntities;
    }

    public void setJpDemoOrderCustomEntities(List<JpDemoOrderCustomEntity> jpDemoOrderCustomEntities) {
        this.jpDemoOrderCustomEntities = jpDemoOrderCustomEntities;
    }

    public List<JpDemoOrderProductEntity> getJpDemoOrderProductEntities() {
        return this.jpDemoOrderProductEntities;
    }

    public void setJpDemoOrderProductEntities(List<JpDemoOrderProductEntity> jpDemoOrderProductEntities) {
        this.jpDemoOrderProductEntities = jpDemoOrderProductEntities;
    }
}
