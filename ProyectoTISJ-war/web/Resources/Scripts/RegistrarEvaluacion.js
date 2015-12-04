$(document).ready(function(){
    
    $(".laboratorio").hide();
    
    $("#frmIngresoDatos\\:tipoEvaluacion").change(function() {
        var tipoEvaluacion = $(this).val();
        if (tipoEvaluacion === 'Laboratorio') {
            $(".examenParcial").hide();
            $(".resultadoEntrega").val(101);
            $(".laboratorio").show();
            $(".inputCuadrado").val(101)
        }else{
            $(".inputCuadrado").val(0);
            $(".examenParcial").show();
            $(".laboratorio").hide();
        }
    });
    
    $(".resultadoEntrega").change(function() {
        var result = $(this).val();
        var resultEval = $(this).parent().parent().find(".inputCuadrado");
        resultEval.val(result);
    });
    
});