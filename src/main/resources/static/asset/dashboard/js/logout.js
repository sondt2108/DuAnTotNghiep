

// logout 
function logout(userId) {
    console.log(userId);
    var data = {userId};
    $.ajax({
        url: "/api/auth/logout",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
          location.assign("/");
          sessionStorage.removeItem("jwtToken");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error")
        },
      });
}