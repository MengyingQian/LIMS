<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
@charset "UTF-8"; 

[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak,
	.x-ng-cloak, .ng-hide:not (.ng-hide-animate ){
	display: none !important;
}

ng\:form {
	display: block;
}

.ng-animate-shim {
	visibility: hidden;
}

.ng-anchor {
	position: absolute;
}

</style>
<style type="text/css">
body {
	font-size: 16px;  font-family: '微软雅黑' ;
}
</style>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0,maximum-scale=1, user-scalable=no">
<meta name="x5-orientation" content="portrait">
<meta http-equiv="cache-control"
	content="no-cache, no-store, must-revalidate, no-siteapp">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta name="copyright"
	content="All Rights Reserved, Copyright (C) 2016, DDXQ" />
<title>智慧小区运营支撑平台-员工</title>
<link rel="stylesheet" type="text/css" href="/view/pub/basic/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/icon.css" />
<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/themes.css" />
<script type="text/javascript" src="/view/pub/basic/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/view/pub/basic/js/jquery-2.2.3.min.js" ></script>
<script type="text/javascript" src="/view/pub/basic/js/jquery.json.min.js" ></script>
<script type="text/javascript" src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/view/pub/basic/js/jquery.cookie.js"></script>
<script>
$(function(){
	$("body").layout({
		
		});
	$("#main_menu").accordion({
		
		});
	$("#menu").tree({
		
		});
	showTime();
});

function currentTime(){
	
	return str;
}

function padleft0(obj) {
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);
}

function getnowtime() {
    var nowtime = new Date();
    var year = nowtime.getFullYear();
    var month = padleft0(nowtime.getMonth() + 1);
    var day = padleft0(nowtime.getDate());
    var hour = padleft0(nowtime.getHours());
    var minute = padleft0(nowtime.getMinutes());
    var second = padleft0(nowtime.getSeconds());
    var millisecond = nowtime.getMilliseconds(); millisecond = millisecond.toString().length == 1 ? "00" + millisecond : millisecond.toString().length == 2 ? "0" + millisecond : millisecond;
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

function showTime() {
	var curTime = getnowtime();
    $("#currenttime").html(curTime);
    setTimeout("showTime()", 1000);
}

function addtt(title, url, id){
	if(!$('#mainTabs').tabs('exists', title)){
		$('#mainTabs').tabs('add',{  
			title:title,  
			content:'<iframe id="'+ id +'" src="'+url+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',  
			closable:true
		});
	} else {
		$('#mainTabs').tabs('select', title);
	}
	//<span style="color:white;font-weight:bold">   当前时间：</span><span style="color:white;font-weight:bold" id="currenttime">10:26:2</span>
}
</script>
</head>

<body >
	<div region="north" style="height: 78px; border: none;overflow-y:hidden">
		<div id="northContent"
			style="background: url(/view/pub/basic/images/main_top_bg.jpg) repeat-x; overflow: auto;">
			<div class="north-left" style="float: left;">
				<img src="/view/pub/basic/images/main_top3.jpg" />
			</div>
			<div class="north-right" style="padding: 10px 10px 10px 10px;float: right;">
				<span style=";color:white;font-weight:bold">当前用户：</span><span style="font-weight:bold;color:white"id="userpower">${sessionScope.account}</span> <span style=";color:white;font-weight:bold">&nbsp身份：</span> <span style="color:white;font-weight:bold"> ${sessionScope.name}</span>
				<span style=";color:white;font-weight:bold">&nbsp小区：</span> <span style="color:white;font-weight:bold"> ${sessionScope.xiaoquname}</span>
				
				<span style=";color:red;font-weight:bold "><a
				style="color:white;font-weight:bold; padding: 0px 0px 0px 20px;"
						href="/ddxq/boss/loginManage/loginout">     退出</a></span>
			</div>
		</div>
	</div>

	<div region='west' class="west" split="true" title='功能导航'
		style="width: 220px;">
		<div id="main_menu" fit="true">
			<c:if test="${sessionScope.actorid==100}">
				<div title="小区系统管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span style="font-family:-apple-system-font,"Helvetica Neue", Helvetica,sans-serif,微软雅黑;">系统管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','ddxq/admin/admininfo/myinfo')" style="font-family:-apple-system-font,"Helvetica Neue", Helvetica,sans-serif,微软雅黑;">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
				 					onclick="addtt('修改密码','ddxq/admin/admininfo/changepwd')" style="font-family:-apple-system-font,"Helvetica Neue", Helvetica,sans-serif,微软雅黑;">修改密码</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区资料','/ddxq/nocontent/show')">小区资料</a>
								</li>
								
							</ul>
						</li>
						<li data-options="state:'closed'"><span>用户管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('商家用户','/ddxq/admin/userman/user?actorid=800')">商家用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('骑手用户','/ddxq/admin/userman/user?actorid=700')">骑手用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('物流代收用户','/ddxq/admin/userman/user?actorid=170')">物流代收用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('物业用户','/ddxq/admin/userman/user?actorid=200')">物业用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('居委会用户','/ddxq/admin/userman/user?actorid=300')">居委会用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('业委会用户','/ddxq/admin/userman/user?actorid=500')">业委会用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('片警用户','/ddxq/admin/userman/user?actorid=110')">片警用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('家医用户','/ddxq/nocontent/show')">家医用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('中介用户','/ddxq/nocontent/show')">中介用户</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('客服用户','/ddxq/nocontent/show')">客服用户</a>
								</li>
							</ul>
						</li>
						<li data-options="state:'closed'"><span>资讯管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区介绍','/ddxq/admin/xxl/xqjs/show')">小区介绍</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('消息中心','/ddxq/admin/xxl/xwzx/showNotice')">消息中心</a>
								</li>
								
								<li><a href="javascript:void(0)"
									onclick="addtt('模板消息','/ddxq/nocontent/show')">模板消息</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								</li>
							</ul>
						</li>
						<li data-options="state:'closed'"><span>商超管理</span>
							<ul>
								 
								<li><a href="javascript:void(0)"
									onclick="addtt('商品列表','/ddxq/admin/shop/showlist?typeid=1')">商品列表</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('今日优惠','/ddxq/admin/shop/showlist?typeid=2')">今日优惠</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('商品审核','/ddxq/nocontent/show')">商品审核</a>
								</li>
							</ul>
						</li>
						
						<li data-options="state:'closed'"><span>服务管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('服务管理','/ddxq/admin/servs/yhby/show')">服务管理</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('服务分类','/ddxq/nocontent/show')">服务分类</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('房屋租售','/ddxq/admin/fwzs/show')">房屋租售</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('招聘求职','/ddxq/admin/zpqz/show')">招聘求职</a>
								</li>
													
							</ul>
						</li>
						<li data-options="state:'closed'"><span>广告管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('发布广告','/ddxq/nocontent/show')">发布广告</a>
								</li>					
							</ul>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('广告统计','/ddxq/nocontent/show')">广告统计</a>
								</li>					
							</ul>
						</li>
						<li data-options="state:'closed'"><span>财务管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('财务报表','/ddxq/nocontent/show')">财务报表</a>
								</li>					
							</ul>
							
						</li>
						<li data-options="state:'closed'"><span>微信管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区粉丝','/ddxq/admin/weixinmanage/show')">小区粉丝</a>
								</li>					
							
							
								<li><a href="javascript:void(0)"
									onclick="addtt('认证居民','/ddxq/nocontent/show')">认证居民</a>
								</li>					
							</ul>
							
						</li>
						<li data-options="state:'closed'"><span>功能配置</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区参数配置','/ddxq/admin/parameter/show')">小区参数配置</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区超市开关','/ddxq/admin/userman/servicesstatus?type=1')">小区超市状态</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('房屋租售开关','/ddxq/admin/userman/servicesstatus?type=4')">房屋租售开关</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('招聘求职开关','/ddxq/admin/userman/servicesstatus?type=5')">招聘求职开关</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('小区直播开关','/ddxq/admin/userman/servicesstatus?type=2')">小区直播开关</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('快递代收开关','/ddxq/admin/userman/servicesstatus?type=3')">快递代收开关</a>
								</li>
													
							</ul>
						</li>
					</ul>
				</div>

			</c:if>

<!-- 小区物业管理 -->			
		<c:if test="${sessionScope.actorid==200}">			
				<div title="小区物业管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>

							</ul>
						</li> 
						<li data-options="state:'closed'"><span>资讯管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('发布物业通知','/ddxq/admin/xxl/xwzx/showSingleNotice')">物业通知</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('寻觅启事','/ddxq/nocontent/show')">寻觅启事</a>
								</li>
							</ul>
						</li>						
						<li data-options="state:'closed'"><span>居民管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('居民列表','/ddxq/nocontent/show')">居民列表</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('居民认证','/ddxq/nocontent/show')">居民认证</a>
								</li>

							</ul>
						</li>
					</ul>
				</div>					
			</c:if>
<!-- 居委会 -->	
		<c:if test="${sessionScope.actorid==300}">			
				<div title="小区居委会管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>

							</ul>
						</li> 
						<li data-options="state:'closed'"><span>资讯管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('发布居委会通知','/ddxq/admin/xxl/xwzx/showSingleNotice')">居委会通知</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>					
			</c:if>
<!-- 业委会	 -->	
		<c:if test="${sessionScope.actorid==500}">			
				<div title="小区业委会管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>
							</ul>
						</li> 
						<li data-options="state:'closed'"><span>资讯管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('发布业委会通知','/ddxq/admin/xxl/xwzx/showSingleNotice')">业委会通知</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>					
			</c:if>
<!--代收用户 -->	
		<c:if test="${sessionScope.actorid==170}">			
				<div title="快递代收管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>
							</ul>
						</li> 
						<li data-options="state:'closed'"><span>通知管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('发送短信','/ddxq/employees/daishou/message')">发送短息通知</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('发送模板消息','/ddxq/employees/daishou/muban')">发送模板消息</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>					
			</c:if>			
<!-- 小区片警	 -->		
				<c:if test="${sessionScope.actorid==110}">			
				<div title="小区片警管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>
							</ul>
						</li> 
						<li data-options="state:'closed'"><span>资讯管理</span>
							<ul>
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('发布派出所通知','/ddxq/admin/xxl/xwzx/showSingleNotice')">派出所通知</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>					
			</c:if>
<!-- 骑手 -->			
			<c:if test="${sessionScope.actorid==700}">
				<div title="小区骑手管理">
				 <ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>
							</ul>
						</li> 
						</li>
						<li data-options="state:'closed'"><span>订单管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('未送达订单','/ddxq/nocontent/show')">未送达订单</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('已送达订单','/ddxq/nocontent/show')">已送达订单</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>					
			</c:if>
<!--商家	 -->		
			<c:if test="${sessionScope.actorid==800}">
				<div title="小区商家管理">
					<ul id="system_menu" class="easyui-tree">
						<li><span>个人信息管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('个人资料','/ddxq/employees/info/myinfo')">个人资料</a>
								</li>
								<li><a href="javascript:void(0)"
									onclick="addtt('修改密码','/ddxq/employees/info/changepwd')">修改密码</a>
								</li>

							</ul>
						</li> 
						<li data-options="state:'closed'">
						<span>商品管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('商品列表','/ddxq/seller/xqcs/show')">商品列表</a>
								</li>
								
								<li><a href="javascript:void(0)"
									onclick="addtt('内容编辑器','/ddxq/admin/xxl/xwzx/showEditor')">内容编辑器</a>
								<li><a href="javascript:void(0)"
									onclick="addtt('订单列表','/ddxq/nocontent/show')">订单列表</a>
								</li>
								</li>

							</ul>					
						</li>
						<li data-options="state:'closed'">
						<span>财务管理</span>
							<ul>

								<li><a href="javascript:void(0)"
									onclick="addtt('销售报表','/ddxq/nocontent/show')">销售报表</a>
								</li>
								
							</ul>					
						</li>
					</ul>
				</div>					
			</c:if>
			
		</div>
	</div>

	<div region='center' style="background: #eee;" scrolling="no">
		<div id="mainTabs" class="easyui-tabs" fit="true" border="false"
			plain="true">
			<div title="欢迎页面">
				<iframe id="WelcomeTab" src="ddxq/boss/loginManage/welcome"
					frameBorder="0" border="0" scrolling="no"
					style="width: 100%; height: 100%;" ></iframe>
			</div>
		</div>
	</div>
	<input type="hidden" id="actorid" name="actorid" value="${actorid}" />
</body>
</html>