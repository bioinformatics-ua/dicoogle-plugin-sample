$( document ).ready(function() {
        console.log( "document loaded" );
        $("#dashboardDicoogleSample").append("This is my Plugin in JS");
        $.get("/sample/hello?uid=111111", function (data) {
            console.log(data);
            $("#dashboardDicoogleSample").append(". This data now comes from web service:  " + data.action);
        });
    });
 
    $( window ).load(function() {
        console.log( "window loaded" );
});