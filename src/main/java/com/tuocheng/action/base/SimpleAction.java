package com.tuocheng.action.base;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.tuocheng.util.base.Util;

public class SimpleAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		OutputStream os = null;
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			byte[] data = Util.isNotNull(json) ? json.toString().getBytes("UTF-8") : " ".getBytes();
			HttpServletResponse response = ServletActionContext.getResponse();
			os = ServletActionContext.getResponse().getOutputStream();
			response.reset();
			response.setContentType("application/json");
			response.setHeader("Content-Length",Integer.toString(data.length));
			os.write(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) os.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(String json) {
		OutputStream os = null;
		try {
			byte[] data = Util.isNotNull(json) ? json.toString().getBytes("UTF-8") : " ".getBytes();
			HttpServletResponse response = ServletActionContext.getResponse();
			os = ServletActionContext.getResponse().getOutputStream();
			response.reset();
			response.setContentType("application/json");
			response.setHeader("Content-Length",Integer.toString(data.length));
			os.write(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) os.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 将对象转换成XML字符串，并响应回前台
	 * 
	 * @param document
	 * @throws IOException
	 */
	public void writeXml(Document document) {
		OutputStream os = null;
		try {
			String xmlString = "<root></root>";
			if(document != null){
				xmlString = document.asXML();
			}
			byte[] data = Util.isNotNull(xmlString) ? xmlString.toString().getBytes("UTF-8") : " ".getBytes();
			HttpServletResponse response = ServletActionContext.getResponse();
			os = ServletActionContext.getResponse().getOutputStream();
			response.reset();
			response.setContentType("text/xml");
			response.setHeader("Content-Length",Integer.toString(data.length));
			os.write(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) os.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * XML字符串，并响应回前台
	 * 
	 * @param document
	 * @throws IOException
	 */
	public void writeXml(String xml) {
		OutputStream os = null;
		try {
			byte[] data = Util.isNotNull(xml) ? xml.toString().getBytes("UTF-8") : " ".getBytes();
			HttpServletResponse response = ServletActionContext.getResponse();
			os = ServletActionContext.getResponse().getOutputStream();
			response.reset();
			response.setContentType("text/xml");
			response.setHeader("Content-Length",Integer.toString(data.length));
			os.write(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) os.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * 输出文本到前台
	 * @param text
	 */
	public void writeText(String text){
		OutputStream os = null;
		try {
			byte[] data = Util.isNotNull(text) ? text.toString().getBytes("UTF-8") : " ".getBytes();
			HttpServletResponse response = ServletActionContext.getResponse();
			os = ServletActionContext.getResponse().getOutputStream();
			response.reset();
			response.setContentType("text/plan");
			response.setHeader("Content-Length",Integer.toString(data.length));
			os.write(data);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (os != null) os.close();
			} catch (Exception ex) {
			}
		}
	}

}
