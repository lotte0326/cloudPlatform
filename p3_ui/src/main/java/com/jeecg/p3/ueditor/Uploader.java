//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.p3.ueditor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class Uploader {
    private static final Logger logger = Logger.getLogger(Uploader.class);
    private static final int MAX_SIZE = 512000;
    private String url = "";
    private String fileName = "";
    private String state = "";
    private String type = "";
    private String originalName = "";
    private String size = "";
    private HttpServletRequest request = null;
    private String title = "";
    private String savePath = "upload";
    private String[] allowFiles = new String[]{".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp"};
    private long maxSize = 0L;
    private HashMap<String, String> errorInfo = new HashMap();
    private Map<String, String> params = null;
    private InputStream inputStream = null;
    public static final String ENCODEING = System.getProperties().getProperty("file.encoding");

    public Uploader(HttpServletRequest request) {
        this.request = request;
        this.params = new HashMap();
        this.setMaxSize(512000L);
        HashMap tmp = this.errorInfo;
        tmp.put("SUCCESS", "SUCCESS");
        tmp.put("NOFILE", "\\u672a\\u5305\\u542b\\u6587\\u4ef6\\u4e0a\\u4f20\\u57df");
        tmp.put("TYPE", "\\u4e0d\\u5141\\u8bb8\\u7684\\u6587\\u4ef6\\u683c\\u5f0f");
        tmp.put("SIZE", "\\u6587\\u4ef6\\u5927\\u5c0f\\u8d85\\u51fa\\u9650\\u5236");
        tmp.put("ENTYPE", "\\u8bf7\\u6c42\\u7c7b\\u578b\\u9519\\u8bef");
        tmp.put("REQUEST", "\\u4e0a\\u4f20\\u8bf7\\u6c42\\u5f02\\u5e38");
        tmp.put("FILE", "\\u672a\\u627e\\u5230\\u4e0a\\u4f20\\u6587\\u4ef6");
        tmp.put("IO", "IO\\u5f02\\u5e38");
        tmp.put("DIR", "\\u76ee\\u5f55\\u521b\\u5efa\\u5931\\u8d25");
        tmp.put("UNKNOWN", "\\u672a\\u77e5\\u9519\\u8bef");
        this.parseParams();
    }

    public void upload() throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
        if(!isMultipart) {
            this.state = (String)this.errorInfo.get("NOFILE");
        } else if(this.inputStream == null) {
            this.state = (String)this.errorInfo.get("FILE");
        } else {
            this.title = this.getParameter("pictitle");

            try {
                String e = this.getFolder(this.savePath);
                if(!this.checkFileType(this.originalName)) {
                    this.state = (String)this.errorInfo.get("TYPE");
                    return;
                }

                this.fileName = this.getName(this.originalName);
                this.type = this.getFileExt(this.fileName);
                this.url = e + "/" + this.fileName;
                FileOutputStream fos = new FileOutputStream(this.getPhysicalPath(this.url));
                BufferedInputStream bis = new BufferedInputStream(this.inputStream);
                byte[] buff = new byte[128];
                boolean count = true;

                int count1;
                while((count1 = bis.read(buff)) != -1) {
                    fos.write(buff, 0, count1);
                }

                bis.close();
                fos.close();
                this.state = (String)this.errorInfo.get("SUCCESS");
            } catch (Exception var7) {
                var7.printStackTrace();
                this.state = (String)this.errorInfo.get("IO");
            }

        }
    }

    public void uploadBase64(String fieldName) {
        String savePath = this.getFolder(this.savePath);
        String base64Data = this.request.getParameter(fieldName);
        this.fileName = this.getName("test.png");
        this.url = savePath + "/" + this.fileName;
        Base64 decoder = new Base64();

        try {
            File e = new File(this.getPhysicalPath(this.url));
            FileOutputStream ro = new FileOutputStream(e);
            byte[] b = decoder.encode(base64Data.getBytes());

            for(int i = 0; i < b.length; ++i) {
                if(b[i] < 0) {
                    b[i] = (byte)(b[i] + 256);
                }
            }

            ro.write(b);
            ro.flush();
            ro.close();
            this.state = (String)this.errorInfo.get("SUCCESS");
        } catch (Exception var9) {
            this.state = (String)this.errorInfo.get("IO");
        }

    }

    public String getParameter(String name) {
        return (String)this.params.get(name);
    }

    private boolean checkFileType(String fileName) {
        Iterator type = Arrays.asList(this.allowFiles).iterator();

        while(type.hasNext()) {
            String ext = (String)type.next();
            if(fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }

        return false;
    }

    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private void parseParams() {
        DiskFileItemFactory dff = new DiskFileItemFactory();

        try {
            ServletFileUpload e = new ServletFileUpload(dff);
            e.setSizeMax(this.maxSize);
            e.setHeaderEncoding(ENCODEING);
            FileItemIterator fii = e.getItemIterator(this.request);

            while(fii.hasNext()) {
                FileItemStream item = fii.next();
                if(item.isFormField()) {
                    this.params.put(item.getFieldName(), this.getParameterValue(item.openStream()));
                } else if(this.inputStream == null) {
                    this.inputStream = item.openStream();
                    this.originalName = item.getName();
                    return;
                }
            }
        } catch (Exception var5) {
            this.state = (String)this.errorInfo.get("UNKNOWN");
        }

    }

    private String getName(String fileName) {
        Random random = new Random();
        return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
    }

    private String getFolder(String path) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path = path + "/" + formater.format(new Date());
        logger.info(" -------UE编辑器----- image path - " + path);
        File dir = new File(this.getPhysicalPath(path));
        if(!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception var5) {
                this.state = (String)this.errorInfo.get("DIR");
                return "";
            }
        }

        return path;
    }

    private String getPhysicalPath(String path) {
        String servletPath = this.request.getServletPath();
        String realPath = this.request.getSession().getServletContext().getRealPath(servletPath);
        String replace_url = "/content/plug-in/ueditor/jsp";
        replace_url = replace_url.replace("/", File.separator);
        String newUrl = (new File(realPath)).getParent().replace(replace_url, "") + "/" + path;
        return newUrl;
    }

    private String getParameterValue(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String result = "";
        String tmpString = null;

        try {
            while((tmpString = reader.readLine()) != null) {
                result = result + tmpString;
            }
        } catch (Exception var6) {
            ;
        }

        return result;
    }

    private byte[] getFileOutputStream(InputStream in) {
        try {
            return IOUtils.toByteArray(in);
        } catch (IOException var3) {
            return null;
        }
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setAllowFiles(String[] allowFiles) {
        this.allowFiles = allowFiles;
    }

    public void setMaxSize(long size) {
        this.maxSize = size * 1024L;
    }

    public String getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public String getOriginalName() {
        return this.originalName;
    }
}
