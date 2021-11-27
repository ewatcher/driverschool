<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div style="padding: 5px;overflow: hidden;">
	<form action="export_ExcelInto" enctype="multipart/form-data" method="post">
		<input name="batch" type="hidden">
      <table width="100%" border="0" align="center">
        <tr>
	      <td colspan="99" id="more">
	        <input type="file" name="uploadfile" id="uploadfile"/>
	      </td>
	    </tr>
      </table>
    </form>

</div>