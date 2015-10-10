$(document).ready(function(){
    
    $('#scrollContainer').slimScroll({
        width: '250px',
        height: '100vh',
        color: '#FFFFFF'
    });
    
    var activado = false;
    var IdActual;
    var IdAnterior = null;
    var AlturaOpcion = $( ".navOptMenu" ).height();
    
    $( ".navOptMenu" ).hover(function() {
        $( this ).css("background-color", "white");
        $( this ).css("color", "#222933");
    }, function() {
        if (($(this).attr("id")!==IdAnterior) || (!activado)){
            $( this ).css("background-color", "#222933");
            $( this ).css("color", "white");  
        }
    });
    
    $( ".navOptMenu" ).click(function() {
        IdActual = $(this).attr( "id" );
        if ((IdActual !== IdAnterior) ){
            resetMenu();
            setActiveMenu($(this));
            IdAnterior = IdActual;
            activado = true;
        }else{
            if (activado){
                resetMenu();
                activado = false;
            }else{
                setActiveMenu($(this));
                activado = true;
            }
        }  
    });
    
    function aumento(cantSubmenu){
        var result = 0;
        for (var i = 1; i <= cantSubmenu; i++) {
            if ((i-1) % 3 === 0) result++;
        }
        return(result*80);
    }
    
    function calcularAltura(opcionMenu){
        var altura = AlturaOpcion;
        var cantidadSubmenus = 0;
        cantidadSubmenus = opcionMenu.children().length - 1;
        var alturaExtra = aumento(cantidadSubmenus) ;
        altura = altura + alturaExtra;
        return altura;
    }
    
    function resetMenu(){
        $(".submenuOpcion").hide();
        $( ".navOptMenu" ).css("background-color", "#222933");
        $( ".navOptMenu" ).css("color", "white");
        $( ".navOptMenu" ).stop().animate({
            height: AlturaOpcion
        });
    }
    
    function setActiveMenu(opcionMenu){
        opcionMenu.stop().animate({
            height: calcularAltura(opcionMenu)
        }, function() {
            opcionMenu.children(".submenuOpcion").fadeIn('slow');
        });
        opcionMenu.css("background-color", "white");
        opcionMenu.css("color", "#222933");
    }
});