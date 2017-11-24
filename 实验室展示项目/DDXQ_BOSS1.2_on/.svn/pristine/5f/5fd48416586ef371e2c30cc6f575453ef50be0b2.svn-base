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
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
		<div class="easyui-panel" title="推送通知" style="width:800px;padding:10px 10px;">
		<form id="ff" method="post">
		<div style="margin-bottom:8px">	
			<select id="select" class="easyui-combobox" name="select"required="true" label="选择推送类型:" labelPosition="top" style="width:100%;">
				<option value="wygl">物业管理通知</option>
				<option value="kdds">快递代收提醒</option>
			</select>
		</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox"id="districtId"  name="districtId" style="width:90%" data-options="label:'小区编号',required:true">
			</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox" id="first"name="first" style="width:90%" data-options="label:'标题',required:true">
			</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox"id="data1" name="data1" style="width:90%" data-options="label:'小标题:',required:true">
			</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox" id="data2"name="data2" style="width:90%" data-options="label:'时间:',required:true">
			</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox" id="data3"name="data3" style="width:90%;height:80px" data-options="label:'内容:',multiline:true">
			</div>
			<div style="margin-bottom:8px">
				<input class="easyui-textbox"id="remark" name="remark" style="width:90%;height:70px" data-options="label:'备注:',multiline:true">
			</div>
			<input type="hidden" id="mobanId"  name="mobanId" value="IKlPHRMuNLkevEA0HWgEfotiEfOp5knMeNB0pQCYmP0" />
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">推送</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
		</div>
		</div>


	<script type="text/javascript">
	$(function(){
		$("#select").combobox({
			onChange:function(newValue,oldValue){
			switch(newValue){
			case "wygl":
				$("#first").textbox({label:"标题"});	
				$("#data1").textbox({label:"小标题"});
				$("#data2").textbox({label:"发布时间"});
				$("#data3").textbox({label:"内容"});
				$("#remark").textbox({label:"备注"});
				$("#mobanId").val("IKlPHRMuNLkevEA0HWgEfotiEfOp5knMeNB0pQCYmP0");
				break;
			case "kdds":
				$("#first").textbox({label:"标题"});	
				$("#data1").textbox({label:"送达时间"});
				$("#data2").textbox({label:"存放地点"});
				$("#data3").textbox({label:"联系方式"});
				$("#remark").textbox({label:"备注"});
				$("#mobanId").val("_mYiJsWlpQCzPiYBZcUkfp8zgiKhmuaiIl5uOs-Pb1M");
				break;			
			}
			}
		})
	});
		function submitForm(){
			var r=confirm("是否推送信息？");
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
			retData.first=$('#first').val();
			retData.data1=$('#data1').val();
			retData.data2=$('#data2').val();
			retData.data3=$('#data3').val();
			retData.remark=$('#remark').val();
			retData.mobanId=$('#mobanId').val();
			$.ajax({
				url : '/ddxq/boss/business/wuye/sendMsg',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.errcode=="0"){
						alert("推送成功");				
					}else{
						alert("推送失败");
					}				
				},
				error : function(xhr) {
				}
			});		
			
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>