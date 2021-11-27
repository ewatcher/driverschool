package com.tuocheng.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GuestAction<T> extends ActionSupport implements ModelDriven<T> {
	
	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = -125457323435702646L;

	private static final Logger logger = Logger.getLogger(GuestAction.class);

	/**
	 * 此变量用来传递数据（action专用的model）
	 */
	public T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GuestAction() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			// SerializeConfig serializeConfig = new SerializeConfig();
			// serializeConfig.setAsmEnable(false);
			// String json = JSON.toJSONString(object);
			// String json = JSON.toJSONString(object, serializeConfig,
			// SerializerFeature.PrettyFormat);
			String json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
			// String json = JSON.toJSONStringWithDateFormat(object,
			// "yyyy-MM-dd HH:mm:ss", SerializerFeature.PrettyFormat);
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}
	
}
