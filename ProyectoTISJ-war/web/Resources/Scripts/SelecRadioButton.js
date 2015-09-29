$(document).ready(function(){
    $( ".menuOpcion" ).click(function() {
        $( ".menuOpcion" ).removeClass( "clickeado" );
        $(".imagenMenuOpcion").removeClass( "imagenMenuOpcionClick" );
        
        var Rol = $(this).attr('name');
        
        switch (Rol) {
            case "Administrador":
                $('#login\\:opcion\\:0').prop('checked',true);
                break;
            case "Administrativo":
                $('#login\\:opcion\\:1').prop('checked',true);
                break;
            case "Docente":
                $('#login\\:opcion\\:2').prop('checked',true);
                break;
            case "Estudiante":
                $('#login\\:opcion\\:3').prop('checked',true);
                break;
        }
        
        $( this ).children(".imagenMenuOpcion").addClass( "imagenMenuOpcionClick" );
        $( this ).addClass( "clickeado" );
        
    });
    
    
});