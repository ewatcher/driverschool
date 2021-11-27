package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.FeeDetailType;

/**
 * 短信实体类管理接口
 * 
 * @author MEI702
 * 
 */
public interface FeeDetailTypeServiceI {
	/**
	 * 添加短信实体类信息
	 * 
	 * @param feeDetailType
	 * @return
	 */
	public FeeDetailType save(FeeDetailType feeDetailType) throws Exception;

	/**
	 * 更新短信实体类信息
	 * 
	 * @param feeDetailType
	 * @return
	 */
	public FeeDetailType udpate(FeeDetailType feeDetailType) throws Exception;

	/**
	 * 根据id标识删除短信实体类信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有短信实体类的信息，并以datagrid开发传递到前台
	 * 
	 * @param feeDetailType
	 * @return
	 */
	public DataGrid dataGrid(FeeDetailType feeDetailType) throws Exception;

	/**
	 * 根据校区获取排序编号
	 * 
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Integer getOrderNoBySchoolArea(String schoolArea) throws Exception;

	/**
	 * 根据校区标识获取收费项目
	 * 
	 * @param schoolArea
	 * @return 返回JSON格式
	 * @throws Exception
	 * 
	 *             2017年7月10日 List<ComboboxJson>
	 */
	public List<ComboboxJson> getAllFeeDetailType(String schoolArea) throws Exception;

	/**
	 * 根据收费项目标识查找出其收费金额
	 * @param schoolArea
	 * @param id
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月12日 Double
	 */
	public Double getMoneyById(String schoolArea, String id) throws Exception;
}
