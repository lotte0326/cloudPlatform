//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.logger;

import org.jeecgframework.p3.core.logger.Logger;

public final class LoggerFactory {
    private static final String EMPTY_PREFIX = null;

    public LoggerFactory() {
    }

    public static Logger getLogger(Class<?> key) {
        return new Logger(EMPTY_PREFIX, org.slf4j.LoggerFactory.getLogger(key));
    }

    public static Logger getLogger(String key) {
        return new Logger(EMPTY_PREFIX, org.slf4j.LoggerFactory.getLogger(key));
    }

    public static Logger getLogger(String prefix, Class<?> key) {
        return new Logger(prefix, org.slf4j.LoggerFactory.getLogger(key));
    }

    public static Logger getLogger(String prefix, String key) {
        return new Logger(prefix, org.slf4j.LoggerFactory.getLogger(key));
    }
}
