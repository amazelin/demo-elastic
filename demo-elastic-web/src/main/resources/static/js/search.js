$(document).ready(function () {
    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        search();
    });
});

function search(){
    var search = {};
    search['query'] = $(".form-control").val();
    $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/search",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("data stringify" + JSON.stringify(data));
                $('.table tbody').empty();

                var result = {};
                data['content'].forEach(function(row){
                     $('.table tbody').append('<tr>' +
                     getColumn(row.id) +
                     getColumn(row.client.firstname + ' ' + row.client.lastname) +
                     getColumn(row.offer) +
                     getColumn(row.structure) +
                     getColumn(row.riskProfile) +
                     getColumn(row.assets) +
                     '</tr>');
                });
            },
            error : function(e){
                console.log("error:");
                console.log(e);
            }
    });
}

function getColumn(property ){
    return '<td>' + property + '</td>';
}
