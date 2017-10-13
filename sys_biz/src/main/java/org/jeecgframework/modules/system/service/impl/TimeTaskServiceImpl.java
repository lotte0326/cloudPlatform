package org.jeecgframework.modules.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.modules.system.service.TimeTaskServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}