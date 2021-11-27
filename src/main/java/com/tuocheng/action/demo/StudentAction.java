package com.tuocheng.action.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.ClassDesign;
import com.tuocheng.pageModel.demo.Person;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.service.demo.ClassServiceI;
import com.tuocheng.service.demo.NetPiontServiceI;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.service.demo.PersonServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.FileUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学生实体类action
 * 
 * @author MEI702
 * 
 */
/**
 * @author TC-EWATCHER
 * 
 */
@Namespace("/demo")
@Action(value = "studentAction", results = { @Result(name = "student", location = "/demo/student/student.jsp"),
		@Result(name = "studentAdd", location = "/demo/student/studentAdd.jsp"),
		@Result(name = "studentUpdate", location = "/demo/student/studentUpdate.jsp"),
		@Result(name = "selectClazz", location = "/demo/student/selectClazz.jsp"),
		@Result(name = "selectTrainer", location = "/demo/student/selectTrainer.jsp"),
		@Result(name = "selectPerson", location = "/demo/student/selectPerson.jsp"),
		@Result(name = "myMessage", location = "/demo/student/myMessage.jsp"),
		@Result(name = "myMessageDetail", location = "/demo/student/myMessageDetail.jsp"),
		@Result(name = "trainerStudents", location = "/demo/student/trainerStudents.jsp"),
		@Result(name = "detail", location = "/demo/student/detail.jsp"),
		@Result(name = "uploadFile", location = "/demo/car/uploadFile.jsp"),
		@Result(name = "exportFile", type = "stream", params = { "contentType", "application/vnd.ms-excel",
				"contentDisposition", "attachment;filename=AllStudents.xls", "inputName",
				"excelFile" }, location = "/demo/car/exportFile.jsp") })
public class StudentAction extends BaseAction<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453040768665910421L;

	private static final Logger logger = Logger.getLogger(StudentAction.class);

	// 1.注入相应的service
	private StudentServiceI studentService;
	private ClassServiceI clazzService;
	private POIUitlServiceI poiService;
	private TrainerSerivceI trainerService;
	private PersonServiceI personService;
	private OrganizationServiceI organizationService;
	private NetPiontServiceI netPiontService;

	private InputStream excelFile;
	private File uploadfile;


	@Autowired
	public void setNetPiontService(NetPiontServiceI netPiontService) {
		this.netPiontService = netPiontService;
	}

	@Autowired
	// 注入service
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setClazzService(ClassServiceI clazzService) {
		this.clazzService = clazzService;
	}

	@Autowired
	public void setPoiService(POIUitlServiceI poiService) {
		this.poiService = poiService;
	}

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setPersonService(PersonServiceI personService) {
		this.personService = personService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	// 达到学员信息主页面
	public String toStudentPage() {
		return "student";
	}

	// 到达添加学员信息页面
	public String toStudentAddPage() {
		return "studentAdd";
	}

	// 打开选择班级页面信息
	public String toSelectTrainerPage() {
		return "selectTrainer";
	}

	// 打开选择班级页面信息
	public String toSelectPersonPage() {
		return "selectPerson";
	}

	// 打开选择班级页面信息
	public String toSelectClazzPage() {
		return "selectClazz";
	}

	public String toStudentUpdatePage() {
		return "studentUpdate";
	}

	public String toStudentDetailPage() {
		return "detail";
	}

	// 到达导入文件页面
	public String toUploadPage() {
		return "uploadFile";
	}

	// 到达导出文件页面
	public String toExportPage() {
		try {
			Workbook workbook = poiService.exportStdentDatas(user.getSchoolArea(), model.getIds());
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(output);
			byte[] ba = output.toByteArray();
			excelFile = new ByteArrayInputStream(ba);
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exportFile";
	}

	// 打开选择班级页面信息
	public String toMyMessagePage() {
		return "myMessage";
	}

	// 到达学员个人详细信息
	public String toMyMessageDetailPage() {
		return "myMessageDetail";
	}

	// 到达学员个人详细信息
	public String toTrainerStudentsPage() {
		return "trainerStudents";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setOrganizationName(user.getSchoolArea());

		}
		super.writeJson(studentService.datagrid(model));
	}

	// 学员个人数据列表
	public void studentMsgDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setOrganizationName(user.getSchoolArea());
			model.setIdentityId(user.getCname());
		}
		super.writeJson(studentService.studentDatagrid(model));
	}

	// 教练所带学员信息列表
	public void trainerStudentsDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setOrganizationName(user.getSchoolArea());
			model.setTrainerIdentity(user.getCname().trim());
		}
		super.writeJson(studentService.datagrid(model));
	}

	// 根据学员登录名查找学员信息
	public void getSingleStudent() throws Exception {
		super.writeJson(studentService.getStudentIdentityId(user.getCname().trim()));
	}

	// 添加学员信息
	public void add() throws Exception {
		Json json = new Json();
		model.setOperator(user.getCname());
		model.setOrganizationId(user.getSchoolArea());
		boolean temp = studentService.studentNoExistOrNot(model.getStudentNo());
		try {
			if (temp == true) {
				json.setMsg("学员编号已经存在，请重新输入学员编号");
				super.writeJson(json);
				return;
			} else {
				Student student = studentService.save(model);
				json.setMsg("添加学员信息成功！");
				json.setSuccess(true);
				// 将成功添加后的数据回传到前台
				json.setObj(student);
			}
		} catch (Exception e) {
			json.setMsg("添加学员信息失败！学员编号已经被占用，请《认证编号》后，再点添加！！！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 更新学员信息
	public void edit() {
		Json json = new Json();
		try {
			Student student = studentService.update(model);
			if (student != null) {
				json.setMsg("修改学员信息成功！");
			} else {
				json.setMsg("该学员与原教练存在未完成的预约，请取消相关预约信息后再更改教练！");
			}
			json.setSuccess(true);
			// 将成功修改后的数据回传到前台
			json.setObj(student);
		} catch (Exception e) {
			json.setMsg("修改学员信息失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(json);
	}

	// 删除学员信息
	public void delete() throws Exception {
		Json json = new Json();
		studentService.delete(model.getIds());
		json.setMsg("删除学员信息成功！");
		json.setSuccess(true);
		super.writeJson(json);

	}

	// 查找出所有班级信息.
	public void getAllClass() throws Exception {
		List<ComboboxJson> jsonLists = clazzService.getAllClass();
		super.writeJson(jsonLists);

	}

	// 查找出所有教练信息
	public void getAllTrainersForCombobox() throws Exception {
		List<ComboboxJson> jsonLists = null;
		if (ValidateUtil.isValid(model.getOrganizationId())) {
			jsonLists = trainerService.getAllTrainersForCombobox(model.getOrganizationId());
		}

		super.writeJson(jsonLists);
	}

	// 查找出所有教练信息
	public void getAllPersonsForCombobox() throws Exception {
		List<ComboboxJson> jsonLists = personService.getAllPersonsForCombobox();
		List<ComboboxJson> trainerLists = trainerService.getAllTrainersForCombobox(user.getSchoolArea());
		if (ValidateUtil.isValidListObject(jsonLists)) {
			for (ComboboxJson person : jsonLists) {
				trainerLists.add(person);
			}
		}
		super.writeJson(trainerLists);
	}

	// 根据查询条件查找班级信息
	@SuppressWarnings("unchecked")
	public void getClazzDatabyType() throws Exception {
		// 1.设置班级查询条件
		ClassDesign clazz = new ClassDesign();
		clazz.setType(model.getClazzType());
		clazz.setSchoolArea(model.getOrganizationId());

		clazz.setRows(model.getRows());
		clazz.setSort(model.getSort());
		clazz.setOrder(model.getOrder());
		clazz.setPage(model.getPage());
		// 2.根据查询条件获取班级信息
		DataGrid dataGrid = clazzService.dataGrid(clazz);
		// 3.对数据进行数据模型转换
		List<ClassDesign> tempClazzs = dataGrid.getRows();
		List<Student> retStudents = new ArrayList<Student>();
		if (tempClazzs != null && tempClazzs.size() > 0) {
			for (ClassDesign c : tempClazzs) {
				Student student = new Student();
				student.setClazzId(c.getId());
				student.setClazzName(c.getName());
				student.setClazzOrderNo(c.getOrderNo());
				student.setClazzType(c.getType());
				student.setClazzFee(c.getFee());
				student.setClazzCostTime(c.getCostTime());
				student.setClazzComment(c.getComment());
				student.setClazzClassType(c.getClassType());
				student.setClazzSchoolAreaName(organizationService.getSingleById(c.getSchoolArea()).getName());
				retStudents.add(student);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retStudents);
		retDatagrid.setTotal(dataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	// 根据查询条件查找教练信息
	@SuppressWarnings("unchecked")
	public void getTrainerDatabyType() throws Exception {
		// 1.设置班级查询条件
		Trainer trainer = new Trainer();
		trainer.setDriverCarType(model.getTrainerTechingType());
		trainer.setIdentity(model.getTrainerIdentity());
		trainer.setName(model.getTrainerName());
		trainer.setSchoolArea(model.getOrganizationId());
		// 添加车辆时查找未与车辆捆绑的教练
		if (model.getCarBanding() != null && model.getCarBanding() != 1) {
			trainer.setCarBanding(0);
		}

		trainer.setRows(model.getRows());
		trainer.setSort(model.getSort());
		trainer.setOrder(model.getOrder());
		trainer.setPage(model.getPage());
		// 2.根据查询条件获取班级信息
		DataGrid dataGrid = trainerService.datagrid(trainer);
		// 3.对数据进行数据模型转换
		List<Trainer> trainers = dataGrid.getRows();
		List<Student> retStudents = new ArrayList<Student>();
		if (trainers != null && trainers.size() > 0) {
			for (Trainer t : trainers) {
				Student student = new Student();
				student.setTrainerId(t.getId());

				student.setTrainerName(t.getName());
				student.setTrainerCodeNo(t.getCodeNo());
				// 教练编号后3位
				String subTrainerNo = t.getCodeNo();
				student.setTrainerSubCodeNo(subTrainerNo.substring(subTrainerNo.length() - 3, subTrainerNo.length()));

				student.setTrainerIdentity(t.getIdentity());
				student.setTrainerPhone(t.getPhone());
				student.setTrainerDriverLicenseId(t.getDriverLicenseId());
				student.setTrainerDriverType(t.getDriverType());
				student.setTrainerType(t.getTrainerType());
				// 显示教练所属于机构
				student.setTrainerSchoolAreaName(organizationService.getSingleById(t.getSchoolArea()).getName());
				retStudents.add(student);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retStudents);
		retDatagrid.setTotal(dataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	@SuppressWarnings("unchecked")
	public void getAllPersons() throws Exception {
		// 1.设置班级查询条件
		Person person = new Person();
		person.setId(model.getPersonId());
		person.setName(model.getPersonName());
		person.setIdentityId(model.getPersonIdentityId());
		person.setDepartmentId(model.getOrganizationId());
		person.setPropertyType(model.getPropertyType());

		person.setRows(model.getRows());
		person.setSort(model.getSort());
		person.setOrder(model.getOrder());
		person.setPage(model.getPage());
		// 2.根据查询条件获取班级信息
		DataGrid dataGrid = personService.dataGrid(person);
		// 3.对数据进行数据模型转换
		List<Person> persons = dataGrid.getRows();
		List<Student> retStudents = new ArrayList<Student>();
		if (persons != null && persons.size() > 0) {
			for (Person t : persons) {
				Student student = new Student();
				student.setPersonId(t.getId());
				student.setPersonName(t.getName());
				student.setPersonIdentityId(t.getIdentityId());
				student.setPersonPhone(t.getPhone());
				student.setPersonDuty(t.getDuty());
				student.setPersonalSchoolAreaName(t.getDepartmentId());
				retStudents.add(student);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retStudents);
		retDatagrid.setTotal(dataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	// 获取最大的学员编号
	public void getMaxStudentNo() throws Exception {
		String studentNo = studentService.getTheBiggerStudentNo(user, model.getStudentNoTypeVal());
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (ValidateUtil.isValid(studentNo)) {
			// 田阳校区学员编号中添加教练编号
			String headStr = studentNo.substring(0, (studentNo.length()) - 7);
			String endStr = studentNo.substring(studentNo.length() - 4, studentNo.length());
			Ttrainer trainer = trainerService.getRandomTrainer(user.getSchoolArea());
			if (trainer != null) {
				String trainerNo = trainer.getCodeNo();
				String trainerIdTemp = trainerNo.substring(trainerNo.length() - 3, trainerNo.length());
				String retStr = headStr + trainerIdTemp + endStr;
				retMap.put("studentNo", retStr);
				retMap.put("trainerId", trainer.getId());
				retMap.put("trainerType", trainer.getTrainerType());
				super.writeJson(retMap);
				return;
			}
			retMap.put("studentNo", studentNo);
			super.writeJson(retMap);
			return;
		}
		super.writeJson(null);
	}

	// 判断当前输入的学员编号是否重复
	public void studentNoExistOrNot() throws Exception {
		super.writeJson(studentService.studentNoExistOrNot(model.getStudentNo()));
	}

	// 身份证校验，不允许重复
	public void identityIdExistOrNot() throws Exception {
		super.writeJson(studentService.identityIdExistOrNot(model.getIdentityId()));
	}

	// 导入数据
	public void importStudentDatas() throws Exception {
		Json json = new Json();

		String directory = "/file";
		String fileName = uploadfile.getName();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		// 创建目标文件
		File target = FileUtil.UploadFile(uploadfile, fileName, targetDirectory);
		// 将文件以流的形式上传到服务器
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		ImportReturnModel reVals = studentService.importDatasFromExcel(wb, user.getSchoolArea(), user.getCname());

		// 组织返回参数
		if (reVals.isSuccess()) {// 导入成功，不存在重复字段
			json.setSuccess(true);
			json.setMsg("数据导入成功 ,合计导入：《" + reVals.getSize() + "》 条记录！");
		} else {
			json.setMsg("导入失败，学员编号，身份证号不允许重复：" + reVals.getMsg());
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	// 初始化校区报名网点
	public void toInitMyNetPiont() {
		try {
			super.writeJson(netPiontService.getMyNetPiont(user.getSchoolArea()));
		} catch (Exception e) {
		}
	}

	// 获取6位随机验证码
	public void toVerificationCode() {
		super.writeJson(StringUtil.getRandVerificationCode());
	}

	// ///////////////////////generating///////////////////////////
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

}
