var jsonSplitStr = '^@|';
/**
 * 主要以name来获取值，如果有同名得name，就会做拼接一个大值给后台去split,split(^@^)可以再做跟进下，总是不好
 * @param divId 获取整个指定div里得元素，并做出json格式
 * @param formObj 提供给后台去解析
 * @returns
 * 页面name属性中值全未输入的情况是不会组装到json里
 */
function getDivJson(divId, formObj) {
	var json = '{';
	var o = false;
	//记录已统计得值，下次name相同得不做统计
	var obj = new Object();
	$("input,select,textarea", $("#" + divId)).not("input:checkbox,input:radio,input:button").each(
			function(i) {
				if ($(this).val() != null && $(this).val().trim() != '' && $(this).attr("name") != 'conditions') {
					var origName = $(this).attr("name");
					
					if (origName && !obj[origName]) {
						var name;
						//name=object.el1 -> el1名称做json key
						if (contains(origName, ".", false)) {
							name = $(this).attr("name").split(".")[1];
						} else {
							name = $(this).attr("name");
						}
						//jquery选择器需要获取得实际name,不以id获取值
						var selectdName = origName.replace('.', '\\.');
						
						json += "\"" + name + "\"";
						json += ":";
						var elValue = '';
						
						if ($("[name='" + selectdName + "']").length > 1) {
							//记录已统计得值，下次name相同得不做统计
							obj[origName] = 1;
							$("[name='" + selectdName + "']").each(function(j) {
								elValue += $(this).val() + jsonSplitStr;
							});
						} else {
							//记录已统计得值，下次name相同得不做统计
							obj[origName] = 1;
							elValue += $(this).val();
						}
						json += "\"" + elValue + "\"";
						json += ",";
						o = true;
					}
				}
			}
	);
	if (o) {
		json = json.substring(0, json.length - 1);
	}
	json += "}";
	if (formObj) {
		json = "{'" + formObj + "':" + "'" + json + "'" + "}"; 
	}
	
	return json;
}


/**
 * 同getDivJson(divId, formObj)
 * @param divId
 * @param formObj
 * @returns {String}
 *  页面name属性中值全未输入的情况是不会组装到json里
 *  但是可以提供一个参数includeNamesWithNull,对null的属性也进行封装
 *  includeNamesWithNull = $(new Object()); 
 *  includeNamesWithNull.data("length", {"filter":0, "sameName":1})  
 *  filter 0:不管有没值都过滤 1:不管有没有值都不过滤，保存到json里；
 *  sameName = 1 因为有些输入框可以动态新增删除，也就是可以出现同样name属性得多列或者只有一列，但是哪怕只有一列，
 *  我们还是以jsonSplitStr的方法拼接起来，让后台代码统一; 
 */
function getDivJsonWithParams(divId, formObj, includeNamesWithNull) {
	var json = '{';
	var o = false;
	//记录已统计得值，下次name相同得不做统计
	var obj = new Object();
	
	$("input,select,textarea", $("#" + divId)).not("input:checkbox,input:radio,input:button").each(
			function(i) {
				//外部参数设置得可否过滤, 0代码不能因为空等条件下过滤除去
				var filterFlag = includeNamesWithNull.data($(this).attr("name")) && includeNamesWithNull.data($(this).attr("name")).filter == 0;
				//外部参数设置得是否是以有多个元素同name存放数据，1:是
				var sameNameFlag = includeNamesWithNull.data($(this).attr("name")) && includeNamesWithNull.data($(this).attr("name")).sameName == 1;
				if (($(this).val() != null && $(this).val().trim() != '' && $(this).attr("name") != 'conditions') || filterFlag) {
					var origName = $(this).attr("name");
					
					if (origName && !obj[origName]) {
						var name;
						//name=object.el1 -> el1名称做json key
						if (contains(origName, ".", false)) {
							name = $(this).attr("name").split(".")[1];
						} else {
							name = $(this).attr("name");
						}
						//jquery选择器需要获取得实际name,不以id获取值
						var selectdName = origName.replace('.', '\\.');
						
						json += "\"" + name + "\"";
						json += ":";
						var elValue = '';
						
						if ($("[name='" + selectdName + "']").length > 1 || sameNameFlag) {
							//记录已统计得值，下次name相同得不做统计
							obj[origName] = 1;
							$("[name='" + selectdName + "']").each(function(j) {
								elValue += $(this).val().trimNull() + jsonSplitStr;
							});
						} else {
							//记录已统计得值，下次name相同得不做统计
							obj[origName] = 1;
							elValue += $(this).val().trimNull();
						}
						json += "\"" + elValue + "\"";
						json += ",";
						o = true;
					}
				}
			}
	);
	if (o) {
		json = json.substring(0, json.length - 1);
	}
	json += "}";
	if (formObj) {
		json = "{'" + formObj + "':" + "'" + json + "'" + "}"; 
	}
	
	return json;
}



//创建select下拉框  validateStr for example class="not-null:true"
function createSelect(divId, elementId, width, height, validateStr) {
	var selectHtml = '<select id="' + elementId + '" name="'+ elementId + '"';
	selectHtml += 'style="';
	if (width) {
		selectHtml += 'width:'+width+'px;';
	}
	if (height) {
		selectHtml += 'height:' + height + 'px;';
	}
	selectHtml += '"';
	if (validateStr) {
		selectHtml += ' ' + validateStr + '>';
	} else {
		selectHtml += '>';
	}
	
	var selectText = $("#" + divId).text();
	var attrs = eval("("+selectText+")");
	var selectJson = attrs.list;
	for (var i = 0; i < selectJson.length; i++) {
		selectHtml += '<option value="' + selectJson[i].key + '">' + selectJson[i].value + '</option>'
	}
	selectHtml += '</select>';
	$("#" + divId).html(selectHtml);
}

/**
 * 在字符串数组中查找字符串是否存在 
 * @param array	字符串数组
 * @param str	要查找的字符串
 * @returns {Boolean}
 */
function isContained(array,str){
	var result = false;
	for(var i=0 ; i<array.length ; i++){
		if(array[i] == str)
			result = true;
	}
	return result;
}
/**
 * 删除字符串数组中指定的元素，并返回删除之后的数组
 * @param array
 * @param str
 * @returns
 */
function removeFromArray(array, str){
	var index = 0;
	for(var i=0 ; i<array.length ; i++){
		if(array[i] == str){
			index = i;
			break;
		}
	}
	return array.splice(index,1);
}

//保留N位小数
//如：num为,n为2(会在2后面补上00.即2.00)
function toDecimal(num,n) {
    var f = parseInt(num * Math.pow(10, n) + 0.5) / Math.pow(10, n);//精度计算
    if (isNaN(f)) {
        return 0.00;
    }

    var f = Math.round(num * n * 10) / (n * 10);
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }

    while (s.length <= rs + n) {
        s += '0';
    }

    return s;

} 

//jquery ui 重写弹出提示框
function alert(message)
{
 if($("#dialogalert").length==0)
 {
  $("body").append('<div id="dialogalert"></div>');
  $("#dialogalert").dialog({
   autoOpen:false,
   title:'消息框',
   model: true,
   width:500,
   buttons:{
    '[关闭]':function(){
       $(this).dialog('close');
    }
   }
   });
 }
 $("#dialogalert").html(message);
 $("#dialogalert").dialog('open');
}

function confirm(message, yesCallback, noCallback)
{
 if($("#dialogconfirm").length==0)
 {
   $("body").append('<div id="dialogconfirm"></div>');
   
   $("#dialogconfirm").dialog({
   autoOpen:false,
   title:'消息框',
   model: true,
   width:500,
   buttons:{
    '[确定]':function(){
    	$(this).dialog('close');
    	yesCallback();
    },
    '[取消]':function(){
        $(this).dialog('close');
        noCallback();
    }
   }
  });
 }
 $("#dialogconfirm").html(message);
 $("#dialogconfirm").dialog('open');
}


//给元素绑定自动补全,没有埋选项值对应的KEY，比如VALUE->LABEL
//checkCall 判断触发得时间是否需要去后台获取服务
function bindAuto(tagAuto, url, dataFunc) {
	$('#'+ tagAuto).autocomplete({
		source: function( request, response) {
						var beforeCondition = {};
						if (dataFunc) {
							beforeCondition = dataFunc();
						}
						
						var q = request.term;
						var requestUrl = '';
						if (contains(url, "?", false)) {
							requestUrl = encodeURI(url + '&q=' + q);
						}  else {
							requestUrl = encodeURI(url + '?q=' + q);
						}
						
						$.ajax({	
					        url : requestUrl,
					        data: beforeCondition,
					        cache : false, 
					        async : false,
					        type : "POST",
					        dataType : 'json',
					        success : function (json){
								 var selectJson = json.list;
								 var arr = new Array();
								 for (var i = 0; i < selectJson.length; i++) {
									arr.push(selectJson[i]);
								 }
								 response(arr);
					        }
					    });
						
		}
	});
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}

//hot.yihaodian.com -> yihaodian.com 
//如果js操作cookie最好加上点号 .yihaodian.com
var getHost = function(url) {
    var host = "null";
    if (typeof url == "undefined"
            || null == url)
        url = window.location.href;
    var regex = /.*\:\/\/([^\/|:]*).*/;
    var match = url.match(regex);
    if (typeof match != "undefined"
            && null != match) {
        host = match[1];
    }
    if (typeof host != "undefined"
            && null != host) {
        var strAry = host.split(".");
        if (strAry.length > 1) {
            host = strAry[strAry.length - 2] + "." + strAry[strAry.length - 1];
        }
    }
    return host;
}

