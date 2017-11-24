<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript"
	src="/view/pub/basic/js/jquery-2.2.3.min.js"></script>
<script src="/view/pub/basic/js/jquery.json.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=dZ2Z266MTOVp8ylGITnE7wg0LzBOXYT8"></script>
<title>绘制小区边界</title>
<script type="text/javascript">
	var a,b,c,d;
	function setBorder() {
		retData = {};
		retData.point = retPoints.join(",");
		if (retPoints.length > 28) {
			alert("边界点太多，请重新绘制");
			retPoints = [];
			return false;
		}
		retData.id = $("#rowId").val();
		retData.x=(a+c)/2;
		retData.y=(b+d)/2;
		$.ajax({
			url : '/ddxq/system/districtManage/addBorderline',
			data : $.toJSON(retData),
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			cache : false,
			success : function(data) {
				if (data.success == true) {
					alert("添加成功");
				} else {
					alert("添加失败");
				}
			},
			error : function(xhr) {
			}
		});
	}
</script>

</head>
<body>
	<div style="width: 100%; height: 70%; border: 1px solid gray"
		id="container"></div>
	<p>
		<a>搜索小区:</a><input type="text" id="suggestId" size="20" value=""
			style="width: 280px;" /> <a id="searchResultPanel"
			style="border: 1px solid #C0C0C0; width: auto; height: auto; display: none;"></a>
		<input id="startBtn" type="button" onclick="startTool();"
			value="点击绘制小区边界" /> <input type="button"
			onclick="map.clearOverlays();document.getElementById('info').innerHTML = '';points=[];retPoints=[];"
			value="清除" /> <input type="button"
			onclick="map.clearOverlays();
    var polygon = new BMap.Polygon(points,{strokeWeight:1,
    	strokeStyle:'dashed',                                                                                                        //strokeOpacity:0.0,
		fillOpacity: '0.1',
		fillColor: 'bule',
		strokeColor: '#7D9EC0'});
     map.addOverlay(polygon);
     a=Math.max.apply(null,pointlng1);b=Math.min.apply(null,pointlat1) ;
     c=Math.min.apply(null,pointlng1);d=Math.max.apply(null,pointlat1);
     pointlng1=[];
     pointlat1=[];
     var newp = new BMap.Point((a+c)/2, (b+d)/2);
	 var marker = new BMap.Marker(newp);
	 map.addOverlay(marker);
     setBorder();
"
			value="提交边界" />

	</p>
	<div id="info"></div>
	<div id="l-map"></div>
	<input type="hidden" id="districtId" name="districtId"value="${districtId}" />
	<input type="hidden" id="rowId" name="rowId"value="${rowId}" />
</body>
</html>
<script type="text/javascript">
	function G(id) {
		return document.getElementById(id);
	}
	var map = new BMap.Map("container");
	map.centerAndZoom("北京", 15); // 初始化地图,设置中心点坐标和地图级别
	map.enableScrollWheelZoom();

	var ac = new BMap.Autocomplete( //建立一个自动完成的对象
	{
		"input" : "suggestId",
		"location" : map
	});
	ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = "
				+ value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = "
				+ value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district
				+ _value.street + _value.business;
		G("searchResultPanel").innerHTML = "onconfirm<br />index = "
				+ e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	function setPlace() {
		map.clearOverlays(); //清除地图上所有覆盖物
		function myFun() {
			var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp)); //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete : myFun
		});
		local.search(myValue);
	}

	var key = 1; //开关
	var newpoint; //一个经纬度
	var pointlng1 = [];
	var pointlat1 = [];
	var points = []; //数组，放经纬度信息
	var retPoints = [];
	var polyline = new BMap.Polyline(); //折线覆盖物
	function startTool() { //开关函数
		if (key != 1) {
			document.getElementById("startBtn").style.background = "red";
			document.getElementById("startBtn").value = "关闭状态";
			key = 1;
		} else {
			document.getElementById("startBtn").style.background = "green";
			document.getElementById("startBtn").style.color = "white";
			document.getElementById("startBtn").value = "开启状态";
			key = 0;
		}
	}
	map.addEventListener("click", function(e) { //单击地图，形成折线覆盖物
		newpoint = new BMap.Point(e.point.lng, e.point.lat);
		var pointlng = e.point.lng;
		var pointlat = e.point.lat;
		if (key == 0) {
			points.push(newpoint); //将新增的点放到数组中
			var x = e.point.lng + "";
			var y = e.point.lat + "";
			retPoints.push(x.substring(0, 9));
			retPoints.push(y.substring(0, 9));
			pointlng1.push(e.point.lng);
			pointlat1.push(e.point.lat);
			var marker = new BMap.Marker(newpoint);
			map.addOverlay(marker);
			var polyline = new BMap.Polyline(points, {
				strokeWeight:"1",
				strokeStyle:'dashed',                                                                                                        //strokeOpacity:0.0,
				fillOpacity: '0.1',
				fillColor: 'bule',
				strokeOpacity:"1",
				strokeColor: '#0000ff'}); //设置折线的点数组
			map.addOverlay(polyline); //将折线添加到地图上
		}
	});
</script>
