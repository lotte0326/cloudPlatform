//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.web;

import com.jeecg.mail.entity.P3MailTSUser;
import com.jeecg.mail.service.P3MailTSUserService;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/mail/p3MailTSUser"})
public class P3MailTSUserController extends BaseController {
    @Autowired
    private P3MailTSUserService tSUserServiceP3Mail;

    public P3MailTSUserController() {
    }

    @RequestMapping(
            params = {"list"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void list(@ModelAttribute P3MailTSUser query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) throws Exception {
        try {
            this.LOG.info(request, " back list");
            MiniDaoPage e = this.tSUserServiceP3Mail.getAll(query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("tSUserP3Mail", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(e));
            String viewName = "mail/tSUserP3Mail-list.vm";
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toDetail"},
            method = {RequestMethod.GET}
    )
    public void tSUserDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/tSUserP3Mail-detail.vm";
        P3MailTSUser tSUserP3Mail = this.tSUserServiceP3Mail.get(id);
        velocityContext.put("tSUserP3Mail", tSUserP3Mail);
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"toAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toAddDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/tSUserP3Mail-add.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute P3MailTSUser tSUserP3Mail) {
        AjaxJson j = new AjaxJson();

        try {
            String e = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            tSUserP3Mail.setId(e);
            this.tSUserServiceP3Mail.insert(tSUserP3Mail);
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
    public void toEdit(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        P3MailTSUser tSUserP3Mail = this.tSUserServiceP3Mail.get(id);
        velocityContext.put("tSUserP3Mail", tSUserP3Mail);
        String viewName = "mail/tSUserP3Mail-edit.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doEdit"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute P3MailTSUser tSUserP3Mail) {
        AjaxJson j = new AjaxJson();

        try {
            this.tSUserServiceP3Mail.update(tSUserP3Mail);
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
    public AjaxJson doDelete(@RequestParam(required = true,value = "id") String id) {
        AjaxJson j = new AjaxJson();

        try {
            P3MailTSUser e = new P3MailTSUser();
            e.setId(id);
            this.tSUserServiceP3Mail.delete(e);
            j.setMsg("删除成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }
}
