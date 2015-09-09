$(document).ready(function(){
    $(".boton.subirImagen").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#ImagenSubida').attr('src', e.target.result);
                $('#ImagenSubida').addClass( "circulo" );
                $('input[type="submit"].boton.siguiente').removeAttr('disabled');
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#ImagenSubida").error(function(){
        $(this).attr('src', '../Resources/Images/brokenimage.png');
        $('#ImagenSubida').removeClass( "circulo" );
        $('input[type="submit"].boton.siguiente').attr('disabled','disabled');
    });
});