package com.tuocheng.util.base;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class HSSFUtils {
	/**
	 * 判断字符串有效性
	 */
	public static boolean isValid(String src) {
		if (src == null || "".equals(src.trim())) {
			return false;
		}
		return true;
	}

	public static HSSFCellStyle getHeadCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle headCellStyle = wb.createCellStyle();
		headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		headCellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);// 上边框
		headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);// 下边框
		headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);// 左边框
		headCellStyle.setBorderRight(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);// 右边框
		headCellStyle.setTopBorderColor(HSSFColor.RED.index);// 上边框颜色
		headCellStyle.setBottomBorderColor(HSSFColor.BLUE.index);// 下边框颜色
		headCellStyle.setLeftBorderColor(HSSFColor.RED.index);// 左边框颜色
		headCellStyle.setRightBorderColor(HSSFColor.RED.index);// 右边框颜色

		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setFontName("BOLD");
		titleFont.setColor(HSSFColor.RED.index);
		titleFont.setBold(true);
		headCellStyle.setFont(titleFont);
		return headCellStyle;
	}

	/**
	 * 标题样式
	 * 
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle titelStyle = wb.createCellStyle();
		titelStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		titelStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		titelStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		titelStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		titelStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		titelStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框颜色
		titelStyle.setTopBorderColor(HSSFColor.BLACK.index);
		titelStyle.setRightBorderColor(HSSFColor.BLACK.index);
		titelStyle.setBottomBorderColor(HSSFColor.BLACK.index);

		// 通用字体
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 11);
		titleFont.setFontName("BOLD");
		titleFont.setColor(HSSFColor.DARK_GREEN.index);
		titleFont.setBold(true);
		titelStyle.setFont(titleFont);
		return titelStyle;
	}

	public static HSSFCellStyle getCommontCellStyle(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		// 通用样式1
		HSSFCellStyle commontStyle = wb.createCellStyle();
		commontStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		commontStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyle.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyle.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyle.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);

		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyle.setFont(commentFont);

		return commontStyle;
	}

	public static HSSFCellStyle getCommontCellStyleBackgroud(HSSFWorkbook wb) {
		// 通用样式2（区别在于有背景色）
		HSSFCellStyle commontStyle2 = wb.createCellStyle();
		commontStyle2.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		commontStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyle2.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyle2.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyle2.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyle2.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		// 添加背景色
		commontStyle2.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
		commontStyle2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置前景色
		commontStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyle2.setFont(commentFont);

		return commontStyle2;
	}

	public static HSSFCellStyle getCommontCellStyleLeft(HSSFWorkbook wb) {
		// 通用样式2（区别在于有背景色）
		HSSFCellStyle commontStyleLeft = wb.createCellStyle();
		commontStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);// 水平居中
		commontStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyleLeft.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleLeft.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleLeft.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyleLeft.setFont(commentFont);
		return commontStyleLeft;
	}

	public static HSSFCellStyle getCommontCellStyleLeftBackgroud(HSSFWorkbook wb) {
		// 通用样式2（区别在于有背景色）
		HSSFCellStyle commontStyleLeft = wb.createCellStyle();
		commontStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);// 水平居中
		commontStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyleLeft.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyleLeft.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleLeft.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleLeft.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		// 添加背景色
		commontStyleLeft.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
		commontStyleLeft.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置前景色
		commontStyleLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyleLeft.setFont(commentFont);
		return commontStyleLeft;
	}

	public static HSSFCellStyle getDateCellStyle(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		HSSFCellStyle commontStyleDate = wb.createCellStyle();
		commontStyleDate.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		commontStyleDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyleDate.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setDataFormat(format.getFormat("yyyy-MM-dd"));
		commontStyleDate.setFillBackgroundColor(HSSFColor.RED.index);
		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyleDate.setFont(commentFont);
		return commontStyleDate;

	}

	public static HSSFCellStyle getDateCellStyleBackgroud(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		HSSFCellStyle commontStyleDate = wb.createCellStyle();
		commontStyleDate.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		commontStyleDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		commontStyleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);// 左边框
		commontStyleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);//
		commontStyleDate.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);// 左边框颜色
		commontStyleDate.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);
		commontStyleDate.setDataFormat(format.getFormat("yyyy-MM-dd"));
		commontStyleDate.setFillBackgroundColor(HSSFColor.RED.index);
		// 添加背景色
		commontStyleDate.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
		commontStyleDate.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置前景色
		commontStyleDate.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 通用字体
		HSSFFont commentFont = wb.createFont();
		commentFont.setFontHeightInPoints((short) 10);
		commentFont.setFontName("BOLD");
		commentFont.setColor(HSSFColor.BLACK.index);
		commontStyleDate.setFont(commentFont);
		return commontStyleDate;

	}

}
