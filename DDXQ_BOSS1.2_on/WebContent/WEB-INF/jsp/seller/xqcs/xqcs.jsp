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
			$("#dg").datagrid('options').url ='/ddxq/seller/xqcs/getinfos';
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
	<table id="dg" class="easyui-datagrid" title="当前销售商品列表"  fit="true" data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '/ddxq/seller/xqcs/getinfos',
				method: 'get',
				onDblClickCell: onClickCell,
			">

			<thead>
				<tr> 
					<th data-options="field:'districtId',width:90">小区ID</th>
					<th data-options="field:'caption',width:90">商品名称</th>
					<th data-options="field:'price',width:90,align:'center'">单价</th>
					<th data-options="field:'unit',width:90,align:'center'">单位、规格</th>
					<th data-options="field:'stock',width:90,align:'center'">库存</th>
					<th data-options="field:'label',width:90,align:'center'">商品标签</th>
					<th data-options="field:'seller',width:90,align:'center'">商家ID</th>
					<!-- <th data-options="field:'sales',width:90,align:'center'">已售数量</th> -->
					<th data-options="field:'phone',width:150,align:'center'">商家电话</th>
					<th data-options="field:'photo_min',width:150,align:'center'">商品主图</th>
					<th data-options="field:'detail',width:150,align:'center'">图文详情</th>
					<th data-options="field:'created',width:100,align:'center'">更新时间</th>
					<th data-options="field:'updated',width:100,align:'center'">更新时间</th>
					<th data-options="field:'docid',width:0,hidden:true,align:'center'"></th>
					<th data-options="field:'photo_1',width:0,hidden:true,align:'center'"></th>
					<th data-options="field:'photo_2',width:0,hidden:true,align:'center'"></th>
					<th data-options="field:'photo_3',width:0,hidden:true,align:'center'"></th>
					<th data-options="field:'menuid',width:0,hidden:true,align:'center'"></th>
				</tr>
			</thead>
		</table>
		<div>
			<input type="hidden" id="disid" name="disid" value="${sessionScope.districtId}" /> 
			<input type="hidden" id="sellerphone" name="sellerphone" value="${seller.employeeMobile}" /> 
		</div>
	<div id="tb" style="height:auto">
<!-- 			小区编号: <input type="text" id="districtIdFrom" class="easyui-numberbox" style="width:50px">
			商品编号: <input type="text" id="subcateid" class="easyui-numberbox" style="width:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchData()">查询销售商品</a> -->
			<br/>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加商品</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="getSelect()">删除选中商品</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"">双击修改商品信息</a>
		</div>
		<div id="dlg" class="easyui-dialog" title="添加销售商品" data-options="iconCls:'icon-save'" style="width:450px;height: 420px;padding:5px">
			<form id="ff" method="post">
				<div style="margin-bottom:10px;margin-top:10px">
					<input disabled="disabled" class="easyui-numberbox" id="districtId" name="districtId" style="width:90%" data-options="label:'小区编号:',required:true" value="${sessionScope.districtId}">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="caption" name="caption" style="width:90%" data-options="label:'商品名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="price" name="price" style="width:90%" data-options="label:'单价（元）:',required:true,prompt:'不包含单位，例如1.7，请严格按照格式'">
				</div>
				<div style="margin-bottom:10px">
					<input placeholder="" class="easyui-textbox" id="unit" name="unit" style="width:90%" data-options="label:'规格（斤等）:',required:true,prompt:'斤、袋、盒、箱等'" >
				</div>
				<div style="margin-bottom:10px"> 
					一级目录：
					<select  id="menuAA"  style="width: 69%;margin-left: 25px;border-radius: 3px;">
						<option value="-1"  selected="selected" > </option>
						<c:forEach items="${menuA}" var="menu"  varStatus="status">
							<option value="${menu.idA }">${menu.nameA}</option>
						</c:forEach>
					</select>
				</div>
				<div style="margin-bottom:10px"> 
					二级目录：
					<select  id="menuBB"  style="width: 69%;margin-left: 25px;border-radius: 3px;">
					</select>
				</div>
				
				<div style="margin-bottom:10px"> 
					商品标签：
					<select  id="label"  style="width: 69%;margin-left: 25px;border-radius: 3px;">
						<option value="精品"  selected="selected" >精品(价格稍高的)</option>
						<option value="直降"   >直降(价格较低)</option>
						<option value="热销"   >热销（卖得好的）</option>
						<option value="进口"   >进口（进口的）</option>
						<option value="好评"   >好评（物美价廉）</option>
					</select>
				</div>
				
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="stock" name="stock" style="width:90%" data-options="label:'库存:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="phone" name="phone" style="width:90%" data-options="label:'电话:',required:true" >
				</div>
				<div style="margin-bottom:10px">
					<img id="showimage" src="/view/seller/xqcs/img/noimage.jpg" style="width:150px;position:relative;float:left;left:150px;" >
				</div>
				<div style="margin-bottom:10px">
            			<div class="easyui-linkbutton" id="upmin" style="width:80%;margin-bottom: 20px;margin-top: 9px;margin-left: 65px;margin-right: 50px;">上传主图（购物页展示，必选）</div>
						<input type="file" onchange="uploadHead();" id="upfile" style="display:none;" name="upfile" />  
						<input type="hidden" id="photo_min" name="photo_min"  value="">  
				</div>
				<div style="margin-bottom:10px">
					<img id="showimage1" src="/view/seller/xqcs/img/noimage.jpg" style="width:150px;position:relative;float:left;left:150px">
				</div>	
				<div style="margin-bottom:10px">
            			<div class="easyui-linkbutton" id="up1" style="width:80%;margin-bottom: 5px;margin-top: 9px;margin-left: 65px;margin-right: 50px;">上传图1（详情页展示，图片可与购物页重复，必选）</div>
						<div class="easyui-linkbutton" id="reup1" style="width:80%;margin-bottom: 20px;margin-top: 2px;margin-left: 65px;margin-right: 50px;">图片与主图相同</div>
						<input type="hidden" id="photo_1" name="photo_1"  > 
				</div>
				<div style="margin-bottom:10px">
					<img id="showimage2" src="/view/seller/xqcs/img/noimage.jpg" style="width:150px;position:relative;float:left;left:150px">
				</div>	
				<div style="margin-bottom:10px">
            			<div class="easyui-linkbutton" id="up2"  style="width:80%;margin-bottom: 5px;margin-top: 9px;margin-left: 65px;margin-right: 50px;">上传图2（详情页展示，图片可与购物页重复，必选）</div>
						<div class="easyui-linkbutton" id="reup2" style="width:80%;margin-bottom: 20px;margin-top: 2px;margin-left: 65px;margin-right: 50px;">图片与主图相同</div>
						<input type="hidden" id="photo_2" name="photo_2"  > 
				</div>
				<div style="margin-bottom:10px">
					<img id="showimage3" src="/view/seller/xqcs/img/noimage.jpg" style="width:150px;position:relative;float:left;left:150px">
				</div>	
				<div style="margin-bottom:10px">
            			<div class="easyui-linkbutton" id="up3"  style="width:80%;margin-bottom: 5px;margin-top: 9px;margin-left: 65px;margin-right: 50px;">上传图3（详情页展示，图片可与购物页重复，必选）</div>
						<div class="easyui-linkbutton" id="reup3" style="width:80%;margin-bottom: 20px;margin-top: 2px;margin-left: 65px;margin-right: 50px;">图片与主图相同</div>
						<input type="hidden" id="photo_3" name="photo_3"  > 
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="detail" name="detail" style="width:90%;height:140px" data-options="label:'商品详情:',multiline:true,required:true">
				</div>
					<input type="hidden"  id="temp" name="temp" />
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">添加商品</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
			<script  type="text/javascript">
				function append(){
					$("#showimage").attr('src',"/view/seller/xqcs/img/noimage.jpg"); 
					$("#showimage1").attr('src',"/view/seller/xqcs/img/noimage.jpg"); 
					$("#showimage2").attr('src',"/view/seller/xqcs/img/noimage.jpg"); 
					$("#showimage3").attr('src',"/view/seller/xqcs/img/noimage.jpg");
					$('#photo_min').val();
					$('#photo_1').val();
					$('#photo_2').val();
					$('#photo_3').val();
					var id = $("#disid").val();
					$("#districtId").textbox('setValue',id);
					$("#phone").numberbox('setValue',$("#sellerphone").val());
					$('#dlg').dialog('open');	
				}
			</script>
			<script  type="text/javascript">
				$(document).ready(function(){
					$('#upmin').on('click',function(){ 
						$("#temp").val(0);
				     	$('#upfile').click();
				    });
					$('#up1').on('click',function(){  
						$("#temp").val(1);
				     	$('#upfile').click(); 
				    });
					$('#up2').on('click',function(){ 
						 $("#temp").val(2);
				     	$('#upfile').click();  
				    });
					$('#up3').on('click',function(){  
						$("#temp").val(3);
				     	$('#upfile').click();  
				    });
					$('#reup1').on('click',function(){  
						var photo_min = $('#photo_min').val();
						if (null == photo_min || photo_min == ""){
							alert("请上传主图");
							return false;
						}
						var showimage = $("#showimage")[0].src; 
	    	    	   $("#showimage1").attr('src',showimage);  
		  	           $("#photo_1").val(photo_min); 		
				    });
					$('#reup2').on('click',function(){
						var photo_min = $('#photo_min').val();
						if (null == photo_min || photo_min == ""){
							alert("请上传主图");
							return false;
						}
						var showimage = $("#showimage")[0].src; 
	    	    	   $("#showimage2").attr('src',showimage);  
		  	           $("#photo_2").val(photo_min); 
				    });
					$('#reup3').on('click',function(){  
						var photo_min = $('#photo_min').val();
						if (null == photo_min || photo_min == ""){
							alert("请上传主图");
							return false;
						}
						var showimage = $("#showimage")[0].src; 
	    	    	   $("#showimage3").attr('src',showimage);  
		  	           $("#photo_3").val(photo_min); 
				    });
				});
				</script>
			<script>
				$(document).ready(function(){
					 $("#menuAA").change(function(){ 
					     var retData = {};
						 retData.idA = $("#menuAA").val();
						 if (parseInt(retData.idA)==-1){
							 alert("请选择一级分类");
							 return;
						 }
						 if (parseInt(retData.idA)==99){
							 $("#menuBB").empty();
							 $("#menuBB").append("<option value=\"99000\"  selected=\"selected\" >其他商品</option>");
							 $("#menuBB").val("99000");
							 return;
						 }
						  $.ajax({
								url: '/ddxq/seller/xqcs/getMenuB',
								data: $.toJSON(retData),
								type: 'post',
								dataType: 'json',
								contentType: 'application/json',
								cache: false,
								async: false,
								success: function(data) {
									var menuB = data.menuB;
									var context = "";
									for(var index in menuB ){
										var dange = menuB[index];
										context = context + createSelection(dange);
									}
									$("#menuBB").empty();
									$("#menuBB").append(context);
								},
								error: function(xhr) {}
							}); 
					 }); 
				 }); 
				function createSelection(content){
				 	var result = "<option value=" + content.idB + ">" + content.nameB + "</option>"; 
					return result;
				}
				</script>
			<script  type="text/javascript">
				function uploadHead(){
					  $.ajaxFileUpload({  
					      url:"/ddxq/seller/xqcs/headimagesave",//需要链接到服务器地址   
					      secureuri:false,  
					      fileElementId:"upfile",//文件选择框的id属性  ,name属性
					      dataType: 'json',   //json  
					      success: function (data) {
					    	       var temp = parseInt($("#temp").val());
					    	       if(temp==0){
					    	    	 	$("#showimage").attr('src',data.imagePath);  
						  	        	$('#photo_min').val(data.imagePathShort); 
					    	       }else {
					    	    	   var id1="#showimage"+temp;
					    	    	   var id2="#photo_"+temp;
					    	    	   $(id1).attr('src',data.imagePath);  
						  	           $(id2).val(data.imagePathShort); 				    	    	   
					    	       }
					      },
					      error: function(XMLHttpRequest, textStatus, errorThrown){  
					     		alert('上传失败！');
					   	  }
					  });  
					};
		</script>
			<script type="text/javascript">
				function getData() {
					var retData = {};
					retData.districtId = $("#districtId").numberbox('getValue');
					retData.caption = $("#caption").textbox('getValue');					
					retData.price = $("#price").textbox('getValue');
					retData.unit = $("#unit").textbox('getValue');
					retData.menuid = $("#menuBB").val();
					retData.label = $("#label").val();
					retData.seller = ${sessionScope.id};
					retData.stock = $("#stock").numberbox('getValue');
					retData.phone = $("#phone").numberbox('getValue');
					retData.photo_min = $("#photo_min").val();
					retData.photo_1 = $("#photo_1").val();
					retData.photo_2 = $("#photo_2").val();
					retData.photo_3 = $("#photo_3").val();
					retData.detail = $("#detail").textbox('getValue');
					return retData;
				}
				function checkData(retData){
					if(retData.photo_min==""){
						alert("请上传第一张图片");
						return false;
						
					}
					 if(retData.photo_1==""){
						retData.photo_1=$("#photo_min").val();
					} 
					if(retData.house_photo_2==""){
						retData.photo_2=$("#photo_min").val();
					}
					if(retData.house_photo_3==""){
						retData.photo_3=$("#photo_min").val();
					}
				}
				function submitForm() {
					var retData = getData();
					var flag=checkData(retData);
					if(flag==false){
						return false;
					}
					var check=$("#ff").form('enableValidation').form('validate');
					if(check==false){
						alert("请填写完整");
						return false;
					}
					$.ajax({
						url: '/ddxq/seller/xqcs/insertInfo',
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
	<div id="dlg2" class="easyui-dialog" title="修改商品信息" data-options="iconCls:'icon-save'" style="width:450px;height: 350px;padding:5px">	
		<form id="ff2" method="post">
				<div style="margin-bottom:10px;margin-top:10px">
					<input disabled="disabled" class="easyui-numberbox" id="districtId2" name="districtId" style="width:90%" data-options="label:'小区编号:',required:true" value="${sessionScope.districtId}">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="caption2" name="caption" style="width:90%" data-options="label:'商品名称:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="price2" name="price" style="width:90%" data-options="label:'单价（元）:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input placeholder="" class="easyui-textbox" id="unit2" name="unit" style="width:90%" data-options="label:'规格（斤等）:',required:true" >
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="stock2" name="stock" style="width:90%" data-options="label:'库存:',required:true">
				</div>
				
				<div style="margin-bottom:10px"> 
					商品标签：
					<select  id="label2"  style="width: 69%;margin-left: 25px;border-radius: 3px;">
						<option value="直降"  selected="selected" >直降</option>
						<option value="热销"   >热销</option>
						<option value="精品"   >精品</option>
						<option value="进口"  >进口</option>
						<option value="好评"  >好评</option>
					</select>
				</div>
				
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" id="phone2" name="phone" style="width:90%" data-options="label:'电话:',required:true" value="${seller.employeeMobile}">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="detail2" name="detail" style="width:90%;height:140px" data-options="label:'商品详情:',multiline:true,required:true">
				</div>
					<input type="hidden" id="docid2" name="docid" >
					<input type="hidden" id="menuid2" name="menuid" >
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
			$("#districtId2").numberbox({ disabled: true});
			$("#districtId2").numberbox('setValue',row.districtId);
			$("#caption2").textbox('setValue',row.caption);
			$("#price2").textbox('setValue',row.price);
			$("#unit2").textbox('setValue',row.unit);
			$("#stock2").numberbox('setValue',row.stock);
			$("#phone2").numberbox('setValue',row.phone);
			$("#label2").val(row.label);
			$("#docid2").val(row.docid);
			$("#menuid2").val(row.menuid);
			$("#detail2").textbox('setValue',row.detail);	
		}
		function getData2(){
			var retData = {};
			retData.districtId=$("#districtId2").numberbox('getValue');	
			retData.caption=$("#caption2").textbox('getValue');
			retData.price=$("#price2").textbox('getValue');
			retData.unit=$("#unit2").textbox('getValue');
			retData.stock=$("#stock2").numberbox('getValue');	
			retData.phone=$("#phone2").numberbox('getValue');
			retData.detail=$("#detail2").textbox('getValue');	
			retData.label=$("#label2").val();
			retData.docid=$("#docid2").val();
			retData.menuid=$("#menuid2").val();	
			return retData;
		}
		function getSelect(){
			var r=confirm("是否删除选中的商品？");
			if (r==false){
				 return false;
			 }
			var retData={};
			var rows = $('#dg').datagrid('getSelected');
			retData.districtId = rows.districtId;
			retData.seller = rows.seller;
			retData.docid = rows.docid;
			retData.menuid = rows.menuid;
			data=$.toJSON(retData);
			$('#dg').datagrid('clearSelections');
			$.ajax({
				url : '/ddxq/seller/xqcs/removeData',
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
				url : '/ddxq/seller/xqcs/editInfo',
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