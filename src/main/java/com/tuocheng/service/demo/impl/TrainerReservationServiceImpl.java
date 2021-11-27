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
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerReservation;
import com.tuocheng.service.demo.TrainerReservationServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("trainerReservationService")
public class TrainerReservationServiceImpl implements TrainerReservationServiceI {

	private BaseDaoI<TtrainerReservation> trainerReservationDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TtrainerReservationDetail> reservationDetailDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Treservation> reservationDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setTrainerReservationDao(BaseDaoI<TtrainerReservation> trainerReservationDao) {
		this.trainerReservationDao = trainerReservationDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setReservationDetailDao(BaseDaoI<TtrainerReservationDetail> reservationDetailDao) {
		this.reservationDetailDao = reservationDetailDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public TrainerReservation save(TrainerReservation reservation) {
		// 1.数据模型转换
		TtrainerReservation trainerReservation = new TtrainerReservation();
		BeanUtils.copyProperties(reservation, trainerReservation, new String[] { "id" });
		trainerReservation.setId(UUID.randomUUID().toString());

		// 2.保存数据
		trainerReservationDao.save(trainerReservation);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(trainerReservation, reservation);
		// 4.将转换后的数据返回给前台
		return reservation;
	}

	@Override
	public TrainerReservation udpate(TrainerReservation reservation) {
		// 1.数据模型转换
		TtrainerReservation trainerReservation = new TtrainerReservation();
		BeanUtils.copyProperties(reservation, trainerReservation, new String[] { "id" });
		trainerReservation.setId(UUID.randomUUID().toString());
		// 2.保存数据
		trainerReservationDao.update(trainerReservation);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(trainerReservation, reservation);
		// 4.将转换后的数据返回给前台
		return reservation;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.1根据ID从数据库中查找数据
					TtrainerReservation reservation = trainerReservationDao.get(TtrainerReservation.class, id);
					// 3.2 删除教练明细信息
					Set<TtrainerReservationDetail> reservationDetails = reservation.getTrainerReservationDetails();
					while (reservationDetails.iterator().hasNext()) {
						TtrainerReservationDetail t = reservationDetails.iterator().next();
						reservationDetailDao.delete(t);
					}
					// 4.调用DAO层删除数据
					trainerReservationDao.delete(reservation);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(TrainerReservation reservation) throws ParseException {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(reservation)));
		// 设置总记录数
		dataGrid.setTotal(this.total(reservation));
		return dataGrid;
	}

	public DataGrid personalDataGrid(TrainerReservation reservation) {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(reservation.getTrainerIdentityId())) {
			// 1.通过登录用户信息查找学员
			String hql = "from Ttrainer t where t.identity=?";
			Ttrainer trainer = (Ttrainer) trainerDao.uniqueResult(hql, reservation.getTrainerIdentityId());
			if (trainer != null) {
				// 2.要所学员与理论考试关联关系查找该学员的理论考试信息
				String hql2 = "from TtrainerReservation t where t.trainer.id=?";
				TtrainerReservation t = (TtrainerReservation) trainerReservationDao.uniqueResult(hql2, trainer.getId());
				TrainerReservation entry = new TrainerReservation();
				BeanUtils.copyProperties(t, entry);
				// 3.组织返回前台的参数
				List<TrainerReservation> rets = new ArrayList<TrainerReservation>();
				entry.setTrainerId(trainer.getId());
				entry.setSchoolAreaName(organizationDao.get(Torganization.class, trainer.getSchoolArea()).getName());
				entry.setSchoolArea(trainer.getSchoolArea());
				entry.setTrainerName(trainer.getName());
				entry.setTrainerIdentityId(trainer.getIdentity());
				entry.setStudentDriverType(trainer.getDriverType());
				entry.setStudentPhone(trainer.getPhone());
				rets.add(entry);
				// 4.将组织后的数据通过Datagrid模型传递到前台
				dataGrid.setRows(rets);
				dataGrid.setTotal(1L);
				return dataGrid;
			}
		}
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 * @throws ParseException
	 */
	private List<TrainerReservation> changedModel(List<TtrainerReservation> reservationLists) throws ParseException {
		List<TrainerReservation> retDriverLicenses = new ArrayList<TrainerReservation>();
		if (reservationLists != null && reservationLists.size() > 0) {
			for (TtrainerReservation t : reservationLists) {
				TrainerReservation entry = new TrainerReservation();
				BeanUtils.copyProperties(t, entry);
				// 将数据转换成教练预约页面中需要展现的教练数据信息
				Map<String, Integer> map = null;
				if (t.getTrainer() != null) {
					entry.setTrainerId(t.getTrainer().getId());
					entry.setTrainerName(t.getTrainer().getName());
					entry.setTrainerCodeNo(t.getTrainer().getCodeNo());
					entry.setTrainerIdentityId(t.getTrainer().getIdentity());
					entry.setTrainerPhone(t.getTrainer().getPhone());
					// 统计教练所带学员总数
					entry.setStudentTotal(
							this.getStudentCountByTrainerId(t.getTrainer().getSchoolArea(), t.getTrainer().getId()));

					if (t.getSchoolArea() != null && !t.getSchoolArea().trim().equals("")) {
						Torganization org = organizationDao.get(Torganization.class, t.getSchoolArea().trim());
						entry.setSchoolAreaName(org.getName());
					}

					// 统计教练预约信息
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String str = sdf.format(new Date());
					Date date = sdf.parse(str);
					map = this.getTrainerReservationCount(t.getTrainer().getId(), date);
					if (map != null && map.size() > 0) {
						entry.setDateReservateCount(map.get("dateReservationCount"));
						entry.setWeekReservateCount(map.get("weekReservationCount"));
						entry.setMonthReservateCount(map.get("monthReservationCount"));
						entry.setYearReservateCount(map.get("yearReservationCount"));

						entry.setDateCount(map.get("dateCount"));
						entry.setWeekCount(map.get("weekCount"));
						entry.setMonthCount(map.get("monthCount"));
						entry.setYearCount(map.get("yearCount"));
					}
				}

				retDriverLicenses.add(entry);
			}
		}
		return retDriverLicenses;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TtrainerReservation> find(TrainerReservation reservation) {
		String hql = "from TtrainerReservation t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(reservation, hql, paramList);
		if (reservation.getSort() != null && reservation.getOrder() != null) {
			hql += " order by " + reservation.getSort() + " " + reservation.getOrder();
		}
		return trainerReservationDao.find(hql, paramList, reservation.getPage(), reservation.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(TrainerReservation driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from TtrainerReservation t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return trainerReservationDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param reservation
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(TrainerReservation reservation, String hql, List<Object> params) {

		// 姓名模糊查询
		if (reservation.getTrainerName() != null && !reservation.getTrainerName().trim().equals("")) {
			hql += " and t.trainer.name like ?";
			params.add("%%" + reservation.getTrainerName().trim() + "%%");
		}
		// 根据教练身份证标识查找教练预约信息
		if (reservation.getTrainerIdentityId() != null && !reservation.getTrainerIdentityId().trim().equals("")) {
			hql += " and t.trainer.identity=?";
			params.add(reservation.getTrainerIdentityId().trim());
		}

		// 所属校区精确查询s
		if (reservation.getSchoolArea() != null && !reservation.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(reservation.getSchoolArea().trim());
		}

		return hql;
	}

	@Override
	public Map<String, Integer> getTrainerReservationCount(String trainerId, Date date) throws ParseException {
		if (date == null || !ValidateUtil.isValid(trainerId)) {
			return null;
		}

		// 按日统计车辆预约用时
		Map<String, Integer> dataMap = new HashMap<String, Integer>(0);
		String dateSql = "select * from tb_trainerReservationDetail t where 1=1 and t.reservationDate=? and t.trainerId=?";
		List<TtrainerReservationDetail> details = reservationDetailDao.findAllBySQL(TtrainerReservationDetail.class,
				dateSql, date, trainerId);
		Long totalDate = 0l;
		Long totalDateReal = 0l;// 已经生效的预约数
		if (details != null && details.size() > 0) {
			TtrainerReservationDetail detail = details.get(0);
			totalDate = this.getDateReservationCount(detail);
			totalDateReal = this.getRealReservationCount(detail);
		}
		dataMap.put("dateReservationCount", totalDate.intValue());
		dataMap.put("dateCount", totalDateReal.intValue());

		// 按<星期>统计车辆预约用时
		Date firstDate = DateUtil.getFisrtLastDateByDate(date).get("firstDateOfWeek");
		Date lastDate = DateUtil.getFisrtLastDateByDate(date).get("lastDateOfWeek");
		String hqlWeekSql = "select * from tb_trainerReservationDetail where reservationDate>=? and reservationDate <=? and trainerId=?";
		Long weekTotal = 0l;
		Long weekTotalReal = 0l;// 已经生效的预约数量
		List<TtrainerReservationDetail> weekDetails = reservationDetailDao.findAllBySQL(TtrainerReservationDetail.class,
				hqlWeekSql, firstDate, lastDate, trainerId);
		if (weekDetails != null && weekDetails.size() > 0) {
			for (TtrainerReservationDetail entry : weekDetails) {
				weekTotal = weekTotal + this.getDateReservationCount(entry);
				weekTotalReal = weekTotalReal + this.getRealReservationCount(entry);
			}
		}
		dataMap.put("weekReservationCount", weekTotal.intValue());
		dataMap.put("weekCount", weekTotalReal.intValue());

		// 按《月》统计车辆预约用时
		Date firstDateOfMonth = DateUtil.getDatesOfMonth(date).get("firstDateOfMonth");
		Date lastDateOfMonth = DateUtil.getDatesOfMonth(date).get("lastDateOfMonth");
		Long monthTotal = 0l;
		Long monthTotalReal = 0l;// 已经生效的预约数量
		List<TtrainerReservationDetail> monthDetails = reservationDetailDao.findAllBySQL(
				TtrainerReservationDetail.class, hqlWeekSql, firstDateOfMonth, lastDateOfMonth, trainerId);
		if (monthDetails != null && monthDetails.size() > 0) {
			for (TtrainerReservationDetail entry : monthDetails) {
				monthTotal = monthTotal + this.getDateReservationCount(entry);
				monthTotalReal = monthTotalReal + this.getRealReservationCount(entry);
			}
		}
		dataMap.put("monthReservationCount", monthTotal.intValue());
		dataMap.put("monthCount", monthTotalReal.intValue());

		// 按《年》统计车辆预约用时
		Date firstDateOfYear = DateUtil.getDatesOfYear(date).get("firstDateOfYear");
		Date lastDateOfYear = DateUtil.getDatesOfYear(date).get("lastDateOfYear");
		Long yearTotal = 0l;
		Long yearTotalReal = 0l;// 已经生效的预约数量
		List<TtrainerReservationDetail> yearDetails = reservationDetailDao.findAllBySQL(TtrainerReservationDetail.class,
				hqlWeekSql, firstDateOfYear, lastDateOfYear, trainerId);
		if (yearDetails != null && yearDetails.size() > 0) {
			for (TtrainerReservationDetail entry : yearDetails) {
				yearTotal = yearTotal + this.getDateReservationCount(entry);
				yearTotalReal = yearTotalReal + this.getRealReservationCount(entry);
			}
		}
		dataMap.put("yearReservationCount", yearTotal.intValue());
		dataMap.put("yearCount", yearTotalReal.intValue());

		if (dataMap != null && dataMap.size() > 0) {
			return dataMap;
		}
		return null;
	}

	private Long getDateReservationCount(TtrainerReservationDetail reservationDetail) {
		Long reservationTotal = 0l;
		if (reservationDetail == null) {
			return null;
		}
		if (reservationDetail.getReservationFieldId7() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId8() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId9() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId10() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId11() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId12() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId13() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId14() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId15() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId16() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId17() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId18() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId19() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId20() != null) {
			reservationTotal++;
		}
		if (reservationDetail.getReservationFieldId21() != null) {
			reservationTotal++;
		}
		return reservationTotal;
	}

	// 统计已经生效的预约数
	private Long getRealReservationCount(TtrainerReservationDetail trainerReservationDetail) {
		Long reservationTotal = 0l;
		if (trainerReservationDetail == null) {
			return null;
		}
		if (trainerReservationDetail.getReservationFieldId7() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId7())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId8() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId8())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId9() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId9())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId10() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId10())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId11() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId11())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId12() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId12())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId13() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId13())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId14() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId14())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId15() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId15())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId16() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId16())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId17() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId17())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId18() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId18())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId19() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId19())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId20() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId20())) {
				reservationTotal++;
			}
		}
		if (trainerReservationDetail.getReservationFieldId21() != null) {
			if (this.getFishedtReservation(trainerReservationDetail.getReservationFieldId21())) {
				reservationTotal++;
			}
		}
		return reservationTotal;
	}

	private boolean getFishedtReservation(String reservationId) {
		if (!ValidateUtil.isValid(reservationId)) {
			return false;
		}
		Treservation reservation = reservationDao.get(Treservation.class, reservationId);
		if (reservation != null) {
			if (reservation.getReservationState() == Treservation.RESERVATIONSTATE_FINISH
					|| reservation.getReservationState() == Treservation.RESERVATIONSTATE_CONFIRMING) {
				return true;
			}
		}
		return false;
	}

	private Integer getStudentCountByTrainerId(String schoolArea, String trainerId) {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)) {
			return null;
		}
		String hql = "select count(*) from Tstudent t where t.organization.id=? and t.trainer.id=?";
		Long total = (Long) studentDao.uniqueResult(hql, schoolArea, trainerId);
		return total.intValue();
	}
}