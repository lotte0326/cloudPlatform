//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.common.utils;

import java.io.UnsupportedEncodingException;
import org.jeecgframework.p3.core.common.utils.StringUtil;

public class CommonUtil {
    public CommonUtil() {
    }

    public static String subString(String text, int length, String endWith) {
        if(StringUtil.isEmpty(text)) {
            return "";
        } else {
            int textLength = text.length();
            int byteLength = 0;
            StringBuffer returnStr = new StringBuffer();

            for(int e = 0; e < textLength && byteLength < length * 2; ++e) {
                String str_i = text.substring(e, e + 1);
                if(str_i.getBytes().length == 1) {
                    ++byteLength;
                } else {
                    byteLength += 2;
                }

                returnStr.append(str_i);
            }

            try {
                if(byteLength < text.getBytes("GBK").length) {
                    returnStr.append(endWith);
                }
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
            }

            return returnStr.toString();
        }
    }

    public static void main(String[] args) {
        String s1 = "QWERTYUIOPASDFGH";
        String s2 = "QWERTYU";
        String s3 = "实得分的师傅的师傅的大幅度的";
        String s4 = "实得分";
        String s5 = "实得w分d第d三方";
        String s6 = "实得w分d第三d方";
        String s7 = "";
        Object s8 = null;
        System.out.println("-----s1---->" + subString(s1, 6, "***"));
        System.out.println("-----s2---->" + subString(s2, 6, "***"));
        System.out.println("-----s3---->" + subString(s3, 6, "***"));
        System.out.println("-----s4---->" + subString(s4, 6, "***"));
        System.out.println("-----s5---->" + subString(s5, 6, "***"));
        System.out.println("-----s6---->" + subString(s6, 6, "***"));
        System.out.println("-----s7---->" + subString(s7, 6, "***"));
        System.out.println("-----s8---->" + subString((String)s8, 6, "***"));
    }
}
