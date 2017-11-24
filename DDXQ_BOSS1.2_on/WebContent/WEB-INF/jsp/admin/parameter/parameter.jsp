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
			
		});
		function addParameter(flag){
			var type="";
			if(flag==1){
				type="jmly";
			}else if(flag==2){
				type="xqzb";
			}else if(flag ==3){
				type="ztqy";
			}else if(flag ==4){
				type="xqtp";
			}else{
				return false;
			}
			var rediskey="districtid:"+$("#disid").val()+":configs:"+type+":url";
			var value=$("#"+type).val();
			if(value.length<3){
				alert("请正确输入");
				return false;
			}
			var data={};
			data.rediskey=rediskey;
			data.value=value;			
			$.ajax({
				url : '/ddxq/admin/parameter/add',
				data : $.toJSON(data),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						alert("添加成功");				
					}else{
						alert("添加失败");
					}				
				},
				error : function(xhr) {
					alert("添加失败");
				}
			});		
		}

		function searchParameter(){
			var rediskey=$("#rediskey2").val();
			var data={};
			data.rediskey=rediskey;
			if(rediskey.length<4){
				alert("请正确输入");
				return false;
			}
			$.ajax({
				url : '/ddxq/system/parameter/search',
				data : $.toJSON(data),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				async: false,
				success : function(data) {
					if(data.success==true){
						$("#result").textbox('setValue',data.value);				
					}else{
						alert("查询失败");
					}				
				},
				error : function(xhr) {
				}
			});		
		}
</script>
</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;">
  <div style="padding:5% 20%;">   
	<div class="easyui-panel" title="添加参数配置" style="width:100%;max-width:1000px;padding:20px 20px;">
			<div style="margin-bottom:5px">
				<input data-options="prompt:'必须带有http://'" id="jmly" name="jmly"class="easyui-textbox" label="居民留言URL:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a sytle="" onclick="addParameter(1)" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
			<div style="margin-bottom:15px">
			</div>
			<div style="margin-bottom:5px">
				<input data-options="prompt:'必须带有http://'" id="xqzb" name="xqzb"class="easyui-textbox" label="小区直播URL:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a  onclick="addParameter(2)" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
			<div style="margin-bottom:15px">
			</div>
			<div style="margin-bottom:5px">
				<input data-options="prompt:'必须带有http://'" id="ztqy" name="ztqy"class="easyui-textbox" label="组团去游URL:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a onclick="addParameter(3)" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
			<div style="margin-bottom:15px">
			</div>
			<div style="margin-bottom:5px">
				<input data-options="prompt:'必须带有http://'" id="xqtp" name="xqtp"class="easyui-textbox" label="小区投票URL:" labelPosition="top" style="width:100%;height:52px">
			</div>
			<div>
				<a onclick="addParameter(4)" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
			</div>
		</div>
	</div>
	<div>
			<input type="hidden" id="disid" name="disid" value="${sessionScope.districtId}" /> 
		</div>
</body>
</html>