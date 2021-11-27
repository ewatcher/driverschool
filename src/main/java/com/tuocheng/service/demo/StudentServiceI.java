package com.tuocheng.service.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.User;

public interface StudentServiceI {

	/**
	 * 添加学员信息
	 * 
	 * @param student
	 * @return
	 */
	public Student save(Student student) throws Exception;

	/**
	 * 将后台数据以datagrid方式传递给前台
	 * 
	 * @param student
	 * @return
	 */
	public DataGrid datagrid(Student student) throws Exception;

	/**
	 * 将后台数据以datagrid方式传递给前台
	 * 
	 * @param student
	 * @return
	 */
	public DataGrid studentDatagrid(Student student) throws Exception;

	/**
	 * 根据ID查询学员信息
	 * 
	 * @param id
	 * @return
	 */
	public Tstudent getSingleById(String id) throws Exception;

	/**
	 * 根据登录用户查询学员信息
	 * 
	 * @param id
	 * @return
	 */
	public Tstudent getSingleByUser(User user) throws Exception;

	/**
	 * 删除学员信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 * @return
	 */
	public Student update(Student student) throws Exception;

	public List<Student> combobox(Student student) throws Exception;

	/**
	 * 根据学员状态查找出所有与此状态有关的学员信息
	 * 
	 * @param nowstate
	 * @return
	 */
	public List<Student> getAllStudentByNowState(String nowstate) throws Exception;

	/**
	 * 根据学员姓名及学员编号（目前学员编号没有暂时用身份证号代替）获取用户信息
	 * 
	 * @param name
	 * @param keyStr
	 * @return
	 */
	public Tstudent getStudentByNameAndKey(String name, String keyStr) throws Exception;

	/**
	 * 根据校区ID统计学员人数
	 * 
	 * @param schoolId
	 * @return
	 */
	public Map<String, Long> getStudentBySchoolArea(String schoolIds) throws Exception;

	/**
	 * 统计所有学员人数
	 * 
	 * @return
	 */
	public Long getAllStudentCount() throws Exception;

	/**
	 * 获取最大的学员编号（目的：取出系统当前最大学员编号后加1，给添加学员页面提供默认的学员编号，减少人工输入造成的错误）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTheBiggerStudentNo(User user, Integer studentNoTypeVal) throws Exception;

	/**
	 * 较验系统当前是否存在该学员编号值
	 * 
	 * @param studentNo
	 * @return
	 */
	public boolean studentNoExistOrNot(String studentNo) throws Exception;

	/**
	 * 较验系统当前身份证号是否重复
	 * 
	 * @param studentNo
	 * @return
	 */
	public boolean identityIdExistOrNot(String identityId) throws Exception;

	/**
	 * 根据学员标识查找出该学员的教练信息
	 * 
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Ttrainer getTrainerByStudentId(String studentId) throws Exception;

	/**
	 * 根据学员标识，查找出该学员的业务员信息
	 * 
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Tperson getPersonByStudentId(String studentId) throws Exception;

	/**
	 * 根据学员身份证号查找出该学员的教练信息
	 * 
	 * @param identityId
	 * @return
	 * @throws Exception
	 */
	public Student getStudentIdentityId(String identityId) throws Exception;

	/**
	 * 根据教练标识查找该教练所带的所有学员信息
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<ComboboxJson> getStudentByItTrainerId(String trainerId);

	/**
	 * 根据所属校区及驾照类型统计学员总人数
	 * 
	 * @return
	 */
	public Map<String, Map<Integer, Long>> getCountByDriverType(String schoolIds);

	/**
	 * 根据学员标识，日期对学员报名进行统计
	 * 
	 * @param schoolIds
	 * @param date
	 * @return
	 */
	public Map<String, Map<Integer, Long>> getStudentCountByYYWD(String schoolIds, Date date);

	/**
	 * 将excel表中学员数据转换为Student数据模型导入
	 * 
	 * @param wb
	 * @param schoolArea
	 * @param operatorName
	 * @return
	 * @throws Exception
	 */
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName) throws Exception;

	/**
	 * 根据校区标识统计报名人数
	 * 
	 * @return（May中第二个Sting 的key为：dateCount,weekCount,monthCout,yearCount）
	 * @throws ParseException
	 */
	public Map<String, Integer> getStudentCountForWechat() throws ParseException;

	/**
	 * 获取当前天所在的周日期
	 * 
	 * @param(2:表示本周，3表示上周) @return（返回该星期的日期字符串） @throws ParseException
	 */
	public List<String> getWeekDays(Integer weekFlag) throws ParseException;

	/**
	 * 根据校区标识，按星期统计返回结果（例如：2017-2-15:50人，2017-2-26：80人）
	 * 
	 * @param (1:表示下一周，2:表示本周，3：表示上一周)
	 * @return （May中第二个Sting
	 *         的key为：Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday）
	 * @throws ParseException
	 */
	public Map<String, Map<String, Integer>> getWeekCountByOrgIds(Integer weekFlag) throws ParseException;

	/**
	 * 按日，周，月，年统计报名总数
	 * 
	 * @param type
	 *            (1:表示按日统计,2:表示按周统计)
	 * @param firstDayStr
	 *            (如：firstDateOfWeek,firstDateOfLastWeek,firstDateOfMonth,
	 *            firstDate)
	 * @param lastDateStr
	 * @return
	 * @throws ParseException
	 */
	public List<ComboboxJson> getStudentCoutByDateType(Integer type, String sql, Integer functionFlag,
			Integer firstDateFlay, Integer lastDateFlay) throws ParseException;

	/**
	 * 根据校区标识查找出所有该校区的所有内部学员
	 * 
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public List<Student> getInsideBySchoolArea(String schoolArea) throws Exception;

	/**
	 * 按周明细统计学员财务状况
	 * 
	 * @param weekFlag
	 *            (2:表示本周，3：表示上一周)
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Map<String, Double>> getMoneyWeekCountByOrgIds(Integer weekFlag) throws ParseException;

	/**
	 * 根据学员标识初始化显示学员编号
	 * 
	 * @param studentId
	 * @return (如：F0008421:农峰)
	 * @throws ParseException
	 */
	public String getStudentNoAndName(String studentId) throws Exception;

	/**
	 * 根据教练信息，查找出与该教练有关的学员信息
	 * 
	 * @param schoolArea
	 * @param trainerId
	 * @param trainerType
	 * @return
	 * @throws Exception
	 */
	public List<Student> getStudentsByTrainer(String schoolArea, String trainerId, Integer trainerType)
			throws Exception;

	/**
	 * 根据校区标识查找出所有的人员 信息
	 * 
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public List<Tperson> getAllPersons(String schoolArea) throws Exception;

}
