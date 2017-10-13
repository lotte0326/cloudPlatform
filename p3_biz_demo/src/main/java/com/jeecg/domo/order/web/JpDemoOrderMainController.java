//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.domo.order.web;

import com.jeecg.domo.order.entity.JpDemoOrderCustomEntity;
import com.jeecg.domo.order.entity.JpDemoOrderMainEntity;
import com.jeecg.domo.order.entity.JpDemoOrderProductEntity;
import com.jeecg.domo.order.page.JpDemoOrderMainPage;
import com.jeecg.domo.order.service.JpDemoOrderCustomService;
import com.jeecg.domo.order.service.JpDemoOrderMainService;
import com.jeecg.domo.order.service.JpDemoOrderProductService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/demo/jpDemoOrderMain"})
public class JpDemoOrderMainController extends BaseController {
    @Autowired
    private JpDemoOrderMainService jpDemoOrderMainService;
    @Autowired
    private JpDemoOrderProductService jpDemoOrderProductService;
    @Autowired
    private JpDemoOrderCustomService jpDemoOrderCustomService;

    public JpDemoOrderMainController() {
    }

    @RequestMapping(
            params = {"list"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void list(@ModelAttribute JpDemoOrderMainEntity query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "10") int pageSize) throws Exception {
        try {
            MiniDaoPage e = this.jpDemoOrderMainService.getAll(query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            query.setDelflag(Integer.valueOf(0));
            velocityContext.put("jpDemoOrderMain", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(e));
            String viewName = "demo/order/jpDemoOrderMain-list.vm";
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toDetail"},
            method = {RequestMethod.GET}
    )
    public void jpDemoOrderMainDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "demo/order/jpDemoOrderMain-detail.vm";
        JpDemoOrderMainEntity jpDemoOrderMain = this.jpDemoOrderMainService.get(id);
        List jpDemoOrderCustomEntities = this.jpDemoOrderCustomService.getByOrderCode(jpDemoOrderMain.getGoOrderCode());
        List jpDemoOrderProductEntities = this.jpDemoOrderProductService.getByOrderCode(jpDemoOrderMain.getGoOrderCode());
        velocityContext.put("jpDemoOrderMain", jpDemoOrderMain);
        velocityContext.put("jpDemoOrderCustomEntities", jpDemoOrderCustomEntities);
        velocityContext.put("jpDemoOrderProductEntities", jpDemoOrderProductEntities);
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"toAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toAddDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "demo/order/jpDemoOrderMain-add.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute JpDemoOrderMainEntity jpDemoOrderMain, @ModelAttribute JpDemoOrderMainPage jpDemoOrderMainPage) {
        AjaxJson j = new AjaxJson();

        try {
            List e = jpDemoOrderMainPage.getJpDemoOrderCustomEntities();
            if(e.size() <= 0) {
                j.setMsg("请至少添加一个客户详情");
                j.setSuccess(false);
                return j;
            }

            List jpDemoOrderProductEntities = jpDemoOrderMainPage.getJpDemoOrderProductEntities();
            if(jpDemoOrderProductEntities.size() <= 0) {
                j.setMsg("请至少添加一个产品详情");
                j.setSuccess(false);
                return j;
            }

            String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            jpDemoOrderMain.setId(randomSeed);
            jpDemoOrderMain.setDelflag(Integer.valueOf(0));
            jpDemoOrderMain.setCreateDt(new Date());
            this.jpDemoOrderMainService.insert(jpDemoOrderMain);
            Iterator var8 = jpDemoOrderProductEntities.iterator();

            while(var8.hasNext()) {
                JpDemoOrderProductEntity jpDemoOrderCustomEntity = (JpDemoOrderProductEntity)var8.next();
                randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                jpDemoOrderCustomEntity.setId(randomSeed);
                if(StringUtils.isEmpty(jpDemoOrderMain.getGoOrderCode())) {
                    j.setMsg("关联外键不能为空");
                    j.setSuccess(false);
                    return j;
                }

                jpDemoOrderCustomEntity.setGoOrderCode(jpDemoOrderMain.getGoOrderCode());
                jpDemoOrderCustomEntity.setCreateDt(new Date());
                jpDemoOrderCustomEntity.setDelflag(Integer.valueOf(0));
                this.jpDemoOrderProductService.insert(jpDemoOrderCustomEntity);
            }

            var8 = e.iterator();

            while(var8.hasNext()) {
                JpDemoOrderCustomEntity jpDemoOrderCustomEntity1 = (JpDemoOrderCustomEntity)var8.next();
                randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                jpDemoOrderCustomEntity1.setId(randomSeed);
                if(StringUtils.isEmpty(jpDemoOrderMain.getGoOrderCode())) {
                    j.setMsg("关联外键不能为空");
                    j.setSuccess(false);
                    return j;
                }

                jpDemoOrderCustomEntity1.setGoOrderCode(jpDemoOrderMain.getGoOrderCode());
                jpDemoOrderCustomEntity1.setCreateDt(new Date());
                jpDemoOrderCustomEntity1.setDelflag(Integer.valueOf(0));
                this.jpDemoOrderCustomService.insert(jpDemoOrderCustomEntity1);
            }

            j.setMsg("保存成功");
        } catch (Exception var9) {
            log.info(var9.getMessage());
            j.setSuccess(false);
            j.setMsg("保存失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toEdit"},
            method = {RequestMethod.GET}
    )
    public void toEdit(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            VelocityContext e = new VelocityContext();
            JpDemoOrderMainEntity jpDemoOrderMain = this.jpDemoOrderMainService.get(id);
            List jpDemoOrderCustomEntities = this.jpDemoOrderCustomService.getByOrderCode(jpDemoOrderMain.getGoOrderCode());
            List jpDemoOrderProductEntities = this.jpDemoOrderProductService.getByOrderCode(jpDemoOrderMain.getGoOrderCode());
            e.put("jpDemoOrderMain", jpDemoOrderMain);
            e.put("jpDemoOrderCustomEntities", jpDemoOrderCustomEntities);
            e.put("jpDemoOrderProductEntities", jpDemoOrderProductEntities);
            String viewName = "demo/order/jpDemoOrderMain-edit.vm";
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doEdit"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute JpDemoOrderMainEntity jpDemoOrderMain, @ModelAttribute JpDemoOrderMainPage jpDemoOrderMainPage) {
        AjaxJson j = new AjaxJson();

        try {
            List e = jpDemoOrderMainPage.getJpDemoOrderCustomEntities();
            if(e.size() <= 0) {
                j.setMsg("请至少添加一个客户详情");
                j.setSuccess(false);
                return j;
            } else {
                List jpDemoOrderProductEntities = jpDemoOrderMainPage.getJpDemoOrderProductEntities();
                if(jpDemoOrderProductEntities.size() <= 0) {
                    j.setMsg("请至少添加一个产品详情");
                    j.setSuccess(false);
                    return j;
                } else {
                    this.jpDemoOrderMainService.update(jpDemoOrderMain);
                    JpDemoOrderMainEntity tempJpDemoOrderMainEntity = this.jpDemoOrderMainService.get(jpDemoOrderMain.getId());
                    boolean jpDemoOrderCustomIsChange = false;
                    boolean jpDemoOrderProductIsChange = false;
                    if(!tempJpDemoOrderMainEntity.getGoOrderCode().equals(jpDemoOrderMain.getGoOrderCode())) {
                        this.jpDemoOrderCustomService.deleteByOrderCode(tempJpDemoOrderMainEntity.getGoOrderCode());
                        jpDemoOrderCustomIsChange = true;
                        this.jpDemoOrderProductService.deleteByOrderCode(tempJpDemoOrderMainEntity.getGoOrderCode());
                        jpDemoOrderProductIsChange = true;
                    } else {
                        Integer entity = this.jpDemoOrderCustomService.getCountByOrderCode(jpDemoOrderMain.getGoOrderCode());
                        if(entity.intValue() != e.size()) {
                            this.jpDemoOrderCustomService.deleteByOrderCode(jpDemoOrderMain.getGoOrderCode());
                            jpDemoOrderCustomIsChange = true;
                        }

                        Integer jpDemoOrderProductCount = Integer.valueOf(this.jpDemoOrderProductService.getCountByOrderCode(jpDemoOrderMain.getGoOrderCode()));
                        if(jpDemoOrderProductCount.intValue() != jpDemoOrderProductEntities.size()) {
                            this.jpDemoOrderProductService.deleteByOrderCode(jpDemoOrderMain.getGoOrderCode());
                            jpDemoOrderProductIsChange = true;
                        }
                    }

                    Iterator jpDemoOrderProductCount1 = e.iterator();

                    while(true) {
                        String randomSeed;
                        while(jpDemoOrderProductCount1.hasNext()) {
                            JpDemoOrderCustomEntity entity1 = (JpDemoOrderCustomEntity)jpDemoOrderProductCount1.next();
                            if(!StringUtils.isEmpty(entity1.getId()) && !jpDemoOrderCustomIsChange) {
                                this.jpDemoOrderCustomService.update(entity1);
                            } else {
                                randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                entity1.setId(randomSeed);
                                entity1.setCreateDt(new Date());
                                entity1.setDelflag(Integer.valueOf(0));
                                entity1.setGoOrderCode(jpDemoOrderMain.getGoOrderCode());
                                this.jpDemoOrderCustomService.insert(entity1);
                            }
                        }

                        jpDemoOrderProductCount1 = jpDemoOrderProductEntities.iterator();

                        while(true) {
                            while(jpDemoOrderProductCount1.hasNext()) {
                                JpDemoOrderProductEntity entity2 = (JpDemoOrderProductEntity)jpDemoOrderProductCount1.next();
                                if(!StringUtils.isEmpty(entity2.getId()) && !jpDemoOrderProductIsChange) {
                                    this.jpDemoOrderProductService.update(entity2);
                                } else {
                                    randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                    entity2.setId(randomSeed);
                                    entity2.setCreateDt(new Date());
                                    entity2.setDelflag(Integer.valueOf(0));
                                    entity2.setGoOrderCode(jpDemoOrderMain.getGoOrderCode());
                                    this.jpDemoOrderProductService.insert(entity2);
                                }
                            }

                            j.setMsg("编辑成功");
                            return j;
                        }
                    }
                }
            }
        } catch (Exception var12) {
            log.info(var12.getMessage());
            j.setSuccess(false);
            j.setMsg("编辑失败");
            return j;
        }
    }

    @RequestMapping(
            params = {"doDelete"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public AjaxJson doDelete(@RequestParam(required = true,value = "id") String id) {
        AjaxJson j = new AjaxJson();

        try {
            JpDemoOrderMainEntity e = new JpDemoOrderMainEntity();
            e.setId(id);
            this.jpDemoOrderMainService.delete(e);
            this.jpDemoOrderCustomService.delByOrderCode(e.getGoOrderCode());
            this.jpDemoOrderProductService.delbyOrderCode(e.getGoOrderCode());
            j.setMsg("删除成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"checkOrderCode"}
    )
    @ResponseBody
    public Map<String, String> checkOrderCode(String orderCode, String id, HttpServletRequest request) {
        orderCode = request.getParameter("param");
        HashMap resultMap = new HashMap();

        try {
            if(orderCode != null && !orderCode.trim().equals("")) {
                JpDemoOrderMainEntity e = this.jpDemoOrderMainService.getByOrderCode(orderCode);
                this.LOG.debug("jpDemoOrderMainEntity == null" + (e == null));
                if(e != null && !e.getId().equals(id)) {
                    resultMap.put("info", "该编码不可用");
                    resultMap.put("status", "n");
                } else {
                    resultMap.put("info", "该编码可用");
                    resultMap.put("status", "y");
                }
            } else {
                resultMap.put("info", "该编码可用");
                resultMap.put("status", "y");
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            resultMap.put("info", "服务端异常：" + var6.getMessage());
            resultMap.put("status", "n");
        }

        this.LOG.debug(resultMap.toString());
        return resultMap;
    }
}
