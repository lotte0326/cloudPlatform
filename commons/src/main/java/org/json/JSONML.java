//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLTokener;

public class JSONML {
    public JSONML() {
    }

    private static Object parse(XMLTokener x, boolean arrayForm, JSONArray ja) throws JSONException {
        String closeTag = null;
        JSONArray newja = null;
        JSONObject newjo = null;
        String tagName = null;

        while(true) {
            while(x.more()) {
                Object token = x.nextContent();
                if(token == XML.LT) {
                    token = x.nextToken();
                    if(token instanceof Character) {
                        if(token == XML.SLASH) {
                            token = x.nextToken();
                            if(!(token instanceof String)) {
                                throw new JSONException("Expected a closing name instead of \'" + token + "\'.");
                            }

                            if(x.nextToken() != XML.GT) {
                                throw x.syntaxError("Misshaped close tag");
                            }

                            return token;
                        }

                        if(token != XML.BANG) {
                            if(token != XML.QUEST) {
                                throw x.syntaxError("Misshaped tag");
                            }

                            x.skipPast("?>");
                        } else {
                            char c = x.next();
                            if(c == 45) {
                                if(x.next() == 45) {
                                    x.skipPast("-->");
                                } else {
                                    x.back();
                                }
                            } else if(c == 91) {
                                token = x.nextToken();
                                if(!token.equals("CDATA") || x.next() != 91) {
                                    throw x.syntaxError("Expected \'CDATA[\'");
                                }

                                if(ja != null) {
                                    ja.put(x.nextCDATA());
                                }
                            } else {
                                int i = 1;

                                while(true) {
                                    token = x.nextMeta();
                                    if(token == null) {
                                        throw x.syntaxError("Missing \'>\' after \'<!\'.");
                                    }

                                    if(token == XML.LT) {
                                        ++i;
                                    } else if(token == XML.GT) {
                                        --i;
                                    }

                                    if(i <= 0) {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if(!(token instanceof String)) {
                            throw x.syntaxError("Bad tagName \'" + token + "\'.");
                        }

                        tagName = (String)token;
                        newja = new JSONArray();
                        newjo = new JSONObject();
                        if(arrayForm) {
                            newja.put(tagName);
                            if(ja != null) {
                                ja.put(newja);
                            }
                        } else {
                            newjo.put("tagName", tagName);
                            if(ja != null) {
                                ja.put(newjo);
                            }
                        }

                        token = null;

                        while(true) {
                            if(token == null) {
                                token = x.nextToken();
                            }

                            if(token == null) {
                                throw x.syntaxError("Misshaped tag");
                            }

                            if(!(token instanceof String)) {
                                if(arrayForm && newjo.length() > 0) {
                                    newja.put(newjo);
                                }

                                if(token == XML.SLASH) {
                                    if(x.nextToken() != XML.GT) {
                                        throw x.syntaxError("Misshaped tag");
                                    }

                                    if(ja == null) {
                                        if(arrayForm) {
                                            return newja;
                                        }

                                        return newjo;
                                    }
                                } else {
                                    if(token != XML.GT) {
                                        throw x.syntaxError("Misshaped tag");
                                    }

                                    closeTag = (String)parse(x, arrayForm, newja);
                                    if(closeTag != null) {
                                        if(!closeTag.equals(tagName)) {
                                            throw x.syntaxError("Mismatched \'" + tagName + "\' and \'" + closeTag + "\'");
                                        }

                                        tagName = null;
                                        if(!arrayForm && newja.length() > 0) {
                                            newjo.put("childNodes", newja);
                                        }

                                        if(ja == null) {
                                            if(arrayForm) {
                                                return newja;
                                            }

                                            return newjo;
                                        }
                                    }
                                }
                                break;
                            }

                            String attribute = (String)token;
                            if(!arrayForm && ("tagName".equals(attribute) || "childNode".equals(attribute))) {
                                throw x.syntaxError("Reserved attribute.");
                            }

                            token = x.nextToken();
                            if(token == XML.EQ) {
                                token = x.nextToken();
                                if(!(token instanceof String)) {
                                    throw x.syntaxError("Missing value");
                                }

                                newjo.accumulate(attribute, XML.stringToValue((String)token));
                                token = null;
                            } else {
                                newjo.accumulate(attribute, "");
                            }
                        }
                    }
                } else if(ja != null) {
                    ja.put(token instanceof String?XML.stringToValue((String)token):token);
                }
            }

            throw x.syntaxError("Bad XML");
        }
    }

    public static JSONArray toJSONArray(String string) throws JSONException {
        return toJSONArray(new XMLTokener(string));
    }

    public static JSONArray toJSONArray(XMLTokener x) throws JSONException {
        return (JSONArray)parse(x, true, (JSONArray)null);
    }

    public static JSONObject toJSONObject(XMLTokener x) throws JSONException {
        return (JSONObject)parse(x, false, (JSONArray)null);
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        return toJSONObject(new XMLTokener(string));
    }

    public static String toString(JSONArray ja) throws JSONException {
        StringBuffer sb = new StringBuffer();
        String tagName = ja.getString(0);
        XML.noSpace(tagName);
        tagName = XML.escape(tagName);
        sb.append('<');
        sb.append(tagName);
        Object object = ja.opt(1);
        int i;
        if(object instanceof JSONObject) {
            i = 2;
            JSONObject jo = (JSONObject)object;
            Iterator keys = jo.keys();

            while(keys.hasNext()) {
                String key = keys.next().toString();
                XML.noSpace(key);
                String value = jo.optString(key);
                if(value != null) {
                    sb.append(' ');
                    sb.append(XML.escape(key));
                    sb.append('=');
                    sb.append('\"');
                    sb.append(XML.escape(value));
                    sb.append('\"');
                }
            }
        } else {
            i = 1;
        }

        int length = ja.length();
        if(i >= length) {
            sb.append('/');
            sb.append('>');
        } else {
            sb.append('>');

            do {
                object = ja.get(i);
                ++i;
                if(object != null) {
                    if(object instanceof String) {
                        sb.append(XML.escape(object.toString()));
                    } else if(object instanceof JSONObject) {
                        sb.append(toString((JSONObject)object));
                    } else if(object instanceof JSONArray) {
                        sb.append(toString((JSONArray)object));
                    }
                }
            } while(i < length);

            sb.append('<');
            sb.append('/');
            sb.append(tagName);
            sb.append('>');
        }

        return sb.toString();
    }

    public static String toString(JSONObject jo) throws JSONException {
        StringBuffer sb = new StringBuffer();
        String tagName = jo.optString("tagName");
        if(tagName == null) {
            return XML.escape(jo.toString());
        } else {
            XML.noSpace(tagName);
            tagName = XML.escape(tagName);
            sb.append('<');
            sb.append(tagName);
            Iterator keys = jo.keys();

            while(keys.hasNext()) {
                String key = keys.next().toString();
                if(!"tagName".equals(key) && !"childNodes".equals(key)) {
                    XML.noSpace(key);
                    String value = jo.optString(key);
                    if(value != null) {
                        sb.append(' ');
                        sb.append(XML.escape(key));
                        sb.append('=');
                        sb.append('\"');
                        sb.append(XML.escape(value));
                        sb.append('\"');
                    }
                }
            }

            JSONArray ja = jo.optJSONArray("childNodes");
            if(ja == null) {
                sb.append('/');
                sb.append('>');
            } else {
                sb.append('>');
                int length = ja.length();

                for(int i = 0; i < length; ++i) {
                    Object object = ja.get(i);
                    if(object != null) {
                        if(object instanceof String) {
                            sb.append(XML.escape(object.toString()));
                        } else if(object instanceof JSONObject) {
                            sb.append(toString((JSONObject)object));
                        } else if(object instanceof JSONArray) {
                            sb.append(toString((JSONArray)object));
                        } else {
                            sb.append(object.toString());
                        }
                    }
                }

                sb.append('<');
                sb.append('/');
                sb.append(tagName);
                sb.append('>');
            }

            return sb.toString();
        }
    }
}
