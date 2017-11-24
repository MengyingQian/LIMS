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
		$('#dlg').dialog('close');	
		$('#dlg2').dialog('close');	
		$("#districtId").numberbox('setValue',$("#dist").val());
		$("#districtId").numberbox({ disabled: true});
		$("#districtIdFrom").numberbox('setValue',$("#dist").val());
		$("#districtIdFrom").numberbox({ disabled: true});
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
	<table id="dg" class="easyui-datagrid" title="添加消息"  fit="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '',
				method: 'get',
				onDblClickCell: onClickCell,
			">

		<thead>
			<tr>
				<th data-options="field:'districtId',width:60">小区编号</th>
				<th data-options="field:'column',width:80">消息类型</th>
				<th data-options="field:'title',width:120,align:'center'">标题</th>
				<th data-options="field:'author',width:80,align:'center'">作者</th>	
				<th data-options="field:'publisher',width:80,align:'center'">发布者</th>	
				<th data-options="field:'subtitle',width:80,align:'center'">摘要</th>		
				<th data-options="field:'content',width:260,hidden:true,nowarp:true,align:'center'">内容</th>
				<th data-options="field:'link',width:60,align:'center'">链接</th>	
				<th data-options="field:'created',width:150,align:'center'">创建时间</th>
				<th data-options="field:'updated',width:150,align:'center'">更新时间</th>				
				<th data-options="field:'truecreated',width:0,hidden:true,align:'center'"></th>
				<th data-options="field:'trueupdated',width:0,hidden:true,align:'center'"></th>
				<th data-options="field:'docid',width:0,hidden:true,align:'center'"></th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">
		小区编号: <input type="text" id="districtIdFrom" class="easyui-numberbox" style="width:50px">		
		<select class="easyui-combobox"id="selectnews" label="选择消息类型" style="width:30%"><option value="wytz">物业通知</option><option value="xmqs">寻觅启示</option><option value="sqgg">社区公告</option><option value="xqhd">小区活动</option><option value="stzz">社团组织</option><option value="yewh">业委会</option><option value="juwh">居委会</option><option value="pchs">派出所</option></select>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查询小区公告</a>
		<br/>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加消息</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelect()">删除选中行</a>
	</div>
	
	<script type="text/javascript">
		var editIndex = undefined;
		var app=false;
		
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');	
			$("#districtId2").numberbox('setValue',row.districtId);
			$("#districtId2").numberbox({ disabled: true});
			$("#info2").combobox({ disabled: true});
			$("#info2").combobox('setValue',$("#selectnews").combobox('getValue'));
			$("#title2").textbox('setValue',row.title);
			$("#author2").textbox('setValue',row.author);
			$("#publisher2").textbox('setValue',row.publisher);
			$("#subtitle2").textbox('setValue',row.subtitle);
			$("#link2").textbox('setValue',row.link);		
			$("#content2").textbox('setValue',row.content);
			$("#docid").textbox('setValue',row.docid);
			$("#created2").textbox('setValue',row.truecreated);	

		}

		function onEndEdit(index, row){
			var ed = $(this).datagrid('getEditor', {
				index: index,
				field: 'productid'
			});
			row.productname = $(ed.target).combobox('getText');
			
		}
		function append(){
			$('#dlg').dialog('open');	
			$("#districtId").numberbox('setValue',$("#dist").val());
			$("#districtId").numberbox({ disabled: true});
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			
			}
			app=true;
		}
		function removeit(){
			var row=$('#dg').datagrid('selectRow', 0);
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
			
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');	
		}				
		function getSelect(){
			var retData = {};
			var row = $('#dg').datagrid('getSelected');		
			retData.districtId=row.districtId;
			retData.info=$("#selectnews").combobox('getValue');
			retData.docid=row.docid;
			dat=$.toJSON(retData);
			$.ajax({
				url : '/ddxq/admin/xxl/xwzx/removeData',
				data : dat,
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
						alert("添加失败");
					}				
				},
				error : function(xhr) {
				}
			});			
		}
		function searchData(){
			var districtId=$("#districtIdFrom").numberbox('getValue');
			var column=$("#selectnews").combobox('getValue');
			if(!districtId.length){
				alert("请输入小区编号");
				return false;
			}
			$("#dg").datagrid('options').url ='/ddxq/admin/xxl/xwzx/searchData?districtIdFrom='+districtId+"&column="+column;
	    	$("#dg").datagrid('reload');
			
		}
	</script>
	<div id="dlg" class="easyui-dialog" title="添加消息" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">	
		<form id="ff" method="post">
			<div style="margin-bottom:10px;margin-top:10px">
				<input class="easyui-numberbox" id="districtId"name="districtId" style="width:90%" data-options="label:'小区编号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="info" name="info" label="选择消息类型" style="width:90%"><option value="wytz">物业通知</option><option value="xmqs">寻觅启示</option><option value="sqgg">社区公告</option><option value="xqhd">小区活动</option><option value="stzz">社团组织</option><option value="yewh">业委会</option><option value="juwh">居委会</option><option value="pchs">派出所</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="title"name="title" style="width:90%" data-options="label:'标题:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="subtitle"name="subtitle" style="width:90%" data-options="label:'摘要:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="author"name="author" style="width:90%" data-options="label:'作者:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="publisher"name="publisher" style="width:90%" data-options="label:'发布者:'">
			</div>
				<div style="margin-bottom:10px;margin-top:10px">
				<input class="easyui-numberbox" id="score"name="score" style="width:90%" data-options="label:'排序号:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="link" name="link" style="width:90%" data-options="label:'超连接:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="content"name="content" style="width:90%;height:140px" data-options="label:'内容:',prompt:'请用内容编辑器',multiline:true,required:true">
			</div>
			
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">添加消息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="scanForm()" style="width:80px">预览消息</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a> -->
		</div>
	<script>
		function getData(){
			var retData = {};
			retData.districtId=$("#districtId").numberbox('getValue');	
			retData.score=$("#score").numberbox('getValue');	
			retData.info=$("#info").combobox('getValue');			
			retData.title=$("#title").textbox('getValue');
			retData.publisher=$("#publisher").textbox('getValue');
			retData.subtitle=$("#subtitle").textbox('getValue');	
			retData.author=$("#author").textbox('getValue');			
			retData.link=$("#link").textbox('getValue');			
			retData.content=$("#content").textbox('getValue');			
			return retData;
		}
		function submitForm(){
			var retData=getData();
			if($("#ff").form('enableValidation').form('validate')==false){
				return false;
			}
			$.ajax({
				url : '/ddxq/admin/xxl/xwzx/insertNotice',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$('#dlg').dialog('close');
						$('#ff').form('clear');
						alert("插入成功");
						$("#districtIdFrom").numberbox('setValue',data.districtId);
						$("#selectnews").combobox('setValue',data.info);
						$("#dg").datagrid('options').url = data.url;
						$("#dg").datagrid('reload');					
					}else{
						alert("插入失败,每日单个类别的消息最多只能添加个消息。");
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
	<script>
    function openNewSpecifiedWindow( windowName ){
     window.open('about:blank',windowName,'width=700,height=800,menubar=no,scrollbars=no');
    }
    function scanForm(){
    	var id=$("#districtId").numberbox('getValue');
    	if(id==""){
    		alert("小区编号不能为空");
    		return false;
    	}
    	$("#h1").val(id);
    	$("#h2").val($("#info").combobox('getValue'));
    	$("#h3").val($("#title").textbox('getValue'));
    	$("#h4").val($("#author").textbox('getValue'));
    	$("#h5").val($("#link").textbox('getValue'));
    	$("#h6").val($("#content").textbox('getValue'));
    	$("#h7").val($("#publisher").textbox('getValue'));
    	$("#h8").val($("#subtitle").textbox('getValue'));
    	$('#editForm').submit();
    }
   </script>

<form id="editForm" name="editForm" method="post" action="/ddxq/admin/xxl/xwzx/testNotice" target="newWindow" onsubmit="openNewSpecifiedWindow('newWindow')">
  <input type="hidden" id="h1" name="districtId" value="" />
  <input type="hidden" id="h2" name="info" value="" />
  <input type="hidden" id="h3" name="title" value="" />
  <input type="hidden" id="h4" name="author" value="" />
  <input type="hidden" id="h5" name="link" value="" />
  <input type="hidden" id="h6" name="content" value="" />  
  <input type="hidden" id="h7" name="publisher" value="" />  
  <input type="hidden" id="h8" name="subtitle" value="" />  
</form>
</div>
	<div id="dlg2" class="easyui-dialog" title="添加消息" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">	
		<form id="ff2" method="post">
			<div style="margin-bottom:10px;margin-top:10px">
				<input class="easyui-numberbox" id="districtId2"name="districtId" style="width:90%" data-options="label:'小区编号:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<select class="easyui-combobox"id="info2" name="info" label="选择消息类型" style="width:90%"><option value="wytz">物业通知</option><option value="xmqs">寻觅启示</option><option value="sqgg">社区公告</option><option value="xqhd">小区活动</option></select>
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="title2"name="title" style="width:90%" data-options="label:'标题:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="subtitle2"name="subtitle" style="width:90%" data-options="label:'摘要:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="author2"name="author" style="width:90%" data-options="label:'作者:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="publisher2"name="publisher" style="width:90%" data-options="label:'发布者:',required:true">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="link2" name="link" style="width:90%" data-options="label:'超连接:'">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" id="content2"name="content" style="width:90%;height:140px" data-options="label:'内容:',prompt:'请用内容编辑器',multiline:true,required:true">
			</div>
			<input class="easyui-textbox" type="hidden" id="docid" name="docid" value="" />	
			<input class="easyui-textbox" type="hidden" id="created2" name="created" value="" />			
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="subEdit()" style="width:80px">更改消息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="scanForm2()" style="width:80px">预览消息</a>
		<!-- 	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm2()" style="width:80px">清空</a> -->
		</div>
		<script> 
		function getData2(){
			var retData = {};
			retData.districtId=$("#districtId2").numberbox('getValue');	
			retData.info=$("#info2").combobox('getValue');
			retData.publisher=$("#publisher2").textbox('getValue');
			retData.title=$("#title2").textbox('getValue');	
			retData.subtitle=$("#subtitle2").textbox('getValue');	
			retData.author=$("#author2").textbox('getValue');			
			retData.link=$("#link2").textbox('getValue');		
			retData.content=$("#content2").textbox('getValue');			
			retData.docid=$("#docid").textbox('getValue');
			retData.created2=$("#created2").textbox('getValue');
			return retData;
		}
		function subEdit(){
			var retData=getData2();
			$.ajax({
				url : '/ddxq/admin/xxl/xwzx/editNotice',
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
		function clearForm2(){
			$('#ff2').form('clear');
		}
		  function scanForm2(){
		    	var id=$("#districtId2").numberbox('getValue');
		    	if(id==""){
		    		alert("小区编号不能为空");
		    		return false;
		    	}
		    	$("#h1").val(id);
		    	$("#h2").val($("#info2").combobox('getValue'));
		    	$("#h3").val($("#title2").textbox('getValue'));
		    	$("#h4").val($("#author2").textbox('getValue'));
		    	$("#h5").val($("#link2").textbox('getValue'));
		    	$("#h6").val($("#content2").textbox('getValue'));
		     	$("#h7").val($("#publisher2").textbox('getValue'));
		    	$("#h8").val($("#subtitle2").textbox('getValue'));
		    	$('#editForm').submit();
		    }
		
	</script>
	</div>
	<input type="hidden" id="dist"name="dist" value="${districtId}">
</body>
</html>