//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private String properiesName = "";

    public PropertiesUtil() {
    }

    public PropertiesUtil(String fileName) {
        this.properiesName = fileName;
    }

    public String readProperty(String key) {
        String value = "";
        InputStream is = null;

        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(this.properiesName);
            Properties e = new Properties();
            e.load(is);
            value = e.getProperty(key);
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return value;
    }

    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;

        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(this.properiesName);
            p.load(is);
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return p;
    }

    public void writeProperty(String key, String value) {
        FileInputStream is = null;
        FileOutputStream os = null;
        Properties p = new Properties();

        try {
            is = new FileInputStream(this.properiesName);
            p.load(is);
            os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(this.properiesName).getFile());
            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if(is != null) {
                    is.close();
                }

                if(os != null) {
                    os.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
        p.writeProperty("namess", "wang");
    }
}
