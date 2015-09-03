$(document).ready(function(){
	var tiempo=0;
	$('.docente').hide().each(function() {
		$(this).delay(tiempo).fadeIn('slow');
		tiempo += 200;
	});
	var blurFix = 0;
	$( ".docente" ).mouseenter(function() {
		var Docente = $(this);
		$(function() {
			$({blurRadius: 50}).animate({blurRadius: 0}, {
				duration: 200,
				easing: 'swing',
				step: function() {
					console.log(this.blurRadius);
					Docente.children(".imagenDocente").css({
						"-webkit-filter": "blur("+this.blurRadius+"px)",
						"filter": "blur("+this.blurRadius+"px)"
					});
					blurFix = this.blurRadius;
				}
			});
		});
	}).mouseleave(function() {
		var Docente = $(this);
		$(function() {
			$({blurRadius: blurFix}).animate({blurRadius: 50}, {
				duration: 200,
				easing: 'swing',
				step: function() {
					console.log(this.blurRadius);
					Docente.children(".imagenDocente").css({
						"-webkit-filter": "blur("+this.blurRadius+"px)",
						"filter": "blur("+this.blurRadius+"px)"
					});
				}
			});
		});
	});
});