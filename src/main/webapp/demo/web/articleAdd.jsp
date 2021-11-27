<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_weixinsettings_addForm" method="post">
		<fieldset >
			<legend>文章内容编辑</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 55px;">标题:</th>
					<td><input name="title" style="width: 450px;"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写文章标题'" /></td>
					<th style="width: 55px;">类型:</th>
					<td>
						<select class="easyui-combobox" name="type" style="width:100%;height:auto;">
							<option value="1">文本</option>
							<option value="2">URL地址</option>
	                		<option value="0">其它</option>
						</select>
					</td>
					<tr><th >描述:</th></tr>
				<tr>
					<td colspan="8">
                        <script id="editor" type="text/plain" name="content" style="width:100%;height:450px;"></script>
                    </td>
				</tr>
			</table>
		</fieldset>
		<input type="hidden" name="status" value="1" />
	</form>
    
    <script>
    
    
    </script>
</div>
