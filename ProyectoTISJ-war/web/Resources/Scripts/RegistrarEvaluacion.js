$(document).ready(function(){
    
    $(".laboratorio").hide();
    
    $("#frmIngresoDatos\\:tipoEvaluacion").change(function() {
        var tipoEvaluacion = $(this).val();
        if (tipoEvaluacion === 'Laboratorio') {
            $(".examenParcial").hide();
            $(".laboratorio").show();
        }else{
            $(".examenParcial").show();
            $(".laboratorio").hide();
        }
    });
    
});