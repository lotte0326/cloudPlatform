package org.jeecgframework.modules.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.modules.system.pojo.base.TSCategoryEntity;

public interface CategoryServiceI extends CommonService{
	/**
	 * 保存分类管理
	 * @param category
	 */
	void saveCategory(TSCategoryEntity category);

}
