		<#if tSUserP3Mail.email ?exists && tSUserP3Mail.email ?length gt 0>
		    /* email */
			and tsu.email like CONCAT('%', :tSUserP3Mail.email ,'%')
		</#if>
		<#if tSUserP3Mail.mobilephone ?exists && tSUserP3Mail.mobilephone ?length gt 0>
		    /* mobilePhone */
			and tsu.mobilePhone like CONCAT('%', :tSUserP3Mail.mobilephone ,'%')
		</#if>
		<#if tSUserP3Mail.officephone ?exists && tSUserP3Mail.officephone ?length gt 0>
		    /* officePhone */
			and tsu.officePhone like CONCAT('%', :tSUserP3Mail.officephone ,'%')
		</#if>
		<#if tSUserP3Mail.signaturefile ?exists && tSUserP3Mail.signaturefile ?length gt 0>
		    /* signatureFile */
			and tsu.signatureFile like CONCAT('%', :tSUserP3Mail.signaturefile ,'%')
		</#if>
		<#if tSUserP3Mail.updateName ?exists && tSUserP3Mail.updateName ?length gt 0>
		    /* 修改人 */
			and tsu.update_name like CONCAT('%', :tSUserP3Mail.updateName ,'%')
		</#if>
	    <#if tSUserP3Mail.updateDate ?exists>
		    /* 修改时间 */
			and tsu.update_date = :tSUserP3Mail.updateDate
		</#if>
		<#if tSUserP3Mail.updateBy ?exists && tSUserP3Mail.updateBy ?length gt 0>
		    /* 修改人id */
			and tsu.update_by like CONCAT('%', :tSUserP3Mail.updateBy ,'%')
		</#if>
		<#if tSUserP3Mail.createName ?exists && tSUserP3Mail.createName ?length gt 0>
		    /* 创建人 */
			and tsu.create_name like CONCAT('%', :tSUserP3Mail.createName ,'%')
		</#if>
	    <#if tSUserP3Mail.createDate ?exists>
		    /* 创建时间 */
			and tsu.create_date = :tSUserP3Mail.createDate
		</#if>
		<#if tSUserP3Mail.createBy ?exists && tSUserP3Mail.createBy ?length gt 0>
		    /* 创建人id */
			and tsu.create_by like CONCAT('%', :tSUserP3Mail.createBy ,'%')
		</#if>
