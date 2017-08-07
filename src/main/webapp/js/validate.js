/**
 * Validate plugin for jQuery
 * @author Rain Ye  2010/06/17
 * @modifier Shaofeng Shi 2010/06/19
 */
(function($) {	
	$.validator = {
			validateForm: function(formObj) {
				var arr = jQuery.makeArray();
				var ymArr = jQuery.makeArray(); 
				var validateResult = true;
				//获取表单的checkbox和radio元素
				$("input:checkbox,input:radio", formObj).each (
					function () {
						//触发输入框的时候，提示应该消失
						if ($.data(this, "bindFocusClear")!= 1) {
							$(this).bind("focus", function(event) {
								$.validator.clearValidateMessage(this);
							});
							$.data(this, "bindFocusClear", 1);
						}
						
						
						/*獲取所有驗證信息以key-value形式存入數組里*/
						var valAttr = $.validator.getValidateAttr(this);
						$.validator.clearValidateMessage(this);
						if(!$.data(this, "validatebox")) {
							$.data(this, "validatebox", {message: "", tip: null,offset_x:0, offset_y:0});
						}
						if(valAttr["validate_offset_x"]) {
							var offsetX=parseInt(valAttr["validate_offset_x"]);
							$.data(this, "validatebox").offset_x=offsetX;
						}
						
						if(valAttr["validate_offset_y"]) {
							var offsetY=parseInt(valAttr["validate_offset_y"]);
							$.data(this, "validatebox").offset_y=offsetY;
						}

						if(valAttr["not-null"] && valAttr["not-null"] == "true") {
							//排除已做过判断的同名元素
							if (jQuery.inArray(this.name, arr) == -1) {
								//同名的checkbox或者radio有一个被选中即不为null
								if($("[type=" + this.type + "][name=" + this.name + "][checked=true]", formObj).length <= 0) {
									$.validator.setValidateMessage(this, message.global_validate_z0);
									$.validator.showValidate(this);
									validateResult  = false;
								}
								arr.push(this.name);
							}
						}
					}
				);
				
				//获取表单的非checkbox,radio,button的input,select元素
				$("input,select,textarea", formObj).not("input:checkbox,input:radio,input:button").each(
					function() {
						//触发输入框的时候，提示应该消失
						if ($.data(this, "bindFocusClear")!= 1) {
							$(this).bind("focus", function(event) {
								$.validator.clearValidateMessage(this);
							});
							$.data(this, "bindFocusClear", 1);
						}
						
						
						var valAttr = $.validator.getValidateAttr(this);
						$.validator.clearValidateMessage(this);
						if(valAttr["not-null"] && valAttr["not-null"] == "true") {
							if(!$.data(this, "validatebox")) {
								$.data(this, "validatebox", {message: "", tip: null,offset_x:0, offset_y:0});
							}
							if(valAttr["validate_offset_x"]) {
								var offsetX=parseInt(valAttr["validate_offset_x"]);
								$.data(this, "validatebox").offset_x=offsetX;
							}
							if(valAttr["validate_offset_y"]) {
								var offsetY=parseInt(valAttr["validate_offset_y"]);
								$.data(this, "validatebox").offset_y=offsetY;
							}
							if(this.value.isNull()) {
								ymArr.push(this); // added by yangming - 2010-12-23
								$.validator.setValidateMessage(this, message.global_validate_z0);								
								$.validator.showValidate(this);
								validateResult  = false;
								//$('html,body').animate({scrollTop:$(ymArr[0]).offset().top}, 200); // added by yangming - 2010-12-23
							}
						}
						
						if(!$.validator.checkMaxLength(this)) {
							validateResult  = false;
						}

						if(!$.validator.checkDataType(this)) {
							ymArr.push(this);
							validateResult  = false;
							//$('html,body').animate({scrollTop:$(ymArr[0]).offset().top}, 200);
						}
					}
				);
				//获取表单的非checkbox,radio,button,hidden的input,select元素
				$("input,textarea", formObj).not("input:checkbox,input:radio,input:button,input:hidden").each(
					function() {
						//触发输入框的时候，提示应该消失
						if ($.data(this, "bindFocusClear")!= 1) {
							$(this).bind("focus", function(event) {
								$.validator.clearValidateMessage(this);
							});
							$.data(this, "bindFocusClear", 1);
						}
						
						var valAttr = $.validator.getValidateAttr(this);					
						//$.validator.clearValidateMessage(this);						
						//特殊字符提示
						if(valAttr["dangerChars"]&&!valAttr["datatype"]) {
							var dangerChars=valAttr["dangerChars"];
							var dangerArray=dangerChars.split(",");
							if(!$.data(this, "validatebox")) {
								$.data(this, "validatebox", {message: "", tip: null,offset_x:0, offset_y:0});
							}
							if(valAttr["validate_offset_x"]) {
								var offsetX=parseInt(valAttr["validate_offset_x"]);
								$.data(this, "validatebox").offset_x=offsetX;
							}
							
							if(valAttr["validate_offset_y"]) {
								var offsetY=parseInt(valAttr["validate_offset_y"]);
								$.data(this, "validatebox").offset_y=offsetY;
							}
							var content=this.value;
							var contentObje=this;
							var dangerStr=dangerArray.join("");						
							$.each(dangerArray,function(n,value) {
								ymArr.push(contentObje);
							      if(content.indexOf(value) != -1) {
										$.validator.setValidateMessage(contentObje, message.global_validate_z23.format(dangerStr));
										$.validator.showValidate(contentObje);
										validateResult  = false;
										//$('html,body').animate({scrollTop:$(ymArr[0]).offset().top}, 200);
							            return false;
									}
							   });
						 }
					}
				);

				return validateResult;
			},
			getValidateAttr:function(ele) {
				var valAttr = new Object();
				if ($(ele).attr("class")) {
					var clsName = $(ele).attr("class").split(/\s+/);
		            for (var i in clsName) {
		                if (clsName[i].indexOf(":") != -1) {
		                    var temp = clsName[i].split(":");
		                	valAttr[temp[0].trim()] = temp[1].trim();
		                }
		            }
				}
				
	            return valAttr;
			},

			//判断maxlength
			checkMaxLength: function(ele) {
				var valAttr = $.validator.getValidateAttr(ele);
				if(valAttr["maxlength"] && parseInt(valAttr["maxlength"]) < ele.value.replace(/[^\x00-\xff]/gi, 'xx').length){
					var msg = message.global_validate_z15.format(valAttr["maxlength"], parseInt(valAttr["maxlength"])/2);
					$.validator.setValidateMessage(ele, msg);
					$.validator.showValidate(ele);
					return false;
				}
				return true;
			},
			
			//判断 Datatype
			checkDataType: function(ele) {
				var valAttr = $.validator.getValidateAttr(ele);
				var dataType = valAttr["datatype"];
				if (!dataType) return true;

				// 数字型
				if(dataType.toUpperCase() == "NUMBER") {
					if (!ele.value.isNull() && !ele.value.isNumber()) {
						$.validator.setValidateMessage(ele, message.global_validate_z1);
						$.validator.showValidate(ele);
						return false;
					}
				}
				
				//邮政编号PINTZIP
				if (dataType.toUpperCase() == "ZIP") {
					if (!ele.value.isNull() && !ele.value.isPostTW()) {
						$.validator.setValidateMessage(ele, message.global_validate_pintzip);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 浮点型
				if (dataType.toUpperCase().indexOf("FLOAT")>=0) {		
					if (!ele.value.isNull() && !ele.value.isFloat()) {
						$.validator.setValidateMessage(ele, message.global_validate_z1);
						$.validator.showValidate(ele);
						return false;
					}
					//如果有精度控制
					if(dataType.indexOf("(")>=0) {
						len = dataType.substring(dataType.indexOf("(")+1,dataType.indexOf(","));
						prec = dataType.substring(dataType.indexOf(",")+1,dataType.indexOf(")"));
						if(!$.validator.checkPrecision(ele,len,prec)){
							return false;
						}
					}
				}
				//自然数
				if (dataType.toUpperCase()=="NATURAL") {
					if (!ele.value.isNull() && !ele.value.isNatural()) {
						$.validator.setValidateMessage(ele, message.global_validate_z2);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 正整数
				if (dataType.toUpperCase()=="POSITIVEINTEGER" || dataType.toUpperCase() == "PINT") {
					if (!ele.value.isNull() && !ele.value.isPositiveInteger()) {
						$.validator.setValidateMessage(ele, message.global_validate_z3);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 整数
				if (dataType.toUpperCase()=="INT" || dataType.toUpperCase()=="INTEGER") {
					if (!ele.value.isNull() && !ele.value.isInteger()) {
						$.validator.setValidateMessage(ele, message.global_validate_z3);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 日期
				if (dataType.toUpperCase()=="DATE") {
					if (!ele.value.isNull() && !ele.value.isDate()) {
						$.validator.setValidateMessage(ele, message.global_validate_z4);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 日期
				if (dataType.toUpperCase() == "DATETIME") {
					if (!ele.value.isNull() && !ele.value.isDateTime()) {
						$.validator.setValidateMessage(ele, message.global_validate_z5);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 非中文字符
				if (dataType.toUpperCase()=="DSTRING" || dataType.toUpperCase()=="ASTRING") {
					if (!ele.value.isNull() && !ele.value.isNotCN()) {
						$.validator.setValidateMessage(ele, message.global_validate_z6);
						$.validator.showValidate(ele);
						return false;
					}
				}
				
				// URL
				if (dataType.toUpperCase() == "URL") {
					
					if (!ele.value.isNull() && !ele.value.isUrl()) {
						$.validator.setValidateMessage(ele, message.global_validate_z21);
						$.validator.showValidate(ele);
						return false;
					}
				}
				
				// email
				if (dataType.toUpperCase()=="EMAIL") {
					if (!ele.value.isNull() && !ele.value.isEmail()) {
						$.validator.setValidateMessage(ele, message.global_validate_z7);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 固定电话
				if (dataType.toUpperCase() == "PHONE") {
					if (!ele.value.isNull() && !ele.value.isPhone()) {
						$.validator.setValidateMessage(ele, message.global_validate_z8);
						$.validator.showValidate(ele);
						return false;
					}
					
				}
				// 手机号码
				if (dataType.toUpperCase() == "MOBILEPHONE" || dataType.toUpperCase() == "MPHONE") {
					
					if (!ele.value.isNull() && !ele.value.isMobilePhone()) {
						$.validator.setValidateMessage(ele, message.global_validate_z9);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 邮政编码
				if (dataType.toUpperCase() == "POST" ) {
					if (!ele.value.isNull() && !ele.value.isPost()) {
						$.validator.setValidateMessage(ele, message.global_validate_z10);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 身份证
				if (dataType.toUpperCase() == "IDCARD" ) {
					if (!ele.value.isNull() && !ele.value.isIDCard()) {
						$.validator.setValidateMessage(ele, message.global_validate_z11);
						$.validator.showValidate(ele);
						return false;
					}
				}
				// 百分數
				if (dataType.toUpperCase() == "PERCENT" ) {
					if (!ele.value.isNull() && !ele.value.isPercent()) {
						$.validator.setValidateMessage(ele, message.global_validate_z16);
						$.validator.showValidate(ele);
						return false;
					}
				}
				return true;
			},

			// 判断浮点数后缀
			checkPrecision: function(obj, len, prec) {   
				var numReg;    
				var strValueTemp, strInt, strDec;        
				var value = obj.value;
				numReg =/[\-]/;   
				strValueTemp = value.replace(numReg, "");   
				numReg =/[\+]/;   
				strValueTemp = strValueTemp.replace(numReg, "");   
				//整数   
				if(prec==0){   
					numReg =/[\.]/;   
					if(numReg.test(value) == true){   
						$.validator.setValidateMessage(obj, message.global_validate_z12);
						$.validator.showValidate(obj);   
						return false;      
					}              
				}          
				if(strValueTemp.indexOf(".") < 0 ){   
					if(strValueTemp.length >( len - prec)){   
						$.validator.setValidateMessage(obj, message.global_validate_z13.format((len - prec)));
						$.validator.showValidate(obj);  
						return false;   
					}          
				}else{   
					strInt = strValueTemp.substr( 0, strValueTemp.indexOf(".") );          
					if(strInt.length >( len - prec)){   
						$.validator.setValidateMessage(obj, message.global_validate_z13.format((len - prec)));
						$.validator.showValidate(obj);  
						return false;   
					}   
					strDec = strValueTemp.substr( (strValueTemp.indexOf(".")+1), strValueTemp.length );    
					if(strDec.length > prec){   
						$.validator.setValidateMessage(obj, message.global_validate_z14.format(prec));
						$.validator.showValidate(obj);  
						return false;   
					}          
				}          
				return true;   
			},

			clearValidateMessage: function(component) {
				if($.data(component, "validatebox")) {
					$.data(component, "validatebox").message="";
					var _Tip = $.data(component, "validatebox").tip;
					if(_Tip) {
						_Tip.css({
							display : "none"
						});
					}
				}
			},
			autoClearValidateMessage: function(timeout, component){  
			    var _cb = function(){
			    	$.validator.clearValidateMessage(component);
			    };
			    setTimeout(_cb,timeout);
			},
			setValidateMessage: function(component,validMessage) {
				if($.data(component, "validatebox")) {
					if($.data(component, "validatebox").message=="") {
						$.data(component, "validatebox").message=validMessage;
					} else {
						$.data(component, "validatebox").message=$.data(component, "validatebox").message+"&nbsp;&nbsp;"+validMessage;
					}
				} else {
					$.data(component, "validatebox", {message: validMessage, tip: null, offset_x:0, offset_y:0});
				}
				
				//5000毫秒后自动删除该component.
				$.validator.autoClearValidateMessage(150000, component);
			},
			showValidate: function(component) {
				var _Component = $(component);
				var _Message = $.data(component, "validatebox").message;
				var _Tip = $.data(component, "validatebox").tip;
				if (!_Tip) {
					_Tip = $(
							"<div class=\"validatebox-tip\">"
									+ "<span class=\"validatebox-tip-content\">"
									+ "</span>"
									+ "<span class=\"validatebox-tip-pointer\">"
									+ "</span>" + "</div>").appendTo("body");
					$.data(component, "validatebox").tip = _Tip;
				}
				_Tip.find(".validatebox-tip-content").html(_Message);
				_Tip.css( {
					display : "block",
					left : _Component.offset().left + _Component.outerWidth() + $.data(component, "validatebox").offset_x - 40,
					top : _Component.offset().top + $.data(component, "validatebox").offset_y - 10
				});
				//页面变化时调整提示块位置
				$(window).resize(function(){
					if (_Tip.css("display") == "block") {
						_Tip.css( {
							display : _Tip.css("display"),
							left : _Component.offset().left + _Component.outerWidth() + $.data(component, "validatebox").offset_x,
							top : _Component.offset().top + $.data(component, "validatebox").offset_y
						});
					}
				});
			}
	};
})(jQuery);