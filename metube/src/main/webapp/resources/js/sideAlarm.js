/**
 * 알림을 누를시 슬라이드 메뉴가 나온다.
 * @returns
 */
$(".btn").click(function() {
	$("#menu,.page_cover,html").addClass("open");
	window.location.hash = "#open";
});

window.onhashchange = function() {
	if (location.hash != "#open") {
		$("#menu,.page_cover,html").removeClass("open");
	}
};