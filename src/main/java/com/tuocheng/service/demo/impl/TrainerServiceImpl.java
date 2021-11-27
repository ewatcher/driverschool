package com.tuocheng.service.demo.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.components.ComboBox;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.Tclass;
import com.tuocheng.model.demo.TdriverLicense;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.TpersonalTiming;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerArrange;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.DriverLicense;
import com.tuocheng.pageModel.demo.Person;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerArrange;
import com.tuocheng.pageModel.demo.TrainerReservation;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.Encrypt;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 教练信息实体类service层实现类
 * 
 * @author MEI702
 * 
 */
@Service("trainerService")
public class TrainerServiceImpl implements TrainerSerivceI {

	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Torganization> orgDao;
	private BaseDaoI<TtrainerReservation> trainerReservationDao;
	private BaseDaoI<TtrainerArrange> arrangedDao;
	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TpersonalTiming> personalTimingDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<Treservation> reservationDao;
	private BaseDaoI<Tperson> personDao;

	@Autowired
	public void setPersonDao(BaseDaoI<Tperson> personDao) {
		this.personDao = personDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setOrgDao(BaseDaoI<Torganization> orgDao) {
		this.orgDao = orgDao;
	}

	@Autowired
	public void setTrainerReservationDao(BaseDaoI<TtrainerReservation> trainerReservationDao) {
		this.trainerReservationDao = trainerReservationDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setArrangedDao(BaseDaoI<TtrainerArrange> arrangedDao) {
		this.arrangedDao = arrangedDao;
	}

	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setPersonalTimingDao(BaseDaoI<TpersonalTiming> personalTimingDao) {
		this.personalTimingDao = personalTimingDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public Trainer save(Trainer trainer) {
		// 1.数制模型转换：action模型－－〉dao模型
		Ttrainer t = new Ttrainer();
		BeanUtils.copyProperties(trainer, t);

		// 2.设置转换后模型数据的相关属性
		t.setId(UUID.randomUUID().toString());
		if (trainer.getJionDay() != null) {
			t.setJionDay(new Date());
		}
		t.setCarBanding(0);
		//将教练排班类型为其它
		t.setArrangeType(Ttrainer.ARRANGETYPE_OTHER);
		// 将教练员作为业务员存储
		Tperson person = new Tperson();
		person.setId(UUID.randomUUID().toString());
		person.setPropertyType(1);
		person.setName(trainer.getName());
		person.setSex(trainer.getSex());
		person.setIdentityId(trainer.getIdentity());
		person.setPhone(trainer.getPhone());
		if (ValidateUtil.isValid(trainer.getSchoolArea())) {
			Torganization organization = organizationDao.get(Torganization.class, trainer.getSchoolArea());
			person.setOrganization(organization);
		}
		person.setOperator(trainer.getOperator());
		personDao.save(person);
		trainerDao.save(t);

		// 4.1创建当前教练预约信息
		TtrainerReservation trainerReservation = new TtrainerReservation();
		trainerReservation.setId(UUID.randomUUID().toString());
		trainerReservation.setFiveItem(0);
		trainerReservation.setRoadExam(0);
		trainerReservation.setSchoolArea(t.getSchoolArea());
		trainerReservation.setTrainer(t);
		trainerReservationDao.saveOrUpdate(trainerReservation);

		// 5.将保存成功后的数据传递到前台
		BeanUtils.copyProperties(t, trainer);
		return trainer;
	}

	@Override
	public Trainer update(Trainer trainer) {
		// 1.数制模型转换：action模型－－〉dao模型
		Ttrainer t = new Ttrainer();
		BeanUtils.copyProperties(trainer, t, new String[] { "id" });

		// 2.设置转换后模型数据的相关属性
		t.setId(trainer.getId());
		if (trainer.getJionDay() != null) {
			t.setJionDay(new Date());
		}
		// 3.将模型数据保存到数据库
		// 3.1更新教练信息
		trainerDao.update(t);
		// 3.2更新教练预约中的教练ID
		String hql = " from TtrainerReservation t where t.trainer.id=?";
		TtrainerReservation tReservation = (TtrainerReservation) trainerReservationDao.uniqueResult(hql, t.getId());
		if (tReservation != null) {
			tReservation.setSchoolArea(t.getSchoolArea());
			trainerReservationDao.update(tReservation);
		}

		// 4.将保存成功后的数据传递到前台
		BeanUtils.copyProperties(t, trainer);
		return trainer;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					Ttrainer t = trainerDao.get(Ttrainer.class, id);
					// 解除与教练有关联关系的实体:如车辆，学员等等
					String hql = "from Tcar t where t.trainer.id=?";
					List<Object> params = new ArrayList<Object>();
					params.add(t.getId());
					List<Tcar> cars = carDao.find(hql, params);
					if (cars.size() > 0) {
						for (Tcar tcar : cars) {
							if (tcar.getTrainer() != null) {
								tcar.setTrainer(null);
								carDao.update(tcar);
							}
						}
					}
					// 解除教练与学员关联
					String stuHql = "from Tstudent t where t.trainer.id=?";
					List<Tstudent> students = studentDao.find(stuHql, params);
					if (students.size() > 0) {
						for (Tstudent student : students) {
							if (student.getTrainer() != null) {
								student.setTrainer(null);
								studentDao.update(student);
							}
						}
					}
					// 解除教练与教练排班关联
					String arrHql = "from TtrainerArrange t where t.trainer.id=?";
					List<TtrainerArrange> arranges = arrangedDao.find(arrHql, params);
					if (arranges.size() > 0) {
						for (TtrainerArrange ttrainerArrange : arranges) {
							if (ttrainerArrange.getTrainer() != null) {
								arrangedDao.delete(ttrainerArrange);
							}
						}
					}
					// 解除教练与教练预约关联
					String reservationHql = "from TtrainerReservation t where t.trainer.id=?";
					List<TtrainerReservation> reservations = trainerReservationDao.find(reservationHql, params);
					if (reservations.size() > 0) {
						for (TtrainerReservation ttrainerReservation : reservations) {
							if (ttrainerReservation.getTrainer() != null) {
								trainerReservationDao.delete(ttrainerReservation);
							}
						}
					}
					// 解除学员个人学时与教练的关联
					String personTimingHql = "from TpersonalTiming t where t.trainer.id=?";
					List<TpersonalTiming> personTimings = personalTimingDao.find(personTimingHql, params);
					if (personTimings.size() > 0) {
						for (TpersonalTiming tpersonalTiming : personTimings) {
							if (tpersonalTiming.getTrainer() != null) {
								tpersonalTiming.setTrainer(null);
								personalTimingDao.update(tpersonalTiming);
							}
						}
					}

					// 删除当前教练的用记信息
					if (t.getUser() != null) {
						userDao.delete(t.getUser());
					}

					// 4.调用DAO层删除数据
					trainerDao.delete(t);
				}
			}
		}
	}

	@Override
	public DataGrid datagrid(Trainer trainer) {
		// fixTrainerToPerson();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(this.changeDaoModelToActionModel(this.find(trainer)));
		dataGrid.setTotal(this.total(trainer));
		return dataGrid;
	}

	public DataGrid personalDatagrid(Trainer trainer) {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(trainer.getIdentity())) {
			// 1.根据用登录信息查找学员对象
			String hql = "from Ttrainer t where t.identity=?";
			Ttrainer tra = (Ttrainer) trainerDao.uniqueResult(hql, trainer.getIdentity().trim());
			if (tra != null) {
				// 2.根据学员对象查找该学员的驾驶证信息
				Trainer entry = new Trainer();
				BeanUtils.copyProperties(tra, entry);
				// 3.组织返回参数
				List<Trainer> retStudents = new ArrayList<Trainer>();
				entry.setSchoolArea(orgDao.get(Torganization.class, tra.getSchoolArea().trim()).getName());
				retStudents.add(entry);
				// 4.将参数组成Datagrid数据模型传递给前台
				dataGrid.setRows(retStudents);
				dataGrid.setTotal(1L);
				return dataGrid;
			}
		}
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param trainers
	 * @return
	 */
	private List<Trainer> changeDaoModelToActionModel(List<Ttrainer> trainers) {
		List<Trainer> trianerList = new ArrayList<Trainer>();
		if (trainers != null && trainers.size() > 0) {
			for (Ttrainer ts : trainers) {
				Trainer trainer = new Trainer();
				BeanUtils.copyProperties(ts, trainer);

				trainer.setSchoolAreaName(orgDao.get(Torganization.class, trainer.getSchoolArea()).getName());

				trianerList.add(trainer);
			}
		}
		return trianerList;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param trainer
	 * @return
	 */
	private List<Ttrainer> find(Trainer trainer) {
		String hql = "from Ttrainer t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(trainer, hql, values);

		if (trainer.getSort() != null && trainer.getOrder() != null) {
			hql += " order by " + trainer.getSort() + " " + trainer.getOrder();
		}
		return trainerDao.find(hql, values, trainer.getPage(), trainer.getRows());
	}

	private Long total(Trainer trainer) {
		String hql = "select count(*) from Ttrainer t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(trainer, hql, values);
		return trainerDao.count(hql, values);
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param trainer
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Trainer trainer, String hql, List<Object> params) {
		// 姓名查询：模糊查询
		if (trainer.getName() != null && !trainer.getName().trim().equals("")) {
			hql += " and t.name like ? ";
			params.add("%%" + trainer.getName().trim() + "%%");
		}
		if (trainer.getIdentity() != null && !trainer.getIdentity().trim().equals("")) {
			hql += " and t.identity = ?";
			params.add(trainer.getIdentity().trim());
		}
		if (trainer.getCodeNo() != null && !trainer.getCodeNo().trim().equals("")) {
			hql += " and t.codeNo = ?";
			params.add(trainer.getCodeNo().trim());
		}
		if (trainer.getDriverLicenseId() != null && !trainer.getDriverLicenseId().trim().equals("")) {
			hql += " and t.driverLicenseId = ?";
			params.add(trainer.getDriverLicenseId().trim());
		}
		// 准驾类型
		if (trainer.getDriverType() != null) {
			hql += " and t.driverType = ?";
			params.add(trainer.getDriverType());
		}

		// 驾驶证有效日期(范围查询)
		if (trainer.getDriverValidatyBegin() != null) {
			hql += " and t.driverValidatyEnd>=? ";
			params.add(trainer.getDriverValidatyBegin());
		}
		if (trainer.getDriverValidatyEndEnd() != null) {
			hql += " and t.driverValidatyEnd<=? ";
			params.add(trainer.getDriverValidatyEndEnd());
		}

		// 根据准教类开开型查询
		if (trainer.getDriverCarType() != null && !trainer.getDriverCarType().trim().equals("")) {
			hql += " and t.driverType <= ? ";
			params.add(Integer.parseInt(trainer.getDriverCarType()));
		}

		// 所属校区查询
		if (trainer.getSchoolArea() != null && !trainer.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea= ? ";
			params.add(trainer.getSchoolArea().trim());
		}

		if (trainer.getCarBanding() != null) {
			hql += " and t.carBanding= ? ";
			params.add(trainer.getCarBanding());
		}
		// 教练类别查询
		if (trainer.getTrainerType() != null) {
			hql += " and t.trainerType= ? ";
			params.add(trainer.getTrainerType());
		}
		if (trainer.getArrangeType() != null && trainer.getArrangeType() > 0) {
			hql += " and t.arrangeType= ? ";
			params.add(trainer.getArrangeType());
		}
		// 教练是否排班
		if (trainer.getArrangeFlag() != null && trainer.getArrangeFlag() > 0) {
			hql += " and t.arrangeFlag= ? ";
			params.add(trainer.getArrangeFlag());
		}

		return hql;
	}

	@Override
	public List<TrainerJson> getAllTrainerNameByJson() {
		List<TrainerJson> retList = new ArrayList<TrainerJson>();
		// 从数据库中查找出所有教练信息
		List<Ttrainer> trainers = trainerDao.find("from Ttrainer");
		// 对数据进行遍历，并以TrainerJson的格式返回
		if (trainers != null && trainers.size() > 0) {
			for (Ttrainer t : trainers) {
				TrainerJson json = new TrainerJson();
				json.setValue(t.getId());
				json.setText(t.getName());
				retList.add(json);
			}
		}
		return retList;
	}

	@Override
	public Ttrainer get(String id) {
		String hql = "from Ttrainer t where t.id = ? ";

		List<Object> values = new ArrayList<Object>();
		values.add(id);

		return trainerDao.get(hql, values);
	}

	public List<ComboboxJson> getAllTrainersForCombobox(String schoolArea) {
		List<ComboboxJson> retJson = new ArrayList<ComboboxJson>();
		String hql = "from Ttrainer t where t.schoolArea=?";
		List<Object> param = new ArrayList<Object>();
		param.add(schoolArea);
		List<Ttrainer> trainers = trainerDao.find(hql, param);

		if (trainers != null && trainers.size() > 0) {
			for (Ttrainer t : trainers) {
				ComboboxJson json = new ComboboxJson();
				json.setValue(t.getId());
				json.setText(t.getName());
				retJson.add(json);
			}
		}
		return retJson;
	}

	@Override
	public Map<String, Long> getTrainerBySchoolArea(String schoolIds) {
		Map<String, Long> retMap = new HashMap<String, Long>(0);
		if (schoolIds != null) {
			for (String s : schoolIds.split(",")) {
				Trainer trainer = new Trainer();
				trainer.setSchoolArea(s);
				Long total = this.total(trainer);
				retMap.put(s, total);
			}
		}

		return retMap;
	}

	@Override
	public Long getAllTrainerCount() {
		Trainer trainer = new Trainer();
		return this.total(trainer);
	}

	/**
	 * 根据驾照类型统计教练总人数
	 * 
	 * @return
	 */
	public Map<String, Map<Integer, Long>> getCountByDriverType(String schoolIds) {

		Map<String, Map<Integer, Long>> retMap = new HashMap<String, Map<Integer, Long>>(0);

		if (ValidateUtil.isValid(schoolIds)) {
			String[] schoolArea = schoolIds.split(",");
			for (String s : schoolArea) {
				Map<Integer, Long> countMap = new HashMap<Integer, Long>();
				Trainer trainer1 = new Trainer();
				trainer1.setSchoolArea(s);
				trainer1.setDriverType(Ttrainer.DRIVERTYPE_A1);
				countMap.put(Ttrainer.DRIVERTYPE_A1, this.total(trainer1));

				Trainer trainer2 = new Trainer();
				trainer2.setSchoolArea(s);
				trainer2.setDriverType(Ttrainer.DRIVERTYPE_A2);
				countMap.put(Ttrainer.DRIVERTYPE_A2, this.total(trainer2));

				Trainer trainer3 = new Trainer();
				trainer3.setSchoolArea(s);
				trainer3.setDriverType(Ttrainer.DRIVERTYPE_A3);
				countMap.put(Ttrainer.DRIVERTYPE_A3, this.total(trainer3));

				Trainer trainer4 = new Trainer();
				trainer4.setSchoolArea(s);
				trainer4.setDriverType(Ttrainer.DRIVERTYPE_B1);
				countMap.put(Ttrainer.DRIVERTYPE_B1, this.total(trainer4));

				Trainer trainer5 = new Trainer();
				trainer5.setSchoolArea(s);
				trainer5.setDriverType(Ttrainer.DRIVERTYPE_B2);
				countMap.put(Ttrainer.DRIVERTYPE_B2, this.total(trainer5));

				Trainer trainer6 = new Trainer();
				trainer6.setSchoolArea(s);
				trainer6.setDriverType(Ttrainer.DRIVERTYPE_C1);
				countMap.put(Ttrainer.DRIVERTYPE_C1, this.total(trainer6));

				Trainer trainer7 = new Trainer();
				trainer7.setSchoolArea(s);
				trainer7.setDriverType(Ttrainer.DRIVERTYPE_C2);
				countMap.put(Ttrainer.DRIVERTYPE_C2, this.total(trainer7));

				Trainer trainer8 = new Trainer();
				trainer8.setSchoolArea(s);
				trainer8.setDriverType(Ttrainer.DRIVERTYPE_C3);
				countMap.put(Ttrainer.DRIVERTYPE_C3, this.total(trainer8));

				Trainer trainer9 = new Trainer();
				trainer9.setSchoolArea(s);
				trainer9.setDriverType(Ttrainer.DRIVERTYPE_C4);
				countMap.put(Ttrainer.DRIVERTYPE_C4, this.total(trainer9));
				// 返回数制格式：map<schoolid <type,totals>>
				retMap.put(s, countMap);
			}
		}

		return retMap;
	}

	public boolean identityIdExistOrNot(String identityId) {
		String hql = "select count(*) from Ttrainer t where t.identity=?";
		Long temp = (Long) trainerDao.uniqueResult(hql, identityId);
		return temp != 0;
	}

	@Override
	public Ttrainer getTrainerByNameAndKey(String name, String keyStr) throws Exception {
		// 1.根据学员状态从数据库中查找出学员信息
		String sql = " select *  from tb_trainers  where name = ? and identity = ?";
		List<Ttrainer> trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, name, keyStr);
		if (trainers != null && trainers.size() > 0) {
			return trainers.get(0);
		}
		return null;
	}

	@Override
	public String getBiguestTrainerNO(User user) {
		String sql = " select * from tb_trainers where schoolArea=?  order by codeNo desc";
		List<Ttrainer> trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, user.getSchoolArea());
		if (trainers.size() > 0) {
			String str = trainers.get(0).getCodeNo();
			String value = Util.getPatternValue(str, "\\D+(\\d+)", 34, 1);
			String start = Util.getPatternValue(str, "(\\D+)\\d+", 34, 1);
			Integer temp = Util.objToInt(value, 0);
			return start + toAddByString(temp + 1);
		} else {
			return StringUtil.getFirstLetterBySchoolArea(user.getSchoolArea()) + "0001";
		}
	}

	private String toAddByString(Integer value) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(4);// 编号暂时定为四位
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		// 输出测试语句
		return nf.format(value);
	}

	@Override
	public boolean trainerCarUsingOrNot(String trainerId) {
		String sql = "select * from tb_cars where trainerId=?";
		List<Tcar> retVals = carDao.findAllBySQL(Tcar.class, sql, trainerId);
		if (retVals != null && retVals.size() > 0) {
			Tcar car = retVals.get(0);
			if (car != null && car.getUsingState() == Tcar.CAR_USING) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Ttrainer getSingleById(String trainerId) {
		if (!ValidateUtil.isValid(trainerId)) {
			return null;
		}
		Ttrainer trainer = trainerDao.get(Ttrainer.class, trainerId);
		if (trainer != null) {
			return trainer;
		}
		return null;
	}

	@Override
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName)
			throws Exception {
		ImportReturnModel model = new ImportReturnModel();
		String trainerNo = "";
		String identity = "";
		boolean repeatFlag = false;

		List<Trainer> reVals = new ArrayList<Trainer>();
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {
			Trainer entry = new Trainer();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				Cell cell = row.getCell(j);
				String cellValue = StringUtil.getCellValueByCellType(cell);

				switch (j) {// 通过列数来判断对应插如的字段
				case 0:// 编号
				{
					if (trainerNoExistOrNot(cellValue)) {
						repeatFlag = true;
						trainerNo = trainerNo + ", " + cellValue;
					}
					entry.setCodeNo(cellValue);
				}
					break;
				case 1:// 姓名
					entry.setName(cellValue);
					break;
				case 2: // 性别
					entry.setSex(cellValue);
					break;
				case 3:// 身份证
				{
					if (identityExistOrNot(cellValue)) {
						repeatFlag = true;
						identity = identity + ", " + cellValue;
					}
					entry.setIdentity(cellValue);
				}
					break;
				case 4:// 地址
					entry.setAddres(cellValue);
					break;
				case 5:// 准驾类型
				{
					entry.setDriverType(StringUtil.getCarTypeValue(cellValue));
					entry.setDriverCarType(cellValue);
				}
					break;
				case 6:// 驾驶证ID
					entry.setDriverLicenseId(cellValue);
					break;
				case 7:// 手机
					entry.setPhone(cellValue);
					break;
				case 8:// 出生日期
					entry.setBirthday(DateUtil.getDateByString(cellValue));
					break;
				case 9:// 入职日期
					entry.setJionDay(DateUtil.getDateByString(cellValue));
					break;
				}// end switch
			}
			reVals.add(entry);

		}
		// 导入数据
		if (reVals.size() > 0 && repeatFlag == false) {
			for (Trainer entry : reVals) {
				entry.setCarBanding(0);// 默认教练没有与车辆绑定
				entry.setArrangeFlag(0);// 默认教练没有排班
				entry.setSchoolArea(schoolArea);
				entry.setOperator(operatorName);
				this.save(entry);
			}
		}
		// 组织返回参数
		model.setMsg(trainerNo + identity);
		model.setSize(reVals.size());
		if (repeatFlag) {
			model.setSuccess(false);
		} else {
			model.setSuccess(true);
		}
		return model;
	}

	private boolean trainerNoExistOrNot(String codeNo) {
		String hql = "select count(*) from Ttrainer t where t.codeNo=?";
		Long temp = (Long) studentDao.uniqueResult(hql, codeNo);
		return temp != 0;
	}

	private boolean identityExistOrNot(String identity) {
		String hql = "select count(*) from Ttrainer t where t.identity=?";
		Long temp = (Long) studentDao.uniqueResult(hql, identity);
		return temp != 0;
	}

	@Override
	public Ttrainer getRandomTrainer(String schoolArea) throws Exception {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}

		String sql = "select * from tb_trainers t where schoolArea=?";
		List<Ttrainer> trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, schoolArea);
		if (ValidateUtil.isValid(trainers)) {
			Random rd = new Random();
			Ttrainer trainer = trainers.get(rd.nextInt(trainers.size()));
			if (trainer != null) {
				return trainer;
			}
		}
		return null;
	}

	// 根据校区标识统计各校区教练总数
	public Map<String, List<ComboboxJson>> getTtrainerTotalByOrg() {
		Map<String, List<ComboboxJson>> retVal = new HashMap<String, List<ComboboxJson>>();
		ComboboxJson json = null;
		ComboboxJson insideJson = null;
		ComboboxJson outJson = null;
		List<ComboboxJson> orgList = new ArrayList<ComboboxJson>();
		List<ComboboxJson> insideList = new ArrayList<ComboboxJson>();
		List<ComboboxJson> outList = new ArrayList<ComboboxJson>();
		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();
		String sql = "select count(*) from tb_trainers where schoolArea=?";
		String insideSql = "select count(*) from tb_trainers where schoolArea=? and trainerType='0'";
		String outSql = "select count(*) from tb_trainers where schoolArea=? and trainerType='1'";
		for (ComboboxJson org : orgs) {
			// 3.1根据校区按日统计
			json = new ComboboxJson();
			json.setText(org.getValue());
			json.setValue(trainerDao.countBySQL(sql, org.getValue()).toString());
			orgList.add(json);

			insideJson = new ComboboxJson();
			insideJson.setText(org.getValue());
			insideJson.setValue(trainerDao.countBySQL(insideSql, org.getValue()).toString());
			insideList.add(insideJson);

			outJson = new ComboboxJson();
			outJson.setText(org.getValue());
			outJson.setValue(trainerDao.countBySQL(outSql, org.getValue()).toString());
			outList.add(outJson);
		}
		retVal.put("orgs", orgList);
		retVal.put("insideTotal", insideList);
		retVal.put("outTotal", outList);
		return retVal;
	}

	// 根据校区标识统计各校区教练总数
	public List<ComboboxJson> getTtrainerTotalByType() {
		List<ComboboxJson> retVal = new ArrayList<ComboboxJson>();
		ComboboxJson json = null;

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();
		String sql = "select count(*) from tb_trainers where schoolArea=?";
		for (ComboboxJson org : orgs) {
			// 3.1根据校区按日统计
			json = new ComboboxJson();
			json.setText(org.getValue());
			json.setValue(trainerDao.countBySQL(sql, org.getValue()).toString());
			retVal.add(json);
		}
		return retVal;
	}

	// 根据校区标识统计各校区教练总数
	public Map<String, List<ComboboxJson>> getTtrainerDatasByOrg() {
		Map<String, List<ComboboxJson>> retVal = new HashMap<String, List<ComboboxJson>>();
		List<Ttrainer> trainers = null;
		List<ComboboxJson> nameMap = new ArrayList<ComboboxJson>();
		List<ComboboxJson> codeNoMap = new ArrayList<ComboboxJson>();
		List<ComboboxJson> typeMap = new ArrayList<ComboboxJson>();
		List<ComboboxJson> studentTotalMap = new ArrayList<ComboboxJson>();
		List<ComboboxJson> reservationTotalMap = new ArrayList<ComboboxJson>();
		String temp = null;

		String sql = "select * from tb_trainers where schoolArea=? order by codeNo asc";
		String stuSql = "select count(*) from tb_student where organizationId=? and trainerId=?";
		String strainerSql = "select count(*) from tb_reservations where schoolArea=? and trainerId=?";
		ComboboxJson names = null;
		ComboboxJson codeNo = null;
		ComboboxJson reservationTotal = null;
		ComboboxJson studentTotal = null;
		ComboboxJson trainerType = null;

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();
		for (ComboboxJson org : orgs) {
			// 3.1根据校区按日统计
			trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, org.getValue());
			if (trainers != null && trainers.size() > 0) {
				for (Ttrainer trainer : trainers) {
					names = new ComboboxJson();
					codeNo = new ComboboxJson();
					reservationTotal = new ComboboxJson();
					studentTotal = new ComboboxJson();
					trainerType = new ComboboxJson();
					// 教练姓名// 教练编号
					names.setText(trainer.getName());
					names.setValue(org.getValue());
					nameMap.add(names);

					codeNo.setValue(org.getValue());
					temp = trainer.getCodeNo();
					codeNo.setText(temp.substring(temp.length() - 3, temp.length()));
					codeNoMap.add(codeNo);
					// 教练性质
					if (0 == trainer.getTrainerType()) {
						trainerType.setText(org.getValue());
						trainerType.setValue("内部");
						studentTotal.setText(org.getValue());
						studentTotal.setValue("0");
					} else {
						trainerType.setText(org.getValue());
						trainerType.setValue("外包");
						studentTotal.setText(org.getValue());
						studentTotal
								.setValue(studentDao.countBySQL(stuSql, org.getValue(), trainer.getId()).toString());
						// 教练所带员总数
					}
					studentTotalMap.add(studentTotal);
					typeMap.add(trainerType);

					reservationTotal.setValue(org.getValue());

					reservationTotal.setText(
							reservationDao.countBySQL(strainerSql, org.getValue(), trainer.getId()).toString());
					reservationTotalMap.add(reservationTotal);
				}
			}
		}
		retVal.put("trainerName", nameMap);
		retVal.put("trainerType", typeMap);
		retVal.put("codeNo", codeNoMap);
		retVal.put("studentTotal", studentTotalMap);
		retVal.put("reservationTotal", reservationTotalMap);
		return retVal;
	}

	// 分别查找系统中电车，模拟的教练信息
	public Map<String, List<Ttrainer>> getElectricAndSimulationTrainers(String schoolArea) {
		Map<String, List<Ttrainer>> retMap = new HashMap<String, List<Ttrainer>>();
		String sql = "select * from tb_trainers where arrangeType=? and schoolArea=?";
		List<Ttrainer> electricTrainers = trainerDao.findAllBySQL(Ttrainer.class, sql, Ttrainer.ARRANGETYPE_ELECTRIC,
				schoolArea);
		List<Ttrainer> simulationcTrainers = trainerDao.findAllBySQL(Ttrainer.class, sql,
				Ttrainer.ARRANGETYPE_SIMULATION, schoolArea);
		if (electricTrainers != null && electricTrainers.size() > 0) {
			retMap.put("electricTrainer", electricTrainers);
		}
		if (simulationcTrainers != null && simulationcTrainers.size() > 0) {
			retMap.put("simulationTrainer", simulationcTrainers);
		}
		if (retMap.size() > 0) {
			return retMap;
		}
		return null;
	}

	public Ttrainer getSimulateOrElectricTrainer(Integer reservationType, String schoolArea) throws Exception {
		if (reservationType == null || reservationType <= 0) {
			return null;
		}
		String sql = "select * from tb_trainers where arrangeType=? and schoolArea=?";
		List<Ttrainer> trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, reservationType, schoolArea);
		if (trainers != null && trainers.size() > 0) {
			return trainers.get(0);
		}
		return null;
	}

	// 按排序获取校区标识
	private List<ComboboxJson> getOrganizationIds() {
		List<ComboboxJson> retLists = new ArrayList<ComboboxJson>();
		String sql = "select * from tb_organizations  where prentId is not null order by sequence asc";
		List<Torganization> orgs = organizationDao.findAllBySQL(Torganization.class, sql);
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

	public void fixTrainerToPerson() {
		String sql = "select * from tb_trainers where 1=?";
		List<Ttrainer> trainers = trainerDao.findAllBySQL(Ttrainer.class, sql, 1);
		if (ValidateUtil.isValidListObject(trainers)) {
			Tperson person = null;
			for (Ttrainer trainer : trainers) {
				person = new Tperson();
				person.setId(trainer.getId());
				person.setPropertyType(1);
				person.setName(trainer.getName());
				person.setSex(trainer.getSex());
				person.setIdentityId(trainer.getIdentity());
				person.setPhone(trainer.getPhone());
				if (ValidateUtil.isValid(trainer.getSchoolArea())) {
					Torganization organization = organizationDao.get(Torganization.class, trainer.getSchoolArea());
					person.setOrganization(organization);
				}
				person.setOperator(trainer.getOperator());
				personDao.save(person);
			}

		}
	}

}
