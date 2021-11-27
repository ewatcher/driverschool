package com.tuocheng.service.demo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.UsingCar;

/**
 * 车辆管理实体类Service接口
 * @author MEI702
 *
 */
public interface UsingCarServiceI {
	/**
	 * 添加实体类信息
	 * @param car
	 * @return
	 */
	public UsingCar save(UsingCar car)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param car
	 * @return
	 */
	public UsingCar update(UsingCar car)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param car
	 * @return
	 */
	public DataGrid dataGrid(UsingCar car)throws Exception;
	
	/**
	 * 按日，周，月，年统计车辆使用总数（总体分析统计）
	 * 
	 * @param type
	 * @param sql
	 * @param functionFlag
	 * @param firstDateFlay
	 * @param lastDateFlay
	 * @return
	 * @throws ParseException
	 */
	public List<ComboboxJson> getCarCoutByDateType(Integer type,
			String sql, Integer functionFlag, Integer firstDateFlay,
			Integer lastDateFlay) throws ParseException;

	/**
	 * 按周详细统计本周内每一天的车辆使用情况
	 * 
	 * @param weekFlag
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Map<String, Integer>> getCarCountByOrgIds(
			Integer weekFlag) throws ParseException;
}
