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
<div region="north" id="west" title="图片URL" split="true" style="height:30px;overflow: auto;">
	<input type="text"  id="imgUrl" style="width: 600px;"/>
	<input type="button" onclick="showImg()" value="显示图片"/>
	<input type="button" onclick="showPageImg()" value="显示页面图片"/>
	
</div>  

<div region="center" id="center" style="padding:5px;height:600px;overflow: auto;"></div> 

<script type="text/javascript">
function showImg(){
	var imgUrl = $("#imgUrl").val();
	var url = "showImg.action?img.imgUrl="+imgUrl;
	$("#center").panel("refresh",url);
}

function showPageImg(){
	var imgUrl = $("#imgUrl").val();
	var url = "showPageImg.action?img.imgUrl="+imgUrl;
	$("#center").panel("refresh",url);
}
</script> 
</body>
</html>