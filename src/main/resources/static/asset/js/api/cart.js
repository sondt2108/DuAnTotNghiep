

$("button.close").click(function () {
  $(this).parent().css("display", "none");
});
$("button.mo-gio-hang").click(function () {
  $("div.gio-hang").css("display", "block");
  fetch("/cart/gio-hang")
    .then((response) => response.text())
    .then((data) => {
      $("#table-content").html(data);
      $("#quantityPr").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
});

function truSP(ev) {
  let id = $(ev.target).attr("id");
  fetch("/cart/tru-san-pham/" + ev)
    .then((response) => response.text())
    .then((data) => {
      $("#table-content").html(data);
      $("#quantityPr").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
}
function congSP(ev) {
  let id = $(ev.target).attr("id");
  console.log(ev);
  fetch("/cart/them-vao-gio/" + ev)
    .then((response) => response.text())
    .then((data) => {
      $("#table-content").html(data);
      $("#quantityPr").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
}

function xoaSP(ev) {
  console.log(ev);
  var r = confirm("Do you really want to delete this row?");
  if (r == true) {
    let id = $(ev.target).attr("id");
    fetch("/cart/xoa-san-pham/" + ev)
      .then((response) => response.text())
      .then((data) => {
        $("#table-content").html(data);
        $("#quantityPr").load(location.href + " #quantityPr");
        loadQuantityCart();
      });
  }
}

/* lấy phần tử modal */
var modal = document.getElementById("myModal");
/* thiết lập nút mở modal */

function popupCartHide() {
  modal.style.display = "none";
}
/*Sẽ đóng modal khi nhấp ra ngoài màn hình*/
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};

//end modal cart



//cart
function minusCart(ev) {
  let id = $(ev.target).attr("id");
  fetch("/cart/minus/" + ev)
    .then((response) => response.text())
    .then((data) => {
      $("#cart-data").html(data);
      $("#quantityPr").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
}
function addCart(ev) {
  let id = $(ev.target).attr("id");
  console.log(ev);
  fetch("/cart/add/" + ev)
    .then((response) => response.text())
    .then((data) => {
      $("#cart-data").html(data);
      $("#quantityPr").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
}

function removeCart(ev) {
  console.log(ev);
  var r = confirm("Bạn có chắc chắn xóa sản phẩm này khỏi giỏ hàng?");
  if (r == true) {
    let id = $(ev.target).attr("id");
    fetch("/cart/remove/" + ev)
      .then((response) => response.text())
      .then((data) => {
        $("#cart-data").html(data);
        $("#quantityPr").load(location.href + " #quantityPr");
        loadQuantityCart();
      });
  }
}

//

var input = document.querySelectorAll('#onchangeQuantity');

input.forEach(function(inp) {
  inp.addEventListener("change", updateValue)});

function updateValue(e) {

  var quantity = e.target.value;
  var productId = e.target.name;
  if (quantity == "" || quantity <= 0) {
    quantity = 1;
  }
  fetch("/cart/addCartByInputOnchange/" + productId+"/" + quantity)
    .then((response) => response.text())
    .then((data) => {
      $("#cart-data").html(data);
      $("#quantityCart").load(location.href + " #quantityPr");
      loadQuantityCart();
    });
}

function loadQuantityCart() {
  var quantityProduct = $("#quantityCart").text();
  console.log(quantityProduct);
    if (quantityProduct == '') {
        console.log("hide");
        $("#quantityCart").hide();
    }else{
      console.log("jejej");
      $("#quantityCart").show();
    }

}