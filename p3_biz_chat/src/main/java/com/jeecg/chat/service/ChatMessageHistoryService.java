//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service;

import com.jeecg.chat.entity.ChatMessage;
import com.jeecg.chat.entity.ChatMessageHistory;
import org.jeecgframework.minidao.pojo.MiniDaoPage;

public interface ChatMessageHistoryService {
    ChatMessageHistory get(String var1, String var2);

    int update(ChatMessageHistory var1);

    void insert(ChatMessageHistory var1);

    void insert(ChatMessage var1);

    MiniDaoPage<ChatMessageHistory> getAll(ChatMessageHistory var1, int var2, int var3);

    void delete(ChatMessageHistory var1);
}
