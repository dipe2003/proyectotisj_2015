$(document).ready(function() {
    $("#ticker-area").hide();
        
    $( "#botonAyuda" ).click(function() {
        $("#ticker-area").show();
        $("#ticker-area").css({"margin-top": "50px"});
        createTicker();
    });    
});
