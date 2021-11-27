package com.tuocheng.service.demo;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.User;

public interface TrainerSerivceI {
	/**
	 * 添加教练信息
	 * 
	 * @param trainer
	 * @return
	 */
	public Trainer save(Trainer trainer) throws Exception;

	/**
	 * 更新教练信息
	 * 
	 * @param trainer
	 * @return
	 */
	public Trainer update(Trainer trainer) throws Exception;

	/**
	 * 根据ID标识删除教练信息
	 * 
	 * @param id
	 */
	public void delete(String id) throws Exception;

	/**
	 * 从数据库存中查找出所有教练员数据信息，并在datagrid方式传递给前台
	 * 
	 * @param trainer
	 * @return
	 */
	public DataGrid datagrid(Trainer trainer) throws Exception;

	/**
	 * 从数据库存中查找出所有教练员数据信息，并在datagrid方式传递给前台
	 * 
	 * @param trainer
	 * @return
	 */
	public DataGrid personalDatagrid(Trainer trainer) throws Exception;

	/**
	 * 查找出所有教练名字
	 * 
	 * @return
	 */
	public List<TrainerJson> getAllTrainerNameByJson() throws Exception;

	/**
	 * 通过ID得到实体对象
	 * 
	 * @param id
	 * @return
	 */
	public Ttrainer get(String id) throws Exception;

	/**
	 * 查找出所有教练，返回combobox的json格式数据
	 * 
	 * @return
	 */
	public List<ComboboxJson> getAllTrainersForCombobox(String schoolArea)
			throws Exception;

	/**
	 * 根据校区标识查找该校教练信息
	 * 
	 * @param schoolIds
	 * @return
	 */
	public Map<String, Long> getTrainerBySchoolArea(String schoolIds);

	/**
	 * 根据校区标识查找该校教练
	 * 
	 * @param identityId
	 * @return
	 */
	public boolean identityIdExistOrNot(String identityId);

	/**
	 * 统计教练人数
	 * 
	 * @return
	 */
	public Long getAllTrainerCount();

	/**
	 * 根据驾照类型统计教练总人数
	 * 
	 * @return
	 */
	public Map<String, Map<Integer, Long>> getCountByDriverType(String schoolIds);

	/**
	 * 根据教练员姓名及教练员编号获取教练员信息
	 * 
	 * @param name
	 * @param keyStr
	 * @return
	 */
	public Ttrainer getTrainerByNameAndKey(String name, String keyStr)
			throws Exception;

	/**
	 * 获取系统当前教练最大编号
	 * 
	 * @param user
	 * @return
	 */
	public String getBiguestTrainerNO(User user);

	/**
	 * 根据教练标识，判断与当前教练捆绑的车是否启用，车启用教练才能排班
	 * 
	 * @param strainerId
	 * @return
	 */
	public boolean trainerCarUsingOrNot(String strainerId);

	/**
	 * 根据标识查找实体信息
	 * 
	 * @param trainerId
	 * @return
	 */
	public Ttrainer getSingleById(String trainerId);

	/**
	 * 将excel中的教练信息转换为Ttrainer数据模型，并导入数据库
	 * 
	 * @param wb
	 * @param schoolArea
	 * @param operatorName
	 * @return
	 * @throws Exception
	 */
	public ImportReturnModel importDatasFromExcel(Workbook wb,
			String schoolArea, String operatorName) throws Exception;

	/**
	 * 获取所有教练ID
	 * 
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Ttrainer getRandomTrainer(String schoolArea) throws Exception;

	/**
	 * 根据校区标识统计各校区教练总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<ComboboxJson>> getTtrainerTotalByOrg()
			throws Exception;

	/**
	 * ' 校长端教练信息统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<ComboboxJson>> getTtrainerDatasByOrg()
			throws Exception;

	/**
	 * 分别查找系统中电车，模拟的教练信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Ttrainer>> getElectricAndSimulationTrainers(
			String schoolArea) throws Exception;

	/**
	 * 根据预约类型，校区标识查找教练信息（查找电车，模拟的教练（电车，模拟仅一个教练））
	 * @param reservationType
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Ttrainer getSimulateOrElectricTrainer(Integer reservationType,
			String schoolArea) throws Exception;
}
