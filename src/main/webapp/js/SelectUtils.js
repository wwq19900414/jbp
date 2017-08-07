/**
 * 对于<select>元素的操作类
 */
SelectUtils = {
/**
 * 将源select控件中选中的项复制到目标select控件中(text重复的不会添加进目标控件)
 */
copySelectedItems : function(srcSelectObjId,targetSelectObjId){
	var srcSelectObj = document.getElementById(srcSelectObjId);
	var targetSelectObj = document.getElementById(targetSelectObjId);
	for (var i = 0; i < srcSelectObj.options.length; i++) {
		var option = srcSelectObj.options[i];
		if(option.selected){
			if(!SelectUtils.containValue(targetSelectObjId,option.value)){
				var optionAdd = new Option(option.text,option.value);
				optionAdd.title = option.value;
        		targetSelectObj.options.add(optionAdd);
			}
			
		}
	}
},


/**
 * 删除指定id的select下拉框中的选中项
 */
removeSelected :function(selectObjId){
	var selectedObj = document.getElementById(selectObjId);
	for (var i = selectedObj.options.length -1 ; i >= 0 ; i--) {
			var option = selectedObj.options[i];
			if(option.selected){
				selectedObj.options.remove(i);
			}
	}
},


/**
 * 指定id的select下拉框中的显示文本是否包含了value值的项
 */
containValue : function(selectObjId,value){
	var selectedObj = document.getElementById(selectObjId);
	for (var i = selectedObj.options.length -1 ; i >= 0 ; i--) {
		var option = selectedObj.options[i];
		if(option.value == value){
			return true;
		}
	}
	return false;
},

/**
 * 清空下拉列表
 */
clearSelectObj:function (selectObjId){
	var selectedObj = document.getElementById(selectObjId);
	while(selectedObj.childNodes.length > 0){
		selectedObj.removeChild(selectedObj.childNodes[0]);
	}
},


/**
 * 获取指定ID下拉框所有选项
 */
getItems : function (selectObjId){
	var selectedObj = document.getElementById(selectObjId);
	return selectedObj.options;
},

/**
 * 获取指定ID下拉框所有选项值,以###号分隔
 */
getItemNum : function(selectObjId){
	var values = "";
	var selectedObj = document.getElementById(selectObjId);
	return selectedObj.options.length
},


setValue : function(selectObjId,value){
	var selectObj = document.getElementById(selectObjId);
	for (var i = 0; i < selectObj.options.length; i++) {
		var option = selectObj.options[i];
		if(option.value == value){
			option.selected = true;
		}
	}
}

}