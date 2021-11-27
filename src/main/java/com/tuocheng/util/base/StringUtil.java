package com.tuocheng.util.base;

import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;

import com.tuocheng.model.demo.Torganization;

/**
 * 字符串辅助类
 * 
 * @author 农峰
 * 
 */
public class StringUtil {
	public static String getStringByReservationType(Integer reservationType) {
		if (!ValidateUtil.isValid(reservationType)) {
			return null;
		}
		if (1 == reservationType) {
			return "电车";
		} else if (2 == reservationType) {
			return "模拟";
		} else if (3 == reservationType) {
			return "五项";
		} else if (4 == reservationType) {
			return "路训";
		}
		return null;
	}

	//随机获取6位数
	public static String getRandVerificationCode() {
		int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < 6) {
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 将字符串转换成字符串数组
	 * 
	 * @param str
	 * @param target
	 * @return
	 */
	public static String[] stringToArray(String str, String target) {
		if (ValidateUtil.isValid(str)) {
			return str.split(target);
		}
		return null;
	}

	/**
	 * 判断数组中是否含有指定的串
	 * 
	 * @param arrays
	 * @param value
	 * @return
	 */
	public static boolean containSubString(String[] arrays, String value) {
		if (ValidateUtil.isValid(arrays)) {
			for (String s : arrays) {
				if (s.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将数组转换成字符串,并使用‘，’作为分隔符
	 * 
	 * @param arrays
	 * @return
	 */
	public static String arrayToString(Object[] arrays) {
		String temp = "";
		if (ValidateUtil.isValid(arrays)) {
			for (Object s : arrays) {
				temp = temp + s + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return null;
	}

	public static String getDescString(String str) {
		if (ValidateUtil.isValid(str) && str.length() > 30) {
			return str.substring(0, 29);
		}
		return str;
	}

	/**
	 * 将字符串数组转换成整型数组，将整型数组从小到大排列
	 * 
	 * @param arr
	 * @return
	 */
	public static Integer[] stringToIntegerAndSort(String[] arr) {
		// 1.将字符串转化为整型数组
		Integer[] reVal = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			reVal[i] = Integer.parseInt(arr[i].trim());
		}
		// 2.对整型数据进行从小到大排序
		Integer temp = 0;
		for (int i = 1; i < reVal.length; i++) {
			int j = i - 1;
			temp = reVal[i];
			for (; j >= 0 && temp < reVal[j]; j--) {
				reVal[j + 1] = reVal[j]; // 将大于temp的值整体后移一个单位
			}
			reVal[j + 1] = temp;
		}
		return reVal;
	}

	/**
	 * 将整型数组转换成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String intArrayToString(Integer[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				sb.append(arr[i]);
			} else {
				sb.append(arr[i] + ",");
			}
		}
		return sb.toString();
	}

	/**
	 * 將驾照字符转换为转型值
	 * 
	 * @param driverTypeName
	 * @return
	 */
	public static Integer getCarTypeValue(String driverTypeName) {
		if (!ValidateUtil.isValid(driverTypeName)) {
			return null;
		}
		Integer reVal = null;
		if (driverTypeName.equals("A1")) {
			reVal = 1;
		} else if (driverTypeName.equals("A2")) {
			reVal = 2;
		} else if (driverTypeName.equals("A3")) {
			reVal = 3;
		} else if (driverTypeName.equals("B1")) {
			reVal = 4;
		} else if (driverTypeName.equals("B2")) {
			reVal = 5;
		} else if (driverTypeName.equals("C1")) {
			reVal = 6;
		} else if (driverTypeName.equals("C2")) {
			reVal = 7;
		} else if (driverTypeName.equals("C3")) {
			reVal = 8;
		} else if (driverTypeName.equals("C4")) {
			reVal = 9;
		} else if (driverTypeName.equals("C5")) {
			reVal = 10;
		} else if (driverTypeName.equals("D")) {
			reVal = 11;
		} else if (driverTypeName.equals("E")) {
			reVal = 12;
		} else if (driverTypeName.equals("F")) {
			reVal = 13;
		} else if (driverTypeName.equals("M")) {
			reVal = 14;
		} else if (driverTypeName.equals("N")) {
			reVal = 15;
		} else if (driverTypeName.equals("P")) {
			reVal = 16;
		}
		return reVal;
	}

	/**
	 * 将单元格按类型转化为字符串
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValueByCellType(Cell cell) {
		if (cell == null || cell.getCellType() < 0) {
			return null;
		}
		String reVal = null;
		switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
		case 0:
			reVal = String.valueOf((int) cell.getNumericCellValue());
			break;
		case 1:
			reVal = cell.getStringCellValue();
			break;
		case 2:
			reVal = String.valueOf((int) cell.getNumericCellValue());
			break;
		case 3:
			reVal = cell.getStringCellValue();
			break;
		}
		if (ValidateUtil.isValid(reVal)) {
			return reVal;
		}
		return null;
	}

	/**
	 * 根据校区标识获取各学校首字母
	 * 
	 * @param schoolArea
	 * @return
	 */
	public static String getFirstLetterBySchoolArea(String schoolArea) {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		String retStr = null;
		if (schoolArea.trim().equals(Torganization.BS_SCHOOLAREA)) {
			retStr = "BS";
		} else if (schoolArea.trim().equals(Torganization.TY_SCHOOLAREA)) {
			retStr = "TY";
		} else if (schoolArea.trim().equals(Torganization.TD_SCHOOLAREA)) {
			retStr = "TD";
		} else if (schoolArea.trim().equals(Torganization.PG_SCHOOLAREA)) {
			retStr = "PG";
		} else if (schoolArea.trim().equals(Torganization.DB_SCHOOLAREA)) {
			retStr = "DB";
		} else if (schoolArea.trim().equals(Torganization.JX_SCHOOLAREA)) {
			retStr = "JX";
		} else if (schoolArea.trim().equals(Torganization.NP_SCHOOLAREA)) {
			retStr = "NP";
		} else if (schoolArea.trim().equals(Torganization.TL_SCHOOLAREA)) {
			retStr = "TL";
		} else if (schoolArea.trim().equals(Torganization.XL_SCHOOLAREA)) {
			retStr = "XL";
		} else if (schoolArea.trim().equals(Torganization.LL_SCHOOLAREA)) {
			retStr = "LL";
		} else if (schoolArea.trim().equals(Torganization.LY_SCHOOLAREA)) {
			retStr = "EY";
		} else if (schoolArea.trim().equals(Torganization.LEY_SCHOOLAREA)) {
			retStr = "EE";
		} else {
			retStr = null;
		}
		return retStr;
	}

	public static String removeFisrtTwoLetter(String str) {
		return str.substring(2, str.length());
	}

	public static String getFormatterStudentNo(String studentNo) {
		if (!ValidateUtil.isValid(studentNo)) {
			return null;
		}
		return studentNo.substring(2, studentNo.length());
	}

}
