package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.jboss.logging.Cause;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphbuilder.math.func.Function;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TautoCarArrange;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TautoCarArrange;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.AutoCarArrange;
import com.tuocheng.service.demo.AutoCarArrangeServiceI;
import com.tuocheng.service.demo.TrainerArrangeServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 自动档排班管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("autoCarArrangeService")
public class AutoCarArrangeServiceImpl implements AutoCarArrangeServiceI {

	private BaseDaoI<TautoCarArrange> autoCarArrangeDao;
	private BaseDaoI<Tcar> carDao;

	@Autowired
	public void setAutoCarArrangeDao(BaseDaoI<TautoCarArrange> autoCarArrangeDao) {
		this.autoCarArrangeDao = autoCarArrangeDao;
	}

	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean save(AutoCarArrange autoCarArrange) throws ParseException {
		String arr[] = autoCarArrange.getNoArrangeDates().split(",");
		Date d = null;
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		TautoCarArrange entry = null;
		// 校验当前排班日期是否重复
		if (ValidateUtil.isValid(arr)) {
			for (String str : arr) {
				d = format.parse(autoCarArrange.getYear() + "-" + autoCarArrange.getMonth() + "-" + str);
				if (!checkArrangedDateByYMD(autoCarArrange.getSchoolArea(), d, autoCarArrange.getCarId())) {
					entry = new TautoCarArrange();
					BeanUtils.copyProperties(autoCarArrange, entry, new String[] { "id" });
					entry.setId(UUID.randomUUID().toString());
					entry.setArrangeDate(d);
					autoCarArrangeDao.save(entry);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public AutoCarArrange udpate(AutoCarArrange autocarArrange) {
		if (!ValidateUtil.isValid(autocarArrange.getId())) {
			return null;
		}
		TautoCarArrange entry = autoCarArrangeDao.get(TautoCarArrange.class, autocarArrange.getId());
		if (entry != null) {
			// 自动档排班仅修改某个时段的车辆排班启用状态
			if (autocarArrange.getItemTime8() != null) {
				entry.setItemTime8(autocarArrange.getItemTime8());
			}
			if (autocarArrange.getItemTime9() != null) {
				entry.setItemTime9(autocarArrange.getItemTime9());
			}
			if (autocarArrange.getItemTime10() != null) {
				entry.setItemTime10(autocarArrange.getItemTime10());
			}
			if (autocarArrange.getItemTime11() != null) {
				entry.setItemTime11(autocarArrange.getItemTime11());
			}
			if (autocarArrange.getItemTime12() != null) {
				entry.setItemTime12(autocarArrange.getItemTime12());
			}
			if (autocarArrange.getItemTime13() != null) {
				entry.setItemTime13(autocarArrange.getItemTime13());
			}
			if (autocarArrange.getItemTime14() != null) {
				entry.setItemTime14(autocarArrange.getItemTime14());
			}
			if (autocarArrange.getItemTime15() != null) {
				entry.setItemTime15(autocarArrange.getItemTime15());
			}
			if (autocarArrange.getItemTime16() != null) {
				entry.setItemTime16(autocarArrange.getItemTime16());
			}
			if (autocarArrange.getItemTime17() != null) {
				entry.setItemTime17(autocarArrange.getItemTime17());
			}
			if (autocarArrange.getItemTime18() != null) {
				entry.setItemTime18(autocarArrange.getItemTime18());
			}
			if (autocarArrange.getItemTime19() != null) {
				entry.setItemTime19(autocarArrange.getItemTime19());
			}
			if (autocarArrange.getItemTime20() != null) {
				entry.setItemTime20(autocarArrange.getItemTime20());
			}
			if (autocarArrange.getItemTime21() != null) {
				entry.setItemTime21(autocarArrange.getItemTime21());
			}
			// 确保标志位值不能为空
			if (entry.getItemTime8Flag() == null) {
				entry.setItemTime8Flag(0);
			} else {
				entry.setItemTime8Flag(autocarArrange.getItemTime8Flag());
			}
			if (entry.getItemTime9Flag() == null) {
				entry.setItemTime9Flag(0);
			} else {
				entry.setItemTime9Flag(autocarArrange.getItemTime9Flag());
			}
			if (entry.getItemTime10Flag() == null) {
				entry.setItemTime10Flag(0);
			} else {
				entry.setItemTime10Flag(autocarArrange.getItemTime10Flag());
			}
			if (entry.getItemTime11Flag() == null) {
				entry.setItemTime11Flag(0);
			} else {
				entry.setItemTime11Flag(autocarArrange.getItemTime11Flag());
			}
			if (entry.getItemTime12Flag() == null) {
				entry.setItemTime12Flag(0);
			} else {
				entry.setItemTime12Flag(autocarArrange.getItemTime12Flag());
			}
			if (entry.getItemTime13Flag() == null) {
				entry.setItemTime13Flag(0);
			} else {
				entry.setItemTime13Flag(autocarArrange.getItemTime13Flag());
			}
			if (entry.getItemTime14Flag() == null) {
				entry.setItemTime14Flag(0);
			} else {
				entry.setItemTime14Flag(autocarArrange.getItemTime14Flag());
			}
			if (entry.getItemTime15Flag() == null) {
				entry.setItemTime15Flag(0);
			} else {
				entry.setItemTime15Flag(autocarArrange.getItemTime15Flag());
			}
			if (entry.getItemTime16Flag() == null) {
				entry.setItemTime16Flag(0);
			} else {
				entry.setItemTime16Flag(autocarArrange.getItemTime16Flag());
			}
			if (entry.getItemTime17Flag() == null) {
				entry.setItemTime17Flag(0);
			} else {
				entry.setItemTime17Flag(autocarArrange.getItemTime17Flag());
			}
			if (entry.getItemTime18Flag() == null) {
				entry.setItemTime18Flag(0);
			} else {
				entry.setItemTime18Flag(autocarArrange.getItemTime18Flag());
			}
			if (entry.getItemTime19Flag() == null) {
				entry.setItemTime19Flag(0);
			} else {
				entry.setItemTime19Flag(autocarArrange.getItemTime19Flag());
			}
			if (entry.getItemTime20Flag() == null) {
				entry.setItemTime20Flag(0);
			} else {
				entry.setItemTime20Flag(autocarArrange.getItemTime20Flag());
			}
			if (entry.getItemTime21Flag() == null) {
				entry.setItemTime21Flag(0);
			} else {
				entry.setItemTime21Flag(autocarArrange.getItemTime21Flag());
			}
			autoCarArrangeDao.update(entry);
			AutoCarArrange retVal = new AutoCarArrange();
			BeanUtils.copyProperties(entry, retVal);
			return retVal;
		}
		return null;
	}

	@Override
	public void delete(String ids) {
		TtrainerReservation reservation = null;
		List<TtrainerReservationDetail> details = new ArrayList<TtrainerReservationDetail>(0);
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.1根据ID从数据库中查找数据
					TautoCarArrange arrange = autoCarArrangeDao.get(TautoCarArrange.class, id);
					// TODO存在预约则不允许删除

					// 4.调用DAO层删除数据
					autoCarArrangeDao.delete(arrange);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(AutoCarArrange autoCarArrange) {
		DataGrid dataGrid = new DataGrid();

		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(autoCarArrange)));

		// 设置总记录数
		dataGrid.setTotal(this.total(autoCarArrange));
		return dataGrid;
	}

	// 根据预约时间判断是否有可预约车辆
	public boolean checkAutocarFree(String schoolArea, Date arrangeDate, Integer itemTime) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || itemTime <= 0) {
			return false;
		}
		List<ComboboxJson> rets = null;
		try {
			rets = this.getAutoCarsForReservation(schoolArea, arrangeDate, itemTime, 0);
			if (rets != null && rets.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 根据预约时间查找可预约的自动档车辆信息
	public List<ComboboxJson> getAutoCarsForReservation(String schoolArea, Date arrangeDate, Integer itemTime,
			Integer invokeFlag) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || itemTime <= 0) {
			return null;
		}
		List<ComboboxJson> ret = new ArrayList<ComboboxJson>(0);
		List<ComboboxJson> wechatRets = new ArrayList<ComboboxJson>(0);
		String itemTimeFlag = null;
		switch (itemTime) {
		case 8:
			itemTimeFlag = "ItemTime8";
			break;
		case 9:
			itemTimeFlag = "ItemTime9";
			break;
		case 10:
			itemTimeFlag = "ItemTime10";
			break;
		case 11:
			itemTimeFlag = "ItemTime11";
			break;
		case 12:
			itemTimeFlag = "ItemTime12";
			break;
		case 13:
			itemTimeFlag = "ItemTime13";
			break;
		case 14:
			itemTimeFlag = "ItemTime14";
			break;
		case 15:
			itemTimeFlag = "ItemTime15";
			break;
		case 16:
			itemTimeFlag = "ItemTime16";
			break;
		case 17:
			itemTimeFlag = "ItemTime17";
			break;
		case 18:
			itemTimeFlag = "ItemTime18";
			break;
		case 19:
			itemTimeFlag = "ItemTime19";
			break;
		case 20:
			itemTimeFlag = "ItemTime20";
			break;
		case 21:
			itemTimeFlag = "ItemTime21";
			break;
		}

		String sql = "select * from tb_autoCarArrange where schoolArea=? and arrangeDate=? and " + itemTimeFlag
				+ "=1 and " + itemTimeFlag + "Flag=0";
		List<TautoCarArrange> arranges = autoCarArrangeDao.findAllBySQL(TautoCarArrange.class, sql, schoolArea,
				arrangeDate);
		if (arranges != null && arranges.size() > 0) {
			Tcar car = null;
			ComboboxJson json = null;
			for (TautoCarArrange temp : arranges) {
				car = carDao.get(Tcar.class, temp.getCarId());
				if (car != null) {
					json = new ComboboxJson();
					json.setValue(car.getId());
					json.setText(car.getCarNo());
					ret.add(json);
					if (1 == car.getReceiveWechatFlag()) {
						wechatRets.add(json);
					}
				}
			}
		}
		// 返回结果
		if (0 == invokeFlag) {
			if (ret.size() > 0) {// 默认后台调用
				return ret;
			}
		} else if (1 == invokeFlag) {
			if (wechatRets.size() > 0) {
				return wechatRets;
			}
		}
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param autoCarArrangeList
	 * @return
	 */
	private List<AutoCarArrange> changedModel(List<TautoCarArrange> autoCarArrangeList) {
		List<AutoCarArrange> retArranges = new ArrayList<AutoCarArrange>();
		if (autoCarArrangeList != null && autoCarArrangeList.size() > 0) {
			for (TautoCarArrange entry : autoCarArrangeList) {
				AutoCarArrange autoCarArrange = new AutoCarArrange();
				BeanUtils.copyProperties(entry, autoCarArrange);
				retArranges.add(autoCarArrange);
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
	private List<TautoCarArrange> find(AutoCarArrange trainerArranged) {
		String hql = "from TautoCarArrange t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(trainerArranged, hql, paramList);
		if (trainerArranged.getSort() != null && trainerArranged.getOrder() != null) {
			hql += " order by " + trainerArranged.getSort() + " " + trainerArranged.getOrder();
		}
		return autoCarArrangeDao.find(hql, paramList, trainerArranged.getPage(), trainerArranged.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(AutoCarArrange trainerArrange) {
		// 拼接查询条件
		String hql = "select count(*) from TautoCarArrange t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(trainerArrange, hql, paramsList);
		return autoCarArrangeDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param person
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(AutoCarArrange autocarArrange, String hql, List<Object> params) {
		if (autocarArrange.getSchoolArea() != null && !autocarArrange.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea=?";
			params.add(autocarArrange.getSchoolArea().trim());
		}
		// 根据教练名称查找教练的排班信息
		if (ValidateUtil.isValid(autocarArrange.getCarId())) {
			hql += " and t.carId=?";
			params.add(autocarArrange.getCarId().trim());
		}
		// 预约日期范围查询
		if (autocarArrange.getArrangeDateBegin() != null) {
			hql += " and t.arrangeDate >= ?";
			params.add(autocarArrange.getArrangeDateBegin());
		}
		if (autocarArrange.getArrangeDateEnd() != null) {
			hql += " and t.arrangeDate <= ?";
			params.add(autocarArrange.getArrangeDateEnd());
		}

		return hql;
	}

	/**
	 * 从数据库中查找出该教练当前月已排班的天
	 * 
	 * @param trainerId
	 * @param year
	 * @param month
	 * @return
	 */
	private boolean checkArrangedDateByYMD(String schoolArea, Date date, String carId) {
		String sql = "select * from tb_autoCarArrange where schoolArea=? and arrangeDate=? and carId=?";
		List<TautoCarArrange> arranges = autoCarArrangeDao.findAllBySQL(TautoCarArrange.class, sql, schoolArea, date,
				carId);
		if (arranges != null && arranges.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Date getNealyArrageDateByCarId(String carId) {
		if (!ValidateUtil.isValid(carId)) {
			return null;
		}
		String sql = " select * from tb_autoCarArrange where carId=? order by arrangeDate desc";
		List<TautoCarArrange> retVals = autoCarArrangeDao.findAllBySQL(TautoCarArrange.class, sql, carId);
		if (retVals != null && retVals.size() > 0) {
			return retVals.get(0).getArrangeDate();
		}
		return null;
	}

}
