$(document).ready(function(){
    $( ".menuOpcion" ).click(function() {
        $( ".menuOpcion" ).removeClass( "clickeado" );
        $(".imagenMenuOpcion").removeClass( "imagenMenuOpcionClick" );
        
        var Rol = $(this).attr('name');
        
        switch (Rol) {
            case "Administrador":
                $('#Administrador').prop('checked',true);
                break;
            case "Administrativo":
                $('#Administrativo').prop('checked',true);
                break;
            case "Docente":
                $('#:Docente').prop('checked',true);
                break;
            case "Estudiante":
                $('#Estudiante').prop('checked',true);
                break;
        }
        
        $( this ).children(".imagenMenuOpcion").addClass( "imagenMenuOpcionClick" );
        $( this ).addClass( "clickeado" );
        
    });
    
    
});