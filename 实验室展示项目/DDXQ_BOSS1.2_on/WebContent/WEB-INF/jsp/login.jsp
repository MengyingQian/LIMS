<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
	<base href="<%=basePath%>">	
    <meta charset="UTF-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
    <meta name="copyright" content="All Rights Reserved, Copyright (C) 2016, DDXQ" />
    <title>智慧小区运营支撑平台</title>
    <link rel="stylesheet" type="text/css" href="/view/pub/basic/easyui_1.3.4/themes/default/easyui.css" />
    <script src="/view/pub/basic/js/jquery-2.2.3.min.js" type="text/javascript"></script>
    <script src="/view/pub/basic/js/jquery.json.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/view/pub/basic/easyui_1.3.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/view/pub/basic/easyui_1.3.4/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="/view/pub/basic/css/supersized.css">
	<link rel="stylesheet" href="/view/pub/basic/css/login.css">
	<link rel="stylesheet" href="/view/pub/basic/css/bootstrap.min.css" >	
	<script type="text/javascript" src="/view/pub/basic/js/jquery.form.js"></script>
	<script type="text/javascript" src="/view/pub/basic/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="/view/pub/basic/js/tooltips.js"></script>
	<script type="text/javascript" src="/view/pub/basic/js/login.js"></script>
	<script src="/view/pub/basic/js/supersized.3.2.7.min.js"></script>
	<script src="/view/pub/basic/js/supersized-init.js"></script>
	<script src="/view/pub/basic/js/scripts.js"></script>
	<style type="text/css">
		body {
			font-size: 16px;  font-family: '微软雅黑' ;
		}
	</style>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#captcha").click(function(){
	    		$(this).attr("src", "<%=basePath%>/Captcha.jpg?time=" + new Date());
	    		return false;
	    		
	    	});
	        $('#submit').click(function(){
				//show_loading();	
				if($('#account').val() == ''){
					show_err_msg('账号还没填呢！');	
					$('#account').focus();
				}else if($('#password').val() == ''){
					show_err_msg('密码还没填呢！');
					$('#password').focus();
				}else if($('#j_captcha').val() == ''){
					show_err_msg('验证码还没填呢！');
					$('#j_captcha').focus();
				}else{
					var retData={};
					retData.account=$('#account').val();
					retData.password=$('#password').val();
					retData.captcha=$('#j_captcha').val() ;
					$.ajax({
						url : '/ddxq/boss/loginManage/mainWork',
						data : $.toJSON(retData),
						type : 'post',
						dataType : 'json',
						contentType : 'application/json',
						cache : false,
						async: false,
						success : function(data) {
							if(data.success==true){
								$("#ff").submit();							
							}else{
								if(data.error=="1"){	
									 $("#captcha").click();
									show_err_msg('验证码错误！');	
								}
								if(data.error=="2"){
									 $("#captcha").click();
									show_err_msg('密码错误！');	
								}
								if(data.error=="3"){
									 $("#captcha").click();
									show_err_msg('账号不存在！');	
								}
								if(data.error=="5"){
									 $("#captcha").click();
									show_err_msg('账号被停用，请联系平台管理员！');	
								}
								if(data.error=="4"){
									 $("#captcha").click();
									show_err_msg('账号被停用，请联系小区管理员！');	
								}
							}				
						},
						error : function(xhr) {
						}
					});		
				}
			});
	        $("#captcha").click();
	      
	    });
	</script>

</head>
<body style="font-family: verdana, helvetica, arial, sans-serif;
    font-size: 0px;
    padding: 0px;
    margin: 8px;">

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<h3>智慧小区后台管理系统</h3>
			</div>	
			<div class="login_form">
				<form action="/ddxq/boss/loginManage/enter" method="post" id="login_form" >
					<div class="form-group">
						<label for="j_username" class="t">帐　号：</label> 
						<input id="account" value="" name="account" type="text" class="form-control x319 in" >
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						<input id="j_captcha" name="j_captcha" type="text" class="form-control x164 in">
						<img class="m"  width="150" title="点击更换"  alt="点击刷新验证码" src="<%=basePath%>/Captcha.jpg" id="captcha"/>
					</div>
					
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit"class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2016 <a href="#">系统登陆</a></div>
	</div>
</div>
<form action="/ddxq/boss/loginManage/enter" method="post" id="ff" >
</form>
</body>
</html>