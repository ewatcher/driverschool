package com.tuocheng.service.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface POIUitlServiceI {
	/**
	 * 通过poi导出车辆信息
	 */
	public HSSFWorkbook exportCarDatas(String schoolArea, String ids)
			throws Exception;

	/**
	 * 根据学员标识导出学员信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook exportStdentDatas(String schoolArea, String ids)
			throws Exception;

	/**
	 * 通过POI导入车辆信息
	 */
	public void importCarDatas(String path) throws Exception;

	public void importStudentDatas(String path) throws Exception;

	/**
	 * 导出考试学员报考信息
	 * 
	 * @param schoolArea
	 * @param ids
	 * @param subjectType
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook exportStudentExamBySubjectType(String schoolArea,
			String ids, Integer subjectType) throws Exception;

}
