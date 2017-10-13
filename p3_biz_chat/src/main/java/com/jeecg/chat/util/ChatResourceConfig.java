//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.util;

import java.util.ResourceBundle;

public class ChatResourceConfig {
    private static final String domain = getConfigByName("domain");
    private static final ResourceBundle bundle = ResourceBundle.getBundle("chat_config");

    public ChatResourceConfig() {
    }

    public static final String getConfigByName(String name) {
        return bundle.getString(name);
    }

    public static final String getDomain() {
        return domain;
    }
}
