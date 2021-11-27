package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.DriverLicense;

/**
 * 人员管理接口
 * 
 * @author MEI702
 * 
 */
public interface DriverLicenseServiceI {
	/**
	 * 添加人员信息
	 * 
	 * @param driverLicense
	 * @return
	 */
	public DriverLicense save(DriverLicense driverLicense);

	/**
	 * 更新人员信息
	 * 
	 * @param driverLicense
	 * @return
	 */
	public DriverLicense udpate(DriverLicense driverLicense);

	/**
	 * 根据id标识删除人员信息
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 从后台中获取所有人员的信息，并以datagrid开发传递到前台
	 * 
	 * @param driverLicense
	 * @return
	 */
	public DataGrid dataGrid(DriverLicense driverLicense);

	/**
	 * 从后台中获取所有人员的信息，并以datagrid开发传递到前台
	 * 
	 * @param driverLicense
	 * @return
	 */
	public DataGrid personalDataGrid(DriverLicense driverLicense);

}
