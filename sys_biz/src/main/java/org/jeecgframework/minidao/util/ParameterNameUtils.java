//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.jeecgframework.minidao.annotation.Param;

public class ParameterNameUtils {
    public ParameterNameUtils() {
    }

    public static String[] getMethodParameterNamesByAnnotation(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if(parameterAnnotations != null && parameterAnnotations.length != 0) {
            String[] parameterNames = new String[parameterAnnotations.length];
            int i = 0;
            Annotation[][] var7 = parameterAnnotations;
            int var6 = parameterAnnotations.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                Annotation[] parameterAnnotation = var7[var5];
                Annotation[] var11 = parameterAnnotation;
                int var10 = parameterAnnotation.length;

                for(int var9 = 0; var9 < var10; ++var9) {
                    Annotation annotation = var11[var9];
                    if(annotation instanceof Param) {
                        Param param = (Param)annotation;
                        parameterNames[i++] = param.value();
                    }
                }
            }

            return parameterNames;
        } else {
            return null;
        }
    }

    public void method1(@Param("parameter1") String param1, @Param("parameter2") String param2) {
        System.out.println(param1 + param2);
    }

    public static void main(String[] args) {
        Method method = null;

        try {
            method = Class.forName("org.jeecgframework.web.cgdynamgraph.dao.core.CgDynamGraphDao").getDeclaredMethod("queryCgDynamGraphItemssss", new Class[]{String.class, String.class});
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        String[] parameterNames = getMethodParameterNamesByAnnotation(method);
        System.out.println(Arrays.toString(parameterNames));
    }
}
