package com.tuocheng.action.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Trainer;
/**
 * 
 * @author MEI702
 *
 */
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.FileUtil;
import com.tuocheng.util.base.ValidateUtil;

@Namespace("/demo")
@Action(value = "trainerAction", results = {
		@Result(name = "trainer", location = "/demo/trainer/trainer.jsp"),
		@Result(name = "trainerAdd", location = "/demo/trainer/trainerAdd.jsp"),
		@Result(name = "trainerUpdate", location = "/demo/trainer/trainerUpdate.jsp"),
		@Result(name = "detail", location = "/demo/trainer/detail.jsp"),
		@Result(name = "myDetail", location = "/demo/trainer/myDetail.jsp"),
		@Result(name = "uploadFile", location = "/demo/car/uploadFile.jsp"),
		@Result(name = "myTrainer", location = "/demo/trainer/myTrainer.jsp"), })
public class TrainerAction extends BaseAction<Trainer> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5671229218093101057L;
	public static final Logger logger = Logger.getLogger(TrainerAction.class);

	// 注入相关的service
	TrainerSerivceI trainerService;

	private InputStream excelFile;
	private File uploadfile;

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	// 达到添加页面
	public String toAddPage() {
		return "trainerAdd";
	}

	// 达到更新页面
	public String toUpdatePage() {
		return "trainerUpdate";
	}

	// 达到详细信息页面
	public String toDetailPage() {
		return "detail";
	}

	// 达到教练信息主页
	public String toTrainerPage() {
		return "trainer";
	}

	// 到达教练个人详细信息
	public String toMyDetailPage() {
		return "myDetail";
	}

	// 到达教练个人页面
	public String toMyTrainerPage() {
		return "myTrainer";
	}

	// 到达导入文件页面
	public String toUploadPage() {
		return "uploadFile";
	}

	// 调用后台数据并传递给前台,
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(trainerService.datagrid(model));
	}

	public void personalDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
			model.setIdentity(user.getCname().trim());
		}
		super.writeJson(trainerService.personalDatagrid(model));
	}

	// 添加教练信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			Trainer trainer = trainerService.save(model);
			json.setMsg("添加教练信息成功！");
			json.setSuccess(true);
			// 将成功添加后的数据回传到前台
			json.setObj(trainer);
		} catch (Exception e) {
			json.setMsg("添加教练信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 更新教练信息
	public void edit() {
		Json json = new Json();
		try {
			Trainer student = trainerService.update(model);
			json.setMsg("修改教练信息成功！");
			json.setSuccess(true);
			// 将成功修改后的数据回传到前台
			json.setObj(student);
		} catch (Exception e) {
			json.setMsg("修改教练信息失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(json);
	}

	// 删除教练信息
	public void delete() throws Exception {
		Json json = new Json();
		try {
			trainerService.delete(model.getIds());
			json.setMsg("删除教练信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除教练信息失败！当前教练与学员存在关联不可删除");
			e.printStackTrace();
		}
		super.writeJson(json);

	}

	// 身份证校验，不允许重复
	public void identityIdExistOrNot() throws Exception {
		super.writeJson(trainerService.identityIdExistOrNot(model.getIdentity()
				.trim()));
	}

	// 获取最大的教練编号
	public void getMaxTrainerNo() throws Exception {
		super.writeJson(trainerService.getBiguestTrainerNO(user));
	}

	// 导入教练数据
	public void importTrainerDatas() throws Exception {

		String directory = "/file";
		String fileName = uploadfile.getName();
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		File target = FileUtil
				.UploadFile(uploadfile, fileName, targetDirectory);
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		ImportReturnModel reVals = trainerService.importDatasFromExcel(wb,
				user.getSchoolArea(), user.getCname());

		// 组织返回参数
		Json json = new Json();
		if (reVals.isSuccess()) {// 导入成功，不存在重复字段
			json.setSuccess(true);
			json.setMsg("数据导入成功 ,合计导入：《" + reVals.getSize() + "》 条记录！");
		} else {
			json.setMsg("导入失败，教练编号，身份证号不允许重复：" + reVals.getMsg());
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	// //////////////generating/////////////////
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
