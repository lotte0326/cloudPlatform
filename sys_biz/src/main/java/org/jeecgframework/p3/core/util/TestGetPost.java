//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class TestGetPost {
    public TestGetPost() {
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;

        try {
            String e = url + "?" + param;
            URL realUrl = new URL(e);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.connect();
            Map map = conn.getHeaderFields();
            Iterator var9 = map.keySet().iterator();

            String line;
            while(var9.hasNext()) {
                line = (String)var9.next();
                System.out.println(line + "--->" + map.get(line));
            }

            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + "/n" + line) {
                ;
            }
        } catch (Exception var18) {
            System.out.println("发送GET请求出现异常！" + var18);
            var18.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

        return result;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL e = new URL(url);
            URLConnection conn = e.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            String line;
            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + "/n" + line) {
                ;
            }
        } catch (Exception var16) {
            System.out.println("发送POST请求出现异常！" + var16);
            var16.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }

                if(in != null) {
                    in.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }

    public static void main(String[] args) {
        String s = sendGet("http://localhost:8888/abc/login.jsp", (String)null);
        System.out.println(s);
        String s1 = sendPost("http://localhost:8888/abc/a.jsp", "user=李刚&pass=abc");
        System.out.println(s1);
    }
}
