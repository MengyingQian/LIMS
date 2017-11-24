<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/view/pub/basic/jsp/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<meta name="format-detection" content="telephone=no">
		<script type="text/javascript" src="/view/pub/basic/js/jquery-2.2.3.min.js"></script>
		<script src="/view/pub/basic/js/jquery.json.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="/view/pub/basic/js/jquery.cookie.js"></script>
		<link rel="stylesheet" href="/view/pub/basic/css/category.css">
		<title>小区超市</title>

		<script type="text/javascript">	
		Array.prototype.indexOf = function(val) {
			for (var i = this.length-1; i >-1 ; i--) {
				if (this[i] == val)
					return i;
			}
			return -1;
		};
		Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
				this.splice(index, 1);
			}
		};
		</script>
		<script type="text/javascript">		
		$(function(){
			//clearCart();			
			$(".mask").on("click",function () {
				listRemove();
			    $(".mask").css("display","none");
			    $(".cartContent").css({
			        "transform": "translateY(0)",
			        "display": "block"
			    })
			    $(".footer .cart").css({
			        "transform": "translateY(0)"
			    })
			    $(".footer .mon").css({
			        "transform": "translateX(0)"
			    })
			});
			$(".cont p").on("click",function (e) {
			    $(".cont p").removeClass("select");
			    $(this).addClass("select");
			    showGoods($(this));
			    e.stopPropagation();
			});
			
			var cateid= $.cookie("cateid");
			if ($.cookie("docList")!=undefined){
				docList = $.cookie("docList").split(",");
				$totalCount=parseInt($.cookie("totalCount"));
				$totalMon=parseFloat($.cookie("totalMon"));
				checkTotal();
			}	
			if(cateid==undefined){
				//没有点击商品，进入的默认	显示
				var qq=""+$("#morenid").val();
				if($("#havediscount").val()=="1"){//没有今日优惠,需要点击默认id的父元素主菜单
					$("#"+qq).parent().prev().click();
				}else{
					$("#"+qq).click();//有今日优惠，直接点击
				}		
			}else{	
						var type = $.cookie("catetype");
						if (type == "2") {//点击一级菜单的返回
							var th = $("#" + cateid).parent().prev().parent();
							$("#list .ul li").removeClass("select");
							if (th.hasClass("click")) {
								$("#list .more .cont").css("display", "none");
								$("#list .more .fir").removeClass("select fir");
								th.removeClass("click");
							} else {
								$("#list .ul li").removeClass("click");
								th.addClass("select");
								$("#list .more .cont").css("display", "none");
								$("#list .more .fir").removeClass("select fir");
								if (th.hasClass("more")) {
									th.addClass("click");
									th.find(".cont").css("display", "block");
									th.children("p").addClass("fir");
									th.find(".fir").css("borderLeft", "none");
								}
							}
							$("#" + cateid).click();
						} else {//点击二级菜单的返回
							$("#" + cateid).parent().prev().click();
						}
				}
			});
		</script>
		<script type="text/javascript">

		var docList=[];
		 function  add(index) {
			 var docid=$("#docid"+index).val();
			 if(docList.indexOf(docid)==-1){
				 docList.push(docid);
			 }
			
		    $("#add"+index).parent().children().removeClass("hide").addClass("show");
		    var $count = $("#add"+index).prev(),
		    num = Number($count.html()) + 1;
		    $count.html(num);
		    var price = $("#add"+index).parent().attr("data-price");			
		    $totalCount += 1;
		    $totalMon += Number(price);
		    var info=num+"|"+price+"|"+$("#caption"+index).val()+"|"+$("#img"+index).val();		    
		    $.cookie(docid,info,{path:'/'});
		    checkTotal();
		};

		 function  reduce(index){
			var docid=$("#docid"+index).val();
		    var $count = $("#reduce"+index).next(),
		        num = Number($count.html());
		    var price = $("#reduce"+index).parent().attr("data-price");
		    if (num == 1) {
		        num = 0;
		        docList.remove(docid);
		        $.cookie(docid,'',{path:'/',expires:-1});
		        $("#reduce"+index).removeClass("show").addClass("hide");
		        $count.removeClass("show").addClass("hide");
		    } else {		    	
		        num--;
		        var info=num+"|"+price+"|"+$("#caption"+index).val()+"|"+$("#img"+index).val();
		        $.cookie(docid,info,{path:'/'});
		    }
		    $count.html(num);
		    $totalCount -= 1;
		    $totalMon -= Number(price);
		    checkTotal();
		};
		function listRemove(){
			for(var i=docList.length-1; i>-1; i--){
				if(docList[i]=="No"){
					docList.remove("No");
				}
			}
			var id=$.cookie("cateid");
			$("#"+id).click();
			//cateid($.cookie("cateid"));
		}	
		
		function showCart(){
			$("#cartlist").empty();
			for(var i=docList.length-1; i>-1; i--){
				if(docList[i]=="No"){
					docList.remove("No");
				}
			}
			for (var i=0; i<docList.length; i++){	
				var doc=docList[i];
			var str=$.cookie(docList[i]);
			var arr=str.split("|");
			var ss="<li id=\"li"+i+"\"class=\"a5y single\">"+
			"<a class=\"a62\" href=\"javascript:void 0\">"+
				"<table class=\"a63\">"+
					"<tbody>"+
						"<tr>"+
							"<td style=\"width:62px;\">"+
								"<img class=\"a64\" src=\""+arr[3]+"\"> </td>"+
							"<td>"+
								"<div class=\"a67\">"+arr[2]+"</div>"+
								"<span id=\"totalprice"+i+"\"class=\"a60\">"+"￥"+arr[1]*arr[0]+"</span>"+
							"</td></tr></tbody></table></a>"+
			"<a onclick=\"reduceCart("+i+")\"class=\"a65\"></a>"+
			"<a id=\"shownum"+i+"\"class=\"a68\">"+arr[0]+"</a>"+
			"<a onclick=\"addCart("+i+")\"class=\"a66\"></a></li>";
			$("#cartlist").append(ss);
			}
			
			$("#selectcount").text($totalCount);
		};
		function reduceCart(i){
			var docid =docList[i];
			var str=$.cookie(docid);
			var arr=str.split("|");
			var num=parseInt(arr[0]);
			var price = parseFloat(arr[1]);	
			if(num==1){
				num=0;
				docList[i]="No";
			    $.cookie(docid,"",{path:'/',expires:-1});
			    $("#li"+i).remove();
			    var trans = -($(".cartContent").height() + 48);
		        $(".mask").css("display","block");
		        $(".cartContent").css({
		            "transform": "translateY("+ trans +"px)",
		            "display": "block"
		        });
		        $(".cart").css({
		            "transform": "translateY("+ trans +"px)"
		        });
		        $(".footer .mon").css({
		            "transform": "translateX(-60px)"
		        });
			}
			num=num-1;
			$("#shownum"+i).text(num);
			
			var totalprice=num*parseFloat(price);
		 	$("#totalprice"+i).text("￥"+totalprice);
			
		 	var info=num+"|"+price+"|"+arr[2]+"|"+arr[3];	
			$.cookie(docid,info,{path:'/'});
			
			$totalCount -= 1;
			$totalMon -= Number(price);
			checkTotal();
			 $("#selectcount").text($totalCount);
		}
		function addCart(i){
			var docid =docList[i];
			var str=$.cookie(docid);
			var arr=str.split("|");
			  
			var num=parseFloat(arr[0])+1;
			$("#shownum"+i).text(num);
			var totalprice=num*parseFloat(arr[1]);
		 	$("#totalprice"+i).text("￥"+totalprice);
		    var price =arr[1];			
		    
		    $totalCount += 1;
		    $totalMon += Number(price);
		    
		    var info=num+"|"+price+"|"+arr[2]+"|"+arr[3];		    
		    $.cookie(docid,info,{path:'/'});
		    $("#selectcount").text($totalCount);
		    checkTotal();
		}

		function tianjia(good,cate,index) {
			var oid=$("#oid").val();
			var retString="";
			var flag=docList.indexOf(good.docid);			
			if(flag==-1){
				retString = 
					"<li>"+
					"<a  onclick=turn() href=\"/ddxq/wx/fwt/detail?docid="+good.docid+"&"+"oid="+oid+"\">"+
					"<img src=\""+good.photo_min+"\" alt=\"\">"+
				    "<dl class=\"dl\">"+
					"<dt>"+good.caption+"</dt>"+
					"<dd><span>"+good.label+"</span></dd>"+
					"<dd><label><em>￥</em>"+good.price+" /"+good.unit+"</label></dd>"+
				    "</dl>"+
				    "</a>"+
				    "<div class=\"Box\" data-price=\""+good.price+"\">"+
					"<span class=\"reduce hide\" id=\"reduce"+index+"\" onclick=reduce("+index+")></span>"+
					"<label class=\"count hide\">0</label>"+
					"<span class=\"add\" id=\"add"+index+"\" onclick=add("+index+")></span>"+
					"<input id=\"docid"+index+"\" type=\"hidden\" value=\""+good.docid+"\">"+
					"<input id=\"caption"+index+"\" type=\"hidden\" value=\""+good.caption+"\">"+
					"<input id=\"img"+index+"\" type=\"hidden\" value=\""+good.photo_min+"\">"+
				    "</div>"+
				    "</li>";
			}else{
				var info =$.cookie(good.docid);
				var num=info.split("|")[0];
				retString = 
					"<li>"+
					"<a  onclick=turn() href=\"/ddxq/wx/fwt/detail?docid="+good.docid+"&"+"cate="+cate+"\">"+
					"<img src=\""+good.photo_min+"\" alt=\"\">"+
				    "<dl class=\"dl\">"+
					"<dt>"+good.caption+"</dt>"+
					"<dd>"+					
					"<span>"+good.label+"</span>"+
					"</dd>"+
					"<dd><label><em>￥</em>"+good.price+" /"+good.unit+"</label></dd>"+
				    "</dl>"+
				    "</a>"+
				    "<div class=\"Box\" data-price=\""+good.price+"\">"+
					"<span class=\"reduce\" id=\"reduce"+index+"\" onclick=reduce("+index+")></span>"+
					"<label class=\"count\">"+num+"</label>"+
					"<span class=\"add\" id=\"add"+index+"\" onclick=add("+index+")></span>"+
					"<input id=\"docid"+index+"\" type=\"hidden\" value=\""+good.docid+"\">"+
					"<input id=\"caption"+index+"\" type=\"hidden\" value=\""+good.caption+"\">"+
					"<input id=\"img"+index+"\" type=\"hidden\" value=\""+good.photo_min+"\">"+
				    "</div>"+
				    "</li>";
			}
			
                return retString;

		}
		
		
		function pay(){
			
			for(var i=docList.length-1; i>-1; i--){
				if(docList[i]=="No"){
					docList.remove("No");
				}
			}
 	 		params={};
		 	params.oid=$("#oid").val();
		 	if(docList.length<1){
		 		return false;
		 	}
		 	$.ajax({
				url: '/ddxq/wx/fwt/checkInfo',
				data: $.toJSON(params),
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				cache: false,
				success: function(data) {
					if(data.success == "0") {
						if(docList.length==0){
							$.cookie("docList","",{path:'/',expires:-1});
						}else{
							$.cookie("docList",docList.join(","),{path:'/'});
						}	
						$.cookie("totalCount",$totalCount,{path:'/'});
						$.cookie("totalMon",$totalMon,{path:'/'});	
						$("#form").submit();
					}else if(data.success == "1") {
						alert("您的手机未填写，请到[互动区]-[个人中心],点击头像认证");
						$("#form2").submit();
					}else if(data.success == "2"){
						alert("您的信息未完善，请到[互动区]-[个人中心],填写收货地址");
						$("#form2").submit();
					}
				},
				error: function(xhr) {

				}
			});		
						
		}
		function turn(){
			if(docList.length==0){
				$.cookie("docList","",{path:'/',expires:-1});
			}else{
				$.cookie("docList",docList.join(","),{path:'/'});
			}	
			$.cookie("totalCount",$totalCount,{path:'/'});
			$.cookie("totalMon",$totalMon,{path:'/'});	
			
		}
	
		function clear(){					

			$("#menu").empty();
		}
		function showGoodss(obj){
		    $(".cont p").removeClass("select");
		    $.cookie("catetype","1",{path:'/'});
			var ps=$(obj).next().children("p");
			 var retCate="";
			 var districtId = $("#districtid").val();
			 for (var i = 1; i < ps.length+1; i++) { 
				 var aa=$(obj).next().children("p:nth-child("+i+")").attr("storeid");	
				 retCate=retCate+","+aa;							
			}
			retCate=retCate.substring(1,retCate.length);
			var params = {};
			params.districtId = districtId;
			params.cate = retCate;
			$.ajax({
				url: '/ddxq/wx/fwt/getcateAll',
				data: $.toJSON(params),
				type: 'post',
				dataType: 'json',
				contentType: 'application/json',
				cache: false,
				success: function(data) {
					if(data.success == true) {
						clear();
						document.getElementById("goodscount").innerHTML=data.goodscount;
						var list_all = data.list_all;						
						$.each(list_all, function(index) {
							var goods = list_all[index];
							$("#menu").append(tianjia(goods,goods.menuid,index));
						});
					}
				},
				error: function(xhr) {
					alert("网络异常");
				}
			});		
			
		}
		function showGoods(obj){
			
			    var cateid = $(obj).attr("storeid");
			    var districtId = $("#districtid").val();
			    $.cookie("cateid",cateid,{path:'/'});
			    $.cookie("catetype","2",{path:'/'});
				var params = {};
				params.districtId = districtId;
				params.cate = cateid;

				$.ajax({
					url: '/ddxq/wx/fwt/getcate',
					data: $.toJSON(params),
					type: 'post',
					dataType: 'json',
					contentType: 'application/json',
					cache: false,
					success: function(data) {
						if(data.success == true) {
							clear();
							document.getElementById("goodscount").innerHTML=data.goodscount;
							var list_all = data.list_all;						
							$.each(list_all, function(index) {
								var goods = list_all[index];
								$("#menu").append(tianjia(goods,cateid,index));
							});
						}
					},
					error: function(xhr) {
						alert("网络异常");
					}
				});		
		}
		
		function clearCart(){
			var id=$.cookie("cateid");
			var type= $.cookie("catetype");
			clearCookie();
			$.cookie("cateid",id,{path:'/'});
			$.cookie("catetype",type,{path:'/'});
			//清空全局变量
			$totalCount = 0;
			$totalMon = 0;
			docList=[];			
			checkTotal();
			$(".mask").click();
		}
		function clearCookie(){
			var cookies = document.cookie.split(";");  
		    for (var i = 0; i < cookies.length; i++) {  
		        var cookie = cookies[i];  
		        var eqPos = cookie.indexOf("=");  
		        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;  
		        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";  
		    }  
			if(cookies.length > 0){  
			    for (var i = 0; i < cookies.length; i++) {  
			        var cookie = cookies[i];  
			        var eqPos = cookie.indexOf("=");  
			        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;  
			    var domain = location.host.substr(location.host.indexOf('.'));  
			        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/; domain=" + domain;  
			    }  
			}  		
		}
	
		</script>
	</head>

	<body>
		<div class="vy sr hide">
			<div class="ss">
				<span class="sg">搜索店内商品</span>
			</div>
		</div>
		<div class="goods">
			<div class="list" id="list">
				<ul class="ul" id="ul">
					<c:if test="${no==1}">
					 <c:if test="${noother==1}">
						<c:forEach items="${list}" var="map">
		             		   <li class="more">
		             		   <p onclick="showGoodss(this)">${map['name']}</p>
		             		   <div class="cont">
			             		    <c:forEach items="${map['second']}" var="map2">
				             		   	<p style="font-size: 12px;text-align:right;padding-right: 10px;" id="${map2['id']}" storeid="${map2['id']}"  onclick="showGoods(this)" >${map2['name2']}</p>
									</c:forEach>  
								</div>           		   
		             		   </li>    
	           			 </c:forEach> 
           			 </c:if> 
					</c:if>
					
					<c:if test="${no==0}">
					 <c:if test="${noother==1}">
						<li  id="${id}"storeid="${id}"  onclick="showGoods(this)" >${name}</li>
						<c:forEach items="${list}" var="map">
		             		   <li class="more">
		             		   <p onclick="showGoodss(this)">${map['name']}</p>
		             		   <div class="cont">
			             		    <c:forEach items="${map['second']}" var="map2">
				             		   	<p  style="font-size: 12px;text-align:right;padding-right: 10px;" id="${map2['id']}" storeid="${map2['id']}"  onclick="showGoods(this)" >${map2['name2']}</p>
									</c:forEach>  
								</div>           		   
		             		   </li>    
	           			 </c:forEach> 
           			 	</c:if>
					</c:if>
					
					<c:if test="${no==1}">
					 <c:if test="${noother==0}">
						
						<c:forEach items="${list}" var="map">
		             		   <li class="more">
		             		   <p onclick="showGoodss(this)">${map['name']}</p>
		             		   <div class="cont">
			             		    <c:forEach items="${map['second']}" var="map2">
				             		   	<p style="font-size: 12px;text-align:right;padding-right: 10px;" id="${map2['id']}" storeid="${map2['id']}"  onclick="showGoods(this)" >${map2['name2']}</p>
									</c:forEach>  
								</div>           		   
		             		   </li>    
	           			 </c:forEach> 
	           			 <li  id="${id2}"storeid="${id2}"  onclick="showGoods(this)" >${name2}</li>
           			 	</c:if>
					</c:if>
						
					<c:if test="${no==0}">
					 <c:if test="${noother==0}">
						<li  id="${id}"storeid="${id}"  onclick="showGoods(this)" >${name}</li>
						<c:forEach items="${list}" var="map">
		             		   <li class="more">
		             		   <p onclick="showGoodss(this)">${map['name']}</p>
		             		   <div class="cont">
			             		    <c:forEach items="${map['second']}" var="map2">
				             		   	<p style="font-size: 12px;text-align:right;padding-right: 10px;" id="${map2['id']}" storeid="${map2['id']}"  onclick="showGoods(this)" >${map2['name2']}</p>
									</c:forEach>  
								</div>           		   
		             		   </li>    
	           			 </c:forEach> 
	           			 <li  id="${id2}"storeid="${id2}"  onclick="showGoods(this)" >${name2}</li>
           			 	</c:if>
					</c:if>

				</ul>
			</div>
			<div class="tit">本类商品<span id="goodscount">(${goodscount})</span></div>
			<div class="conRig">
				<div class="context" id="context">
					<div class="con"  >
						<ul class="menu">
						<div id="menu">

							</div>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="zi">
				<div class="cart">
					<i class="total hide">0</i>
				</div>
				<p class="mon kong">购物车是空的</p>
				<div class="pay" onclick="pay()">下订单</div>
			</div>

			<div class="cartContent">
				<i class="a1"></i>
				<div class="a5j">
					
					<!--checked选中<span class="a6k a6l checked">全选</span>加class  checked-->
					<p ><span>共</span><span id="selectcount"></span><span>件</span></p>
					<a  class="a5k"  onclick="clearCart()">清空购物车</a>
				</div>
				<div class="a5m">
					<div class="a5w single">
						<ul class="minicart-goods-list single">
						<div id="cartlist">
							<li class="a5y single">
								<span class="a6k a5z checked"></span>
								<!--checked选中加class  checked-->
								<a class="a62" href="javascript:void 0">
									<table class="a63">
										<tbody>
											<tr>
												<td style="width:62px;">
													<img class="a64" src="/view/pub/basic/img/thumbnails.jpg"> </td>
												<td>
													<div class="a67">香菜（约）200g/份</div>
													<span class="a60">￥1.90</span>
												</td>
											</tr>
										</tbody>
									</table>
								</a>
								<a class="a65"></a>
								<em class="a68">2</em>
								<a class="a66"></a>
							</li>
							
							</div>
						</ul>
					</div>

				</div>
			</div>
		</div>
		<div class="mask"></div>
		
		<input type="hidden" id="districtid" name="districtid" value="${districtId}" />
		<input type="hidden" id="morenid" name="morenid" value="${morenid}" />
		<input type="hidden" id="havediscount" name="havediscount" value="${no}" />
		<form  id="form" action="/ddxq/wx/fwt/downorder">
			<input type="hidden" id="oid" name="oid" value="${oid}" />
		</form>
		<form  id="form2" action="/ddxq/wx/hdq/grzx/list2">
			<input type="hidden" id="oid2" name="oid" value="${oid}" />
		</form>
		
	</body>

</html>
<script src="/view/pub/basic/js/zepto.min.js"></script>
<script src="/view/pub/basic/js/category.js"></script>