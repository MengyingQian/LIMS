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
	<script src="/view/pub/basic/js/ajaxfileupload.js" type="text/javascript"></script>
	<script type="text/javascript">	
		$(function(){
			$('#dlg').dialog('close');	
			$('#dlg2').dialog('close');	
			$("#dg").datagrid('options').url ='/ddxq/admin/zpqz/getInfo';
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
	<table id="dg" class="easyui-datagrid" title="当前求职招聘列表"  fit="true" data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '/ddxq/admin/zpqz/getInfo',
				method: 'get',
				onDblClickCell: onClickCell,
			">

			<thead>
				<tr>
					<th data-options="field:'districtname',width:90">小区名称</th> 
					<th data-options="field:'title',width:90">招聘标题</th>
					<th data-options="field:'digest',width:90">简要描述</th>
					<th data-options="field:'position',width:90,align:'center'">工作岗位</th>
					<th data-options="field:'place',width:90,align:'center'">工作地点</th>
					<th data-options="field:'welfare',width:90,align:'center'">福利说明</th>
					<th data-options="field:'employer',width:60,align:'center'">招聘单位</th>
					<th data-options="field:'description',width:90,hidden:true,align:'center'">详细描述</th>
					<th data-options="field:'salary_from',width:90,align:'center'">薪资范围从</th>
					<th data-options="field:'salary_to',width:90,align:'center'">薪资范围至</th>
					<th data-options="field:'phone',width:90,align:'center'">联系电话</th>
					<th data-options="field:'contact',width:90,align:'center'">联系人</th>			
					<th data-options="field:'status',width:20,align:'center'">状态</th>
					<th data-options="field:'created',width:120,align:'center'">创建时间</th>
					<th data-options="field:'updated',width:120,align:'center'">更新时间</th>
					<th data-options="field:'expired',width:120,hidden:true,align:'center'">过期时间</th>
					<th data-options="field:'sort',width:120,align:'center',hidden:true"></th>
					<th data-options="field:'type',width:120,align:'center',hidden:true"></th>
					<th data-options="field:'docid',width:0,hidden:true,align:'center'"></th>
					
				</tr>
			</thead>
		</table>
		<div>
			<input type="hidden" id="disid" name="disid" value="${sessionScope.districtId}" /> 
			<input type="hidden" id="sellerphone" name="sellerphone" value="${seller.employeeMobile}" /> 
		</div>
	<div id="tb" style="height:auto">	
			<select class="easyui-combobox"id="selectRoom" label="添加招聘求职信息" style="width:20%"><option value="all">所有信息</option><option value="recruit">招聘</option><option value="apply">求职</option></select>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查询</a>
			<br/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加招聘求职信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelect()">删除信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改信息</a>
		</div>
		<div id="dlg" class="easyui-dialog" title="添加招聘求职信息" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">
			<form id="ff" method="post">
				<div style="margin-bottom:10px">
					<select class="easyui-combobox"id="type" name="type" label="选择信息类型"required="true" style="width:90%"><option value="recruit">招聘</option><option value="apply">求职</option></select>
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="districtname" name="districtname" style="width:90%" data-options="label:'小区名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="title" name="title" style="width:90%" data-options="label:'信息标题:',required:true,validType:'length[1,18]'">
				</div>
				
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="position" name="position" style="width:90%" data-options="label:'工作岗位:',required:true,prompt:'若是-求职-,则填期望职位'">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="place" name="place" style="width:90%" data-options="label:'工作地点:',required:true,prompt:'若是-求职-,则填期望地点'">
				</div>
				<div style="margin-bottom:10px">
					<input placeholder="" class="easyui-textbox" id="welfare" name="welfare" style="width:90%" data-options="label:'福利说明:',required:true,validType:'length[1,25]',prompt:'若是-求职-,则填期望福利'" >
				</div>							
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="employer" name="employer" style="width:90%" data-options="label:'招聘单位:',required:true,prompt:'若是-求职-,则随意填写,并不在前端显示'">
				</div>
					<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="phone" name="phone" style="width:90%" data-options="label:'联系电话',required:true" >
				</div>
					<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="contact" name="contact" style="width:90%" data-options="label:'联系人',required:true" >
				</div>
				<div style="margin-bottom:10px">
					薪资范围:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-numberbox" id="salary_from" name="salary_from" style="width:16%" data-options="required:true,prompt:'最低'" >
					到:<input class="easyui-numberbox" id="salary_to" name="salary_to" style="width:16%" data-options="required:true,prompt:'最高'" >元/月
				</div>
					<div style="margin-bottom:10px">
					简要说明1:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-textbox" id="digest1" name="digest1" style="width:16%" data-options="required:true,prompt:'最多5字',validType:'length[1,5]'" >
					简要说明2:<input class="easyui-textbox" id="digest2" name="digest2" style="width:16%" data-options="prompt:'最多5字',validType:'length[0,5]'" >
					简要说明3:<input class="easyui-textbox" id="digest3" name="digest3" style="width:16%" data-options="prompt:'最多5字',validType:'length[0,5]'" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="sort" name="sort" style="width:90%" data-options="label:'排序号',required:true,prompt:'数字，大数排在前',validType:'length[1,5]'" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="description" name="description" style="width:90%;height:90px" data-options="label:'详细描述:',required:true,multiline:true,prompt:'请一行一行按条输入,每一行用*号分割,\n例如：1.周末双休*\n2.假期多*\n3.XXX*\n4.XXX'" >
				</div>
				
				
				<div style="margin-bottom:10px">
					<select class="easyui-combobox"id="status" name="status" label="选择消息状态"required="true" style="width:90%"><option value="1">正常</option><option value="0">失效</option></select>
				</div>
				
					<input type="hidden"  id="temp" name="temp" />
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">添加信息</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
			<script  type="text/javascript">
				function append(){				
					$('#dlg').dialog('open');	
				}
			</script>			
			<script type="text/javascript">
				function getData() {
					var retData = {};
					retData.districtid=$("#disid").val();
					retData.districtname = $("#districtname").textbox('getValue');
					retData.title = $("#title").textbox('getValue');			
					retData.position = $("#position").textbox('getValue');					
					retData.place = $("#place").textbox('getValue');
					retData.welfare = $("#welfare").textbox('getValue');
					retData.employer = $("#employer").textbox('getValue');
					retData.salary_from = $("#salary_from").numberbox('getValue');
					retData.salary_to = $("#salary_to").numberbox('getValue');
					retData.description = $("#description").textbox('getValue');
					retData.digest = $("#digest1").textbox('getValue') +"*"+$("#digest2").textbox('getValue')+"*"+ $("#digest3").textbox('getValue');
					retData.phone = $("#phone").numberbox('getValue');
					retData.contact = $("#contact").textbox('getValue');
					retData.sort = $("#sort").numberbox('getValue');
					retData.status = $("#status").numberbox('getValue');
					retData.type = $("#type").combobox('getValue');
					retData.show=$("#selectRoom").combobox('getValue');
					return retData;
				}
				
				function submitForm() {
					var retData = getData();
					var check=$("#ff").form('enableValidation').form('validate');
					if(check==false){
						alert("请填写完整");
						return false;
					}

					$.ajax({
						url: '/ddxq/admin/zpqz/insertInfo',
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

		</div>
	<div id="dlg2" class="easyui-dialog" title="修改信息" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">	
		<form id="ff2" method="post">
				<div style="margin-bottom:10px">
					<select class="easyui-combobox"id="type2" name="type2" label="选择信息类型"required="true" style="width:90%"><option value="recruit">招聘</option><option value="apply">求职</option></select>
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="districtname2" name="districtname2" style="width:90%" data-options="label:'小区名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="title2" name="title2" style="width:90%" data-options="label:'信息标题:',required:true,validType:'length[1,18]'">
				</div>
				
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="position2" name="position2" style="width:90%" data-options="label:'工作岗位:',required:true,prompt:'若是-求职-，则填期望职位'">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="place2" name="place2" style="width:90%" data-options="label:'工作地点:',required:true,prompt:'若是-求职-，则填期望地点'">
				</div>
				<div style="margin-bottom:10px">
					<input placeholder="" class="easyui-textbox" id="welfare2" name="welfare2" style="width:90%" data-options="label:'福利说明:',required:true,validType:'length[1,25]',prompt:'若是-求职-，则填期望福利'" >
				</div>							
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="employer2" name="employer2" style="width:90%" data-options="label:'招聘单位:',required:true,prompt:'若是-求职-，则随意填写，并不在前端显示'">
				</div>
					<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="phone2" name="phone2" style="width:90%" data-options="label:'联系电话',required:true" >
				</div>
					<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="contact2" name="contact2" style="width:90%" data-options="label:'联系人',required:true" >
				</div>
				<div style="margin-bottom:10px">
					薪资范围:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-numberbox" id="salary_from2" name="salary_from2" style="width:16%" data-options="required:true,prompt:'最低'" >
					到:<input class="easyui-numberbox" id="salary_to2" name="salary_to2" style="width:16%" data-options="required:true,prompt:'最高'" >元/月
				</div>
					<div style="margin-bottom:10px">
					简要说明1:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-textbox" id="digest-1" name="digest-1" style="width:16%" data-options="required:true,prompt:'最多5字',validType:'length[1,5]'" >
					简要说明2:<input class="easyui-textbox" id="digest-2" name="digest-2" style="width:16%" data-options="prompt:'最多5字',validType:'length[0,5]'" >
					简要说明3:<input class="easyui-textbox" id="digest-3" name="digest-3" style="width:16%" data-options="prompt:'最多5字',validType:'length[0,5]'" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="sort2" name="sort2" style="width:90%" data-options="label:'排序号',required:true,prompt:'数字,大数排在前',validType:'length[1,5]'" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="description2" name="description2" style="width:90%;height:90px" data-options="label:'详细描述:',required:true,multiline:true,prompt:'请一行一行按条输入,每一行用*号分割,例如：1.周末双休*\n2.假期多*\n3.XXX*\n4.XXX'" >
				</div>
				
				
				<div style="margin-bottom:10px">
					<select class="easyui-combobox"id="status2" name="status2" label="选择消息状态"required="true" style="width:90%"><option value="1">正常</option><option value="0">失效</option></select>
				</div>
				    <input type="hidden"  id="docid2" name="docid2" />
					<input type="hidden"  id="temp" name="temp" />
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
			$("#type2").combobox({ disabled: true});			
			$("#type2").combobox('setValue',row.type);
			$("#districtname2").textbox('setValue',row.districtname);	
			$("#title2").textbox('setValue',row.title);	
			$("#position2").textbox('setValue',row.position);
			$("#employer2").textbox('setValue',row.employer);	
			$("#place2").textbox('setValue',row.place);
			$("#welfare2").textbox('setValue',row.welfare);
			$("#description2").textbox('setValue',row.description);		
			$("#salary_from2").numberbox('setValue',row.salary_from);
			$("#salary_to2").numberbox('setValue',row.salary_to);
			var a=row.digest;
			var ad=a.split("*");
			$("#digest-1").textbox('setValue',ad[0]);
			$("#digest-2").textbox('setValue',ad[1]);
			$("#digest-3").textbox('setValue',ad[2]);		
			$("#contact2").textbox('setValue',row.contact);
			$("#phone2").numberbox('setValue',row.phone);
			$("#sort2").numberbox('setValue',row.sort);
			$("#status2").combobox('setValue',row.status);		
			$("#docid2").val(row.docid);
		}
		function getData2(){
			var retData = {};
			retData.districtid=$("#disid").val();
			retData.districtname = $("#districtname2").textbox('getValue');
			retData.title = $("#title2").textbox('getValue');			
			retData.position = $("#position2").textbox('getValue');					
			retData.place = $("#place2").textbox('getValue');
			retData.welfare = $("#welfare2").textbox('getValue');
			retData.employer = $("#employer2").textbox('getValue');
			retData.salary_from = $("#salary_from2").numberbox('getValue');
			retData.salary_to = $("#salary_to2").numberbox('getValue');
			retData.description = $("#description2").textbox('getValue');
			retData.digest = $("#digest-1").textbox('getValue') +"*"+$("#digest-2").textbox('getValue')+"*"+ $("#digest-3").textbox('getValue');
			retData.phone = $("#phone2").numberbox('getValue');
			retData.contact = $("#contact2").textbox('getValue');
			retData.sort = $("#sort2").numberbox('getValue');
			retData.status = $("#status2").numberbox('getValue');
			retData.type = $("#type2").combobox('getValue');
			retData.docid = $("#docid2").val();
			retData.show=$("#selectRoom").combobox('getValue');
			return retData;
		}
		function getSelect(){
			var r=confirm("是否删除选中的信息？");
			if (r==false){
				 return false;
			 }
			var retData={};
			var rows = $('#dg').datagrid('getSelected');
			retData.docid = rows.docid;
			retData.type = rows.type;
			retData.show=$("#selectRoom").combobox('getValue');
			data=$.toJSON(retData);
			$('#dg').datagrid('clearSelections');
			$.ajax({
				url : '/ddxq/admin/zpqz/removeData',
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
			var type=$("#selectRoom").combobox('getValue');
			if(type!="all"){
				$("#dg").datagrid('options').url ='/ddxq/admin/zpqz/searchData?type='+type;
		    	$("#dg").datagrid('reload');
			}else{
				$("#dg").datagrid('options').url ='/ddxq/admin/zpqz/getInfo';
		    	$("#dg").datagrid('reload');
				
			}
			
		}
	</script>
	<script> 
		function submitEdit(){
			var retData=getData2();
			var check=$("#ff2").form('enableValidation').form('validate');
			if(check==false){
				alert("请填写完整");
				return false;
			}
			$.ajax({
				url : '/ddxq/admin/zpqz/editInfo',
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