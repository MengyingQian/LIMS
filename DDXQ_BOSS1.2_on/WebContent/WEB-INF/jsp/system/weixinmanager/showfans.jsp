<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css"href="/view/pub/basic/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"href="/view/pub/basic/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css"href="/view/pub/basic/css/demo.css">
	<script type="text/javascript"src="/view/pub/basic/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/js/jquery.json.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">	
		$(function(){
			
		});
		function showData(){
		$("#dg").datagrid('options').url ='/ddxq/system/weixinmanager/showAll';
    	$("#dg").datagrid('reload');
	}
		function searchData(){
			var districtId=$("#districtId").val();
			$("#dg").datagrid('options').url ='/ddxq/system/weixinmanager/search?districtId='+districtId;
	    	$("#dg").datagrid('reload');
		}
</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="管理员信息"  fit="true"
			data-options="
				rownumbers:true,
				singleSelect: true,
				toolbar: '#tb',
				url: '',
				method: 'get'
			">
		<thead>
			<tr>				
				<th data-options="field:'districtId',width:55">小区编号</th>
				<th data-options="field:'openid',width:60">openId</th>
				<th data-options="field:'ticket',width:80">ticket</th>		
				<th data-options="field:'nickname',width:80">昵称</th>
				<th data-options="field:'sex',width:80,align:'center'">性别</th>
				<th data-options="field:'province',width:80">省份</th>
				<th data-options="field:'city',width:80">城市</th>
				<th data-options="field:'phone',width:80">电话</th>
				<th data-options="field:'address',width:80">地址</th>
				<th data-options="field:'created',width:150,align:'center'">创建时间</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">
			小区编号: <input type="text" id="districtId" class="easyui-textbox"
			style="width: 50px"> 
		<a href="javascript:void(0);" onclick="searchData()"class="easyui-linkbutton" iconCls="icon-search">查询粉丝</a> (请输入小区编号查询)		
		
	</div>
</body>
</html>