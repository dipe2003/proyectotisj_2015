$(document).ready(function(){
    if ($('#frmIngresoDatos\\:inputSexo\\:0').prop('checked')===true){
        $('#femenino').attr('src', '../Resources/Images/femenino.png');
        $('#masculino').attr('src', '../Resources/Images/masculinoActivo.png');
    }
    if ($('#frmIngresoDatos\\:inputSexo\\:1').prop('checked')===true){
        $('#femenino').attr('src', '../Resources/Images/femeninoActivo.png');
        $('#masculino').attr('src', '../Resources/Images/masculino.png');
    }
});