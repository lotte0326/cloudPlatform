		<#if p3MailJformInnerMailAttach.mailid ?exists && p3MailJformInnerMailAttach.mailid ?length gt 0>
		    /* mailid */
			and jima.mailid like CONCAT('%', :p3MailJformInnerMailAttach.mailid ,'%')
		</#if>
