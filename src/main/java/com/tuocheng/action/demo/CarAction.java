package com.tuocheng.action.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Car;
import com.tuocheng.service.demo.AccessControlListServiceI;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.FileUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆管理action
 * 
 * @author MEI702
 * 
 */

@Namespace("/demo")
@Action(value = "carAction", results = { @Result(name = "car", location = "/demo/car/car.jsp"),
		@Result(name = "carAdd", location = "/demo/car/carAdd.jsp"),
		@Result(name = "carEdit", location = "/demo/car/carEdit.jsp"),
		@Result(name = "uploadFile", location = "/demo/car/uploadFile.jsp"),
		@Result(name = "exportFile", type = "stream", params = { "contentType", "application/vnd.ms-excel",
				"contentDisposition", "attachment;filename=AllCars.xls", "inputName",
				"excelFile" }, location = "/demo/car/exportFile.jsp") })
public class CarAction extends BaseAction<Car> implements ServletContextAware {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4736894377398372683L;
	private static final Logger log = Logger.getLogger(CarAction.class);

	private CarServiceI carService;
	private POIUitlServiceI poiService;

	private InputStream excelFile;
	private File uploadfile;
	private String uploadfileName;
	private ServletContext sc;
	private AccessControlListServiceI aclService;

	@Autowired
	public void setAclService(AccessControlListServiceI aclService) {
		this.aclService = aclService;
	}

	@Autowired
	public void setCarService(CarServiceI carService) {
		this.carService = carService;
	}

	@Autowired
	public void setPoiService(POIUitlServiceI poiService) {
		this.poiService = poiService;
	}

	// 到达car主页面
	public String car() {
		return "car";
	}

	// 到达添加页面
	public String toAddPage() {
		return "carAdd";
	}

	// 到达更新页面
	public String toEditPage() {
		return "carEdit";
	}

	// 到达导入文件页面
	public String toUploadPage() {

		return "uploadFile";
	}

	// 到达导出文件页面
	public String toExportPage() {
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				Workbook workbook = poiService.exportCarDatas(user.getSchoolArea(), model.getIds());
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				workbook.write(output);
				byte[] ba = output.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				output.flush();
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exportFile";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		List<TreeNode> list=aclService.getTreeNodeByMainMenuId("0200-4c26-92a6-driver170200","nongfeng-4f14-4625-bf58-7b20171688");
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(carService.dataGrid(model));
	}

	// 添加实体类信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			Car c = carService.save(model);
			json.setSuccess(true);
			json.setMsg("添加信息成功！");
			json.setObj(c);
		} catch (Exception e) {
			json.setMsg("添加信息失败！" + e.getMessage());
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			Car c = carService.update(model);
			json.setSuccess(true);
			json.setMsg("更新车辆信息成功！");
			json.setObj(c);
		} catch (Exception e) {
			json.setMsg("更新车辆信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			carService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除实体信息成功！");
		} catch (Exception e) {
			json.setMsg("删除信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 导入车辆数据
	public void importCarDatas() throws Exception {

		String directory = "/file";
		String fileName = uploadfile.getName();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = FileUtil.UploadFile(uploadfile, fileName, targetDirectory);
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		ImportReturnModel reVals = carService.importDatasFromExcel(wb, user.getSchoolArea(), user.getCname());
		Json json = new Json();

		// 组织返回参数
		if (reVals.isSuccess()) {// 导入成功，不存在重复字段
			json.setSuccess(true);
			json.setMsg("数据导入成功 ,合计导入：《" + reVals.getSize() + "》 条记录！");
		} else {
			json.setMsg("导入失败，车牌号，行驶证编号不允许重复：" + reVals.getMsg());
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	public void getAllCarsForCombobox() {
		List<ComboboxJson> jsonLists = null;
		if (ValidateUtil.isValid(model.getSchoolArea())) {
			jsonLists = carService.getAllCarsForCombobox(model.getSchoolArea());
		}

		super.writeJson(jsonLists);
	}

	/**
	 * 注入
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

	// ////////////////////////////////
	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileName() {
		return uploadfileName;
	}

	public void setUploadfileName(String uploadfileName) {
		this.uploadfileName = uploadfileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

}
