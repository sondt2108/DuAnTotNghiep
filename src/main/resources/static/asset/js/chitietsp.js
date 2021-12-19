// $(function() {
//     $.getScript("/asset/js/api/product.js",function(){
//         var prid = localStorage.getItem("prid");
//         loaddetail(prid);

//         });
// });

var prid = localStorage.getItem("prid");

var productApi = "/api/productdetails/" + prid;

function product() {
  getProduct(renderProduct);
}
product();

function getProduct(callback) {
  fetch(productApi)
    .then(function (response) {
      return response.json();
    })
    .then(callback);
}

function renderProduct(prs) {
  console.log(prs)
  var breadcrumb = document.querySelector("#breadcrumb");
  var loadBreadcrumb = [prs].map(function (prs) {
    return `                         
        <li style="font-size: 14px">
            Trang chủ > Sản phẩm > ${prs.tensanpham}
        </li>               
                       
        `;
  });

  breadcrumb.innerHTML = loadBreadcrumb.join(" ");

  var detailimg = document.querySelector("#img-detail");
  var imgdetail = [prs].map(function (prs) {
    return `
       
                   
        <figure class="zoom" onmousemove="zoom(event)" style="background-image: url(${prs.hinhanh})">
            <img src="${prs.hinhanh}" />
        </figure>
             
                       
        `;
  });

  detailimg.innerHTML = imgdetail.join(" ");

  var datapr = document.querySelector("#data-detail");
  var data_detail = [prs].map(function (prs) {
    var price = prs.gia;
    var priceFormat = price
      .toString()
      .replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
      var discount = prs.khuyenmai;
    var priceDiscount = price/(100 - discount)*100;
    var priceDiscountFormat = priceDiscount.toFixed(0).toString()
    .replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
    console.log(priceDiscount);
    return `
       
        <h1 class="title-head">${prs.tensanpham}</h1>
                        
        <div class="product__details__price" >${priceFormat}₫ <span style="text-decoration:line-through; color: #a4a4a4;">${priceDiscountFormat}₫</span></div>
        <ul>
            <li><b>Thương hiệu</b> <span>${prs.thuonghieu.tenTH}</span></li>
            <li><b>Tình trạng</b> <span>${prs.tinhtranghang}</span></li>
        </ul>
        
        `;
  });

  datapr.innerHTML = data_detail.join(" ");

  var description = document.querySelector("#description")
  var dataDescription = [prs].map(function (prs) {
    
    return `
    <h6>${prs.tensanpham}</h6>
     <p>${prs.thongtinsp}</p>
                  
        `;
  });
  description.innerHTML = dataDescription.join(" ");
}

function addSP() {
  var prid = localStorage.getItem("prid");

  fetch("/cart/them-vao-gio/" + prid)
    .then((response) => response.text())
    .then((data) => {
      $("#table-content").html(data);
      $("#quantityCart").show();
      $("#quantityPr").load(location.href + " #quantityPr");
      $("div.gio-hang").css("display", "block");
    });
}

function zoom(e) {
  var zoomer = e.currentTarget;
  e.offsetX ? (offsetX = e.offsetX) : (offsetX = e.touches[0].pageX);
  e.offsetY ? (offsetY = e.offsetY) : (offsetX = e.touches[0].pageX);
  x = (offsetX / zoomer.offsetWidth) * 100;
  y = (offsetY / zoomer.offsetHeight) * 100;
  zoomer.style.backgroundPosition = x + "% " + y + "%";
  console.log("sonne");
}

