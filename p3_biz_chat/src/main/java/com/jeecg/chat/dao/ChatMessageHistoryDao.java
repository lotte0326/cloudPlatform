//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.chat.dao;

import com.jeecg.chat.entity.ChatMessageHistory;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageHistoryDao {
    @Sql("SELECT * from jp_chat_message_his where msg_from=:id or msg_to=:id and type=:type")
    ChatMessageHistory get(@Param("id") String var1, @Param("type") String var2);

    int update(@Param("chatMessageHis") ChatMessageHistory var1);

    void insert(@Param("chatMessageHis") ChatMessageHistory var1);

    @ResultType(ChatMessageHistory.class)
    MiniDaoPage<ChatMessageHistory> getAll(@Param("chatMessageHis") ChatMessageHistory var1, @Param("page") int var2, @Param("rows") int var3);

    @ResultType(ChatMessageHistory.class)
    MiniDaoPage<ChatMessageHistory> getAllToGroup(@Param("chatMessageHis") ChatMessageHistory var1, @Param("page") int var2, @Param("rows") int var3);

    @Sql("DELETE from jp_chat_message_his WHERE ID = :chatMessageHis.id")
    void delete(@Param("chatMessageHis") ChatMessageHistory var1);
}
