// JavaScript Document
var BankingFed={
	leftNavList:function(){
		var ListItem=$(".side_nav .side_nav_ul li");
		ListItem.find(".nav_item").click(function(){
			var listLi=$(this).parent("li");
			var listLink=listLi.find(".hasList");
			if(listLi.find("ul").hasClass("side_sub_nav_ul")){
				if(listLink.hasClass("list_up")){
					listLink.removeClass("list_up");
					listLi.find(".side_sub_nav_ul").slideUp(300);
				}else{
					listLink.addClass("list_up");
					listLi.find(".side_sub_nav_ul").slideDown(300);
				}
			}else{
				ListItem.removeClass("cur");
				listLi.addClass("cur");	
			}	
		})
	},
	tableTrRemove:function(){
		
		$(".table_01 tr").hover(function(){
			$(this).find(".close_tr_icon").css("display","block");
		},function(){
			$(this).find(".close_tr_icon").css("display","none");	
		});
		
		$(".table_01 .close_tr_icon").click(function(){
			$(this).parents("tr").remove();	
		})
	},
	hoverBox:function(){
		$(".hor_box").hover(function(){
			$(this).find(".cont_list").show();
		},function(){
			$(this).find(".cont_list").hide();
		})	
	},
	loginFocus:function(boxId){
		$(boxId).css("color","#DADADA");
		var txt_value = $(boxId).val();
		$(boxId).live("focus",function(){
			$(this).css("color","#666");
			var txt_true=$(this).val();
			if(txt_true == txt_value){
				$(this).val("");
			}	
		}).live("blur",function(){
			if($(this).val() == ""){
				$(this).val(txt_value);
				$(this).css("color","#DADADA");
			}	
		});
	},
	/*
	**模拟下拉框
	*/
	selectFun:function(arg){
        var defaultArgs = {selectContainer:false,selectTxt:false,selectIcon:false,selectCon:false,eventType:'click'}
            ,usArg = $.extend({},defaultArgs,arg)
            ,selectContainer= $(usArg.selectContainer)
            ,selectTxt = $(usArg.selectTxt,selectContainer)
            ,selectIcon = $(usArg.selectIcon,selectContainer)
            ,selectCon = $(usArg.selectCon,selectContainer);
        selectTxt.unbind('click').bind('click',function(_this){
            var _this = $(this);
            simulateSelectFun(_this);
        });
        selectIcon.unbind('click').bind('click',function(_this){
            var _this = $(this);
            simulateSelectFun(_this);
        });
        //模拟selcet
        function simulateSelectFun(_this){
            var selectContainer = _this.parent(selectContainer)
                ,thisStConChildren = selectContainer.children()
                ,thisSelectCon = thisStConChildren.filter(selectCon);
            if(thisSelectCon.css('display')=='block'){
                thisSelectCon.hide();
            }else{
                $(usArg.selectCon).hide();
                thisSelectCon.show();
            }
            thisSelectCon.find('.radio_choose').change(function(event){
                event ? event.stopPropagation() : event.cancelBubble = true;
                var _this=$(this);
				if(_this.is(':checked')){
					_this.parent("li").addClass('on');
                }else{
					_this.parent("li").removeClass('on');
                }
				
				var text=selectedCity(_this);
				
                thisStConChildren.filter(selectTxt).text(text);
                //thisSelectCon.hide();
            });
			function selectedCity(_this){
				var _text="";
				_this.closest("ul").find(".on").each(function(){
					_text += $(this).text();
				});
				return _text;
			}
            //select 以外区域关闭模拟select
            $(document).click(function(event){
                if(thisSelectCon.css('display')=='block'){
                    var elem = $(event.target);
                    if(elem.closest(usArg.selectContainer).length == 0){
                        thisSelectCon.hide();
                    }
                }
            });
        }
    },
	showHideClick:function(){
		$(".table_03 .tab_show_icon").click(function(){
			if($(this).hasClass("tab_hide_icon")){
				
				$(this).removeClass("tab_hide_icon").parents("tr").next("tr.tab_show_box").hide();
			}else{
				$(this).addClass("tab_hide_icon").parents("tr").next("tr.tab_show_box").show();
			}
		})
	},
	changeTab:function(boxId){
		var $div_li=$(boxId).find("ul li");
		$div_li.click(function(){
			$(this).addClass("cur").siblings().removeClass("cur");
			var index=$div_li.index(this);
			$(boxId +" > div").eq(index).show()
								 .siblings("div").hide();
			$(boxId +" > div").eq(index).removeClass("none").addClass("clearfix")
			 .siblings("div").removeClass("clearfix").addClass("none");
		});
	},
	checkedFun:function(){
		$("#sports_area").change(function(){
			if($(this).is(":checked")){
				$(this).parents(".sports_area").find(".choose_area_box").slideDown(300);
			}else{
				$(this).parents(".sports_area").find(".choose_area_box").slideUp(300);
			}
		})
	},
	funLoad:function(){
		BankingFed.leftNavList();
		BankingFed.tableTrRemove();	
		BankingFed.hoverBox();
		BankingFed.loginFocus(".user_input");
		BankingFed.selectFun({selectContainer:'.area_manage',selectTxt:'.area_txt',selectIcon:'.pro_manage_icon',selectCon:'.area_con '});
		BankingFed.showHideClick();
		BankingFed.changeTab(".yhdbk-tab-01");
		BankingFed.checkedFun();
	}
}
$(function(){
	BankingFed.funLoad();	
})