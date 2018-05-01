$(document).ready(function () {
    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        search();
    });
});

function search(){
    var search = $(".form-control").val();
    console.log("search: " + search);

    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/search",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log(data);
            },
            error : function(e){
                console.log("error:");
                console.log(e);
            }
    });
}
