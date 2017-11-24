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
        <link href="/view/pub/basic/css/xinwen.css" rel="stylesheet" type="text/css"/>
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
                    <form action="/ddxq/system/poweruser/huojiang" method="post" class="top-nav-link w-nav-link" name="index"></form>
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
                    <h1 class="wow  bounceInRight">实验室<span class="highlight">近期</span>热点</h1>
                    <p class="lead wow bounceInRight" style="color: #fff" >Leading micro-channel marketing services platform to create a full range of marketing services!</p>
                </div>
            </div>
        </section>

        <div data-ix="move-up-on-scroll-3" class="content-section inner-pages">
            <div data-ix="move-down-on-load" class="content-wrapper w-container">
                <div class="title-box">
                    <div class="title">Job openings in the Barabási Lab</div>
                </div>
                <div>
                    <div class="w-dyn-list">
                        <div class="w-dyn-items">
                            <div class="w-dyn-item">
                                <div class="upcoming-event-text">
                                    <div class="event flex-wrapper">
                                        <div class="upcoming-event-date">
                                            <div>
                                                <div class="day large">08</div>
                                                <div class="month">Aug</div>
                                            </div>
                                        </div>
                                        <div class="upcoming-event-text-box">
                                            <a href="#" class="heading-link w-inline-block">
                                                <h4 class="heading-4-link">Tenure and Non-Tenure Track Visiting Faculty Positions</h4>
                                            </a>
                                            <p class="paragraph-3">Republic of Korea</p>
                                            <div class="event-item">
                                                <p class="paragraph-4 w-dyn-bind-empty"></p>
                                            </div>
                                            <div class="text-block w-richtext">
                                                <p>The Graduate School of Culture Technology (GSCT) at KAIST(*) invites applicants for tenure-track and non-tenure-track visiting faculty positions. </p>
                                              
                                             
                                            </div>
                                            <a class="text-link top-border">Learn more &amp;apply</a>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="w-dyn-item">
                                <div class="upcoming-event-text">
                                    <div class="event flex-wrapper">
                                        <div class="upcoming-event-date">
                                            <div>
                                                <div class="day large">08</div>
                                                <div class="month">Aug</div>
                                            </div>
                                        </div>
                                        <div class="upcoming-event-text-box">
                                            <a href="#" class="heading-link w-inline-block">
                                                <h4 class="heading-4-link">Associate and Full Professor</h4>
                                            </a>
                                            <p class="paragraph-3">IMT School for Advanced Studies Lucca</p>
                                            <div class="event-item">
                                                <p class="paragraph-4">Italy</p>
                                            </div>
                                            <div class="text-block w-richtext">
                                                <p>Deadline for expressions of interest: September 15th 2017 at 11:59 PM, Italian time.</p>
                                               
                                            </div>
                                            <a class="text-link top-border">Learn more &amp;apply</a>
                                            <a class="text-link top-border">email</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="w-dyn-items">
                                <div class="w-dyn-item">
                                    <div class="upcoming-event-text">
                                        <div class="event flex-wrapper">
                                            <div class="upcoming-event-date">
                                                <div>
                                                    <div class="day large">08</div>
                                                    <div class="month">Aug</div>
                                                </div>
                                            </div>
                                            <div class="upcoming-event-text-box">
                                                <a href="#" class="heading-link w-inline-block">
                                                    <h4 class="heading-4-link">Tenure and Non-Tenure Track Visiting Faculty Positions</h4>
                                                </a>
                                                <p class="paragraph-3">Republic of Korea</p>
                                                <div class="event-item">
                                                    <p class="paragraph-4 w-dyn-bind-empty"></p>
                                                </div>
                                                <div class="text-block w-richtext">
                                                    <p>The Graduate School of Culture Technology (GSCT) at KAIST(*) invites applicants for tenure-track and non-tenure-track visiting faculty positions. </p>
                                                  
                                                 
                                                </div>
                                                <a class="text-link top-border">Learn more &amp;apply</a>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="w-dyn-items">
                                    <div class="w-dyn-item">
                                        <div class="upcoming-event-text">
                                            <div class="event flex-wrapper">
                                                <div class="upcoming-event-date">
                                                    <div>
                                                        <div class="day large">08</div>
                                                        <div class="month">Aug</div>
                                                    </div>
                                                </div>
                                                <div class="upcoming-event-text-box">
                                                    <a href="#" class="heading-link w-inline-block">
                                                        <h4 class="heading-4-link">Tenure and Non-Tenure Track Visiting Faculty Positions</h4>
                                                    </a>
                                                    <p class="paragraph-3">Republic of Korea</p>
                                                    <div class="event-item">
                                                        <p class="paragraph-4 w-dyn-bind-empty"></p>
                                                    </div>
                                                    <div class="text-block w-richtext">
                                                        <p>The Graduate School of Culture Technology (GSCT) at KAIST(*) invites applicants for tenure-track and non-tenure-track visiting faculty positions. </p>
                                                      
                                                     
                                                    </div>
                                                    <a class="text-link top-border">Learn more &amp;apply</a>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            <div class="w-dyn-item">
                                <div class="upcoming-event-text">
                                    <div class="event flex-wrapper">
                                        <div class="upcoming-event-date">
                                            <div>
                                                <div class="day large">03</div>
                                                <div class="month">Mar</div>
                                            </div>
                                        </div>
                                        <div class="upcoming-event-text-box">
                                            <a href="#" class="heading-link w-inline-block">
                                                <h4 class="heading-4-link">Postdoctoral Research Associate</h4>
                                            </a>
                                            <p class="paragraph-3">Network Science Institute at Northeastern University</p>
                                            <div class="event-item">
                                                <p class="paragraph-4">Boston, MA</p>
                                            </div>
                                            <div class="text-block w-richtext">
                                                <p>The lab of Professor Albert-László Barabási, together with the
Network Science Institute at Northeastern University and the Division of Network
Medicine at Harvard University.</p>
                                             
                                            </div>
                                            <a class="text-link top-border">Learn more &amp;apply</a>
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-section">
            <div class="footer">
                <div class="content-wrapper w-container">
                    <div class="flex-wrapper space-between">
                        <div class="_33-percent-column footer-logo">
                            <a href="http://nunetsi.org" target="_blank" class="w-inline-block">
                                <img src="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black.png" srcset="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black-p-500x101.png 500w, https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black-p-800x162.png 800w, https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black-p-1080x218.png 1080w, https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black-p-1600x323.png 1600w, https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58c0eacec308a2772cd4f080_NU_NetworkScienceInstitute-black.png 1917w" sizes="(max-width: 479px) 92vw, (max-width: 767px) 68vw, 31vw"/>
                            </a>
                        </div>
                        <div class="_17-percent-column">
                            <div class="footer-box-link">
                                <div class="bottom-border mini-title">Menu</div>
                                <a href="/" class="mini-title-link">home</a>
                                <a href="/publications" class="mini-title-link">Publications</a>
                                <a href="/people" class="mini-title-link">people</a>
                                <a href="/jobs" class="mini-title-link">Jobs</a>
                            </div>
                        </div>
                        <div class="_33-percent-column contact-details">
                            <div class="bottom-border mini-title">Contact us</div>
                            <div class="contact-details">
                                <div class="contact-details-item">
                                    <div>
                                        <img src="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58bcae2c9d6c401e73a2703d_Pin-2.png" width="16" class="contact-details-icon"/>
                                    </div>
                                    <div class="contact-detail-text-box">
                                        <p class="contact-details-text">
                                            Northeastern University<br/>
                                            177 Huntington Ave.<br/>
                                            11th floor<br/>Boston, MA 02115
                                        </p>
                                    </div>
                                </div>
                                <div class="contact-details-item">
                                    <div>
                                        <img src="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58bcae2c9d6c401e73a27060_Phone%20(1).png" width="19" class="contact-details-icon"/>
                                    </div>
                                    <div class="contact-detail-text-box">
                                        <p class="contact-details-text">617 373 2355</p>
                                    </div>
                                </div>
                                <div class="contact-details-item">
                                    <div>
                                        <img src="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/58d49c057dec36a576b49a4c_email-icon-126.png" width="19" class="contact-details-icon"/>
                                    </div>
                                    <div class="contact-detail-text-box">
                                        <p class="contact-details-text">
                                            <a href="mailto:s.aleva@neu.edu" class="footer-link">email</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="small-print">
                <div class="content-wrapper w-container">
                    <div>
                        <p class="small-print-text">© 2017 Barabási Lab</p>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js" type="text/javascript"></script>
        <script src="https://daks2k3a4ib2z.cloudfront.net/58bcae2c9d6c401e73a26fed/js/webflow.a34d28150.js" type="text/javascript"></script>
        <!--[if lte IE 9]><script src="//cdnjs.cloudflare.com/ajax/libs/placeholders/3.0.2/placeholders.min.js"></script><![endif]-->
    </body>
</html>
