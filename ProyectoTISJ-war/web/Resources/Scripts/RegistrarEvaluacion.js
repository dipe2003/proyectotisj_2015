$(document).ready(function(){
    
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