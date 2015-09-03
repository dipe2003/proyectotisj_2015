$(document).ready(function(){
	var rotateFix = 0;
	$( "#add" ).mouseenter(function() {
		$(function() {
			$({deg: rotateFix}).animate({deg: 360}, {
				duration: 2000,
				step: function(now) {
					$('#add').css({
						transform: 'rotate(' + now + 'deg)'
					});
					rotateFix = now;
				}
			});
		});
	}).mouseleave(function() {
		  $(function() {
			$({deg: rotateFix}).animate({deg: 0}, {
				duration: 2000,
				step: function(now) {
					$('#add').css({
						transform: 'rotate(' + now + 'deg)'
					});
					rotateFix = now;
				}
			});
		});
	});
});