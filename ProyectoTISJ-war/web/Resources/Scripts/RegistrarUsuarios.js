$(document).ready(function(){
    var tiempo=0;
    $('.menuOpcion').hide().each(function() {
        $(this).delay(tiempo).fadeIn('slow');
        tiempo += 200;
    });
});