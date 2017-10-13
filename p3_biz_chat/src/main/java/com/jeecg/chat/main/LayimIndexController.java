package com.jeecg.chat.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/layim/index"})
public class LayimIndexController extends BaseController {
    public LayimIndexController() {
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public void index(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "layim/main/index.vm";
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}
