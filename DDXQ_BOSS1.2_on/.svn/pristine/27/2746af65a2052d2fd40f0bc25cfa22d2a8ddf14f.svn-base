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
		$('#dlg').dialog('close');
		$('#dlg2').dialog('close');	
	});
	function addData() {
		var retData = {};
		var check=$("#ff").form('enableValidation').form('validate');
		if(check ==false){
			return false;
		}
		retData.districtId=$('#districtId').val();
		retData.areacode=$('#areacode').val();
		retData.name=$('#name').val();
		retData.alias=$('#alias').val();		
		retData.description=$('#description').val();
		//retData.qrcodeURL=$('#description').val();
		//retData.ticket=$('#ticket').val();
		retData.address=$('#address').val();
		retData.postcode=$('#postcode').val();
		retData.phone=$('#phone').val();
		retData.site=$('#site').val();
		retData.busroute=$('#busroute').val();
		retData.qualitiy=$('#qualitiy').val();
		retData.category=$('#category').val();
		retData.level=$('#level').val();
		//retData.longitude=$('#longitude').val();
		//retData.latitude=$('#latitude').val();
		retData.weathercode=$("#weathercode").val();
		retData.tags=$('#tags').val();
		retData.notes=$('#notes').val();
		$.ajax({
			url : '/ddxq/system/districtManage/addDistrictInfo',
			data : $.toJSON(retData),
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			success : function(data) {
				if(data.success==true){
					$('#dlg').dialog('close');
					alert("添加成功");
					$("#dg").datagrid('options').url = data.url;
					$("#dg").datagrid('reload');					
				}else{
					alert("添加失败");
				}				
			},
			error : function(xhr) {
			}
		});
		return false;
	}
	function searchData(){
		var districtIdFrom=$("#districtIdFrom").val();
		var districtIdTo=$("#districtIdTo").val();
		$("#dg").datagrid('options').url ='/ddxq/system/districtManage/searchDistrictInfo?districtIdFrom='+districtIdFrom+'&districtIdTo='+districtIdTo;
    	$("#dg").datagrid('reload');
	}
	function removeData(){
			var r=confirm("是否删除小区信息");
			if (r==false){
				 return false;
			}
			var retData = {};
			var row = $('#dg').datagrid('getSelected');		
			retData.districtId=row.districtId;
			dat=$.toJSON(retData);
			$.ajax({
				url : '/ddxq/system/districtManage/removeDistrictInfo',
				data : dat,
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
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
	function updateData(){
		var retData = {};
		var check=$("#ff2").form('enableValidation').form('validate');
		if(check ==false){
			return false;
		}
		retData.id=$("#hideid").val();
		retData.districtId=$('#districtId2').val();
		retData.areacode=$('#areacode2').val();
		retData.name=$('#name2').val();
		retData.alias=$('#alias2').val();		
		retData.description=$('#description2').val();
		//retData.ticket=$('#ticket2').val();
		//retData.qrcodeURL=$('#qrcodeURL2').val();
		retData.address=$('#address2').val();
		retData.postcode=$('#postcode2').val();
		retData.phone=$('#phone2').val();
		retData.site=$('#site2').val();
		retData.busroute=$('#busroute2').val();
		retData.qualitiy=$('#qualitiy2').val();
		retData.category=$('#category2').val();
		retData.level=$('#level2').val();
		//retData.longitude=$('#longitude2').val();
		//retData.latitude=$('#latitude2').val();
		retData.weathercode=$("#weathercode2").val();
		retData.tags=$('#tags2').val();
		retData.notes=$('#notes2').val();
		retData.id=$("#hideid").val();
		$.ajax({
			url : '/ddxq/system/districtManage/editDistrictInfo',
			data : $.toJSON(retData),
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			success : function(data) {
				if(data.success==true){
					$('#dlg2').dialog('close');
					alert("更新成功");
					$("#dg").datagrid('options').url = data.url;
					$("#dg").datagrid('reload');
					
					
				}else{
					alert("更新失败,请检查所填信息是否重复");
				}				
			},
			error : function(xhr) {
			}
		});
		return false;
	}
	function onClickCell(index, field){		
		var row=$('#dg').datagrid('getSelected');
		$('#dlg2').dialog('open');	
		$("#districtId2").numberbox('setValue',row.districtId);
		$("#areacode2").numberbox('setValue',row.areacode);
		$("#name2").textbox('setValue',row.name);
		$("#level2").numberbox('setValue',row.level);
		$("#alias2").textbox('setValue',row.alias);
		//$("#ticket2").textbox('setValue',row.ticket);	
		//$("#qrcodeURL2").textbox('setValue',row.qrcodeURL);
		$("#description2").textbox('setValue',row.description);
		$("#address2").textbox('setValue',row.address);
		$("#postcode2").numberbox('setValue',row.postcode);
		$("#phone2").numberbox('setValue',row.phone);
		$("#site2").textbox('setValue',row.site);
		$("#busroute2").textbox('setValue',row.busroute);
		$("#qualitiy2").textbox('setValue',row.qualitiy);
		$("#category2").textbox('setValue',row.category);
		//$("#longitude2").textbox('setValue',row.longitude);
		//$("#latitude2").textbox('setValue',row.latitude);
		$("#weathercode2").textbox('setValue',row.weathercode);
		$("#tags2").textbox('setValue',row.tags);
		$("#notes2").textbox('setValue',row.notes);
		$("#hideid").val(row.id);
	}
	function searchDistrict(){
		var name=$("#districtName").val();
		if(name==""||name.length<2){
			alert("请正确输入小区名称");
			return false;
		}
		$("#dg").datagrid('options').url ='/ddxq/system/districtManage/searchDistrict?name='+name;
    	$("#dg").datagrid('reload');

	}
	function showAddDialog() {
		$('#dlg').dialog('open');
	}
	function clearDialog(){
		 $(".easyui-textbox").textbox('setValue',"");
		 $(".easyui-numberbox").textbox('setValue',"");
	}
	var i=0;
	function toMap(){
		i=i+1;	
		window.open('http://api.map.baidu.com/lbsapi/getpoint/index.html',i,'width=1000,height=850,menubar=no,scrollbars=yes');
	}
	function openNewSpecifiedWindow( windowName ){
	     window.open('about:blank',windowName,'width=1000,height=800,menubar=no,scrollbars=no');
	    }
	function toMap2(){
	    	var id=$("#districtId").numberbox('getValue');
	    	if(id==""){
	    		alert("小区编号不能为空");
	    		return false;
	    	}
	    	$("#h1").val(id);
	    	$('#editForm').submit();
	    }
	function toMap3(){
    	var id=$("#districtId2").numberbox('getValue');
    	if(id==""){
    		alert("小区编号不能为空");
    		return false;
    	}
    	$("#h1").val(id);
    	$("#h2").val($("#hideid").val());
    	$('#editForm').submit();
    }
	</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
     margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;;">
	<table id="dg" class="easyui-datagrid" title="小区信息"
		 fit="true" style="overflow-y:hidden"
		data-options="rownumbers:true,singleSelect:true,collapsible:true,onDblClickCell: onClickCell,url:'/ddxq/system/districtManage/showDistrictGrid',method:'get',toolbar:'#tb'">
		<thead>
			<tr>
			    <th data-options="field:'id',width:60,hidden:true">序号</th>
				<th data-options="field:'districtId',width:60">小区编号</th>
				<th data-options="field:'areacode',width:90">区号</th>
				<th data-options="field:'level',width:70">区划级别</th>				
				<th data-options="field:'name',width:70">小区全名</th>
				<th data-options="field:'alias',width:70">小区别名</th>
				<th data-options="field:'ticket',width:70">ticket</th>	
				<th data-options="field:'qrcodeURL',width:70">小区二维码</th>					
				<th data-options="field:'description',width:70">小区简介</th>
				<th data-options="field:'address',width:70">小区地址</th>			
				<th data-options="field:'postcode',width:60">小区邮编</th>
				<th data-options="field:'phone',width:60">小区管家电话</th>
				<th data-options="field:'site',width:80">小区网址</th>
				<th data-options="field:'busroute',width:80">乘车路线</th>
				<th data-options="field:'longitude',width:60">经度</th>
				<th data-options="field:'latitude',width:60">纬度</th>
				<th data-options="field:'borderline',width:60">小区边界</th>
				<th data-options="field:'weathercode',width:60">天气编码</th>				
				<th data-options="field:'qualitiy',width:70">小区性质</th>
				<th data-options="field:'category',width:70">小区类型</th>
				<th data-options="field:'created',width:80">创建时间</th>
				<th data-options="field:'updated',width:80">更新时间</th>
				<th data-options="field:'tags',width:80">标签</th>
				<th data-options="field:'notes',width:80">备注</th>	
				<th data-options="field:'id',hidden:true,width:80">id</th>				
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		小区编号: <input type="text" id="districtIdFrom" class="easyui-textbox"style="width: 47px"> 
		至: <input type="text" id="districtIdTo"class="easyui-textbox" style="width: 47px"> 
		<a  onclick="searchData()"class="easyui-linkbutton" iconCls="icon-search">查询</a>
		小区名称: <input type="text" id="districtName"class="easyui-textbox" style="width: 60px">  
		<a  onclick="searchDistrict()"class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		<a  onclick="showAddDialog()"class="easyui-linkbutton" iconCls="icon-add">添加小区</a> 
		<a  onclick="removeData()"class="easyui-linkbutton" iconCls="icon-clear">删除选中小区</a> 
		<a  onclick=""class="easyui-linkbutton",plain='true', iconCls="icon-save">双击编辑</a> 
	</div>

	<div id="dlg" class="easyui-dialog" title="添加小区信息"
		data-options="iconCls:'icon-add'"
		style="width:60%; height: 500px; padding: 8px">
			<form id="ff"> 
		<div style="margin: 8px 0;"></div>		
			<input class="easyui-numberbox" id="districtId" style="width: 90%"
				data-options="label:'小区编号:',required:true">
			<div style="margin-bottom:8px"></div>		
			<input class="easyui-numberbox" id="areacode" style="width: 90%"
				data-options="label:'区号:',required:true">  
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="name" style="width: 90%"
				data-options="label:'小区名称:',required:true">
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="alias" style="width: 90%"
				data-options="label:'小区别名:',required:true"> 
		<!-- 		<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="ticket" style="width: 90%"
				data-options="label:'ticket:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="qrcodeURL" style="width: 90%"
				data-options="label:'小区二维码:',prompt:'URL网址'">  -->
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="description" style="width: 90%"
				data-options="label:'小区简介:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="address" style="width: 90%"
				data-options="label:'小区地址:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="postcode" style="width: 90%"
				data-options="label:'小区邮编:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="phone" style="width: 90%"
				data-options="label:'小区电话:',prompt:'小区大管家电话'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="site" style="width: 90%"
				data-options="label:'小区网址:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="qualitiy" style="width: 90%"
				data-options="label:'小区性质:',prompt:'预留'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="category" style="width: 90%"
				data-options="label:'小区类型:',prompt:'(城乡分类码)111,210,220等'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="level" style="width: 90%"
				data-options="label:'区划级别:',prompt:'1：省(市)2：市(地)3：区(县)4：街道(乡镇)5：小区(村)',required:true"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="busroute" style="width: 90%"
				data-options="label:'乘车路线:'"> 
				<div style="margin-bottom:8px"></div>
		<!-- 	<input class="easyui-numberbox" id="longitude" style="width: 70%"
				data-options="label:'经度:',required:true,precision:3"> 
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap()" style="width: 90px;height:27px">查找经纬度</a> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="latitude" style="width: 70%"
				data-options="label:'纬度:',required:true,precision:3"> 
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap()" style="width: 90px;height:27px">查找经纬度</a> 
				<div style="margin-bottom:8px"></div> -->			
			<input class="easyui-numberbox" id="borderline" style="width: 70%"
				data-options="label:'绘制小区边界:',prompt:'请点击右侧按钮绘制'"> 
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap2()" style="width: 90px;height:27px">绘制小区边界</a> 
				<div style="margin-bottom:8px"></div>	
			<input class="easyui-textbox" id="weathercode" style="width: 90%"
				data-options="label:'天气编码:',prompt:'101010100'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="tags" style="width: 90%"
				data-options="label:'标签:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="notes" style="width: 90%"
				data-options="label:'备注:',multiline:true"> 
				<div style="margin-bottom:8px"></div>
			<br/>
			<div style="margin-bottom:8px"></div>					
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="addData()" style="width: 80px">提交</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="clearDialog()" style="width: 80px">清空</a>
			</div>
				</form>
	</div>

	
	<div id="dlg2" class="easyui-dialog" title="更改小区信息 " data-options="iconCls:'icon-tip'" style="width:60%; height: 500px; padding: 8px">
		<form id="ff2">
		<div style="margin: 8px 0;"></div>
			<input class="easyui-numberbox" id="districtId2" style="width: 90%"
				data-options="label:'小区编号:',required:true"> 					
			<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="areacode2" style="width: 90%"
				data-options="label:'区号:',required:true"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="name2" style="width: 90%"
				data-options="label:'小区全称:',required:true">
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="alias2" style="width: 90%"
				data-options="label:'小区别名:',required:true"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="description2" style="width: 90%"
				data-options="label:'小区简介:'"> 
					<div style="margin-bottom:8px"></div>
	<!-- 		<input class="easyui-textbox" id="ticket2" style="width: 90%"
				data-options="label:'ticket:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="qrcodeURL2" style="width: 90%"
				data-options="label:'小区二维码:',prompt:'URL网址'"> 
				<div style="margin-bottom:8px"></div> -->
			<input class="easyui-textbox" id="address2" style="width: 90%"
				data-options="label:'小区地址:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="postcode2" style="width: 90%"
				data-options="label:'小区邮编:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="phone2" style="width: 90%"
				data-options="label:'小区电话:',prompt:'小区大管家电话'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="site2" style="width: 90%"
				data-options="label:'小区网址:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="qualitiy2" style="width: 90%"
				data-options="label:'小区性质:',prompt:'预留'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="category2" style="width: 90%"
				data-options="label:'小区类型:',prompt:'(城乡分类码)111,210,220等'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="level2" style="width: 90%"
				data-options="label:'区划级别:',prompt:'1：省(市)2：市(地)3：区(县)4：街道(乡镇)5：小区(村)',required:true"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="busroute2" style="width: 90%"
				data-options="label:'乘车路线:'"> 
				<div style="margin-bottom:8px"></div>			
			<!-- <input class="easyui-numberbox" id="longitude2" style="width: 70%"
				data-options="label:'经度:',precision:3"> 
			<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap()" style="width: 90px;height:27px">查找经纬度</a> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-numberbox" id="latitude2" style="width: 70%"
				data-options="label:'纬度:',precision:3"> 
			<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap()" style="width: 90px;height:27px">查找经纬度</a> 
				<div style="margin-bottom:8px"></div> -->
			<input class="easyui-numberbox" id="borderline2" style="width: 70%"
				data-options="label:'绘制小区边界:',prompt:'请点击右侧按钮绘制'"> 
			<a href="javascript:void(0)" class="easyui-linkbutton"onclick="toMap3()" style="width: 90px;height:27px">绘制小区边界</a> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="weathercode2" style="width: 90%"
				data-options="label:'天气编码:',prompt:'101010100'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="tags2" style="width: 90%"
				data-options="label:'标签:'"> 
				<div style="margin-bottom:8px"></div>
			<input class="easyui-textbox" id="notes2" style="width: 90%"
				data-options="label:'备注:',multiline:true"> 
				<div style="margin-bottom:8px"></div>					
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"onclick="updateData()" style="width: 90px">更新小区信息</a> 
			</div>
			<input type="hidden" id="hideid" value=""/>
			</form>
	</div>
		<form id="editForm" name="editForm" method="post" action="/ddxq/system/districtManage/showDrawBorderline" target="newWindow" onsubmit="openNewSpecifiedWindow('newWindow')">
			  <input type="hidden" id="h1" name="districtId" value="" />
			  <input type="hidden" id="h2" name="rowId" value="" />
		</form>
</body>
</html>