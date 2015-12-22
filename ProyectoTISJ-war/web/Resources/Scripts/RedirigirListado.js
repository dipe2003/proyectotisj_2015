
function RedirigirRespuestaEncuesta(data){
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
    window.location.href="ListarEncuesta.xhtml?rol=Estudiante";
};

