window.onload=function(){
	var Phone=document.getElementById('phone');
	var Tijiao=document.getElementById('showToast_tijiao');
	var Dia=document.getElementById('dialog2');
	var Queding=document.getElementById('queding');
	Tijiao.onclick=function(){
		if(Phone.value==''){
			Dia.style.display='block'
		}
	}
	Queding.onclick=function(){
		if(Dia.style.display=='block'){
			Dia.style.display='none'
		}
	}	
}
