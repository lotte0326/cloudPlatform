		<#if tSBaseUserP3Mail.activitisync ?exists && tSBaseUserP3Mail.activitisync ?length gt 0>
		    /* activitiSync */
			and tsbu.activitiSync like CONCAT('%', :tSBaseUserP3Mail.activitisync ,'%')
		</#if>
		<#if tSBaseUserP3Mail.browser ?exists && tSBaseUserP3Mail.browser ?length gt 0>
		    /* browser */
			and tsbu.browser like CONCAT('%', :tSBaseUserP3Mail.browser ,'%')
		</#if>
		<#if tSBaseUserP3Mail.password ?exists && tSBaseUserP3Mail.password ?length gt 0>
		    /* password */
			and tsbu.password like CONCAT('%', :tSBaseUserP3Mail.password ,'%')
		</#if>
		<#if tSBaseUserP3Mail.realname ?exists && tSBaseUserP3Mail.realname ?length gt 0>
		    /* realname */
			and tsbu.realname like CONCAT('%', :tSBaseUserP3Mail.realname ,'%')
		</#if>
		<#if tSBaseUserP3Mail.signature ?exists && tSBaseUserP3Mail.signature ?length gt 0>
		    /* signature */
			and tsbu.signature like CONCAT('%', :tSBaseUserP3Mail.signature ,'%')
		</#if>
		<#if tSBaseUserP3Mail.status ?exists && tSBaseUserP3Mail.status ?length gt 0>
		    /* status */
			and tsbu.status like CONCAT('%', :tSBaseUserP3Mail.status ,'%')
		</#if>
		<#if tSBaseUserP3Mail.userkey ?exists && tSBaseUserP3Mail.userkey ?length gt 0>
		    /* userkey */
			and tsbu.userkey like CONCAT('%', :tSBaseUserP3Mail.userkey ,'%')
		</#if>
		<#if tSBaseUserP3Mail.username ?exists && tSBaseUserP3Mail.username ?length gt 0>
		    /* username */
			and tsbu.username like CONCAT('%', :tSBaseUserP3Mail.username ,'%')
		</#if>
		<#if tSBaseUserP3Mail.departid ?exists && tSBaseUserP3Mail.departid ?length gt 0>
		    /* departid */
			and tsbu.departid like CONCAT('%', :tSBaseUserP3Mail.departid ,'%')
		</#if>
		<#if tSBaseUserP3Mail.deleteFlag ?exists && tSBaseUserP3Mail.deleteFlag ?length gt 0>
		    /* 删除状态 */
			and tsbu.delete_flag like CONCAT('%', :tSBaseUserP3Mail.deleteFlag ,'%')
		</#if>
