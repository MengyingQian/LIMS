$(document).ready(function(){
	var flag = false;
	var num = parseInt($('.abc').text());
	var a=1;
	$('.zan').click(function(){
		if(false==flag){
			$(this).text(eval($(this).text())+a);
			$(this).addClass('zan2');
			flag = true; 
		}else{
$(this).text(eval($(this).text())-a);
			$(this).removeClass('zan2');
			flag=false;
		}
	});
});