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
	<script src="/view/pub/basic/js/jquery-2.2.3.min.js"type="text/javascript"></script>
	<script src="/view/pub/basic/js/jquery.json.min.js"type="text/javascript"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"src="/view/pub/basic/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">	
		$(function(){
				
		})
	</script>
 <script type="text/javascript">
        
         function submitEdit(){
        	 
			var retData={};
			var year = "2017";
			retData.year=year;
			alert(1);
			$.ajax({
					url : '/ddxq/system/poweruser/searchyear',
					data : $.toJSON(retData),
					type : 'post',
					dataType : 'json',
					contentType : 'application/json',
					cache : false,
					async: false,
					success : function(data) {
						if(data.success==true){
							alert("success!");	
							//sex =data.sex;
							//alert(sex);
							alert(data.total);
							console.log(data);
						}else{
							alert(2);
						}
					
				
					},
					error : function() {
						alert("faild!");	
					}
				});
			alert(3);
		
		}
         </script>
</head>
<body>
<a  onclick="submitEdit()">2017</a>
</body>
</html>