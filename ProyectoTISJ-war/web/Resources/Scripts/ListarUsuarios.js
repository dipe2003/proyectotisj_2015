$(document).ready(function(){    
    
    $( ".masInformacion" ).click(function() {
        var ListItem = $( this ).parent();
        if (ListItem.hasClass("ListItemClick")){
            ListItem.removeClass("ListItemClick");
            ListItem.parent().animate({
                height: 50
            }, 200, function() {
                //fin de la animacion
            }); 
        }else{
            ListItem.addClass("ListItemClick");
            ListItem.parent().animate({
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