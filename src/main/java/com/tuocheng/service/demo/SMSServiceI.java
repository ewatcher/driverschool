package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.SMS;

/**
 * 短信实体类管理接口
 * 
 * @author MEI702
 * 
 */
public interface SMSServiceI {
	/**
	 * 添加短信实体类信息
	 * 
	 * @param sms
	 * @return
	 */
	public SMS save(SMS sms)throws Exception;

	/**
	 * 更新短信实体类信息
	 * 
	 * @param sms
	 * @return
	 */
	public SMS udpate(SMS sms)throws Exception;

	/**
	 * 根据id标识删除短信实体类信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有短信实体类的信息，并以datagrid开发传递到前台
	 * 
	 * @param sms
	 * @return
	 */
	public DataGrid dataGrid(SMS sms)throws Exception;
	/**
	 * 根据学员标识，通过微信通知学员
	 * @param studentId
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public boolean sendMsgToStudentByWechat(SMS sms) throws Exception;

}
