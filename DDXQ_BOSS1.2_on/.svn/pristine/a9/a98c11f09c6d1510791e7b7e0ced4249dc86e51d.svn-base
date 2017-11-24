<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
		$("#districtId").numberbox('setValue',$("#dist").val());
		$("#districtId").numberbox({ disabled: true});
	})
	
	</script>
<style>
.datagrid-btable tr{height: 30px;}
</style>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
    
   <div style="padding:5% 20%;">   
	<div class="easyui-panel" title="小区介绍" style="width:100%;max-width:1000px;padding:20px 20px;">
		<form id="ff" method="post">
		<div style="margin-bottom:8px">	
			<select id="select" class="easyui-combobox" name="select"required="true" label="选择内容类型:"  style="width:80%;">
				<option value="xqjj">小区简介</option>
				<option value="xqjg">小区机构</option>
				<option value="gjxl">公交线路</option>
				<option value="fwtd">服务团队</option>
			</select>
		</div>
			<div style="margin-bottom:8px">
				<input class="easyui-numberbox"id="districtId"  name="districtId" style="width:80%" data-options="label:'小区编号',required:true">
			</div>

			<div style="margin-bottom:8px">
				<input class="easyui-textbox" id="content"name="content" style="width:90%;height:180px" data-options="label:'内容:',prompt:'请用内容编辑器',multiline:true,required:true">
			</div>
			
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
	<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a> -->
			<input type="hidden" id="dist" value="${districtId}"/>
		</div>
	</div>
   </div>

	<script type="text/javascript">
		function submitForm(){
			var r=confirm("是否添加信息？");
			if (r==false){
				 return false;
			 }
		  	var check=$("#ff").form('enableValidation').form('validate');
			if(check==false){
				alert("请填写完整");
				return false;
			}
			var retData = {};
			retData.districtId=$('#districtId').val();
			retData.type=$("#select").combobox('getValue');	
			retData.content=$('#content').val();
			$.ajax({
				url : '/ddxq/admin/xxl/xqjs/add',
				data : $.toJSON(retData),
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
		function clearForm(){
			$('#content').textbox('setValue',"");;
		}
	</script>
</body>
</html>