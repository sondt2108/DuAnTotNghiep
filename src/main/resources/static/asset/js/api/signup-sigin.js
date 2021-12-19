// VARIABLES =============================================================
var TOKEN_KEY = "jwtToken";
var REFRESF_TOKEN_KEY = "refreshToken";

// FUNCTIONS =============================================================
function getJwtToken() {
  return sessionStorage.getItem(TOKEN_KEY);
}

function getRefreshToken() {
  return sessionStorage.getItem(REFRESF_TOKEN_KEY);
}

function setJwtToken(token) {
  sessionStorage.setItem(TOKEN_KEY, token);
}

function setRefreshToken(refreshToken) {
  sessionStorage.setItem(REFRESF_TOKEN_KEY, refreshToken);
}

function removeJwtToken() {
  sessionStorage.removeItem(TOKEN_KEY);
}

function removeRefreshToken() {
  sessionStorage.removeItem(REFRESF_TOKEN_KEY);
}



const urlParams = new URLSearchParams(window.location.search);
const cartStatus = urlParams.get('cartStatus');
console.log(cartStatus);
        

//validate form login

// get url current

var currentUrl = sessionStorage.getItem("urlReturn");

$(function () {
  $("#loginForm").validate({
    rules: {
      username: {
        required: true,
        minlength: 3,
        maxlength: 20,
      },
      password: "required",
    },
    messages: {
      username: "Tên đăng nhập không được để trống",
      password: "Mật khẩu không được để trống",
    },
    submitHandler: function () {
      var $form = $("#loginForm");
      var loginData = {
        username: $form.find('input[name="username"]').val(),
        password: $form.find('input[name="password"]').val(),
      };

      console.log(loginData);

      $.ajax({
        url: "/api/auth/signin",
        type: "POST",
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
          setJwtToken(data.accessToken);
          setRefreshToken(data.refreshToken);

          if (data.roles.includes('ROLE_ADMIN')) {
            location.assign("/admin/dashboard");
          }else{
            if (cartStatus == 0) {
              location.assign("/checkout")
            }else{
              history.back();
            }
          }
          
          
        },
        error: function (jqXHR, textStatus, errorThrown) {
          if (jqXHR.status === 401) {
            console.clear();
            $("#loginErrorModal")
              .modal("show")
              .find(".modal-body")
              .empty()
              .html("<p class='error'>" + 'Thông tin tài khoản hoặc mật khẩu không chính xác!' + "</p>");
            
          } else {
            throw new Error("an unexpected error occured: " + errorThrown);
          }
        },
      });
    },
  });
});

//register

function checkPass() {
  var password = $("#password-input").val();
  var confirmPassword = $("#password-confirm").val();
  if (password === confirmPassword) {
    $("#CheckPasswordMatch").html("");
  }
}

$(function () {
  $.validator.addMethod(
    "checkEmail",
    function (value, element) {
      return (
        this.optional(element) ||
        /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i.test(value)
      );
    },
    "Sai định dạng email, vui lòng nhập lại!"
  );

  $("#registerForm").validate({
    rules: {
      username: {
        required: true,
        minlength: 3,
        maxlength: 20,
      },
      email: "required checkEmail",
      name :{
        required: true,
        minlength: 3,
        maxlength: 40,
      },
      address : {
        required: true,
        minlength: 3,
        maxlength: 100,
      },
      password: {
        required: true,
        minlength: 6,
        maxlength: 20,
      },
      passwordConfirm: {
        required: true,
        minlength: 6,
        maxlength: 20,
      },
    },
    messages: {
      username: "Tên đăng nhập không được để trống",
      name : "Họ và tên không được để trống",
      address :"Địa chỉ không được để trống",
      email: {
        required: "Email không được để trống",
      },
      password: "Mật khẩu không được để trống",
      passwordConfirm: "Vui lòng nhập lại mật khẩu",
    },
    submitHandler: function () {
      var password = $("#password-input").val();
      var confirmPassword = $("#password-confirm").val();

      if (password != confirmPassword) {
        $("#CheckPasswordMatch").html("Mật khẩu không khớp!");
      } else {
        var $form = $("#registerForm");
        var loginData = {
          username: $form.find('input[name="username"]').val(),
          email: $form.find('input[name="email"]').val(),
          name : $form.find('input[name="name"]').val(),
          address : $form.find('input[name="address"]').val(),
          password: $form.find('input[name="password"]').val(),
        };

        console.log(loginData);

        $.ajax({
          url: "/api/auth/signup",
          type: "POST",
          data: JSON.stringify(loginData),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function (data, textStatus, jqXHR) {
            alert("đăng ký thành công");
            location.assign("/login");
          },
          error: function (jqXHR, textStatus, errorThrown) {
            console.clear();
            if (jqXHR.status === 400) {
              $('#loginErrorModal')
              .modal("show")
              .find(".modal-body")
              .empty()
              .html("<p class='error'>" + jqXHR.responseJSON.message + "</p>");
            } 
          },
        });
      }
    },
  });
});

//forgot-password

$(function () {
  $.validator.addMethod(
    "checkEmail",
    function (value, element) {
      return (
        this.optional(element) ||
        /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i.test(value)
      );
    },
    "Sai định dạng email, vui lòng nhập lại!"
  );
  $("#forgotpasswordForm").validate({
    rules: {
      email: "required checkEmail",
    },
    messages: {
      email: {
        required: "Email không được để trống",
      },
    },
    submitHandler: function () {
      var $form = $("#forgotpasswordForm");
      var loginData = {
        email: $form.find('input[name="email"]').val(),
      };

      document.getElementById("forgotpasswordbtn").disabled = true;

      $.ajax({
        url: "/account/forgot-password",
        type: "POST",
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
          if(jqXHR.responseJSON.message.startsWith("Email chưa đăng ký")){
            document.getElementById("forgotpasswordbtn").disabled = false;
          }
          $("#loginErrorModal")
              .modal("show")
              .find(".modal-body")
              .empty()
              .html("<p class='error'>" + jqXHR.responseJSON.message + "</p>");
        },
        error: function (jqXHR, textStatus, errorThrown) {
          if (jqXHR.status === 401) {
            
          }
        },
      });
    },
  });
});

//reset password

$(function () {
  $("#resetPasswordForm").validate({
    rules: {
      password: {
        required: true,
        minlength: 6,
        maxlength: 20,
      },
      passwordConfirm: {
        required: true,
        minlength: 6,
        maxlength: 20,
      },
    },
    messages: {
      password: "Mật khẩu không được để trống",
      passwordConfirm: "Vui lòng nhập lại mật khẩu",
    },
    submitHandler: function () {
      var password = $("#password-input").val();
      var confirmPassword = $("#password-confirm").val();

      if (password != confirmPassword) {
        $("#CheckPasswordMatch").html("Mật khẩu không khớp!");
      } else {
        var $form = $("#resetPasswordForm");
        let params = new URL(document.location).searchParams;
        let token = params.get("token");

        
        var loginData = {
          password,
          token
        };

        console.log(loginData);

        const queryString = window.location.search;
        console.log(queryString);


        $.ajax({
          url: "/account/reset-password" + queryString,
          type: "PUT",
          data: JSON.stringify(loginData),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function (data, textStatus, jqXHR) {

            $("#loginErrorModal")
              .modal("show")
              .find(".modal-body")
              .empty()
              .html("<p class='error'>" + jqXHR.responseJSON.message + "</p>");
            location.assign("/login")

          },
          error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
            }
          },
        });
      }
    },
  });
});
