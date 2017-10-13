//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.cache.manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.jeecgframework.poi.cache.manager.FileLoade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class POICacheManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(POICacheManager.class);
    private static LoadingCache<String, byte[]> loadingCache;

    public POICacheManager() {
    }

    public static InputStream getFile(String id) {
        try {
            byte[] e = Arrays.copyOf((byte[])loadingCache.get(id), ((byte[])loadingCache.get(id)).length);
            return new ByteArrayInputStream(e);
        } catch (ExecutionException var2) {
            LOGGER.error(var2.getMessage(), var2);
            return null;
        }
    }

    static {
        loadingCache = CacheBuilder.newBuilder().expireAfterWrite(7L, TimeUnit.DAYS).maximumSize(50L).build(new CacheLoader() {
            public byte[] load(Object url) throws Exception {
                return (new FileLoade()).getFile((String)url);
            }
        });
    }
}
