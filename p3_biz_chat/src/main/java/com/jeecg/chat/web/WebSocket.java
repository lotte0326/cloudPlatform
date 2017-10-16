package com.jeecg.chat.web;

import com.alibaba.fastjson.JSON;
import com.jeecg.chat.entity.ChatMessage;
import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.SocketUser;
import com.jeecg.chat.service.ChatMessageHistoryService;
import com.jeecg.chat.service.ImService;
import com.jeecg.chat.service.impl.UserManager;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@ServerEndpoint("/WebSocket/{id}")
public class WebSocket {
    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);
    private static int onlineCount = 0;
    private ChatMessageHistoryService chatMessageHistoryService;
    private ImService imService;

    public WebSocket() {
        ApplicationContext ctx = ApplicationContextUtil.getContext();
        if(ctx.containsBean("chatMessageHistoryService")) {
            this.chatMessageHistoryService = (ChatMessageHistoryService)ctx.getBean("chatMessageHistoryService");
        }

        if(ctx.containsBean("imService")) {
            this.imService = (ImService)ctx.getBean("imService");
        }

    }

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        SocketUser socketUser = new SocketUser();
        socketUser.setSession(session);
        socketUser.setUserId(id);
        UserManager.getInstance().addUser(socketUser);
        logger.info("有新连接加入！当前在线人数为" + UserManager.getInstance().getOnlineCount());
    }

    @OnClose
    public void close(Session session, @PathParam("id") String id) {
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(id);
        UserManager.getInstance().removeUser(user);
        logger.info("当前在线用户：" + UserManager.getInstance().getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:" + message);
        ChatMessage chatMessage = (ChatMessage)JSON.parseObject(message, ChatMessage.class);
        this.chatMessageHistoryService.insert(chatMessage);
        if("friend".equals(chatMessage.getTo().getType())) {
            this.sendToFriend(chatMessage);
        } else if("group".equals(chatMessage.getTo().getType())) {
            this.sendTOGroup(chatMessage);
        }

    }

    private void sendToFriend(ChatMessage chatMessage) {
        try {
            if(UserManager.getInstance().getUser(chatMessage.getTo().getId()).getSession() != null) {
                UserManager.getInstance().getUser(chatMessage.getTo().getId()).getSession().getBasicRemote().sendText(JSON.toJSONString(chatMessage));
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void sendTOGroup(ChatMessage chatMessage) {
        List imTSBaseUsers = this.imService.getUsers();
        P3ImTSBaseUser myuser = null;
        Iterator var5 = imTSBaseUsers.iterator();

        P3ImTSBaseUser user;
        while(var5.hasNext()) {
            user = (P3ImTSBaseUser)var5.next();
            if(chatMessage.getMine().getId().equals(user.getId())) {
                myuser = user;
            }
        }

        var5 = imTSBaseUsers.iterator();

        while(var5.hasNext()) {
            user = (P3ImTSBaseUser)var5.next();
            if(myuser.getMaindepartid().equals(user.getMaindepartid()) && !myuser.getId().equals(user.getId())) {
                try {
                    if(UserManager.getInstance().getUser(user.getId()).getSession() != null) {
                        UserManager.getInstance().getUser(user.getId()).getSession().getBasicRemote().sendText(JSON.toJSONString(chatMessage));
                    }
                } catch (IOException var7) {
                    var7.printStackTrace();
                }
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        logger.debug(error.getMessage());
    }
}
