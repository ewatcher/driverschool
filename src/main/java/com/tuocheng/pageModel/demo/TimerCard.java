package com.tuocheng.pageModel.demo;

/**
 * 车辆管理实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class TimerCard extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;

	private String id;
	private Integer driverTypeId; // 驾照类型ID
	private String driverTypeName; // 驾照类型
	private Integer totalHours; // 总学时
	private Integer oneHours; // 科目1学时
	private Integer twosTheoryHours; // 科目2理论学时
	private Integer twosOperateHours; // 科目2实操学时
	private Integer twosSimulatorHours; // 科目2模拟学时
	private Integer threesTheoryHours; // 科目3理论学时
	private Integer threesOperateHours; // 科目3实操学时
	private Integer threesSimulatorHours; // 科目3模拟学时
	private String studentNo;// 学员编号

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDriverTypeId() {
		return driverTypeId;
	}

	public void setDriverTypeId(Integer driverTypeId) {
		this.driverTypeId = driverTypeId;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public Integer getOneHours() {
		return oneHours;
	}

	public void setOneHours(Integer oneHours) {
		this.oneHours = oneHours;
	}

	public Integer getTwosTheoryHours() {
		return twosTheoryHours;
	}

	public void setTwosTheoryHours(Integer twosTheoryHours) {
		this.twosTheoryHours = twosTheoryHours;
	}

	public Integer getTwosOperateHours() {
		return twosOperateHours;
	}

	public void setTwosOperateHours(Integer twosOperateHours) {
		this.twosOperateHours = twosOperateHours;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getTwosSimulatorHours() {
		return twosSimulatorHours;
	}

	public void setTwosSimulatorHours(Integer twosSimulatorHours) {
		this.twosSimulatorHours = twosSimulatorHours;
	}

	public Integer getThreesTheoryHours() {
		return threesTheoryHours;
	}

	public void setThreesTheoryHours(Integer threesTheoryHours) {
		this.threesTheoryHours = threesTheoryHours;
	}

	public Integer getThreesOperateHours() {
		return threesOperateHours;
	}

	public void setThreesOperateHours(Integer threesOperateHours) {
		this.threesOperateHours = threesOperateHours;
	}

	public Integer getThreesSimulatorHours() {
		return threesSimulatorHours;
	}

	public void setThreesSimulatorHours(Integer threesSimulatorHours) {
		this.threesSimulatorHours = threesSimulatorHours;
	}

	public String getDriverTypeName() {
		return driverTypeName;
	}

	public void setDriverTypeName(String driverTypeName) {
		this.driverTypeName = driverTypeName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
