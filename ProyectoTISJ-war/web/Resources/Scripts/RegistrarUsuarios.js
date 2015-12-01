$(document).ready(function(){
    
    var tiempo=0;
    $('.menuOpcion').hide().each(function() {
        $(this).delay(tiempo).fadeIn('slow');
        tiempo += 200;
    });
    
    $("#ImagenSubida").attr('src', '../Resources/Images/imagen.png');
    $(".boton.subirImagen.perfil").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#ImagenSubida').attr('src', e.target.result);
                $('input[type="submit"].boton.siguiente').removeAttr('disabled');
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#ImagenSubida").error(function(){
        $(this).attr('src', '../Resources/Images/brokenimage.png');
        $('input[type="submit"].boton.siguiente').attr('disabled','disabled');
    });
    
    $("#formulario").attr('src', '../Resources/Images/imagen.png');
    $(".boton.subirImagen.formulario").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#formulario').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#formulario").error(function(){
        $(this).attr('src', '../Resources/Images/brokenimage.png');
    });
    
    $("#frmIngresoDatos\\:inputGeneracion").val("");
    
    $( ".genero" ).click(function() {
        var Rol = $(this).attr('id');
        switch (Rol) {
            case "masculino":
                $('#femenino').attr('src', '../Resources/Images/femenino.png');
                $('#masculino').attr('src', '../Resources/Images/masculinoActivo.png');
                $('#frmIngresoDatos\\:inputSexo\\:0').prop('checked',true);
                break;
            case "femenino":
                $('#femenino').attr('src', '../Resources/Images/femeninoActivo.png');
                $('#masculino').attr('src', '../Resources/Images/masculino.png');
                $('#frmIngresoDatos\\:inputSexo\\:1').prop('checked',true);
                break;
        }
    });
    
    function Redirigir(data){
        if(data.status === 'success'){
            var exito = $('#frmIngresoDatos\\:regExito').val();
            if(exito === 'true'){
                window.location.href="ListarUsuario.xhtml";
            }else{
                alert("No se pudo registrar el usuario.");
            }
        }
    }
    
});