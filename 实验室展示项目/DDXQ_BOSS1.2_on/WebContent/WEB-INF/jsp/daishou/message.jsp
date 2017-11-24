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
		function checkMobile(str) { 
			var re = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/; 
			if(re.test(str)){
				return true; 
				}else{
					return false;
				}
		}
		function send(){
			retData={};
			var mobile = $("#mobile").val();
			var address = $("#address").val();
			var mibile2=$("#mibile2").val();
			retData.mobile = mobile;
			retData.address = address;
			retData.mibile2 = mibile2;
			if(!mobile.length){
				alert('请输入手机号');
				return false;
			}
			if(!checkMobile(mobile)){
				alert('手机号不正确');
				return false;
			} 
			if(!address.length){
				alert('请输入快递站点地址');
				return false;
			}
			if(!mibile2.length){
				alert('请输入快递站点电话');
				return false;
			}
			$.ajax({
				url : '/ddxq/employees/daishou/sendmessage',
				data : $.toJSON(retData),
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				cache : false,
				success : function(data) {
					if(data.success==true){
						alert("发送成功");		
					}else{
						alert("发送失败");
					}				
				},
				error : function(xhr) {
				}
			});
			return true;
		}
</script>
</head>
<body  style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin-top: 2px;
    margin-right: 2px;
    margin-left: 2px;" >
   <div style="padding:4% 20%;">
	 <div class="easyui-panel" title="发送短信通知" style="width:100%;max-width:1000px;padding:20px 20px; "> 
		<form id="ff">
		<div style="margin-bottom:20px">
			<input id="mobile"  type="tel" pattern="[0-9]*" class="easyui-textbox" label="发送到（手机号）:" labelPosition="top" style="width:100%;height:52px">
		</div>
		<div style="margin-bottom:20px">
			<input id="address" class="easyui-textbox" label="快递站点地址:" labelPosition="top" style="width:100%;height:52px" value="${kuaidi.employeeAddr }">
		</div>
		<div style="margin-bottom:20px">
			<input id="mibile2"  type="tel" pattern="[0-9]*" class="easyui-textbox" label="快递站点电话:" labelPosition="top" style="width:100%;height:52px" value="${kuaidi.employeeMobile }">
		</div>
		<div style="margin-bottom:20px">
			<a onclick="send()"class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">发送</a>
		</div>
		<div style="margin-bottom:20px">
			<button  type="reset" "class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">清空</button>
		</div>
		</form>
	 </div>
   </div>
</body>
</html>