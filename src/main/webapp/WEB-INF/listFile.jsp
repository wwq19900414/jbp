<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<%String _base = request.getContextPath(); %>
<title>JBP用户行为数据API可下载文件列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="<%=_base%>/js/jquery-1.6.2.js"></script>
</head> 

<body>
<input type="hidden" id="operateCode" value='${operateCode}' />
<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
<table style="border: 1px dotted rgb(204,204,204);width: 90%;">

<tr bgcolor="#D0E0F0">
	<td width="30%">【<a href='javascript:void(0)' title='点击文件名称即可下载该文件'>文件名称</a>】</td>
	<td width="20%">【创建时间】</td>
	<td width="10%" align="right">【大小(K)】</td>
	<td width="10%" align="center">【操作】</td>
</tr>

<s:iterator value="fileList" id="file" status="st">
<tr  <s:if test="#st.count % 2 == 0">bgcolor="#D0E5F5"</s:if> id='fileTr<s:property value="#st.count"/>'>
	<td>
	【
	<a target='_blank'  href='downloadFile.action?filename=<s:property value="#file.name"/>' >
	<s:property value="#file.name"/>
	</a>
	】
	</td>

	<td>
	<s:property value="#file.createTimeStr"/>
	</td>

	<td align="right">
		<a href="javascript:void(0)" title='<s:property value="#file.size / 1024"/>M'>
			<s:property value="#file.size"/>K
		</a>
	</td>

	<td align="center">
	<a onclick="deleteFile('<s:property value="#file.name"/>',<s:property value="#st.count"/>)"  href='javascript:void(0)' >删除</a>
	</td>

</tr>
</s:iterator>

</table>
	
</div>

<script type="text/javascript">
/**
 * 删除指定文件
 */
function deleteFile(fileName,index){
	var operateCode = $("#operateCode").val();

	if( confirm("确定要删除【"+fileName+"】这个文件吗") ){
		$.ajax({
			url : "deleteFile.action", 
			type : "POST",
			data : {
				"uploadFileName" : fileName,
				"operateCode" : operateCode
			},
			success : function(data){
						if(data.indexOf("success") > 0 ){
							delTr(index);
						}else{
							alert(data);				
						}
					}
		});
	}
}

/**
 * 收回一个输入框
 */
function delTr(index){
	var tr = document.getElementById("fileTr" + index);
	tr.parentNode.removeChild(tr);
}

</script> 
</body>
</html>


