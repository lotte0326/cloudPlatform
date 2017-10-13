UPDATE t_s_user
SET 
	   <#if tSUserP3Mail.email ?exists>
		   email = :tSUserP3Mail.email,
		</#if>
	   <#if tSUserP3Mail.mobilephone ?exists>
		   mobilePhone = :tSUserP3Mail.mobilephone,
		</#if>
	   <#if tSUserP3Mail.officephone ?exists>
		   officePhone = :tSUserP3Mail.officephone,
		</#if>
	   <#if tSUserP3Mail.signaturefile ?exists>
		   signatureFile = :tSUserP3Mail.signaturefile,
		</#if>
	   <#if tSUserP3Mail.updateName ?exists>
		   update_name = :tSUserP3Mail.updateName,
		</#if>
	    <#if tSUserP3Mail.updateDate ?exists>
		   update_date = :tSUserP3Mail.updateDate,
		</#if>
	   <#if tSUserP3Mail.updateBy ?exists>
		   update_by = :tSUserP3Mail.updateBy,
		</#if>
	   <#if tSUserP3Mail.createName ?exists>
		   create_name = :tSUserP3Mail.createName,
		</#if>
	    <#if tSUserP3Mail.createDate ?exists>
		   create_date = :tSUserP3Mail.createDate,
		</#if>
	   <#if tSUserP3Mail.createBy ?exists>
		   create_by = :tSUserP3Mail.createBy,
		</#if>
WHERE id = :tSUserP3Mail.id