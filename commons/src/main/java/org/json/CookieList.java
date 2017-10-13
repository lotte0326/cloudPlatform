//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import java.util.Iterator;
import org.json.Cookie;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CookieList {
    public CookieList() {
    }

    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject jo = new JSONObject();
        JSONTokener x = new JSONTokener(string);

        while(x.more()) {
            String name = Cookie.unescape(x.nextTo('='));
            x.next('=');
            jo.put(name, Cookie.unescape(x.nextTo(';')));
            x.next();
        }

        return jo;
    }

    public static String toString(JSONObject jo) throws JSONException {
        boolean b = false;
        Iterator keys = jo.keys();
        StringBuffer sb = new StringBuffer();

        while(keys.hasNext()) {
            String string = keys.next().toString();
            if(!jo.isNull(string)) {
                if(b) {
                    sb.append(';');
                }

                sb.append(Cookie.escape(string));
                sb.append("=");
                sb.append(Cookie.escape(jo.getString(string)));
                b = true;
            }
        }

        return sb.toString();
    }
}
