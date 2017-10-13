//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jeecgframework.minidao.aspect.EmptyInterceptor;

public class MinidaoInterceptor implements EmptyInterceptor {
    public MinidaoInterceptor() {
    }

    public boolean onInsert(Field[] fields, Object obj) {
        HashMap map = new HashMap();

        for(int e = 0; e < fields.length; ++e) {
            fields[e].setAccessible(true);
            String fieldName = fields[e].getName();
            if("createBy".equals(fieldName)) {
                map.put("createBy", "scott");
            }

            if("createDate".equals(fieldName)) {
                map.put("createDate", new Date());
            }
        }

        try {
            setFieldValue(map, obj);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return false;
    }

    public boolean onUpdate(Field[] fields, Object obj) {
        return false;
    }

    public static void setFieldValue(Map<Object, Object> map, Object bean) throws Exception {
        Class cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        Field[] var8 = fields;
        int var7 = fields.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            Field field = var8[var6];
            String fldtype = field.getType().getSimpleName();
            String fldSetName = field.getName();
            String setMethod = pareSetName(fldSetName);
            if(checkMethod(methods, setMethod) && map.containsKey(fldSetName)) {
                Object value = map.get(fldSetName);
                System.out.println(value.toString());
                Method method = cls.getMethod(setMethod, new Class[]{field.getType()});
                System.out.println(method.getName());
                if("String".equals(fldtype)) {
                    method.invoke(bean, new Object[]{(String)value});
                } else if("Double".equals(fldtype)) {
                    method.invoke(bean, new Object[]{(Double)value});
                } else if("int".equals(fldtype)) {
                    int val = Integer.valueOf((String)value).intValue();
                    method.invoke(bean, new Object[]{Integer.valueOf(val)});
                } else {
                    method.invoke(bean, new Object[]{value});
                }
            }
        }

    }

    public static String pareSetName(String fldname) {
        if(fldname != null && !"".equals(fldname)) {
            String pro = "set" + fldname.substring(0, 1).toUpperCase() + fldname.substring(1);
            return pro;
        } else {
            return null;
        }
    }

    public static boolean checkMethod(Method[] methods, String met) {
        if(methods != null) {
            Method[] var5 = methods;
            int var4 = methods.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                Method method = var5[var3];
                if(met.equals(method.getName())) {
                    return true;
                }
            }
        }

        return false;
    }
}
