//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.mail.main;

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
@RequestMapping({"/mail/index"})
public class mailIndexController extends BaseController {
    public mailIndexController() {
    }

    @RequestMapping(
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void index(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "mail/main/index.vm";
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}
