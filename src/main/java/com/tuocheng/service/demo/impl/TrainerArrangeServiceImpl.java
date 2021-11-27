package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphbuilder.math.func.Function;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerArrange;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerArrange;
import com.tuocheng.service.demo.TrainerArrangeServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 人员管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("trainerArrangeService")
public class TrainerArrangeServiceImpl implements TrainerArrangeServiceI {

	private BaseDaoI<TtrainerArrange> trainerArrangeDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TtrainerReservation> trainerReservationDao;
	private BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao;
	private BaseDaoI<Treservation> reservationDao;
	private BaseDaoI<TusingCar> usingCarDao;

	@Autowired
	public void setTrainerArrangeDao(BaseDaoI<TtrainerArrange> trainerArrangeDao) {
		this.trainerArrangeDao = trainerArrangeDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setTrainerReservationDao(
			BaseDaoI<TtrainerReservation> trainerReservationDao) {
		this.trainerReservationDao = trainerReservationDao;
	}

	@Autowired
	public void setTrainerReservationDetailDao(
			BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao) {
		this.trainerReservationDetailDao = trainerReservationDetailDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Autowired
	public void setUsingCarDao(BaseDaoI<TusingCar> usingCarDao) {
		this.usingCarDao = usingCarDao;
	}

	@SuppressWarnings("deprecation")
	@Override
	public TrainerArrange save(TrainerArrange trainerArrange)
			throws ParseException {
		// 1.数据模型转换
		Ttrainer trainer = null;
		TtrainerReservation trainerReservation = null;
		TtrainerArrange trainerArr = new TtrainerArrange();
		BeanUtils.copyProperties(trainerArrange, trainerArr,
				new String[] { "id" });
		String trainerArrangeId = UUID.randomUUID().toString();
		trainerArr.setId(trainerArrangeId);
		trainerArr.setNo(GeneraterIdByDateUtil.getGeneraterIdByDate());// 排班编号

		// 获取教练对象
		if (trainerArrange.getTrainerName() != null
				&& !trainerArrange.getTrainerName().trim().equals("")) {
			trainer = trainerDao.get(Ttrainer.class,
					trainerArrange.getTrainerId());
			if (trainer != null) {
				trainerArr.setTrainer(trainer);
				// 根据教练信息读取教练预约信息
				String hql = "from TtrainerReservation t where t.trainer.id=?";
				trainerReservation = (TtrainerReservation) trainerReservationDao
						.uniqueResult(hql, trainer.getId());
			}
		}
		if (trainerArrange.getArrangeDate() == null) {
			trainerArr.setArrangeDate(new Date());
		}

		// 3.根据排班信息创建当前教练的预约明细信息
		int year = Integer.parseInt(trainerArrange.getYear());
		int month = Integer.parseInt(trainerArrange.getMonth());

		// date得到当前月的第一天

		// 3.1预约日期
		Date d = null;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		// 从的后台读取该教练当前月排班日
		String arrangedDate = this.getArrangedDateByYMD(trainer.getId(),
				Integer.toString(year), Integer.toString(month));
		// 标记当前教练排班计划的第一天
		// 记录当前教练排班计划最后一天
		String arr[] = trainerArrange.getNoArrangeDates().split(",");
		Integer[] temp = StringUtil.stringToIntegerAndSort(arr);
		trainerArr.setFirstArrangeDate(format.parse(trainerArrange.getYear()
				+ "-" + trainerArrange.getMonth() + "-" + temp[0]));
		trainerArr
				.setLastArrangeDate(format.parse(trainerArrange.getYear() + "-"
						+ trainerArrange.getMonth() + "-"
						+ temp[temp.length - 1]));
		// 校验当前排班日期是否重复
		if (ValidateUtil.isValid(arrangedDate)) {
			if (this.checkDateArranged(trainerArrange.getNoArrangeDates(),
					arrangedDate)) {
				return null;
			}
		}

		// 根据月份创建教练预约明细信息模块（提供给学员选择预约页面）
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			for (String s : trainerArrange.getNoArrangeDates().split(",")) {
				// 创建教练预约明细信息模块（提供给学员选择预约页面）
				if (trainerReservation != null) {
					d = format.parse(trainerArrange.getYear() + "-"
							+ trainerArrange.getMonth() + "-" + s);

					TtrainerReservationDetail trainerReservationDetail = new TtrainerReservationDetail();
					String trainerReservationDetailId = UUID.randomUUID()
							.toString();

					trainerReservationDetail.setId(trainerReservationDetailId);
					trainerReservationDetail.setReservationDate(d);
					trainerReservationDetail.setSchoolArea(trainer
							.getSchoolArea());
					// 设置教练排班时段内容
					this.setTrainingTimeItem(trainerArrange,
							trainerReservationDetail);
					// 教练预约明细与教练预约关联
					trainerReservationDetail
							.setTrainerReservation(trainerReservation);
					trainerReservationDetail.setTrainerId(trainer.getId());

					// 排班类型设定
					if (trainerArrange.getWorkingType() != null) {
						trainerReservationDetail
								.setReservationType(trainerArrange
										.getWorkingType());
					}
					//教练排班时，设置每个时段的可预约总数为0
					trainerReservationDetail.setReservation7MaxTotal(0);
					trainerReservationDetail.setReservation8MaxTotal(0);
					trainerReservationDetail.setReservation9MaxTotal(0);
					trainerReservationDetail.setReservation10MaxTotal(0);
					trainerReservationDetail.setReservation11MaxTotal(0);
					trainerReservationDetail.setReservation12MaxTotal(0);
					trainerReservationDetail.setReservation13MaxTotal(0);
					trainerReservationDetail.setReservation14MaxTotal(0);
					trainerReservationDetail.setReservation15MaxTotal(0);
					trainerReservationDetail.setReservation16MaxTotal(0);
					trainerReservationDetail.setReservation17MaxTotal(0);
					trainerReservationDetail.setReservation18MaxTotal(0);
					trainerReservationDetail.setReservation19MaxTotal(0);
					trainerReservationDetail.setReservation20MaxTotal(0);
					trainerReservationDetail.setReservation21MaxTotal(0);
					
					trainerReservationDetail.setArrangeId(trainerArrangeId);

					trainerReservationDetailDao.save(trainerReservationDetail);
				}
			}

		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			for (String s : trainerArrange.getNoArrangeDates().split(",")) {
				// 创建教练预约明细信息模块（提供给学员选择预约页面）
				if (trainerReservation != null) {
					d = format.parse(trainerArrange.getYear() + "-"
							+ trainerArrange.getMonth() + "-" + s);

					TtrainerReservationDetail trainerReservationDetail = new TtrainerReservationDetail();
					String trainerReservationDetailId = UUID.randomUUID()
							.toString();

					trainerReservationDetail.setId(trainerReservationDetailId);
					trainerReservationDetail.setReservationDate(d);
					trainerReservationDetail.setSchoolArea(trainer
							.getSchoolArea());
					this.setTrainingTimeItem(trainerArrange,
							trainerReservationDetail);
					// 保持关联关系
					trainerReservationDetail
							.setTrainerReservation(trainerReservation);
					trainerReservationDetail.setTrainerId(trainer.getId());

					// 排班类型设定
					if (trainerArrange.getWorkingType() != null) {
						trainerReservationDetail
								.setReservationType(trainerArrange
										.getWorkingType());
					}
					//教练排班时，设置每个时段的可预约总数为0
					trainerReservationDetail.setReservation7MaxTotal(0);
					trainerReservationDetail.setReservation8MaxTotal(0);
					trainerReservationDetail.setReservation9MaxTotal(0);
					trainerReservationDetail.setReservation10MaxTotal(0);
					trainerReservationDetail.setReservation11MaxTotal(0);
					trainerReservationDetail.setReservation12MaxTotal(0);
					trainerReservationDetail.setReservation13MaxTotal(0);
					trainerReservationDetail.setReservation14MaxTotal(0);
					trainerReservationDetail.setReservation15MaxTotal(0);
					trainerReservationDetail.setReservation16MaxTotal(0);
					trainerReservationDetail.setReservation17MaxTotal(0);
					trainerReservationDetail.setReservation18MaxTotal(0);
					trainerReservationDetail.setReservation19MaxTotal(0);
					trainerReservationDetail.setReservation20MaxTotal(0);
					trainerReservationDetail.setReservation21MaxTotal(0);
					trainerReservationDetail.setArrangeId(trainerArrangeId);
					trainerReservationDetailDao.save(trainerReservationDetail);
				}

			}
		} else if (month == 2) {
			// just leapyear or not
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				for (String s : trainerArrange.getNoArrangeDates().split(",")) {
					// 创建教练预约明细信息模块（提供给学员选择预约页面）
					if (trainerReservation != null) {
						d = format.parse(trainerArrange.getYear() + "-"
								+ trainerArrange.getMonth() + "-" + s);

						TtrainerReservationDetail trainerReservationDetail = new TtrainerReservationDetail();
						String trainerReservationDetailId = UUID.randomUUID()
								.toString();

						trainerReservationDetail
								.setId(trainerReservationDetailId);
						trainerReservationDetail.setReservationDate(d);
						trainerReservationDetail.setSchoolArea(trainer
								.getSchoolArea());
						this.setTrainingTimeItem(trainerArrange,
								trainerReservationDetail);
						// 保持关联关系
						trainerReservationDetail
								.setTrainerReservation(trainerReservation);
						trainerReservationDetail.setTrainerId(trainer.getId());

						// 排班类型设定
						if (trainerArrange.getWorkingType() != null) {
							trainerReservationDetail
									.setReservationType(trainerArrange
											.getWorkingType());
						}
						//教练排班时，设置每个时段的可预约总数为0
						trainerReservationDetail.setReservation7MaxTotal(0);
						trainerReservationDetail.setReservation8MaxTotal(0);
						trainerReservationDetail.setReservation9MaxTotal(0);
						trainerReservationDetail.setReservation10MaxTotal(0);
						trainerReservationDetail.setReservation11MaxTotal(0);
						trainerReservationDetail.setReservation12MaxTotal(0);
						trainerReservationDetail.setReservation13MaxTotal(0);
						trainerReservationDetail.setReservation14MaxTotal(0);
						trainerReservationDetail.setReservation15MaxTotal(0);
						trainerReservationDetail.setReservation16MaxTotal(0);
						trainerReservationDetail.setReservation17MaxTotal(0);
						trainerReservationDetail.setReservation18MaxTotal(0);
						trainerReservationDetail.setReservation19MaxTotal(0);
						trainerReservationDetail.setReservation20MaxTotal(0);
						trainerReservationDetail.setReservation21MaxTotal(0);
						trainerReservationDetail.setArrangeId(trainerArrangeId);
						trainerReservationDetailDao
								.save(trainerReservationDetail);
					}
				}
			} else {// 非闰年
				for (String s : trainerArrange.getNoArrangeDates().split(",")) {
					if (trainerReservation != null) {
						d = format.parse(trainerArrange.getYear() + "-"
								+ trainerArrange.getMonth() + "-" + s);

						TtrainerReservationDetail trainerReservationDetail = new TtrainerReservationDetail();
						String trainerReservationDetailId = UUID.randomUUID()
								.toString();

						trainerReservationDetail
								.setId(trainerReservationDetailId);
						trainerReservationDetail.setReservationDate(d);
						trainerReservationDetail.setSchoolArea(trainer
								.getSchoolArea());
						this.setTrainingTimeItem(trainerArrange,
								trainerReservationDetail);
						// 保持关联关系
						trainerReservationDetail
								.setTrainerReservation(trainerReservation);
						trainerReservationDetail.setTrainerId(trainer.getId());

						// 排班类型设定
						if (trainerArrange.getWorkingType() != null) {
							trainerReservationDetail
									.setReservationType(trainerArrange
											.getWorkingType());
						}
						//教练排班时，设置每个时段的可预约总数为0
						trainerReservationDetail.setReservation7MaxTotal(0);
						trainerReservationDetail.setReservation8MaxTotal(0);
						trainerReservationDetail.setReservation9MaxTotal(0);
						trainerReservationDetail.setReservation10MaxTotal(0);
						trainerReservationDetail.setReservation11MaxTotal(0);
						trainerReservationDetail.setReservation12MaxTotal(0);
						trainerReservationDetail.setReservation13MaxTotal(0);
						trainerReservationDetail.setReservation14MaxTotal(0);
						trainerReservationDetail.setReservation15MaxTotal(0);
						trainerReservationDetail.setReservation16MaxTotal(0);
						trainerReservationDetail.setReservation17MaxTotal(0);
						trainerReservationDetail.setReservation18MaxTotal(0);
						trainerReservationDetail.setReservation19MaxTotal(0);
						trainerReservationDetail.setReservation20MaxTotal(0);
						trainerReservationDetail.setReservation21MaxTotal(0);
						trainerReservationDetail.setArrangeId(trainerArrangeId);
						trainerReservationDetailDao
								.save(trainerReservationDetail);
					}
				}
			}

		}

		// 2.保存数据
		trainerArrangeDao.save(trainerArr);
		// 3.2时间段落处理

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(trainerArr, trainerArrange);
		// 4.将转换后的数据返回给前台
		return trainerArrange;
	}

	@Override
	public TrainerArrange udpate(TrainerArrange trainerArrange) {
		// 1.数据模型转换
		TtrainerArrange t = new TtrainerArrange();
		// 不能教练员改名
		BeanUtils.copyProperties(trainerArrange, t, new String[] { "id", });
		t.setId(trainerArrange.getId());
		t.setNo(trainerArrange.getNo());
		if (ValidateUtil.isValid(trainerArrange.getTrainerId())) {
			Ttrainer trainer = trainerDao.get(Ttrainer.class,
					trainerArrange.getTrainerId());
			t.setTrainer(trainer);
		}

		// 2.保存数据
		trainerArrangeDao.update(t);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, trainerArrange);
		// 4.将转换后的数据返回给前台
		return trainerArrange;
	}

	@Override
	public void delete(String ids) {
		TtrainerReservation reservation = null;
		List<TtrainerReservationDetail> details = new ArrayList<TtrainerReservationDetail>(
				0);
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.1根据ID从数据库中查找数据
					TtrainerArrange arrange = trainerArrangeDao.get(
							TtrainerArrange.class, id);
					// 3.2删除该教练预约明细信息
					Ttrainer trainer = arrange.getTrainer();
					if (trainer != null) {
						String hql = "from TtrainerReservationDetail t where t.trainerId=?";
						List<Object> param = new ArrayList<Object>(0);
						param.add(trainer.getId());
						details = trainerReservationDetailDao.find(hql, param);
						if (details.size() > 0) {
							for (TtrainerReservationDetail t : details) {
								// 删除已经预约的标识
								delectObject(t.getReservationFieldId7());
								delectObject(t.getReservationFieldId8());
								delectObject(t.getReservationFieldId9());
								delectObject(t.getReservationFieldId10());
								delectObject(t.getReservationFieldId11());
								delectObject(t.getReservationFieldId12());
								delectObject(t.getReservationFieldId13());
								delectObject(t.getReservationFieldId14());
								delectObject(t.getReservationFieldId15());
								delectObject(t.getReservationFieldId16());
								delectObject(t.getReservationFieldId17());
								delectObject(t.getReservationFieldId18());
								delectObject(t.getReservationFieldId19());
								delectObject(t.getReservationFieldId20());
								delectObject(t.getReservationFieldId21());

								trainerReservationDetailDao.delete(t);
							}
						}

					}

					// 4.调用DAO层删除数据
					trainerArrangeDao.delete(arrange);
				}
			}

		}

	}

	private void delectObject(String reservationId) {
		if (ValidateUtil.isValid(reservationId)) {
			Treservation temp = reservationDao.get(Treservation.class,
					reservationId);
			if (temp != null) {
				reservationDao.delete(temp);
			}
			String hql = "from TusingCar t where t.reservationId=?";
			TusingCar useringCar = (TusingCar) usingCarDao.uniqueResult(hql,
					reservationId);
			if (useringCar != null) {
				usingCarDao.delete(useringCar);
			}
		}
		return;
	}

	@Override
	public DataGrid dataGrid(TrainerArrange trainerArrange) {
		DataGrid dataGrid = new DataGrid();

		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(trainerArrange)));

		// 设置总记录数
		dataGrid.setTotal(this.total(trainerArrange));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param trainerArrangeList
	 * @return
	 */
	private List<TrainerArrange> changedModel(
			List<TtrainerArrange> trainerArrangeList) {
		List<TrainerArrange> retArranges = new ArrayList<TrainerArrange>();
		if (trainerArrangeList != null && trainerArrangeList.size() > 0) {
			for (TtrainerArrange t : trainerArrangeList) {
				TrainerArrange trainerArrange = new TrainerArrange();
				BeanUtils.copyProperties(t, trainerArrange);
				Ttrainer trainer = t.getTrainer();
				String trainerNames = "";
				// 所属机构
				if (trainer != null) {
					Torganization org = organizationDao.get(
							Torganization.class, trainer.getSchoolArea());
					if (org != null) {
						trainerArrange.setSchoolArea(trainer.getSchoolArea());
						trainerArrange.setSchoolAreaId(org.getId());
						trainerArrange.setSchoolAreaName(org.getName());
					}
				}
				// 将字符串分割后进行排序
				String[] arrA = t.getNoArrangeDates().split(",");
				trainerArrange.setNoArrangeDates(StringUtil
						.intArrayToString(StringUtil
								.stringToIntegerAndSort(arrA)));
				trainerArrange.setTrainerId(trainer.getId());
				trainerArrange.setTrainerCodeNo(trainer.getCodeNo());
				trainerArrange.setTrainerName(trainerNames);
				trainerArrange.setTrainerIdentity(trainer.getIdentity());
				trainerArrange.setTrainerPhone(trainer.getPhone());
				trainerArrange.setArrangeType(trainer.getArrangeType());
				trainerArrange.setTrainerType(trainer.getTrainerType());
				retArranges.add(trainerArrange);
			}
		}
		return retArranges;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TtrainerArrange> find(TrainerArrange trainerArranged) {
		String hql = "from TtrainerArrange t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(trainerArranged, hql, paramList);
		if (trainerArranged.getSort() != null
				&& trainerArranged.getOrder() != null) {
			hql += " order by " + trainerArranged.getSort() + " "
					+ trainerArranged.getOrder();
		}
		return trainerArrangeDao.find(hql, paramList,
				trainerArranged.getPage(), trainerArranged.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(TrainerArrange trainerArrange) {
		// 拼接查询条件
		String hql = "select count(*) from TtrainerArrange t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(trainerArrange, hql, paramsList);
		return trainerArrangeDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param person
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(TrainerArrange trainerArrage, String hql,
			List<Object> params) {
		if (trainerArrage.getSchoolArea() != null
				&& !trainerArrage.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea=?";
			params.add(trainerArrage.getSchoolArea().trim());
		}
		// 根据教练名称查找教练的排班信息
		if (ValidateUtil.isValid(trainerArrage.getTrainerId())) {
			hql += " and t.trainer.id=?";
			params.add(trainerArrage.getTrainerId().trim());
		}
		if (trainerArrage.getTrainerName() != null
				&& !trainerArrage.getTrainerName().trim().equals("")) {
			hql += " and t.trainer.name like ?";
			params.add("%%" + trainerArrage.getTrainerName().trim() + "%%");
		}

		// TODO(查询功能细化)
		if (trainerArrage.getStartArrangeDate() != null) {
			hql += " and t.arrangeDate>=?";
			params.add(trainerArrage.getStartArrangeDate());
		}
		if (trainerArrage.getEndArrangeDate() != null) {
			hql += " and t.arrangeDate<=?";
			params.add(trainerArrage.getEndArrangeDate());
		}

		// 根据根据排班计划的第一天进行检索(为预约模块服务)
		if (trainerArrage.getFirstArrangeDateStart() != null) {
			hql += " and t.firstArrangeDate >=?";
			params.add(trainerArrage.getFirstArrangeDateStart());
		}
		if (trainerArrage.getFirstArrangeDateEnd() != null) {
			hql += " and t.firstArrangeDate <=?";
			params.add(trainerArrage.getFirstArrangeDateEnd());
		}
		// 根据根据排班计划的最后一天进行检索
		if (trainerArrage.getLastArrangeDateStart() != null) {
			hql += " and t.lastArrangeDate >=?";
			params.add(trainerArrage.getLastArrangeDateStart());
		}
		if (trainerArrage.getLastArrangeDateEnd() != null) {
			hql += " and t.lastArrangeDate <=?";
			params.add(trainerArrage.getLastArrangeDateEnd());
		}

		if (trainerArrage.getTeachingType() != null) {
			hql += " and t.teachingType =?";
			params.add(trainerArrage.getTeachingType());
		}
		if (trainerArrage.getWorkingType() != null) {
			hql += " and t.workingType =?";
			params.add(trainerArrage.getWorkingType());
		}

		if (ValidateUtil.isValid(trainerArrage.getTrainerIdentity())) {
			hql += " and t.trainer.identity =?";
			params.add(trainerArrage.getTrainerIdentity());
		}

		return hql;
	}

	private void setTrainingTimeItem(TrainerArrange trainerArranged,
			TtrainerReservationDetail trainerReservationDetail) {

		if (trainerArranged.getItemTime7flag() != null) {
			if (trainerArranged.getItemTime7flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem7Studentid("NO");
			}
			trainerReservationDetail.setItem7TrainContent(trainerArranged
					.getItemTime7flag());
		}
		if (trainerArranged.getItemTime8flag() != null) {
			if (trainerArranged.getItemTime8flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem8Studentid("NO");
			}
			trainerReservationDetail.setItem8TrainContent(trainerArranged
					.getItemTime8flag());
		}
		if (trainerArranged.getItemTime9flag() != null) {
			if (trainerArranged.getItemTime9flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem9Studentid("NO");
			}
			trainerReservationDetail.setItem9TrainContent(trainerArranged
					.getItemTime9flag());
		}
		if (trainerArranged.getItemTime10flag() != null) {
			if (trainerArranged.getItemTime10flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem10Studentid("NO");
			}
			trainerReservationDetail.setItem10TrainContent(trainerArranged
					.getItemTime10flag());
		}
		if (trainerArranged.getItemTime11flag() != null) {
			if (trainerArranged.getItemTime11flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem11Studentid("NO");
			}
			trainerReservationDetail.setItem11TrainContent(trainerArranged
					.getItemTime11flag());
		}
		if (trainerArranged.getItemTime12flag() != null) {
			if (trainerArranged.getItemTime12flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem12Studentid("NO");
			}
			trainerReservationDetail.setItem12TrainContent(trainerArranged
					.getItemTime12flag());
		}
		if (trainerArranged.getItemTime13flag() != null) {
			if (trainerArranged.getItemTime13flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem13Studentid("NO");
			}
			trainerReservationDetail.setItem13TrainContent(trainerArranged
					.getItemTime13flag());
		}
		if (trainerArranged.getItemTime14flag() != null) {
			if (trainerArranged.getItemTime14flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem14Studentid("NO");
			}
			trainerReservationDetail.setItem14TrainContent(trainerArranged
					.getItemTime14flag());
		}
		if (trainerArranged.getItemTime15flag() != null) {
			if (trainerArranged.getItemTime15flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem15Studentid("NO");
			}
			trainerReservationDetail.setItem15TrainContent(trainerArranged
					.getItemTime15flag());
		}
		if (trainerArranged.getItemTime16flag() != null) {
			if (trainerArranged.getItemTime16flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem16Studentid("NO");
			}
			trainerReservationDetail.setItem16TrainContent(trainerArranged
					.getItemTime16flag());
		}
		if (trainerArranged.getItemTime17flag() != null) {
			if (trainerArranged.getItemTime17flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem17Studentid("NO");
			}
			trainerReservationDetail.setItem17TrainContent(trainerArranged
					.getItemTime17flag());
		}
		if (trainerArranged.getItemTime18flag() != null) {
			if (trainerArranged.getItemTime18flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem18Studentid("NO");
			}
			trainerReservationDetail.setItem18TrainContent(trainerArranged
					.getItemTime18flag());
		}
		if (trainerArranged.getItemTime19flag() != null) {
			if (trainerArranged.getItemTime19flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem19Studentid("NO");
			}
			trainerReservationDetail.setItem19TrainContent(trainerArranged
					.getItemTime19flag());
		}
		if (trainerArranged.getItemTime20flag() != null) {
			if (trainerArranged.getItemTime20flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem20Studentid("NO");
			}
			trainerReservationDetail.setItem20TrainContent(trainerArranged
					.getItemTime20flag());
		}
		if (trainerArranged.getItemTime21flag() != null) {
			if (trainerArranged.getItemTime21flag() == 5) {// 该时段不排班
				trainerReservationDetail.setItem21Studentid("NO");
			}
			trainerReservationDetail.setItem21TrainContent(trainerArranged
					.getItemTime21flag());
		}

	}

	/**
	 * 从数据库中查找出该教练当前月已排班的天
	 * 
	 * @param trainerId
	 * @param year
	 * @param month
	 * @return
	 */
	private String getArrangedDateByYMD(String trainerId, String year,
			String month) {
		String hql = "from TtrainerArrange t where t.trainer.id=? and t.year=? and t.month=?";
		List<Object> params = new ArrayList<Object>();
		params.add(trainerId);
		params.add(year);
		params.add(month);
		List<TtrainerArrange> arranges = trainerArrangeDao.find(hql, params);
		if (arranges != null && arranges.size() > 0) {
			TtrainerArrange arrange = arranges.get(0);
			return arrange.getNoArrangeDates();
		}
		return null;
	}

	/**
	 * 比较两个字符串，如果相等返回true，否则返回false
	 * 
	 * @param source
	 * @param str
	 * @return
	 */
	private boolean checkDateArranged(String arr0, String arr1) {
		if (ValidateUtil.isValid(arr0)) {
			for (String s1 : arr0.split(",")) {
				for (String s2 : arr1.split(",")) {
					if (s1.trim().equals(s2.trim())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Date getNealyArrageDateByTrainerId(String trainerId) {
		if (!ValidateUtil.isValid(trainerId)) {
			return null;
		}
		String hql = "from TtrainerArrange t where t.trainer.id=? order by arrangeDate desc";
		List<Object> params = new ArrayList<Object>();
		params.add(trainerId);
		List<TtrainerArrange> retVals = trainerArrangeDao.find(hql, params);
		if (retVals.size() > 0) {
			return retVals.get(0).getArrangeDate();
		}
		return null;
	}

}
