$(document).ready(function(){    
    
    eventoClick();
    
    var tiempo=0;
    $('.ListItemContainer').hide().each(function() {
        $(this).delay(tiempo).fadeIn('slow');
        tiempo += 200;
    });
    
    $("#filtro\\:cedulaFilter").val("");
    
});

function eventoClick(){
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
            var alturaconColumna = ListItem.parent().children('.columna').height() + 50;
            ListItem.addClass("ListItemClick");
            ListItem.parent().animate({
                height: alturaconColumna
            }, 200, function() {
                //fin de la animacion
            }); 
        }
    });
}