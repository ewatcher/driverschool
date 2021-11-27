package com.tuocheng.service.demo;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.StudentExam;

/**
 * 学员考试理实体类
 * 
 * @author 农峰
 * 
 */
public interface StudentExamServiceI {
	/**
	 * 添加学员考试信息信息
	 * 
	 * @param studentExam
	 * @return
	 */
	public StudentExam save(StudentExam studentExam) throws Exception;

	/**
	 * 更新学员考试信息信息
	 * 
	 * @param studentExam
	 * @return
	 */
	public StudentExam udpate(StudentExam studentExam) throws Exception;

	/**
	 * 根据id标识删除学员考试信息信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有学员考试信息的信息，并以datagrid开发传递到前台
	 * 
	 * @param studentExam
	 * @return
	 */
	public DataGrid dataGrid(StudentExam studentExam) throws Exception;

	/**
	 * 导入考试学员明细信息
	 * 
	 * @param wb
	 * @param schoolArea
	 * @param operatorName
	 * @param batch学员批次
	 * @return
	 * @throws Exception
	 */
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName,
			String examAnalysId) throws Exception;

	/**
	 * 从后台选择学员输入考试批次中
	 * 
	 * @param studentExam
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentToExamAnalys(StudentExam studentExam) throws Exception;

	/**
	 * 删除考试学员信息
	 * 
	 * @param ids
	 */
	public void deleteStudentExam(String ids);

	/**
	 * 移动学员到目标批次
	 * 
	 * @param studentExam
	 * @return
	 * @throws Exception
	 */
	public boolean saveStudentToNewBatch(StudentExam studentExam) throws Exception;

	/**
	 * 批量修改考试学员信息
	 * 
	 * @param studentExam
	 * @throws Exception
	 */
	public void batchHandupExam(StudentExam studentExam) throws Exception;

	/**
	 * 取消批量报考
	 * 
	 * @param ids
	 * @param subjectType
	 * @return
	 * @throws Exception
	 */
	public boolean batchCancelHandup(String ids, Integer subjectType) throws Exception;

	/**
	 * 根据批次查找所有当前批次的考试学员信息
	 * 
	 * @param batch
	 * @param SchoolArea
	 * @return
	 * @throws Exception
	 */
	public List<TstudentExam> getStudentExamByBatch(Integer batch, String SchoolArea) throws Exception;

	/**
	 * 根据学员信息，查找出该学员的考试信息
	 * 
	 * @param schoolArea
	 * @param studentNo
	 * @param studentName
	 * @param studentIdentityId
	 * @return
	 * @throws Exception
	 */
	public StudentExam getStudentExamForRepeatSearch(String schoolArea, String studentNo, String studentName,
			String studentIdentityId) throws Exception;

	/**
	 * 导入学员成绩信息
	 * @param wb
	 * @param schoolArea
	 * @param operatorName
	 * @param examAnalysId（考试批次标识）
	 * @param subjectType（科目类型）
	 * @return
	 * @throws Exception
	 */
	public ImportReturnModel importStudentScore(Workbook wb, String schoolArea, String operatorName,
			String examAnalysId, Integer subjectType) throws Exception;
	/**
	 * 根据校区标识，学员身份证查找考试学员信息
	 * @param schoolArea
	 * @param identityId
	 * @return
	 */
	public TstudentExam getByStudentIdentityId(String schoolArea,String identityId);
}
