$(document).ready(function(){

 var abierto = false;
    $( "#BotonFiltro" ).click(function() {
        if (!abierto){
            $( ".cabecera" ).animate({
                height: 75
            }, 200, function() {
                abierto = true;
            }); 
        }else{
            $( ".cabecera" ).animate({
                height: 50
            }, 200, function() {
                abierto = false;
            }); 
        }
    });
    
});