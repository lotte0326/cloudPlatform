//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.spring.map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class MiniDaoLinkedMap extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private final Locale locale;

    public MiniDaoLinkedMap() {
        this((Locale)null);
    }

    public MiniDaoLinkedMap(int initialCapacity) {
        this(initialCapacity, (Locale)null);
    }

    public MiniDaoLinkedMap(int initialCapacity, Locale locale) {
        super(initialCapacity);
        this.locale = locale == null?Locale.getDefault():locale;
    }

    public MiniDaoLinkedMap(Locale locale) {
        this.locale = locale == null?Locale.getDefault():locale;
    }

    public void clear() {
        super.clear();
    }

    public boolean containsKey(Object key) {
        return key instanceof String && super.containsKey(this.convertKey((String)key));
    }

    protected String convertKey(String key) {
        return key.toLowerCase(this.locale);
    }

    public Object get(Object key) {
        return key instanceof String?super.get(this.convertKey((String)key)):null;
    }

    public Object put(String key, Object value) {
        return super.put(this.convertKey(key), value);
    }

    public void putAll(Map map) {
        if(!map.isEmpty()) {
            Iterator iterator = map.entrySet().iterator();

            while(iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                this.put(this.convertKey((String)entry.getKey()), entry.getValue());
            }

        }
    }

    public Object remove(Object key) {
        return key instanceof String?super.remove(this.convertKey((String)key)):null;
    }
}
