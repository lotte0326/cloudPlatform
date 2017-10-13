//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.cache.manager;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FileLoade {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoade.class);

    FileLoade() {
    }

    public byte[] getFile(String url) {
        FileInputStream fileis = null;
        ByteArrayOutputStream baos = null;

        try {
            try {
                fileis = new FileInputStream(url);
            } catch (FileNotFoundException var19) {
                String len = PoiPublicUtil.getWebRootPath(url);
                fileis = new FileInputStream(len);
            }

            baos = new ByteArrayOutputStream();
            byte[] e = new byte[1024];

            int len1;
            while((len1 = fileis.read(e)) > -1) {
                baos.write(e, 0, len1);
            }

            baos.flush();
            byte[] var6 = baos.toByteArray();
            return var6;
        } catch (FileNotFoundException var20) {
            LOGGER.error(var20.getMessage(), var20);
        } catch (IOException var21) {
            LOGGER.error(var21.getMessage(), var21);
        } finally {
            try {
                if(fileis != null) {
                    fileis.close();
                }

                if(fileis != null) {
                    baos.close();
                }
            } catch (IOException var18) {
                LOGGER.error(var18.getMessage(), var18);
            }

        }

        LOGGER.error(fileis + "这个路径文件没有找到,请查询");
        return null;
    }
}
