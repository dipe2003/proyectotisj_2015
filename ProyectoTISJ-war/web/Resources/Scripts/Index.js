$(document).ready(function(){
    
    $( "#BotonFiltro" ).hover(function() {
        $( this ).stop( true, true ).animate({
            height: 100,
            width: 100
        }, 400, "easeOutElastic", function() {
            // fin de la animacion
        }); 
    }, function() {
        $( this ).stop( true, true ).animate({
            height: 50,
            width: 50
        }, 200, function() {
            // fin de la animacion
        }); 
    });
    
    var blurFix = 0;
    $( "#triger" ).mouseenter(function() {
        $(function() {
            $({blurRadius: blurFix}).animate({blurRadius: 50}, {
                duration: 200,
                easing: 'swing',
                step: function() {
                    console.log(this.blurRadius);
                    $("#fondo").css({
                        "-webkit-filter": "blur("+this.blurRadius+"px)",
                        "-moz-filter": "blur("+this.blurRadius+"px)",
                        "filter": "blur("+this.blurRadius+"px)"
                    });
                }
            });
        });
    }).mouseleave(function() {
        $(function() {
            $({blurRadius: 50}).animate({blurRadius: 0}, {
                duration: 200,
                easing: 'swing',
                step: function() {
                    console.log(this.blurRadius);
                    $("#fondo").css({
                        "-webkit-filter": "blur("+this.blurRadius+"px)",
                        "-moz-filter": "blur("+this.blurRadius+"px)",
                        "filter": "blur("+this.blurRadius+"px)"
                    });
                    blurFix = this.blurRadius;
                }
            });
        });
    });
    
});




