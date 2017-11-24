<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/view/pub/basic/css/demo.css">
	<script type="text/javascript"src="/view/pub/basic/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/js/jquery.json.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	String.prototype.startWith=function(str){
		if(str==null||str==""||this.length==0||str.length>this.length)
		  return false;
		if(this.substr(0,str.length)==str)
		  return true;
		else
		  return false;
		return true;
		}
	$(function(){
		function showQRPicture(pictureUrl,ticket,districtId){
			var url="";
			if(pictureUrl.startWith("http")){
				url=pictureUrl;
			}else{
				var host=$("#host").val();
				url="http://"+host+pictureUrl;
			}
			
			$('#showDistrictId').text(' '+districtId);
			$('#img').attr('src',url);
			$("#ticket").text(ticket);
			$("#qrcode").text(pictureUrl);
			$('#dlg').dialog('open');
		}
		$('#dlg').dialog('close');
		$('#dg').datagrid({
			onDblClickRow:function(index,data){
			var pictureUrl=data.qrCodeURL;
			var ticket=data.ticket;
			var districtId=data.districtId;
			var row=$('#dg').datagrid('getSelected');
			select_ticke_id = row.id;
			if(row){
				showQRPicture(pictureUrl,ticket,districtId);
			}
			}
		});
		var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
		pager.pagination({			
			pageSize : 20,
			pageList: [ 20, 40],
			onSelectPage: function (pageNumber, pageSize) {
				notify(pageNumber, pageSize);
			},
		});
		function notify(pageNumber, pageSize){
			var total=$('#dg').datagrid('getData').total;
			$("#dg").datagrid('options').url ='/ddxq/system/qrcodeManage/pageData?pageNumber='+pageNumber+'&pageSize='+pageSize+'&total='+total;
        	$("#dg").datagrid('reload');
		}

	})
		function searchData() {
			var districtIdFrom=$("#districtIdFrom").val();
			var districtIdTo=$("#districtIdTo").val();
			var dateFrom=$("#dateFrom").datebox('getValue');
			var dateTo=$("#dateTo").datebox('getValue');
			$("#dg").datagrid('options').url ='/ddxq/system/qrcodeManage/searchData?districtIdFrom='+districtIdFrom+'&districtIdTo='+districtIdTo+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
        	$("#dg").datagrid('reload');
			//alert($("#dg").datagrid('getPager').data("pagination").options.pageSize+" "+$("#dg").datagrid('getPager').data("pagination").options.pageNumber);
			//alert($("#dg").datagrid('options').pageNumber);
			//$("#dg").datagrid('getPager').data("pagination").options.pageSize=20;
			//alert($("#dg").datagrid('getPager').data("pagination").options.pageSize);
		}
		function genData() {
			var districtIdFrom=$("#districtIdFrom").val();
			var districtIdTo=$("#districtIdTo").val();
			$("#dg").datagrid('options').url ='/ddxq/system/qrcodeManage/genData?districtIdFrom='+districtIdFrom+'&districtIdTo='+districtIdTo;
        	$("#dg").datagrid('reload');
		}
		function deleteData() {
			var districtIdFrom=$("#districtIdFrom").val();
			var districtIdTo=$("#districtIdTo").val();
			var dateFrom=$("#dateFrom").datebox('getValue');
			var dateTo=$("#dateTo").datebox('getValue');
			$("#dg").datagrid('options').url ='/ddxq/system/qrcodeManage/deleteData?districtIdFrom='+districtIdFrom+'&districtIdTo='+districtIdTo+"&dateFrom="+dateFrom+"&dateTo="+dateTo;
        	$("#dg").datagrid('reload');
		}
		function noteData() {
			var districtIdFrom=$("#districtIdFrom2").val();
			var districtIdTo=$("#districtIdTo2").val();
			var note=$("#note").val();
			$("#dg").datagrid('options').url ='/ddxq/system/qrcodeManage/noteData?districtIdFrom='+districtIdFrom+'&districtIdTo='+districtIdTo+"&note="+note;
        	$("#dg").datagrid('reload');
		}

	</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
	<table id="dg" class="easyui-datagrid" title="小区二维码信息"  fit="true"
			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/ddxq/system/qrcodeManage/showQRcodeGrid',method:'get',toolbar:'#tb',footer:'#ft'">
		<thead>
			<tr>
				<th data-options="field:'id',width:0,hidden:true">序号</th>
				<th data-options="field:'districtId',width:60">小区编号</th>
				<th data-options="field:'ticket',width:90">授权码</th>
				<th data-options="field:'qrCodeURL',width:160">二维码地址</th>
				<th data-options="field:'created',width:150">创建时间</th>
				<th data-options="field:'updated',width:150">更新时间</th>
				<th data-options="field:'notes',width:60">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="小区二维码 " data-options="iconCls:'icon-tip'" style="width:500px;height:440px">
		小区编号： <a id="showDistrictId"></a>
		<img id="img" alt="error" src="" width="280"></br>
		ticket：<p id="ticket"></p>	
		二维码地址：<p id="qrcode"></p>	
	</div>
	<div id="tb" style="padding:2px 5px;">
		小区编号: <input type="text" id="districtIdFrom" class="easyui-textbox" style="width:50px">
		至: <input type="text" id="districtIdTo" class="easyui-textbox" style="width:50px">
		创建时间: <input  type="text" id="dateFrom" class="easyui-datebox"  style="width:90px">
		至: <input type="text" id="dateTo" class="easyui-datebox" style="width:90px">		
		<a href="javascript:void(0);" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a href="javascript:void(0);" onclick="genData()" class="easyui-linkbutton" iconCls="icon-add">生成</a>
		<a href="javascript:void(0);" onclick="deleteData();" class="easyui-linkbutton" iconCls="icon-clear">删除</a>
	</div>
	<input type="hidden" id="host" name="host" value="${host}"/>
</body>
</html>