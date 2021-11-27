package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.CarusingTemp;
import com.tuocheng.pageModel.demo.UsingCar;
import com.tuocheng.service.demo.AutoCarArrangeServiceI;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.CarusingTempServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.service.demo.UsingCarServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆管理action
 * 
 * @author MEI702
 * 
 */

@Namespace("/demo")
@Action(value = "usingCarAction", results = {
		@Result(name = "usingCar", location = "/demo/usingCar/usingCar.jsp"),
		@Result(name = "usingCarAdd", location = "/demo/usingCar/usingCarAdd.jsp"),
		@Result(name = "usingCarEdit", location = "/demo/usingCar/usingCarEdit.jsp"),
		@Result(name = "selectCarInput", location = "/demo/usingCar/selectCarInput.jsp"),
		@Result(name = "selectTrainerInput", location = "/demo/usingCar/selectTrainerInput.jsp"),
		@Result(name = "usingCarDetail", location = "/demo/usingCar/usingCarDetail.jsp"),
		@Result(name = "autoCar", location = "/demo/usingCar/autoCar.jsp"), })
public class UsingcarAction extends BaseAction<CarusingTemp> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4736894377398372683L;
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(UsingcarAction.class);

	private UsingCarServiceI usingCarService;
	private TrainerSerivceI trainerService;
	private CarusingTempServiceI carusingService;
	private CarServiceI carService;
	private AutoCarArrangeServiceI autoCarArrangeService;

	@Autowired
	public void setUsingCarService(UsingCarServiceI usingCarService) {
		this.usingCarService = usingCarService;
	}

	@Autowired
	public void setCarusingService(CarusingTempServiceI carusingService) {
		this.carusingService = carusingService;
	}

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setCarService(CarServiceI carService) {
		this.carService = carService;
	}

	@Autowired
	public void setAutoCarArrangeService(
			AutoCarArrangeServiceI autoCarArrangeService) {
		this.autoCarArrangeService = autoCarArrangeService;
	}

	// 到达car主页面
	public String toUsingCarPage() {
		return "usingCar";
	}

	// 到达添加页面
	public String toAddPage() {
		return "usingCarAdd";
	}

	// 到达更新页面
	public String toEditPage() {
		return "usingCarEdit";
	}

	// 到达更新页面
	public String toUsingCarDetailPage() {
		return "usingCarDetail";
	}

	// 到达选择车辆信息页面
	public String toSelectCarInputPage() {
		return "selectCarInput";
	}

	// 到达选择教练信息页面
	public String toSelectTrainerInputPage() {
		return "selectTrainerInput";
	}

	// 到达自动档排班主页面
	public String toAutoCarPage() {
		return "autoCar";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(carusingService.dataGrid(model));
	}

	// 获取自动档车辆信息
	@SuppressWarnings("unchecked")
	public void getAutoCarDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		DataGrid retDatGrid = new DataGrid();
		List<CarusingTemp> retsList = new ArrayList<CarusingTemp>(0);

		DataGrid autoDataGrid = carusingService.dataGrid(model);
		List<CarusingTemp> carusingList = autoDataGrid.getRows();
		if (carusingList != null && carusingList.size() > 0) {
			for (CarusingTemp temp : carusingList) {
				Tcar car = carService.getSingleById(temp.getCarId());
				if (car != null && Tcar.CAR_USINGTYPE_C2 == car.getCarType()) {
					temp.setLastArrangeDate(autoCarArrangeService
							.getNealyArrageDateByCarId(car.getId()));
					retsList.add(temp);
				}
			}
		}
		// 返回参数
		retDatGrid.setTotal(new Long((long) retsList.size()));
		retDatGrid.setRows(retsList);
		retDatGrid.setFooter(autoDataGrid.getFooter());

		super.writeJson(retDatGrid);
	}

	/**
	 * 根据校区标识获取车辆信息
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getCarusingDetailByCarId() throws Exception {
		// TODO 筛选学员参数设计
		// 1.根据student的model查找出所有符合查询条件的学员信息
		UsingCar entry = new UsingCar();
		entry.setSchoolArea(model.getSchoolArea());
		entry.setCarId(model.getCarId());

		entry.setRows(model.getRows());
		entry.setSort(model.getSort());
		entry.setOrder(model.getOrder());
		entry.setPage(model.getPage());

		DataGrid myDataGrid = usingCarService.dataGrid(entry);
		List<UsingCar> temps = myDataGrid.getRows();

		List<CarusingTemp> retCars = new ArrayList<CarusingTemp>();
		if (temps != null && temps.size() > 0) {
			for (UsingCar temp : temps) {
				CarusingTemp carusingTemp = new CarusingTemp();
				// 数据模型转换
				carusingTemp.setId(carusingTemp.getUsingCarId());
				carusingTemp.setUsingCarId(temp.getId());
				carusingTemp.setUsingCarNumbering(temp.getNumbering());
				carusingTemp.setUsingCarUsingDate(temp.getUsingDate());
				carusingTemp.setUsingCarReason(temp.getReason());
				carusingTemp.setUsingCarPlace(temp.getPlace());
				carusingTemp.setUsingCarStartTime(temp.getStartTime());
				carusingTemp.setUsingCarEndTime(temp.getEndTime());
				carusingTemp.setUsingCarStartMile(temp.getStartMile());
				carusingTemp.setUsingCarEndMile(temp.getEndMile());
				carusingTemp.setUsingCarUsingMile(temp.getUsingMile());
				carusingTemp.setSchoolAreaName(temp.getSchoolAreaName());
				carusingTemp.setCarNo(model.getCarNo());
				carusingTemp.setTrainerName(model.getTrainerName());
				carusingTemp.setUsingCarUsingState(temp.getUsingcarState());

				retCars.add(carusingTemp);

			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retCars);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			usingCarService.delete(model.getIds());
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}
