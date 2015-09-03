$(document).ready(function(){
    $( ".imagenDocente" ).mouseenter(function() {
        $('.optionContainer').stop().fadeIn(200);
    }).mouseleave(function() {
        $('.optionContainer').stop().fadeOut(200);
    });
});