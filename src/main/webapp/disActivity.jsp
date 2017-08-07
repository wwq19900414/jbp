<%@ page contentType="text/html;charset=UTF-8" %>
<%String ctx = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=ctx%>/js/jquery-1.4.2.min.js"></script>

<title>专属活动</title>
<style type='text/css'>
.dis_tit {
	width:134px;
	height:20px;
	overflow:hidden;
	margin:16px auto 23px;
	background:url(http://image.yihaodianimg.com/front-homepage/index/images/index_sprite_new.png?1750614) no-repeat 0 -160px;
	font-size:1
}
.mod_dis_wrap .dis_wrap {
	position:relative;
	width:1040px;
	height:190px;
	overflow:hidden;
	margin:0 auto
}
.dis_wrap ul {
	position:absolute;
	left:0;
	top:0;
	width:9999px
}
.dis_wrap li {
	width:132px;
	float:left;
	margin:0 21px;
	list-style:none;
	_display:inline
}
.dis_wrap .dis_item {
	position:relative;
	display:block;
	padding-top:132px;
	text-align:center
}
.dis_wrap .dis_img {
	position:absolute;
	top:0;
	left:0;
	z-index:105;
	width:130px;
	height:100px;
	padding:15px 0;
	overflow:hidden;
	border:1px solid #e4e1e1;
	border:0\9;
	background-color:#fff;
	-moz-border-radius:80px;
	-webkit-border-radius:80px;
	border-radius:80px;
	-moz-transition:all .3s linear;
	-webkit-transition:all .3s linear;
	-o-transition:all .3s linear;
	transition:all .3s linear
}
.dis_wrap a.dis_item img {
	width:100px;
	height:100px;
	-moz-transition:all .3s linear;
	-webkit-transition:all .3s linear;
	-o-transition:all .3s linear;
	transition:all .3s linear
}
.dis_wrap a.dis_item:hover .dis_img {
	height:110px;
	padding:10px 0
}
.dis_wrap a.dis_item:hover img {
	width:110px;
	height:110px
}
.dis_wrap li span {
	display:none;
	display:block\9;
	position:absolute;
	top:0;
	left:0;
	z-index:108;
	width:132px;
	height:132px;
	background:url(../images/dis_circle.png?1750614) no-repeat;
	cursor:pointer
}
.dis_wrap .dis_name {
	margin-top:10px;
	font-size:16px;
	color:#333;
	line-height:20px;
	font: 12px Arial,Helvetica,sans-serif;
}
.dis_wrap .dis_sale {
	margin-top:6px;
	color:#fb6c68;
	font-size:16px;
	line-height:20px;
	letter-spacing:2px
}
.mod_dis_wrap a.prev_btn{
	position:absolute;
	margin-top:0px;
	margin-left:20px;
	width:18px;
	height:30px;
	padding:66px 12px
}
.mod_dis_wrap a.prev_btn em{
	display:block;
	width:18px;
	height:30px;
	background:url(http://image.yihaodianimg.com/front-homepage/index/images/index_sprite_new.png?1750614) no-repeat 0 -120px
}
.mod_dis_wrap a.prev_btn:hover em{
	background-position:-75px -120px
}
.mod_dis_wrap a.next_btn{
	position:absolute;
	margin-top:0px;
	margin-left:1180px;
	width:18px;
	height:30px;
	padding:66px 12px
}
.mod_dis_wrap a.next_btn em{
	display:block;
	width:18px;
	height:30px;
	background:url(http://image.yihaodianimg.com/front-homepage/index/images/index_sprite_new.png?1750614) no-repeat -30px -120px
}
.mod_dis_wrap a.next_btn:hover em{
	background-position:-105px -120px
}
.w1200 .mod_dis_wrap .dis_wrap {
	width:1044px
}
</style>
</head>
<body>


<div class="wrap mod_dis_wrap" style="" id="selectActivityWrapOld" data-abtest="front-homepage-DIS-ABTest" data-tpa="INDEX2_SELECTION_ACTIVITY">
	<div class="dis_tit"></div>
	<a class="prev_btn" href="javascript:pre();"><em></em></a>
	<a class="next_btn" href="javascript:next();"><em></em></a>
	<div class="dis_wrap">
	<ul class="clearfix" style="left:-40px;transition: all 1s ease;" id="actList"></ul>
	</div>
</div>

<script type="text/javascript">
var current = 0;
var count = 20;
var step = 6;
var stepWidth = 174;
var left = -40;

var a = Math.floor((count+step-1)/step);
var leftArr = [];

for(var i = 0; i<a;i++){
	if(count > step){
		leftArr.push(left-i*step*stepWidth);
	}
}
	



function next(){
	if(current+1 == leftArr.length){
		current = 0;
	}else{
		current ++;
	}

	document.getElementById("actList").style.left=leftArr[current]+"px";
}

function pre(){
	if(current == 0){
		current = leftArr.length - 1;
	}else{
		current --;
	}
	
	document.getElementById("actList").style.left=leftArr[current]+"px";
}

var actList=[];

function initActList(){
	for(var i = 0;i<count;i++){
		var obj = {};
		obj.landingPage = "http://list.yhd.com/p/c0-b-a-s1-v0-p1-price-d0-pid12519122-pt1757555-pl1957647-m0";
		obj.imgUrl = "http://d9.yihaodianimg.com/N08/M00/5F/13/ChEi1FZc_FyAZyb_AAYhqFsEgqc67201_140x140.jpg";
		obj.productName = "传统滋补营养品"+(i+1);
		actList.push(obj);
	}
}

function initUl(){
	var html = "";
	for(var i = 0;i<actList.length;i++ ){
		var act = actList[i];
		html += "<li index='"+(i+1)+"' id='li"+(i+1)+"'>";
		html += "  <a href='"+act.landingPage+"' class='dis_item' target='_blank' data-tc='ad.6.0.18660-42551830.1' data-tce='bhstr-null'>";
		html += "    <div class='dis_img'><img src='"+act.imgUrl+"' alt='传统滋补营养品' width='140' height='140'>";
		html += "    </div>";
		html += "    <p class='dis_name'>"+act.productName+"</p>";
		html += "  </a>";
		html += "</li>";
	}
	
	$("#actList").html("");
	$("#actList").html(html);
		
}

function init(){
	initActList();
	initUl();
}

init();
</script>
<body>
</html>
