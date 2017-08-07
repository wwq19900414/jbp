// 勾选所有的checkbox状态为status
selectAll = function(ele, status) {
	status = (status == null || status == true) ? true : false;
	Form.getInputs(document.forms[0], 'checkbox', ele).each(
		function (item) {
			item.checked = status;
		}
	);
}

/* 弹出非模式页面
 *  输入：
 *	  url：需要打开页面的url
 *	  width： 界面宽度
 *	  height：界面高度
 *    name：弹出界面名称
 *    status：界面的一些相关设置项   
 *  返回：
 *	  弹出界面句柄
 */
openwin = function(url, width, height, name, status) {
	if(name == null) {
		name = "win";
	}
	if(status == null) {
		status = "yes";
	}
	newwin = window.open(url,name,"width="+ width + ","
			+ "height="+ height + "," + "status=" + status + "," 
			+ "toolbar=no,menubar=no,location=no,scrollbars=yes,depended=yes,z-look=yes,top=230,left=300");
	newwin.focus();
}

/* 检查 单选选框/多选框 被选中的数目
 *  输入：
 *	  eleName：页面中Radio的名字(name属性)
 *	  theForm：页面中表单的对象 
 *  返回：
 *	  复选框数量
 */	
checkSelect = function(eleName,theForm) {
	return theForm ? $("input[checked='true'][name='" + eleName +"']",theForm).length :
			$("input[checked='true'][name='" + eleName +"']").length
}

/* 判断是否选择了要刪除的记录
 *  输入：
 *	  eleName：Radio/Checkbox的名字
 *	  theForm：表单对象 
 *  返回：
 *	  true/false
 */	
deleteRecord = function(eleName,theForm, error) {
	if(checkSelect(eleName, theForm) < 1 ) {
		error = error || message.msgbox_please_select_del_record;
		top.ymPrompt.alert({message:error,title:message.msgbox_title_prompt,handler:function(tp) {
		}});
		return false;
	}
	var callback = function(){theForm.submit();};
	top.ymPrompt.confirmInfo({message:message.msgbox_confirm_del,title:message.msgbox_title_ask,handler:function(tp) {
		if(tp == 'ok'){
			showWaiting();
			callback();
		}
	}});
	return true;
}

/* 判断是否选择了要上架的记录
 *  输入：
 *	  eleName：Radio/Checkbox的名字
 *	  theForm：表单对象 
 *  返回：
 *	  true/false
 */	
upRecord = function(eleName,theForm, error) {
	if(checkSelect(eleName, theForm) < 1 ) {
		error = error || message.msgbox_please_select_up_record;
		top.ymPrompt.alert({message:error,title:message.msgbox_title_prompt,handler:function(tp) {
		}});
		return false;
	}
	var callback = function(){theForm.submit();};
	top.ymPrompt.confirmInfo({message:message.msgbox_confirm_up,title:message.msgbox_title_ask,handler:function(tp) {
		if(tp == 'ok'){
			showWaiting();
			callback();
		}
	}});
	return true;
}

/* 判断是否选择了要下架的记录
 *  输入：
 *	  eleName：Radio/Checkbox的名字
 *	  theForm：表单对象 
 *  返回：
 *	  true/false
 */	
downRecord = function(eleName,theForm, error) {
	if(checkSelect(eleName, theForm) < 1 ) {
		error = error || message.msgbox_please_select_down_record;
		top.ymPrompt.alert({message:error,title:message.msgbox_title_prompt,handler:function(tp) {
		}});
		return false;
	}
	var callback = function(){theForm.submit();};
	top.ymPrompt.confirmInfo({message:message.msgbox_confirm_down,title:message.msgbox_title_ask,handler:function(tp) {
		if(tp == 'ok'){
			showWaiting();
			callback();
		}
	}});
	return true;
}

/*  
 *  根据名称获取cookie信息
 *  输入：
 *	  name：页面中cookie名称 
 *  返回：
 *	  cookie中对应该名称的值
 */
getCookie = function(name) {
    var start = document.cookie.indexOf(name + "=");
    var len = start + name.length + 1;
    if ((!start) && (name != document.cookie.substring(0, name.length))) {
        return null;
    }
    if (start == -1) {
        return null;
    }
    var end = document.cookie.indexOf(";", len);
    if (end == -1) {
        end = document.cookie.length;
    }
    return unescape(document.cookie.substring(len, end));
}

/*  
 */
setCookie = function(name, value, expires, path, domain, secure) {
	// set time, it's in milliseconds
    var today = new Date();
    today.setTime(today.getTime());
    if (expires) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date(today.getTime() + (expires));
    document.cookie = name + "=" + escape(value) + ((expires) ? ";expires=" + expires_date.toGMTString() : "") + ((path) ? ";path=" + path : "") + ((domain) ? ";domain=" + domain : "") + ((secure) ? ";secure" : "");
}
/*
*/
deleteCookie = function(name, path, domain) {
    if (Get_Cookie(name)) {
        document.cookie = name + "=" + ((path) ? ";path=" + path : "") + ((domain) ? ";domain=" + domain : "") + ";expires=Thu, 01-Jan-1970 00:00:01 GMT";
    }
}

switchKeyBoxes = function (eleName, checked, theForm) {
	theForm = theForm ? theForm : document.forms[0];
	$("input:checkbox[name='" + eleName +"']",theForm).attr("checked",checked);
}

/*表单验证*/
function checkform(form) {
	return $.validator.validateForm(form);
}

/*显示验证提示信息*/
function showValInfo(input, info) {
	$.validator.setValidateMessage(input, info);
	$.validator.showValidate(input);  
}

/*放大镜弹出框*/
function openPrintWin(url, width, height, name, status) {
	if(name == null) {
		name = "win" + Math.ceil(Math.random()*100);
	}
	if(status == null) {
		status = "yes";
	}
	newwin = window.open(url,name,"width="+ width + "," + "height="+ height + "," + "status=" + status + ","
			+ "toolbar=no,menubar=no,location=no,scrollbars=yes,depended=yes,z-look=yes,top=230,left=400");
	newwin.focus();
}


/*判断长度，英文1位，中文2位*/
function getLen(str){
	 var len=str.length;
	 //var re=/[\u4E00-\u9FA5]/g;  //测试中文字符的正则
	 var re = /[^\u0020-\u007e\uff61-\uffef]/g;//測試全角字符
	 if(re.test(str)) { //使用正则判断是否存在中文
		 len= len+str.match(re).length //返回中文的个数
	 }      
   return len;
}

//將分页表格中的<a>转换为<label>
function convertHref2Lbl(){
	//分页表格中的<a>转换为<label>
	$(".dataTable tr td a").each(function() {
		var href = $(this).attr("href");
		var onClickEvent=$(this).attr("onclick");
		var text = $(this).text();
		var $label = $("<label></label>");
		var $parent = $(this).parent();
		$label.text(text);
		$label.css("color", "#0c7ca9").css("text-decoration","underline");
		$label.mouseout(function() {
			$(this).css("color", "#0c7ca9").css("text-decoration","underline");
		}).mouseover(function() {
			$(this).css("color", "#000000").css("text-decoration","none").css("cursor","pointer");
		})

		if (onClickEvent) {
			$label.click(onClickEvent);
		}
		
		if (href && href != "#") {
			$label.click(function() {document.location.href=href;})
		}
		$parent.html("").append($label);
	});
}


/*上传文件*/

var end = 5;
function ajaxupload(end,myUrl,mySuccess,myError) {
	var start = 0;
	for(var i=start;i<=end;i++){
		if ( $.trim($("#file" + start).val())== "") {
			continue;
		}
		
		//1.上传   
		$.ajaxFileUpload( {
			timeout : 10000,
			url : myUrl,//'<s:url action="generalBook!addFile.so?index='+i+'" namespace="/mat" encode="true"/>'
			secureuri : false,
			fileElementId : 'file' + i,
			dataType : 'json',
			success : function(data, status) {
				//如果文件上传成功
			// alert(data.msg+start);alert(start);
			if (data.msg == "success") {
				mySuccess();
			} else {
				myError();
			}
		
		},
		error : function(data, status, e) {
			myError();
			
		}
		})
	}
}


/*判断是否包含exts 中后缀*/
function hasExt(fileId,exts) {
	var fileName=$.trim($("#"+fileId).val());
	if(fileName.indexOf(".")==-1){
		return false;
	}
	
	//判断后缀        
	var fileExtend=fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	for(var i=0;i<exts.length;i++){
		 if(exts[i]==fileExtend){
			 return true;
		 }
	}
	return false;
}

/*ie外浏览器 控件变灰加样式*/
function disabled2gray(){
	var disArr={};
	function	checkDis (){
			$(":disabled").each(function(){
				disArr[this.id]=$(this);
				$(this).addClass("gray"); 
			}); 
		   for (var d in disArr) {
				var tag=   disArr[d];
				if(!tag.is(":disabled")){
					tag.removeClass("gray"); 
				}
		   }	   
		setTimeout(checkDis,400);
	} 
	checkDis();
}
	