//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import java.io.IOException;
import java.io.Writer;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWriter {
    private static final int maxdepth = 200;
    private boolean comma = false;
    protected char mode = 105;
    private final JSONObject[] stack = new JSONObject[200];
    private int top = 0;
    protected Writer writer;

    public JSONWriter(Writer w) {
        this.writer = w;
    }

    private JSONWriter append(String string) throws JSONException {
        if(string == null) {
            throw new JSONException("Null pointer");
        } else if(this.mode != 111 && this.mode != 97) {
            throw new JSONException("Value out of sequence.");
        } else {
            try {
                if(this.comma && this.mode == 97) {
                    this.writer.write(44);
                }

                this.writer.write(string);
            } catch (IOException var3) {
                throw new JSONException(var3);
            }

            if(this.mode == 111) {
                this.mode = 107;
            }

            this.comma = true;
            return this;
        }
    }

    public JSONWriter array() throws JSONException {
        if(this.mode != 105 && this.mode != 111 && this.mode != 97) {
            throw new JSONException("Misplaced array.");
        } else {
            this.push((JSONObject)null);
            this.append("[");
            this.comma = false;
            return this;
        }
    }

    private JSONWriter end(char mode, char c) throws JSONException {
        if(this.mode != mode) {
            throw new JSONException(mode == 97?"Misplaced endArray.":"Misplaced endObject.");
        } else {
            this.pop(mode);

            try {
                this.writer.write(c);
            } catch (IOException var4) {
                throw new JSONException(var4);
            }

            this.comma = true;
            return this;
        }
    }

    public JSONWriter endArray() throws JSONException {
        return this.end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return this.end('k', '}');
    }

    public JSONWriter key(String string) throws JSONException {
        if(string == null) {
            throw new JSONException("Null key.");
        } else if(this.mode == 107) {
            try {
                this.stack[this.top - 1].putOnce(string, Boolean.TRUE);
                if(this.comma) {
                    this.writer.write(44);
                }

                this.writer.write(JSONObject.quote(string));
                this.writer.write(58);
                this.comma = false;
                this.mode = 111;
                return this;
            } catch (IOException var3) {
                throw new JSONException(var3);
            }
        } else {
            throw new JSONException("Misplaced key.");
        }
    }

    public JSONWriter object() throws JSONException {
        if(this.mode == 105) {
            this.mode = 111;
        }

        if(this.mode != 111 && this.mode != 97) {
            throw new JSONException("Misplaced object.");
        } else {
            this.append("{");
            this.push(new JSONObject());
            this.comma = false;
            return this;
        }
    }

    private void pop(char c) throws JSONException {
        if(this.top <= 0) {
            throw new JSONException("Nesting error.");
        } else {
            int m = this.stack[this.top - 1] == null?97:107;
            if(m != c) {
                throw new JSONException("Nesting error.");
            } else {
                --this.top;
                this.mode = (char)(this.top == 0?100:(this.stack[this.top - 1] == null?97:107));
            }
        }
    }

    private void push(JSONObject jo) throws JSONException {
        if(this.top >= 200) {
            throw new JSONException("Nesting too deep.");
        } else {
            this.stack[this.top] = jo;
            this.mode = (char)(jo == null?97:107);
            ++this.top;
        }
    }

    public JSONWriter value(boolean b) throws JSONException {
        return this.append(b?"true":"false");
    }

    public JSONWriter value(double d) throws JSONException {
        return this.value(new Double(d));
    }

    public JSONWriter value(long l) throws JSONException {
        return this.append(Long.toString(l));
    }

    public JSONWriter value(Object object) throws JSONException {
        return this.append(JSONObject.valueToString(object));
    }
}
