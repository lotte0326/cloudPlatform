//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.web;

import com.jeecg.mail.entity.P3MailJformInnerMail;
import com.jeecg.mail.entity.P3MailJformInnerMailReceiver;
import com.jeecg.mail.entity.P3MailJformInnerMailReceiverEntity;
import com.jeecg.mail.entity.P3MailTSBaseUser;
import com.jeecg.mail.service.P3MailJformInnerMailAttachService;
import com.jeecg.mail.service.P3MailJformInnerMailReceiverService;
import com.jeecg.mail.service.P3MailJformInnerMailService;
import com.jeecg.mail.service.impl.P3MailP3MailTSBaseUserServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
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
@RequestMapping({"/mail/p3MailJformInnerMail"})
public class P3MailJformInnerMailController extends BaseController {
    @Autowired
    private P3MailJformInnerMailService p3MailJformInnerMailService;
    @Autowired
    private P3MailJformInnerMailReceiverService p3MailJformInnerMailReceiverService;
    @Autowired
    private P3MailP3MailTSBaseUserServiceImpl tsBaseUserService;
    @Autowired
    private P3MailJformInnerMailAttachService p3MailJformInnerMailAttachService;

    public P3MailJformInnerMailController() {
    }

    @RequestMapping(
            params = {"list"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void list(@ModelAttribute P3MailJformInnerMail query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) throws Exception {
        try {
            this.LOG.info(request, " back list");
            MiniDaoPage e = this.p3MailJformInnerMailService.getAll(query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("p3MailJformInnerMail", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(e));
            String viewName = "mail/p3MailJformInnerMail-list.vm";
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toDetail"},
            method = {RequestMethod.GET}
    )
    public void jformInnerMailDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/p3MailJformInnerMail-detail.vm";
        P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.get(id);
        velocityContext.put("p3MailJformInnerMail", p3MailJformInnerMail);
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"toAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toAddDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/p3MailJformInnerMail-add.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doAdd"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute P3MailJformInnerMail p3MailJformInnerMail) {
        AjaxJson j = new AjaxJson();

        try {
            String e = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            p3MailJformInnerMail.setId(e);
            this.p3MailJformInnerMailService.insert(p3MailJformInnerMail);
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
        P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.get(id);
        velocityContext.put("p3MailJformInnerMail", p3MailJformInnerMail);
        String viewName = "mail/p3MailJformInnerMail-edit.vm";
        ViewVelocity.view(request, response, viewName, velocityContext);
    }

    @RequestMapping(
            params = {"doEdit"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute P3MailJformInnerMail p3MailJformInnerMail) {
        AjaxJson j = new AjaxJson();

        try {
            this.p3MailJformInnerMailService.update(p3MailJformInnerMail);
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
            P3MailJformInnerMail e = new P3MailJformInnerMail();
            e.setId(id);
            this.p3MailJformInnerMailService.delete(e);
            j.setMsg("删除成功");
        } catch (Exception var4) {
            log.info(var4.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toSendMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toSendMail(HttpServletRequest request, HttpServletResponse response) {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/toSendMail.vm";

        try {
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doSave"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doSave(@ModelAttribute P3MailJformInnerMail p3MailJformInnerMail, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        try {
            LoginUser e = ContextHolderUtils.getLoginSessionUser();
            String createBy = e.getUserName();
            P3MailTSBaseUser p3MailTsBaseUser = this.tsBaseUserService.getByUsername(createBy);
            String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            p3MailJformInnerMail.setId(randomSeed);
            p3MailJformInnerMail.setCreateBy(createBy);
            p3MailJformInnerMail.setCreateName(p3MailTsBaseUser.getRealname());
            p3MailJformInnerMail.setCreateDate(new Date());
            this.p3MailJformInnerMailService.insert(p3MailJformInnerMail);
            this.saveMailReceiver(p3MailJformInnerMail);
            j.setMsg("保存成功");
        } catch (Exception var8) {
            log.info(var8.getMessage());
            j.setSuccess(false);
            j.setMsg("保存失败");
        }

        return j;
    }

    private void saveMailReceiver(P3MailJformInnerMail p3MailJformInnerMail) {
        String[] userids = p3MailJformInnerMail.getReceiverIds().split(",");
        this.p3MailJformInnerMailReceiverService.deleteByMailId(p3MailJformInnerMail);

        for(int i = 0; i < userids.length; ++i) {
            P3MailJformInnerMailReceiver mailReceiver = new P3MailJformInnerMailReceiver();
            String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            mailReceiver.setId(randomSeed);
            mailReceiver.setMailId(p3MailJformInnerMail.getId());
            mailReceiver.setCreateDate(new Date());
            mailReceiver.setStatus("00");
            mailReceiver.setIsdelete("0");
            mailReceiver.setUpdateDate(new Date());
            mailReceiver.setUserId(userids[i]);
            this.p3MailJformInnerMailReceiverService.insert(mailReceiver);
        }

    }

    @RequestMapping(
            params = {"toUserList"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toUserList(HttpServletRequest request, HttpServletResponse response) {
        VelocityContext velocityContext = new VelocityContext();
        String viewName = "mail/userList.vm";

        try {
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toReceivedMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toReceivedMail(@ModelAttribute P3MailJformInnerMailReceiver query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) {
        try {
            LoginUser e = ContextHolderUtils.getLoginSessionUser();
            String userid = e.getId();
            String viewName = "mail/toReceivedMail.vm";
            MiniDaoPage list = this.p3MailJformInnerMailReceiverService.getByUserid(userid, query, pageNo, pageSize);
            MiniDaoPage list1 = new MiniDaoPage();
            List receivers = list.getResults();
            ArrayList receiversEntity = new ArrayList();

            for(int velocityContext = 0; velocityContext < receivers.size(); ++velocityContext) {
                P3MailJformInnerMailReceiver receiver = (P3MailJformInnerMailReceiver)receivers.get(velocityContext);
                P3MailJformInnerMailReceiverEntity receiverEntity = new P3MailJformInnerMailReceiverEntity();
                receiverEntity.setId(receiver.getId());
                receiverEntity.setMailId(receiver.getMailId());
                receiverEntity.setUserId(receiver.getUserId());
                receiverEntity.setCreateDate(receiver.getCreateDate());
                receiverEntity.setStatus(receiver.getStatus());
                receiverEntity.setUpdateDate(receiver.getUpdateDate());
                receiverEntity.setSender(this.p3MailJformInnerMailService.get(receiver.getMailId()).getCreateName());
                receiverEntity.setSenderName(this.p3MailJformInnerMailService.get(receiver.getMailId()).getCreateBy());
                receiverEntity.setTitle(this.p3MailJformInnerMailService.get(receiver.getMailId()).getTitle());
                receiversEntity.add(receiverEntity);
            }

            list1.setResults(receiversEntity);
            VelocityContext var17 = new VelocityContext();
            var17.put("p3MailJformInnerMail", query);
            var17.put("pageInfos", SystemTools.convertPaginatedList(list1));
            ViewVelocity.view(request, response, viewName, var17);
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"getReceivedMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void getReceivedMail(@ModelAttribute P3MailJformInnerMail query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) throws Exception {
        try {
            this.LOG.info(request, " back list");
            MiniDaoPage e = this.p3MailJformInnerMailService.getAll(query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("p3MailJformInnerMail", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(e));
            String viewName = "mail/p3MailJformInnerMail-list.vm";
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toMailDetail"},
            method = {RequestMethod.GET}
    )
    public void toMailDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "mail/toMailDetail.vm";
            P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.getByReceivedId(id);
            e.put("p3MailJformInnerMail", p3MailJformInnerMail);
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doMailDelete"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doMailDelete(@RequestParam(required = true,value = "ids") String ids) {
        AjaxJson j = new AjaxJson();

        try {
            String[] var6;
            int var5 = (var6 = ids.split(",")).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String e = var6[var4];
                P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver = new P3MailJformInnerMailReceiver();
                p3MailJformInnerMailReceiver.setId(e);
                p3MailJformInnerMailReceiver.setUpdateDate(new Date());
                p3MailJformInnerMailReceiver.setIsdelete("1");
                this.p3MailJformInnerMailReceiverService.update(p3MailJformInnerMailReceiver);
                j.setMsg("删除成功");
            }
        } catch (Exception var8) {
            log.info(var8.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toUnSendMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toUnSendMail(@ModelAttribute P3MailJformInnerMail query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) {
        try {
            LoginUser e = ContextHolderUtils.getLoginSessionUser();
            String createBy = e.getUserName();
            String viewName = "mail/toUnSendMail.vm";
            MiniDaoPage list = this.p3MailJformInnerMailService.getUnSendMailByCreateBy(createBy, query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("p3MailJformInnerMail", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toUnSendMailDetail"},
            method = {RequestMethod.GET}
    )
    public void toUnSendMailDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "mail/toMailDetail.vm";
            P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.get(id);
            e.put("p3MailJformInnerMail", p3MailJformInnerMail);
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doUnSendMailDelete"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doUnSendMailDelete(@RequestParam(required = true,value = "ids") String ids) {
        AjaxJson j = new AjaxJson();

        try {
            String[] var6;
            int var5 = (var6 = ids.split(",")).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String e = var6[var4];
                P3MailJformInnerMail p3MailJformInnerMail = new P3MailJformInnerMail();
                p3MailJformInnerMail.setId(e);
                this.p3MailJformInnerMailService.delete(p3MailJformInnerMail);
                j.setMsg("删除成功");
            }
        } catch (Exception var8) {
            log.info(var8.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toUnSendMailUpdate"},
            method = {RequestMethod.GET}
    )
    public void toUnSendMailUpdate(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "mail/toMailUpdate.vm";
            P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.get(id);
            e.put("p3MailJformInnerMail", p3MailJformInnerMail);
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doUpdate"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doUpdate(@RequestParam(required = true,value = "id") String id, @ModelAttribute P3MailJformInnerMail p3MailJformInnerMail, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        try {
            P3MailJformInnerMail e = this.p3MailJformInnerMailService.get(id);
            e.setTitle(p3MailJformInnerMail.getTitle());
            e.setStatus(p3MailJformInnerMail.getStatus());
            e.setContent(p3MailJformInnerMail.getContent());
            e.setReceiverIds(p3MailJformInnerMail.getReceiverIds());
            e.setReceiverNames(p3MailJformInnerMail.getReceiverNames());
            this.p3MailJformInnerMailService.update(p3MailJformInnerMail);
            this.saveMailReceiver(p3MailJformInnerMail);
            j.setMsg("保存成功");
        } catch (Exception var6) {
            log.info(var6.getMessage());
            j.setSuccess(false);
            j.setMsg("保存失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"toSendedMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toSendedMail(@ModelAttribute P3MailJformInnerMail query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) {
        try {
            LoginUser e = ContextHolderUtils.getLoginSessionUser();
            String createBy = e.getUserName();
            String viewName = "mail/toSendedMail.vm";
            MiniDaoPage list = this.p3MailJformInnerMailService.getSendedMailByCreateBy(createBy, query, pageNo, pageSize);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("p3MailJformInnerMail", query);
            velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toSendedMailDetail"},
            method = {RequestMethod.GET}
    )
    public void toSendedMailDetail(@RequestParam(required = true,value = "id") String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "mail/toMailDetail.vm";
            P3MailJformInnerMail p3MailJformInnerMail = this.p3MailJformInnerMailService.get(id);
            e.put("p3MailJformInnerMail", p3MailJformInnerMail);
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doSendedMailDelete"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doSendedMailDelete(@RequestParam(required = true,value = "ids") String ids) {
        AjaxJson j = new AjaxJson();

        try {
            String[] var6;
            int var5 = (var6 = ids.split(",")).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String e = var6[var4];
                P3MailJformInnerMail p3MailJformInnerMail = new P3MailJformInnerMail();
                p3MailJformInnerMail.setId(e);
                p3MailJformInnerMail.setStatus("02");
                this.p3MailJformInnerMailService.update(p3MailJformInnerMail);
                j.setMsg("删除成功");
            }
        } catch (Exception var8) {
            log.info(var8.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"menuCount"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson menuCount() {
        AjaxJson j = new AjaxJson();
        HashMap maps = new HashMap();
        LoginUser u = ContextHolderUtils.getLoginSessionUser();
        String createBy = u.getUserName();
        String userid = u.getId();
        String viewName = "mail/toSendedMail.vm";
        maps.put("receivedCount", this.p3MailJformInnerMailReceiverService.getReceivedMailCountByUserid(userid));
        maps.put("sendedCount", this.p3MailJformInnerMailService.getSendedMailCountByCreateBy(createBy));
        maps.put("unSendedCount", this.p3MailJformInnerMailService.getUnSendedMailCountByCreateBy(createBy));
        maps.put("deletedCount", this.p3MailJformInnerMailReceiverService.getDeletedMailCountByUserid(userid));
        j.setAttributes(maps);
        return j;
    }

    @RequestMapping(
            params = {"toDeletedMail"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void toDeletedMail(@ModelAttribute P3MailJformInnerMailReceiver query, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "100") int pageSize) {
        try {
            LoginUser e = ContextHolderUtils.getLoginSessionUser();
            String userid = e.getId();
            String viewName = "mail/toDeletedMail.vm";
            MiniDaoPage list = this.p3MailJformInnerMailReceiverService.getDeletedByUserid(userid, query, pageNo, pageSize);
            MiniDaoPage list1 = new MiniDaoPage();
            List receivers = list.getResults();
            ArrayList receiversEntity = new ArrayList();

            for(int velocityContext = 0; velocityContext < receivers.size(); ++velocityContext) {
                P3MailJformInnerMailReceiver receiver = (P3MailJformInnerMailReceiver)receivers.get(velocityContext);
                P3MailJformInnerMailReceiverEntity receiverEntity = new P3MailJformInnerMailReceiverEntity();
                receiverEntity.setId(receiver.getId());
                receiverEntity.setMailId(receiver.getMailId());
                receiverEntity.setUserId(receiver.getUserId());
                receiverEntity.setCreateDate(receiver.getCreateDate());
                receiverEntity.setStatus(receiver.getStatus());
                receiverEntity.setUpdateDate(receiver.getUpdateDate());
                receiverEntity.setSender(this.p3MailJformInnerMailService.get(receiver.getMailId()).getCreateName());
                receiverEntity.setSenderName(this.p3MailJformInnerMailService.get(receiver.getMailId()).getCreateBy());
                receiverEntity.setTitle(this.p3MailJformInnerMailService.get(receiver.getMailId()).getTitle());
                receiversEntity.add(receiverEntity);
            }

            list1.setResults(receiversEntity);
            VelocityContext var17 = new VelocityContext();
            var17.put("p3MailJformInnerMail", query);
            var17.put("pageInfos", SystemTools.convertPaginatedList(list1));
            ViewVelocity.view(request, response, viewName, var17);
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doDeletedMailDelete"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson doDeletedMailDelete(@RequestParam(required = true,value = "ids") String ids) {
        AjaxJson j = new AjaxJson();

        try {
            String[] var6;
            int var5 = (var6 = ids.split(",")).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String e = var6[var4];
                P3MailJformInnerMailReceiver p3MailJformInnerMailReceiver = new P3MailJformInnerMailReceiver();
                p3MailJformInnerMailReceiver.setId(e);
                this.p3MailJformInnerMailReceiverService.delete(p3MailJformInnerMailReceiver);
                j.setMsg("删除成功");
            }
        } catch (Exception var8) {
            log.info(var8.getMessage());
            j.setSuccess(false);
            j.setMsg("删除失败");
        }

        return j;
    }
}
