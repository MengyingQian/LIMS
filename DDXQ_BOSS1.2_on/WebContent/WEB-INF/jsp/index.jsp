<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js"> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cN">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="keywords" content="媒体接入技术实验室">
	<meta name="description" content="媒体接入技术实验室">
	<meta name="author" content="媒体接入技术实验室">
	<title>媒体接入技术实验室</title>
	<meta name="mobile-agent" content="format=html5;url=http://www.5shiguang.net">
	
	<link rel="stylesheet" href="/view/pub/basic/css/animate.min.css">

	<link rel="stylesheet" href="http://at.alicdn.com/t/font_458427_artx4d8n54eljtt9.css">
	<link href="/view/pub/basic/css/shiguang.css" rel="stylesheet" />
	
	<link rel="stylesheet" type="text/css"  href="/view/pub/basic/css/evenFlow.css">
	<link rel="stylesheet" type="text/css"  href="/view/pub/basic/css/picture.css">	
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/jq22-demo.css">
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans'>
	<link rel="stylesheet" href="/view/pub/basic/css/style2.css">
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/reset.css" />
	<script src="/view/pub/basic/js/jquery.bootstrap.newsbox.min.js" type="text/javascript"></script>
	
	<script src="/view/pub/basic/js/jquery-3.2.1.min.js"></script>

	<script src="/view/pub/basic/js/bootstrap.min.js"></script>

</head>
<body class="home" data-spy="scroll" data-target=".navbar-collapse">

	<nav class="navbar navbar-default navbar-shiguang navbar-fixed-top" role="navigation" data-nav="#home">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" aria-expanded="false" data-target=".navbar-collapse">
					<span class="sr-only">导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/"><img src="/view/pub/basic/images/logo.png" alt="媒体接入技术实验室" title="媒体接入技术实验室" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-respond">
					<li class="active"><a href="#home">首页</a></li>
					<li><a href="#abouts">课题组成员</a></li>
					<li><a href="#services">科研方向</a></li>
					<li><a href="#solutions">科研成果</a></li>
					<li><a href="#clients">就业去向</a></li>
					<li><a href="#contacts">联系我们</a></li>
				</ul>
				<!-- <ul class="nav navbar-nav navbar-right">
					<li><a href="tel:400-6650-361" title="全国统一客服热线：400-6650-361"><span class="segmdl2 segmdl2-Phone"></span>  010-62283412</a></li>
				</ul> -->
			</div>
		</div>
	</nav>
	<section id="loading" class="section fullscreen" style="display:none;">
		<div class="progress-container">
			<div class="progress">
				<div class="progress-bar" role="progressbar">
					<span class="sr-only"></span>
				</div>
			</div>
		</div>
	</section>


	<section id="home" class="section fullscreen">
		<!-- <div class="container">
			<div class="row "> -->
				
				<div id="carousel-example-generic" class="  carousel carousel-fade slide  half  waves-container" data-ride="carousel"
				 data-interval="2700">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						<li data-target="#carousel-example-generic" data-slide-to="4"></li>
					</ol>
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item carousel-slider1 active">
							<div class="carousel-content"></div>
							<div class="carousel-caption">
								<h1 class="lingxin-font highlight wow bounceIn" data-wow-delay="0.2s">SUCCESS</h1>
								<h3 class="lingxin-font wow bounceInUp" data-wow-delay="0.5s">让我们为您的<span class="highlight">成功</span>指引道路!</h3>
								<p class="wow fadeIn" data-wow-delay="1s">Let Us Show You the Way to Success</p>
							</div>
						</div>
						<div class="item carousel-slider2">
							<div class="carousel-content"></div>
							<div class="carousel-caption">
								<h1 class="lingxin-font highlight wow bounceIn" data-wow-delay="0.2s">SPECIALTY</h1>
								<h3 class="lingxin-font wow bounceInUp" data-wow-delay="0.5s">为您的项目提供<span class="highlight">专业</span>技术支持!</h3>
								<p class="wow fadeIn" data-wow-delay="1s">Provide professional technical support for your project!</p>
							</div>
						</div>
						<div class="item carousel-slider3">
							<div class="carousel-content"></div>
							<div class="carousel-caption">
								<h1 class="lingxin-font highlight wow bounceIn" data-wow-delay="0.2s">SOLUTION</h1>
								<h3 class="lingxin-font wow bounceInUp" data-wow-delay="0.5s">我们知道如何<span class="highlight">解决</span>您的问题!</h3>
								<p class="wow fadeIn" data-wow-delay="1s">We know how to solve your problems!</p>
							</div>
						</div>
						<div class="item carousel-slider4">
							<div class="carousel-content"></div>
							<div class="carousel-caption">
								<h1 class="lingxin-font highlight wow bounceIn" data-wow-delay="0.2s">FLOURISH</h1>
								<h3 class="lingxin-font wow bounceInUp" data-wow-delay="0.5s">使您的企业永久<span class="highlight">蓬勃发展</span>!</h3>
								<p class="wow fadeIn" data-wow-delay="1s">Make your business flourish forever!</p>
							</div>
						</div>
						<div class="item carousel-slider5">
							<div class="carousel-content"></div>
							<div class="carousel-caption">
								<h1 class="lingxin-font highlight wow bounceIn" data-wow-delay="0.2s">LEARNING</h1>
								<h3 class="lingxin-font wow bounceInUp" data-wow-delay="0.5s">我们也在不断学习,<span class="highlight">与时俱进</span>!</h3>
								<p class="wow fadeIn" data-wow-delay="1s">We are constantly learning, advancing with the times!</p>
							</div>
						</div>
					</div>
					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			        </a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			        </a>
				</div>
			    <!-- </div> -->

		<div class="xinwen_panel">
			
				<div  style=" text-align: center; font-size:20px;margin : 50px 0px 0px 0px;" >最新资讯</div>
					
					
				<div onclick="javascript:document.xwzx.submit()" class=" xinwen_panel-body " data-example-id="contextual-panels">
					<div class="panel panel-primary evenflow_scale ">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
					<div class="panel panel-success evenflow_scale">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
					<div class="panel panel-info evenflow_scale">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
					<div class="panel panel-warning evenflow_scale">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
					<div class="panel panel-danger evenflow_scale">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
					<div class="panel panel-success evenflow_scale">
						<div class="panel-heading">
							<h3 class="panel-title">Panel title</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
					</div>
				
				<div class="panel panel-submit evenflow_scale">
						<div class="panel-heading">
								<h3 class="panel-title">了解更多</h3>
							</div>
				</div>
			</div>
		</div> 
			<!-- </div> --> 
		
		
		<a href="#abouts" class="scroll-down toscroll hidden-xs">
			<span class="down-button scroll-button"></span>
			<span class="down-button scroll-button"></span>
		</a>
	</section>
	<section id="abouts" class="section fullscreen">
		<div class="container">
			<div class="section-content">
				<div class="row">
					<div class="col-md-6 col-md-offset-0 col-sm-8 col-sm-offset-1 col-xs-12 col-xs-offset-0 text-center">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs wow bounceInRight" role="tablist">
							<li role="presentation" class="active"><a href="#about1" aria-controls="about1" role="tab" data-toggle="tab">简介</a></li>
							<li role="presentation"><a href="#about2" aria-controls="about2" role="tab" data-toggle="tab">优势</a></li>
							<li role="presentation"><a href="#about3" aria-controls="about3" role="tab" data-toggle="tab">成员</a></li>
							<li role="presentation"><a href="#about4" aria-controls="about3" role="tab" data-toggle="tab">项目</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content wow bounceInRight" data-wow-delay="0.2s">
							<div role="tabpanel" class="tab-pane fade in active" id="about1">
								<p>北京邮电大学是教育部直属、工业和信息化部共建、首批进行“211工程”建设的全国重点大学，是“985优势学科创新平台”项目重点建设高校，是一所以信息科技为特色、工学门类为主体、工管文理协调发展的多科性、研究型大学，是我国信息科技人才的重要培养基地。</p>
								<p> 自1955年建校以来，经过60多年的建设与发展，学校全日制教育已经形成了信息背景浓郁、专业特色鲜明、学科优势突出的办学格局。学校现设有信息与通信工程学院、电子工程学院、计算机学院、自动化学院、软件学院、数字媒体与设计艺术学院、现代邮政学院、网络空间安全学院、光电信息学院、理学院、经济管理学院、公共管理学院、人文学院、马克思主义学院、国际学院、网络教育学院、继续教育学院、民族教育学院、体育部等19个教学单位，以及网络技术、信息光子学与光通信、感知技术与产业3个研究院，可信网络通信2011协同创新中心，并设有研究生院。目前，学科专业已经涵盖理学、工学、文学、法学、经济学、管理学、教育学、哲学、艺术学等9个学科门类，涉及22个一级学科。学校现有西土城路校区、沙河校区、宏福校区和延庆世纪学院校区，在江苏无锡和广东深圳分别设有研究院。全日制本、硕、博学生及留学生近30000名，正式注册的非全日制学生近45000名。</p>
								<!-- <p>时光网络科技倡导"专业、务实、高效、创新"的企业精神，具有良好的内部机制。优良的工作环境以及良好的激励机制，吸引了一批年轻的、有学识的、具有实干精神的人才。高素质、高水平、高效率的人才是时光网络科技在当今激烈的市场中立于不败之地的保障。</p> -->
							</div>
							<div role="tabpanel" class="tab-pane fade" id="about2">
								<dl>
									<dt class="h4 highlight">教育部重点实验室</dt>
									<dd>公司在产品研发方面的高投入，众多项目的实际应用，让我们具备了相应的创造力和丰富的经验，这将成为解决疑问和难题的良好保证。可为您提供多方面完整、完美的策划和设计：如项目的规划立项、总体方案设计、方案评估论证。</dd>
									<dt class="h4 highlight">全国领先的XXX技术</dt>
									<dd>公司众多一流人才的深层磨合，对最新技术执拗的探讨精神，使我们能够保证为你提供专业的应用。严格遵守"及时、专业、真诚"的服务方针以保证最快的速度、最有效的方法为您提供集成化的技术服务，解除您的后顾之忧。</dd>
									<dt class="h4 highlight">行业领先的XXX研究</dt>
									<dd>与知名IT厂商和IT服务供应商建立长久战略伙伴合作关系，让您时刻把握前沿的技术脉搏和行业动态。时光网络科技真诚希望能够有机会参与您的企业信息化建设，解决您在信息建设过程中出现的各类的问题，为您信息化建设做出我们的贡献。</dd>
								</dl>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="about3">
								<dl>
									<dt class="h4 highlight">教授团队XX人</dt>
									<dd>以客户业务为中心，以客户实际业务需求为导向，从客户利益出发，为客户提供完善的信息化应用解决方案。</dd>
									<dt class="h4 highlight">博士生XX人</dt>
									<dd>不论职位高低，都将受到充分的尊重和平等的对待。给予同等的机会，鼓励与公司共同发展，制定与公司发展步调一致的个人年度事业发展计划。</dd>
									<dt class="h4 highlight">研究生XX人</dt>
									<dd>系统开发是一项智力型的事业，公司提供挑战性的事业发展空间，鼓励大家创造性地工作，充分发挥个人的最大价值，从而创造出最佳的工作效果。</dd>
									<dt class="h4 highlight">本科生XX人</dt>
									<dd>在工作中，公司强调相互尊重、相互理解和有效的沟通，从而在平等和谐的合作氛围中创造"1 + 1>2"的工作效应。</dd>
								</dl>
								<div class="text-left"><a href="/News" target="_blank" class="text-info">了解更多 &raquo;</a></div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="about4">
								<dl>
									<dt class="h4"><a href="/News/Details/c0be6a04-cf14-e611-9427-5cf3fce69a34" target="_blank" class="text-info undecoration">国家863课题</a></dt>
									<dd>关于技术人员知识体系的思考PS：网上已经有很多类似的文章，但我依然要再造一次“轮子”，因为多少有些不同的心得。人生总有目标和追求不管…</dd>
									<dt class="h4"><a href="/News/Details/66ee805a-3ffb-e511-9425-5ef3fcde9a37" target="_blank" class="text-info undecoration">国家科技重大专项</a></dt>
									<dd>服务器情况：服务器上IIS配置的是网站，ftp服务器采用的是Serv-U，用于文件上传。症状：总是看到Serv-U的图标有黄色感叹号出现，而且IIS中…</dd>
									<dt class="h4"><a href="/News/Details/8bb96166-79f5-e511-9424-5ef3fcde9a37" target="_blank" class="text-info undecoration">国家自然科学基金</a></dt>
									<dd>最近在做一些比较基础的部分，做到界面时想到一个问题：在ASP.NET MVC(Razor View Engine)中，如果我要让多个项目之间共享一个_Layout.csht…</dd>
									<dt class="h4"><a href="/News/Details/133035b6-17f4-e511-9424-5ef3fcde9a37" target="_blank" class="text-info undecoration">企业合作项目</a></dt>
									<dd>虽然我一直以来都很享受于读书，但我总是很难挤出时间来阅读。而当真的空闲了，我通常会去阅读小说、自传，或一些怪癖但有趣的东西。我的Ki…</dd>
								</dl>
								<div class="text-left"><a href="/News" target="_blank" class="text-info">了解更多 &raquo;</a></div>
							</div>
						</div>
					</div>
					<div onclick="javascript:document.chengyuan.submit()" class=" col-md-offset-5 content">
						<img class="pic pic1 " src="/view/pub/basic/images/1.jpg" />
						<img class="pic pic2 " src="/view/pub/basic/images/2.jpg" />
						<img class="pic pic3 " src="/view/pub/basic/images/19.jpg" />
						<img class="pic pic4 " src="/view/pub/basic/images/18.jpg" />
						<img class="pic pic5 " src="/view/pub/basic/images/29.jpg" />
						<img class="pic pic6 " src="/view/pub/basic/images/16.jpg" />
						<img class=" pic pic7" src="/view/pub/basic/images/28.jpg" />
						<img class="pic pic8 " src="/view/pub/basic/images/27.jpg" />
						<img class="pic pic9 " src="/view/pub/basic/images/26.jpg" />
						
					 </div>
				</div>
			</div>
		</div>
		<div class="robot"></div>
		<div class="phone"></div>
		<div class="zhantai"></div>
	</section>
	<section id="services" class="section fullscreen">
		<div class="container">
			<div class="section-content">
			<div class="zhezhao">
				<div  class="  section-title lingxin-font wow rubberBand ">我们<span class="highlight">主要</span>有以下<span class="highlight">研究方向</span></div>
			</div>	
				<div class="splitter"><img src="/view/pub/basic/images/shiguang_splitter.png" alt="splitter" /></div>
			
				
				<div class="row">
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class=" icon-mobilephone_fill iconfont  "></i>
							<h3 class=" deep" target="_blank">5G组网</h3>
							<p><a href="/Services/Details/SoftwareCustomization" class="undecoration" target="_blank">我们推出的软件定制、定制软件服务，以最快的速度，简单的流程为客户定制高质量、易操作的管理软件，协助客户解决管理难题，为客户提供更优质的服务。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-erweima1688 "></i>
							<h3 class="deep" target="_blank">数据分析及资源优化</a></h3>
							<p><a href="/Services/Details/Software" class="undecoration" target="_blank">公司代理的有财务软件、商超管理软件、酒店客房餐饮管理软件、进销存管理软件、网络办公(OA)管理软件等，满足不同行业对企业管理及发展的软件需求。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-narrow"></i>
							<h3 class=" deep" target="_blank">干扰协调</a></h3>
							<p><a href="/Services/Details/WeiXin" class="undecoration" target="_blank">15大服务项目，开通简单快捷；10分钟熟悉操作界面和功能位置，30分钟即可完成上线。国内领先的微信营销服务平台,为企业打造全方位的微营销服务。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-decoration_fill"></i>
							<h3 class=" deep" target="_blank">智能交通</a></h3>
							<p><a href="/Services/Details/BusinessPhone" class="undecoration" target="_blank">对外只需公布唯一的400号码，统一宣传，简单易记。适用于教育咨询、旅游、医药、网络IT、贸易、金融、商务服务、保险、餐饮等多种类型行业。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-erji-yunpingtai"></i>
							<h3 class=" deep" target="_blank">大数据平台搭建</a></h3>
							<p><a href="/Services/Details/Website" class="undecoration" target="_blank">专业团队，提供一对一的专属定位；好的营销网站，带来更多的咨询量和成交量；结合网络推广，访问量、成交量翻一番；并为您提供以下运营指导服务！</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-coordinates"></i>
							<h3 class=" deep" target="_blank">室内定位技术</a></h3>
							<p><a href="/Services/Details/Domain" class="undecoration" target="_blank">电子商务、网上销售、网络广告已成为商界关注的热点。要想在网上建立服务器发布信息，则必须首先申请域名，只有有了自己的域名才能让别人访问到自己。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-erji-shujuku"></i>
							<h3 class=" deep" target="_blank">超密集网络组网</a></h3>
							<p><a href="/Services/Details/Hosting" class="undecoration" target="_blank">主机是以：空间大小、流量大小、支持网站语言、数据库类型、操作系统及功能区分，我们提供经济型、全能型、ASP.Net专业型、PHP专业型等多种类型。</a></p>
						</div>
					</div>
					<div class="col-md-3 wow bounceIn" data-wow-delay="0.4s">
						<div class="services-item">
							<i class="iconfont icon-businesscard"></i>
							<h3 class=" deep" target="_blank">社交D2D技术</a></h3>
							<p><a href="/Services/Details/Webmail" class="undecoration" target="_blank">微信直接收发邮件，管理功能丰富多样，应对特殊办公情景，全面支持移动办公，与QQ、微信完美整合。为企业打造全方位的信息化服务。</a></p>
						</div>
					</div>
				</div>
			
			</div>
		</div>
	</section>
	<section id="solutions" class="section fullscreen">
		<div class="container">
			<div class="section-content">
			<div class="zhezhao">
				<div class="section-title lingxin-font wow rubberBand">实验室<span class="hidden-xs">取得</span><span class="highlight">研究成果</span><span class="highlight"></span>：</div>
			</div>
				<div class="splitter"><img src="/view/pub/basic/images/shiguang_splitter.png" alt="splitter" /></div>
			
				<div class="weizhi">
					<div class="row ">
						<div class="col-md-6" >
						
							<div class="jq22-container  ">
				
								<div onclick="javascript:document.chengguo.submit()" class="leaderboard">
									<h1>
										<svg class="ico-cup">
											<use xlink:href="#cup"></use>
										</svg>
										发表论文
									</h1>
									<ol id=list>
									<!-- 	<li>
											<mark>Jerry Wood</mark>
											<small>315</small>
										</li>
										<li>
											<mark>Brandon Barnes</mark>
											<small>301</small>
										</li>
										<li>
											<mark>Raymond Knight</mark>
											<small>292</small>
										</li>
										<li>
											<mark>Trevor McCormick</mark>
											<small>245</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
								         	<mark>查看更多</mark>
										
											<small>...</small>
									    
										</li> -->
									</ol>
								</div>
							</div>
						</div>
				
				
						<div class="col-md-6">
							<div class="jq22-container  ">
				
								<div class="leaderboard">
									<h1>
										<svg class="ico-cup">
											<use xlink:href="#cup"></use>
										</svg>
										获得奖项
									</h1>
									<ol  >
										<li>
											<mark>Jerry Wood</mark>
											<small>315</small>
										</li>
										<li>
											<mark>Brandon Barnes</mark>
											<small>301</small>
										</li>
										<li>
											<mark>Raymond Knight</mark>
											<small>292</small>
										</li>
										<li>
											<mark>Trevor McCormick</mark>
											<small>245</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
											<mark>Andrew Fox</mark>
											<small>203</small>
										</li>
										<li>
											<mark>查看更多</mark>
											<small>...</small>
										</li>
									</ol>
								</div>
							</div>
						</div>
					
					</div>
				</div>
				<svg style="display: none;">
									  <symbol id="cup" x="0px" y="0px"
										 width="25px" height="26px" viewBox="0 0 25 26" enable-background="new 0 0 25 26" xml:space="preserve">
				<path fill="#388bf8" d="M21.215,1.428c-0.744,0-1.438,0.213-2.024,0.579V0.865c0-0.478-0.394-0.865-0.88-0.865H6.69
										C6.204,0,5.81,0.387,5.81,0.865v1.142C5.224,1.641,4.53,1.428,3.785,1.428C1.698,1.428,0,3.097,0,5.148
										C0,7.2,1.698,8.869,3.785,8.869h1.453c0.315,0,0.572,0.252,0.572,0.562c0,0.311-0.257,0.563-0.572,0.563
										c-0.486,0-0.88,0.388-0.88,0.865c0,0.478,0.395,0.865,0.88,0.865c0.421,0,0.816-0.111,1.158-0.303
										c0.318,0.865,0.761,1.647,1.318,2.31c0.686,0.814,1.515,1.425,2.433,1.808c-0.04,0.487-0.154,1.349-0.481,2.191
										c-0.591,1.519-1.564,2.257-2.975,2.257H5.238c-0.486,0-0.88,0.388-0.88,0.865v4.283c0,0.478,0.395,0.865,0.88,0.865h14.525
										c0.485,0,0.88-0.388,0.88-0.865v-4.283c0-0.478-0.395-0.865-0.88-0.865h-1.452c-1.411,0-2.385-0.738-2.975-2.257
										c-0.328-0.843-0.441-1.704-0.482-2.191c0.918-0.383,1.748-0.993,2.434-1.808c0.557-0.663,1-1.445,1.318-2.31
										c0.342,0.192,0.736,0.303,1.157,0.303c0.486,0,0.88-0.387,0.88-0.865c0-0.478-0.394-0.865-0.88-0.865
										c-0.315,0-0.572-0.252-0.572-0.563c0-0.31,0.257-0.562,0.572-0.562h1.452C23.303,8.869,25,7.2,25,5.148
										C25,3.097,23.303,1.428,21.215,1.428z M5.238,7.138H3.785c-1.116,0-2.024-0.893-2.024-1.99c0-1.097,0.908-1.99,2.024-1.99
										c1.117,0,2.025,0.893,2.025,1.99v2.06C5.627,7.163,5.435,7.138,5.238,7.138z M18.883,21.717v2.553H6.118v-2.553H18.883
										L18.883,21.717z M13.673,18.301c0.248,0.65,0.566,1.214,0.947,1.686h-4.24c0.381-0.472,0.699-1.035,0.947-1.686
										c0.33-0.865,0.479-1.723,0.545-2.327c0.207,0.021,0.416,0.033,0.627,0.033c0.211,0,0.42-0.013,0.627-0.033
										C13.195,16.578,13.344,17.436,13.673,18.301z M12.5,14.276c-2.856,0-4.93-2.638-4.93-6.273V1.73h9.859v6.273
										C17.43,11.638,15.357,14.276,12.5,14.276z M21.215,7.138h-1.452c-0.197,0-0.39,0.024-0.572,0.07v-2.06
										c0-1.097,0.908-1.99,2.024-1.99c1.117,0,2.025,0.893,2.025,1.99C23.241,6.246,22.333,7.138,21.215,7.138z"/>
				</symbol>
				</svg>
				</div>
				</div>
				
				</div>
    <script src="/view/pub/basic/js/jquery-2.2.3.min.js"type="text/javascript"></script>
    <script src="/view/pub/basic/js/jquery.json.min.js"type="text/javascript"></script>
	<script type="text/javascript">
	       submitstatus('1');
	         function submitstatus(status){
           
			var retData={};
// 			var year = '2017';
			retData.status=status;
// 			alert(1);
			$.ajax({
					url : '/ddxq/system/poweruser/searchstatus',
					data : $.toJSON(retData),
					type : 'post',
					dataType : 'json',
					contentType : 'application/json',
					cache : false,
					async: false,
					success : function(data) {
						if(data.success==true){
 				//			alert("success!");	
							//sex =data.sex;
							//alert(sex);
							console.log(data);							
							papers=data.rows;
							for (var i=0; i<data.total; i++){
								status=papers[i];
							
								div=add(status);
								$("#list").append(div);
							}
 					//		alert("0");
						}else{
							alert(2);
						}
					
				
					},
					error : function() {
						alert("faild!");	
					}
				});
 		//	alert(3);
		
		}
         function add(paper){
        	title=paper.title;
        	
        	publish_year=paper.publish_year;
        	 retString="<li>"+
				"<mark>"+title+"</mark>"+
 				"<small>"+"."+"</small>"+
			"</li>"
        return retString;	 
         }
         </script>
         </section>
	<section id="clients" class="section fullscreen">
		<div class="container">
			<div class="section-content">
				<div class="zhezhao"> 
				<div class="section-title lingxin-font wow rubberBand">毕业生<span class="highlight">就业去向</span>！</div>
			    </div>
				<div class="splitter"><img src="/view/pub/basic/images/shiguang_splitter.png" alt="splitter" /></div>
				<div class="row">
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo01.jpg" class="client-img" alt="力帆汽车4s店" />
						<p class="highlight">中国移动</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo02.jpg" class="client-img" alt="西平凯达燃气有限公司" />
						<p class="highlight">华为</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo03.jpg" class="client-img" alt="郾城环保局" />
						<p class="highlight">中国电信</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo04.jpg" class="client-img" alt="中共漯河市委党校" />
						<p class="highlight">中国国家电网</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo05.jpg" class="client-img" alt="漯河市委统战部" />
						<p class="highlight">阿里巴巴</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo06.jpg" class="client-img" alt="永城市委统战部" />
						<p class="highlight">微软</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo07.jpg" class="client-img" alt="河南万顺科技有限公司" />
						<p class="highlight">UTS</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo08.jpg" class="client-img" alt="漯河市公安局" />
						<p class="highlight">中国电子科技</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo09.jpg" class="client-img" alt="河南简好节能技术有限公司" />
						<p class="highlight">上海贝尔</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo10.jpg" class="client-img" alt="恒涛拍卖" />
						<p class="highlight">中兴</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo11.jpg" class="client-img" alt="香港夏博装饰" />
						<p class="highlight">中国联通</p>
					</div>
					<div class="col-md-3 wow flipInY">
						<img src="/view/pub/basic/images/clients/logo12.jpg" class="client-img" alt="勐海县银海石斛开发有限公司" />
						<p class="highlight">招商银行</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="contacts" class="section fullscreen">
		<div class="container">
			<div class="section-content">
				<div class="zhezhao"> 
				<div class="section-title lingxin-font wow rubberBand">与我们<span class="highlight">保持联系</span></div>
				</div> 
				<div class="splitter"><img src="/view/pub/basic/images/shiguang_splitter.png" alt="splitter" /></div>
				<div class="row">
					<div class="col-sm-4 mb30 wow flipInX">
						<p class="hidden-xs"><span id="c" class="iconfont sm icon-attachment">&nbsp;</span> 媒体接入技术实验室</p>
						<p><span class="iconfont sm icon-coordinates">&nbsp;</span> <a href="http://j.map.baidu.com/eI237" target="_blank" title="地图上找到我们">北京邮电大学</a></p>
						<p><span class="iconfont sm icon-phone">&nbsp;</span> <a href="tel:0395-5565361">0395-5565361</a>，<a href="tel:400-6650-361">400-6650-361</a></p>
						<p><span class="iconfont sm icon-mail">&nbsp;</span> QQ：<a href="http://wpa.qq.com/msgrd?v=3&uin=9348526&site=qq&menu=yes" target="_blank">9348526</a>；Email：support </p>
						<p><span class="iconfont sm icon-homepage"></span> bupt.net</p>
						<a class="btn btn-lg btn-block btn-info mb15 iconfont sm icon-bianji" data-ajax="true" data-ajax-begin="modalLoading" data-ajax-failure="onFailure"
						 data-ajax-success="onSuccess" href="/Home/Comment"> 在线提交需求</a>
						<a href="/Jobs" class="btn btn-lg btn-block btn-danger mb15  iconfont sm icon-shengqian"> 查找工作机会</a>
					</div>
					<div class="col-sm-4 col-xs-6 wow flipInX text-center">
						<img src="/view/pub/basic/images/qrcode_for_weibo.png" class="img-responsive2 mb15" alt="漯河时光网络科技有限公司 官方微博" title="官方微博 @时光网络科技" />
						<p>官方微博 <a href="http://weibo.com/sgwlkj" class="hidden-xs" title="漯河时光网络科技有限公司 官方微博">@媒体接入技术实验室</a></p>
					</div>
					<div class="col-sm-4 col-xs-6 wow flipInX text-center">
						<img src="/view/pub/basic/images/qrcode_for_weixin.jpg" class="img-responsive2 mb15" alt="漯河时光网络科技有限公司 官方微信" title="漯河时光网络科技有限公司 官方微信"
						/>
						<p>官方微信</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Modal -->
	<div class="modal fade" id="defaultModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">标题</h4>
				</div>
				<div class="modal-body">无内容</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
	<script src="/view/pub/basic/js/jqueryval.js"></script>



	<script src="/view/pub/basic/js/waves.min.js"></script>
	<script src="/view/pub/basic/js/index.js"></script>

	<script type="text/javascript" src="./WebResource.axd"></script>
	<script>
		//push
		(function () {
			var s = document.getElementsByTagName("script")[0];
			var bp = document.createElement('script');
			bp.src = '//push.zhanzhang.baidu.com/push.js';
			s.parentNode.insertBefore(bp, s);
		})();
	</script>
	<script>
		var _hmt = _hmt || [];
		(function () {
			var hm = document.createElement("script");
			hm.src = "./hm.js";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<script type="text/javascript" src="Scripts/wow.min.js"></script>
	<script type="text/javascript">
		new WOW().init();
	</script>
	   <form action="/ddxq/system/poweruser/index" method="post" class="top-nav-link w-nav-link" name="index"></form>
       <form action="/ddxq/system/poweruser/chengguo" method="post" class="top-nav-link w-nav-link" name="chengguo"></form>
       <form action="/ddxq/system/poweruser/chengyuan" method="post" class="top-nav-link w-nav-link" name="chengyuan"></form>
       <form action="/ddxq/system/poweruser/yjfx" method="post" class="top-nav-link w-nav-link" name="yjfx"></form>
       <form action="/ddxq/system/poweruser/xwzx" method="post" class="top-nav-link w-nav-link" name="xwzx"></form>
       <form action="/ddxq/system/poweruser/huojiang" method="post" class="top-nav-link w-nav-link" name="index"></form>
</body>

</html>