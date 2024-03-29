package com.tuocheng.util.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static void main(String[] args) {
		String path2003 = "";// Excel2003文件路径
		String path2007 = "";// Excel2007文件路径
		path2003 = System.getProperties().getProperty("user.dir")
				+ File.separator + "Excel" + File.separator + "user_2003.xls";
		path2007 = System.getProperties().getProperty("user.dir")
				+ File.separator + "Excel" + File.separator + "user_2007.xlsx";

		long start2003 = System.currentTimeMillis();
		System.out.println("Excel文件路径：" + path2003);
		List<User> list2003 = parseExcel(path2003);
		System.out.println("解析2003输出总人数：" + list2003.size());
		long end2003 = System.currentTimeMillis();
		System.out.println("解析Excel2003完毕！共用时" + (end2003 - start2003) + "毫秒！");

		long start2007 = System.currentTimeMillis();
		System.out.println("Excel文件路径：" + path2007);
		List<User> list2007 = parseExcel(path2007);
		System.out.println("解析2007输出总人数：" + list2007.size());
		long end2007 = System.currentTimeMillis();
		System.out.println("解析Excel完毕！共用时" + (end2007 - start2007) + "毫秒！");

		System.out.println("》》》》》》》》》》开始执行Excel文件导出：");
		buildXSLExcel();// 导出Excel 2003
		buildXSLXExcel();// 导出Excel 2007
	}

	/**
	 * 根据路径加载解析Excel
	 * 
	 * @param path
	 * @return
	 */
	public static List<User> parseExcel(String path) {
		List<User> list = new ArrayList<User>();
		File file = null;
		InputStream input = null;
		Workbook workBook = null;
		Sheet sheet = null;
		if (path != null && path.length() > 7) {
			// 判断文件是否是Excel(2003、2007)
			String suffix = path
					.substring(path.lastIndexOf("."), path.length());
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
					if (POIFSFileSystem.hasPOIFSHeader(input)
							|| POIXMLDocument.hasOOXMLHeader(input)) {
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
	public static List<User> getExcelContent(Sheet sheet) {
		List<User> list = new ArrayList<User>();
		int rowCount = sheet.getPhysicalNumberOfRows();// 总行数
		if (rowCount > 1) {
			Row titleRow = sheet.getRow(0);// 标题行
			for (int i = 1; i < rowCount; i++) {// 遍历行，略过标题行，从第二行开始
				Row row = sheet.getRow(i);
				User entity = new User();
				for (int j = 0; j < 3; j++) {
					Cell cell = row.getCell(j);
					if (titleRow.getCell(j).getStringCellValue().indexOf("姓名") >= 0) {
						if (cell != null
								&& cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setName(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("性别") >= 0) {
						if (cell != null
								&& cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setSex(cell.getStringCellValue().trim());
						}
					}
					if (titleRow.getCell(j).getStringCellValue().indexOf("年龄") >= 0) {
						if (cell != null
								&& cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							entity.setAge((int) cell.getNumericCellValue());
						} else if (cell != null
								&& cell.getCellType() == cell.CELL_TYPE_STRING) {
							entity.setAge(Integer.parseInt(cell
									.getStringCellValue().trim()));
						}
					}
				}
				list.add(entity);
			}
		}
		return list;
	}

	/**
	 * 生成2003 Excel
	 */
	public static void buildXSLExcel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String now = format.format(new Date());
		String basePath = System.getProperties().getProperty("user.dir")
				+ File.separator + "Excel" + File.separator;
		String exportFileName = "人员信息导出2003_" + now + ".xls";// 导出文件名

		List<User> list = parseExcel(basePath + "user_2003.xls");
		HSSFWorkbook workBook = null;
		String[] cellTitle = { "序号", "姓名", "性别", "年龄" };
		try {
			workBook = new HSSFWorkbook();// 创建工作薄
			HSSFSheet sheet = workBook.createSheet();
			workBook.setSheetName(0, "人员信息");// 工作簿名称
			HSSFFont font = workBook.createFont();
			font.setColor(HSSFFont.COLOR_NORMAL);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 创建第一行标题
			HSSFRow titleRow = sheet.createRow((short) 0);// 第一行标题
			for (int i = 0, size = cellTitle.length; i < size; i++) {// 创建第1行标题单元格
				switch (i) {
				case 0:
					sheet.setColumnWidth(0, 3000);
					break;
				case 1:
					sheet.setColumnWidth(1, 4000);
					break;
				case 2:
					sheet.setColumnWidth(2, 4000);
					break;
				case 3:
					sheet.setColumnWidth(3, 2000);
					break;
				}
				HSSFCell cell = titleRow.createCell(i, 0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cellTitle[i]);
			}
			// 从第二行开始写入数据
			// 注：此处如果数据过多，会抛出java.lang.IllegalStateException异常：The maximum
			// number of cell styles was exceeded.
			// You can define up to 4000 styles in a .xls workbook。这是是由于cell
			// styles太多create造成，故一般可以把cellstyle设置放到循环外面

			if (list != null && !list.isEmpty()) {
				HSSFCellStyle style = workBook.createCellStyle();// 创建格式
				for (int i = 0, size = list.size(); i < size; i++) {
					User entity = list.get(i);
					HSSFRow row = sheet.createRow((short) i + 1);
					for (int j = 0, length = cellTitle.length; j < length; j++) {
						HSSFCell cell = row.createCell(j, 0);// 在上面行索引0的位置创建单元格
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型
						switch (j) {// 在单元格中输入一些内容
						case 0:// 序号
							cell.setCellValue(i + 1);
							style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cell.setCellStyle(style);
							break;
						case 1:// 姓名
							cell.setCellValue(entity.getName());
							style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
							cell.setCellStyle(style);
							break;
						case 2:// 性别
							cell.setCellValue(String.valueOf(entity.getSex()));
							style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
							cell.setCellStyle(style);
							break;
						case 3:// 年龄
							cell.setCellValue(entity.getAge());
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
							cell.setCellStyle(style);
							break;
						}
					}
				}
			}

			// 通过文件输出流生成Excel文件
			File file = new File(basePath + exportFileName);
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			outStream.flush();
			outStream.close();
			System.out.println("Excel 2003文件导出完成！导出文件路径：" + file.getPath());

			/***
			 * Web形式输出Excel
			 * 
			 */
			/**
			 * // 表示以附件的形式把文件发送到客户端 response.setHeader("Content-Disposition",
			 * "attachment;filename=" + new String((exportFileName).getBytes(),
			 * "ISO-8859-1"));//设定输出文件头 response.setContentType(
			 * "application/vnd.ms-excel;charset=UTF-8");// 定义输出类型 //
			 * 通过response的输出流把工作薄的流发送浏览器形成文件 OutputStream outStream =
			 * response.getOutputStream(); workBook.write(outStream);
			 * outStream.flush();
			 */
		} catch (IOException e) {
			System.out.println("生成人员信息Excel发生IO 异常！" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("文件导出发生异常！异常原因：" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 生成2007 Excel
	 */
	public static void buildXSLXExcel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String now = format.format(new Date());
		String basePath = System.getProperties().getProperty("user.dir")
				+ File.separator + "Excel" + File.separator;
		String exportFileName = "人员信息导出2007_" + now + ".xlsx";// 导出文件名

		List<User> list = parseExcel(basePath + "user_2003.xls");
		XSSFWorkbook workBook = null;
		String[] cellTitle = { "序号", "姓名", "性别", "年龄" };
		try {
			workBook = new XSSFWorkbook();// 创建工作薄
			XSSFSheet sheet = workBook.createSheet();
			workBook.setSheetName(0, "人员信息");// 工作簿名称
			XSSFFont font = workBook.createFont();
			font.setColor(XSSFFont.COLOR_NORMAL);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			XSSFCellStyle cellStyle = workBook.createCellStyle();// 创建格式
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 创建第一行标题
			XSSFRow titleRow = sheet.createRow((short) 0);// 第一行标题
			for (int i = 0, size = cellTitle.length; i < size; i++) {// 创建第1行标题单元格
				switch (i) {
				case 0:
					sheet.setColumnWidth(0, 3000);
					break;
				case 1:
					sheet.setColumnWidth(1, 4000);
					break;
				case 2:
					sheet.setColumnWidth(2, 4000);
					break;
				case 3:
					sheet.setColumnWidth(3, 2000);
					break;
				}
				XSSFCell cell = titleRow.createCell(i, 0);
				cell.setCellStyle(cellStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(cellTitle[i]);
			}
			// 从第二行开始写入数据
			// 注：此处如果数据过多，会抛出java.lang.IllegalStateException异常：The maximum
			// number of cell styles was exceeded.
			// You can define up to 4000 styles in a .xls workbook。这是是由于cell
			// styles太多create造成，故一般可以把cellstyle设置放到循环外面

			if (list != null && !list.isEmpty()) {
				XSSFCellStyle style = workBook.createCellStyle();// 创建格式
				for (int i = 0, size = list.size(); i < size; i++) {
					User entity = list.get(i);
					XSSFRow row = sheet.createRow((short) i + 1);
					for (int j = 0, length = cellTitle.length; j < length; j++) {
						XSSFCell cell = row.createCell(j, 0);// 在上面行索引0的位置创建单元格
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型
						switch (j) {// 在单元格中输入一些内容
						case 0:// 序号
							cell.setCellValue(i + 1);
							style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
							cell.setCellStyle(style);
							break;
						case 1:// 姓名
							cell.setCellValue(entity.getName());
							style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
							cell.setCellStyle(style);
							break;
						case 2:// 性别
							cell.setCellValue(String.valueOf(entity.getSex()));
							style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
							cell.setCellStyle(style);
							break;
						case 3:// 年龄
							cell.setCellValue(entity.getAge());
							cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
							style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
							cell.setCellStyle(style);
							break;
						}
					}
				}
			}

			// 通过文件输出流生成Excel文件
			File file = new File(basePath + exportFileName);
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			outStream.flush();
			outStream.close();
			System.out.println("Excel 2007文件导出完成！导出文件路径：" + file.getPath());

			/***
			 * Web形式输出Excel
			 * 
			 */
			/**
			 * // 表示以附件的形式把文件发送到客户端 response.setHeader("Content-Disposition",
			 * "attachment;filename=" + new String((exportFileName).getBytes(),
			 * "ISO-8859-1"));//设定输出文件头 response.setContentType(
			 * "application/vnd.ms-excel;charset=UTF-8");// 定义输出类型 //
			 * 通过response的输出流把工作薄的流发送浏览器形成文件 OutputStream outStream =
			 * response.getOutputStream(); workBook.write(outStream);
			 * outStream.flush();
			 */
		} catch (IOException e) {
			System.out.println("生成人员信息Excel发生IO 异常！" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("文件导出发生异常！异常原因：" + e.getMessage());
			e.printStackTrace();
		}
	}
}

class User {
	private String name;
	private String sex;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
