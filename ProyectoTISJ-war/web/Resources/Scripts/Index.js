$(document).ready(function(){
    
    $( "#BotonFiltro" ).hover(function() {
        $( this ).stop( true, true ).animate({
            height: 100,
            width: 100
        }, 400, "easeOutElastic", function() {
            // fin de la animacion
        }); 
    }, function() {
        $( this ).stop( true, true ).animate({
            height: 50,
            width: 50
        }, 200, function() {
            // fin de la animacion
        }); 
    });
    
});