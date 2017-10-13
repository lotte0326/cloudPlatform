//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XMLTokener;

public class XML {
    public static final Character AMP = new Character('&');
    public static final Character APOS = new Character('\'');
    public static final Character BANG = new Character('!');
    public static final Character EQ = new Character('=');
    public static final Character GT = new Character('>');
    public static final Character LT = new Character('<');
    public static final Character QUEST = new Character('?');
    public static final Character QUOT = new Character('\"');
    public static final Character SLASH = new Character('/');

    public XML() {
    }

    public static String escape(String string) {
        StringBuffer sb = new StringBuffer();
        int i = 0;

        for(int length = string.length(); i < length; ++i) {
            char c = string.charAt(i);
            switch(c) {
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void noSpace(String string) throws JSONException {
        int length = string.length();
        if(length == 0) {
            throw new JSONException("Empty string.");
        } else {
            for(int i = 0; i < length; ++i) {
                if(Character.isWhitespace(string.charAt(i))) {
                    throw new JSONException("\'" + string + "\' contains a space character.");
                }
            }

        }
    }

    private static boolean parse(XMLTokener x, JSONObject context, String name) throws JSONException {
        JSONObject jsonobject = null;
        Object token = x.nextToken();
        String string;
        if(token == BANG) {
            char c = x.next();
            if(c == 45) {
                if(x.next() == 45) {
                    x.skipPast("-->");
                    return false;
                }

                x.back();
            } else if(c == 91) {
                token = x.nextToken();
                if("CDATA".equals(token) && x.next() == 91) {
                    string = x.nextCDATA();
                    if(string.length() > 0) {
                        context.accumulate("content", string);
                    }

                    return false;
                }

                throw x.syntaxError("Expected \'CDATA[\'");
            }

            int i = 1;

            do {
                token = x.nextMeta();
                if(token == null) {
                    throw x.syntaxError("Missing \'>\' after \'<!\'.");
                }

                if(token == LT) {
                    ++i;
                } else if(token == GT) {
                    --i;
                }
            } while(i > 0);

            return false;
        } else if(token == QUEST) {
            x.skipPast("?>");
            return false;
        } else if(token == SLASH) {
            token = x.nextToken();
            if(name == null) {
                throw x.syntaxError("Mismatched close tag " + token);
            } else if(!token.equals(name)) {
                throw x.syntaxError("Mismatched " + name + " and " + token);
            } else if(x.nextToken() != GT) {
                throw x.syntaxError("Misshaped close tag");
            } else {
                return true;
            }
        } else if(token instanceof Character) {
            throw x.syntaxError("Misshaped tag");
        } else {
            String tagName = (String)token;
            token = null;
            jsonobject = new JSONObject();

            while(true) {
                if(token == null) {
                    token = x.nextToken();
                }

                if(!(token instanceof String)) {
                    if(token == SLASH) {
                        if(x.nextToken() != GT) {
                            throw x.syntaxError("Misshaped tag");
                        }

                        if(jsonobject.length() > 0) {
                            context.accumulate(tagName, jsonobject);
                        } else {
                            context.accumulate(tagName, "");
                        }

                        return false;
                    }

                    if(token != GT) {
                        throw x.syntaxError("Misshaped tag");
                    }

                    while(true) {
                        token = x.nextContent();
                        if(token == null) {
                            if(tagName != null) {
                                throw x.syntaxError("Unclosed tag " + tagName);
                            }

                            return false;
                        }

                        if(token instanceof String) {
                            string = (String)token;
                            if(string.length() > 0) {
                                jsonobject.accumulate("content", stringToValue(string));
                            }
                        } else if(token == LT && parse(x, jsonobject, tagName)) {
                            if(jsonobject.length() == 0) {
                                context.accumulate(tagName, "");
                            } else if(jsonobject.length() == 1 && jsonobject.opt("content") != null) {
                                context.accumulate(tagName, jsonobject.opt("content"));
                            } else {
                                context.accumulate(tagName, jsonobject);
                            }

                            return false;
                        }
                    }
                }

                string = (String)token;
                token = x.nextToken();
                if(token == EQ) {
                    token = x.nextToken();
                    if(!(token instanceof String)) {
                        throw x.syntaxError("Missing value");
                    }

                    jsonobject.accumulate(string, stringToValue((String)token));
                    token = null;
                } else {
                    jsonobject.accumulate(string, "");
                }
            }
        }
    }

    public static Object stringToValue(String string) {
        if("true".equalsIgnoreCase(string)) {
            return Boolean.TRUE;
        } else if("false".equalsIgnoreCase(string)) {
            return Boolean.FALSE;
        } else if("null".equalsIgnoreCase(string)) {
            return JSONObject.NULL;
        } else {
            try {
                char ignore = string.charAt(0);
                if(ignore == 45 || ignore >= 48 && ignore <= 57) {
                    Long value1 = new Long(string);
                    if(value1.toString().equals(string)) {
                        return value1;
                    }
                }
            } catch (Exception var4) {
                try {
                    Double value = new Double(string);
                    if(value.toString().equals(string)) {
                        return value;
                    }
                } catch (Exception var3) {
                    ;
                }
            }

            return string;
        }
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        XMLTokener x = new XMLTokener(string);

        while(x.more() && x.skipPast("<")) {
            parse(x, jo, (String)null);
        }

        return jo;
    }

    public static String toString(Object object) throws JSONException {
        return toString(object, (String)null);
    }

    public static String toString(Object object, String tagName) throws JSONException {
        StringBuffer sb = new StringBuffer();
        int i;
        JSONArray ja;
        int length;
        String string;
        if(!(object instanceof JSONObject)) {
            if(object.getClass().isArray()) {
                object = new JSONArray(object);
            }

            if(object instanceof JSONArray) {
                ja = (JSONArray)object;
                length = ja.length();

                for(i = 0; i < length; ++i) {
                    sb.append(toString(ja.opt(i), tagName == null?"array":tagName));
                }

                return sb.toString();
            } else {
                string = object == null?"null":escape(object.toString());
                return tagName == null?"\"" + string + "\"":(string.length() == 0?"<" + tagName + "/>":"<" + tagName + ">" + string + "</" + tagName + ">");
            }
        } else {
            if(tagName != null) {
                sb.append('<');
                sb.append(tagName);
                sb.append('>');
            }

            JSONObject jo = (JSONObject)object;
            Iterator keys = jo.keys();

            while(true) {
                while(true) {
                    while(keys.hasNext()) {
                        String key = keys.next().toString();
                        Object value = jo.opt(key);
                        if(value == null) {
                            value = "";
                        }

                        if(value instanceof String) {
                            string = (String)value;
                        } else {
                            string = null;
                        }

                        if("content".equals(key)) {
                            if(value instanceof JSONArray) {
                                ja = (JSONArray)value;
                                length = ja.length();

                                for(i = 0; i < length; ++i) {
                                    if(i > 0) {
                                        sb.append('\n');
                                    }

                                    sb.append(escape(ja.get(i).toString()));
                                }
                            } else {
                                sb.append(escape(value.toString()));
                            }
                        } else if(value instanceof JSONArray) {
                            ja = (JSONArray)value;
                            length = ja.length();

                            for(i = 0; i < length; ++i) {
                                value = ja.get(i);
                                if(value instanceof JSONArray) {
                                    sb.append('<');
                                    sb.append(key);
                                    sb.append('>');
                                    sb.append(toString(value));
                                    sb.append("</");
                                    sb.append(key);
                                    sb.append('>');
                                } else {
                                    sb.append(toString(value, key));
                                }
                            }
                        } else if("".equals(value)) {
                            sb.append('<');
                            sb.append(key);
                            sb.append("/>");
                        } else {
                            sb.append(toString(value, key));
                        }
                    }

                    if(tagName != null) {
                        sb.append("</");
                        sb.append(tagName);
                        sb.append('>');
                    }

                    return sb.toString();
                }
            }
        }
    }
}
