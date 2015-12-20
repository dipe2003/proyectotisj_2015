$(document).ready(function(){
    $("#cargando").show();
});
$(window).load(function(){
    $("#cargando").hide();
});
function indicador(data){
    if(data.status==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
};

