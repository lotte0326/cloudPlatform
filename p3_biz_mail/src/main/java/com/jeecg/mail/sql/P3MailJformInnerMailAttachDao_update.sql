UPDATE jp_inner_mail_attach
SET 
	   <#if p3MailJformInnerMailAttach.mailid ?exists>
		   mailid = :p3MailJformInnerMailAttach.mailid,
		</#if>
WHERE id = :p3MailJformInnerMailAttach.id