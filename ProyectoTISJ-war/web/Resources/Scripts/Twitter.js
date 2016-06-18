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
        $("iframe").contents().find(".timeline-Viewport").css("overflow", "hidden");
        $("iframe").contents().find(".footer").css("display", "none");
        $("iframe").contents().find(".tweet-actions").css("display", "none");
        $("iframe").css({"max-width": "none", "width": "100%", "height": "100%"});
        $("iframe").contents().find(".root").css({"border": "none", "max-width": "none", "background-color": "transparent"});
        $("iframe").contents().find(".Avatar").css("display", "none");
        $("iframe").contents().find(".timeline-Tweet-author").css("display", "none");
        $("iframe").contents().find(".timeline-Tweet-brand").css("display", "none");
        $("iframe").contents().find(".timeline-Tweet-metadata").css("display", "none");
        
        $("iframe").contents().find(".timeline-Tweet-actions").css("display", "none");
        $("iframe").contents().find(".timeline-TweetList-tweet").css({
            "border": "black solid 1px", 
            "padding": "20px",
            "margin": "20px",
            "box-shadow": "grey 5px 5px 5px",
            "color": "black",
            "background-color": "white",
            "text-shadow": "rgba(0, 0, 0, 0.3) 2px 2px 2px",
            "background-image": "url(http://localhost:8880/tecnoinfsj/Resources/Images/twitterBackGround.jpg)",
            "background-size": "cover",
            "background-repeat-y": "repeat"
        });
        $("iframe").contents().find(".p-name").css({"font-size": "25px", "color":"white"});
        $("iframe").contents().find(".timeline-Tweet-text").css({
            "text-align": "center",
            "font-size": "20px", 
            "color":"white",
            "line-height": "initial",
            "margin": "0"
        });
        $("iframe").contents().find(".timeline-Body").css({
            "border": "none"
        });
        
        $("iframe").contents().find(".timeline-LoadMore-prompt").css("display", "none");
        $("iframe").contents().find(".tco-display").css("color", "white");
        
        $("iframe").contents().find(".tweet").each(function( index ) {
            if(index>7)  $( this ).hide() ;
        });
        $("iframe").contents().find(".h-card").css({
            "text-align": "center",
            "margin-bottom": "15px"
        });
        
        $("iframe").contents().find(".timeline-TweetList-tweet").each(function( index ) {
            var posicion = -100 * index;
            $(this).css("background-position-y",posicion+"px");
        });
        
        
        $("#escondedor").css({"display": "block"});
        
        $("#footer").css({"visibility": "visible"});
    };