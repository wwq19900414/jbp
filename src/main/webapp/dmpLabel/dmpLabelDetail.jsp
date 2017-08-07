<%@ page contentType="text/html;charset=UTF-8" %>
<%String ctx = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>标签信息页</title>
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
<div class="boxB clearfix" style="width:94%;height:0px;margin-left:10px;margin-top:10px;margin-bottom:0px;background: url('<%=ctx%>/css/themes/default/images/sky_blue_grid.gif') repeat-x scroll 0 -1px #FAFAFA;">
	<b>标签详情信息</b>
</div>

<div id="detailZone" class="boxB clearfix" style="width:94%;margin-left:10px;margin-top:0px;">
<input type="hidden" id="dmpLabelId" value="${dmpLabel.id}"/>

<table width="50%" border="0" cellspacing="0" cellpadding="0">
<tr height="30">
<td align="right" width="20%">标签目录：</td>
<td ><a href="javascript:void(0)" onclick="showDmpLabelCategoryDetail(${dmpLabel.parentId})" title="标签目录主键id：${dmpLabel.parentId}">${dmpLabel.labelCategoryDesc}</a></td>
<td colspan="2" align="left">
<a id="addLabelBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑标签</a>
</td>
</tr>

<tr height="30">
<td align="right">标签名称：</td>
<td align="left"><a href="javascript:void(0)" title="标签主键id: ${dmpLabel.id}">${dmpLabel.labelName}</a></td>

<td colspan="2" align="left">
标签ID：${dmpLabel.labelId}</td>
</tr>

<tr height="30">
<td align="right">标签描述：</td>
<td colspan="3" align="left">${dmpLabel.labelDesc}</td>
</tr>

<tr height="30">
<td align="right"> 覆盖人群：</td>
<td colspan="3" align="left">
<label>${dmpLabel.userCount}</label>
</td>
</tr>

<tr height="30">
<td align="right">标签值：</td>
<td align="left">${dmpLabel.valueCount}</td>

<td colspan="2" align="left">
<input type="checkbox" id="isSingeSelect" disabled="disabled" <s:if test="dmpLabel.isSingleSelect == 1">checked</s:if>>
<label for="isSingeSelect" align="left">相同开发者互斥</label>
</td>
</tr>

</table>
</div>

<table style="margin:10px;width:99%">
	<tr>
		<td>
			<div class="boxD" id="boxD">
				<div id="tbls">
				</div>
			</div>
		</td>
	</tr>
</table>
	
	
<script type="text/javascript">
/**
 * 查询生成列表
 */
function createGrid(){
		try{
			$('#tbls').datagrid({
				fit : false,
				nowrap : true,
				striped : true,
				url : "dmpLabel!findDmpLabelValue.action",
				queryParams: {
					"dmpLabel.labelId":$("#dmpLabelId").val()
				},
				loadMsg : "正在加载数据,请稍候...",
				remoteSort: true,
				pageList : [10,20,50,100,200],
				pageNumber:1,
				columns : [[{
					title : '值名称',
					field : "labelValueName",
					disabled: true,
					width : 80,
					align : "center",
					formatter : function(val, rec, rowIndex) {
						return "<label title='"+rec.id+"'>"+rec.labelValueName+"</lable>";
					}
				},{
					title : '值ID',
					field : 'labelValueId',
					width : 80,
					align : "center"
				},{
					title : '来源',
					field : 'source',
					width : 180,
					align : "center"
				},{
					title : '开发者',
					field : 'developer',
					width : 180,
					align : "center"
				},{
					title : '状态',
					field : 'status',
					width : 50,
					align : "center",
					formatter : function(val, rec, rowIndex) {
						if(rec.status == 1){
							return "正常";
						}else{
							return "<label style='color:red;'>停用</label>";
						}
					}
				},{
					title : '更新频率',
					field : 'updateFrequency',
					width : 100,
					align : "center"
				},{
					title : '人群',
					field : 'userCount',
					width : 100,
					align : "center"
				}
				]],
				nowrap : false,
				pagination : true,
				rownumbers : true,
				singleSelect : false
			});
		}catch(E){
			alert(E);
		}
		
};

createGrid();

/**
 * 查看标签目录详情
 */
function showDmpLabelCategoryDetail(parentId){
	var url="dmpLabel!showDmpLabelCategoryDetail.action?dmpLabel.id="+parentId;
	$("#center").panel("refresh",url);
}
</script> 
</body>
</html>