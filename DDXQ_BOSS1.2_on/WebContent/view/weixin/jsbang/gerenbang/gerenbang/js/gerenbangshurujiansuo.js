﻿$(function(){
$(".main-limian ul .geren2").click(function(){
			var boor=$(this).parents(".main-limian").find(".tankuang-Y").is(":hidden");
			$(".main-limian").find(".tankuang-Y").slideUp();
				if(boor){
					$(this).parents(".main-limian").find(".tankuang-Y").slideDown();
				}

		});
});
window.onload=function()
  {
    
      objmy=document.getElementById("mytitle");
	  objcontent=document.getElementById("content");
	  objcontent.style.display="none";
	  objmy.style.background="url(img/jiantou_03.png) no-repeat 98% center";
	  objmy.style.backgroundSize='20px';
	  objmy.onclick=function()
	  {
	       if(objcontent.style.display=="block")
		   {
		      objcontent.style.display="none";
			  objmy.style.background="url(img/jiantou_03.png) no-repeat 98% center";
			  objmy.style.backgroundSize='20px';
		   }
		   else
		   {
		      objcontent.style.display="block";
			  objmy.style.background="url(img/jiantou_05.png) no-repeat 98% center";
			  objmy.style.backgroundSize='20px';
		   }
	  }

 function checkMobile(str) {
					var re = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/; if(re.test(str)) { return true; }else{return false;}}
		$(function(){
			var foo = function(){
				var mobile = $('.xingming input').val();
					if(!mobile.length){
					alert('请输入姓名')
					return false;
				}
			}
			$('.weui3_btn').click(function(){
				if(foo());
				if(!$('.xingming select').val()){
					alert('请选择领域!')
					return false;
				}else{
				}
			})
		})
} 