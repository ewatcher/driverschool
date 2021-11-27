package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.TcarusingTemp;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CarusingTemp;
import com.tuocheng.service.demo.CarusingTempServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆与车辆使用中间类Service
 * 
 * @author nongfeng
 * 
 */
@Service("carusingTempService")
public class CaruingTempServiceImpl implements CarusingTempServiceI {
	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<TcarusingTemp> carusingTempDao;
	private BaseDaoI<TusingCar> usingCarDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setCarusingTempDao(BaseDaoI<TcarusingTemp> carusingTempDao) {
		this.carusingTempDao = carusingTempDao;
	}

	@Autowired
	public void setUsingCarDao(BaseDaoI<TusingCar> usingCarDao) {
		this.usingCarDao = usingCarDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public CarusingTemp save(CarusingTemp carusingTemp) throws Exception {
		// 1.数据模型转换
		TcarusingTemp entry = new TcarusingTemp();
		BeanUtils.copyProperties(carusingTemp, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());

		// 2.保存数据
		carusingTempDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, carusingTemp);
		// 4.将转换后的数据返回给前台
		return carusingTemp;
	}

	@Override
	public CarusingTemp udpate(CarusingTemp CarusingTemp) throws Exception {
		// 1.数据模型转换
		TcarusingTemp entry = new TcarusingTemp();
		BeanUtils.copyProperties(CarusingTemp, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		// 2.保存数据
		carusingTempDao.update(entry);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, CarusingTemp);
		// 4.将转换后的数据返回给前台
		return CarusingTemp;
	}

	@Override
	public void delete(String ids) throws Exception {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.1根据ID从数据库中查找数据
					TcarusingTemp entry = carusingTempDao.get(
							TcarusingTemp.class, id);
					// 3.2 删除明细信息
					Set<TusingCar> usingCars = entry.getUsingCars();
					while (usingCars.iterator().hasNext()) {
						TusingCar t = usingCars.iterator().next();
						usingCarDao.delete(t);
					}
					// 4.调用DAO层删除数据
					carusingTempDao.delete(entry);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(CarusingTemp CarusingTemp) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 1.从数据库中查找出所有实体类信息
		// 2.查找出的信息进行数据模型转换
		// 3.设置展现数据
		dataGrid.setRows(this.changedModel(this.find(CarusingTemp)));
		dataGrid.setTotal(this.total(CarusingTemp));
		return dataGrid;
	}

	@Override
	public DataGrid personalDataGrid(CarusingTemp CarusingTemp)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 * @throws ParseException
	 */
	private List<CarusingTemp> changedModel(List<TcarusingTemp> carusingTemps)
			throws ParseException {
		List<CarusingTemp> reVal = new ArrayList<CarusingTemp>();
		if (carusingTemps != null && carusingTemps.size() > 0) {
			for (TcarusingTemp carusingTemp : carusingTemps) {
				CarusingTemp entry = new CarusingTemp();
				BeanUtils.copyProperties(carusingTemp, entry);
				// 将数据转换成教练预约页面中需要展现的教练数据信息
				Map<String, Integer> map = null;
				if (carusingTemp.getCar() != null) {
					Tcar car = carusingTemp.getCar();
					Ttrainer trainer = car.getTrainer();
					// 展现车辆信息
					entry.setCarId(car.getId());
					entry.setCarNo(car.getCarNo());
					entry.setCarType(car.getCarType());
					entry.setLicenseNo(car.getLicenseNo());
					entry.setUsingState(car.getUsingState());
					entry.setSchoolArea(car.getSchoolArea());
					entry.setWechatFalg(car.getReceiveWechatFlag());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String str = sdf.format(new Date());
					Date date = sdf.parse(str);
					map = this.getUsingcarCout(car.getId(), date);
					if (map != null && map.size() > 0) {
						entry.setDateReservateCount(map
								.get("dateReservationCount"));
						entry.setWeekReservateCount(map
								.get("weekReservationCount"));
						entry.setMonthReservateCount(map
								.get("monthReservationCount"));
						entry.setYearReservateCount(map
								.get("yearReservationCount"));
						
						entry.setDateCount(map.get("dateCount"));
						entry.setWeekCount(map.get("weekCount"));
						entry.setManthCount(map.get("monthCount"));
						entry.setYearCount(map.get("yearCount"));
					}
					// 展现该车辆有关的教练信息
					if (trainer != null) {
						entry.setTrainerId(trainer.getId());
						entry.setTrainerName(trainer.getName());
					}
					// 展现车辆所属机构
					if (ValidateUtil.isValid(carusingTemp.getSchoolArea())) {
						Torganization org = organizationDao.get(
								Torganization.class, carusingTemp
										.getSchoolArea().trim());
						entry.setSchoolAreaName(org.getName());
					}
				}

				reVal.add(entry);
			}
		}
		return reVal;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TcarusingTemp> find(CarusingTemp carusingTemp) {
		String hql = "from TcarusingTemp t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(carusingTemp, hql, paramList);
		if (carusingTemp.getSort() != null && carusingTemp.getOrder() != null) {
			hql += " order by " + carusingTemp.getSort() + " "
					+ carusingTemp.getOrder();
		}
		return carusingTempDao.find(hql, paramList, carusingTemp.getPage(),
				carusingTemp.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(CarusingTemp driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from TcarusingTemp t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return carusingTempDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param carusingTemp
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(CarusingTemp carusingTemp, String hql,
			List<Object> params) {

		// 姓名模糊查询
		if (ValidateUtil.isValid(carusingTemp.getCarNo())) {
			hql += " and t.car.carNo = ?";
			params.add(carusingTemp.getCarNo().trim());
		}
		// 根据教练身份证标识查找教练预约信息
		if (ValidateUtil.isValid(carusingTemp.getLicenseNo())) {
			hql += " and t.car.licenseNo=?";
			params.add(carusingTemp.getLicenseNo().trim());
		}

		// 所属校区精确查询s
		if (ValidateUtil.isValid(carusingTemp.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(carusingTemp.getSchoolArea().trim());
		}

		return hql;
	}

	@Override
	public Map<String, Integer> getUsingcarCout(String carId, Date date)
			throws ParseException {
		if (date == null || !ValidateUtil.isValid(carId)) {
			return null;
		}

		// 按日统计车辆预约用时
		Map<String, Integer> dataMap = new HashMap<String, Integer>(0);
		String hqlDate = "select count(*) from TusingCar t where 1=1 and t.usingDate=? and t.carId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(date);
		param.add(carId);
		Long dateTotal = usingCarDao.count(hqlDate, param);
		dataMap.put("dateReservationCount", dateTotal.intValue());

		// 按<星期>统计车辆预约用时
		Date firstDate = DateUtil.getFisrtLastDateByDate(date).get(
				"firstDateOfWeek");
		Date lastDate = DateUtil.getFisrtLastDateByDate(date).get(
				"lastDateOfWeek");
		String hqlWeek = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=?";
		List<Object> paramWeek = new ArrayList<Object>();
		paramWeek.add(firstDate);
		paramWeek.add(lastDate);
		paramWeek.add(carId);
		Long weekTotal = usingCarDao.count(hqlWeek, paramWeek);
		dataMap.put("weekReservationCount", weekTotal.intValue());

		// 按《月》统计车辆预约用时
		Date firstDateOfMonth = DateUtil.getDatesOfMonth(date).get(
				"firstDateOfMonth");
		Date lastDateOfMonth = DateUtil.getDatesOfMonth(date).get(
				"lastDateOfMonth");
		String monthHql = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=?";
		List<Object> paramMonth = new ArrayList<Object>();
		paramMonth.add(firstDateOfMonth);
		paramMonth.add(lastDateOfMonth);
		paramMonth.add(carId);
		Long monthTotal = usingCarDao.count(monthHql, paramMonth);
		dataMap.put("monthReservationCount", monthTotal.intValue());

		// 按《年》统计车辆预约用时
		Date firstDateOfYear = DateUtil.getDatesOfYear(date).get(
				"firstDateOfYear");
		Date lastDateOfYear = DateUtil.getDatesOfYear(date).get(
				"lastDateOfYear");
		String hqlYear = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=?";
		List<Object> paramYear = new ArrayList<Object>();
		paramYear.add(firstDateOfYear);
		paramYear.add(lastDateOfYear);
		paramYear.add(carId);
		Long yearTotal = usingCarDao.count(hqlYear, paramYear);
		dataMap.put("yearReservationCount", yearTotal.intValue());

		// 按日统计已经生效的车辆用时
		String hqlDateCout = "select count(*) from TusingCar t where 1=1 and t.usingDate=? and t.carId=? and t.usingcarState=?";
		List<Object> paramDateCout = new ArrayList<Object>();
		paramDateCout.add(date);
		paramDateCout.add(carId);
		paramDateCout.add(Treservation.RESERVATIONSTATE_FINISH);
		Long dateCoutTotal = usingCarDao.count(hqlDateCout, paramDateCout);
		dataMap.put("dateCount", dateCoutTotal.intValue());

		// 按<星期>统计已经生效的车辆用时
		Date firstDateCount = DateUtil.getFisrtLastDateByDate(date).get(
				"firstDateOfWeek");
		Date lastDateCount = DateUtil.getFisrtLastDateByDate(date).get(
				"lastDateOfWeek");
		String hqlWeekCout = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=? and t.usingcarState=?";
		List<Object> paramWeekCout = new ArrayList<Object>();
		paramWeekCout.add(firstDateCount);
		paramWeekCout.add(lastDateCount);
		paramWeekCout.add(carId);
		paramWeekCout.add(Treservation.RESERVATIONSTATE_FINISH);
		Long weekCoutTotal = usingCarDao.count(hqlWeekCout, paramWeekCout);
		dataMap.put("weekCount", weekCoutTotal.intValue());

		// 按《月》统计已经生效的车辆用时
		Date firstDateOfMonthCount = DateUtil.getDatesOfMonth(date).get(
				"firstDateOfMonth");
		Date lastDateOfMonthCount = DateUtil.getDatesOfMonth(date).get(
				"lastDateOfMonth");
		String monthHqlCount = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=? and t.usingcarState=?";
		List<Object> paramMonthCount = new ArrayList<Object>();
		paramMonthCount.add(firstDateOfMonthCount);
		paramMonthCount.add(lastDateOfMonthCount);
		paramMonthCount.add(carId);
		paramMonthCount.add(Treservation.RESERVATIONSTATE_FINISH);
		Long monthCountTotal = usingCarDao.count(monthHqlCount, paramMonthCount);
		dataMap.put("monthCount", monthCountTotal.intValue());

		// 按《年》统计已经生效的车辆用时
		Date firstDateOfYearCount = DateUtil.getDatesOfYear(date).get(
				"firstDateOfYear");
		Date lastDateOfYearCount = DateUtil.getDatesOfYear(date).get(
				"lastDateOfYear");
		String hqlYearCount = "select count(*) from TusingCar t where 1=1 and t.usingDate>=? and t.usingDate <=? and t.carId=? and t.usingcarState=?";
		List<Object> paramYearCount = new ArrayList<Object>();
		paramYearCount.add(firstDateOfYearCount);
		paramYearCount.add(lastDateOfYearCount);
		paramYearCount.add(carId);
		paramYearCount.add(Treservation.RESERVATIONSTATE_FINISH);
		Long yearCountTotal = usingCarDao.count(hqlYearCount, paramYearCount);
		dataMap.put("yearCount", yearCountTotal.intValue());

		if (dataMap != null && dataMap.size() > 0) {
			return dataMap;
		}
		return null;
	}

}
