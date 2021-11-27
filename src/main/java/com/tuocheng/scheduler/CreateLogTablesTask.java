package com.tuocheng.scheduler;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tuocheng.service.demo.LogServiceI;
import com.tuocheng.util.base.LogUtil;

/**
 * @author nongshaoyang 创建动态生成日志表的任务
 */
public class CreateLogTablesTask extends QuartzJobBean {

	private LogServiceI logService;
  
	@Autowired
	public void setLogService(LogServiceI logService) {
		this.logService = logService;
	}

	// 生成日志表
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// 生成下一个月日志表 表名：logs_2016_01
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);

		// 生成下二个月日志表
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);

		// 生成下三个月日志表
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
	}

}
