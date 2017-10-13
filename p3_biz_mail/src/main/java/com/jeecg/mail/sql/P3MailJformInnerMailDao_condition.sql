		<#if p3MailJformInnerMail.createName ?exists && p3MailJformInnerMail.createName ?length gt 0>
		    /* 创建人名称 */
			and jim.create_name like CONCAT('%', :p3MailJformInnerMail.createName ,'%')
		</#if>
		<#if p3MailJformInnerMail.createBy ?exists && p3MailJformInnerMail.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and jim.create_by like CONCAT('%', :p3MailJformInnerMail.createBy ,'%')
		</#if>
	    <#if p3MailJformInnerMail.createDate ?exists>
		    /* 创建日期 */
			and jim.create_date = :p3MailJformInnerMail.createDate
		</#if>
		<#if p3MailJformInnerMail.title ?exists && p3MailJformInnerMail.title ?length gt 0>
		    /* 主题 */
			and jim.title like CONCAT('%', :p3MailJformInnerMail.title ,'%')
		</#if>
		<#if p3MailJformInnerMail.attachment ?exists && p3MailJformInnerMail.attachment ?length gt 0>
		    /* 附件 */
			and jim.attachment like CONCAT('%', :p3MailJformInnerMail.attachment ,'%')
		</#if>
		<#if p3MailJformInnerMail.content ?exists && p3MailJformInnerMail.content ?length gt 0>
		    /* 内容 */
			and jim.content like CONCAT('%', :p3MailJformInnerMail.content ,'%')
		</#if>
		<#if p3MailJformInnerMail.status ?exists && p3MailJformInnerMail.status ?length gt 0>
		    /* 状态 */
			and jim.status like CONCAT('%', :p3MailJformInnerMail.status ,'%')
		</#if>
		<#if p3MailJformInnerMail.receiverNames ?exists && p3MailJformInnerMail.receiverNames ?length gt 0>
		    /* 接收者姓名列表 */
			and jim.receiver_names like CONCAT('%', :p3MailJformInnerMail.receiverNames ,'%')
		</#if>
		<#if p3MailJformInnerMail.receiverIds ?exists && p3MailJformInnerMail.receiverIds ?length gt 0>
		    /* 收件人标识列表 */
			and jim.receiver_ids like CONCAT('%', :p3MailJformInnerMail.receiverIds ,'%')
		</#if>
