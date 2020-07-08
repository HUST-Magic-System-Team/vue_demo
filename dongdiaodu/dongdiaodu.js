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

	});
	
	$(window).scroll(function() {
	  if ($(window).scrollTop() > 400) {
	    $("#scrollUp").fadeIn(200);
	  } else {
	    $("#scrollUp").fadeOut(200);
	  }
	});
	    //点击返回顶部500ms的滑动效果
   $('#scrollUp').on('click',function () {
	       $('html,body').animate(
		   {
			   scrollTop:0,
		   },
		   1500,"easeInOutExpo");
	   })

})
