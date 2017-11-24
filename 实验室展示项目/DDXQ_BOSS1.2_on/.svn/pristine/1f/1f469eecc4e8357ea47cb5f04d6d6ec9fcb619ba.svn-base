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
			$('#dg').datagrid({
				rowStyler:function(index,row){
					if (row.level == 1){
						return 'color:blue;font-weight:bold;';/* return 'background-color:pink;color:blue;font-weight:bold;'; */
					}
				}
			});
			$('#dlg').dialog('close');	
			$('#dlg2').dialog('close');	
			$("#dg").datagrid('options').url ='/ddxq/system/dictionary/getmenu';
	    	$("#dg").datagrid('reload');
	    	/* $("#selectactor").combobox({ disabled: true}); */
		})
</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="销售商品菜单类别"  fit="true" data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '',
				method: 'get',
				onDblClickCell: onClickCell,
			">
			<thead>
				<tr> 
					<th data-options="field:'id',width:0,hidden:true,">类别ID</th>
					<th data-options="field:'cateid',width:90">类别编码</th>
					<th data-options="field:'catename',width:90,align:'center'">类别名称</th>
					<th data-options="field:'level',width:90,align:'center'">类别级数</th>
					<th data-options="field:'ordernum',width:90,align:'center'">展示顺序</th>
					<th data-options="field:'created',width:90,align:'center'">更新时间</th>
					<th data-options="field:'updated',width:90,align:'center'">更新时间</th>
					<th data-options="field:'status',width:90,align:'center'">状态</th>
					<th data-options="field:'memo',width:90,align:'center'">备注</th>
				</tr>
			</thead>
		</table>
	<div id="tb" style="height:auto">
<!-- 			小区编号: <input type="text" id="districtIdFrom" class="easyui-numberbox" style="width:50px">
			商品编号: <input type="text" id="subcateid" class="easyui-numberbox" style="width:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查询销售商品</a> -->
			<br/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加菜单类别</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelect()">删除菜单类别</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改菜单类别</a>
		</div>
		<div id="dlg" class="easyui-dialog" title="添加菜单类别" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">
			<form id="ff" method="post">
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="catename" name="catename" style="width:90%" data-options="label:'类别名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="cateid" name="cateid" style="width:90%" data-options="label:'类别编码:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input  class="easyui-numberbox" id="level" name="level" style="width:90%" data-options="label:'类别级别:',required:true" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="ordernum" name="ordernum" style="width:90%" data-options="label:'展示顺序:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="status" name="status" style="width:90%" data-options="label:'状态 1可用:',required:true">
				</div>				
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="memo" name="memo" style="width:90%;height:140px" data-options="label:'菜单备注:',multiline:true,required:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">添加类别</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
			<script type="text/javascript">
				function getData() {
					var retData = {};
					retData.cateid = $("#cateid").textbox('getValue');	
					retData.catename = $("#catename").textbox('getValue');
					retData.level = $("#level").numberbox('getValue');
					retData.ordernum = $("#ordernum").numberbox('getValue');
					retData.status = $("#status").textbox('getValue');
					retData.memo = $("#memo").textbox('getValue');
					return retData;
				}
				function submitForm() {
					var retData = getData();
					$.ajax({
						url: '/ddxq/system/dictionary/insertInfo',
						data: $.toJSON(retData),
						type: 'post',
						dataType: 'json',
						contentType: 'application/json',
						cache: false,
						async: false,
						success: function(data) {
							if(data.success == true) {
								$('#dlg').dialog('close');
								$('#ff').form('clear');
								alert("插入成功");
								/* $("#districtIdFrom").numberbox('setValue', data.districtId); */
								$("#dg").datagrid('options').url = data.url;
								$("#dg").datagrid('reload');
							} else {
								alert("插入失败");
							}
						},
						error: function(xhr) {}
					});
				}

				function clearForm() {
				/* 	$('#ff').form('clear'); */
					$(':input','#ff') 
					.not(':button, :submit, :reset, :hidden,:disabled') 
					.val('') 
				}
			</script>
			<script  type="text/javascript">
				function openNewSpecifiedWindow(windowName) {
					window.open('about:blank', windowName, 'width=700,height=800,menubar=no,scrollbars=no');
				}

				function scanForm() {
					var id = $("#districtId").numberbox('getValue');
					if(id == "") {
						alert("小区编号不能为空");
						return false;
					}
					$("#h1").val(id);
					$("#h2").val($("#newscenter").combobox('getValue'));
					$("#h3").val($("#title").textbox('getValue'));
					$("#h4").val($("#author").textbox('getValue'));
					$("#h5").val($("#link").textbox('getValue'));
					$("#h6").val($("#content").textbox('getValue'));
					$('#editForm').submit();
				}
			</script>

			<form id="editForm" name="editForm" method="post" action="/ddxq/boss/xxl/xwzx/testNotice" target="newWindow" onsubmit="openNewSpecifiedWindow('newWindow')">
				<input type="hidden" id="h1" name="districtId" value="" />
				<input type="hidden" id="h2" name="newscenter" value="" />
				<input type="hidden" id="h3" name="title" value="" />
				<input type="hidden" id="h4" name="author" value="" />
				<input type="hidden" id="h5" name="link" value="" />
				<input type="hidden" id="h6" name="content" value="" />
			</form>
		</div>
	<div id="dlg2" class="easyui-dialog" title="修改菜单" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff2" method="post">
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="catename2" name="catename" style="width:90%" data-options="label:'类别名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="cateid2" name="cateid" style="width:90%" data-options="label:'类别编码:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input  class="easyui-numberbox" id="level2" name="level" style="width:90%" data-options="label:'类别级别:',required:true" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="ordernum2" name="ordernum" style="width:90%" data-options="label:'展示顺序:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="status2" name="status" style="width:90%" data-options="label:'状态 :',required:true">
				</div>				
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="memo2" name="memo" style="width:90%;height:140px" data-options="label:'菜单备注:',multiline:true,required:true">
				</div>
					<input type="hidden" id="id2" name="id" >
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()" style="width:80px">更新</a>
		</div>
	</div>
	<script type="text/javascript">
		var editIndex = undefined;
		var app=false;
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');	
			$("#catename2").textbox('setValue',row.catename);
			$("#cateid2").textbox('setValue',row.cateid);
			$("#level2").textbox('setValue',row.level);
			$("#ordernum2").numberbox('setValue',row.ordernum);
			$("#status2").textbox('setValue',row.status);
			$("#memo2").textbox('setValue',row.memo);
			$("#id2").val(row.id);
		}
		function getData2(){
			var retData = {};
			retData.catename=$("#catename2").textbox('getValue');
			retData.cateid=$("#cateid2").textbox('getValue');
			retData.level=$("#level2").textbox('getValue');
			retData.ordernum=$("#ordernum2").numberbox('getValue');	
			retData.status=$("#status2").numberbox('getValue');
			retData.memo=$("#memo2").textbox('getValue');	
			retData.id=$("#id2").val();
			return retData;
		}
		function append(){
			$('#dlg').dialog('open');	
		}
		function getSelect(){
			var r=confirm("是否删除选中的商品？");
			if (r==false){
				 return false;
			 }
			var retData={};
			var rows = $('#dg').datagrid('getSelected');
			retData.id = rows.id;
			data=$.toJSON(retData);
			$('#dg').datagrid('clearSelections');
			$.ajax({
				url : '/ddxq/system/dictionary/removeInfo',
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
		function searchData(){
			var districtId=$("#districtIdFrom").numberbox('getValue');
			var cateid=$("#subcateid").numberbox('getValue');
			if(cateid==""){
				cateid=-1;
			}
			if(!districtId.length){
				alert("请输入小区编号");
				return false;
			}
			$("#dg").datagrid('options').url ='/ddxq/seller/xqcs/searchData?districtIdFrom='+districtId+"&cateid="+cateid;
	    	$("#dg").datagrid('reload');
			
		}
	</script>
	<script> 
		function submitEdit(){
			var retData=getData2();
			$.ajax({
				url : '/ddxq/system/dictionary/editInfo',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$('#dlg2').dialog('close');
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