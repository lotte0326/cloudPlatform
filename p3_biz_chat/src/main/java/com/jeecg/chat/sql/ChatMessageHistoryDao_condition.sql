		<#if ( chatMessageHis.msgType )?? && chatMessageHis.msgType ?length gt 0>
		    /* 消息类型 */
			and cmh.msg_type like CONCAT('%', :chatMessageHis.msgType ,'%') 
		</#if>
		<#if ( chatMessageHis.msg )?? && chatMessageHis.msg ?length gt 0>
		    /* 消息数据 */
			and cmh.msg like CONCAT('%', :chatMessageHis.msg ,'%') 
		</#if>
		<#if ( chatMessageHis.fromUser )?? && chatMessageHis.fromUser ?length gt 0>
		    /* 消息发送者 */
			and cmh.from_user like CONCAT('%', :chatMessageHis.fromUser ,'%') 
		</#if>
		<#if ( chatMessageHis.fromName )?? && chatMessageHis.fromName ?length gt 0>
		    /* 发送者的真实姓名 */
			and cmh.from_name like CONCAT('%', :chatMessageHis.fromName ,'%') 
		</#if>
		<#if ( chatMessageHis.toUser )?? && chatMessageHis.toUser ?length gt 0>
		    /* 消息接收者 */
			and cmh.to_user like CONCAT('%', :chatMessageHis.toUser ,'%') 
		</#if>
		<#if ( chatMessageHis.toName )?? && chatMessageHis.toName ?length gt 0>
		    /* 接收者的真实姓名 */
			and cmh.to_name like CONCAT('%', :chatMessageHis.toName ,'%') 
		</#if>
		<#if ( chatMessageHis.accountid )?? && chatMessageHis.accountid ?length gt 0>
		    /* accountid */
			and cmh.accountid like CONCAT('%', :chatMessageHis.accountid ,'%') 
		</#if>
	    <#if ( chatMessageHis.createDate )??>
		    /* create_date */
			and cmh.create_date = :chatMessageHis.createDate
		</#if>
		<#if ( chatMessageHis.createBy )?? && chatMessageHis.createBy ?length gt 0>
		    /* create_by */
			and cmh.create_by like CONCAT('%', :chatMessageHis.createBy ,'%') 
		</#if>
