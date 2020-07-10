$(function() {
	$(".c11 .btn").click(function() {
		var $video = $(".video");
		var $vi = $(".vi");
		$vi.slideDown(500);
		$video.trigger("play");

	});
	$(".vi .btn2").click(function() {
		var $video = $(".video");
		var $vi = $(".vi");
		$vi.slideUp(500);
		$video.trigger("pause");

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
	$(".nav ul li").mouseenter(function(){//鼠标移入
	    var left = $(this).children().position().left;
	    var width = $(this).children().width();
	    $(".bottomLine").stop().animate({
	        left:left,
	        width:width
	    },500);
	});
	$(".nav").mouseleave(function(){
		var left = $(".active").position().left;
		var width = $(".active").width();
		$(".bottomLine").stop().animate({
		    left:left,
		    width:width
		},500);
		
	})

})
