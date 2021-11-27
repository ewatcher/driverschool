package com.tuocheng.service.demo.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.IDStarAlgorithm;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tsms;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.SMS;
import com.tuocheng.service.demo.SMSServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.SendSMSUtils;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.message.WeixinMessageService;

/**
 * 短信实体实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("smsService")
public class SMSServiceImpl implements SMSServiceI {

	private BaseDaoI<Tsms> smsDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TweixinUser> weixinUserDao;

	@Autowired
	public void setSmsDao(BaseDaoI<Tsms> smsDao) {
		this.smsDao = smsDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	// 注入DAO
	@Autowired
	public void setWeixinUserDao(BaseDaoI<TweixinUser> weixinUserDao) {
		this.weixinUserDao = weixinUserDao;
	}

	@Override
	public SMS save(SMS sms) {
		// 1.数据模型转换
		Tsms entry = null;
		if (ValidateUtil.isValid(sms.getIds())) {
			Tstudent student = null;
			for (String idTemp : sms.getIds().split(",")) {
				student = studentDao.get(Tstudent.class, idTemp);
				Date date = new Date();
				date.setHours(0);
				date.setMinutes(0);
				date.setSeconds(0);
				if (student != null) {
					entry = new Tsms();
					BeanUtils.copyProperties(sms, entry, new String[] { "id" });
					entry.setId(UUID.randomUUID().toString());
					entry.setName(student.getName());
					entry.setPhone(student.getPhone());
					entry.setDate(date);
					entry.setContent("尊敬的学员【" + student.getName() + "】您将于【" + DateUtil.DateToStringYMDHM(sms.getDate())
							+ "】参加科目【" + sms.getSubjectType() + "】考试，请提前30分钟达到考场，请须知。");
					// 短信通知
					SendSMSUtils.setExamSMS(student.getPhone(), student.getName(),
							DateUtil.DateToStringYMDHM(sms.getDate()), sms.getSubjectType().toString());

					// 2.保存数据
					smsDao.save(entry);
				}
			}
		}
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, sms);
		// 4.将转换后的数据返回给前台
		return sms;
	}

	@Override
	public SMS udpate(SMS sms) {
		// 1.数据模型转换
		Tsms t = new Tsms();
		BeanUtils.copyProperties(sms, t, new String[] { "id" });
		t.setId(sms.getId());
		// 2.保存数据
		smsDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, sms);
		// 4.将转换后的数据返回给前台
		return sms;
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
					Tsms sms = smsDao.get(Tsms.class, id);
					// 4.调用DAO层删除数据
					smsDao.delete(sms);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(SMS sms) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(sms)));
		// 设置总记录数
		dataGrid.setTotal(this.total(sms));
		return dataGrid;
	}

	// 微信通知学员
	public boolean sendMsgToStudentByWechat(SMS sms) throws Exception {
		if (!ValidateUtil.isValid(sms.getIds())) {
			return false;
		}
		Tstudent student = null;
		TweixinUser wechatUser = null;
		Weixin weixinStudent = null;
		WeixinMessageService wxmsgStudent = null;
		String text = null;
		for (String studentId : sms.getIds().split(",")) {
			student = studentDao.get(Tstudent.class, studentId);
			wechatUser = getWechatUserByStudentId(studentId);
			if (student != null && wechatUser != null) {
				text = "尊敬的学员【" + student.getName() + "】您将于【" + DateUtil.DateToStringYMDHM(sms.getDate()) + "】参加科目【"
						+ sms.getSubjectType() + "】考试，请提前30分钟达到考场，请须知。";
				weixinStudent = new WeixinUtil().getWeixinByOrganizationId(student.getOrganization().getId());
				if (weixinStudent != null) {
					wxmsgStudent = new WeixinMessageService(weixinStudent);
					wxmsgStudent.postCustomServiceMessage(ServletActionContext.getServletContext(),
							wechatUser.getOpenId(), text);
				}
			}

		}
		return false;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<SMS> changedModel(List<Tsms> smsList) {
		List<SMS> retPersons = new ArrayList<SMS>();
		if (smsList != null && smsList.size() > 0) {
			for (Tsms t : smsList) {
				SMS sms = new SMS();
				sms.setSchoolAreaName(organizationDao.get(Torganization.class, t.getSchoolArea()).getName());
				BeanUtils.copyProperties(t, sms);
				retPersons.add(sms);
			}
		}
		return retPersons;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tsms> find(SMS sms) {
		String hql = "from Tsms t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(sms, hql, paramList);
		if (sms.getSort() != null && sms.getOrder() != null) {
			hql += " order by " + sms.getSort() + " " + sms.getOrder();
		}
		return smsDao.find(hql, paramList, sms.getPage(), sms.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(SMS sms) {
		// 拼接查询条件
		String hql = "select count(*) from Tsms t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(sms, hql, paramsList);
		return smsDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param sms
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(SMS sms, String hql, List<Object> params) {
		// 姓名模糊查询
		if (ValidateUtil.isValid(sms.getName())) {
			hql += " and t.name like ?";
			params.add("%%" + sms.getName().trim() + "%%");
		}
		// 手机
		if (ValidateUtil.isValid(sms.getPhone())) {
			hql += " and t.phone =?";
			params.add(sms.getPhone().trim());
		}
		// 所属部门精确查询
		if (ValidateUtil.isValid(sms.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(sms.getSchoolArea().trim());
		}
		// 日期
		if (ValidateUtil.isValid(sms.getDateStart())) {
			hql += " and t.date >=?";
			params.add(sms.getDateStart());
		}
		if (ValidateUtil.isValid(sms.getDateEnd())) {
			hql += " and t.date <=?";
			params.add(sms.getDateEnd());
		}
		// 操作者
		if (ValidateUtil.isValid(sms.getOperator())) {
			hql += " and t.operator =?";
			params.add(sms.getOperator());
		}

		return hql;
	}

	private TweixinUser getWechatUserByStudentId(String studentId) {
		if (Util.isNotNull(studentId)) {
			String hql = " from TweixinUser t where studentId = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(studentId);
			return weixinUserDao.get(hql, params);
		} else {
			return null;
		}
	}
}
