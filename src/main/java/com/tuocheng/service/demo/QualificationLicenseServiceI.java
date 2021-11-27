package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.QualificationLicense;

/**
 * 人员管理接口
 * @author MEI702
 *
 */
public interface QualificationLicenseServiceI {
	/**
	 * 添加人员信息
	 * @param qualificationLicense
	 * @return
	 */
	public QualificationLicense save(QualificationLicense qualificationLicense)throws Exception;
	/**
	 * 更新人员信息
	 * @param qualificationLicense
	 * @return
	 * 
	 */
	public QualificationLicense udpate(QualificationLicense qualificationLicense)throws Exception;
	/**
	 * 根据id标识删除人员信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	/**
	 * 从后台中获取所有人员的信息，并以datagrid开发传递到前台
	 * @param qualificationLicense
	 * @return
	 */
	public DataGrid dataGrid(QualificationLicense qualificationLicense)throws Exception;
	
}
