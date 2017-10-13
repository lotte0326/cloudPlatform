SELECT ID,msg_from as "from" ,msg_to as "to" ,msg_data as "data",create_date,type  from jp_chat_message_his where msg_from=:chatMessageHis.from  and msg_to=:chatMessageHis.to and type=:chatMessageHis.type
UNION ALL
SELECT ID, msg_from as "from" ,msg_to as "to" ,msg_data as "data",create_date,type  from jp_chat_message_his where msg_from=:chatMessageHis.to  and msg_to=:chatMessageHis.from and type=:chatMessageHis.type
ORDER BY ID ASC
