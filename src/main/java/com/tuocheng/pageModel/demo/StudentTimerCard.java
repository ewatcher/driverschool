package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.alibaba.fastjson.JSON;


/**
 * 学员计时卡实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class StudentTimerCard extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;
	
	private String id;
	private Integer driverTypeId; // 驾照类型ID
	private String driverTypeName; // 驾照类型
	private String studentId; // 学员ID
	private String studentNo;  // 学员编号
	private String studentName; // 学员名
	private Integer totalHours; // 总学时
	private Integer oneHours; // 科目1学时
	private Integer twosTheoryHours; // 科目2理论学时
	private Integer twosOperateHours; // 科目2实操学时
	private Integer twosSimulatorHours; // 科目2模拟学时
	private Integer threesTheoryHours; // 科目3理论学时
	private Integer threesOperateHours; // 科目3实操学时
	private Integer threesSimulatorHours; // 科目3模拟学时
	
	private Date createTimeEnd; // 注册时间－－－范围查询
	private Date createTimeStart; // 注册时间－－－范围查询
	private String identityId; // 身份证号
	private String name; // 姓名
	private Integer nowState; // 学员在学状态
	private String organizationId; // 机构
	private String schoolArea;//所属校区
	
	private String ids;
	
	public StudentTimerCard(){
	}

	public StudentTimerCard(String id, Integer driverTypeId,
			String driverTypeName, String studentId, String studentName,
			Integer totalHours, Integer oneHours, Integer twosTheoryHours,
			Integer twosOperateHours, Integer twosSimulatorHours,
			Integer threesTheoryHours, Integer threesOperateHours,
			Integer threesSimulatorHours, Date createTimeEnd,
			Date createTimeStart, String identityId, String name,
			Integer nowState, String organizationId, String ids) {
		super();
		this.id = id;
		this.driverTypeId = driverTypeId;
		this.driverTypeName = driverTypeName;
		this.studentId = studentId;
		this.studentName = studentName;
		this.totalHours = totalHours;
		this.oneHours = oneHours;
		this.twosTheoryHours = twosTheoryHours;
		this.twosOperateHours = twosOperateHours;
		this.twosSimulatorHours = twosSimulatorHours;
		this.threesTheoryHours = threesTheoryHours;
		this.threesOperateHours = threesOperateHours;
		this.threesSimulatorHours = threesSimulatorHours;
		this.createTimeEnd = createTimeEnd;
		this.createTimeStart = createTimeStart;
		this.identityId = identityId;
		this.name = name;
		this.nowState = nowState;
		this.organizationId = organizationId;
		this.ids = ids;
	}

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

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNowState() {
		return nowState;
	}

	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}

}
