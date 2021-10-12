

$(function() {
    $.ajax({
        url: "/api/categories",
        type: "GET",
        dataType: "json",
        success: function(data) {
            data.forEach(function(cate) {
                $("#categories").append("<li><a href='#'>" + cate.name + "</a><li>")
            })
        }
    });
});