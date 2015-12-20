function RedirigirUsuarios(data){
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
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
function RedirigirEditarPerfil(data){
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
    if(data.status === 'success'){
        var exito = $('#frmIngresoDatos\\:editPerfilExito').val();
        var rol = $('#frmIngresoDatos\\:regRol').val();
        var destino = "index.xhtml";
        if(exito === 'true'){
            window.location.href=destino;
        }else{
            alert("No se pudo guardar los cambios");
        }
    }
};


function RedirigirEncuestas(data){
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
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
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
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
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
    window.location.href="ListarEncuesta.xhtml?rol=Estudiante";
};

function RedirigirCurso(data){
    if(data.status ==='begin'){
        $("#cargando").show();
    }
    if(data.status === 'complete'){
        $("#cargando").hide();
    }
    if(data.status === 'success'){
        var exito = $('#frmIngresoDatos\\:exitoRegCurso').val();
        var destino = "ListarCursos.xhtml";
        if(exito === 'true'){
            window.location.href=destino;
        }else{
            alert("No se pudo registrar el curso. Todos los campos son obligatorios.");
        }
    }
};
