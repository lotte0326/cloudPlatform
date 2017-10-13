//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.utils.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    public static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    public HttpUtil() {
    }

    public static HttpUtil.Response sendPostRequest(String url, String charset, Map<String, String> params) throws Exception {
        URI uri = URI.create(url);
        HttpPost request = new HttpPost(uri);
        HttpClient httpClient = getHttpClientByProtocal(uri);
        ArrayList nameValuePairs = new ArrayList();
        Iterator it = params.entrySet().iterator();

        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            nameValuePairs.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
        }

        request.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
        return retrieveResponse(httpClient, request);
    }

    public static HttpUtil.Response sendGetRequest(String url, String charset, Map<String, String> params) throws Exception {
        URI uri = URI.create(url);
        HttpGet request = new HttpGet(uri);
        HttpClient httpClient = getHttpClientByProtocal(uri);
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        Iterator var8 = params.entrySet().iterator();

        while(var8.hasNext()) {
            Entry entry = (Entry)var8.next();
            basicHttpParams.setParameter((String)entry.getKey(), entry.getValue());
        }

        request.setParams(basicHttpParams);
        return retrieveResponse(httpClient, request);
    }

    private static HttpUtil.Response retrieveResponse(HttpClient httpClient, HttpUriRequest request) throws Exception {
        HttpResponse httpResponse = httpClient.execute(request);
        HttpUtil.Response response = new HttpUtil.Response();
        response.code = httpResponse.getStatusLine().getStatusCode();
        BufferedReader reader = null;
        StringBuffer rc = new StringBuffer();

        try {
            InputStream is = httpResponse.getEntity().getContent();
            reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while((line = reader.readLine()) != null) {
                rc.append(line);
            }
        } finally {
            if(reader != null) {
                reader.close();
            }

        }

        response.content = rc.toString();
        return response;
    }

    private static HttpClient getHttpClientByProtocal(URI uri) throws Exception {
        HttpClient httpClient = null;
        String scheme = uri.getScheme();
        if(!StringUtils.isEmpty(scheme) && !"http".equals(scheme.toLowerCase())) {
            httpClient = getHttpClient(true);
        } else {
            httpClient = getHttpClient(false);
        }

        return httpClient;
    }

    private static HttpClient getHttpClient(boolean enableSSL) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(3000));
        httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(3000));
        if(enableSSL) {
            SSLContext sslContext = null;

            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init((KeyManager[])null, new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }}, new SecureRandom());
            } catch (Exception var6) {
                throw var6;
            }

            SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager clientConnectionManager = httpClient.getConnectionManager();
            SchemeRegistry schemeRegistry = clientConnectionManager.getSchemeRegistry();
            schemeRegistry.register(new Scheme("https", 443, sslSocketFactory));
        }

        return httpClient;
    }

    public static HttpUtil.Response uploadResource(String url, File file, Map<String, String> params) throws Exception {
        StringBuffer queryString = new StringBuffer(512);
        Iterator uri = params.entrySet().iterator();

        while(uri.hasNext()) {
            Entry httpClient = (Entry)uri.next();
            queryString.append((String)httpClient.getKey());
            queryString.append("=");
            queryString.append((String)httpClient.getValue());
            queryString.append("&");
        }

        if(queryString.length() > 0) {
            queryString = queryString.deleteCharAt(queryString.length() - 1);
            url = url + "?" + queryString.toString();
        }

        URI uri1 = URI.create(url);
        HttpClient httpClient1 = getHttpClientByProtocal(uri1);
        HttpPost request = new HttpPost(uri1);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("file", new FileBody(file));
        request.setEntity(multipartEntity);
        return retrieveResponse(httpClient1, request);
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        if(ip == null) {
            ip = request.getRemoteAddr();
        }

        LOG.debug("x-real-ip = {}", new Object[]{ip});
        String[] stemps = ip.split(",");
        if(stemps != null && stemps.length >= 1) {
            ip = stemps[0];
        }

        LOG.debug("过滤反向代理ip = {}", new Object[]{ip});
        ip = ip.trim();
        if(ip.length() > 23) {
            ip = ip.substring(0, 23);
        }

        return ip;
    }

    public static class Response {
        public static final int STATUS_LINE_SUC = 200;
        public int code;
        public String content;

        public Response() {
        }

        public String toString() {
            return "Response [code=" + this.code + ", content=" + this.content + "]";
        }
    }
}
