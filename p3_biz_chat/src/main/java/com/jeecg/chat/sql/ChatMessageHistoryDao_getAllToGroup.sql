SELECT ID,msg_from as "from" ,msg_to as "to" ,msg_data as "data",create_date,type  from jp_chat_message_his where  msg_to=:chatMessageHis.to and type=:chatMessageHis.type
ORDER BY ID ASC
