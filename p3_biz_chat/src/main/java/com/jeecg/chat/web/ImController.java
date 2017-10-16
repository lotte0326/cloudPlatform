//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.P3ImTSDepart;
import com.jeecg.chat.service.ImService;
import com.jeecg.chat.util.ChatResourceConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping({"/chat/imController"})
public class ImController extends BaseController {
    private Log log = LogFactory.getLog(this.getClass());
    private String localPath = "D://upFiles";
    @Autowired
    private ImService imService;

    public ImController() {
    }

    @RequestMapping(
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public void index(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        try {
            VelocityContext e = new VelocityContext();
            String viewName = "chat/index.vm";
            ViewVelocity.view(request, response, viewName, e);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping(
            params = {"getMembers"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public JSONObject getMembers(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List imTSUsers = this.imService.getUsers();
        LoginUser u = ContextHolderUtils.getLoginSessionUser();
        JSONArray list = new JSONArray();
        Iterator data = imTSUsers.iterator();

        while(data.hasNext()) {
            P3ImTSBaseUser jsonObject = (P3ImTSBaseUser)data.next();
            if(this.imService.isInThisOrg(u.getDepartid(), jsonObject.getMaindepartid())) {
                JSONObject json = new JSONObject();
                json.put("username", jsonObject.getRealname());
                json.put("id", jsonObject.getId());
                json.put("avatar", "http://tp1.sinaimg.cn/1571889140/180/40030060651/1");
                json.put("sign", jsonObject.getUsername());
                list.add(json);
            }
        }

        JSONObject jsonObject1 = new JSONObject();
        JSONObject data1 = new JSONObject();
        data1.put("list", list);
        jsonObject1.put("data", data1);
        jsonObject1.put("members", Integer.valueOf(list.size() + 1));
        jsonObject1.put("code", "0");
        jsonObject1.put("msg", "");
        return jsonObject1;
    }

    @ResponseBody
    @RequestMapping(
            params = {"getUsers"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public JSONObject getUsers(HttpServletResponse response, HttpServletRequest request) throws Exception {
        LoginUser u = ContextHolderUtils.getLoginSessionUser();
        JSONObject jsonObject = new JSONObject();
        JSONArray groups = new JSONArray();
        JSONObject group = new JSONObject();
        List imTSUsers = this.imService.getUsers();
        P3ImTSBaseUser imTSBaseUser = new P3ImTSBaseUser();

        for(int jsonObjectData = 0; jsonObjectData < imTSUsers.size(); ++jsonObjectData) {
            if(((P3ImTSBaseUser)imTSUsers.get(jsonObjectData)).getId().equals(u.getId())) {
                imTSBaseUser = (P3ImTSBaseUser)imTSUsers.get(jsonObjectData);
                imTSUsers.remove(jsonObjectData);
            }
        }

        jsonObject.put("code", "0");
        jsonObject.put("msg", "");
        JSONObject var21 = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("username", u.getRealName());
        jsonObject1.put("id", u.getId());
        jsonObject1.put("status", "online");
        jsonObject1.put("sign", StringUtils.isEmpty(imTSBaseUser.getImsign())?"这家伙很懒没有签名":imTSBaseUser.getImsign());
        String basePath = request.getContextPath();
        jsonObject1.put("avatar", StringUtils.isEmpty(imTSBaseUser.getPortrait())?"content/chat/layui/images/portrait/qq.jpg":basePath + "/chat/imController/showOrDownByurl.do?dbPath=" + imTSBaseUser.getPortrait());
        var21.put("mine", jsonObject1);
        JSONArray jsonArray1 = new JSONArray();
        List departs = this.imService.getDeparts();
        Iterator var15 = departs.iterator();

        while(var15.hasNext()) {
            P3ImTSDepart depart = (P3ImTSDepart)var15.next();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("groupname", depart.getDepartname());
            jsonObject2.put("id", depart.getId());
            JSONArray jsonArray = new JSONArray();
            if(this.imService.isInThisOrg(u.getDepartid(), depart.getOrgCode())) {
                group.put("groupname", depart.getDepartname());
                group.put("id", depart.getId());
                group.put("avatar", "content/chat/layui/images/portrait/group.jpg");
                groups.add(group);
            }

            Iterator var19 = imTSUsers.iterator();

            while(var19.hasNext()) {
                P3ImTSBaseUser tsBaseUser = (P3ImTSBaseUser)var19.next();
                if(tsBaseUser.getMaindepartid().equals(depart.getOrgCode())) {
                    JSONObject json = new JSONObject();
                    json.put("username", tsBaseUser.getRealname());
                    json.put("id", tsBaseUser.getId());
                    json.put("avatar", StringUtils.isEmpty(tsBaseUser.getPortrait())?"content/chat/layui/images/portrait/qq.jpg":basePath + "/chat/imController/showOrDownByurl.do?dbPath=" + tsBaseUser.getPortrait());
                    json.put("sign", StringUtils.isEmpty(tsBaseUser.getImsign())?"这家伙很懒没有签名":tsBaseUser.getImsign());
                    jsonArray.add(json);
                }
            }

            jsonObject2.put("list", jsonArray);
            jsonArray1.add(jsonObject2);
        }

        var21.put("friend", jsonArray1);
        var21.put("group", groups);
        jsonObject.put("data", var21);
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(
            params = {"getUserid"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String getUserid() {
        LoginUser u = ContextHolderUtils.getLoginSessionUser();
        System.out.println("当前登录人id为：" + u.getId());
        return u.getId();
    }

    @RequestMapping(
            params = {"uploadImage"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public JSONObject uploadImage(MultipartHttpServletRequest request) {
        JSONObject resultJson = new JSONObject();

        try {
            MultipartFile e = request.getFile("file");
            byte[] bytes = e.getBytes();
            String realFilename = e.getOriginalFilename();
            String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
            String filename = System.currentTimeMillis() + fileExtension;
            StringBuffer path = new StringBuffer();
            path.append(this.localPath);
            path.append(File.separator);
            StringBuffer dbPath = new StringBuffer();
            dbPath.append("chat");
            dbPath.append(File.separator);
            dbPath.append("uploadImage");
            dbPath.append((new SimpleDateFormat(File.separator + "YYYY" + File.separator + "MM" + File.separator + "dd")).format(new Date()));
            path.append(dbPath.toString());
            String uploadDir = path.toString();
            File dirPath = new File(uploadDir);
            if(!dirPath.exists()) {
                dirPath.mkdirs();
            }

            File uploadedFile = new File(uploadDir + File.separator + filename);
            FileCopyUtils.copy(bytes, uploadedFile);
            JSONObject jsonObject = new JSONObject();
            String basePath = request.getContextPath();
            jsonObject.put("src", basePath + "/chat/imController/showOrDownByurl.do?dbPath=" + dbPath.toString() + "/" + filename);
            this.log.info("上传路径为=" + jsonObject.get("src"));
            jsonObject.put("name", filename);
            resultJson.put("code", Integer.valueOf(0));
            resultJson.put("msg", "success");
            resultJson.put("data", jsonObject);
        } catch (Exception var15) {
            this.log.debug(var15.getMessage());
            resultJson.put("code", Integer.valueOf(1));
            resultJson.put("msg", "上传图片失败：" + var15.getMessage());
        }

        return resultJson;
    }

    @ResponseBody
    @RequestMapping(
            params = {"uploadFile"}
    )
    public JSONObject uploadFile(MultipartHttpServletRequest request) {
        JSONObject resultJson = new JSONObject();

        try {
            MultipartFile e = request.getFile("file");
            byte[] bytes = e.getBytes();
            String realFilename = e.getOriginalFilename();
            String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
            String filename = System.currentTimeMillis() + fileExtension;
            StringBuffer path = new StringBuffer();
            path.append(this.localPath);
            path.append(File.separator);
            StringBuffer dbPath = new StringBuffer();
            dbPath.append("chat");
            dbPath.append(File.separator);
            dbPath.append("uploadFile");
            dbPath.append((new SimpleDateFormat(File.separator + "YYYY" + File.separator + "MM" + File.separator + "dd")).format(new Date()));
            path.append(dbPath);
            String uploadDir = path.toString();
            File dirPath = new File(uploadDir);
            if(!dirPath.exists()) {
                dirPath.mkdirs();
            }

            File uploadedFile = new File(uploadDir + File.separator + filename);
            FileCopyUtils.copy(bytes, uploadedFile);
            (new StringBuilder(String.valueOf(ChatResourceConfig.getDomain()))).append("/").append(path.toString().replace(File.separator, "/")).append("/").append(filename).toString();
            JSONObject jsonObject = new JSONObject();
            String basePath = request.getContextPath();
            jsonObject.put("src", basePath + "/chat/imController/showOrDownByurl.do?down=1&dbPath=" + dbPath.toString() + "/" + filename);
            jsonObject.put("name", realFilename);
            resultJson.put("code", Integer.valueOf(0));
            resultJson.put("msg", "success");
            resultJson.put("data", jsonObject);
        } catch (Exception var16) {
            this.log.debug(var16.getMessage());
            resultJson.put("code", Integer.valueOf(1));
            resultJson.put("msg", "上传文件失败：" + var16.getMessage());
        }

        return resultJson;
    }

    @RequestMapping(
            value = {"showOrDownByurl"},
            method = {RequestMethod.GET}
    )
    public void getImgByurl(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String flag = request.getParameter("down");
        String dbpath = request.getParameter("dbPath");
        if("1".equals(flag)) {
            response.setContentType("application/x-msdownload;charset=utf-8");
            String inputStream = dbpath.substring(dbpath.lastIndexOf(File.separator) + 1);
            response.setHeader("Content-disposition", "attachment; filename=" + new String(inputStream.getBytes("utf-8"), "ISO8859-1"));
        } else {
            response.setContentType("image/jpeg;charset=utf-8");
        }

        BufferedInputStream inputStream1 = null;
        ServletOutputStream outputStream = null;

        try {
            String e = this.localPath + File.separator + dbpath;
            inputStream1 = new BufferedInputStream(new FileInputStream(e));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];

            int len;
            while((len = inputStream1.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }

            response.flushBuffer();
        } catch (Exception var13) {
            this.log.info("--通过流的方式获取文件异常--" + var13.getMessage());
        } finally {
            if(inputStream1 != null) {
                inputStream1.close();
            }

            if(outputStream != null) {
                outputStream.close();
            }

        }

    }

    @ResponseBody
    @RequestMapping(
            params = {"changeSign"}
    )
    public String changeSign(String sign) {
        LoginUser u = ContextHolderUtils.getLoginSessionUser();
        this.imService.updateSign(sign, u.getId());
        return "success";
    }
}
