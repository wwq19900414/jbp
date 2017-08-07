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
	<b>标签目录详情信息</b>
</div>

<div id="detailZone" class="boxB clearfix" style="width:94%;margin-left:10px;margin-top:0px;">
<input type="hidden" id="dmpLabelCategoryId" value="${dmpLabel.id}"/>

<table width="50%" border="0" cellspacing="0" cellpadding="0">
<tr height="20">
<td align="left">目录名称：${dmpLabel.labelName}</td>

<td align="left">标签数量：${dmpLabel.valueCount}</td>
<td>
<a id="addLabelBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增标签</a>
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
				url : "dmpLabel!findDmpLabel.action",
				queryParams: {
					"dmpLabel.parentId":$("#dmpLabelCategoryId").val()
				},
				loadMsg : "正在加载数据,请稍候...",
				remoteSort: true,
				pageList : [10,20,50,100,200],
				pageNumber:1,
				columns : [[{
					title : '标签名称',
					field : "labelName",
					width : 200,
					disabled: true,
					align : "center",
					formatter : function(val, rec, rowIndex) {
						return "<a title='"+rec.id+"' href='javascript:void(0)'>"+rec.labelName+"</a>";
					}
				},{
					title : '标签值数量',
					field : "valueCount",
					width : 120,
					disabled: true,
					align : "center"
				},{
					title : '覆盖人群',
					field : 'userCount',
					width : 120,
					align : "center"
				},{
					title : '操作',
					field : 'aaa',
					width : 120,
					align : "center",
					formatter : function(val, rec, rowIndex) {
						var content = "";
						content+="<a class='easyui-linkbutton l-btn l-btn-plain' href='javascript:void(0)' onclick='showDmpLabelDetail("+rec.id+")'>"
						+"<span class=\"l-btn-left\" >"
						+"<span class='l-btn-text icon-search' style='padding-left: 20px;'>详情"
						+"</span></span></a>";
						return content;
					}
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
 * 查看标签详情
 */
function showDmpLabelDetail(id){
	var url="dmpLabel!showDmpLabelDetail.action?dmpLabel.id="+id;
	$("#center").panel("refresh",url);
}
</script> 
</body>
</html>