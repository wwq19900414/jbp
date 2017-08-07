//翻頁方法
goPage = function(pageIndex) {
	$("body").mask("正在努力为您加载数据..."); //启动遮罩层
	$("#page input:hidden").eq(0).val(pageIndex);
	$("[name$='page']").filter("input:hidden").eq(0).val(pageIndex);
	
	document.forms[0].submit();
}


//翻頁方法
goPageForPageSize = function(rows) {
	$("body").mask("正在努力为您加载数据..."); //启动遮罩层
	$("[name$='rows']").each(function (i) {
		$(this).val(rows);
	})
	$("[name$='page']").filter("input:hidden").eq(0).val(1);
	document.forms[0].submit();
}

//刷新当前页
reflushLocalPage = function() {
	goPage($("[name$='page']").filter("input:hidden").eq(0).val());
}


var showRGBColor = 'rgb(255, 255, 153)';
var showRGBColorIE = 'rgb(255,255,153)';
var showColor = "#ffff99";
var hideColor = "#f8e6e6";

//comFlag如果是组合列的情况，那子列父列还需要维护关系
changeShow = function(tdId, comFlag) {
	if (!comFlag) {
		//隐藏
		if ($("#"+tdId).css("background-color") == showRGBColor || $("#"+tdId).css("background-color") == showColor || $("#"+tdId).css("background-color") ==  showRGBColorIE) {
			hideTd(tdId);
		} else { //显示
			showTd(tdId);
		}
	} 
	//点击组合列得父列
	if (comFlag == 1) {
		//隐藏
		if ($("#"+tdId).css("background-color") == showRGBColor  || $("#"+tdId).css("background-color") == showColor   || $("#"+tdId).css("background-color") == showRGBColorIE) {
			var hideParentFlag = true; //是否隐藏父列，如果父列下得子列包括得查询列表对应得所有子列，那需要隐藏，反正不可以
			//获得子列的行对象
			var sonTr = $("#"+tdId).parent().next();
			$('td', sonTr).each(function(i) {
				//don't forget me
				if ($(this).attr("id")=='dfm') {
					hideParentFlag = false;
				} else {
					//子列还未隐藏
					if ($(this).css("background-color") == showRGBColor  || $(this).css("background-color") == showColor || $(this).css("background-color") == showRGBColorIE) {
						hideTd($(this).attr("id"));
					}
					
				}
			})
			
			if (hideParentFlag) {
				hideTd(tdId);
			} else {
				$("#"+tdId).css("background-color", hideColor);//虽然不对大列做实质display变化，但是颜色还是要变化
			}
			
		} else { //显示
			var showParentFlag = true; //是否显示父列，如果父列下得子列包括得查询列表对应得所有子列，那需要显示，反正不可以
			//获得子列的行对象
			var sonTr = $("#"+tdId).parent().next();
			$('td', sonTr).each(function(i) {
				//don't forget me
				if ($(this).attr("id")=='dfm') {
					showParentFlag = false;
				} else {
					//子列还未显示
					if ($(this).css("background-color") != showRGBColor || $(this).css("background-color") != showColor || $(this).css("background-color") != showRGBColorIE) {
						showTd($(this).attr("id"));
					}
					
				}
			})
			
			if (showParentFlag) {
				showTd(tdId);
			} else {
				$("#"+tdId).css("background-color", hideColor);//虽然不对大列做实质display变化，但是颜色还是要变化
			}
		}
	}
	
	//点击组合列得子列
	if (comFlag == 2) {
		//隐藏
		if ($("#"+tdId).css("background-color") == showRGBColor  || $("#"+tdId).css("background-color") == showColor || $("#"+tdId).css("background-color") == showRGBColorIE) {
			hideTd(tdId);
			
			//获得子列的行对象,如果行对象中只要至少有一个还是显示得，则不能操作隐藏父列
			var sonTr = $("#"+tdId).parent();
			//是否隐藏父列，如果父列下得子列包括得查询列表对应得所有子列，那需要隐藏，反正不可以
			var hideParentFlag = true; 
			
			$('td', sonTr).each(function(i) {
				//don't forget me
				if ($(this).attr("id")=='dfm') {
					hideParentFlag = false;
				} else {
					//子列还未隐藏
					if ($(this).css("background-color") == showRGBColor || $(this).css("background-color") == showColor || $(this).css("background-color") == showRGBColorIE) {
						hideParentFlag = false;
					}
				}
			});
			
			if (hideParentFlag) {
				var parentTdId = $("#"+tdId).parent().prev().find("td").eq(0).attr("id");
				hideTd(parentTdId);
			};
		} else { //显示
			showTd(tdId);
			var parentTdId = $("#"+tdId).parent().prev().find("td").eq(0).attr("id");
			showTd(parentTdId);
		}
	}
	
}

function getShowTdNum(firstTdId) {
	var showTdNum = 0;
	$("#"+firstTdId).parent().find("td").each(function(i) {
		if ($(this).css("display") != "none") {
			showTdNum++;
		}
	});
	return showTdNum;
}

//jquery(table).each遍历tr的时候，如果你得table得td里也有tr，这样也会遍历出来
//所以我只好在tableTag里对可遍历得一级tr,一级td加了熟悉each='1'
function hideTd(tdId) {
	$("#"+tdId).css("background-color", hideColor);
	var tdNum = tdId.substring(8);
	var tabId = $("#hidTabPage").val();
	var tabObj = $("#"+tabId);
	//非组合列
	if (! contains(tdNum, "-", false)) {
		$("tr[each=1]", tabObj).each(function(i) {
				$("td[each=1]", $(this)).eq(tdNum).css("display", "none");
		});
	} else {
		var tdNumArr = tdNum.split("-");
		var firstTdId = 'dh-' + tdNum;
		$("#"+firstTdId).css("display", "none");
		
		//更新组合列父列得colspan		
		var showTdNum = getShowTdNum(firstTdId);
		$("#"+firstTdId).parent().prev().children().eq(0).attr("colspan", showTdNum);
		$("tr[each=1]", tabObj).each(function(i) {
			if (i != 0) {
				$("table:eq(0) tr:eq(0) td", $("td[each=1]", $(this)).eq(tdNumArr[0]) ).eq(tdNumArr[1]).css("display", "none");
			}
		});
	}
} 

function showTd(tdId) {
	$("#"+tdId).css("background-color", showColor);
	var tdNum = tdId.substring(8);
	var tabId = $("#hidTabPage").val();
	var tabObj = $("#"+tabId);
	//非组合列
	if (! contains(tdNum, "-", false)) {
		$("tr[each=1]", tabObj).each(function(i) {
			var showTd = $("td[each=1]", $(this)).eq(tdNum);
			showTd.css("display", "");
		});
	} else {
		var tdNumArr = tdNum.split("-");
		var firstTdId = 'dh-' + tdNum;
		$("#"+firstTdId).css("display", "");
		
		//更新组合列父列得cospan
		$("#"+firstTdId).parent().prev().children().eq(0).attr("colspan", getShowTdNum(firstTdId));
		
		$("tr[each=1]", tabObj).each(function(i) {
			if (i != 0) {
				var showTd = $("table:eq(0) tr:eq(0) td", $("td[each=1]", $(this)).eq(tdNumArr[0]) ).eq(tdNumArr[1]);
				
				showTd.css("display", "");
			}
		});
	}
}

showEditShowDiv = function(obj) {
	//动画速度  
    var speed = 500;  

    //设置弹出层位置  
    var offset = $(obj).offset();  
    $("#editShowDiv").css({ top: offset.top - 200 + "px", left: offset.left });  
    //动画显示  
    $("#editShowDiv").show(speed); 
}

hideEditShow = function() {
	//动画速度  
    var speed = 500;  
	$("#editShowDiv").hide(speed); 
}

getShowProperties = function() {
	var proSplit = ",";
	var properties = '';
	$("#editShowTable td").each(function(i){
		if ($(this).css("background-color")) {
			if ($(this).css("background-color") == showRGBColor || $(this).css("background-color") == showColor) {
				if ($(this).attr('property')) {
					properties += $(this).attr('property') + ",";
				};
			}
		}
	})
	return properties;
}

//weight 权重，当用户点击查询按钮 weight=1，必须更新查询条件，给下次带去
//当用户第一次进入页面，且没有预查询，直接显示数据得话，压根就没有触发查询按钮让你去hold值
//则你必须在页面初始化得时候加上 weight不传值,因为页面初始化可能是有条件后得刷新
prepareQuery = function(conditionDivId, weight) {
	if (weight != 1) {
		weight = 0;
	} else {
		//如果用户点击查询，则应该是给用户展示第一页去了。 也就是重置查询条件
		if ($("[name$='page']").length != 0 && $("[name$='page']").filter("input:hidden").length != 0) {
			$("[name$='page']").filter("input:hidden").eq(0).val(1);
		}
	};
	if ($("#conditions").val().isNull() || weight == 1) {
		var conditionsJson = getDivJson(conditionDivId);
		$("#conditions").val(conditionsJson);
	};
}

holdQeuryConditios = function(conditionDivId) {
	if (!$("#conditions").val().isNull()) {
		var conditionsJson = $("#conditions").val();
		var queryObj = eval('(' + conditionsJson + ')');
		$("input,select,textarea", $("#" + conditionDivId)).not("input:checkbox,input:radio,input:button").each(
				function(i) {
					var eleName = $(this).attr("name");
					if (eleName != 'conditions') {
						var eleValue = eval("queryObj." + eleName);
						$(this).val(eleValue);
					}
				}
		)
	}
}

//判断查询后有没有数据 true:有  false:没有
hasDatas = function() {
	if ($("#hasDatas").length == 0 || $("#hasDatas").val() == 0) {
		return false;
	} else if ( $("#hasDatas").val() == 1) {
		return true;
	}
	return false;
}


