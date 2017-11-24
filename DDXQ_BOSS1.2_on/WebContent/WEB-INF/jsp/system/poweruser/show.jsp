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
	<table id="dg" class="easyui-datagrid" title="论文信息"  fit="true"
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
				<th data-options="field:'paper_id',width:60">id</th>
				<th data-options="field:'title',width:360,align:'center'">题目</th>
				<th data-options="field:'author',width:80,align:'center'">作者</th>		
				<th data-options="field:'publish_year',width:80,align:'center'">年份</th>
				<th data-options="field:'publish_month_date',width:80,align:'center'">月份</th>
				<th data-options="field:'department',width:80,align:'center'">发行商</th>
				<th data-options="field:'url',width:80,align:'center'">url</th>
			<!-- 	<th data-options="field:'privcate',width:80,align:'right'">分类权限</th>
				<th data-options="field:'level',width:80,align:'right'">级别</th> -->
				<th data-options="field:'create_time',width:150,align:'center'">创建时间</th>
				<th data-options="field:'update_time',width:150,align:'center'">更新时间</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
				<!-- <th data-options="field:'memo',width:0,hidden:true,align:'center'">备注</th> -->
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">		
		<select class="easyui-combobox"id="selectactor" label="请选择年份" style="width:25%"><option value="2009" selected="selected">2009</option><option value="2008" >2008</option><option value="2010" >2010</option><option value="2011" >2011</option><option value="2012" >2012</option><option value="2013" >2013</option><option value="2014" >2014</option><option value="2015" >2015</option><option value="2016" >2016</option><option value="2017" >2017</option></select>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查找论文</a>
		<br/>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加新文章</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeSelect()">删除选中</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改</a>
	</div>
<script type="text/javascript">
		var editIndex = undefined;
		var app=false;
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');
			//$("#paper_id").textbox('setValue',row.title);
			$("#title2").textbox('setValue',row.title);
// 			$("#account2").textbox({ disabled: true});
// 			$("#actorid2").combobox('setValue',$("#selectactor").combobox('getValue'));
			$("#author2").textbox('setValue',row.author);
			$("#publish_year2").textbox('setValue',row.publish_year);
			$("#publish_month_date2").textbox('setValue',row.publish_month_date);
			$("#department2").textbox('setValue',row.department);	
			$("#url2").textbox('setValue',row.url);
			/* $("#privcate2").textbox('setValue',row.privcate);
			$("#level2").textbox('setValue',row.level); */
			$("#status2").textbox('setValue',row.status);	
// 			$("#memo2").textbox('setValue',row.memo);	
		}
		function getData2(){
			var retData = {};
			if($("#ff2").form('enableValidation').form('validate')==false){
				return false;
			}
			/* var password =$("#password2").textbox('getValue');
			var passwordd =$("#passwordd2").textbox('getValue');
			if( password != passwordd){
				alert("两次输入密码不一致，请重新输入");
				return false;
			} */
			retData.paper_id=$('#dg').datagrid('getSelected').paper_id;	
			retData.title=$("#title2").textbox('getValue');	
			retData.author=$("#author2").textbox('getValue');			
// 			retData.actorid=$("#actorid2").combobox('getValue');			
			retData.publish_year=$("#publish_year2").numberbox('getValue');
			retData.publish_month_date=$("#publish_month_date2").textbox('getValue');
			retData.department=$("#department2").textbox('getValue');
			retData.url=$("#url2").textbox('getValue');
// 			retData.privcate=$("#privcate2").textbox('getValue');			
// 			retData.level=$("#level2").numberbox('getValue');			
			retData.status=$("#status2").numberbox('getValue');	
// 			retData.memo=$("#memo2").textbox('getValue');
           
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
			retData.paper_id=row.paper_id;
			retData.publish_year=$("#selectactor").combobox('getValue');
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
<div id="dlg" class="easyui-dialog" title="添加新的论文" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff" method="post">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox"id="title" name="title" style="width:90%" data-options="label:'题目:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="author"name="author" style="width:90%" data-options="label:'作者:',required:true">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passwordd"style="width:90%" data-options="label:'密码:',required:true">
			</div> -->
			<!-- <div style="margin-bottom:10px">
				<select class="easyui-combobox"id="actorid" label="选择管理用户类型" style="width:90%"><option value="1">总管理员</option><option value="2">运营管理员</option></select>
			</div> -->
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="publish_year"name="publish_year" style="width:90%" data-options="label:'年份:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="publish_month_date"name="publish_month_date" style="width:90%" data-options="label:'月份:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="department"name="department" style="width:90%" data-options="label:'发行商:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="url"name="url" style="width:90%" data-options="label:'url:'">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privcate" name="privcate" style="width:90%" data-options="label:'分类权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="level" name="level" style="width:90%" data-options="label:'级别:'">
			</div> -->
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="status" name="status" style="width:90%" data-options="label:'状态:',required:true">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="memo" name="memo" style="width:90%" data-options="label:'备注:'">
			</div> -->
					
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
		/* 	var password =$("#password").textbox('getValue');
			var passwordd =$("#passwordd").textbox('getValue');
			if( password != passwordd){
				alert("两次输入密码不一致，请重新输入");
				return false;
			} */
			retData.title=$("#title").textbox('getValue');	
			retData.author=$("#author").textbox('getValue');			
// 			retData.actorid=$("#publish_year").combobox('getValue');			
			retData.publish_year=$("#publish_year").numberbox('getValue');
			retData.publish_month_date=$("#publish_month_date").textbox('getValue');
			retData.department=$("#department").textbox('getValue');
			retData.url=$("#url").textbox('getValue');
		/* 	retData.privcate=$("#privcate").textbox('getValue');			
			retData.level=$("#level").numberbox('getValue');	 */		
			retData.status=$("#status").numberbox('getValue');	
// 			retData.memo=$("#memo").textbox('getValue');	
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
	<div id="dlg2" class="easyui-dialog" title="修改论文信息" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff2" method="post">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox"id="title2" name="title" style="width:90%" data-options="label:'题目:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="author2"name="author" style="width:90%" data-options="label:'作者:',required:true">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="passwordd2"style="width:90%" data-options="label:'密码:',required:true">
			</div> -->
			<!-- <div style="margin-bottom:10px">
				<select class="easyui-combobox"id="actorid2" label="选择管理用户类型" style="width:90%"><option value="1">总管理员</option><option value="2">运营管理员</option></select>
			</div> -->
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="publish_year2"name="publish_year" style="width:90%" data-options="label:'年份:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="publish_month_date2"name="publish_month_date" style="width:90%" data-options="label:'月份:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="department2"name="department" style="width:90%" data-options="label:'发行商:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="url2"name="url" style="width:90%" data-options="label:'url:'">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="privcate2" name="privcate" style="width:90%" data-options="label:'分类权限:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="level2" name="level" style="width:90%" data-options="label:'级别:'">
			</div> -->
			<div style="margin-bottom:10px">
				<input class="easyui-numberbox" id="status2" name="status" style="width:90%" data-options="label:'状态:'">
			</div>
			<!-- <div style="margin-bottom:10px">
				<input class="easyui-textbox" id="memo2" name="memo" style="width:90%" data-options="label:'备注:'">
			</div> -->
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