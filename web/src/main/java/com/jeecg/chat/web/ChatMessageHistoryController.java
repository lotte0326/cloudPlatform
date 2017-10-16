//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.web;

import com.jeecg.chat.entity.ChatMessageHistory;
import com.jeecg.chat.service.ChatMessageHistoryService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/chat/chatMessageHistory"})
public class ChatMessageHistoryController extends BaseController {
    @Autowired
    private ChatMessageHistoryService chatMessageHistoryService;

    public ChatMessageHistoryController() {
    }

    @RequestMapping(
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void index(String id, String type, String from, HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "10") int pageSize) {
        try {
            int e = from.indexOf("?");
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("from", from.substring(0, e));
            velocityContext.put("to", from.substring(e + 4));
            velocityContext.put("type", type);
            String viewName = "chat/history.vm";
            ViewVelocity.view(request, response, viewName, velocityContext);
        } catch (Exception var11) {
            log.debug(var11.getMessage());
        }

    }

    @RequestMapping(
            params = {"getHistoryMessage"}
    )
    @ResponseBody
    public AjaxJson getHistoryMessage(String from, String to, String type, @RequestParam(required = false,value = "pageNo",defaultValue = "1") int pageNo, @RequestParam(required = false,value = "pageSize",defaultValue = "1000") int pageSize) {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            ChatMessageHistory e = new ChatMessageHistory();
            e.setFrom(from);
            e.setTo(to);
            e.setType(type);
            MiniDaoPage list = this.chatMessageHistoryService.getAll(e, pageNo, pageSize);
            ajaxJson.setObj(SystemTools.convertPaginatedList(list));
            ajaxJson.setMsg("success");
        } catch (Exception var9) {
            log.debug(var9.getMessage());
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("获取数据失败：" + var9.getMessage());
        }

        return ajaxJson;
    }
}
