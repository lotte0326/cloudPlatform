//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.util;

import java.util.Map;
import org.jeecgframework.poi.exception.excel.ExcelExportException;
import org.jeecgframework.poi.util.PoiFunctionUtil;
import org.jeecgframework.poi.util.PoiPublicUtil;

public final class PoiElUtil {
    public static final String LENGTH = "le:";
    public static final String FOREACH = "fe:";
    public static final String FOREACH_NOT_CREATE = "!fe:";
    public static final String FOREACH_AND_SHIFT = "$fe:";
    public static final String START_STR = "{{";
    public static final String END_STR = "}}";
    public static final String NUMBER_SYMBOL = "n:";
    public static final String FORMAT_DATE = "fd:";
    public static final String FORMAT_NUMBER = "fn:";
    public static final String IF_DELETE = "!if:";
    public static final String EMPTY = "";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";

    private PoiElUtil() {
    }

    public static Object eval(String text, Map<String, Object> map) throws Exception {
        String tempText = new String(text);
        Object obj = innerEval(text, map);
        return tempText.equals(obj.toString()) && map.containsKey(tempText.split("\\.")[0])?PoiPublicUtil.getParamsValue(tempText, map):obj;
    }

    public static Object innerEval(String text, Map<String, Object> map) throws Exception {
        return text.indexOf("?") != -1 && text.indexOf(":") != -1?trinocular(text, map):(text.indexOf("le:") != -1?length(text, map):(text.indexOf("fd:") != -1?formatDate(text, map):(text.indexOf("fn:") != -1?formatNumber(text, map):(text.indexOf("!if:") != -1?ifDelete(text, map):(text.startsWith("\'")?text.replace("\'", ""):text)))));
    }

    private static Object ifDelete(String text, Map<String, Object> map) throws Exception {
        text = text.replaceAll("\\s{1,}", " ").trim();
        String[] keys = getKey("!if:", text).split(" ");
        text = text.replace("!if:", "");
        return isTrue(keys, map);
    }

    private static Boolean isTrue(String[] keys, Map<String, Object> map) throws Exception {
        Object first;
        if(keys.length == 0) {
            first = null;
            String first1;
            return (first1 = isConstant(keys[0])) != null?Boolean.valueOf(first1):Boolean.valueOf(PoiPublicUtil.getParamsValue(keys[0], map).toString());
        } else if(keys.length == 3) {
            first = eval(keys[0], map);
            Object second = eval(keys[2], map);
            return Boolean.valueOf(PoiFunctionUtil.isTrue(first, keys[1], second));
        } else {
            throw new ExcelExportException("判断参数不对");
        }
    }

    private static String isConstant(String param) {
        return param.indexOf("\'") != -1?param.replace("\'", ""):null;
    }

    private static Object formatNumber(String text, Map<String, Object> map) throws Exception {
        String[] key = getKey("fn:", text).split(";");
        text = text.replace("fn:", "");
        return innerEval(replacinnerEvalue(text, PoiFunctionUtil.formatNumber(PoiPublicUtil.getParamsValue(key[0], map), key[1])), map);
    }

    private static Object formatDate(String text, Map<String, Object> map) throws Exception {
        String[] key = getKey("fd:", text).split(";");
        text = text.replace("fd:", "");
        return innerEval(replacinnerEvalue(text, PoiFunctionUtil.formatDate(PoiPublicUtil.getParamsValue(key[0], map), key[1])), map);
    }

    private static Object length(String text, Map<String, Object> map) throws Exception {
        String key = getKey("le:", text);
        text = text.replace("le:", "");
        Object val = PoiPublicUtil.getParamsValue(key, map);
        return innerEval(replacinnerEvalue(text, Integer.valueOf(PoiFunctionUtil.length(val))), map);
    }

    private static String replacinnerEvalue(String text, Object val) {
        StringBuilder sb = new StringBuilder();
        sb.append(text.substring(0, text.indexOf("(")));
        sb.append(" ");
        sb.append(val);
        sb.append(" ");
        sb.append(text.substring(text.indexOf(")") + 1, text.length()));
        return sb.toString().trim();
    }

    private static String getKey(String prefix, String text) {
        int leftBracket = 1;
        int rigthBracket = 0;
        int position = 0;

        for(int index = text.indexOf(prefix) + prefix.length(); text.charAt(index) == " ".charAt(0); text = text.substring(0, index) + text.substring(index + 1, text.length())) {
            ;
        }

        for(int i = text.indexOf(prefix + "(") + prefix.length() + 1; i < text.length(); ++i) {
            if(text.charAt(i) == "(".charAt(0)) {
                ++leftBracket;
            }

            if(text.charAt(i) == ")".charAt(0)) {
                ++rigthBracket;
            }

            if(leftBracket == rigthBracket) {
                position = i;
                break;
            }
        }

        return text.substring(text.indexOf(prefix + "(") + 1 + prefix.length(), position).trim();
    }

    public static void main(String[] args) {
        System.out.println(getKey("!if:", "测试 !if: (小明)"));
    }

    private static Object trinocular(String text, Map<String, Object> map) throws Exception {
        text = text.replaceAll("\\s{1,}", " ").trim();
        String testText = text.substring(0, text.indexOf("?"));
        text = text.substring(text.indexOf("?") + 1, text.length()).trim();
        text = innerEval(text, map).toString();
        String[] keys = text.split(":");
        Object first = eval(keys[0].trim(), map);
        Object second = eval(keys[1].trim(), map);
        return isTrue(testText.split(" "), map).booleanValue()?first:second;
    }
}
