package org.jeecgframework.modules.cgform.service.enhance;

import org.jeecgframework.modules.cgform.entity.enhance.CgformEnhanceJsEntity;

import org.jeecgframework.core.common.service.CommonService;

public interface CgformEnhanceJsServiceI extends CommonService{

	/**
	 * 根据cgJsType和formId查找数据
	 * @param cgJsType
	 * @param formId
	 * @return
	 */
	public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType, String formId);
}
