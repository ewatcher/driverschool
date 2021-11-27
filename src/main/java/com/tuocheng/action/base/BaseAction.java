package com.tuocheng.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tucheng.struts2.UserAware;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 基础ACTION,其他ACTION继承此ACTION来获得writeJson和ActionSupport的功能
 * imayu
 * @author 农峰
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> ,UserAware{
	
	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = -125457323435702646L;

	private static final Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * 此变量用来传递数据（action专用的model）
	 */
	public T model;
	public User user;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction() {
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

	
	//将从session中注入的对象传递到页面
	public void getSchoolArea(){
		this.writeJson(user.getSchoolArea());
	}

	@Override
	public void setUser(User user) {
		//pei mtd
		this.user=user;
	}
	
}
