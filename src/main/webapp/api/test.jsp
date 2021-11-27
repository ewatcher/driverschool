<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="com.tuocheng.util.base.Util"%>
<%
	String sendXML = null;
	boolean check = true;

	if (check) {
		String signature = Util.objToString(request.getParameter("signature"), "");
		long timestamp = Util.objToLong(request.getParameter("timestamp"), 0);
		long nonce = Util.objToLong(request.getParameter("nonce"), 0);
		String echostr = Util.objToString(request.getParameter("echostr"), "");

		if (Util.isNotNull(signature)) {
			sendXML = echostr;
		}
	}

	byte[] data = Util.isNotNull(sendXML) ? sendXML.toString().getBytes("UTF-8") : " ".getBytes();
	OutputStream os = null;
	try {
		os = response.getOutputStream();
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
%>
