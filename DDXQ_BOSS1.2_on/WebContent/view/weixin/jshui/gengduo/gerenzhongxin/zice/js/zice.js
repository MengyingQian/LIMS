window.onload=function(){
	var fushu=document.getElementsByClassName("comment-others")
	for(var i=0; i<fushu.length; i++){
		if(i%2==0){
			fushu[i].style.background="#f0f0f0";
		}
	}
}
