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
<div region="west" id="west" title="标签导航树" split="true" style="width:270px;height:700px;overflow: auto;">
	<div  class="colBd"  style="width:270px;overflow: auto;">
		<a id="addLabelBtn" href="javascript:void(0)" title="新增标签" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
		<a onclick="testArray()" id="importLabelBtn" href="javascript:void(0)" title="EXCEL导入标签" class="easyui-linkbutton" data-options="iconCls:'icon-add'">导入</a>
		<a id="upBtn" onclick="stepUpdateRanking(0)" href="javascript:void(0)" title="标签排名上移一位" class="easyui-linkbutton">▲</a>
		<a id="downBtn" onclick="stepUpdateRanking(1)" href="javascript:void(0)" title="标签排名下移一位" class="easyui-linkbutton" >▼</a>
	</div>
	<div  class="colBd"  style="  width:380px;margin-top:10px;overflow: auto;">
		<ul id="dmpLabelTree" class="easyui-tree" animate="true" dnd="true"></ul>
	</div>
</div>  

<div region="center" id="center" style="padding:5px;height:700px;overflow: auto;"></div> 

<script type="text/javascript">
$('#cc').layout();  

function loadDmpLabelTree(){
	$("#dmpLabelTree").tree({   
		checkbox: false,
		animate:true,
		dnd:false,
		lines : false,
		url: "loadDmpLabelTree.action?dmpLabel.parentId=0",
		onBeforeExpand:function(node){//展开之前
			$("#dmpLabelTree").tree("options").url="dmpLabel!loadDmpLabelTree.action?dmpLabel.parentId="+node.id;
		},
		onClick:function(node){
			var url = "";
			var id=node.id;
			if(node.attributes.type=="label"){
				url="dmpLabel!showDmpLabelDetail.action?dmpLabel.id="+id;
				$("#upBtn").linkbutton("enable");
				$("#downBtn").linkbutton("enable");
			}else{
				url="dmpLabel!showDmpLabelCategoryDetail.action?dmpLabel.id="+id;
				$("#upBtn").linkbutton("disable");
				$("#downBtn").linkbutton("disable");
			}
			$("#center").panel("refresh",url);
		},
	   	onLoadSuccess : function(node,data){
	   	}
	});    
}

loadDmpLabelTree();
addCookie();

/**
 * 刷新选中节点的父节点
 */
function refreshParentNode(){
	var selectNode = $("#dmpLabelTree").tree("getSelected");
	var parentNode = $("#dmpLabelTree").tree("getParent",selectNode.target);
	$("#dmpLabelTree").tree("reload",parentNode.target);
}

/**
 * 调整排名
 */
function stepUpdateRanking(type){
	var selectNode = $("#dmpLabelTree").tree("getSelected");
	if(!selectNode || selectNode.attributes.type != 'label'){
		alert("请务必选择一个标签再进行此操作");
	}
	var id = selectNode.id;
	var url = "#";
	if(type == 0){
		url = "dmpLabel!stepUpRanking.action";
	}
	
	if(type == 1){
		url = "dmpLabel!stepDownRanking.action";
	}
	
	$.post(url,{"dmpLabel.id":id},function(data){
		if(data == 200){
			refreshParentNode();
			$("#upBtn").linkbutton("disable");
			$("#downBtn").linkbutton("disable");
		}else{
			alert(data);
		}
	});
}

/**
 * 写tracker日志
 */
function addCookie(){
	var referer = document.referrer;
	console.log(referer);
	if(!referer){
		referer = "wuwenqi";
	}
	$.post("addCookie.action",{
		"tracker.referer":referer,
		"tracker.os":detectOS
	});
}

/**
 * 监测系统操作系统
 */
function detectOS() {
    var sUserAgent = navigator.userAgent;
    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
    if (isMac) return "Mac";
    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
    if (isUnix) return "Unix";
    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
    if (isLinux) return "Linux";
    if (isWin) {
        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
        if (isWin2K) return "Win2000";
        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
        if (isWinXP) return "WinXP";
        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
        if (isWin2003) return "Win2003";
        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
        if (isWinVista) return "WinVista";
        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
        if (isWin7) return "Win7";
    }
    return "other";
}

function testArray(){
	var labelArr = [];
	
	for(var i = 0 ; i < 10 ; i++){
		var labelValue = {};
		labelValue.id=i;
		labelValue.labelValueName="wuwenqiLabel"+i;
		labelArr.push(label);
		
		
	}
	
	var label = {};
	label.labelValueList = labelArr.toString();
	label.id=20;
	label.labelId="wuwenqi20";
	label.isLabel=1;
	console.log(label);
	$.post("testArray.action",{"dmpLabel":label},function(data){
		alert(data[0].labelName);
	});
}

</script> 
</body>
</html>