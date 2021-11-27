package com.tuocheng.scheduler;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tuocheng.service.demo.ReservationServiceI;

/**
 * 本任务，在下午20点将当天的预约状态设置为完成
 * 
 * @author 农峰
 * 
 */
public class ConfirmReservationTask extends QuartzJobBean {

	private ReservationServiceI reservationService;

	@Autowired
	public void setReservationService(ReservationServiceI reservationService) {
		this.reservationService = reservationService;
	}

	// 生成日志表
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		reservationService.createConfirmReservation();
	}

}
