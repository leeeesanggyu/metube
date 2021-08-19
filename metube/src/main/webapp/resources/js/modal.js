$(document).ready(function( $ ){     
	$(".openPopup").on("click", function(event) {  //팝업오픈 버튼 누르면
		$("#popup01").show();   //팝업 오픈
		$("body").append('<div class="backon"></div>'); //뒷배경 생성
	});

	$("body").on("click", function(event) { 
		if(event.target.className == 'close' || event.target.className == 'backon'){
			$("#popup01").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
			$(".backon").hide();
		}
	});

});
