<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cN">
	<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0,maximum-scale=1, user-scalable=no">
    <meta name="x5-orientation" content="portrait">
    <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate, no-siteapp">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta name="copyright" content="All Rights Reserved, Copyright (C) 2016, DDXQ" />
    <title>智慧小区运营支撑平台</title>
    <link rel="stylesheet" type="text/css" href="/view/pub/basic/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/main.css" />
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/user_login.css" />
    <script src="/view/pub/basic/js/jquery-2.2.3.min.js" type="text/javascript"></script>
    <script src="/view/pub/basic/js/jquery.json.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	 function sendJsonData(){
	    	var retData = {};
	    	retData.sceneId = $("#sceneId_box").val();
	    	$.ajax({
		        url: '/ddxq/boss/loginManage/getSceneId',
		        data: $.toJSON(retData),
		        type: 'post',
		        dataType: 'json',
		        contentType: 'application/json',
		        cache: false,
		        success: function(data) {
		            $("#sceneId_box").val(data.qrUrl);
		        },
		        error: function(xhr) {
		            //中间发生异常，具体查看xhr.responseText
		        }
		    });
	    	return false;
	    }
	</script>
	
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin: 5px;
    ">
    <div style="padding: 10% 9%">
	    <div class="easyui-panel" style="padding:10%;background:#fafafa;" title="欢迎登陆"  >
	    	<div style="text-align:center;font-size: 18px;  font-family: '微软雅黑' ;">欢迎登陆小区管理系统!</div>
		</div>
	 </div>
</body>
</html>