package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.UsingCar;
import com.tuocheng.service.demo.UsingCarServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆使用情况service实现类
 * 
 * @author 农峰
 * 
 */
@Service("usingCarService")
public class UsingCarServiceImpl implements UsingCarServiceI {

	private BaseDaoI<TusingCar> usingCarDao;
	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Torganization> orgDao;
	private BaseDaoI<Torganization> organizationDao;

	@Resource
	public void setUsingCarDao(BaseDaoI<TusingCar> usingCarDao) {
		this.usingCarDao = usingCarDao;
	}

	@Resource
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Resource
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Resource
	public void setOrgDao(BaseDaoI<Torganization> orgDao) {
		this.orgDao = orgDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public UsingCar save(UsingCar usingCar) {
		// 1.数据模型转换
		TusingCar t = new TusingCar();
		BeanUtils.copyProperties(usingCar, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		if (ValidateUtil.isValid(usingCar.getCarId())) {
			Tcar car = carDao.get(Tcar.class, usingCar.getCarId().trim());
		}
		if (ValidateUtil.isValid(usingCar.getTrainerId())) {
			Ttrainer trainer = trainerDao.get(Ttrainer.class, usingCar
					.getTrainerId().trim());
			t.setTrainer(trainer);
		}

		// 自动计算行驶公里数
		if (usingCar.getStartMile() != null && usingCar.getEndTime() != null) {
			t.setUsingMile(usingCar.getEndMile() - usingCar.getStartMile());
		}

		// 3.保存数据
		usingCarDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, usingCar);
		return usingCar;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				if (!id.equals("0")) {
					// 3.根据ID从数据库中查找数据
					TusingCar t = usingCarDao.get(TusingCar.class, id);
					// 4.调用DAO层删除数据
					usingCarDao.delete(t);
				}
			}
		}
	}

	@Override
	public UsingCar update(UsingCar usingCar) {
		// 1.数据模型转换
		TusingCar t = new TusingCar();
		BeanUtils.copyProperties(usingCar, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(usingCar.getId());
		// 保存关联关系
		if (ValidateUtil.isValid(usingCar.getCarId())) {
			Tcar car = carDao.get(Tcar.class, usingCar.getCarId().trim());
		}
		if (ValidateUtil.isValid(usingCar.getTrainerId())) {
			Ttrainer trainer = trainerDao.get(Ttrainer.class, usingCar
					.getTrainerId().trim());
			t.setTrainer(trainer);
		}

		// 自动计算行驶公里数
		if (usingCar.getStartMile() != null && usingCar.getEndTime() != null) {
			t.setUsingMile(usingCar.getEndMile() - usingCar.getStartMile());
		}

		// 3.更新数据
		usingCarDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, usingCar);
		return usingCar;
	}

	@Override
	public DataGrid dataGrid(UsingCar usingCar) {
		DataGrid dataGrid = new DataGrid();
		// 1.从数据库中查找出所有实体类信息
		// 2.查找出的信息进行数据模型转换
		// 3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(usingCar)));
		dataGrid.setTotal(this.total(usingCar));
		return dataGrid;
	}

	// 按日，周，月，年统计预约总数（总体分析统计）
	public List<ComboboxJson> getCarCoutByDateType(Integer type, String sql,
			Integer functionFlag, Integer firstDateFlay, Integer lastDateFlay)
			throws ParseException {
		List<ComboboxJson> retVal = new ArrayList<ComboboxJson>();
		ComboboxJson json = null;

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil
				.getFisrtLastDateByDate(new Date());
		Map<String, Date> retMonthMap = DateUtil.getLastDateOfMonth(new Date());
		Date firstDate = null;
		Date lastDate = null;
		if (type == 1) {// 按日统计报名
			Date date = new Date();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			firstDate = date;
			lastDate = date;
		} else {// 按周月年统计报名
			if (firstDateFlay == Tuser.FIRSTDATE_OF_WEEK) {// 本周
				firstDate = dateMaps.get("firstDateOfWeek");
				lastDate = dateMaps.get("lastDateOfWeek");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTWEEK) {// 上一周
				firstDate = dateMaps.get("firstDateOfLastWeek");
				lastDate = dateMaps.get("lastDateOfLastWeek");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_NEXTWEEK) {// 下一周
				firstDate = dateMaps.get("firstDateOfNextWeek");
				lastDate = dateMaps.get("lastDateOfNextWeek");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_MONTH) {// 本月
				firstDate = retMonthMap.get("firstDateOfMonth");
				lastDate = retMonthMap.get("lastDateOfMonth");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTMONTH) {// 上月
				firstDate = retMonthMap.get("firstDateOfLastMonth");
				lastDate = retMonthMap.get("lastDateOfLastMonth");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_YEAR) {// 今年
				firstDate = dateMaps.get("firstDateOfYear");
				lastDate = dateMaps.get("lastDateOfYear");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTYEAR) {// 去年
				firstDate = dateMaps.get("firstDateOfLastYear");
				lastDate = dateMaps.get("lastDateOfLastYear");
			}
		}

		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			json = new ComboboxJson();
			json.setText(comboboxJson.getValue());
			if (Tuser.FUNCTION_COUNT == functionFlag) {
				json.setValue(usingCarDao.countBySQL(sql,
						comboboxJson.getValue(), firstDate, lastDate)
						.toString());
			} else if (Tuser.FUNCTION_SUM == functionFlag) {
				json.setValue(usingCarDao.columSum(sql,
						comboboxJson.getValue(), firstDate, lastDate)
						.toString());
			}

			retVal.add(json);
		}

		if (retVal != null && retVal.size() > 0) {
			return retVal;
		}
		return null;
	}

	// 按周详细统计本周内每一天的学员报名数量
	public Map<String, Map<String, Integer>> getCarCountByOrgIds(
			Integer weekFlag) throws ParseException {

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil
				.getFisrtLastDateByDate(new Date());

		// 当前日期所在的周
		Date firstDateOfWeek = null;
		Date secondDateOfWeek = null;
		Date thirdDateOfWeek = null;
		Date fourthDateOfWeek = null;
		Date fifthDateOfWeek = null;
		Date sixthDateOfWeek = null;
		Date lastDateOfWeek = null;
		if (weekFlag == 3) {// 上周
			firstDateOfWeek = dateMaps.get("firstDateOfLastWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfLastWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfLastWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfLastWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfLastWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfLastWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfLastWeek");
		} else if (weekFlag == 2) {// 本同
			firstDateOfWeek = dateMaps.get("firstDateOfWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfWeek");
		} else if (weekFlag == 1) {// 下周
			firstDateOfWeek = dateMaps.get("firstDateOfNextWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfNextWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfNextWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfNextWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfNextWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfNextWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfNextWeek");
		}

		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(
				0);
		Map<String, Integer> totalMap = null;
		// 3.根据校区统计学员人数
		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			String dateSql = "select count(*) from tb_usingcars where schoolArea=? and usingDate=? and usingcarState<>4";
			Long mondayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), firstDateOfWeek);
			Long tuesdayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), secondDateOfWeek);
			Long wednesdayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), thirdDateOfWeek);
			Long thursdayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), fourthDateOfWeek);
			Long fridayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), fifthDateOfWeek);
			Long saturdayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), sixthDateOfWeek);
			Long sundayTotal = usingCarDao.countBySQL(dateSql,
					comboboxJson.getValue(), lastDateOfWeek);

			totalMap = new HashMap<String, Integer>(0);
			totalMap.put("Monday", mondayTotal.intValue());
			totalMap.put("Tuesday", tuesdayTotal.intValue());
			totalMap.put("Wednesday", wednesdayTotal.intValue());
			totalMap.put("Thursday", thursdayTotal.intValue());
			totalMap.put("Friday", fridayTotal.intValue());
			totalMap.put("Saturday", saturdayTotal.intValue());
			totalMap.put("Sunday", sundayTotal.intValue());

			retMap.put(comboboxJson.getValue(), totalMap);
		}
		if (retMap != null && retMap.size() > 0) {
			return retMap;
		}
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<UsingCar> changeModel(List<TusingCar> usingCar) {
		List<UsingCar> retCars = new ArrayList<UsingCar>();
		if (usingCar != null && usingCar.size() > 0) {
			for (TusingCar obj : usingCar) {
				UsingCar entry = new UsingCar();
				BeanUtils.copyProperties(obj, entry);
				Torganization org = orgDao.get(Torganization.class,
						obj.getSchoolArea());
				entry.setSchoolArea(org.getId());
				entry.setSchoolAreaName(org.getName());

				// TODO
				Ttrainer trainer = obj.getTrainer();
				entry.setTrainerId(trainer.getId());
				entry.setTrainerIdentity(trainer.getIdentity());
				entry.setTrainerPhone(trainer.getPhone());
				entry.setTrainerName(trainer.getName());

				retCars.add(entry);
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
	private List<TusingCar> find(UsingCar car) {
		String hql = " from TusingCar t where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		hql = this.addWhere(car, hql, params);

		if (car.getSort() != null && car.getOrder() != null) {
			hql += " order by usingDate desc " + car.getOrder();
		}

		return usingCarDao.find(hql, params, car.getPage(), car.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param car
	 * @return
	 */
	private Long total(UsingCar car) {
		String hql = "select count(*) from TusingCar t where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		hql = this.addWhere(car, hql, params);
		return usingCarDao.count(hql, params);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param usingCar
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(UsingCar usingCar, String hql, List<Object> params) {

		// 车辆使用日期查询
		if (usingCar.getUsingDateStart() != null) {
			hql += " and t.usingDate >=?";
			params.add(usingCar.getUsingDateStart());
		}
		if (usingCar.getUsingDateEnd() != null) {
			hql += " and t.usingDate <=?";
			params.add(usingCar.getUsingDateEnd());
		}
		// 行驶证号(精确查询)
		if (ValidateUtil.isValid(usingCar.getCarId())) {
			hql += " and t.carId =?";
			params.add(usingCar.getCarId().trim());
		}
		// 教练身份证号查询
		if (ValidateUtil.isValid(usingCar.getTrainerIdentity())) {
			hql += " and t.trainer.identity =?";
			params.add(usingCar.getTrainerIdentity().trim());
		}
		// 场地查询
		if (ValidateUtil.isValid(usingCar.getPlace())) {
			hql += " and t.place =?";
			params.add(usingCar.getPlace().trim());
		}
		// 校区查询
		if (ValidateUtil.isValid(usingCar.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(usingCar.getSchoolArea().trim());
		}
		return hql;
	}

	// 按排序获取校区标识
	private List<ComboboxJson> getOrganizationIds() {
		List<ComboboxJson> retLists = new ArrayList<ComboboxJson>();
		String sql = "select * from tb_organizations  where prentId is not null order by sequence asc";
		List<Torganization> orgs = organizationDao.findAllBySQL(
				Torganization.class, sql);
		if (orgs != null && orgs.size() > 0) {
			for (Torganization t : orgs) {
				ComboboxJson json = new ComboboxJson();
				json.setText(t.getName());
				json.setValue(t.getId());
				retLists.add(json);
			}
		}
		return retLists;
	}

}
