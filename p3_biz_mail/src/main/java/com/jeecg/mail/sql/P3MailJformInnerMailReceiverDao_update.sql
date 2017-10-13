UPDATE jp_inner_mail_receiver
SET 
	    <#if p3MailJformInnerMailReceiver.createDate ?exists>
		   create_date = :p3MailJformInnerMailReceiver.createDate,
		</#if>
	    <#if p3MailJformInnerMailReceiver.updateDate ?exists>
		   update_date = :p3MailJformInnerMailReceiver.updateDate,
		</#if>
	   <#if p3MailJformInnerMailReceiver.mailId ?exists>
		   mail_id = :p3MailJformInnerMailReceiver.mailId,
		</#if>
	   <#if p3MailJformInnerMailReceiver.userId ?exists>
		   user_id = :p3MailJformInnerMailReceiver.userId,
		</#if>
	   <#if p3MailJformInnerMailReceiver.status ?exists>
		   status = :p3MailJformInnerMailReceiver.status,
		</#if>
		<#if p3MailJformInnerMailReceiver.isdelete ?exists>
		   isdelete = :p3MailJformInnerMailReceiver.isdelete,
		</#if>
WHERE id = :p3MailJformInnerMailReceiver.id