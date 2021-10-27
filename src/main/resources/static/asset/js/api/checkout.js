


$(function () {

  $.validator.addMethod(
    "checkPhoneNumber",
    function (value, element) {
      return (
        this.optional(element) ||
        /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value)
      );
    },
    "Số điện thoại không hợp lệ, vui lòng nhập lại!"
  );
    $("#checkoutForm").validate({
      rules: {
        name: {
          required: true,
          minlength: 3,
          maxlength: 20,
        },
        phoneNumber : "required checkPhoneNumber"

      },
      messages: {
        name: "Tên đăng nhập không được để trống",
        phoneNumber: {
          required: "Số điện thoại không được để trống",
        }
      },
      submitHandler: function () {

        if($("select[name=ls_province]").val() == null && $("select[name=ls_ward]").val() == null ) {
          $("label.province_error").show(); // show Warning 
          $("select#province").focus();  // Focus the select box      
            
      }else if($("select[name=ls_district]").val() == null) {
        $('.province_error').hide(); 
        $("label.district_error").show(); // show Warning 
        $("select#district").focus();  // Focus the select box

      }else if($("select[name=ls_ward]").val() == null) {
        $('.district_error').hide();
        $("label.ward_error").show(); // show Warning 
        $("select#ward").focus();  // Focus the select box


      }else {
        $('.province_error').hide(); 
        $('.district_error').hide();
        $('.ward_error').hide();
        var province = $('#province option:selected').text();
        var district = $('#district option:selected').text();
        var ward = $('#ward option:selected').text();

        var $form = $("#checkoutForm");
        var loginData = {
          name: $form.find('input[name="name"]').val(),
          email: $form.find('input[name="email"]').val(),
          address: $form.find('input[name="address"]').val(),
          province,
          district,
          ward,
        };


        console.log(loginData);
  
        // $.ajax({
        //   url: "/api/auth/signin",
        //   type: "POST",
        //   data: JSON.stringify(loginData),
        //   contentType: "application/json; charset=utf-8",
        //   dataType: "json",
        //   success: function (data, textStatus, jqXHR) {
        //     setJwtToken(data.accessToken);
        //     setRefreshToken(data.refreshToken);
        //     console.log(data);
        //     if (cartStatus == 0) {
        //       location.assign("/checkout")
        //     }else{
        //       location.assign("/")
        //     }
            
        //   },
        //   error: function (jqXHR, textStatus, errorThrown) {
        //     if (jqXHR.status === 401) {
        //       $("#loginErrorModal")
        //         .modal("show")
        //         .find(".modal-body")
        //         .empty()
        //         .html("<p class='error'>" + 'Thông tin tài khoản hoặc mật khẩu không chính xác!' + "</p>");
              
        //     } else {
        //       throw new Error("an unexpected error occured: " + errorThrown);
        //     }
        //   },
        // });
      }
      },
    });
  });