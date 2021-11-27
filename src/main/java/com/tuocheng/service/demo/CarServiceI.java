package com.tuocheng.service.demo;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.tuocheng.model.demo.Tcar;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.Car;

/**
 * 车辆管理实体类Service接口
 * 
 * @author MEI702
 * 
 */
public interface CarServiceI {
	/**
	 * 添加实体类信息
	 * 
	 * @param car
	 * @return
	 */
	public Car save(Car car) throws Exception;

	/**
	 * 删除实体类信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 更新实体类信息
	 * 
	 * @param car
	 * @return
	 */
	public Car update(Car car) throws Exception;

	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * 
	 * @param car
	 * @return
	 */
	public DataGrid dataGrid(Car car) throws Exception;

	/**
	 * 根据校区标识查找该校车辆信息
	 * 
	 * @param schoolIds
	 * @return
	 */
	public Map<String, Long> getCarBySchoolArea(String schoolIds);

	/**
	 * 统计车辆人数
	 * 
	 * @return
	 */
	public Long getAllCarCount();

	public void executeSQL(String sql);

	/**
	 * 将excel文件中的数据转化为车辆数据模块集合, 并导入
	 * 
	 * @param wb
	 * @param schoolArea
	 * @param operatorName
	 * @return
	 * @throws Exception
	 */
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName) throws Exception;

	/**
	 * 根据车辆标识获取车辆信息
	 * 
	 * @param carId
	 * @return
	 * @throws Exception
	 */
	public Tcar getSingleById(String carId) throws Exception;

	/**
	 * 获取自动档车辆信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Tcar> getAutoCars(String schoolArea) throws Exception;

	/**
	 * 获取车辆id,车牌号
	 * 
	 * @param schoolArea
	 * @return
	 */
	public List<ComboboxJson> getAllCarsForCombobox(String schoolArea);

	/**
	 * 根据教练标识查找教练捆绑的车辆信息
	 * @param schoolArea
	 * @param trainerId
	 * @return
	 * @throws Exception
	 * 
	 *2017年7月9日
	 *Tcar
	 */
	public Tcar getCarsByTrainerId(String schoolArea, String trainerId) throws Exception;
}
