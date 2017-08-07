<%@ page contentType="text/html;charset=UTF-8" %>
<%String ctx = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<body id="cc" >

<s:iterator value="imgList" id="image" status="st">
<img id="currentImg" 
alt="can not show the pic" 
src="${image.imgUrl}" 
title="${image.imgUrl}"  
ref="${image.imgUrl}" height="600" 
onclick="showSelf(this)">
<br/>
</s:iterator>

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
		$(obj).removeAttr("height");
	}else{
		$(obj).attr("height","100");
	}
	i++;
}


window.onscroll = function(){ 
    var t = document.documentElement.scrollTop || document.body.scrollTop;
    alert(t);
    if( t >= 300 ) { 
        $("img").attr("src",$("img").attr("ref"));
    } else { 
       
    } 
} 
</script> 
</body>
</html>