<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<%String ctx = request.getContextPath(); %>
<title>1号店用户行为数据API说明文档</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head>
<body>

<center><h1>1号店用户行为数据接口说明</h1></center>

<a name="api">目录</a>
<ul>
<li>一、<a href="#api1">概述</a></li>
<li>二、<a href="#api2">API总体信息接口</a></li>
<li>三、<a href="#api3">浏览页面数据接口</a></li>
<li>四、<a href="#api4">购物车数据接口</a></li>
<li>五、<a href="#api5">订单数据接口</a></li>
<li>六、<a href="#api6">订单商品数据接口</a></li>
<li>七、<a href="#api7">Track日志数据接口</a></li>
<li>八、<a href="#api8">抵用卷数据接口</a></li>
</ul>



<h2>一、<a name="api1">	概述</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
A、【业务流程】
1、【品牌商】确认和【一号店】合作后,由一号店生成并分配[tracker_u]给到【品牌商】。
2、【品牌商】在自己投放的网络广告的landingPage(指向一号店站内的落地页url)中加入参数【tracker_u=[tracker_u]】。
3、用户点击【品牌商】在网络上投放的广告,进入一号店后,会发生一系列的行为,如浏览页面、加入购物车、下单、使用抵用卷、页面间跳转等,从而在一号店站内产生了用户站内行为数据
3、一号店有专门的数据记录和收集系统,只要用户访问一号店网站的所用url带有【tracker_u=[tracker_u]】参数,就能将该用户行为数据收集并用特定的[tracker_u]值标识
4、一号店会在本API系统开通一个API账号和密码,并将分配的[tracker_u]关联到这个API账号。
5、【品牌商】使用API账号和密码,按照如下的调用说明调用API,API就会以XML的格式将  以【该API账号下所有tracker_u】 标识的用户行为数据返回。


B、【接口通用部分说明】
1、接口采用最普通的http请求来实现，返回标准的xml格式的数据，下面将逐一对接口的功能及调用方式予以说明，并附有xml的样例。
2、1号店会提供API调用的所需的
key暂定为：yhdadm,MD5加密后为06b8fd4570c747741b7dfee434e9dd(请直接使用此加密后的key)
【Appid】(确定合作时会提供到的,也即API账户名)
【SecurityKey】(确定合作时会提供到的,也即API密码)
通过接口返回的XML中都有一个&lt;response-status&gt;的通用元素，用于记录返回状态，此元素的状态具体说明如下：

<b>【不正常返回】</b>时的状态码：
【101】代表请求的参数组成不符合约定，个数不对
【102】代表参数虽然符合约定，但是key值不匹配。
【103】代表虽然参数个数是对的，但是传入的appId在站外系统不存在。
【104】代表虽然参数个数是对的，appId在站外系统也是存在的，但是传入的SecurityKey和站外系统中此appId对应的SecurityKey不匹配。
【105】代表url过期,系统会将传入的时间戳和当前时间比较，若是其间的间隔大于某个时间段（目前暂定为10分钟），则不处理该请求。
【106】代表传入的参数中的日期格式不符合yyyy-mm-dd的约定。
【111】代表指定日期内没有相对应的数据或是指定日期内的所有数据都不对外开放
【112】代表指定日期了的数据总共只有n页,但是你请求的pageFlag（0为请求第一页的数据）大于n-1了

<b>【正常返回】</b>时的状态码：
【200】此状态代表正常返回，而且所有要求的数据都已经返回了。
【201】此状态代表数据过多，一次无法全部返回，而本次返回的数据只是其中的一部分，需要（按规定修改分页标记和时间戳后）再发送请求来获取剩余的数据。
</pre>

<h2>二、<a name="api2">	API总体信息接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
此接口用于获取整个API的信息
A、整个【用户行为数据API】分发数据的主要单元是tracker_u,只有将tracker_u绑定在某个api账号下,以这个tracker_u标识的行为数据才能够通过该api账号返回。
B、另外,针对每一个tracker_u进行了更细粒度的权限控制,是通过增加了一个【有效时间段】（即结束时间-开始时间）来实现的。
C、调用整个【用户行为数据API】（不包含本接口)的当时的时间不在某个指定tracker_U有效时间段内,则该tracker_U的数据即使有也不会通过API返回了
D、如果需要指定某个tracker_U的有效时间段,使得其数据能够通过api返回,请查看本接口的返回结果,对【已过期】的tracker_u,联系一号店相关人员进行续约。

本接口就是返回的某个api账号下目前所拥有的tracker_U及其有效时间段、状态的信息
2、调用说明
此接口需要用户传入的参数由两个部分拼接成字符串组成：
●	Appid：由一号店提供,也就是api账号
●	SecurityKey：由一号店提供,即api密码


请求的url形式为：
http://adm.yihaodian.com/adoutside/service/analysis/getApiTrackerU.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将Appid、SecurityKey，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/api.xml">示例api.xml</a>



<h2>三、<a name="api3">	浏览页面数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
A、此接口用于获取指定日期内通过指定的tracker_u投放的广告带来用户浏览的一号店站内网页的相关数据，对应于同一tracker_u且同一click_id只会有一条数据,记录PV数和UV数
B、在指定日期内会有多个trackeru和clickId的组合，因而会有多条数据返回，会采用分页标记，一次最多只能返回5000条tracku与clickId组合对应的页面相关数据。
C、返回xml文件如附件pageViewInfo.xml所示，也就是一次最多有5000个&lt;pageview-info&gt;元素

2、调用说明
此接口需要用户传入的参数由六部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	pageFlag：第一次发送此参数为0，若返回201状态，则加1作为此参数值再发送请求，若还是201，则再加1后再发送请求，直到返回200状态为止
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数），

请求的url形式为：
http://adm.yihaodian.com/adoutside/service/analysis/getPageViewInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey，
B、然后以date、Appid、SecurityKey、pageFlag、encodeKey、time的顺序，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/pageViewInfo.xml">示例pageview.xml</a>



<h2>四、<a name="api4">	加入购物车数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
A、此接口用于获取指定日期内通过指定的tracker_u投放的广告带来用户加入购物车的商品信息，对于同一tracku和同一clckid就会对应多条购物车商品信息。
B、数据可能会多于5000条,因此会采用分页标记，一次请求最多只能返回5000条各购物车商品信息,多出来的在下一页,以此类推。
C、返回xml文件如附件cartInfo.xml所示，也就是一次请求返回的xml中最多有5000个&lt;cart-product&gt;元素

2、调用说明
此接口需要用户传入的参数由六部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	pageFlag：第一次发送此参数为0，若返回201状态，则加1作为此参数值再发送请求，若还是201，则再加1后再发送请求，直到返回200状态为止
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数）

请求的url形式为：
http://adm.yihaodian.com/adoutside/service/analysis/getCartInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey，
B、然后以Date、Appid、SecurityKey 、encodeKey、pageFlag、time的顺序，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/cartInfo.xml">示例cart.xml</a>


<h2>五、<a name="api5">	订单数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
A、此接口用于获取指定日期内通过指定的tracker_u投放的广告带来用户所下订单的信息。
B、返回的数据有可能比较多，因而会采用分页的标记，一次最多只会取5000个订单相关信息。
C、返回的xml文件如附件orderInfo.xml所示，也就是一次最多有5000个&lt;order-info&gt;元素

2、调用说明
此接口需要用户传入的参数由六部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	pageFlag：第一次发送此参数为0，若返回201状态，则加1作为此参数值再发送请求，若还是201，则再加1后再发送请求，直到返回200状态为止
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数）

请求的url形式为：

http://adm.yihaodian.com/adoutside/service/analysis/getOrderInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey，
B、然后以Date、Appid、SecurityKey、pageFlag、encodeKey、time的顺序，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/orderInfo.xml">示例order.xml</a>


<h2>六、<a name="api6">	订单商品数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
A、此接口用于获取指定日期内通过指定的tracker_u投放的广告带来用户所下订单中商品的详细信息。
B、返回的数据有可能比较多，因而会采用分页的标记,一次请求一页,一页最多只会取5000个订单商品相关的信息,多出来的需要在下一页,需要更改分页标记再次获取。
C、返回的xml文件如示例orderProduct.xml所示，也就是一次请求返回的xml中最多有5000个&lt;order-product&gt;元素

2、调用说明
此接口需要用户传入的参数由六部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	pageFlag：第一次发送此参数为0，若返回201状态，则加1作为此参数值再发送请求，若还是201，则再加1后再发送请求，直到返回200状态为止
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数）

请求的url形式为：

http://adm.yihaodian.com/adoutside/service/analysis/getOrderProductInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey，
B、然后以Date、Appid、SecurityKey、pageFlag、encodeKey、time的顺序，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/orderProduct.xml">示例orderProduct.xml</a>


<h2>七、<a name="api7">	Track数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
此接口用于获取指定日期内一号店记录的指定渠道商投放的tracku的跟踪信息，由于一天的track数据相当的多，因而会将一天的数据分放在多个xml文件中，然后将这些xml文件打包压缩为一个压缩文件，因而请求的url中不再需要分页标识。通过本接口访问会返回一个比较简短的xml文件（如附件中的trackInfo-response.xml所示），里面会给出通用状态码、数据记录的日期、以及track数据包的下载url。用户获得url后需要再用此url来下载数据包，数据压缩包中的xml格式如附件中的trackInfo.xml所示 。

2、调用说明
此接口需要用户传入的参数由五部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数）

请求的url形式为：

http://adm.yihaodian.com/mrc/adoutside/analysis/getTrackInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey(文档开头已经提供)
B、然后以Date、Appid、SecurityKey、encodeKey、time的顺序，以‘_’分隔拼接成字符串paramStr
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/trackInfo-response.xml">示例trackInfo-response.xml</a>
<a target="_blank" href="<%=ctx %>/xml/trackInfo.xml">示例trackInfo.xml</a>






<h2>八、<a name="api8">	抵用卷数据接口</a> &nbsp;&nbsp;&nbsp;<a href="#api">↑top</a></h2>
<pre>
1、概述
此接口用于获取指定日期内一号店记录的通过指定的tracker_u投放的广告带来的用户在订单中使用了的抵用卷信息,抵用卷数据会分页。

2、调用说明
此接口需要用户传入的参数由六部分拼接成字符串组成：
●	Date：yyyy-mm-dd格式的日期，表示请求数据的日期
●	Appid：由一号店提供
●	SecurityKey：由一号店提供
●	pageFlag：第一次发送此参数为0，若返回201状态，则加1作为此参数值再发送请求，若还是201，则再加1后再发送请求，直到返回200状态为止
●	key：一个为接口安全考虑由站外系统定义的key值
●	time：时间戳（long类型的毫秒数）

请求的url形式为：

http://adm.yihaodian.com/adoutside/service/analysis/getCouponInfo.action?params=[encodeParams]

其中[encodeParams]是按如下步骤所得：
A、先将key以MD5加密成encodeKey
B、然后以Date、Appid、SecurityKey、pageFlag、encodeKey、time的顺序，以‘_’分隔拼接成字符串paramStr，
C、得到的paramStr即作为[encodeParams]
</pre>
<a target="_blank" href="<%=ctx %>/xml/coupon.xml">示例coupon.xml</a>
</body>
</html>


