package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.HashedMap;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.Validateable;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TtrainerArrange;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerReservation;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.TrainerReservationDetailServiceI;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("reservationDetailService")
public class TrainerReservationDetailServiceImpl implements TrainerReservationDetailServiceI {

	private BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao;
	private BaseDaoI<TtrainerReservation> trainerReservationDao;
	private BaseDaoI<TtrainerArrange> trainerArrangeDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setTrainerReservationDetailDao(BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao) {
		this.trainerReservationDetailDao = trainerReservationDetailDao;
	}

	@Autowired
	public void setTrainerReservationDao(BaseDaoI<TtrainerReservation> trainerReservationDao) {
		this.trainerReservationDao = trainerReservationDao;
	}

	@Autowired
	public void setTrainerArrangeDao(BaseDaoI<TtrainerArrange> trainerArrangeDao) {
		this.trainerArrangeDao = trainerArrangeDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public TrainerReservationDetail save(TrainerReservationDetail reservationDetail) {
		// 1.数据模型转换
		TtrainerReservationDetail t = new TtrainerReservationDetail();
		BeanUtils.copyProperties(reservationDetail, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());

		// 2.保存数据
		trainerReservationDetailDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, reservationDetail);
		// 4.将转换后的数据返回给前台
		return reservationDetail;
	}

	@Override
	public TrainerReservationDetail udpate(TrainerReservationDetail reservationDetail) {
		// 1.数据模型转换
		TtrainerReservationDetail trainerResercationDetail = new TtrainerReservationDetail();
		BeanUtils.copyProperties(reservationDetail, trainerResercationDetail, new String[] { "id" });
		trainerResercationDetail.setId(reservationDetail.getId());
		// 更新五项，路考总和
		Integer fiveItemTotal = new Integer(0);
		Integer roadExamTotal = new Integer(0);
		if (ValidateUtil.isValid(reservationDetail.getItem7Studentid())) {
			if (!reservationDetail.getItem7Studentid().equals("NO")) {
				if (reservationDetail.getItem7TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem7TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem7TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem8Studentid())) {
			if (!reservationDetail.getItem8Studentid().equals("NO")) {
				if (reservationDetail.getItem8TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem8TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem8TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem9Studentid())) {
			if (!reservationDetail.getItem9Studentid().equals("NO")) {
				if (reservationDetail.getItem9TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem9TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem9TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem10Studentid())) {
			if (!reservationDetail.getItem10Studentid().equals("NO")) {
				if (reservationDetail.getItem10TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem10TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem10TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem11Studentid())) {
			if (!reservationDetail.getItem11Studentid().equals("NO")) {
				if (reservationDetail.getItem11TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem11TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem11TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem12Studentid())) {
			if (!reservationDetail.getItem12Studentid().equals("NO")) {
				if (reservationDetail.getItem12TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem12TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem12TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem13Studentid())) {
			if (!reservationDetail.getItem13Studentid().equals("NO")) {
				if (reservationDetail.getItem13TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem13TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem13TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem14Studentid())) {
			if (!reservationDetail.getItem14Studentid().equals("NO")) {
				if (reservationDetail.getItem14TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem14TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem14TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem15Studentid())) {
			if (!reservationDetail.getItem15Studentid().equals("NO")) {
				if (reservationDetail.getItem15TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem15TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem15TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem16Studentid())) {
			if (!reservationDetail.getItem16Studentid().equals("NO")) {
				if (reservationDetail.getItem16TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem16TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem16TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem17Studentid())) {
			if (!reservationDetail.getItem17Studentid().equals("NO")) {
				if (reservationDetail.getItem17TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem17TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem17TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem18Studentid())) {
			if (!reservationDetail.getItem18Studentid().equals("NO")) {
				if (reservationDetail.getItem18TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem18TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem18TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem19Studentid())) {
			if (!reservationDetail.getItem19Studentid().equals("NO")) {
				if (reservationDetail.getItem19TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem19TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem19TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem20Studentid())) {
			if (!reservationDetail.getItem20Studentid().equals("NO")) {
				if (reservationDetail.getItem20TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem20TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem20TrainContent(null);
			}

		}
		if (ValidateUtil.isValid(reservationDetail.getItem21Studentid())) {
			if (!reservationDetail.getItem21Studentid().equals("NO")) {
				if (reservationDetail.getItem21TrainContent() == 1) {
					fiveItemTotal += 1;
				} else if (reservationDetail.getItem21TrainContent() == 2) {
					roadExamTotal += 1;
				}
			} else {
				trainerResercationDetail.setItem18TrainContent(null);
			}

		}

		trainerResercationDetail.setFiveItemTotal(fiveItemTotal);
		trainerResercationDetail.setRaodExamTotal(roadExamTotal);

		if (ValidateUtil.isValid(reservationDetail.getTrainerReservationId())) {
			TtrainerReservation reservation = trainerReservationDao.get(TtrainerReservation.class,
					reservationDetail.getTrainerReservationId());
			trainerResercationDetail.setTrainerReservation(reservation);

		}

		// 2.保存数据
		trainerReservationDetailDao.update(trainerResercationDetail);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(trainerResercationDetail, reservationDetail);
		// 4.将转换后的数据返回给前台
		return reservationDetail;
	}

	@Override
	public boolean delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					TtrainerReservationDetail detail = trainerReservationDetailDao.get(TtrainerReservationDetail.class,
							id);
					// 预约标识字段不为空，表示当前教练预约明细中存在学员预约，不允许删除
					if (ValidateUtil.isValid(detail.getReservationFieldId7())
							|| ValidateUtil.isValid(detail.getReservationFieldId8())
							|| ValidateUtil.isValid(detail.getReservationFieldId9())
							|| ValidateUtil.isValid(detail.getReservationFieldId10())
							|| ValidateUtil.isValid(detail.getReservationFieldId11())
							|| ValidateUtil.isValid(detail.getReservationFieldId12())
							|| ValidateUtil.isValid(detail.getReservationFieldId13())
							|| ValidateUtil.isValid(detail.getReservationFieldId14())
							|| ValidateUtil.isValid(detail.getReservationFieldId15())
							|| ValidateUtil.isValid(detail.getReservationFieldId16())
							|| ValidateUtil.isValid(detail.getReservationFieldId17())
							|| ValidateUtil.isValid(detail.getReservationFieldId18())
							|| ValidateUtil.isValid(detail.getReservationFieldId19())
							|| ValidateUtil.isValid(detail.getReservationFieldId20())
							|| ValidateUtil.isValid(detail.getReservationFieldId21())) {

						return false;
					}
					// 4.调用DAO层删除数据
					TtrainerArrange arrange = null;
					if (ValidateUtil.isValid(detail.getArrangeId())) {
						arrange = trainerArrangeDao.get(TtrainerArrange.class, detail.getArrangeId());
						if (arrange != null) {
							trainerArrangeDao.delete(arrange);
						}
					}

					trainerReservationDetailDao.delete(detail);

				}
			}
			return true;
		}
		return false;
	}

	@Override
	public DataGrid dataGrid(TrainerReservationDetail reservationDetail) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(reservationDetail)));
		// 设置总记录数
		dataGrid.setTotal(this.total(reservationDetail));
		return dataGrid;
	}

	public DataGrid personalDataGrid(TrainerReservationDetail reservationDetail) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(reservationDetail)));
		// 设置总记录数
		dataGrid.setTotal(this.total(reservationDetail));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<TrainerReservationDetail> changedModel(List<TtrainerReservationDetail> reservationDetails) {
		// TODO
		// updateStudentNo(reservationDetails);

		List<TrainerReservationDetail> retDetails = new ArrayList<TrainerReservationDetail>();
		if (reservationDetails != null && reservationDetails.size() > 0) {

			for (TtrainerReservationDetail trainerReservationDetail : reservationDetails) {
				TrainerReservationDetail entry = new TrainerReservationDetail();
				BeanUtils.copyProperties(trainerReservationDetail, entry);
				// 给页面展现教练预约明细属性信息
				if (trainerReservationDetail.getTrainerReservation().getId() != null) {
					entry.setTrainerReservationId(trainerReservationDetail.getTrainerReservation().getId());
					entry.setReservationType(trainerReservationDetail.getReservationType());
				}
				Integer fiveTotalTemp = 0;
				Integer roadTotalTemp = 0;
				// 如果学员ID标记位为空表示该教练预约明细时段不存在预约
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId7())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem7Studentid())) {
						if (!trainerReservationDetail.getItem7Studentid().equals("NO")) {
							entry.setItem7Studentid(null);
						}
					}
				} else {
					entry.setItem7Studentid(getStudentNoById(trainerReservationDetail.getItem7Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem7TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem7TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId8())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem8Studentid())) {
						if (!trainerReservationDetail.getItem8Studentid().equals("NO")) {
							entry.setItem8Studentid(null);
						}
					}
				} else {
					entry.setItem8Studentid(getStudentNoById(trainerReservationDetail.getItem8Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem8TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem8TrainContent()) {
						roadTotalTemp += 1;
					}
				}

				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId9())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem9Studentid())) {
						if (!trainerReservationDetail.getItem9Studentid().equals("NO")) {
							entry.setItem9Studentid(null);
						}
					}
				} else {
					entry.setItem9Studentid(getStudentNoById(trainerReservationDetail.getItem9Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem9TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem9TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId10())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem10Studentid())) {
						if (!trainerReservationDetail.getItem10Studentid().equals("NO")) {
							entry.setItem10Studentid(null);
						}
					}
				} else {
					entry.setItem10Studentid(getStudentNoById(trainerReservationDetail.getItem10Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem10TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem10TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId11())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem11Studentid())) {
						if (!trainerReservationDetail.getItem11Studentid().equals("NO")) {
							entry.setItem11Studentid(null);
						}
					}
				} else {
					entry.setItem11Studentid(getStudentNoById(trainerReservationDetail.getItem11Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem11TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem11TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId12())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem12Studentid())) {
						if (!trainerReservationDetail.getItem12Studentid().equals("NO")) {
							entry.setItem12Studentid(null);
						}
					}
				} else {
					entry.setItem12Studentid(getStudentNoById(trainerReservationDetail.getItem12Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem12TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem12TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId13())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem13Studentid())) {
						if (!trainerReservationDetail.getItem13Studentid().equals("NO")) {
							entry.setItem13Studentid(null);
						}
					}
				} else {
					entry.setItem13Studentid(getStudentNoById(trainerReservationDetail.getItem13Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem13TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem13TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId14())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem14Studentid())) {
						if (!trainerReservationDetail.getItem14Studentid().equals("NO")) {
							entry.setItem14Studentid(null);
						}
					}
				} else {
					entry.setItem14Studentid(getStudentNoById(trainerReservationDetail.getItem14Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem14TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem14TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId15())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem15Studentid())) {
						if (!trainerReservationDetail.getItem15Studentid().equals("NO")) {
							entry.setItem15Studentid(null);
						}
					}
				} else {
					entry.setItem15Studentid(getStudentNoById(trainerReservationDetail.getItem15Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem15TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem15TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId16())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem16Studentid())) {
						if (!trainerReservationDetail.getItem16Studentid().equals("NO")) {
							entry.setItem16Studentid(null);
						}
					}
				} else {
					entry.setItem16Studentid(getStudentNoById(trainerReservationDetail.getItem16Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem16TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem16TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId17())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem17Studentid())) {
						if (!trainerReservationDetail.getItem17Studentid().equals("NO")) {
							entry.setItem17Studentid(null);
						}
					}
				} else {
					entry.setItem17Studentid(getStudentNoById(trainerReservationDetail.getItem17Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem17TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem17TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId18())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem18Studentid())) {
						if (!trainerReservationDetail.getItem18Studentid().equals("NO")) {
							entry.setItem18Studentid(null);
						}
					}
				} else {
					entry.setItem18Studentid(getStudentNoById(trainerReservationDetail.getItem18Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem18TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem18TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId19())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem19Studentid())) {
						if (!trainerReservationDetail.getItem19Studentid().equals("NO")) {
							entry.setItem19Studentid(null);
						}
					}
				} else {
					entry.setItem19Studentid(getStudentNoById(trainerReservationDetail.getItem19Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem19TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem19TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId20())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem20Studentid())) {
						if (!trainerReservationDetail.getItem20Studentid().equals("NO")) {
							entry.setItem20Studentid(null);
						}
					}
				} else {
					entry.setItem20Studentid(getStudentNoById(trainerReservationDetail.getItem20Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem20TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem20TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				if (!ValidateUtil.isValid(trainerReservationDetail.getReservationFieldId21())) {
					if (ValidateUtil.isValid(trainerReservationDetail.getItem21Studentid())) {
						if (!trainerReservationDetail.getItem21Studentid().equals("NO")) {
							entry.setItem21Studentid(null);
						}
					}
				} else {
					entry.setItem21Studentid(getStudentNoById(trainerReservationDetail.getItem21Studentid()));
					// 统计该条预约五项，路训总数
					if (3 == trainerReservationDetail.getItem21TrainContent()) {
						fiveTotalTemp += 1;
					} else if (4 == trainerReservationDetail.getItem21TrainContent()) {
						roadTotalTemp += 1;
					}
				}
				entry.setFiveItemTotal(fiveTotalTemp);
				entry.setRaodExamTotal(roadTotalTemp);

				retDetails.add(entry);
			}
		}
		return retDetails;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TtrainerReservationDetail> find(TrainerReservationDetail trainerRerservationdetail) {
		String hql = "from TtrainerReservationDetail t where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(trainerRerservationdetail, hql, paramList);
		if (trainerRerservationdetail.getSort() != null && trainerRerservationdetail.getOrder() != null) {
			hql += " order by " + trainerRerservationdetail.getSort() + " " + trainerRerservationdetail.getOrder();
		}
		List<TtrainerReservationDetail> ret = trainerReservationDetailDao.find(hql, paramList,
				trainerRerservationdetail.getPage(), trainerRerservationdetail.getRows());
		return ret;
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(TrainerReservationDetail reservationDetail) {
		// 拼接查询条件
		String hql = "select count(*) from TtrainerReservationDetail t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(reservationDetail, hql, paramsList);
		return trainerReservationDetailDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param reservationDetail
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(TrainerReservationDetail reservationDetail, String hql, List<Object> params) {
		// 姓名模糊查询
		// TODO
		if (reservationDetail.getTrainerReservationId() != null
				&& !reservationDetail.getTrainerReservationId().trim().equals("")) {
			hql += " and t.TrainerReservation.id=?";
			params.add(reservationDetail.getTrainerReservationId().trim());
		}

		// 按月份查询
		if (reservationDetail.getReservationDateBegin() != null) {
			hql += " and t.reservationDate >= ?";
			params.add(reservationDetail.getReservationDateBegin());
		}
		if (reservationDetail.getReservationDateEnd() != null) {
			hql += " and t.reservationDate <= ?";
			params.add(reservationDetail.getReservationDateEnd());
		}

		if (ValidateUtil.isValid(reservationDetail.getTrainerId())) {
			hql += " and t.trainerId=?";
			params.add(reservationDetail.getTrainerId());
		}

		return hql;
	}

	@Override
	public List<TtrainerReservationDetail> getTrainerReservationDetailByTrainerId(String trainerId,
			Date reservarionDateBegin, Date reservarionDateEnd) throws Exception {
		String hql = "from TtrainerReservationDetail t where t.trainerId=? and t.reservationDate >= ? and t.reservationDate <= ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(trainerId);
		paramList.add(reservarionDateBegin);
		paramList.add(reservarionDateEnd);
		List<TtrainerReservationDetail> retDatas = trainerReservationDetailDao.find(hql, paramList);
		if (retDatas.size() > 0) {
			return retDatas;
		}
		return null;
	}

	@Override
	public List<TrainerReservationDetail> trainerReservationDetailsForUpdate(String trainerId, String schoolArea) {
		if (!ValidateUtil.isValid(trainerId) || !ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		// 根据教练标识，校区标识查找出所有教练预约明细信息
		List<TrainerReservationDetail> reVals = new ArrayList<TrainerReservationDetail>();
		String hql = "from TtrainerReservationDetail t where t.trainerId=? and t.schoolArea=? order by t.reservationDate desc";
		List<Object> param = new ArrayList<Object>();
		param.add(trainerId);
		param.add(schoolArea);
		List<TtrainerReservationDetail> reservationDetails = trainerReservationDetailDao.find(hql, param);
		// 转换数据模型,并返回
		if (reservationDetails.size() > 0) {
			for (TtrainerReservationDetail entry : reservationDetails) {
				TrainerReservationDetail detail = new TrainerReservationDetail();
				BeanUtils.copyProperties(entry, detail);
				reVals.add(detail);
			}
			return reVals;
		}
		return null;
	}

	@Override
	public TrainerReservationDetail arrangeDetailUpdate(TrainerReservationDetail reservationDetail) throws Exception {
		if (!ValidateUtil.isValid(reservationDetail.getId())) {
			return null;
		}
		TtrainerReservationDetail detail = trainerReservationDetailDao.get(TtrainerReservationDetail.class,
				reservationDetail.getId());
		if (detail != null) {
			// 不存在预约的教练预约明细信息可以进行修改排班信息
			if (reservationDetail.getItem7TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId7())) {
				if (reservationDetail.getItem7TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem7Studentid("NO");
				} else {
					detail.setItem7Studentid(null);
				}
				detail.setItem7TrainContent(reservationDetail.getItem7TrainContent());
			}
			if (reservationDetail.getItem8TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId8())) {
				if (reservationDetail.getItem8TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem8Studentid("NO");
				} else {
					detail.setItem8Studentid(null);
				}
				detail.setItem8TrainContent(reservationDetail.getItem8TrainContent());
			}
			if (reservationDetail.getItem9TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId9())) {
				if (reservationDetail.getItem9TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem9Studentid("NO");
				} else {
					detail.setItem9Studentid(null);
				}
				detail.setItem9TrainContent(reservationDetail.getItem9TrainContent());
			}
			if (reservationDetail.getItem10TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId10())) {
				if (reservationDetail.getItem10TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem10Studentid("NO");
				} else {
					detail.setItem10Studentid(null);
				}
				detail.setItem10TrainContent(reservationDetail.getItem10TrainContent());
			}
			if (reservationDetail.getItem11TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId11())) {
				if (reservationDetail.getItem11TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem11Studentid("NO");
				} else {
					detail.setItem11Studentid(null);
				}
				detail.setItem11TrainContent(reservationDetail.getItem11TrainContent());
			}
			if (reservationDetail.getItem12TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId12())) {
				if (reservationDetail.getItem12TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem12Studentid("NO");
				} else {
					detail.setItem12Studentid(null);
				}
				detail.setItem12TrainContent(reservationDetail.getItem12TrainContent());
			}
			if (reservationDetail.getItem13TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId13())) {
				if (reservationDetail.getItem13TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem13Studentid("NO");
				} else {
					detail.setItem13Studentid(null);
				}
				detail.setItem13TrainContent(reservationDetail.getItem13TrainContent());
			}
			if (reservationDetail.getItem14TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId14())) {
				if (reservationDetail.getItem14TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem14Studentid("NO");
				} else {
					detail.setItem14Studentid(null);
				}
				detail.setItem14TrainContent(reservationDetail.getItem14TrainContent());
			}
			if (reservationDetail.getItem15TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId15())) {
				if (reservationDetail.getItem15TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem15Studentid("NO");
				} else {
					detail.setItem15Studentid(null);
				}
				detail.setItem15TrainContent(reservationDetail.getItem15TrainContent());
			}
			if (reservationDetail.getItem16TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId16())) {
				if (reservationDetail.getItem16TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem16Studentid("NO");
				} else {
					detail.setItem16Studentid(null);
				}
				detail.setItem16TrainContent(reservationDetail.getItem16TrainContent());
			}
			if (reservationDetail.getItem17TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId17())) {
				if (reservationDetail.getItem17TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem17Studentid("NO");
				} else {
					detail.setItem17Studentid(null);
				}
				detail.setItem17TrainContent(reservationDetail.getItem17TrainContent());
			}
			if (reservationDetail.getItem18TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId18())) {
				if (reservationDetail.getItem18TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem18Studentid("NO");
				} else {
					detail.setItem18Studentid(null);
				}
				detail.setItem18TrainContent(reservationDetail.getItem18TrainContent());
			}
			if (reservationDetail.getItem19TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId19())) {
				if (reservationDetail.getItem19TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem19Studentid("NO");
				} else {
					detail.setItem19Studentid(null);
				}
				detail.setItem19TrainContent(reservationDetail.getItem19TrainContent());
			}
			if (reservationDetail.getItem20TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId20())) {
				if (reservationDetail.getItem20TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem20Studentid("NO");
				} else {
					detail.setItem20Studentid(null);
				}
				detail.setItem20TrainContent(reservationDetail.getItem20TrainContent());
			}
			if (reservationDetail.getItem21TrainContent() > 0
					&& !ValidateUtil.isValid(detail.getReservationFieldId21())) {
				if (reservationDetail.getItem21TrainContent() == TtrainerReservationDetail.ItemTrainContent_REST) {
					detail.setItem21Studentid("NO");
				} else {
					detail.setItem21Studentid(null);
				}
				detail.setItem21TrainContent(reservationDetail.getItem21TrainContent());
			}
			trainerReservationDetailDao.update(detail);
			// 返回更新结果
			TrainerReservationDetail reVal = new TrainerReservationDetail();
			BeanUtils.copyProperties(detail, reVal);
			return reVal;
		}

		return null;
	}

	@Override
	public Date getLatestDateByTrainerId(String trainerId, String schoolArea) throws Exception {
		if (!ValidateUtil.isValid(trainerId)) {
			return null;
		}
		List<TrainerReservationDetail> details = this.trainerReservationDetailsForUpdate(trainerId, schoolArea);
		if (details != null && details.size() > 0) {
			return details.get(0).getReservationDate();
		}
		return null;
	}

	@Override
	public TrainerReservationDetail getDetailByTrainerIdDate(String trainerId, Date date) throws Exception {
		if (!ValidateUtil.isValid(trainerId) || date == null) {
			return null;
		}
		String sql = "select * from tb_trainerReservationDetail where trainerId=? and reservationDate=?";
		List<TtrainerReservationDetail> retsDetails = trainerReservationDetailDao
				.findBySQL(TtrainerReservationDetail.class, sql, trainerId, date);
		if (retsDetails != null && retsDetails.size() > 0) {
			TrainerReservationDetail retVal = new TrainerReservationDetail();
			BeanUtils.copyProperties(retsDetails.get(0), retVal);
			// 去年学员编号前的两个英文字母
			if (ValidateUtil.isValid(retsDetails.get(0).getItem7Studentid())) {
				retVal.setItem7Studentid(getStudentNoById(retsDetails.get(0).getItem7Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem8Studentid())) {
				retVal.setItem8Studentid(getStudentNoById(retsDetails.get(0).getItem8Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem9Studentid())) {
				retVal.setItem9Studentid(getStudentNoById(retsDetails.get(0).getItem9Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem10Studentid())) {
				retVal.setItem10Studentid(getStudentNoById(retsDetails.get(0).getItem10Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem11Studentid())) {
				retVal.setItem11Studentid(getStudentNoById(retsDetails.get(0).getItem11Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem12Studentid())) {
				retVal.setItem12Studentid(getStudentNoById(retsDetails.get(0).getItem12Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem13Studentid())) {
				retVal.setItem13Studentid(getStudentNoById(retsDetails.get(0).getItem13Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem14Studentid())) {
				retVal.setItem14Studentid(getStudentNoById(retsDetails.get(0).getItem14Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem15Studentid())) {
				retVal.setItem15Studentid(getStudentNoById(retsDetails.get(0).getItem15Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem16Studentid())) {
				retVal.setItem16Studentid(getStudentNoById(retsDetails.get(0).getItem16Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem17Studentid())) {
				retVal.setItem17Studentid(getStudentNoById(retsDetails.get(0).getItem17Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem18Studentid())) {
				retVal.setItem18Studentid(getStudentNoById(retsDetails.get(0).getItem18Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem19Studentid())) {
				retVal.setItem19Studentid(getStudentNoById(retsDetails.get(0).getItem19Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem20Studentid())) {
				retVal.setItem20Studentid(getStudentNoById(retsDetails.get(0).getItem20Studentid()));
			}
			if (ValidateUtil.isValid(retsDetails.get(0).getItem21Studentid())) {
				retVal.setItem21Studentid(getStudentNoById(retsDetails.get(0).getItem21Studentid()));
			}

			return retVal;
		}
		return null;
	}

	private String getStudentNoById(String studentId) {
		if (!ValidateUtil.isValid(studentId)) {
			return null;
		}
		Tstudent student = studentDao.get(Tstudent.class, studentId);
		if (student != null) {
			return StringUtil.getFormatterStudentNo(student.getStudentNo()) + ":" + student.getName();
		}
		return null;
	}

}
