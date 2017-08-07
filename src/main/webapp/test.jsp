<%@ page contentType="text/html;charset=UTF-8" %>
<%String ctx = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>代理商管理</title>
</head>
<body>
<!-- S BoxB -->
	<div class="boxB clearfix" style="width:680px;margin-left:10px;margin-top:0px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="30" id="level1AddTr">
			<td width="40%" align="right">
				<label>*一级渠道类型：</label>
			</td>
			<td>
				<select>
					<option>广告投放</option>
				</select>
			</td>
		</tr>
		<tr height="30" id="level2AddTr">
			<td width="30%" align="right">
				<label>*二级渠道类型：</label>
			</td>
			<td>
				<select>
					<option>导航网站</option>
				</select>
			</td>
		</tr>
		<tr height="30" id="level3AddTr">
			<td width="30%" align="right">
				<label>*三级渠道类型：</label>
			</td>
			<td>
				<select>
					<option>导航</option>
				</select>
			</td>
		</tr>
		<tr height="30" id="level4AddTr">
			<td width="30%" align="right">
				<label>*四级渠道类型：</label>
			</td>
			<td>
				<select>
					<option>360导航</option>
				</select>
			</td>
		</tr>
		<tr height="30">
			<td width="30%" align="right">
				<label>父渠道名称：</label>
			</td>
			<td>
				<select>
					<option>360导航</option>
				</select>
			</td>
		</tr>
		<tr height="30">
			<td width="30%" align="right">
				<label>总投放费用：</label>
			</td>
			<td>
				<input type="text" id="channelNameAdd" value="${channelCategory.channelName}">
				<input type="button" id="channelNameAdd" value="分摊">
			</td>
		</tr>
		<tr height="30">
			<td width="30%" align="right">
				<label>调整费用：</label>
			</td>
			<td>
				<input type="text" id="channelNameAdd" value="${channelCategory.channelName}">
				<input type="button" id="channelNameAdd" value="调整">
			</td>
		</tr>
		<tr height="30">
			<td width="30%" align="right">
				<label>投放月份：</label>
			</td>
			<td>
				2016年3月
			</td>
		</tr>
		<tr height="30">
			<td width="90%" align="right" colspan="2">
				<table>
				<tr>
				 <td width="20%">日期</td>
				 <td width="20%">周</td>
				 <td width="30%">录入费用</td>
				 <td width="30%">实际费用</td>
				</tr>
				
				<tr>
				 <td><input type="checkbox">1</td>
				 <td>二</td>
				 <td><input type="text" id="channelNameAdd"/></td>
				 <td><input type="text" id="channelNameAdd"/></td>
				</tr>
				
				<tr>
				 <td><input type="checkbox">2</td>
				 <td>三</td>
				 <td><input type="text" id="channelNameAdd"/></td>
				 <td><input type="text" id="channelNameAdd"/></td>
				</tr>
				
				<tr>
				 <td><input type="checkbox">3</td>
				 <td>四</td>
				 <td><input type="text" id="channelNameAdd"/></td>
				 <td><input type="text" id="channelNameAdd"/></td>
				</tr>
				
				<tr>
				 <td><input type="checkbox">4</td>
				 <td>五</td>
				 <td><input type="text" id="channelNameAdd"/></td>
				 <td><input type="text" id="channelNameAdd"/></td>
				</tr>
				
				</table>
			</td>
		</tr>
		</table>
	</div>

<script type="text/javascript">
</script>
</body>
</html>