//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.json;

import java.util.Arrays;
import org.json.JSONException;

public class Kim {
    private byte[] bytes;
    private int hashcode;
    public int length;
    private String string;

    public Kim(byte[] bytes, int from, int thru) {
        this.bytes = null;
        this.hashcode = 0;
        this.length = 0;
        this.string = null;
        int sum = 1;
        this.hashcode = 0;
        this.length = thru - from;
        if(this.length > 0) {
            this.bytes = new byte[this.length];

            for(int at = 0; at < this.length; ++at) {
                int value = bytes[at + from] & 255;
                sum += value;
                this.hashcode += sum;
                this.bytes[at] = (byte)value;
            }

            this.hashcode += sum << 16;
        }

    }

    public Kim(byte[] bytes, int length) {
        this((byte[])bytes, 0, length);
    }

    public Kim(Kim kim, int from, int thru) {
        this(kim.bytes, from, thru);
    }

    public Kim(String string) throws JSONException {
        this.bytes = null;
        this.hashcode = 0;
        this.length = 0;
        this.string = null;
        int stringLength = string.length();
        this.hashcode = 0;
        this.length = 0;
        if(stringLength > 0) {
            int at = 0;

            while(true) {
                if(at >= stringLength) {
                    this.bytes = new byte[this.length];
                    at = 0;
                    int var9 = 1;

                    for(int i = 0; i < stringLength; ++i) {
                        int character = string.charAt(i);
                        if(character <= 127) {
                            this.bytes[at] = (byte)character;
                            var9 += character;
                            this.hashcode += var9;
                            ++at;
                        } else {
                            int var8;
                            if(character <= 16383) {
                                var8 = 128 | character >>> 7;
                                this.bytes[at] = (byte)var8;
                                var9 += var8;
                                this.hashcode += var9;
                                ++at;
                                var8 = character & 127;
                                this.bytes[at] = (byte)var8;
                                var9 += var8;
                                this.hashcode += var9;
                                ++at;
                            } else {
                                if(character >= '\ud800' && character <= '\udbff') {
                                    ++i;
                                    character = ((character & 1023) << 10 | string.charAt(i) & 1023) + 65536;
                                }

                                var8 = 128 | character >>> 14;
                                this.bytes[at] = (byte)var8;
                                var9 += var8;
                                this.hashcode += var9;
                                ++at;
                                var8 = 128 | character >>> 7 & 255;
                                this.bytes[at] = (byte)var8;
                                var9 += var8;
                                this.hashcode += var9;
                                ++at;
                                var8 = character & 127;
                                this.bytes[at] = (byte)var8;
                                var9 += var8;
                                this.hashcode += var9;
                                ++at;
                            }
                        }
                    }

                    this.hashcode += var9 << 16;
                    break;
                }

                char b = string.charAt(at);
                if(b <= 127) {
                    ++this.length;
                } else if(b <= 16383) {
                    this.length += 2;
                } else {
                    if(b >= '\ud800' && b <= '\udfff') {
                        ++at;
                        char sum = string.charAt(at);
                        if(b > '\udbff' || sum < '\udc00' || sum > '\udfff') {
                            throw new JSONException("Bad UTF16");
                        }
                    }

                    this.length += 3;
                }

                ++at;
            }
        }

    }

    public int characterAt(int at) throws JSONException {
        int c = this.get(at);
        if((c & 128) == 0) {
            return c;
        } else {
            int c1 = this.get(at + 1);
            int character;
            if((c1 & 128) == 0) {
                character = (c & 127) << 7 | c1;
                if(character > 127) {
                    return character;
                }
            } else {
                int c2 = this.get(at + 2);
                character = (c & 127) << 14 | (c1 & 127) << 7 | c2;
                if((c2 & 128) == 0 && character > 16383 && character <= 1114111 && (character < '\ud800' || character > '\udfff')) {
                    return character;
                }
            }

            throw new JSONException("Bad character at " + at);
        }
    }

    public static int characterSize(int character) throws JSONException {
        if(character >= 0 && character <= 1114111) {
            return character <= 127?1:(character <= 16383?2:3);
        } else {
            throw new JSONException("Bad character " + character);
        }
    }

    public int copy(byte[] bytes, int at) {
        System.arraycopy(this.bytes, 0, bytes, at, this.length);
        return at + this.length;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Kim)) {
            return false;
        } else {
            Kim that = (Kim)obj;
            return this == that?true:(this.hashcode != that.hashcode?false:Arrays.equals(this.bytes, that.bytes));
        }
    }

    public int get(int at) throws JSONException {
        if(at >= 0 && at <= this.length) {
            return this.bytes[at] & 255;
        } else {
            throw new JSONException("Bad character at " + at);
        }
    }

    public int hashCode() {
        return this.hashcode;
    }

    public String toString() throws JSONException {
        if(this.string == null) {
            int length = 0;
            char[] chars = new char[this.length];

            int c;
            for(int at = 0; at < this.length; at += characterSize(c)) {
                c = this.characterAt(at);
                if(c < 65536) {
                    chars[length] = (char)c;
                    ++length;
                } else {
                    chars[length] = (char)('\ud800' | c - 65536 >>> 10);
                    ++length;
                    chars[length] = (char)('\udc00' | c & 1023);
                    ++length;
                }
            }

            this.string = new String(chars, 0, length);
        }

        return this.string;
    }
}
