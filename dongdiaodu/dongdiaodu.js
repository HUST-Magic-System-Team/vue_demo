$(function() {
	$(".c11 .btn").click(function() {
		var $video = $(".video");
		var $vi = $(".vi");
		$vi.slideDown(500);
		$video.play();

	});
	$(".vi .btn2").click(function() {
		var $video = $(".video");
		var $vi = $(".vi");
		$vi.slideUp(500);
		$video.pause()

	})
})
