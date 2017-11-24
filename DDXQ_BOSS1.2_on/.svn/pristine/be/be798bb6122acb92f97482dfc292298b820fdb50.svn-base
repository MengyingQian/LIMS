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
		function change(){
			
			retData={};
			retData.prepwd=$("#prepwd").val();
			retData.pwd=$("#pwd").val();
			$.ajax({
				url : '/ddxq/admin/admininfo/submit',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				success : function(data) {
					if(data.success==true){
						alert("修改成功");		
						$('#ff').form('clear');								
					}else{
						alert("修改失败");
					}				
				},
				error : function(xhr) {
				}
			});
			return true;
		}
</script>
</head>
<body  style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;" >
   <div style="padding:9% 20%;">
	 <div class="easyui-panel" title="修改密码" style="width:100%;max-width:1000px;padding:20px 20px; "> 
		<form id="ff">
		<div style="margin-bottom:20px">
			<input id="prepwd"class="easyui-textbox" label="原密码:" labelPosition="top" style="width:100%;height:52px">
		</div>
		<div style="margin-bottom:20px">
			<input id="pwd"class="easyui-textbox" label="新密码:" labelPosition="top" style="width:100%;height:52px">
		</div>
		<div style="margin-bottom:20px">
			<input id="pwdd"class="easyui-textbox" label="再一次输入新密码:" labelPosition="top" style="width:100%;height:52px">
		</div>
		
		<div>
			<a onclick="change()"class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">修改</a>
		</div>
		</form>
	 </div>
   </div>
</body>
</html>