UPDATE t_s_base_user
SET 
	   <#if tSBaseUserP3Mail.activitisync ?exists>
		   activitiSync = :tSBaseUserP3Mail.activitisync,
		</#if>
	   <#if tSBaseUserP3Mail.browser ?exists>
		   browser = :tSBaseUserP3Mail.browser,
		</#if>
	   <#if tSBaseUserP3Mail.password ?exists>
		   password = :tSBaseUserP3Mail.password,
		</#if>
	   <#if tSBaseUserP3Mail.realname ?exists>
		   realname = :tSBaseUserP3Mail.realname,
		</#if>
	   <#if tSBaseUserP3Mail.signature ?exists>
		   signature = :tSBaseUserP3Mail.signature,
		</#if>
	   <#if tSBaseUserP3Mail.status ?exists>
		   status = :tSBaseUserP3Mail.status,
		</#if>
	   <#if tSBaseUserP3Mail.userkey ?exists>
		   userkey = :tSBaseUserP3Mail.userkey,
		</#if>
	   <#if tSBaseUserP3Mail.username ?exists>
		   username = :tSBaseUserP3Mail.username,
		</#if>
	   <#if tSBaseUserP3Mail.departid ?exists>
		   departid = :tSBaseUserP3Mail.departid,
		</#if>
	   <#if tSBaseUserP3Mail.deleteFlag ?exists>
		   delete_flag = :tSBaseUserP3Mail.deleteFlag,
		</#if>
WHERE id = :tSBaseUserP3Mail.id