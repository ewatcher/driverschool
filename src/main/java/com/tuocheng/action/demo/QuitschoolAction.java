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
import com.tuocheng.pageModel.demo.QuitSchool;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.service.demo.QuitschoolServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 退学登记管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "quitschoolAction", results = {
		@Result(name = "quitschool", location = "/demo/quitschool/quitschool.jsp"),
		@Result(name = "quitschoolAdd", location = "/demo/quitschool/quitschoolAdd.jsp"),
		@Result(name = "quitschoolAddInput", location = "/demo/quitschool/quitschoolAddInput.jsp"),
		@Result(name = "quitschoolEdit", location = "/demo/quitschool/quitschoolEdit.jsp"),
		@Result(name = "detail", location = "/demo/quitschool/detail.jsp"),})
public class QuitschoolAction extends BaseAction<QuitSchool> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -676166711198031916L;

	private static final Logger logger = Logger
			.getLogger(QuitschoolAction.class);

	private QuitschoolServiceI quitschoolService;
	private StudentServiceI studentService;

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setQuitschoolService(QuitschoolServiceI quitschoolService) {
		this.quitschoolService = quitschoolService;
	}

	// 到达退学登记管理主页面
	public String toQuitschoolPage() {
		return "quitschool";
	}

	// 到达添加退学登记页面
	public String toQuitschoolAddPage() {
		return "quitschoolAdd";
	}

	// 到达添加输入界面
	public String toQuitschoolAddInputPage() {
		return "quitschoolAddInput";
	}

	// 到达编辑页面
	public String toQuitschoolEditPage() {
		return "quitschoolEdit";
	}

	// 到达详细信息页面
	public String toQuitschoolDetailPage() {
		return "detail";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(quitschoolService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			QuitSchool quitschool = quitschoolService.save(model);
			json.setMsg("添加退学登记信息成功！");
			json.setSuccess(true);
			json.setObj(quitschool);
		} catch (Exception e) {
			json.setMsg("添加退学登记信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			QuitSchool quitschool = quitschoolService.udpate(model);
			json.setMsg("编辑退学登记信息成功！");
			json.setSuccess(true);
			json.setObj(quitschool);
		} catch (Exception e) {
			json.setMsg("编辑退学登记信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			quitschoolService.delete(model.getIds());
			json.setMsg("删除退学登记信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除退学登记信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	public void getAllStudentByState() throws Exception {
		// TODO 筛选学员参数设计
		// 1.根据student的model查找出所有符合查询条件的学员信息
		Student s = new Student();
		s.setOrganizationName(user.getSchoolArea());
		s.setName(model.getStudentName());
		s.setIdentityId(model.getStudentIdentityId());
		s.setNowState(Tstudent.NOWSTATE_INTRAINING);
		// 分页与排序属性配置
		s.setRows(model.getRows());
		s.setSort(model.getSort());
		s.setOrder(model.getOrder());
		s.setPage(model.getPage());

		DataGrid myDataGrid = studentService.datagrid(s);
		List<Student> tempStudents = myDataGrid.getRows();

		// 3.将student数据转换成subject展示
		List<QuitSchool> quitschoolLists = new ArrayList<QuitSchool>();
		if (tempStudents != null && tempStudents.size() > 0) {
			for (Student student : tempStudents) {
				QuitSchool quitSchool = new QuitSchool();
				if(ValidateUtil.isValid(user.getSchoolArea())){
					quitSchool.setSchoolArea(user.getSchoolArea());
				}
				if(ValidateUtil.isValid(user.getSchoolAreaName())){
					quitSchool.setSchoolAreaName(user.getSchoolAreaName());
				}
				quitSchool.setStudentName(student.getName());
				quitSchool.setStudentNo(student.getStudentNo());
				quitSchool.setStudentSex(student.getSex());
				quitSchool.setStudentPhone(student.getPhone());
				quitSchool.setStudentIdentityId(student.getIdentityId());
				quitSchool.setStudentCreateTime(student.getCreateTime());
				quitSchool.setStudentNowstate(student.getNowState());
				quitSchool.setStudentBirthdate(student.getBirthdate());
				quitSchool.setStudentContry(student.getContry());
				quitSchool.setStudentAddress(student.getAddress());
				quitSchool.setStudentNativeNation(student.getNativeNation());
				quitSchool.setStudentPhone(student.getTelephone());
				quitSchool.setStudentPhone(student.getPhone());
				quitSchool.setStudentEmail(student.getEmail());
				quitSchool.setStudentMailCode(student.getMailCode());
				quitSchool.setStudentResidenceId(student.getResidenceId());
				quitSchool.setStudentResidenceAddr(student.getResidenceAddr());
				quitSchool.setStudentImageId(student.getImageId());
				quitSchool.setStudentCreateTime(student.getCreateTime());
				quitSchool.setStudentGraduateDate(student.getGraduateDate());
				quitSchool.setStudentDriverType(student.getDriverType());
				quitSchool.setStudentId(student.getId());
				quitSchool.setStudentApplyType(student.getApplyType());
				quitSchool.setStudentFeed(student.getFeed());
				quitSchool.setStudentRealFeed(student.getRealFeed());
				quitSchool.setStudentOwnFeed(student.getOwnFeed());
				quitSchool.setStudentType(student.getStudentType());
				quitSchool.setStudentTimingFlag(student.getTimingFlag());
				if(ValidateUtil.isValid(student.getTrainerName())){
					quitSchool.setTrainerName(student.getTrainerName());
				}
				quitSchool.setStudentLearningTime(student.getLearningTime());
				quitSchool.setStudentPassword(student.getPassword());
				quitschoolLists.add(quitSchool);
			}

		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(quitschoolLists);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);
	}

	// 根据学员ID查找学员信息
	public void getSingStudentInformation() throws Exception {
		super.writeJson(studentService.getSingleById(model.getStudentId()));
	}

}
