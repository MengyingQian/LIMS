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
		function addParameter(){
			var rediskey=$("#rediskey").val();
			var value=$("#value").val();
			if(rediskey.length<4||value.length<3){
				alert("请正确输入");
				return false;
			}
			var data={};
			data.rediskey=rediskey;
			data.value=value;			
			$.ajax({
				url : '/ddxq/system/parameter/add',
				data : $.toJSON(data),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						alert("添加成功");				
					}else{
						alert("添加失败");
					}				
				},
				error : function(xhr) {
				}
			});		
		}
		function addParameter2(){
			var rediskey="ddxq:system:duanxinip";
			var value=$("#duanxinip").val();
			if(value.length<3){
				alert("请正确输入");
				return false;
			}
			var data={};
			data.rediskey=rediskey;
			data.value=value;			
			$.ajax({
				url : '/ddxq/system/parameter/add',
				data : $.toJSON(data),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						alert("添加成功");				
					}else{
						alert("添加失败");
					}				
				},
				error : function(xhr) {
					alert("添加失败");
				}
			});		
		}
		function searchParameter(){
			var rediskey=$("#rediskey2").val();
			var data={};
			data.rediskey=rediskey;
			if(rediskey.length<4){
				alert("请正确输入");
				return false;
			}
			$.ajax({
				url : '/ddxq/system/parameter/search',
				data : $.toJSON(data),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$("#result").textbox('setValue',data.value);				
					}else{
						alert("查询失败");
					}				
				},
				error : function(xhr) {
				}
			});		
		}
</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
  <div style="padding:5% 20%;">   
	<div class="easyui-panel" title="添加参数配置" style="width:100%;max-width:1000px;padding:20px 20px;">
	
			<div style="margin-bottom:5px">
				<input id="duanxinip" name="duanxinip"class="easyui-textbox" label="系统短信平台IP地址:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a onclick="addParameter2()" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
			<div style="margin-bottom:20px">
				
			</div>
	
			<div style="margin-bottom:5px">
				<input id="rediskey" name="rediskey"class="easyui-textbox" label="添加RedisKey:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div style="margin-bottom:5px">
				<input id="value"name="value" class="easyui-textbox" label="添加Value:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a onclick="addParameter()" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
			<div style="margin-bottom:20px">
				
			</div>
			<div style="margin-bottom:5px">
				<input id="rediskey2" name="rediskey"class="easyui-textbox" label="查询RedisKey:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div style="margin-bottom:5px">		
				<input id="result"class="easyui-textbox" label="查询结果Value：" labelPosition="top" style="width:100%;height:52px">
				<a onclick="searchParameter()" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">查询</a>
			</div>
			
			
			
			
		</div>
	</div>
</body>
</html>