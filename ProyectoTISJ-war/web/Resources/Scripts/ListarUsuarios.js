$(document).ready(function(){    
    
    $( ".ListItem" ).click(function() {
        if ($(this).hasClass("ListItemClick")){
            $(this).removeClass("ListItemClick");
            $( this ).parent().animate({
                height: 50
            }, 200, function() {
                //fin de la animacion
            }); 
        }else{
            $( this ).addClass("ListItemClick");
            $( this ).parent().animate({
                height: 300
            }, 200, function() {
                //fin de la animacion
            }); 
        }
    });
    
    var tiempo=0;
    $('.ListItemContainer').hide().each(function() {
        $(this).delay(tiempo).fadeIn('slow');
        tiempo += 200;
    });
    
    $("#filtro\\:cedulaFilter").val("");
    
});