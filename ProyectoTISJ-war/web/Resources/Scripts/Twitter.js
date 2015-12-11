! function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0],
            p = /^http:/.test(d.location) ? 'http' : 'https';
    if (!d.getElementById(id)) {
        js = d.createElement(s);
        js.id = id;
        js.src = p + "://platform.twitter.com/widgets.js";
        js.setAttribute('onload', "twttr.events.bind('rendered',function(e) {doSomething()});");
        fjs.parentNode.insertBefore(js, fjs);
    }
}(document, "script", "twitter-wjs");

function doSomething(){
    $("iframe").contents().find(".timeline-footer").hide();
    $("iframe").contents().find(".timeline-header").hide();
    $("iframe").contents().find(".stream").css("overflow", "hidden");
    $("iframe").contents().find(".footer").css("display", "none");
    $("iframe").contents().find(".tweet-actions").css("display", "none");
    $("iframe").css({"max-width": "none", "width": "100%", "height": "100%"});
    $("iframe").contents().find(".root").css({"border": "none", "max-width": "none", "background-color": "transparent"});
    $("iframe").contents().find(".u-photo").css("display", "none");
    $("iframe").contents().find(".p-nickname").css("display", "none");
    $("iframe").contents().find(".dt-updated").css("display", "none");
    $("iframe").contents().find(".tweet").css({
        "border": "black solid 1px", 
        "padding": "20px",
        "margin": "20px",
        "box-shadow": "grey 5px 5px 5px",
        "color": "black",
        "background-color": "white",
        "text-shadow": "rgba(0, 0, 0, 0.3) 2px 2px 2px",
        "background-image": "url(http://static.tumblr.com/c25db28edc166bfe367476a32913b4c5/0ztvotg/UKJn3km2m/tumblr_static_background-cool-backgrounds-vladstudio-2616004.jpg)",
        "background-size": "cover",
        "background-repeat-y": "repeat"
    });
    $("iframe").contents().find(".p-name").css({"font-size": "25px", "color":"white"});
    $("iframe").contents().find(".e-entry-title").css({
        "text-align": "center",
        "font-size": "20px", 
        "color":"white"
    });
    $("iframe").contents().find(".load-more").css("display", "none");
    $("iframe").contents().find(".tco-display").css("color", "white");
    
    $("iframe").contents().find(".tweet").each(function( index ) {
        if(index>7)  $( this ).hide() ;
    });
    $("iframe").contents().find(".h-card").css({
        "text-align": "center",
        "margin-bottom": "15px"
    });
    
    $("iframe").contents().find(".tweet").each(function( index ) {
        var posicion = -100 * index;
        $(this).css("background-position-y",posicion+"px")
    });
    
    $("#escondedor").css({"display": "block"});
};