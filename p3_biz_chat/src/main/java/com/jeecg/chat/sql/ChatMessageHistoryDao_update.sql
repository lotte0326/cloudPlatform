UPDATE jp_chat_message_his
SET 
	   <#if chatMessageHis.msgType ?exists>
		   msg_type = :chatMessageHis.msgType,
		</#if>
	   <#if chatMessageHis.msg ?exists>
		   msg = :chatMessageHis.msg,
		</#if>
	   <#if chatMessageHis.fromUser ?exists>
		   from_user = :chatMessageHis.fromUser,
		</#if>
	   <#if chatMessageHis.fromName ?exists>
		   from_name = :chatMessageHis.fromName,
		</#if>
	   <#if chatMessageHis.toUser ?exists>
		   to_user = :chatMessageHis.toUser,
		</#if>
	   <#if chatMessageHis.toName ?exists>
		   to_name = :chatMessageHis.toName,
		</#if>
	   <#if chatMessageHis.accountid ?exists>
		   accountid = :chatMessageHis.accountid,
		</#if>
	    <#if chatMessageHis.createDate ?exists>
		   create_date = :chatMessageHis.createDate,
		</#if>
	   <#if chatMessageHis.createBy ?exists>
		   create_by = :chatMessageHis.createBy,
		</#if>
WHERE id = :chatMessageHis.id