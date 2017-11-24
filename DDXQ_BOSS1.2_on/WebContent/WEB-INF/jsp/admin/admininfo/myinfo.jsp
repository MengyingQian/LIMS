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
	<style type="text/css">
body {
	font-size: 16px;  font-family: '微软雅黑' ;
}
</style>
	<script type="text/javascript"src="/view/pub/basic/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/js/jquery.json.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">	
		$(function(){
			$('#dlg').dialog('close');	
			$('#dlg2').dialog('close');	
		})
</script>
	<title>个人资料 </title>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="我的管理员信息"  fit="true" 
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				rownumbers:true,
				toolbar: '#tb',
				url: '/ddxq/admin/admininfo/search',
				method: 'get',
				onDblClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'employeeId',width:180">账号（手机号）</th>
				<th data-options="field:'passWD',hidden:true,width:60">密码</th>
				<th data-options="field:'employeeActor',hidden:true,width:80,align:'right'">角色</th>		
				<th data-options="field:'districtId',width:80">小区编号</th>
				<th data-options="field:'employeeName',width:80,align:'center'">职员姓名</th>
				<th data-options="field:'employeeNickName',width:100,align:'center'">昵称</th>
				
				<th data-options="field:'employeeIdCard',width:180,align:'right'">身份证号</th>
				<th data-options="field:'employeeSex',width:80,align:'right'">性别</th>
<!-- 				<th data-options="field:'employeeBirth',width:80,align:'right'">出生日期</th>-->
				<th data-options="field:'employeeNation',width:80,align:'right'">民族</th> 
				
				<th data-options="field:'employeeMobile',width:80,align:'right'">手机号</th>
<!-- 				<th data-options="field:'employeeEmail',width:80,align:'right'">邮箱</th> -->
				
<!-- 				<th data-options="field:'employeeProvince',width:80,align:'center'">省份</th>
				<th data-options="field:'employeeCity',width:80,align:'right'">城市</th> -->
				<th data-options="field:'employeeAddr',width:80,align:'right'">地址</th>
				<th data-options="field:'employeePostcode',width:80,align:'right'">邮编</th>
				
<!-- 				<th data-options="field:'employeeDepart',width:80,align:'center'">职员部门</th>
				<th data-options="field:'employeeSalary',width:80,align:'right'">职员待遇</th>
				<th data-options="field:'employeeLevel',width:80,align:'right'">职员级别</th>
				<th data-options="field:'employeePoints',width:80,align:'right'">职员积分</th>
			
				<th data-options="field:'created',width:150,align:'center'">创建时间</th>
				<th data-options="field:'updated',width:150,align:'center'">更新时间</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th> -->
				<th data-options="field:'notes',width:0,hidden:true,align:'center'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">		
		<select class="easyui-combobox"id="selectactor" label="管理员类型：" style="width:25%"><option value="100">小区管理员</option></select>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改</a>
	</div>
<script type="text/javascript">
		var editIndex = undefined;
		var app=false;
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');
			$("#employeeId2").textbox('setValue',row.employeeId);
			$("#employeeId2").textbox({ disabled: true});
			$("#employeeActor2").combobox({ disabled: true});
			$("#employeeActor2").combobox('setValue',$("#selectactor").combobox('getValue'));			
			$("#districtId2").numberbox('setValue',row.districtId);
			$("#districtId2").numberbox({ disabled: true});
			$("#employeeName2").textbox('setValue',row.employeeName);
			$("#employeeNickName2").textbox('setValue',row.employeeNickName);
			$("#employeeIdCard2").numberbox('setValue',row.employeeIdCard);
			$("#employeeSex2").combobox('setValue',row.employeeSex);
			$("#employeeBirth2").datebox('setValue',row.employeeBirth);
			$("#employeeMobile2").numberbox('setValue',row.employeeMobile);		
			$("#employeeEmail2").textbox('setValue',row.employeeEmail);
			$("#employeeProvince2").textbox('setValue',row.employeeProvince);		
			$("#employeeCity2").textbox('setValue',row.employeeCity);		
			$("#employeeAddr2").textbox('setValue',row.employeeAddr);
			$("#employeePostcode2").numberbox('setValue',row.employeePostcode);
			$("#employeeDepart2").textbox('setValue',row.employeeDepart);
			$("#employeeSalary2").numberbox('setValue',row.employeeSalary);
			$("#employeeLevel2").numberbox('setValue',row.employeeLevel);
			$("#employeePoints2").numberbox('setValue',row.employeePoints);			
			$("#status2").combobox('setValue',row.status);	
			$("#notes2").textbox('setValue',row.notes);		
		}
		function getData2(){
			var retData = {};
			if($("#ff2").form('enableValidation').form('validate')==false){
				return false;
			}
			
			retData.employeeId=$("#employeeId2").numberbox('getValue');			
			retData.employeeActor=$("#employeeActor2").combobox('getValue');			
			retData.districtId=$("#districtId2").numberbox('getValue');			
			retData.employeeName=$("#employeeName2").textbox('getValue');
			retData.employeeNickName=$("#employeeNickName2").textbox('getValue');		
			retData.employeeIdCard=$("#employeeIdCard2").numberbox('getValue');
			retData.employeeSex=$("#employeeSex2").combobox('getValue');		
			retData.employeeBirth=$("#employeeBirth2").datebox('getValue');
			retData.employeeMobile=$("#employeeMobile2").numberbox('getValue');			
			retData.employeeEmail=$("#employeeEmail2").textbox('getValue');
			retData.employeeProvince=$("#employeeProvince2").textbox('getValue');			
			retData.employeeCity=$("#employeeCity2").textbox('getValue');			
			retData.employeeAddr=$("#employeeAddr2").textbox('getValue');	
			retData.employeePostcode=$("#employeePostcode2").numberbox('getValue');	
			retData.employeeDepart=$("#employeeDepart2").textbox('getValue');
			retData.employeeSalary=$("#employeeSalary2").numberbox('getValue');
			retData.employeeLevel=$("#employeeLevel2").numberbox('getValue');
			retData.employeePoints=$("#employeePoints2").numberbox('getValue');					
			retData.status=$("#status2").combobox('getValue');	
			retData.notes=$("#notes2").textbox('getValue');	
			return retData;
		}
		
		function append(){
			$('#dlg').dialog('open');	
		
		}
		function removeSelect(){
			var r=confirm("是否删除选中的信息？");
			if (r==false){
				 return false;
			 }
			var retData={};
			var row = $('#dg').datagrid('getSelected');
			retData.account=row.account;
			retData.actorid=$("#selectactor").combobox('getValue');
			$('#dg').datagrid('clearSelections');
			data=$.toJSON(retData);			
			$.ajax({
				url : '/ddxq/system/distman/remove',
				data : data,
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$('#dlg').dialog('close');
						alert("删除成功");
						$("#dg").datagrid('options').url = data.url;
						$("#dg").datagrid('reload');					
					}else{
						alert("删除失败");
					}				
				},
				error : function(xhr) {
				}
			});
		}
	</script>
<div id="dlg" class="easyui-dialog" title="添加总管理员" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff" method="post">
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox"id="employeeId" name="employeeId" style="width:90%" data-options="label:'账号（手机号）:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passWD"name="passWD" style="width:90%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passWDD"style="width:90%" data-options="label:'再次输入密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="employeeActor" label="选择管理用户类型" style="width:90%"><option value="100">小区管理员</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="districtId"name="districtId" style="width:90%" data-options="label:'小区编号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeName"name="employeeName" style="width:90%" data-options="label:'姓名:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeNickName"name="employeeNickName" style="width:90%" data-options="label:'昵称:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeIdCard"name="employeeIdCard" style="width:90%" data-options="label:'身份证号:'">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="employeeSex" label="性别" style="width:90%"><option value="0"selected="selected">保密</option><option value="1">男</option><option value="2">女</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-datebox" id="employeeBirth" name="employeeBirth" style="width:90%" data-options="label:'出生日期:',required:true">
			</div>			
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeMobile"name="employeeMobile" style="width:90%" data-options="label:'手机号:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeEmail"name="employeeEmail" style="width:90%" data-options="label:'邮箱:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeProvince"name="employeeProvince" style="width:90%" data-options="label:'省份:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeCity"name="employeeCity" style="width:90%" data-options="label:'城市:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeAddr" name="employeeAddr" style="width:90%" data-options="label:'地址:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeePostcode" name="employeePostcode" style="width:90%" data-options="label:'邮编:'">
			</div>
			
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeDepart"name="employeeDepart" style="width:90%" data-options="label:'部门:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeSalary" value="0"name="employeeSalary" style="width:90%" data-options="label:'职员工资:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeLevel" value="0"name="employeeLevel" style="width:90%" data-options="label:'职员级别:'">
			</div>		
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeePoints"value="0"name="employeePoints" style="width:90%" data-options="label:'职员积分:'">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="status" label="状态" style="width:90%"><option value="1"selected="selected">正常</option><option value="0">冻结</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="notes" name="notes" style="width:90%" data-options="label:'备注:'">
			</div>
					
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			
		</div>
		</div>
		<script> 
		function getData(){
			var retData = {};
			if($("#ff").form('enableValidation').form('validate')==false){
				return false;
			}
			var password =$("#passWD").textbox('getValue');
			var passwordd =$("#passWDD").textbox('getValue');
			if( password != passwordd){
				alert("两次输入密码不一致，请重新输入");
				return false;
			}
			
			retData.employeeId=$("#employeeId").numberbox('getValue');	
			retData.passWD=$("#passWD").textbox('getValue');			
			retData.employeeActor=$("#employeeActor").combobox('getValue');			
			retData.districtId=$("#districtId").numberbox('getValue');			
			retData.employeeName=$("#employeeName").textbox('getValue');
			retData.employeeNickName=$("#employeeNickName").textbox('getValue');		
			retData.employeeIdCard=$("#employeeIdCard").numberbox('getValue');
			retData.employeeSex=$("#employeeSex").combobox('getValue');		
			retData.employeeBirth=$("#employeeBirth").datebox('getValue');
			retData.employeeMobile=$("#employeeMobile").numberbox('getValue');			
			retData.employeeEmail=$("#employeeEmail").textbox('getValue');
			retData.employeeProvince=$("#employeeProvince").textbox('getValue');			
			retData.employeeCity=$("#employeeCity").textbox('getValue');			
			retData.employeeAddr=$("#employeeAddr").textbox('getValue');	
			retData.employeePostcode=$("#employeePostcode").numberbox('getValue');	
			retData.employeeDepart=$("#employeeDepart").textbox('getValue');
			retData.employeeSalary=$("#employeeSalary").numberbox('getValue');
			retData.employeeLevel=$("#employeeLevel").numberbox('getValue');
			retData.employeePoints=$("#employeePoints").numberbox('getValue');					
			retData.status=$("#status").combobox('getValue');	
			retData.notes=$("#notes").textbox('getValue');	
			return retData;
		}
		function submitForm(){
			var retData=getData();
			
			$.ajax({
				url : '/ddxq/system/distman/insertInfo',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$('#dlg').dialog('close');
						alert("插入成功");
						$('#ff').form('clear');
						$("#districtIdFrom").numberbox('setValue',data.districtId);
						$("#dg").datagrid('options').url = data.url;
						$("#dg").datagrid('reload');					
					}else{
						alert("插入失败");
					}				
				},
				error : function(xhr) {
				}
			});

		}		
		function clearForm(){
			$('#ff').form('clear');
		}
		function searchData(){
			var employeeActor=$("#selectactor").combobox('getValue');
			$("#dg").datagrid('options').url ='/ddxq/system/distman/search?employeeActor='+employeeActor;
	    	$("#dg").datagrid('reload');
			
		}
		
	</script>
	<div id="dlg2" class="easyui-dialog" title="修改管理员信息" data-options="iconCls:'icon-save'" style="width:450px;height: 450px;padding:5px">	
		<form id="ff2" method="post">
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox"id="employeeId2" name="employeeId" style="width:90%" data-options="label:'账号（手机号）:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="employeeActor2" label="选择管理用户类型" style="width:90%"><option value="100">小区管理员</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="districtId2"name="districtId" style="width:90%" data-options="label:'小区编号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeName2"name="employeeName" style="width:90%" data-options="label:'姓名:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeNickName2"name="employeeNickName" style="width:90%" data-options="label:'昵称:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeIdCard2"name="employeeIdCard" style="width:90%" data-options="label:'身份证号:'">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="employeeSex2" label="性别" style="width:90%"><option value="0"selected="selected">保密</option><option value="1">男</option><option value="2">女</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-datebox" id="employeeBirth2" name="employeeBirth" style="width:90%" data-options="label:'出生日期:',required:true">
			</div>			
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeMobile2"name="employeeMobile" style="width:90%" data-options="label:'手机号:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeEmail2"name="employeeEmail" style="width:90%" data-options="label:'邮箱:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeProvince2"name="employeeProvince" style="width:90%" data-options="label:'省份:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeCity2"name="employeeCity" style="width:90%" data-options="label:'城市:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeAddr2" name="employeeAddr" style="width:90%" data-options="label:'地址:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeePostcode2" name="employeePostcode" style="width:90%" data-options="label:'邮编:'">
			</div>
			
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="employeeDepart2"name="employeeDepart" style="width:90%" data-options="label:'部门:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeSalary2" value="0"name="employeeSalary" style="width:90%" data-options="label:'职员工资:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeeLevel2" value="0"name="employeeLevel" style="width:90%" data-options="label:'职员级别:'">
			</div>		
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="employeePoints2"value="0"name="employeePoints" style="width:90%" data-options="label:'职员积分:'">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="status2" label="状态" disabled="disabled" style="width:90%"><option value="1"selected="selected">正常</option><option value="0">冻结</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="notes2" name="notes" style="width:90%" data-options="label:'备注:'">
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()" style="width:80px">更新</a>
		</div>
	</div>
	<script> 
		function submitEdit(){
			var retData=getData2();
			$.ajax({
				url : '/ddxq/admin/admininfo/editInfo',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$('#dlg2').dialog('close');
						$('#ff2').form('clear');
						alert("更新成功");
						$("#dg").datagrid('options').url = data.url;
						$("#dg").datagrid('reload');					
					}else{
						alert("更新失败");
					}				
				},
				error : function(xhr) {
				}
			});
		}				
	</script>




</body>
</html>