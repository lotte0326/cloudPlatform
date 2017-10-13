//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.service.impl;

import com.alibaba.fastjson.JSON;
import com.jeecg.chat.dao.ChatMessageHistoryDao;
import com.jeecg.chat.entity.ChatMessage;
import com.jeecg.chat.entity.ChatMessageHistory;
import com.jeecg.chat.service.ChatMessageHistoryService;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chatMessageHistoryService")
public class ChatMessageHistoryServiceImpl implements ChatMessageHistoryService {
    @Autowired
    private ChatMessageHistoryDao messageHistoryDao;

    public ChatMessageHistoryServiceImpl() {
    }

    public ChatMessageHistory get(String id, String type) {
        return this.messageHistoryDao.get(id, type);
    }

    public int update(ChatMessageHistory chatMessageHis) {
        return this.messageHistoryDao.update(chatMessageHis);
    }

    public void insert(ChatMessageHistory chatMessageHis) {
        this.messageHistoryDao.insert(chatMessageHis);
    }

    public void insert(ChatMessage chatMessage) {
        ChatMessageHistory chatMessageHistory = new ChatMessageHistory();
        chatMessageHistory.setFrom(chatMessage.getMine().getId());
        chatMessageHistory.setTo(chatMessage.getTo().getId());
        chatMessageHistory.setData(JSON.toJSONString(chatMessage));
        chatMessageHistory.setType(chatMessage.getTo().getType());
        this.messageHistoryDao.insert(chatMessageHistory);
    }

    public MiniDaoPage<ChatMessageHistory> getAll(ChatMessageHistory chatMessageHis, int page, int rows) {
        return chatMessageHis.getType().equals("group")?this.messageHistoryDao.getAllToGroup(chatMessageHis, page, rows):this.messageHistoryDao.getAll(chatMessageHis, page, rows);
    }

    public void delete(ChatMessageHistory chatMessageHis) {
        this.messageHistoryDao.delete(chatMessageHis);
    }
}
