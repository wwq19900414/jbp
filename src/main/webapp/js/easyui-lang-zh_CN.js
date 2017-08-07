if (jQuery.fn.pagination){
	jQuery.fn.pagination.defaults.beforePageText = '第';
	jQuery.fn.pagination.defaults.afterPageText = '共{pages}页';
	jQuery.fn.pagination.defaults.displayMsg = '显示{from}到{to},共{total}记录';
}
if (jQuery.fn.datagrid){
	jQuery.fn.datagrid.defaults.loadMsg = '正在处理，请稍待。。。';
}
if (jQuery.messager){
	jQuery.messager.defaults.ok = '确定';
	jQuery.messager.defaults.cancel = '取消';
}
if (jQuery.fn.validatebox){
	jQuery.fn.validatebox.defaults.missingMessage = '该输入项为必输项';
	jQuery.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	jQuery.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	jQuery.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
}
if (jQuery.fn.numberbox){
	jQuery.fn.numberbox.defaults.missingMessage = '该输入项为必输项';
}
if (jQuery.fn.combobox){
	jQuery.fn.combobox.defaults.missingMessage = '该输入项为必输项';
}
if (jQuery.fn.combotree){
	jQuery.fn.combotree.defaults.missingMessage = '该输入项为必输项';
}
if (jQuery.fn.calendar){
	jQuery.fn.calendar.defaults.weeks = ['日','一','二','三','四','五','六'];
	jQuery.fn.calendar.defaults.months = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
}
if (jQuery.fn.datebox){
	jQuery.fn.datebox.defaults.currentText = '今天';
	jQuery.fn.datebox.defaults.closeText = '关闭';
	jQuery.fn.datebox.defaults.missingMessage = '该输入项为必输项';
}
