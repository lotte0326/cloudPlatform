package org.jeecgframework.modules.cgform.service.button;

import java.util.List;

import org.jeecgframework.modules.cgform.entity.button.CgformButtonSqlEntity;

import org.jeecgframework.core.common.service.CommonService;

public interface CgformButtonSqlServiceI extends CommonService{

	/**
	 * 校验按钮sql增强唯一性
	 * @param cgformButtonEntity
	 * @return
	 */
	public List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity cgformButtonSqlEntity);
	
	/**
	 * 根据buttonCode和formId初始化数据
	 * @param buttonCode
	 * @param formId
	 * @return
	 */
	public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode, String formId);
}
