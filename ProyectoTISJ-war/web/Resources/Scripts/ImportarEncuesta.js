$(document).ready(function(){
    $(".botonRadioSimil").on('click', function() {
        if ($(this).is(":checked")) { 
            $(".botonRadioSimil.BotonCheck").prop("checked", false);
            $(this).prop("checked", true);
        } else {
            
            $(this).prop("checked", false);
        }
    });
});