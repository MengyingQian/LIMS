//window.onload=function()
//{
//	var Nan=document.getElementById("nan");
//	var Nv=document.getElementById("nv");
//	var NN=document.getElementById("nannv");
//	var XingBie=document.getElementById("xingbie");
//	var TK=document.getElementById("tankuang");
//	XingBie.onclick=function()
//	{
//		if(var TK.style.display=="block")
//		{
//			TK.style.display="none";
//		}
//		else
//		{
//			TK.style.display="block";
//		}
//	}
//}
$(document).ready(function() {
	$('#xingbie').click(function(){
		$('#tankuang').css('display','block');
	});
	$('#zhezhao').click(function(){
		$('#tankuang').css('display','none');
	});
	$('#nan').click(function(){
		$('#nannv').html('君子');
		$('#tankuang').css('display','none');
//		alert('我是君子')
	});
	$('#nv').click(function(){
		$('#nannv').html('淑女');
		$('#tankuang').css('display','none');
//		alert('我是淑女')
	});
});
