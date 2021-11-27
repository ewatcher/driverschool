package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.TcarusingTemp;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.Car;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆管理service实现类
 * 
 * @author MEI702
 * 
 */
@Service("carService")
public class CarServiceImpl implements CarServiceI {

	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<Torganization> orgDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<TcarusingTemp> carusingTempDao;
	private BaseDaoI<Treservation> reservationDao;

	// 注入DAO
	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setOrgDao(BaseDaoI<Torganization> orgDao) {
		this.orgDao = orgDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setCarusingTempDao(BaseDaoI<TcarusingTemp> carusingTempDao) {
		this.carusingTempDao = carusingTempDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public Car save(Car car) {
		// 1.数据模型转换
		Tcar entry = new Tcar();
		BeanUtils.copyProperties(car, entry);
		// 2.设置相关属性
		entry.setId(UUID.randomUUID().toString());

		// 修改前教练的标识

		Ttrainer trainer = null;
		if (ValidateUtil.isValid(car.getTrainerId())) {
			trainer = trainerDao.get(Ttrainer.class, car.getTrainerId().trim());
			entry.setTrainer(trainer);
			// 标记该教练已经与车辆捆绑
			trainer.setCarBanding(1);
			// 标记该教练可以排班
			if (car.getUsingState() == Tcar.CAR_USING) {
				trainer.setArrangeFlag(1);
			}
			trainerDao.update(trainer);
		}

		// 创建车辆与车辆明细中间类
		TcarusingTemp carsingTemp = new TcarusingTemp();
		String carusingTempId = UUID.randomUUID().toString();
		carsingTemp.setId(carusingTempId);
		carsingTemp.setSchoolArea(car.getSchoolArea());
		carsingTemp.setCar(entry);
		carsingTemp.setDateCount(0);
		carsingTemp.setWeekCount(0);
		carsingTemp.setManthCount(0);
		carsingTemp.setYearCount(0);
		carsingTemp.setDateReservateCount(0);
		carsingTemp.setWeekReservateCount(0);
		carsingTemp.setMonthReservateCount(0);
		carsingTemp.setYearReservateCount(0);
		carusingTempDao.save(carsingTemp);
		entry.setCarusingTempId(carusingTempId);

		// 3.保存数据
		carDao.save(entry);
		// 4.返回结果集
		BeanUtils.copyProperties(entry, car);
		return car;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				if (!id.equals("0")) {
					// 3.根据ID从数据库中查找数据
					Tcar t = carDao.get(Tcar.class, id);
					if (t.getTrainer() != null) {
						// 修改教练与车辆捆绑状态
						Ttrainer trainer = t.getTrainer();
						trainer.setCarBanding(0);
						trainer.setArrangeFlag(0);
						trainerDao.update(trainer);
					}
					// 删除

					// 4.调用DAO层删除数据
					carDao.delete(t);
				}
			}
		}
	}

	@Override
	public Car update(Car car) throws ParseException {
		// 1.数据模型转换
		Tcar entry = carDao.get(Tcar.class, car.getId().trim());
		BeanUtils.copyProperties(car, entry, new String[] { "id" });
		// 2.设置相关属性
		entry.setId(car.getId().trim());
		// 修改前教练的标识
		String oldTrainerId = null;
		Ttrainer newTrainer = null;
		Ttrainer oldTrainer = null;
		if (entry.getTrainer() != null) {
			oldTrainer = entry.getTrainer();
			oldTrainerId = entry.getTrainer().getId();
		}
		if (ValidateUtil.isValid(car.getTrainerId())) {// 更新教练
			newTrainer = trainerDao.get(Ttrainer.class, car.getTrainerId().trim());
			// 1.更换车辆捆绑教练
			// 1.1原是车辆没有与教练捆绑
			if (entry.getTrainer() == null) {// 车辆之前不存在捆绑
				entry.setTrainer(newTrainer);
				// 标记该教练已经与车辆捆绑
				newTrainer.setCarBanding(1);// 标记教练已经与车辆捆绑
				// 设置教练排班状态
				if (car.getUsingState() == Tcar.CAR_USING) {
					newTrainer.setArrangeFlag(1);
				} else {
					newTrainer.setArrangeFlag(0);
				}
				if (this.getUnfinishedReservation(car.getSchoolArea(), newTrainer.getId())) {
					// 修改预约中的车辆状态为无车辆
					updateReservationCatState(car.getSchoolArea(), newTrainer.getId(), 1);
				}
				trainerDao.update(newTrainer);
			} else {// 2.1原先车辆与教练已经发生捆绑
					// 2.1.1更换车辆捆绑教练
				if (!oldTrainerId.equals(car.getTrainerId().trim())) {
					entry.setTrainer(newTrainer);
					// 标记该教练已经与车辆捆绑
					newTrainer.setCarBanding(1);// 标记教练已经与车辆捆绑
					// 设置教练排班状态
					if (car.getUsingState() == Tcar.CAR_USING) {
						newTrainer.setArrangeFlag(1);
					} else {
						newTrainer.setArrangeFlag(0);
					}
					oldTrainer.setArrangeFlag(0);
					oldTrainer.setCarBanding(0);
				} else {// 2.1.2保持原来捆绑的教练
						// 设置教练排班状态
					if (car.getUsingState() == Tcar.CAR_USING) {
						oldTrainer.setArrangeFlag(1);
					} else {
						oldTrainer.setArrangeFlag(0);
					}
				}
				trainerDao.update(newTrainer);
				trainerDao.update(oldTrainer);
			}
		} else {// 解除车与教练的捆绑
				// 如果原捆绑的教练有没有完成的预约不允许解除绑定
			if (oldTrainer != null) {
				oldTrainer.setCarBanding(0);// 标记当前教练没有与车辆捆绑
				oldTrainer.setArrangeFlag(0);// 当前教练排班状态为否
				trainerDao.update(oldTrainer);
				if (this.getUnfinishedReservation(car.getSchoolArea(), oldTrainerId)) {
					// 修改预约中的车辆状态为无车辆
					updateReservationCatState(car.getSchoolArea(), oldTrainerId, 0);
				}
			}
			entry.setTrainer(null);// 车辆解除捆绑
		}
		// 3.保存数据
		carDao.update(entry);
		// 4.返回结果集
		BeanUtils.copyProperties(entry, car);
		return car;
	}

	@Override
	public DataGrid dataGrid(Car car) {
		DataGrid dataGrid = new DataGrid();
		// 1.从数据库中查找出所有实体类信息
		// 2.查找出的信息进行数据模型转换
		// 3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(car)));
		dataGrid.setTotal(this.total(car));
		return dataGrid;
	}

	@Override
	public Map<String, Long> getCarBySchoolArea(String schoolIds) {
		Map<String, Long> retMap = new HashMap<String, Long>(0);
		if (schoolIds != null) {
			for (String s : schoolIds.split(",")) {
				Car car = new Car();
				car.setSchoolArea(s);
				Long total = this.total(car);
				retMap.put(s, total);
			}
		}

		return retMap;
	}

	@Override
	public Long getAllCarCount() {
		Car Car = new Car();
		return this.total(Car);
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<Car> changeModel(List<Tcar> cars) {
		List<Car> retCars = new ArrayList<Car>();
		if (cars != null && cars.size() > 0) {
			for (Tcar t : cars) {
				Car car = new Car();
				BeanUtils.copyProperties(t, car);
				Torganization org = orgDao.get(Torganization.class, t.getSchoolArea());
				if (org != null) {
					car.setSchoolArea(org.getName());
					car.setSchoolAreaName(org.getName());
				}
				if (t.getTrainer() != null) {
					car.setTrainerId(t.getTrainer().getId());
					car.setTrainerName(t.getTrainer().getName());
				}
				retCars.add(car);
			}
		}
		return retCars;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param car
	 * @return
	 */
	private List<Tcar> find(Car car) {
		String hql = " from Tcar t where 1=1";
		List<Object> params = new ArrayList<Object>();
		hql = this.addWhere(car, hql, params);
		if (car.getSort() != null && car.getOrder() != null) {
			hql += " order by " + car.getSort() + " " + car.getOrder();
		}

		return carDao.find(hql, params, car.getPage(), car.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param car
	 * @return
	 */
	private Long total(Car car) {
		String hql = "select count(*) from Tcar t where 1=1";
		List<Object> params = new ArrayList<Object>();
		hql = this.addWhere(car, hql, params);
		return carDao.count(hql, params);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param car
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Car car, String hql, List<Object> params) {
		// 车牌号（精确查询）
		if (car.getCarNo() != null && !car.getCarNo().trim().equals("")) {
			hql += " and t.carNo =?";
			params.add(car.getCarNo().trim());
		}
		// 行驶证号(精确查询)
		if (car.getLicenseNo() != null && !car.getLicenseNo().trim().equals("")) {
			hql += " and t.licenseNo =?";
			params.add(car.getLicenseNo().trim());
		}
		// 校区查询
		if (ValidateUtil.isValid(car.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(car.getSchoolArea().trim());
		}
		if (car.getCarType() != null) {
			hql += " and t.carType=?";
			params.add(car.getCarType());
		}
		if (car.getUsingState() != null) {
			hql += " and t.usingState=?";
			params.add(car.getUsingState());
		}
		if (car.getUseType() != null) {
			hql += " and t.useType=?";
			params.add(car.getUseType());
		}
		return hql;
	}

	@Override
	public void executeSQL(String sql) {
		carDao.executeSQL(sql);
	}

	// 将excel文件中的数据转化为车辆数据模块集合
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName)
			throws ParseException {

		ImportReturnModel model = new ImportReturnModel();
		boolean repeatFlag = false;
		String carNo = "";
		String licenseNo = "";
		List<Car> reVals = new ArrayList<Car>();

		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {
			Car car = new Car();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				Cell cell = row.getCell(j);
				String cellValue = StringUtil.getCellValueByCellType(cell);

				switch (j) {// 通过列数来判断对应插如的字段
				case 0:// 车辆号
				{
					if (carNoExistOrNot(cellValue)) {
						repeatFlag = true;
						carNo = carNo + ", " + cellValue;
					}
					car.setCarNo(cellValue);
				}

					break;
				case 1:// 行驶证号
				{
					if (licenseNoExistOrNot(cellValue)) {
						repeatFlag = true;
						licenseNo = licenseNo + ", " + cellValue;
					}
					car.setLicenseNo(cellValue);
				}

					break;
				// 车辆类型
				case 2: {
					car.setCarType(StringUtil.getCarTypeValue(cellValue));
				}
					break;
				case 3:// 使用状态
				{
					if (cellValue.equals("未启用")) {
						car.setUsingState(1);
					} else if (cellValue.equals("启用")) {
						car.setUsingState(2);
					} else if (cellValue.equals("其它")) {
						car.setUsingState(3);
					}
				}

					break;
				case 4:// 使用类型
					car.setUseType(StringUtil.getCarTypeValue(cellValue));

					break;
				case 5:// 车辆品牌
				{
					if (ValidateUtil.isValid(cellValue)) {
						car.setVehicleBrands(cellValue);
					} else {
						car.setVehicleBrands(null);
					}
				}
					break;
				case 6:// 车辆型号
				{
					if (ValidateUtil.isValid(cellValue)) {
						car.setVehicleType(cellValue);
					} else {
						car.setVehicleType(null);
					}

				}
					break;
				case 7:// 购买日期
					car.setBuyDate(DateUtil.getDateByString(cellValue));
					break;
				case 8:// 商业保险日期
					car.setBusinessExpireDay(DateUtil.getDateByString(cellValue));
					break;
				case 9:// 交强险日期
					car.setInsuranceExpireDay(DateUtil.getDateByString(cellValue));
					break;
				case 10:// 维护日期
					car.setMaintainDay(DateUtil.getDateByString(cellValue));
					break;
				case 11:// 年审日期
					car.setExaminedDay(DateUtil.getDateByString(cellValue));
					break;
				case 12:// 年审日期
					car.setTrainerId(this.getTrainerIdByIdentity(cellValue));
					break;
				}// end switch

			}
			reVals.add(car);

		}
		// 导入数据
		if (reVals.size() > 0 && repeatFlag == false) {
			for (Car car : reVals) {
				car.setSchoolArea(schoolArea);
				car.setOperator(operatorName);
				this.save(car);
			}
		}

		// 组织返回参数
		model.setMsg(carNo + licenseNo);
		model.setSize(reVals.size());
		if (repeatFlag) {
			model.setSuccess(false);
		} else {
			model.setSuccess(true);
		}
		return model;
	}

	private String getTrainerIdByIdentity(String identityId) {
		if (!ValidateUtil.isValid(identityId)) {
			return null;
		}
		String hql = "from Ttrainer t where t.identity=?";
		List<Object> params = new ArrayList<Object>();
		params.add(identityId);
		Ttrainer trainer = (Ttrainer) trainerDao.uniqueResult(hql, identityId);
		if (trainer != null) {
			return trainer.getId();
		}
		return null;
	}

	private boolean carNoExistOrNot(String carNo) {
		String hql = "select count(*) from Tcar t where t.carNo=?";
		Long temp = (Long) carDao.uniqueResult(hql, carNo);
		return temp != 0;
	}

	private boolean licenseNoExistOrNot(String licenseNo) {
		String hql = "select count(*) from Tcar t where t.licenseNo=?";
		Long temp = (Long) carDao.uniqueResult(hql, licenseNo);
		return temp != 0;
	}

	private boolean getUnfinishedReservation(String schoolArea, String trainerId) throws ParseException {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)) {
			return false;
		}
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String sql = "select count(*) from tb_reservations where schoolArea=? and trainerId=? and date>=?";
		Long total = reservationDao.countBySQL(sql, schoolArea, trainerId, date);
		if (total != null && total > 0) {
			return true;
		}
		return false;
	}

	private void updateReservationCatState(String schoolArea, String trainerId, Integer updateFlag) {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)) {
			return;
		}
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String sql = "select * from tb_reservations where schoolArea=? and trainerId=? and date>=?";
		List<Treservation> rets = reservationDao.findAllBySQL(Treservation.class, sql, schoolArea, trainerId, date);
		if (rets != null && rets.size() > 0) {
			for (Treservation entry : rets) {
				if (updateFlag == 0) {
					entry.setCarStateFlag(0);
				} else {
					entry.setCarStateFlag(1);
				}

				reservationDao.update(entry);
			}
		}
		return;
	}

	// 根据车辆ID获取车辆信息
	public Tcar getSingleById(String carId) throws Exception {
		if (!ValidateUtil.isValid(carId)) {
			return null;
		}
		String sql = "select * from tb_cars where id=?";
		List<Tcar> cars = carDao.findAllBySQL(Tcar.class, sql, carId);
		if (cars != null && cars.size() > 0) {
			return cars.get(0);
		}
		return null;
	}

	// 获取所有自动档车辆
	public List<Tcar> getAutoCars(String schoolArea) throws Exception {
		String sql = "select * from tb_cars where schoolArea=? and carType=7";
		List<Tcar> cars = carDao.findAllBySQL(Tcar.class, sql, schoolArea);
		if (cars != null && cars.size() > 0) {
			return cars;
		}
		return null;
	}

	public List<ComboboxJson> getAllCarsForCombobox(String schoolArea) {
		List<ComboboxJson> retJson = new ArrayList<ComboboxJson>();
		String sql = "select * from tb_cars where schoolArea=?";
		List<Tcar> cars = carDao.findAllBySQL(Tcar.class, sql, schoolArea);
		if (cars != null && cars.size() > 0) {
			for (Tcar car : cars) {
				ComboboxJson json = new ComboboxJson();
				json.setValue(car.getCarNo());
				json.setText(car.getCarNo());
				retJson.add(json);
			}
		}
		return retJson;
	}

	/**
	 * 根据教练标识查找教练车辆信息
	 * @param schoolArea
	 * @param trainerId
	 * @return
	 * @throws Exception
	 * 
	 *2017年7月9日
	 *Tcar
	 */
	public Tcar getCarsByTrainerId(String schoolArea, String trainerId) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)) {
			return null;
		}
		String sql = "select * from tb_cars where schoolArea=? and trainerId=?";
		List<Tcar> rets = carDao.findAllBySQL(Tcar.class, sql, schoolArea, trainerId);
		if (ValidateUtil.isValidListObject(rets)) {
			return rets.get(0);
		}
		return null;
	}

}
