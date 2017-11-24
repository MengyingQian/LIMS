$(document).ready(function(){
	var flag = false;
	$('.geren').click(function(){
				if(false==flag){
					$(this).children('.jilu').slideDown();
					flag = true; 
				}else{
					$(this).children('.jilu').slideUp();
					flag=false;
				}
		});
});