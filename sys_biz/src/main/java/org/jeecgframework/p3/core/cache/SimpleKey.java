//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.cache;

import java.io.Serializable;
import java.util.Arrays;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.MD5Util;
import org.springframework.util.StringUtils;

public final class SimpleKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger("CACHE_KEY", SimpleKey.class);
    private final Object[] params;
    private final String className;
    private final String methodName;

    public SimpleKey(String className, String methodName, Object... elements) {
        this.className = className;
        this.methodName = methodName;
        this.params = new Object[elements.length];
        System.arraycopy(elements, 0, this.params, 0, elements.length);
    }

    public boolean equals(Object obj) {
        return this == obj || obj instanceof SimpleKey && Arrays.equals(this.params, ((SimpleKey)obj).params);
    }

    public int hashCode() {
        return Arrays.deepHashCode(this.params);
    }

    public String toString() {
        String result = this.className + "." + this.methodName + "." + "SimpleKey[" + StringUtils.arrayToCommaDelimitedString(this.params) + "]";
        logger.debug("Serialize simple key to {}", new Object[]{result});
        return MD5Util.MD5Encode(result, "utf-8");
    }
}
