//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.auth.web;

import com.jeecg.auth.dao.JwSystemAuthDao;
import com.jeecg.auth.entity.JwSystemAuth;
import com.jeecg.auth.util.SystemUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/p3/auth"})
public class JwSystemAuthController extends BaseController {
    @Autowired
    private JwSystemAuthDao jwSystemAuthDao;

    public JwSystemAuthController() {
    }

    @RequestMapping(
            params = {"list"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void list(@ModelAttribute JwSystemAuth query, HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) throws Exception {
        MiniDaoPage list = this.jwSystemAuthDao.getAll(query, pageNo, pageSize);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("jwSystemAuth", query);
        velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
        String viewName = "auth/jwSystemAuth-list.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"toDetail"},
            method = {RequestMethod.GET}
    )
    public void jwSystemAuthDetail(@RequestParam(required = true,value = "id") Long id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "auth/jwSystemAuth-detail.vm";
        JwSystemAuth jwSystemAuth = this.jwSystemAuthDao.get(id);
        velocityContext.put("jwSystemAuth", jwSystemAuth);
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"toAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toAddDialog(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "auth/jwSystemAuth-add.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute JwSystemAuth jwSystemAuth) {
        AjaxJson j = new AjaxJson();

        try {
            this.jwSystemAuthDao.insert(jwSystemAuth);
            j.setMsg("保存成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("保存失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toEdit"},
            method = {RequestMethod.GET}
    )
    public void toEdit(@RequestParam(required = true,value = "id") Long id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        JwSystemAuth jwSystemAuth = this.jwSystemAuthDao.get(id);
        velocityContext.put("jwSystemAuth", jwSystemAuth);
        String viewName = "auth/jwSystemAuth-edit.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doEdit"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute JwSystemAuth jwSystemAuth) {
        AjaxJson j = new AjaxJson();

        try {
            this.jwSystemAuthDao.update(jwSystemAuth);
            j.setMsg("编辑成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("编辑失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"doDelete"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public AjaxJson doDelete(@RequestParam(required = true,value = "id") Long id) {
        AjaxJson j = new AjaxJson();

        try {
            JwSystemAuth e = new JwSystemAuth();
            e.setId(id);
            this.jwSystemAuthDao.delete(e);
            j.setMsg("删除成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"getAuthTree"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public String getAuthTree(HttpServletRequest request, HttpServletResponse response) {
        String tree = "";

        try {
            List e = this.jwSystemAuthDao.queryMenuAndFuncAuth();
            tree = SystemUtil.listTreeToAuth(e);
            log.info("权限树: " + tree);
        } catch (Exception var5) {
            log.info(var5.getMessage());
        }

        return tree;
    }
}
