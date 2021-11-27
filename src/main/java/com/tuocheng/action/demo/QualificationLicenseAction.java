package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.DriverLicense;
import com.tuocheng.pageModel.demo.Qualification;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.QualificationLicense;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.QualificationLicenseServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 驾驶证管理action
 * 
 * @author MEI702s
 * 
 */
@Namespace("/demo")
@Action(value = "qualificationLicenseAction", results = {
		@Result(name = "qualificationLicense", location = "/demo/qualificationLicense/qualificationLicense.jsp"),
		@Result(name = "qualificationLicenseAdd", location = "/demo/qualificationLicense/qualificationLicenseAdd.jsp"),
		@Result(name = "qualificationLicenseAddInput", location = "/demo/qualificationLicense/qualificationLicenseAddInput.jsp"),
		@Result(name = "qualificationLicenseEdit", location = "/demo/qualificationLicense/qualificationLicenseEdit.jsp"),
		@Result(name = "detail", location = "/demo/qualificationLicense/detail.jsp"), })
public class QualificationLicenseAction extends
		BaseAction<QualificationLicense> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -676166711198031916L;

	private static final Logger logger = Logger
			.getLogger(QualificationLicenseAction.class);

	private QualificationLicenseServiceI qualificationLicenseService;
	private StudentServiceI studentService;

	@Autowired
	public void setQualificationLicenseService(
			QualificationLicenseServiceI qualificationLicenseService) {
		this.qualificationLicenseService = qualificationLicenseService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}


	// 到达驾驶证管理主页面
	public String toQualificationLicensePage() {
		return "qualificationLicense";
	}

	// 到达添加驾驶证页面
	public String toQualificationLicenseAddPage() {
		return "qualificationLicenseAdd";
	}

	// 到达添加驾驶证添加输入页面
	public String toQualificationLicenseAddInputPage() {
		return "qualificationLicenseAddInput";
	}

	// 到达编辑页面
	public String toQualificationLicenseEditPage() {
		return "qualificationLicenseEdit";
	}

	// 到达详细信息页面
	public String toQualificationLicenseDetailPage() {
		return "detail";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(qualificationLicenseService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			QualificationLicense theory = qualificationLicenseService
					.save(model);
			json.setMsg("添加驾驶证信息成功！");
			json.setSuccess(true);
			json.setObj(theory);
		} catch (Exception e) {
			json.setMsg("添加驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			QualificationLicense theory = qualificationLicenseService
					.udpate(model);
			json.setMsg("编辑驾驶证信息成功！");
			json.setSuccess(true);
			json.setObj(theory);
		} catch (Exception e) {
			json.setMsg("编辑驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			qualificationLicenseService.delete(model.getIds());
			json.setMsg("删除驾驶证信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据查询条件查找出所有
	@SuppressWarnings("unchecked")
	public void getAllGraduateStudents() throws Exception {
		/*// TODO 筛选学员参数设计
		// 1.根据student的model查找出所有符合查询条件的学员信息
		SubjectFour entry = new SubjectFour();
		entry.setSchoolArea(model.getSchoolArea());
		entry.setName(model.getStudentName());
		entry.setStudentNo(model.getStudentNo());
		entry.setIdentityId(model.getStudentIdentityId());

		// 配置学员考试科目类型
		entry.setExamType(model.getDriverType());
		entry.setPassFlag(TsubjectFour.FINSHEXAME);// 考试合格的学员
		entry.setQualiLicensExamSelected(TsubjectFour.EXAMSELECTED_FALSE);

		entry.setRows(model.getRows());
		entry.setSort(model.getSort());
		entry.setOrder(model.getOrder());
		entry.setPage(model.getPage());

		DataGrid myDataGrid = subjectFourService.dataGrid(entry);
		List<SubjectFour> temps = myDataGrid.getRows();

		// 3.将student数据转换成subject展示
		List<QualificationLicense> retLists = new ArrayList<QualificationLicense>();
		if (temps != null && temps.size() > 0) {
			for (SubjectFour temp : temps) {
				if (ValidateUtil.isValid(temp.getStudentId())) {
					Tstudent stu = studentService.getSingleById(temp
							.getStudentId());
					QualificationLicense license = new QualificationLicense();
					license.setStudentName(stu.getName());
					license.setStudentOrganization(stu.getOrganization()
							.getName());
					if(stu.getClazz()!=null){
						license.setStudentClazz(stu.getClazz().getName());
					}
					license.setStudentSex(stu.getSex());
					license.setStudentPhone(stu.getPhone());
					license.setStudentIdentityId(stu.getIdentityId());
					license.setStudentCreateTime(stu.getCreateTime());
					license.setStudentDriverType(stu.getDriverType());
					license.setStudentId(stu.getId());
					license.setStudentNowstate(stu.getNowState());
					license.setStudentGraduateDate(stu.getGraduateDate());
					retLists.add(license);
				}

			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retLists);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);*/
	}

	// 根据学员ID查找学员信息
	public void getSingStudentInformation() throws Exception {
		super.writeJson(studentService.getSingleById(model.getStudentId()));
	}
}
