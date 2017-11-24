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
			$("#dg").datagrid('options').url ='/ddxq/admin/shop/show?typeid='+${typeid};
			$("#selectactor").combobox({ disabled: true});
			$("#selectactor").combobox('setValue',${typeid});
	    	$("#dg").datagrid('reload');
		})
</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="商品信息"  fit="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				rownumbers:true,
				toolbar: '#tb',
				url: '',
				method: 'get',
				onDblClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'caption',width:80">商品名称</th>
				<th data-options="field:'price',hidden:true,width:60">价格</th>
				<th data-options="field:'unit',width:80">价格单位</th>
				<th data-options="field:'seller',width:80,align:'center'">商家id</th>
				<th data-options="field:'stock',width:80,align:'center'">库存</th>
				<th data-options="field:'phone',width:120,align:'center'">联系电话</th>
				<th data-options="field:'menuid',width:80,align:'right'">商品分类</th>
				<th data-options="field:'docid',width:0,hidden:true,align:'center'">docid</th>
				<th data-options="field:'districtId',width:80,align:'right'">小区id</th>
				<th data-options="field:'photo_min',width:80,align:'right'">购物主图</th>
				<th data-options="field:'updated',width:80,align:'right'">更新时间</th>
				<th data-options="field:'created',width:80,align:'right'">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="height:auto">
		<c:if test="${typeid eq '2'}">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="refesh()">刷新今日优惠</a>
		</c:if>		
		<select class="easyui-combobox" id="selectactor" label="商品类型:" style="width:25%"><option value="1">全部商品</option><option value="2">今日优惠</option></select>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改信息</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加员工</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeSelect()">删除选中员工</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelectCert()">获取选中员工身份识别码</a> -->
		<c:if test="${typeid eq '1'}">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">选中商品加入今日优惠</a>
		</c:if>
		<c:if test="${typeid eq '2' }">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeSelect()">选中商品移除今日优惠</a>
		</c:if>
	</div>
 <script type="text/javascript"> 
	function append(){
		var r=confirm("是否将该商品加入到今日优惠？");
		if (r==false){
			 return false;
		 }
		var retData={};
		var row = $('#dg').datagrid('getSelected');
		retData.districtId=row.districtId;
		retData.docid=row.docid;
		retData.typeid=$("#selectactor").combobox('getValue');
		data=$.toJSON(retData);		
		$.ajax({
			url : '/ddxq/admin/shop/insertInfo',
			data : data,
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			async: false,
			success : function(data) {
				if(data.success==true){
					alert("成功加入商品到今日优惠");
					$("#dg").datagrid('reload');					
				}else{
					alert("加入商品到今日优惠失败");
				}				
			},
			error : function(xhr) {
			}
		});
	}
	function removeSelect(){
		var r=confirm("是否从今日优惠删除该商品？");
		if (r==false){
			 return false;
		 }
		var retData={};
		var row = $('#dg').datagrid('getSelected');
		retData.districtId=row.districtId;
		retData.docid=row.docid;
		retData.typeid=$("#selectactor").combobox('getValue');
		data=$.toJSON(retData);		
		$.ajax({
			url : '/ddxq/admin/shop/remove',
			data : data,
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			async: false,
			success : function(data) {
				if(data.success==true){
					alert("成功从今日优惠中删除");
					$("#dg").datagrid('reload');					
				}else{
					alert("删除失败");
				}				
			},
			error : function(xhr) {
			}
		});
	}	
	function refesh(){
		$("#dg").datagrid('reload');
	}
 </script>
 <script type="text/javascript"> 
	 	var editIndex = undefined;
		var app=false;
		function onClickCell(index, field){			
			var row=$('#dg').datagrid('getSelected');
			$('#dlg2').dialog('open');
			$("#employeeId2").textbox({ disabled: true});
			$("#employeeId2").textbox('setValue',row.employeeId);			
			$("#employeeActor2").combobox({ disabled: true});
			$("#employeeActor2").combobox('setValue',$("#selectactor").combobox('getValue'));			
			$("#districtId2").numberbox('setValue',row.districtId);
			$("#districtId2").numberbox({ disabled: true});
			$("#employeeName2").textbox('setValue',row.employeeName);
			$("#employeeNickName2").textbox('setValue',row.employeeNickName);
			$("#employeeIdCard2").numberbox('setValue',row.employeeIdCard);
			$("#employeeSex2").combobox('setValue',row.employeeSex);
			$("#employeeBirth2").datebox('setValue',row.employeeBirth);
			$("#employeeNation2").textbox('setValue',row.employeeNation);
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
	</script>
</body>	
</html>