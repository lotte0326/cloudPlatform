//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.entity;

import com.jeecg.chat.entity.ChatMessageMine;
import com.jeecg.chat.entity.ChatMessageTo;

public class ChatMessage {
    private ChatMessageTo to;
    private ChatMessageMine mine;

    public ChatMessage() {
    }

    public ChatMessageTo getTo() {
        return this.to;
    }

    public void setTo(ChatMessageTo to) {
        this.to = to;
    }

    public ChatMessageMine getMine() {
        return this.mine;
    }

    public void setMine(ChatMessageMine mine) {
        this.mine = mine;
    }
}
