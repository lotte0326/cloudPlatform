UPDATE jp_inner_mail
SET 
	   <#if p3MailJformInnerMail.createName ?exists>
		   create_name = :p3MailJformInnerMail.createName,
		</#if>
	   <#if p3MailJformInnerMail.createBy ?exists>
		   create_by = :p3MailJformInnerMail.createBy,
		</#if>
	    <#if p3MailJformInnerMail.createDate ?exists>
		   create_date = :p3MailJformInnerMail.createDate,
		</#if>
	   <#if p3MailJformInnerMail.title ?exists>
		   title = :p3MailJformInnerMail.title,
		</#if>
	   <#if p3MailJformInnerMail.attachment ?exists>
		   attachment = :p3MailJformInnerMail.attachment,
		</#if>
	   <#if p3MailJformInnerMail.content ?exists>
		   content = :p3MailJformInnerMail.content,
		</#if>
	   <#if p3MailJformInnerMail.status ?exists>
		   status = :p3MailJformInnerMail.status,
		</#if>
	   <#if p3MailJformInnerMail.receiverNames ?exists>
		   receiver_names = :p3MailJformInnerMail.receiverNames,
		</#if>
	   <#if p3MailJformInnerMail.receiverIds ?exists>
		   receiver_ids = :p3MailJformInnerMail.receiverIds,
		</#if>
WHERE id = :p3MailJformInnerMail.id