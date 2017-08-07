<%@ page contentType="text/html;charset=UTF-8" %>
<%String ctx = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>DSP渠道效果</title>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/style.css" media="all" />
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=ctx%>/js/jquery.autocomplete.css"/>
<script type="text/javascript" src="<%=ctx%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/js/ajaxfileupload.js"></script>
</head>
<body id="cc" class="easyui-layout">
<input type="hidden" id="imgId" value="${img.id}"/>

<img id="currentImg" alt="can not show the pic" src="${img.imgUrl}" title="${img.imgUrl}" height="500" onclick="showSelf(this)">

<hr/>

<input type="button" onclick="showPreImg()" value="上一张图片"/>
<input type="button" onclick="showNextImg()" value="下一张图片"/>
<input type="button" onclick="deleteImg()" value="删除"/>

<br/>

<label>编号：</label>
<label id="imgIdLabel">${img.id}</label>
<br/>
<label>时间：</label>
<label id="imgCreateTime">${img.createTimeStr}</label>
<script type="text/javascript">
function showNextImg(){
	var imgId = $("#imgId").val();
	$.post("showNextImg.action",{"img.id":imgId},function(data){
		var obj = eval("("+data+")");
		$("#currentImg").attr("src",obj.imgUrl);
		$("#currentImg").attr("title",obj.imgUrl);
		$("#imgId").val(obj.id);
		$("#imgIdLabel").text(obj.id);
		$("#imgCreateTime").text(obj.createTimeStr);

		$("#imgUrl").val(obj.imgUrl);
	});
}

function showPreImg(){
	var imgId = $("#imgId").val();
	$.post("showPreImg.action",{"img.id":imgId},function(data){
		var obj = eval("("+data+")");
		$("#currentImg").attr("src",obj.imgUrl);
		$("#currentImg").attr("title",obj.imgUrl);
		$("#imgId").val(obj.id);
		$("#imgIdLabel").text(obj.id);
		$("#imgCreateTime").text(obj.createTimeStr);
		$("#imgUrl").val(obj.imgUrl);
	});
}

var i=0;
function showSelf(obj){
	if(i % 2 == 0){
		$(obj).removeAttr("height","500");
	}else{
		$(obj).attr("height","500");
	}
	i++;
}
</script> 
</body>
</html>