//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class Classes {
    private Classes() {
    }

    protected static String[] getMethodParamNames(CtMethod cm) {
        CtClass cc = cm.getDeclaringClass();
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = null;
        if(codeAttribute == null) {
            return null;
        } else {
            attr = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
            String[] paramNames = (String[])null;

            try {
                paramNames = new String[cm.getParameterTypes().length];
            } catch (NotFoundException var8) {
                ;
            }

            int pos = Modifier.isStatic(cm.getModifiers())?0:1;

            for(int i = 0; i < paramNames.length; ++i) {
                paramNames[i] = attr.variableName(i + pos);
            }

            return paramNames;
        }
    }

    public static String[] getMethodParamNames(Class<?> clazz, String method, Class... paramTypes) {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        CtMethod cm = null;

        try {
            cc = pool.get(clazz.getName());
            String[] paramTypeNames = new String[paramTypes.length];

            for(int i = 0; i < paramTypes.length; ++i) {
                paramTypeNames[i] = paramTypes[i].getName();
            }

            cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
        } catch (NotFoundException var8) {
            ;
        }

        return getMethodParamNames(cm);
    }

    public static String[] getMethodParamNames(Class<?> clazz, String method) {
        ClassPool pool = ClassPool.getDefault();
        CtMethod cm = null;

        try {
            CtClass cc = pool.get(clazz.getName());
            cm = cc.getDeclaredMethod(method);
        } catch (NotFoundException var6) {
            ;
        }

        return getMethodParamNames(cm);
    }

    public static void main(String[] args) {
        try {
            String[] e = getMethodParamNames(Class.forName("org.jeecgframework.web.demo.service.test.TransactionTestServiceI"), "insertData");
            String[] var5 = e;
            int var4 = e.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                String c = var5[var3];
                System.out.println(c);
            }
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        }

    }
}
