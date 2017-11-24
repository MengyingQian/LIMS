$(document).ready(function(){
	var storage = window.localStorage;
	var allIndex = -1;
	var searchIndex = -1;
	var time_start= new Date();
	var clock_start = time_start.getTime(); 
	var i_total_secs = Math.round(clock_start); 
	
	
	var url = window.location.href;
	$("#url").val(url);

    var strUrl=location.href;
    var arrUrl=strUrl.split("/");
    var strPage=arrUrl[arrUrl.length-1];
    $("#jspname").val(strPage);
 
    var year = time_start.getFullYear();
    var month = time_start.getMonth( )+1;
   	var day = time_start.getDate();
  	var hour = time_start.getHours( );
  	var minute =time_start.getMinutes( ); 
   	var second = time_start.getSeconds( ); 
   	var min = time_start.getMilliseconds();
  	if (minute < 10)
  			minute="0"+minute; 
  	if (second < 10)
  	second="0"+second;  
  	$("#start_time").val(time_start.toLocaleString());
  	

	allIndex = -1;
	searchIndex = -1;

	var jspoint = $("#jspoint").val();
	

	var retString = "";
	var added = parseFloat(jspoint) + 0.5;
	var normalized = Math.floor(added);
	var completeStar = Math.floor(normalized / 2);
	var halfStar = normalized % 2;
	var greyStar = 5 - completeStar - halfStar;
	while(completeStar > 0 ){
		retString += "<i class=\"icon-star\"></i>";
		completeStar--;
	}
	while(halfStar > 0 ){
		retString += "<i class=\"icon-star-half\">"
						+ "<i class=\"icon-star\"></i>"
						+ "<i class=\"icon-star no\"></i>"
					+"</i>";
		halfStar--;
	}
	while(greyStar > 0 ){
		retString += "<i class=\"icon-star no\"></i>";
		greyStar--;
	}
	
	$("#star").append(retString);
	
//		if($("#pulltype").val()=="search"){
//			storage.setItem("search",$("#sql").val());
//		}
//		gengduo();
});