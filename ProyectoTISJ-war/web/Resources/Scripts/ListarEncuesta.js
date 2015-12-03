$(document).ready(function(){    
    
    $( ".masInformacionSinColumna" ).click(function() {
        var ListItem = $( this ).parent();
        if (ListItem.hasClass("ListItemClick")){
            ListItem.removeClass("ListItemClick");
            ListItem.parent().animate({
                height: 50
            }, 200, function() {
                //fin de la animacion
            }); 
        }else{
            var alturaconColumna = ListItem.parent().children('.sinColumna').height() + 50;
            ListItem.addClass("ListItemClick");
            ListItem.parent().animate({
                height: alturaconColumna
            }, 200, function() {
                //fin de la animacion
            }); 
        }
    });
});

function ObtenerDatos(idEncuesta){
    var resultados = "";
    var len = $('.datos_'+idEncuesta).length;
    $('.datos_'+idEncuesta).each(function(index, element) {
        resultados += $(this).children( ".respuesta" ).val() + "-";
        resultados += $(this).children( ".idPregunta" ).val();
        if (index < len - 1) {
            resultados +=  ",";
        }
    });
    $("#Formulario\\:hiddenRespuesta").val(resultados);
}