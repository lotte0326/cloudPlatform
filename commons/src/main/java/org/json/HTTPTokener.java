//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import org.json.JSONException;
import org.json.JSONTokener;

public class HTTPTokener extends JSONTokener {
    public HTTPTokener(String string) {
        super(string);
    }

    public String nextToken() throws JSONException {
        StringBuffer sb = new StringBuffer();

        char c;
        do {
            c = this.next();
        } while(Character.isWhitespace(c));

        if(c != 34 && c != 39) {
            while(c != 0 && !Character.isWhitespace(c)) {
                sb.append(c);
                c = this.next();
            }

            return sb.toString();
        } else {
            char q = c;

            while(true) {
                c = this.next();
                if(c < 32) {
                    throw this.syntaxError("Unterminated string.");
                }

                if(c == q) {
                    return sb.toString();
                }

                sb.append(c);
            }
        }
    }
}
