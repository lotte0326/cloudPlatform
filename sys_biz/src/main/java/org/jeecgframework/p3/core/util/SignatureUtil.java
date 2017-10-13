//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.jeecgframework.p3.core.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SignatureUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SignatureUtil.class);

    public SignatureUtil() {
    }

    public static String sign(Map<String, String> paramMap, String key) {
        if(key == null) {
            throw new IllegalArgumentException("key不能为空");
        } else {
            String sign = createSign(paramMap, key);
            return sign;
        }
    }

    private static String createSign(Map<String, String> paramMap, String key) {
        StringBuffer sb = new StringBuffer();
        TreeMap sort = new TreeMap(paramMap);
        Set es = sort.entrySet();
        Iterator it = es.iterator();

        while(it.hasNext()) {
            Entry sign = (Entry)it.next();
            String k = (String)sign.getKey();
            String v = (String)sign.getValue();
            if(v != null && !"".equals(v) && !"null".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + key);
        LOG.info("HMAC source:{}", new Object[]{sb.toString()});
        String sign1 = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        LOG.info("HMAC:{}", new Object[]{sign1});
        return sign1;
    }

    public static boolean checkSign(Map<String, String> paramMap, String key, String sign) {
        if(key == null) {
            throw new IllegalArgumentException("key不能为空");
        } else if(sign == null) {
            throw new IllegalArgumentException("需要验签的字符为空");
        } else {
            LOG.info("HMAC sign:{}", new Object[]{sign});
            return sign.equals(sign(paramMap, key));
        }
    }

    public static void main(String[] args) {
        HashMap paramMap = new HashMap();
        paramMap.put("openid", "o9SrbjpQbDU0lj0aSROQGgAS3Cwo");
        paramMap.put("actId", "402880ee513747290151378f1b5a0007");
        paramMap.put("jwid", "gh_f0f070498613");
        paramMap.put("subscribe", "1");
        paramMap.put("sign", "4872DEC7D77E5E9E627E4FCAF6DE95C1");
        String sign = (String)paramMap.get("sign");
        String key = "4B6CAED6F7B1912";
        String localsign = sign(paramMap, key);
        System.out.println(localsign);
        System.out.println(checkSign(paramMap, key, sign));
    }
}
