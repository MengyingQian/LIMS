<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cN">
	<head>
 <meta charset="utf-8"/>
        <title>北邮泛网无线通信实验室</title>
        <meta content="The Center for Complex Network Research (CCNR), directed by Professor Albert-László Barabási, has a simple objective: think networks. The center&#x27;s research focuses on how networks emerge, what they look like, and how they evolve; and how networks impact on understanding of complex systems." name="description"/>
        <meta content="Publications" property="og:title"/>
        <meta content="The Center for Complex Network Research (CCNR), directed by Professor Albert-László Barabási, has a simple objective: think networks. The center&#x27;s research focuses on how networks emerge, what they look like, and how they evolve; and how networks impact on understanding of complex systems." property="og:description"/>
        <meta content="summary" name="twitter:card"/>
        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <link href="/view/pub/basic/css/css.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../Content/animate.min.css">
       
<!--         <script type="text/javascript">
            WebFont.load({  google: {    families: ["Montserrat:100,100italic,200,200italic,300,300italic,400,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic","Lato:100,100italic,300,300italic,400,400italic,700,700italic,900,900italic","Heebo:100,300,regular,800","Playfair Display:regular,italic,700,700italic,900,900italic","Raleway:200,200italic,300,300italic,regular,italic,700,700italic"]  }});
        </script>
       
        <script type="text/javascript">
            !function(o,c){var n=c.documentElement,t=" w-mod-";n.className+=t+"js",("ontouchstart"in o||o.DocumentTouch&&c instanceof DocumentTouch)&&(n.className+=t+"touch")}(window,document);
        </script> -->
        <link href="/view/pub/basic/css/shiguang.css" rel="stylesheet"/>
       
       <script src="/view/pub/basic/js/jquery-2.2.3.min.js"type="text/javascript"></script>
		<script src="/view/pub/basic/js/jquery.json.min.js"type="text/javascript"></script>
		<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
        <script src="/view/pub/basic/js/bootstrap.min.js"></script>
      
        <style>
            .w-container {
                max-width: 1100px;
            }
        </style>
        
      
     

	
</head>
 <body data-ix="fade-in-on-load" class="body">
        <div data-collapse="medium" data-animation="default" data-duration="400" data-doc-height="1" class="navbar w-nav">
            <div class="content-wrapper w-container">
                <a href="/" class="brand w-nav-brand">
                    <div class="lab-name">泛网无线通信实验室</div>
                </a>
                <nav role="navigation" class="w-nav-menu">
                   
                    <form action="/ddxq/system/poweruser/index" method="post" class="top-nav-link w-nav-link" name="index"></form>
                    <form action="/ddxq/system/poweruser/chengguo" method="post" class="top-nav-link w-nav-link" name="chengguo"></form>
                    <form action="/ddxq/system/poweruser/chengyuan" method="post" class="top-nav-link w-nav-link" name="chengyuan"></form>
                    <form action="/ddxq/system/poweruser/yjfx" method="post" class="top-nav-link w-nav-link" name="yjfx"></form>
                    <form action="/ddxq/system/poweruser/xwzx" method="post" class="top-nav-link w-nav-link" name="xwzx"></form>
                    <form action="/ddxq/system/poweruser/huojiang" method="post" class="top-nav-link w-nav-link" name="huojiang"></form>
                    <a href="javascript:document.index.submit()" role="button" class="top-nav-link w-nav-link">首页</a>
                    <a href="javascript:document.chengyuan.submit()" role="button" class="top-nav-link w-nav-link">课题组成员</a>     
                    <a href="javascript:document.yjfx.submit()" role="button" class="top-nav-link w-nav-link">研究方向</a>
                    <a href="javascript:document.xwzx.submit()" role="button" class="top-nav-link w-nav-link">新闻资讯</a>        
                    <a href="javascript:document.chengguo.submit()" role="button" class="top-nav-link w-nav-link">论文发表</a>
                    <a href="javascript:document.huojiang.submit()" role="button" class="top-nav-link w-nav-link">获奖情况</a>
                </nav>
                <div class="menu-button-2 w-nav-button">
                    <div class="w-icon-nav-menu"></div>
                </div>
            </div>
        </div>
    
        
  <section class="banner">
            <img  src="/view/pub/basic/images/banner-services.jpg" alt="weixin" class="banner-img" />
            <div class="banner-content">
                <div class="container">
                    <h1 class="wow  bounceInRight">实验室<span class="highlight">成员</span>一览</h1>
                    <p class="lead wow bounceInRight" style="color: #fff" >Leading micro-channel marketing services platform to create a full range of marketing services!</p>
                </div>
            </div>
        </section>



        
        <div data-ix="move-up-on-scroll-3" class="content-section inner-pages">
            <div data-ix="move-down-on-load" class="content-wrapper w-container">
                <div class="w-dyn-list">
                    <div class="flex-wrapper w-dyn-items">
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/laszlo-barabasi" data-ix="full-opacity-on-hover" style="background-image:url(/view/pub/basic/images/1.jpg);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a> 
                            <div>
                                <a href="/people/laszlo-barabasi" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Albert-László Barabási</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Director, Robert Gray Dodge Professor of Network Science, Distinguished University Professor</div>
                        </div>
                    </div>
                </div>
                <div class="title-box">
                    <div class="title">Researchers</div>
                </div>
                <div class="w-dyn-list">
                    <div class="flex-wrapper w-dyn-items">
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/feixiong-cheng" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b7491f274e91bc1038b0a_FCheng-2.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/feixiong-cheng" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Feixiong Cheng</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/sean-p-cornelius" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dab71117b41a773aa687a6_Sean%20Cornelius.png&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/sean-p-cornelius" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Sean P. Cornelius</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/nima-dehmami" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b93abe9deef392444d605_NDehmami.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/nima-dehmami" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Nima Dehmami</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/italo-do-valle" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/598b4504e77b2d0001c3c6ee_idovalle_small.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/italo-do-valle" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Italo Do Valle</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">POSTDOCTORAL RESEARCH ASSOCIATE</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/alexander-gates" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/598b4475e1f54f0001eb086e_agates_small.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/alexander-gates" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Alexander Gates</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/junming-huang" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dacc2422aca8523aaa4c3a_junming.png&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/junming-huang" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Junming Huang</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                       
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/marc-santolini" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b744ce9deef392444c4e7_MSantolini.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/marc-santolini" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Marc Santolini</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/emma-towlson" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b8af0d2de1d32ccaef970_ETowslon.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/emma-towlson" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Emma Towlson</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Postdoctoral Research Associate</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/onur-varol" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/598b4496135a20000131b5a8_ovarol_small.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/onur-varol" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Onur Varol</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">POSTDOCTORAL RESEARCH ASSOCIATE</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/jianxi-gao" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dabe25bfcc83dd319f586c_Jianxi%20Gao.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/jianxi-gao" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Jianxi Gao</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Associate Research Scientist</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/burcu-yucesoy" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dabdfe36d942c1313890a4_BurcuYucesoy.jpeg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/burcu-yucesoy" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Burcu Yucesoy</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Associate Research Scientist</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/jie-lie-8" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58de9ac94b2ea7f4248262da_LabPic.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/jie-lie-8" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Jie Li</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Scientist</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/rasoul-r-rajaei" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b973c68091f770a66fbbe_RRajaei-2.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/rasoul-r-rajaei" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Rasoul R. Rajaei</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Scientist</div>
                        </div>
                    </div>
                </div>
                <div class="title-box">
                    <div class="people-heading title">Visiting Scientists &amp;Professors</div>
                </div>
                <div class="w-dyn-list">
                    <div class="flex-wrapper w-dyn-items">
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/jose-brum" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590b79cc8f8cc35efcab744a_JBrum2.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/jose-brum" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Jose Brum</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Research Professor</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/amar-dhand" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/590c6781318a895df0af200a_photo.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/amar-dhand" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Amar Dhand</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Assistant Professor</div>
                        </div>
                       
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/andrew-mccallum" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dac76136d942c131389186_mccallum.png&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/andrew-mccallum" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Andrew McCallum</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Research Associate Professor</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/hiroki-sayama" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dd39f715cbf1d31654cf47_sayama.png&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/hiroki-sayama" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Hiroki Sayama</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Visiting Research Associate Professor</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/amitabh-sharma" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dac2f836d942c131389102_Amitabh%20Sharma.jpeg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/amitabh-sharma" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Amitabh Sharma</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Affiliated Research Assistant Professor</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/roberta-sinatra" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dabdc522aca8523aaa4b01_SinatraR2.JPG&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/roberta-sinatra" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Roberta Sinatra</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Affiliated Research Assistant Professor</div>
                        </div>
                      
                    </div>
                </div>
                <div class="title-box">
                    <div class="people-heading title">Staff</div>
                </div>
                <div class="w-dyn-list">
                    <div class="flex-wrapper w-dyn-items">
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/alice-grishchenko" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/598b4583e77b2d0001c3c6f5_agrishchenko_small.jpg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/alice-grishchenko" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">Alice Grishchenko</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Data Visualization Specialist</div>
                        </div>
                        <div class="_33-percent-column team-member w-clearfix w-dyn-item">
                            <a href="/people/james-stanfill" data-ix="full-opacity-on-hover" style="background-image:url(&quot;https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26ff3/58dac32d36d942c131389104_James%20Stanfill.jpeg&quot;);" class="team-member-picture w-inline-block">
                                <div data-ix="-overlay-hover-" class="hover-overlay">
                                    <div data-ix="display-none-on-load" class="hover-icon"></div>
                                </div>
                            </a>
                            <div>
                                <a href="/people/james-stanfill" class="link-block w-clearfix w-inline-block">
                                    <h4 class="team-member-name">James Stanfill</h4>
                                </a>
                            </div>
                            <div class="center underline"></div>
                            <div class="job-title">Communications Specialist</div>
                        </div>
                    </div>
                </div>
                <div class="title-box">
                    <div class="people-heading title">Students</div>
                </div>
                <div  class="w-dyn-list">
                  <div id=list class="flex-wrapper w-dyn-items">
                  </div>
                </div>
                
        <script src="/view/pub/basic/js/jquery-2.2.3.min.js"type="text/javascript"></script>
		<script src="/view/pub/basic/js/jquery.json.min.js"type="text/javascript"></script>
		<script type="text/javascript">
 		submitpeople('Student');
		
         function submitpeople(position){
         
			var retData={};
// 			var year = '2017';
			retData.position=position;
 			
			$.ajax({
					url : '/ddxq/system/poweruser/searchposition',
					data : $.toJSON(retData),
					type : 'post',
					dataType : 'json',
					contentType : 'application/json',
					cache : false,
					async: false,
					success : function(data) {
						if(data.success==true){
//  							alert("success!");	
							//sex =data.sex;
							//alert(sex);
							console.log(data);							
							papers=data.rows;
							for (var i=0; i<data.total; i++){
								paper=papers[i];
							
								div=add(paper);
								$("#list").append(div);
							}
// 							alert("0");
						}else{
							alert(2);
						}
					
				
					},
					error : function() {
						alert("faild!");	
					}
				});
 		
		
		}
         function add(paper){
        	name=paper.name;
        	direction=paper.direction;
        	pic_url=paper.pic_url;
        	
        	 retString= " <div class=\"_33-percent-column team-member w-clearfix w-dyn-item\">"+
                            "<div  data-ix=\"full-opacity-on-hover\" style=\"background-image:url(/view/pub/basic/images/"+pic_url+");background-size:100%100%;\" class=\"team-member-picture w-inline-block\">"+
                              /*   "<div data-ix=\"-overlay-hover-\" class=\"hover-overlay\">"+
                                    "<div data-ix=\"display-none-on-load\" class=\"hover-icon\">"+"</div>"+
                               " </div>"+ */
                           "</div>"+
                            "<div>"+
                                "<div  class=\"link-block w-clearfix w-inline-block\">"+
                                    "<h4 class=\"team-member-name\">"+name+"</h4>"+
                                "</div>"+
                            "</div>"+
                            "<div class=\"center underline\"></div>"+
                            "<div class=\"job-title\">"+direction+"</div>"+
                         "</div>"
        return retString;	 
         }
         </script>
     </body>
</html>