	    <#if p3MailJformInnerMailReceiver.createDate ?exists>
		    /* 创建日期 */
			and jimr.create_date = :p3MailJformInnerMailReceiver.createDate
		</#if>
	    <#if p3MailJformInnerMailReceiver.updateDate ?exists>
		    /* 更新日期 */
			and jimr.update_date = :p3MailJformInnerMailReceiver.updateDate
		</#if>
		<#if p3MailJformInnerMailReceiver.mailId ?exists && p3MailJformInnerMailReceiver.mailId ?length gt 0>
		    /* 邮件标识 */
			and jimr.mail_id like CONCAT('%', :p3MailJformInnerMailReceiver.mailId ,'%')
		</#if>
		<#if p3MailJformInnerMailReceiver.userId ?exists && p3MailJformInnerMailReceiver.userId ?length gt 0>
		    /* 收件人标识 */
			and jimr.user_id like CONCAT('%', :p3MailJformInnerMailReceiver.userId ,'%')
		</#if>
		<#if p3MailJformInnerMailReceiver.status ?exists && p3MailJformInnerMailReceiver.status ?length gt 0>
		    /* 收件状态 */
			and jimr.status like CONCAT('%', :p3MailJformInnerMailReceiver.status ,'%')
		</#if>
		<#if p3MailJformInnerMailReceiver.isdelete ?exists && p3MailJformInnerMailReceiver.isdelete ?length gt 0>
		    /* 是否删除状态 */
			and jimr.isdelete like CONCAT('%', :p3MailJformInnerMailReceiver.isdelete ,'%')
		</#if>
