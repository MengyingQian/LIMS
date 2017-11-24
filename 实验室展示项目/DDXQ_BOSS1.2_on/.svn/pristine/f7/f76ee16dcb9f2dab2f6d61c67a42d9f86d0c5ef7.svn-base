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
	<script src="/view/pub/basic/js/jquery-2.2.3.min.js"type="text/javascript"></script>
	<script src="/view/pub/basic/js/jquery.json.min.js"type="text/javascript"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">	
		$(function(){
			$('#dlg').dialog('close');	
			$('#dlg2').dialog('close');	
		})
</script>
</head>
<body  style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
     margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="管理员信息"  fit="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '',
				method: 'get',
				onDblClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'account',width:60">账号</th>
				<th data-options="field:'pwd',hidden:true,width:60">密码</th>
				<th data-options="field:'actorid',width:80,align:'right'">角色ID</th>		
				<th data-options="field:'name',width:80">用户名</th>
				<th data-options="field:'mobile',width:80,align:'center'">电话</th>
				<th data-options="field:'ims',width:80,align:'center'">ims</th>
				<th data-options="field:'privmod',width:80,align:'right'">模块权限</th>
				<th data-options="field:'privcate',width:80,align:'right'">分类权限</th>
				<th data-options="field:'level',width:80,align:'right'">级别</th>
				<th data-options="field:'created',width:150,align:'center'">创建时间</th>
				<th data-options="field:'updated',width:150,align:'center'">更新时间</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
				<th data-options="field:'memo',width:0,hidden:true,align:'center'">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">		
		<select class="easyui-combobox"id="selectactor" label="选择管理员类型" style="width:25%"><option value="1" selected="selected">总管理员</option><option value="2" >运营管理员</option></select>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查看管理员</a>
		<br/>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加管理员</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeSelect()">删除选中</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改</a>
	</div>
<script type="text/javascript">
		var editIndex = undefined;
		var app=false;
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');
			$("#account2").textbox('setValue',row.account);
			$("#account2").textbox({ disabled: true});
			$("#actorid2").combobox('setValue',$("#selectactor").combobox('getValue'));
			$("#seller2").textbox('setValue',row.seller);
			$("#mobile2").textbox('setValue',row.mobile);
			$("#name2").textbox('setValue',row.name);
			$("#ims2").textbox('setValue',row.ims);	
			$("#privmod2").textbox('setValue',row.privmod);
			$("#privcate2").textbox('setValue',row.privcate);
			$("#level2").textbox('setValue',row.level);
			$("#status2").textbox('setValue',row.status);	
			$("#memo2").textbox('setValue',row.memo);	
		}
		function getData2(){
			var retData = {};
			if($("#ff2").form('enableValidation').form('validate')==false){
				return false;
			}
			var password =$("#password2").textbox('getValue');
			var passwordd =$("#passwordd2").textbox('getValue');
			if( password != passwordd){
				alert("两次输入密码不一致，请重新输入");
				return false;
			}
			retData.account=$("#account2").textbox('getValue');	
			retData.password=$("#password2").textbox('getValue');			
			retData.actorid=$("#actorid2").combobox('getValue');			
			retData.mobile=$("#mobile2").numberbox('getValue');
			retData.name=$("#name2").textbox('getValue');
			retData.ims=$("#ims2").textbox('getValue');
			retData.privmod=$("#privmod2").textbox('getValue');
			retData.privcate=$("#privcate2").textbox('getValue');			
			retData.level=$("#level2").numberbox('getValue');			
			retData.status=$("#status2").numberbox('getValue');	
			retData.memo=$("#memo2").textbox('getValue');
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
				url : '/ddxq/system/poweruser/remove',
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
				<input class="easyui-textbox"id="account" name="account" style="width:90%" data-options="label:'账号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="password"name="password" style="width:90%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passwordd"style="width:90%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="actorid" label="选择管理用户类型" style="width:90%"><option value="1">总管理员</option><option value="2">运营管理员</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="mobile"name="mobile" style="width:90%" data-options="label:'电话:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="name"name="name" style="width:90%" data-options="label:'用户名:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="ims"name="ims" style="width:90%" data-options="label:'ims:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privmod"name="privmod" style="width:90%" data-options="label:'模块权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privcate" name="privcate" style="width:90%" data-options="label:'分类权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="level" name="level" style="width:90%" data-options="label:'级别:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="status" name="status" style="width:90%" data-options="label:'状态:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="memo" name="memo" style="width:90%" data-options="label:'备注:'">
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
			var password =$("#password").textbox('getValue');
			var passwordd =$("#passwordd").textbox('getValue');
			if( password != passwordd){
				alert("两次输入密码不一致，请重新输入");
				return false;
			}
			retData.account=$("#account").textbox('getValue');	
			retData.password=$("#password").textbox('getValue');			
			retData.actorid=$("#actorid").combobox('getValue');			
			retData.mobile=$("#mobile").numberbox('getValue');
			retData.name=$("#name").textbox('getValue');
			retData.ims=$("#ims").textbox('getValue');
			retData.privmod=$("#privmod").textbox('getValue');
			retData.privcate=$("#privcate").textbox('getValue');			
			retData.level=$("#level").numberbox('getValue');			
			retData.status=$("#status").numberbox('getValue');	
			retData.memo=$("#memo").textbox('getValue');	
			return retData;
		}
		function submitForm(){
			var retData=getData();
			$.ajax({
				url : '/ddxq/system/poweruser/insertInfo',
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
			var actorid=$("#selectactor").combobox('getValue');
			$("#dg").datagrid('options').url ='/ddxq/system/poweruser/search?actorid='+actorid;
	    	$("#dg").datagrid('reload');
			
		}
		
	</script>
	<div id="dlg2" class="easyui-dialog" title="修改管理员信息" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff2" method="post">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox"id="account2" name="account" style="width:90%" data-options="label:'账号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="password2"name="password" style="width:90%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passwordd2"style="width:90%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="actorid2" label="选择管理用户类型" style="width:90%"><option value="1">总管理员</option><option value="2">运营管理员</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="mobile2"name="mobile" style="width:90%" data-options="label:'电话:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="name2"name="name" style="width:90%" data-options="label:'用户名:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="ims2"name="ims" style="width:90%" data-options="label:'ims:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privmod2"name="privmod" style="width:90%" data-options="label:'模块权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privcate2" name="privcate" style="width:90%" data-options="label:'分类权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="level2" name="level" style="width:90%" data-options="label:'级别:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="status2" name="status" style="width:90%" data-options="label:'状态:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="memo2" name="memo" style="width:90%" data-options="label:'备注:'">
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
				url : '/ddxq/system/poweruser/edit',
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