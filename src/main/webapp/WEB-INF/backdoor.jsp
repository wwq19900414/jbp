<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<%String _base = request.getContextPath(); %>
<title>JBP用户行为数据API管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<link rel="stylesheet" type="text/css" href="<%=_base%>/css/main.css">
		<script src="<%=_base%>/js/jquery-1.6.2.js"></script>
		<script src="<%=_base%>/js/jquery.md5.js"></script>
		<script src="<%=_base%>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=_base%>/js/ajaxfileupload.js"></script>
</head> 
<body style="overflow: auto;">
	<form action="saveCampaign.action" method="post" name="campaignForm" id="campaignForm" enctype="multipart/form-data"> 
		<fieldset style="width: 90%; height:50px;padding:5px;spacing:20px;margin:20px;">
			<legend><b>当前管理员的操作码</b></legend><input type="password" id="operateCode" value="${operateCode}"/>
		</fieldset>

		<hr/>
		
		<fieldset style="width: 90%; height:100px;padding:5px;spacing:20px;margin:20px;">
			<legend><b>googleFeed</b></legend>
   			<div style="border:0 solid #808080;width:100%;height:40px;padding:7px;" class="field">
   				<input type="button" onclick="googleFeed()" value="生成googleFeed"/>
   				<input type="button" id="noPackage" onclick="switchGoogleFeed(1)" value="设置为一号店自营商品"/>
				<input type="button" id="hasPackage" onclick="switchGoogleFeed(2)" value="设置为全站所有商品"/>
		    	<a href="javascript:void(0)" title="【1】:一号店自营商品，【2】：全站所有商品">feed数据范围:</a>
		    	<label id="googleFeedStatus">${googleFeedFlag}</label>
   			</div>
   			<div style="border:0 solid #808080;width:100%;height:40px;padding:7px;" class="field">
   				<form action="saveCampaign.action" method="post" enctype="multipart/form-data"> 
				<input type="file" id="upload" name="upload">
				<input type="button" onclick="uploadFile()" value="上传文件">
			</form>
   				&nbsp;&nbsp;|&nbsp;&nbsp;
   				<input type="button" id="list" onclick="listFile()" value="列出可下载文件"/>
   			</div>
		</fieldset>
		
		<hr/>
		
		<fieldset style="width: 90%; height:260px;padding:5px;spacing:20px;margin:20px;">
			<legend><b>执行计数和求和的sql</b></legend>
   			<div style="border:0 solid #808080;width:100%;height:40px;padding:7px;" class="field">
				<label>请输入sql(只能select,返回整数):</label><br/>
				<textarea rows="8" cols="100" id="sql"></textarea>
   				<input type="button" onclick="countRecord()" value="执行BI计数sql"/>
   				<input type="button" onclick="exeSql()" value="执行adm库sql"/>
   			</div>
   			
   			
		</fieldset>
		
		<hr/>
		
		<fieldset style="width: 90%; height: 300;padding:5px;spacing:20px;margin:20px;">
			<legend><b>重跑所有类型接口数据(包括打包track数据)后门</b></legend>
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>重跑所有类型接口数据</label>
		    	<input type="text" size="10" readonly="readonly" id="date" onclick="WdatePicker()"/>
				<input type="button" onclick="collectData()" value="强制性重跑接口数据" title="【强制性】体现在已有了该天的数据也得删了再重跑"/>
				<input type="button" onclick="reCollectData()" value="修复性重跑接口数据" title="【修复性】是指的系统没有这一天某种类型的数据才会重跑,已经有了则不再重跑"/>
				
				<input type="button" onclick="countDataNum()" value="查看这一天数据量"/>
				<input type="button" id="closeTask" onclick="switchTask(2)" value="关闭定时任务"/>
				<input type="button" id="openTask" onclick="switchTask(1)" value="开启定时任务"/>
				<a href="javascript:void(0)" title="【1】:开启状态，【2】：关闭状态">定时任务状态:</a>
				<label id="taskStatus">${taskFlag}</label>
		    </div>
		</fieldset>
		
		
		
		
		<fieldset style="width: 90%; height: 200;padding:5px;spacing:20px;margin:20px">
			<legend><b>重跑某一类型接口数据后门</b></legend>
			<div style="border:0 solid #808080;width100%;height:40px;padding:7px;" class="field">
		    	<label>请选择要重跑数据的时间段</label>
		    	<input type="text" size="10" readonly="readonly" id="typeDate" onclick="WdatePicker()"/>
		    	到
		    	<input type="text" size="10" readonly="readonly" id="typeEndDate" onclick="WdatePicker()"/>
			</div>
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择要重跑的数据类型</label>
				<select id="type">
					<option value="1" selected="selected">PageView</option>
					<option value="2">CartInfo</option>
					<option value="3">OrderInfo</option>
					<option value="4">OrderProduct</option>
					<option value="6">CouponInfo</option>
				</select>
				<input type="button" onclick="collectTypeData()" value="重跑接口数据"/>
		    </div>
		</fieldset>
		
		
		
		<hr/>
		
		<fieldset style="width: 90%; height: 150;padding:5px;spacing:20px;margin:20px">
			<legend><b>打包系统所有渠道track数据后门</b></legend>
			
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择要打包的数据的日期</label>
		    	<input type="text" size="20"  readonly="readonly" onclick="WdatePicker()" maxlength="10" id="packageAllDate">
		    	<input type="button" onclick="packageAllData()" value="重新打包所有渠道数据"/>
		    	
		    	<input type="button" id="noPackage" onclick="switchPackage(2)" value="设置为没有打包"/>
				<input type="button" id="hasPackage" onclick="switchPackage(1)" value="设置为已经打包"/>
		    	<a href="javascript:void(0)" title="【1】:已经打包，【2】：尚未打包">打包状态:</a>
		    	<label id="packageStatus">${packageFlag}</label>
		    </div>
		</fieldset>
		
		
		<fieldset style="width: 90%; height: 150;padding:5px;spacing:20px;margin:20px">
			<legend><b>打包某一渠道track数据后门</b></legend>
			
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择要打包的数据的日期</label>
		    	<input type="text" size="20"  readonly="readonly" onclick="WdatePicker()" maxlength="10" id="packageDate">
		    </div>
		    
		    <div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择要打包的数据所属渠道</label>
				<s:select id="road" list="agentList" listValue="agentName" listKey="apiId"></s:select>
				<input type="button" onclick="packageData()" value="重新打包某一渠道数据"/>
				
			</div>
		</fieldset>
		
		
		
		
		<hr/>
		
		
		
		
		<fieldset style="width: 90%; height: 150;padding:5px;spacing:20px;margin:20px">
			<legend><b>XML数据检验</b></legend>
			
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择数据的日期</label>
		    	<input type="text" size="20"  readonly="readonly" onclick="WdatePicker()" maxlength="10" id="preDate">
		    </div>
		    
		    <div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择数据的类型</label>
		    	<select id="preType">
					<option value="1">PageView</option>
					<option value="2">CartInfo</option>
					<option value="3">OrderInfo</option>
					<option value="4">OrderProduct</option>
					<option value="5" selected>TrackInfo</option>
					<option value="6">CouponInfo</option>
					<option value="7">DspOrderInfo</option>
				</select>
		    </div>
		     <s:iterator value="agentList" id="agent">
				<input type="hidden" id="<s:property value="#agent.apiId"/>" value="<s:property value="#agent.apiId"/>_<s:property value="#agent.apiPassword"/>">
			</s:iterator>
		    <div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
				<lable>要访问的主机及项目(可不输入,不输入时为http://adm.yihaodian.com/adoutside)</lable>
				<input type="text" id="host" value="http://adm.yihaodian.com/adoutside"/>
			</div>
			<div style="border:0 solid #808080;width100%;height:30px;padding:7px;" class="field">
		    	<label>请选择数据所属渠道</label>
				<s:select id="preRoad" list="agentList" listValue="agentName" listKey="apiId"></s:select>
				<input type="button" onclick="previewData()" value="查看返回结果数据"/>
				<input type="button" onclick="countAgentDataNum()" value="查看某一代理商数据"/>
				<input type="button" onclick="previewApiInfo()" value="查看某一代API账号信息"/>
				
			</div>
		    
		</fieldset>
    </form>
</body> 

<script type="text/javascript"> 

/**
 * BI库执行计数或统计的sql
 */
function googleFeed(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	$.ajax({
		url : "googleFeed.action?operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					alert(data);
				}
	});
}


/**
 * BI库执行计数或统计的sql
 */
function countRecord(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var sql =$("#sql").val();
	if(sql == ""){
		alert("需要执行的sql呢呢。。。");
		return;
	}
	
	$.ajax({
		url : "getCount.action?sql=" + sql+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					alert(data);
				}
	});
}


/**
 * adm库执行sql
 */
function exeSql(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var sql =$("#sql").val();
	if(sql == ""){
		alert("需要执行的sql呢呢。。。");
		return;
	}
	
	$.ajax({
		url : "executeSql.action?sql=" + sql+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					alert(data);
				}
	});
}


/**
 * 查看某一日期的API数据量
 */
function countDataNum(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#date").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要查看数据量的日期!!!");
		return;
	}
	$.ajax({
		url : "countDataNum.action?date=" + dateStr+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					alert(data);
				}
	});
}



/**
 * 强制重跑所有类型接口某一天的数据
 */
function collectData(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#date").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要重跑数据的日期!!!");
		return;
	}
	$.ajax({
			url : "collectDataFromBI.action?date=" + dateStr+"&operateCode=" + operateCode, 
			type : "GET",
			success : function(data){
						alert("collect API data " + data);
					}
	});
}

/**
 * 修复性重跑所有类型接口某一天的数据(即没有数据时才重跑)
 */
function reCollectData(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#date").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要重跑数据的日期!!!");
		return;
	}
	$.ajax({
			url : "reCollectDataFromBI.action?date=" + dateStr+"&operateCode=" + operateCode, 
			type : "GET",
			success : function(data){
						alert("collect API data " + data);
					}
	});
	
}


/**
 * 重跑某种类型接口数据
 */
function collectTypeData(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#typeDate").val();
	var endDateStr = $("#typeEndDate").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要重跑数据的日期!!!");
		return;
	}
	
	if($.trim(endDateStr)==""){
		endDateStr = dateStr;
	}
	
	var type = $("#type").val();
	$.ajax({
			url : "collectTypeDataFromBI.action?date=" + dateStr+"&type="+type+"&endDate="+endDateStr+"&operateCode=" + operateCode, 
			type : "GET",
			success : function(data){
						alert(data);
					}
	});
}


/**
 * 重新打包某一日期内某一特定代理商的track数据
 */
function packageData(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#packageDate").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要重跑数据的日期!!!");
		return;
	}
	var road = $("#road").val();
	$.ajax({
			url : "packageTrackInfo.action?date=" + dateStr+"&agentId="+road+"&operateCode=" + operateCode, 
			type : "GET",
			success : function(data){
						alert("package track data "+data);
					}
	});
}


/**
 * 重新打包track某一日期内系统所有代理商的track数据
 */
function packageAllData(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	var dateStr = $("#packageAllDate").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要重跑数据的日期!!!");
		return;
	}
	$.ajax({
			url : "packageTrackInfo.action?date=" + dateStr+"&operateCode=" + operateCode, 
			type : "GET",
			success : function(data){
						alert(data);
					}
	});
}

function switchPackage(status){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	
	$.ajax({
		url : "switchPackage.action?packageFlag=" + status+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					$("#packageStatus").text(""+data);
				}
	});
}


function switchTask(status){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	$.ajax({
		url : "switchTask.action?taskFlag=" + status+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					$("#taskStatus").text(""+data);
				}
	});
}


function switchGoogleFeed(status){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	$.ajax({
		url : "switchGoogleFeed.action?googleFeedFlag=" + status+"&operateCode=" + operateCode, 
		type : "GET",
		success : function(data){
					$("#googleFeedStatus").text(""+data);
				}
	});
}



function uploadFile(){
	var fileName = $("#upload").val();
	if(fileName == ""){
		alert("请选择要上传的文件");
		return;
	}
	
	if(confirm("确定上传的文件符合标准吗?")){
		 $.ajaxFileUpload({
			 url:"uploadFile.action",
			 secureuri:false,
			 fileElementId:"upload",
			 dataType: 'text',
			 success: function (data, status) {
				 alert(data);
			 },
			 error: function (data, status, e) {
				 alert(e);
				 
			 }		
		 });
	}
}


/**
 * 另开一个页面来列出可供下载的文件
 */
function listFile(){
	var operateCode = $("#operateCode").val();
	if(operateCode == ""){
		alert("该操作需要操作码哦!!!")
		return;
	}
	
	window.open("listFile.action?operateCode=" + operateCode);
}















/**
 * 查看某一代理商某一日期的数据量
 */
function countAgentDataNum(){
	var dateStr = $("#preDate").val();
	var road = $("#preRoad").val();
	if($.trim(dateStr)==""){
		alert("你没有选择要查看数据量的日期!!!");
		return;
	}
	
	if($.trim(road)==""){
		alert("你没有选择要查看数据的代理商!!!");
		return;
	}
	
	var host = $.trim($("#host").val());
	$.ajax({
		url : host+"/service/analysis/countAgentDataNum.action?date=" + dateStr+"&agentId="+road, 
		type : "GET",
		success : function(data){
					alert(data);
				}
	});
}

/**
 * 模拟代理商获取数据
 */
function previewData(){
	var dateStr = $("#preDate").val();
	var type = parseInt($("#preType").val());
	var road = $("#preRoad").val();
	if($.trim(dateStr)==""){
		alert("你没有所查看数据的日期!!!");
		return;
	}
	
	var url = "http://adm.yihaodian.com/adoutside";
	var host = $.trim($("#host").val());
	if(host != ""){
		url = host;
	}
	url += "/service/analysis/";
	
	switch(type){
	case 1:
		url += "getPageViewInfo";
		break;
	case 2:
		url += "getCartInfo";
		break;
	case 3:
		url += "getOrderInfo";
		break;
	case 4:
		url += "getOrderProductInfo";
		break;
	case 5:
		url += "getTrackInfo";
		break;
	case 6:
		url +="getCouponInfo";
		break;
	case 7:
		url +="getDspOrderInfo";
		break;
	}
	url+=".action?params="+dateStr+"_";
	
	var appIdAndKey = $("#"+road).val();
	url+=appIdAndKey;
	
	var now = new Date();
	var nowTime = now.getTime();
	
	if(type == 5){
		url += "_06b8fd4570c747741b7dfee434e9dd_";
	}else if(type == 7){
		url += "_1_"+$.md5('O&VtQbgl'+appIdAndKey.split('_')[0]+nowTime)+"_";
	}else{
		url += "_0_06b8fd4570c747741b7dfee434e9dd_";
	}
	
	url += nowTime;
	
	window.open(url);
}


/**
 * 模拟代理商获取数据
 */
function previewApiInfo(){
	var road = $("#preRoad").val();
	
	var url = "http://adm.yihaodian.com/adoutside";
	var host = $.trim($("#host").val());
	if(host != ""){
		url = host;
	}
	url += "/service/analysis/getApiTrackerU.action?params=";
	
	var appIdAndKey = $("#"+road).val();
	url+=appIdAndKey;
	window.open(url);
}

/**
 * 看BI新增的我们没有的tracker_u信息
 */
function showBITrack(){
	var url = "http://adm.yihaodian.com/adoutside";
	var host = $.trim($("#host").val());
	if(host != ""){
		url = host;
	}
	
	url+="/service/analysis/getBITrackerUs.action";
	window.open(url);
}
</script> 

</html>


