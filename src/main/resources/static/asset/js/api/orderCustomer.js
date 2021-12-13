var insertModal = new bootstrap.Modal(
    document.getElementById("editModal"),
    {
      keyboard: false,
    }
  );


  var url = "/api/order";
  var rowTemplate =
    "{{#each order}}" +
    "<tr>" +
    '<td class="py-3">{{orderId}}</td>' +
    '<td class="py-3">{{phoneNumber}}</td>' +
    '<td class="py-3">{{address}} <p style = "display:block;">({{ward}}-{{district}}-{{province}})</td></p>' +
    '<td class="py-3">{{total}}</td>' +
    "{{#each order_items}}" +
    '<td class="py-3" style = "display:block;">{{productName}} <p>(Số lượng: {{quantity}})</p> <p>Đơn giá: {{price}}</p></td>' +
    "{{/each}}"+
    
    '<td class="py-3" style="color:#85AAD7;">{{tinhtrang.tinhTrang}}</td>' +
    '<td class="py-3"><button style="color:red;" class="edit btn btn-outline" id="{{tinhtrang.idTT}}" c-id="{{orderId}}">Hủy đơn hàng</button></td>' +
    "</tr>" +
    "{{/each}}";
  var rowHbs = Handlebars.compile(rowTemplate);
  
var tinhtrangSelect = '<select id="tinhtrangSelect" class="form-control" name="idTT">{{#each tinhtrangs}}'
        + '<option value="{{idTT}}">'
        + "{{tinhTrang}}"
        + "</option>"
        + "{{/each}}</select>";
    var tinhtrangSelectHbs = Handlebars.compile(tinhtrangSelect); 
    

  function loadList() {
    var searchForm = FormDataJson.toJson(document.querySelector("form"));
    console.log(searchForm);
    var searchOption = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(searchForm),
    };

    fetch("/api/orderCustomer/search", searchOption)
      .then((res) => res.json())
      .then((data) => {
        var rows = rowHbs({ order: data.content });
       
        $("#myTable").html(rows);
        orderStatus();
       
        bindClick();
        var pageHtml = "";
        for (var i = 0; i < data.totalPages; i++) {
          pageHtml += `<button style="display: inline-block; margin-left:5px;" value="${i}" id="datatablePagination0" class='${i == searchForm.trang ? "page-link active pageIndex" : "page-link pageIndex"}'>${i+1}</button>`;
        }
        $("#phanTrang").html(pageHtml);

        $(".pageIndex").click(function () {
          $('#searchForm input[name="trang"]').val($(this).val());
          loadList();
        });

        
      });


      
  }

  $("#searchBt").click(function () {
    loadList();
  });


  function orderStatus() {
    $( "#2" ).remove();
    $( "#3" ).remove();
    $( "#4" ).remove();
    $( "#5" ).remove();
    
    $('.edit').each(function(index, item) {
console.log(item.id);

if (item.id == 6 || item.id == 5 || item.id == 4  || item.id == 3  || item.id == 2) {
    item.remove();
}


});
  }

  

  

  function bindClick() {
    $(".edit").click(function () {
      insertModal.show();
      var id = $(this).attr("c-id");
      console.log(id);

      idTT = 6;

      var order = {
      id,
      idTT,
      tinhtrang: {idTT: '6'}

    };


    console.log(order);

      var editOption = {
                    method: "PUT",
                    headers: {
                      "Content-Type": "application/json",
                    },
                    body: JSON.stringify(order),
                  };

                  
                  $("#cancelOrder").button().click(function(){
                    
                      order = fetch("/api/cencelOrder/" + id, editOption)
                      .then((response) => response.json())
                      .then((data) => {
                        console.log(data);
                        loadList();
                      });
                    insertModal.hide();
                  
});   
    });
  }
  


  // isLogin == false

  function isLoginFalse() {
    checkOrderLoginFalseModal.show();
  }