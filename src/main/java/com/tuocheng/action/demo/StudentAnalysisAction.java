package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.StudentAnalysis;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 学员数据他析action
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "studentAnalysisAction")
public class StudentAnalysisAction extends BaseAction<StudentAnalysis> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4484366331150652363L;

	private StudentServiceI studentService;
	private TrainerSerivceI trainerService;
	private CarServiceI carService;

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setCarService(CarServiceI carService) {
		this.carService = carService;
	}


	// 根据校区标识查找学员信息
	public void getStudentBySchoolArea() {
		Json json = new Json();
		try {
			List<StudentAnalysis> comboList = new ArrayList<StudentAnalysis>();
			Map<String, Long> orgIdsMap = studentService
					.getStudentBySchoolArea(model.getOrganizationIds());
			for (Map.Entry<String, Long> entry : orgIdsMap.entrySet()) {
				StudentAnalysis stua = new StudentAnalysis();
				stua.setOrganizationId(entry.getKey().toString());
				stua.setTotals(entry.getValue());
				comboList.add(stua);
			}

			json.setObj(comboList);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 统计系统中的学员总人数
	public void getAllStudentCount() {
		Json json = new Json();
		try {
			Long studentTotal = studentService.getAllStudentCount();
			json.setObj(studentTotal);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据校区标识查找该校教练信息
	public void getTrainerBySchoolArea() {
		Json json = new Json();
		try {
			List<StudentAnalysis> comboList = new ArrayList<StudentAnalysis>();
			Map<String, Long> orgIdsMap = trainerService
					.getTrainerBySchoolArea(model.getOrganizationIds());
			for (Map.Entry<String, Long> entry : orgIdsMap.entrySet()) {
				StudentAnalysis stua = new StudentAnalysis();
				stua.setOrganizationId(entry.getKey().toString());
				stua.setTotals(entry.getValue());
				comboList.add(stua);
			}

			json.setObj(comboList);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 统计教练人数
	public void getAllTrainerCount() {
		Json json = new Json();
		try {
			Long trainerTotal = trainerService.getAllTrainerCount();
			json.setObj(trainerTotal);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据校区标识查找该校车辆信息
	public void getCarBySchoolArea() {
		Json json = new Json();
		try {
			List<StudentAnalysis> comboList = new ArrayList<StudentAnalysis>();
			Map<String, Long> orgIdsMap = carService.getCarBySchoolArea(model
					.getOrganizationIds());
			for (Map.Entry<String, Long> entry : orgIdsMap.entrySet()) {
				StudentAnalysis stua = new StudentAnalysis();
				stua.setOrganizationId(entry.getKey().toString());
				stua.setTotals(entry.getValue());
				comboList.add(stua);
			}

			json.setObj(comboList);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 统计车辆信息
	public void getAllCarCount() {
		Json json = new Json();
		try {
			Long trainerTotal = carService.getAllCarCount();
			json.setObj(trainerTotal);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据驾照类型统计教练总人数
	public void getTrainerCountByDriverType() {
		Json json = new Json();
		try {
			// 1.组织参数
			Map<String, Map<Integer, Long>> totalMap = trainerService
					.getCountByDriverType(model.getOrganizationIds());
			List<StudentAnalysis> retCombos = new ArrayList<StudentAnalysis>(0);

			// 2.配置参数
			for (Map.Entry<String, Map<Integer, Long>> entry : totalMap
					.entrySet()) {
				Map<Integer, Long> countEntry = entry.getValue();
				for (Integer key : countEntry.keySet()) {
					StudentAnalysis stua = new StudentAnalysis();
					stua.setSchoolId(entry.getKey());
					stua.setDriverTypeId(key);
					stua.setTotals(countEntry.get(key));
					retCombos.add(stua);
				}
			}
			json.setObj(retCombos);
			json.setSuccess(true);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据所属于校区，驾照类型统计学员总人数
	public void getStudentCountByDriverType() {
		Json json = new Json();
		try {
			// 1.组织参数
			Map<String, Map<Integer, Long>> totalMap = studentService
					.getCountByDriverType(model.getOrganizationIds());
			List<StudentAnalysis> retCombos = new ArrayList<StudentAnalysis>(0);

			// 2.配置参数
			for (Map.Entry<String, Map<Integer, Long>> entry : totalMap
					.entrySet()) {
				Map<Integer, Long> countEntry = entry.getValue();
				for (Integer key : countEntry.keySet()) {
					StudentAnalysis stua = new StudentAnalysis();
					stua.setSchoolId(entry.getKey());
					stua.setDriverTypeId(key);
					stua.setTotals(countEntry.get(key));
					retCombos.add(stua);
				}
			}
			json.setObj(retCombos);
			json.setSuccess(true);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据年月周日对学员报名进行统计
	public void getSignupStudent() {
		Json json = new Json();
		try {
			Date date = new Date();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			// 1.组织参数
			Map<String, Map<Integer, Long>> totalMap = studentService
					.getStudentCountByYYWD(model.getOrganizationIds(), date);

			List<StudentAnalysis> retCombos = new ArrayList<StudentAnalysis>(0);
			// 2.配置参数
			for (Map.Entry<String, Map<Integer, Long>> entry : totalMap
					.entrySet()) {
				Map<Integer, Long> countEntry = entry.getValue();
				for (Integer key : countEntry.keySet()) {
					StudentAnalysis stua = new StudentAnalysis();
					stua.setSchoolId(entry.getKey());
					stua.setDataType(key);
					stua.setTotals(countEntry.get(key));
					retCombos.add(stua);
				}
			}
			json.setObj(retCombos);
			json.setSuccess(true);
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	
}
