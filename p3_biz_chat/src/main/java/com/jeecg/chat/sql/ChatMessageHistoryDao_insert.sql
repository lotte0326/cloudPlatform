INSERT INTO `jp_chat_message_his`
(msg_from,msg_to,msg_data,create_date,type)
VALUES
	(
		:chatMessageHis.from,
		:chatMessageHis.to,
		:chatMessageHis.data,
		sysdate(),
		:chatMessageHis.type
	);