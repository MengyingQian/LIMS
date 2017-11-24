$(document).ready(function(){
	$('#xiugai').click(function(){
		if ($('#shuxie').css('display','none')) {
			$('#shuxie').css('display','block');
			$('#xiugaibianti').text($('#bianti').text());
		}
	});
	$('#dialogButton0').click(function(){
		if ($('#shuxie').css('display','block')) {
			$('#shuxie').css('display','none');
		}
	});
});
