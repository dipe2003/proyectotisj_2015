function RedirigirUsuarios(data){
    if(data.status === 'success'){
        var exito = $('#frmIngresoDatos\\:regExito').val();
        var rol = $('#frmIngresoDatos\\:regRol').val();
        var destino = "ListarUsuarios.xhtml?rol="+rol;
        if(exito === 'true'){
            window.location.href=destino;
        }else{
            alert("No se pudo registrar el usuario.");
        }
    }
};


function RedirigirEncuestas(data){
    if(data.status === 'success'){
        var exito = $('#frmRegEncuesta\\:encExito').val();
        var destino = "ListarEncuesta.xhtml?rol=Administrador";
        if(exito === 'true'){
            window.location.href=destino;
        }else{
            alert("No se pudo registrar la encuesta.");
        }
    }
};


function RedirigirClases(data){
    if(data.status === 'success'){
        var exito = $('#frmIngresoDatosClase\\:claseExito').val();
        var idcurso = $('#frmIngresoDatosClase\\:clasecursoid').val();
        var destino = "ListarClases.xhtml?opt="+idcurso;
        if(exito === 'true'){
            window.location.href=destino;
        }else{
            alert("No se pudo registrar la clase.");
        }
    }
};

function RedirigirRespuestaEncuesta(data){
    window.location.href="ListarEncuesta.xhtml?rol=Estudiante";
};
