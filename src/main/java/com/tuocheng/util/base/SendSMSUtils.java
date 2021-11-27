package com.tuocheng.util.base;

import java.util.HashMap;

import javax.xml.crypto.Data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuocheng.aliyun.SingleSendSmsApi;

/**
 * 短信平台帮助类
 * 
 * @author huangqinting
 *
 */
public class SendSMSUtils {
	private final static String SIGN_NAME = "拓程科技"; // 签名

																					// code

	// 尊敬的学员${name}您将于${date},${time}参加科目${subject}考试，请提前30分钟达到考场，请须知。
	private final static String TEMPLATE_CODE_EXAMMSG = "SMS_75720070"; // 拼车线路取消短信模板
																				// code

	/**
	 * 
	 * @param mobile 学员手机号码
	 * @param name 学员名称
	 * @param date 考试日期
	 * @param time 考试时间
	 * @param subject 考试科目
	 * @return
	 */
	public static boolean setExamSMS(String mobile, String name, String date,String subject) {
		if (!ValidateUtil.isValid(mobile) || !ValidateUtil.isValid(name) || !ValidateUtil.isValid(date)
				||  !ValidateUtil.isValid(subject)) {
			return false;
		}
		SingleSendSmsApi singleSendSms = new SingleSendSmsApi();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("date", date);
		params.put("subject", subject);
		String text = singleSendSms.sendSMS(mobile, SIGN_NAME, TEMPLATE_CODE_EXAMMSG, params);
		System.out.println("得到结果:[" + text + "]");
		if (Util.isNotNull(text)) {
			JSONObject obj = JSON.parseObject(text);
			if (obj.get("success").equals(true)) {
				return true;
			}
		}
		return false;
	}

}
