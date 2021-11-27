package com.tuocheng.service.demo.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sound.midi.VoiceStatus;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tassess;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.Tclass;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.util.base.HSSFUtils;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 数据导入导出工具类
 * 
 * @author MEI702
 * 
 */
@Service("poiService")
public class POIUtilServiceImpl implements POIUitlServiceI {

	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Tclass> clazzDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TstudentExam> studentExamDao;

	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setClazzDao(BaseDaoI<Tclass> clazzDao) {
		this.clazzDao = clazzDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Override
	public HSSFWorkbook exportCarDatas(String schoolArea, String ids) {
		List<Tcar> cars = this.getAllCarDatas(schoolArea, ids);
		// 1.excel表格
		// 1).创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2).创建工作表
		HSSFSheet sheet = wb.createSheet("车辆基本信息");

		HSSFHeader header = sheet.getHeader();
		header.setCenter("车辆基本信息管理");

		// 设置列宽
		sheet.setColumnWidth(0, 4200);
		sheet.setColumnWidth(1, 5200);
		sheet.setColumnWidth(2, 5200);
		sheet.setColumnWidth(3, 5200);
		sheet.setColumnWidth(4, 5200);
		sheet.setColumnWidth(5, 5200);
		sheet.setColumnWidth(6, 5200);
		sheet.setColumnWidth(7, 5200);
		sheet.setColumnWidth(8, 5200);
		sheet.setColumnWidth(9, 5200);
		sheet.setColumnWidth(10, 13200);

		// 冻结窗格
		// 1:左侧冻结的列数
		// 2:上侧冻结的行数
		// 3:右侧窗格开始显示列的索引
		// 4:下侧窗格开始显示行的索引
		sheet.createFreezePane(2, 2, 2, 2);

		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 10);
		sheet.addMergedRegion(cra);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);// 双实线
		headStyle.setTopBorderColor(HSSFColor.BLUE.index);
		// 3).创建行
		// 设置表头
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);

		cell.setCellValue(new HSSFRichTextString("车辆基本信息管理"));
		cell.setCellStyle(style);

		row = sheet.createRow(1);
		// 4).创建单元格
		row.createCell(0).setCellValue(new HSSFRichTextString("车牌号"));
		row.createCell(1).setCellValue(new HSSFRichTextString("行驶证号"));
		row.createCell(2).setCellValue(new HSSFRichTextString("车辆品牌"));
		row.createCell(3).setCellValue(new HSSFRichTextString("车辆型号"));
		row.createCell(4).setCellValue(new HSSFRichTextString("运输证号"));
		row.createCell(5).setCellValue(new HSSFRichTextString("购买日期"));
		row.createCell(6).setCellValue(new HSSFRichTextString("商业保险日期"));
		row.createCell(7).setCellValue(new HSSFRichTextString("交强险日期 "));
		row.createCell(8).setCellValue(new HSSFRichTextString("维护日期 "));
		row.createCell(9).setCellValue(new HSSFRichTextString("年审日期 "));
		row.createCell(10).setCellValue(new HSSFRichTextString("备注"));
		row.setRowStyle(headStyle);

		// 创建数据格式对象
		HSSFDataFormat format = wb.createDataFormat();
		// 格式化数据
		HSSFCellStyle style1 = wb.createCellStyle();
		// 设置样式的数据格式,对日期格式化
		style1.setDataFormat(format.getFormat("yyyy-MM-dd"));

		if (cars != null && cars.size() > 0) {
			for (int i = 0; i < cars.size(); i++) {
				Tcar t = cars.get(i);
				row = sheet.createRow(i + 2);
				if (ValidateUtil.isValid(t.getCarNo())) {
					row.createCell(0).setCellValue(new HSSFRichTextString(t.getCarNo()));
				}
				if (ValidateUtil.isValid(t.getLicenseNo())) {
					row.createCell(1).setCellValue(new HSSFRichTextString(t.getLicenseNo()));
				}
				if (ValidateUtil.isValid(t.getVehicleBrands())) {
					row.createCell(2).setCellValue(new HSSFRichTextString(t.getVehicleBrands()));
				}
				if (ValidateUtil.isValid(t.getVehicleType())) {
					row.createCell(3).setCellValue(new HSSFRichTextString(t.getVehicleType()));
				}
				if (ValidateUtil.isValid(t.getTransportNo())) {
					row.createCell(4).setCellValue(new HSSFRichTextString(t.getTransportNo()));
				}
				if (t.getBuyDate() != null) {
					row.createCell(5).setCellValue(t.getBuyDate());
					row.getCell(5).setCellStyle(style1);
				}
				if (t.getBusinessExpireDay() != null) {
					row.createCell(6).setCellValue(t.getBusinessExpireDay());
					row.getCell(6).setCellStyle(style1);
				}
				if (t.getInsuranceExpireDay() != null) {
					row.createCell(7).setCellValue(t.getInsuranceExpireDay());
					row.getCell(7).setCellStyle(style1);
				}
				if (t.getMaintainDay() != null) {
					row.createCell(8).setCellValue(t.getMaintainDay());
					row.getCell(8).setCellStyle(style1);
				}
				if (t.getExaminedDay() != null) {
					row.createCell(9).setCellValue(t.getExaminedDay());
					row.getCell(9).setCellStyle(style1);
				}
				if (ValidateUtil.isValid(t.getComment())) {
					row.createCell(10).setCellValue(new HSSFRichTextString(t.getComment()));
				}
			}
		}

		return wb;

	}

	// 导入数据
	public void importCarDatas(String path) {
		List<Tcar> cars = this.loadingAndParseExcel(path);
		for (Tcar t : cars) {
			t.setId(UUID.randomUUID().toString());
			carDao.save(t);
		}
	}

	public void importStudentDatas(String path) {
		List<Tstudent> studentLists = this.loadingAndParseStudentExcel(path);
		for (Tstudent t : studentLists) {
			t.setId(UUID.randomUUID().toString());
			studentDao.save(t);
		}
	}

	// 根据科目类型导出报考该科目的学员信息
	public HSSFWorkbook exportStudentExamBySubjectType(String schoolArea, String ids, Integer subjectType)
			throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(ids) || !ValidateUtil.isValid(subjectType)) {
			return null;
		}
		List<Tstudent> studentLists = this.getStudentExamBySubject(schoolArea, ids);
		// 1.excel表格
		// 1).创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2).创建工作表
		HSSFSheet sheet = null;
		HSSFHeader header = null;
		if (1 == subjectType) {
			sheet = wb.createSheet("科目一学员考试信息");
			header = sheet.getHeader();
			header.setCenter("科目一学员考试信息");
		} else if (2 == subjectType) {
			sheet = wb.createSheet("科目二学员考试信息");
			header = sheet.getHeader();
			header.setCenter("科目二学员考试信息");
		} else if (3 == subjectType) {
			sheet = wb.createSheet("科目三学员考试信息");
			header = sheet.getHeader();
			header.setCenter("科目三学员考试信息");
		} else if (4 == subjectType) {
			sheet = wb.createSheet("科目四学员考试信息");
			header = sheet.getHeader();
			header.setCenter("科目四学员考试信息");
		} else if (5 == subjectType) {
			sheet = wb.createSheet("科目五学员考试信息");
			header = sheet.getHeader();
			header.setCenter("科目五学员考试信息");
		}

		// 设置列宽
		sheet.setColumnWidth(0, 1400);
		sheet.setColumnWidth(1, 2400);
		sheet.setColumnWidth(2, 1400);
		sheet.setColumnWidth(3, 5200);
		sheet.setColumnWidth(4, 2800);
		sheet.setColumnWidth(5, 2800);
		sheet.setColumnWidth(6, 12000);
		sheet.setColumnWidth(7, 3200);
		sheet.setColumnWidth(8, 4200);

		// 冻结窗格
		// 1:左侧冻结的列数
		// 2:上侧冻结的行数
		// 3:右侧窗格开始显示列的索引
		// 4:下侧窗格开始显示行的索引
		sheet.createFreezePane(2, 2, 2, 2);

		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 8);
		sheet.addMergedRegion(cra);
		// 3).创建行
		// 设置表头
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 表头高度

		HSSFCell cell = null;
		for (int i = 0; i < 9; i++) {
			cell = row.createCell(i);
			if (i == 0) {
				if (1 == subjectType) {
					cell.setCellValue(new HSSFRichTextString("科目一学员考试信息"));
				} else if (2 == subjectType) {
					cell.setCellValue(new HSSFRichTextString("科目二学员考试信息"));
				} else if (3 == subjectType) {
					cell.setCellValue(new HSSFRichTextString("科目三学员考试信息"));
				} else if (4 == subjectType) {
					cell.setCellValue(new HSSFRichTextString("科目四学员考试信息"));
				} else if (5 == subjectType) {
					cell.setCellValue(new HSSFRichTextString("科目五学员考试信息"));
				}
			}
			row.getCell(i).setCellStyle(HSSFUtils.getHeadCellStyle(wb));
		}

		row = sheet.createRow(1);
		row.setHeight((short) 400);// 行高
		// 4).创建单元格
		row.createCell(0).setCellValue(new HSSFRichTextString("序号"));
		row.createCell(1).setCellValue(new HSSFRichTextString("姓名"));
		row.createCell(2).setCellValue(new HSSFRichTextString("性别"));
		row.createCell(3).setCellValue(new HSSFRichTextString("身份证号"));
		row.createCell(4).setCellValue(new HSSFRichTextString("报考车型"));
		row.createCell(5).setCellValue(new HSSFRichTextString("原有车型"));
		row.createCell(6).setCellValue(new HSSFRichTextString("地址"));
		row.createCell(7).setCellValue(new HSSFRichTextString("编号"));
		row.createCell(8).setCellValue(new HSSFRichTextString("电话号码 "));
		for (int i = 0; i < 9; i++) {
			row.getCell(i).setCellStyle(HSSFUtils.getTitleCellStyle(wb));
		}

		// 创建数据格式对象

		if (ValidateUtil.isValidListObject(studentLists)) {
			Tstudent student = null;
			Integer j = 1;
			HSSFCellStyle commontStyle = null;
			HSSFCellStyle leftStyle = null;
			for (int i = 0; i < studentLists.size(); i++) {
				student = studentLists.get(i);
				row = sheet.createRow(i + 2);

				if ((i + 2) % 2 == 0) {
					commontStyle = HSSFUtils.getCommontCellStyle(wb);
					leftStyle = HSSFUtils.getCommontCellStyleLeft(wb);
					row.createCell(0).setCellValue(new HSSFRichTextString(j.toString()));
					row.getCell(0).setCellStyle(HSSFUtils.getCommontCellStyle(wb));
				} else {
					commontStyle = HSSFUtils.getCommontCellStyle(wb);
					leftStyle = HSSFUtils.getCommontCellStyleLeft(wb);
					row.createCell(0).setCellValue(new HSSFRichTextString(j.toString()));
					row.getCell(0).setCellStyle(HSSFUtils.getCommontCellStyle(wb));
				}
				row.setHeight((short) 400);// 表头高度
				j++;
				if (ValidateUtil.isValid(student.getName())) {
					row.createCell(1).setCellValue(new HSSFRichTextString(student.getName()));
					row.getCell(1).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getSex())) {
					row.createCell(2).setCellValue(new HSSFRichTextString(student.getSex()));
					row.getCell(2).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getIdentityId())) {
					row.createCell(3).setCellValue(new HSSFRichTextString(student.getIdentityId()));
					row.getCell(3).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getDriverType())) {
					row.createCell(4)
							.setCellValue(new HSSFRichTextString(getDriverTypeNameByInt(student.getDriverType())));
					row.getCell(4).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getPrimaryDriver())) {
					row.createCell(5).setCellValue(student.getPrimaryDriver());
					row.getCell(5).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getAddress())) {
					row.createCell(6).setCellValue(student.getAddress());
					row.getCell(6).setCellStyle(leftStyle);
				}
				if (ValidateUtil.isValid(student.getOtherNo())) {
					row.createCell(7).setCellValue(student.getOtherNo());
					row.getCell(7).setCellStyle(commontStyle);
				}
				if (ValidateUtil.isValid(student.getExamPhone())) {
					row.createCell(8).setCellValue(student.getExamPhone());
					row.getCell(8).setCellStyle(commontStyle);
				}

			}
		}

		return wb;

	}

	// 根据标识获取考试学员信息
	private List<Tstudent> getStudentExamBySubject(String schoolArea, String ids) {
		List<Tstudent> retLists = new ArrayList<Tstudent>();
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(ids)) {
			return null;
		}
		Tstudent student = null;
		for (String id : ids.split(",")) {
			student = studentDao.get(Tstudent.class, id.trim());
			if (student != null) {
				retLists.add(student);
			}
		}
		return retLists;
	}

	private List<Tcar> getAllCarDatas(String schoolArea, String ids) {
		List<Tcar> retVals = new ArrayList<Tcar>();
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(ids)) {
			return null;
		}
		for (String id : ids.split(",")) {
			Tcar car = carDao.get(Tcar.class, id);
			if (car != null) {
				retVals.add(car);
			}
		}
		return retVals;
	}

	/**
	 * 根据路径加载解析Excel
	 * 
	 * @param path
	 * @return
	 */
	private List<Tcar> loadingAndParseExcel(String path) {
		List<Tcar> list = new ArrayList<Tcar>();
		File file = null;
		InputStream input = null;
		Workbook workBook = null;
		Sheet sheet = null;
		if (path != null && path.length() > 7) {
			// 判断文件是否是Excel(2003、2007)
			String suffix = path.substring(path.lastIndexOf("."), path.length());
			if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {// 2003后缀或2007后缀
				file = new File(path);
				try {
					input = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					System.out.println("未找到指定的文件！");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("读取Excel文件发生异常！");
					e.printStackTrace();
				}
				if (!input.markSupported()) {
					input = new PushbackInputStream(input, 8);
				}
				// 创建工作薄
				try {
					if (POIFSFileSystem.hasPOIFSHeader(input) || POIXMLDocument.hasOOXMLHeader(input)) {
						workBook = WorkbookFactory.create(input);
					} else {
						System.out.println("非法的输入流：当前输入流非OLE2流或OOXML流！");
					}
				} catch (IOException e) {
					System.out.println("创建表格工作簿对象发生IO异常！原因：" + e.getMessage());
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// Your InputStream was neither an OLE2 stream, nor an OOXML
					// stream.
					System.out.println("非法的输入流：当前输入流非OLE2流或OOXML流！");
					e.printStackTrace();
				}
				// 创建工作表
				try {
					if (workBook != null) {
						int numberSheet = workBook.getNumberOfSheets();
						if (numberSheet > 0) {
							sheet = workBook.getSheetAt(0);// 获取第一个工作簿(Sheet)的内容【注意根据实际需要进行修改】
							list = getExcelContent(sheet);
						} else {
							System.out.println("目标表格工作簿(Sheet)数目为0！");
						}
					}
					input.close();
				} catch (IOException e) {
					System.out.println("关闭输入流异常！" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				System.out.println("非法的Excel文件后缀！");
			}
		} else {
			System.out.println("非法的文件路径!");
		}
		return list;
	}

	/**
	 * 解析(读取)Excel内容
	 * 
	 * @param sheet
	 * @return
	 */
	@SuppressWarnings("static-access")
	private List<Tcar> getExcelContent(Sheet sheet) {
		List<Tcar> list = new ArrayList<Tcar>();
		int rowCount = sheet.getPhysicalNumberOfRows();// 总行数
		if (rowCount > 1) {
			Row titleRow = sheet.getRow(1);// 标题行
			for (int i = 2; i < rowCount; i++) {// 遍历行，略过标题行，从第三行开始
				Row row = sheet.getRow(i);
				Tcar entity = new Tcar();
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
				for (int j = 0; j < 11; j++) {
					Cell cell = row.getCell(j);
					// 设置实体类的相关属性
					if (titleRow.getCell(j).getStringCellValue().indexOf("车牌号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setCarNo(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("行驶证号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setLicenseNo(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("车辆品牌") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setVehicleBrands(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("车辆型号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setVehicleType(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("运输证号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setTransportNo(cell.getStringCellValue().trim());
						}
					}

					if (titleRow.getCell(j).getStringCellValue().indexOf("购买日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setBuyDate(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("商业保险日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setBusinessExpireDay(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}

					if (titleRow.getCell(j).getStringCellValue().indexOf("交强险日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setInsuranceExpireDay(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}

					if (titleRow.getCell(j).getStringCellValue().indexOf("维护日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setMaintainDay(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}

					if (titleRow.getCell(j).getStringCellValue().indexOf("年审日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setExaminedDay(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}

					if (titleRow.getCell(j).getStringCellValue().indexOf("备注") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setComment(cell.getStringCellValue().trim());
						}
					}

				}
				list.add(entity);

			}
		}
		return list;
	}

	private List<Tstudent> loadingAndParseStudentExcel(String path) {
		List<Tstudent> list = new ArrayList<Tstudent>();
		File file = null;
		InputStream input = null;
		Workbook workBook = null;
		Sheet sheet = null;
		if (path != null && path.length() > 7) {
			// 判断文件是否是Excel(2003、2007)
			String suffix = path.substring(path.lastIndexOf("."), path.length());
			if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {// 2003后缀或2007后缀
				file = new File(path);
				try {
					input = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					System.out.println("未找到指定的文件！");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("读取Excel文件发生异常！");
					e.printStackTrace();
				}
				if (!input.markSupported()) {
					input = new PushbackInputStream(input, 8);
				}
				try {
					if (POIFSFileSystem.hasPOIFSHeader(input) || POIXMLDocument.hasOOXMLHeader(input)) {
						workBook = WorkbookFactory.create(input);
					} else {
						System.out.println("非法的输入流：当前输入流非OLE2流或OOXML流！");
					}
				} catch (IOException e) {
					System.out.println("创建表格工作簿对象发生IO异常！原因：" + e.getMessage());
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// Your InputStream was neither an OLE2 stream, nor an OOXML
					// stream.
					System.out.println("非法的输入流：当前输入流非OLE2流或OOXML流！");
					e.printStackTrace();
				}
				try {
					if (workBook != null) {
						int numberSheet = workBook.getNumberOfSheets();
						if (numberSheet > 0) {
							sheet = workBook.getSheetAt(0);// 获取第一个工作簿(Sheet)的内容【注意根据实际需要进行修改】
							list = getStudentDataFromExcel(sheet);
						} else {
							System.out.println("目标表格工作簿(Sheet)数目为0！");
						}
					}
					input.close();
				} catch (IOException e) {
					System.out.println("关闭输入流异常！" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				System.out.println("非法的Excel文件后缀！");
			}
		} else {
			System.out.println("非法的文件路径!");
		}
		return list;
	}

	public List<Tstudent> getStudentDataFromExcel(Sheet sheet) {
		List<Tstudent> retLists = new ArrayList<Tstudent>();
		int rowCount = sheet.getPhysicalNumberOfRows();// 总行数
		if (rowCount > 1) {
			Row titleRow = sheet.getRow(1);// 标题行
			for (int i = 1; i < rowCount; i++) {// 遍历行，略过标题行，从第二行开始
				Row row = sheet.getRow(i);
				Tstudent entity = new Tstudent();
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					// 设置实体类的相关属性
					if (titleRow.getCell(j).getStringCellValue().indexOf("姓名") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setName(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("性别") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setSex(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("出生日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setBirthdate(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("身份证号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setIdentityId(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("地址") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setAddress(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("报名日期") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							String reString = sdf.format(date);
							Date d = null;
							try {
								d = sdf.parse(reString);
								entity.setCreateTime(d);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("状态") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							int a = (int) cell.getNumericCellValue();
							entity.setNowState(Integer.valueOf(a));
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("手机") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setPhone(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("固定电话") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setTelephone(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("邮箱") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setEmail(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("照片回执编号") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setImageId(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("邮编") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setMailCode(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("居住证") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setResidenceId(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("居住证地址") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setResidenceAddr(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("国籍") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setContry(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("地域") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setNativeNation(cell.getStringCellValue().trim());
						}
					}
					// 班级关联
					if (titleRow.getCell(j).getStringCellValue().indexOf("班级") >= 0) {
						if (cell != null && cell.getCellType() == cell.CELL_TYPE_STRING) {
							List<Map<String, String>> clazzList = new ArrayList<Map<String, String>>();
							List<Tclass> lists = clazzDao.find("from Tclass");
							Map<String, String> map = new HashMap<String, String>();
							for (Tclass t : lists) {
								map.put(t.getId(), t.getName());
								clazzList.add(map);
							}
							// TODO关联
							entity.setNativeNation(cell.getStringCellValue().trim());
						}
					}

				}
				retLists.add(entity);

			}
		}
		return retLists;
	}

	@Override
	public HSSFWorkbook exportStdentDatas(String schoolArea, String ids) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(ids)) {
			return null;
		}

		// 1.excel表格
		// 1).创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 2).创建工作表
		HSSFSheet sheet = wb.createSheet("学员基本信息");
		HSSFHeader header = sheet.getHeader();
		header.setCenter("学员基本信息管理");
		// 设置列宽
		sheet.setColumnWidth(0, 3500);
		sheet.setColumnWidth(1, 2800);
		sheet.setColumnWidth(2, 2500);
		sheet.setColumnWidth(3, 5200);
		sheet.setColumnWidth(4, 1800);
		sheet.setColumnWidth(5, 12000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 2500);
		sheet.setColumnWidth(9, 3200);
		sheet.setColumnWidth(10, 3400);
		sheet.setColumnWidth(11, 3200);
		sheet.setColumnWidth(12, 3200);
		sheet.setColumnWidth(13, 3200);

		// 冻结窗格
		// 1:左侧冻结的列数
		// 2:上侧冻结的行数
		// 3:右侧窗格开始显示列的索引
		// 4:下侧窗格开始显示行的索引
		sheet.createFreezePane(2, 2, 2, 2);
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 13);
		sheet.addMergedRegion(cra);
		// 3).创建行
		// 001设置表头
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 表头高度
		HSSFCell cell = null;
		for (int i = 0; i <= 13; i++) {
			cell = row.createCell(i);
			if (i == 0) {
				cell.setCellValue(new HSSFRichTextString("学员基本信息管理"));
			}
			row.getCell(i).setCellStyle(HSSFUtils.getHeadCellStyle(wb));
		}

		row = sheet.createRow(1);
		row.setHeight((short) 400);// 表头高度
		// 4).创建单元格
		row.createCell(0).setCellValue(new HSSFRichTextString("所属校区"));
		row.createCell(1).setCellValue(new HSSFRichTextString("学员编号"));
		row.createCell(2).setCellValue(new HSSFRichTextString("姓名"));
		row.createCell(3).setCellValue(new HSSFRichTextString("身份证号"));
		row.createCell(4).setCellValue(new HSSFRichTextString("性别"));
		row.createCell(5).setCellValue(new HSSFRichTextString("地址"));
		row.createCell(6).setCellValue(new HSSFRichTextString("手机"));
		row.createCell(7).setCellValue(new HSSFRichTextString("报名日期 "));
		row.createCell(8).setCellValue(new HSSFRichTextString("驾照类型 "));
		row.createCell(9).setCellValue(new HSSFRichTextString("申领类型 "));
		row.createCell(10).setCellValue(new HSSFRichTextString("教练名称"));
		row.createCell(11).setCellValue(new HSSFRichTextString("应收学费"));
		row.createCell(12).setCellValue(new HSSFRichTextString("实收学费"));
		row.createCell(13).setCellValue(new HSSFRichTextString("欠费"));
		for (int i = 0; i <= 13; i++) {
			row.getCell(i).setCellStyle(HSSFUtils.getTitleCellStyle(wb));
		}

		List<Tstudent> students = this.getStudentByIds(schoolArea, ids);

		int i = 0;
		HSSFCellStyle tempCellStyle = null;
		HSSFCellStyle dateStyle = null;
		HSSFCellStyle leftStyle = null;
		for (Tstudent student : students) {

			if (student != null) {
				row = sheet.createRow(i + 2);
				if ((i + 2) % 2 == 0) {
					tempCellStyle = HSSFUtils.getCommontCellStyle(wb);
					dateStyle = HSSFUtils.getDateCellStyle(wb);
					leftStyle = HSSFUtils.getCommontCellStyleLeft(wb);
				} else {
					tempCellStyle = HSSFUtils.getCommontCellStyle(wb);
					dateStyle = HSSFUtils.getDateCellStyle(wb);
					leftStyle = HSSFUtils.getCommontCellStyleLeft(wb);
				}
				row.setHeight((short) 400);// 表头高度
				// 1.所属校区
				if (student.getOrganization() != null) {
					String schoolAreaName = organizationDao.get(Torganization.class, student.getOrganization().getId())
							.getName();
					row.createCell(0).setCellValue(new HSSFRichTextString(schoolAreaName));
					row.getCell(0).setCellStyle(tempCellStyle);
				}
				// 2.学员编号
				if (ValidateUtil.isValid(student.getStudentNo())) {
					String studentNo = student.getStudentNo();
					row.createCell(1).setCellValue(new HSSFRichTextString(studentNo.substring(studentNo.length() - 8)));
					row.getCell(1).setCellStyle(tempCellStyle);
				}
				// 3.姓名
				if (ValidateUtil.isValid(student.getName())) {
					row.createCell(2).setCellValue(new HSSFRichTextString(student.getName()));
					row.getCell(2).setCellStyle(tempCellStyle);
				}
				// 4.身份证
				if (ValidateUtil.isValid(student.getIdentityId())) {
					row.createCell(3).setCellValue(new HSSFRichTextString(student.getIdentityId()));
					row.getCell(3).setCellStyle(tempCellStyle);
				}
				// 5.性别
				if (ValidateUtil.isValid(student.getSex())) {
					row.createCell(4).setCellValue(new HSSFRichTextString(student.getSex()));
					row.getCell(4).setCellStyle(tempCellStyle);
				}
				// 6.地址
				if (ValidateUtil.isValid(student.getAddress())) {
					row.createCell(5).setCellValue(new HSSFRichTextString(student.getAddress()));
					row.getCell(5).setCellStyle(leftStyle);
				}
				// 7.手机
				if (ValidateUtil.isValid(student.getPhone())) {
					row.createCell(6).setCellValue(new HSSFRichTextString(student.getPhone()));
					row.getCell(6).setCellStyle(tempCellStyle);
				}
				// 8.报名日期
				if (student.getCreateTime() != null) {
					row.createCell(7).setCellValue(student.getCreateTime());
					row.getCell(7).setCellStyle(dateStyle);
				}
				// 9.驾照类型
				if (ValidateUtil.isValid(student.getDriverType())) {
					row.createCell(8)
							.setCellValue(new HSSFRichTextString(getDriverTypeNameByInt(student.getDriverType())));
					row.getCell(8).setCellStyle(tempCellStyle);
				}
				// 10.申领类型
				if (ValidateUtil.isValid(student.getApplyType())) {
					row.createCell(9)
							.setCellValue(new HSSFRichTextString(getApplyTypeNameByInt(student.getApplyType())));
					row.getCell(9).setCellStyle(tempCellStyle);
				}
				// 11.教练名称
				if (student.getTrainer() != null) {
					row.createCell(10).setCellValue(new HSSFRichTextString(student.getTrainer().getName()));
					row.getCell(10).setCellStyle(tempCellStyle);
				} else {
					row.createCell(10).setCellValue(new HSSFRichTextString(""));
					row.getCell(10).setCellStyle(tempCellStyle);
				}
				// 12.应交学费
				if (student.getFeed() != null && student.getFeed() > 0) {
					row.createCell(11).setCellValue(student.getFeed());
					row.getCell(11).setCellStyle(tempCellStyle);
				}
				// 13.实收学费
				if (student.getRealFeed() != null && student.getRealFeed() > 0) {
					row.createCell(12).setCellValue(student.getRealFeed());
					row.getCell(12).setCellStyle(tempCellStyle);
				}
				// 14.欠费
				if (student.getOwnFeed() != null && student.getOwnFeed() > 0) {
					row.createCell(13).setCellValue(student.getOwnFeed());
					row.getCell(13).setCellStyle(tempCellStyle);
				}
				i++;
			}
		}
		return wb;
	}

	private String getDriverTypeNameByInt(Integer temp) {
		if (temp <= 0) {
			return "";
		}
		if (temp == 1) {
			return "A1";
		} else if (temp == 2) {
			return "A2";
		} else if (temp == 3) {
			return "A3";
		} else if (temp == 4) {
			return "B1";
		} else if (temp == 5) {
			return "B2";
		} else if (temp == 6) {
			return "C1";
		} else if (temp == 7) {
			return "C2";
		} else if (temp == 8) {
			return "C3";
		} else if (temp == 9) {
			return "C4";
		} else if (temp == 10) {
			return "C5";
		} else if (temp == 11) {
			return "D";
		} else if (temp == 12) {
			return "E";
		} else if (temp == 13) {
			return "F";
		} else if (temp == 14) {
			return "M";
		} else if (temp == 15) {
			return "N";
		} else if (temp == 16) {
			return "P";
		}
		return "";
	}

	private String getApplyTypeNameByInt(Integer temp) {
		if (temp <= 0) {
			return "";
		}
		if (temp == 1) {
			return "初次申领";
		} else if (temp == 2) {
			return "增加准驾车型";
		} else if (temp == 3) {
			return "持军官驾驶证";
		} else if (temp == 4) {
			return "持境外驾驶证";
		}
		return "";
	}

	private List<Tstudent> getStudentByIds(String schoolArea, String ids) {
		List<Tstudent> retVals = new ArrayList<Tstudent>();
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(ids)) {
			return null;
		}
		for (String id : ids.split(",")) {
			Tstudent student = studentDao.get(Tstudent.class, id);
			if (student != null) {
				retVals.add(student);
			}
		}
		return retVals;
	}

}
