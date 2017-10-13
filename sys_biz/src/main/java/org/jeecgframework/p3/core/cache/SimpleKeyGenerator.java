//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.cache;

import java.lang.reflect.Method;
import org.jeecgframework.p3.core.cache.SimpleKey;
import org.springframework.cache.interceptor.KeyGenerator;

public class SimpleKeyGenerator implements KeyGenerator {
    public SimpleKeyGenerator() {
    }

    public Object generate(Object target, Method method, Object... params) {
        String className = target.getClass().getName();
        String methodName = method.getName();
        return (new SimpleKey(className, methodName, params)).toString();
    }
}
