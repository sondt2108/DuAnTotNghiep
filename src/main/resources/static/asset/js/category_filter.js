const url = window.location.pathname;

const urlParams = new URLSearchParams(window.location.search);
const param = urlParams.get("sort");
const sortBy = urlParams.get("sortBy");
const trademake = urlParams.get("trademake");
console.log(trademake);

const urlPrice = new URLSearchParams(window.location.search);
const price_min = urlPrice.get("price_min");
const price_max = urlPrice.get("price_max");

if (price_max == 100000) {
  document.getElementById("filter_price_mobile").selectedIndex = "1";
  document.getElementById("filter_price_desktop").selectedIndex = "1";
} else if (price_min == 100000 && price_max == 300000) {
  document.getElementById("filter_price_mobile").selectedIndex = "2";
  document.getElementById("filter_price_desktop").selectedIndex = "2";
} else if (price_min == 300000 && price_max == 500000) {
  document.getElementById("filter_price_mobile").selectedIndex = "3";
  document.getElementById("filter_price_desktop").selectedIndex = "3";
} else if (price_min == 500000 && price_max == 700000) {
  document.getElementById("filter_price_mobile").selectedIndex = "4";
  document.getElementById("filter_price_desktop").selectedIndex = "4";
} else if (price_min == 700000) {
  document.getElementById("filter_price_mobile").selectedIndex = "5";
  document.getElementById("filter_price_desktop").selectedIndex = "5";
}

if (param == "tensanpham") {
  console.log("1");
  document.getElementById("nameASC").checked = true;
  document.getElementById("filter_products").selectedIndex = "1";
  $("#page_default").css("display", "none");
  $("#page_sort").css("display", "block");
} else if (param == "gia") {
  console.log("2");
  document.getElementById("priceASC").checked = true;
  document.getElementById("filter_products").selectedIndex = "3";
  $("#page_default").css("display", "none");
  $("#page_sort").css("display", "block");
} else if (trademake === null && param === null && sortBy === null) {
  console.log("4");
  $("#page_sort").css("display", "none");
} else if (trademake != "") {
  console.log("3");
  $("#page_default").css("display", "none");

  $("#page_sort").css("display", "block");
}

if (param == "tensanpham" && sortBy == "DESC") {
  console.log("5");

  document.getElementById("nameDESC").checked = true;
  document.getElementById("filter_products").selectedIndex = "2";
  $("#page_default").css("display", "none");
  $("#page_sort").css("display", "block");
} else if (param == "gia" && sortBy == "DESC") {
  console.log("6");
  document.getElementById("priceDESC").checked = true;
  document.getElementById("filter_products").selectedIndex = "4";
  $("#page_default").css("display", "none");
  $("#page_sort").css("display", "block");
}

var x = location.search;

function refreshPage() {
  $(".loader").fadeOut();
  $("#preloder").delay(200).fadeOut("slow");

  /*------------------
        Gallery filter
    --------------------*/
  $(".featured__controls li").on("click", function () {
    $(".featured__controls li").removeClass("active");
    $(this).addClass("active");
  });
  if ($(".featured__filter").length > 0) {
    var containerEl = document.querySelector(".featured__filter");
    var mixer = mixitup(containerEl);
  }

  //document.getElementById("page_default").remove();
  $("#page_default").css("display", "none");
}

document.body.addEventListener("change", function (e) {
  let target = e.target;

  const url = window.location.pathname;

  const urlParams = new URLSearchParams(window.location.search);
  const param = urlParams.get("sort");
  const sortBy = urlParams.get("sortBy");
  const trademake = urlParams.get("trademake");

  const urlPrice = new URLSearchParams(window.location.search);
  const price_min = urlPrice.get("price_min");
  const price_max = urlPrice.get("price_max");

  if (
    x === "" ||
    (trademake === null && price_min === null && price_max === null)
  ) {
    console.log("11");

    console.log(price_min);
    console.log(price_max);

    if (price_min === null && price_max === null) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign('?sort=tensanpham')//
          $("#page_products").load("?sort=tensanpham" + " #page_products");
          window.history.pushState("", "New Page Title", "?sort=tensanpham");
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");
          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign('?sort=tensanpham&sortBy=DESC')
          $("#page_products").load(
            "?sort=tensanpham&sortBy=DESC" + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            "?sort=tensanpham&sortBy=DESC"
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");
          refreshPage();
          break;
        case "priceASC":
          //window.location.assign('?sort=gia')
          $("#page_products").load("?sort=gia" + " #page_products");
          window.history.pushState("", "New Page Title", "?sort=gia");

          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");
          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign('?sort=gia&sortBy=DESC')
          $("#page_products").load("?sort=gia&sortBy=DESC" + " #page_products");
          window.history.pushState(
            "",
            "New Page Title",
            "?sort=gia&sortBy=DESC"
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");
          refreshPage();
          break;
      }
    } else {
      if (price_max == 100000) {
        switch (target.id) {
          case "nameASC":
            // window.location.assign(`?price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=tensanpham` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceASC":
            // window.location.assign(`?price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=gia` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=gia&sortBy=DESC` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
        }
      } else if (
        (price_min == 100000 && price_max == 300000) ||
        (price_min == 300000 && price_max == 500000) ||
        (price_min == 500000 && price_max == 700000)
      ) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();

            break;
          case "nameDESC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=gia` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
        }
      } else if (price_min == 700000) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?price_min=${price_min}&sort=tensanpham`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=tensanpham` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();

            break;
          case "nameDESC":
            //window.location.assign(`?price_min=${price_min}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceASC":
            // window.location.assign(`?price_min=${price_min}&sort=gia`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=gia` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_min=${price_min}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=gia&sortBy=DESC` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");
            refreshPage();
            break;
        }
      }
    }
    // switch (target.id) {
    //     case 'nameASC':
    //         //window.location.assign('?sort=tensanpham')
    //         $("#page_products").load('?sort=tensanpham' + " #page_products");
    //             window.history.pushState('', 'New Page Title', '?sort=tensanpham');
    //             $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
    //             $("#page_sort").attr('style', 'display: block !important');

    //             refreshPage();

    //         break;
    //     case 'nameDESC':
    //         //window.location.assign('?sort=tensanpham&sortBy=DESC')
    //         $("#page_products").load('?sort=tensanpham&sortBy=DESC' + " #page_products");
    //             window.history.pushState('', 'New Page Title', '?sort=tensanpham&sortBy=DESC');
    //             $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
    //             $("#page_sort").attr('style', 'display: block !important');

    //             refreshPage();
    //         break;
    //     case 'priceASC':
    //         //window.location.assign('?sort=gia')
    //         $("#page_products").load('?sort=gia' + " #page_products");
    //             window.history.pushState('', 'New Page Title', '?sort=gia');
    //             $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
    //             $("#page_sort").attr('style', 'display: block !important');

    //             refreshPage();
    //         break;
    //     case 'priceDESC':
    //         //window.location.assign('?sort=gia&sortBy=DESC')
    //         $("#page_products").load('?sort=gia&sortBy=DESC' + " #page_products");
    //             window.history.pushState('', 'New Page Title', '?sort=gia&sortBy=DESC');
    //             $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
    //             $("#page_sort").attr('style', 'display: block !important');

    //             refreshPage();
    //         break;
    // }
  } else if (trademake != "" && price_min === null && price_max === null) {
    switch (target.id) {
      case "nameASC":
        //window.location.assign(`?trademake=${trademake}&sort=tensanpham`)
        $("#page_products").load(
          `?trademake=${trademake}&sort=tensanpham` + " #page_products"
        );
        window.history.pushState(
          "",
          "New Page Title",
          `?trademake=${trademake}&sort=tensanpham`
        );
        $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
        $("#page_sort").attr("style", "display: block !important");

        refreshPage();

        break;
      case "nameDESC":
        // window.location.assign(`?trademake=${trademake}&sort=tensanpham&sortBy=DESC`)
        $("#page_products").load(
          `?trademake=${trademake}&sort=tensanpham&sortBy=DESC` +
            " #page_products"
        );
        window.history.pushState(
          "",
          "New Page Title",
          `?trademake=${trademake}&sort=tensanpham&sortBy=DESC`
        );
        $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
        $("#page_sort").attr("style", "display: block !important");

        refreshPage();
        break;
      case "priceASC":
        //window.location.assign(`?trademake=${trademake}&sort=gia`)
        $("#page_products").load(
          `?trademake=${trademake}&sort=gia` + " #page_products"
        );
        window.history.pushState(
          "",
          "New Page Title",
          `?trademake=${trademake}&sort=gia`
        );
        $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
        $("#page_sort").attr("style", "display: block !important");

        refreshPage();
        break;
      case "priceDESC":
        //window.location.assign(`?trademake=${trademake}&sort=gia&sortBy=DESC`)
        $("#page_products").load(
          `?trademake=${trademake}&sort=gia&sortBy=DESC` + " #page_products"
        );
        window.history.pushState(
          "",
          "New Page Title",
          `?trademake=${trademake}&sort=gia&sortBy=DESC`
        );
        $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
        $("#page_sort").attr("style", "display: block !important");

        refreshPage();
        break;
    }
  } else if (price_min != null || price_max != null) {
    if (trademake === null) {
      if (price_max == 100000) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=tensanpham` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=gia` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_max=${price_max}&sort=gia&sortBy=DESC` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_max=${price_max}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      } else if (
        (price_min == 100000 && price_max == 300000) ||
        (price_min == 300000 && price_max == 500000) ||
        (price_min == 500000 && price_max == 700000)
      ) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=gia` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      } else if (price_min == 700000) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?price_min=${price_min}&sort=tensanpham`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=tensanpham` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?price_min=${price_min}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?price_min=${price_min}&sort=gia`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=gia` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?price_min=${price_min}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?price_min=${price_min}&sort=gia&sortBy=DESC` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?price_min=${price_min}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      }
    } else if (trademake != "") {
      if (price_max == 100000) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?trademake=${trademake}&price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=${price_max}&sort=tensanpham` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?trademake=${trademake}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?trademake=${trademake}&price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=${price_max}&sort=gia` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?trademake=${trademake}&price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=${price_max}&sort=gia&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=${price_max}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      } else if (
        (price_min == 100000 && price_max == 300000) ||
        (price_min == 300000 && price_max == 500000) ||
        (price_min == 500000 && price_max == 700000)
      ) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      } else if (price_min == 700000) {
        switch (target.id) {
          case "nameASC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&sort=tensanpham`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&sort=tensanpham` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&sort=tensanpham`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "nameDESC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&sort=tensanpham&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&sort=tensanpham&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&sort=tensanpham&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceASC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&sort=gia`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&sort=gia` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&sort=gia`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
          case "priceDESC":
            //window.location.assign(`?trademake=${trademake}&price_min=${price_min}&sort=gia&sortBy=DESC`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=${price_min}&sort=gia&sortBy=DESC` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=${price_min}&sort=gia&sortBy=DESC`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
            break;
        }
      }
    }
  } else if (price_min != "" || price_max != "") {
    console.log("price_min != '' || price_max != ''");
    if (price_max == 100000) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign(`?price_max=${price_max}&sort=tensanpham`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=tensanpham` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_max=${price_max}&sort=gia`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=gia` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign(`?price_max=${price_max}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=gia&sortBy=DESC` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=gia&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    } else if (
      (price_min == 100000 && price_max == 300000) ||
      (price_min == 300000 && price_max == 500000) ||
      (price_min == 500000 && price_max == 700000)
    ) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=gia` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    } else if (price_min == 700000) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign(`?price_min=${price_min}&sort=tensanpham`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=tensanpham` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_min=${price_min}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_min=${price_min}&sort=gia`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=gia` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign(`?price_min=${price_min}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=gia&sortBy=DESC` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=gia&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    }
  } else if (param != "" || sortBy != "") {
    console.log("param != '' || sortBy != ''");
    if (price_max == 100000) {
      switch (target.id) {
        case "nameASC":
          // window.location.assign(`?price_max=${price_max}&sort=tensanpham`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=tensanpham` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_max=${price_max}&sort=gia`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=gia` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign(`?price_max=${price_max}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_max=${price_max}&sort=gia&sortBy=DESC` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=${price_max}&sort=gia&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    } else if (
      (price_min == 100000 && price_max == 300000) ||
      (price_min == 300000 && price_max == 500000) ||
      (price_min == 500000 && price_max == 700000)
    ) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=gia` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          // window.location.assign(`?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&price_max=${price_max}&sort=gia&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&price_max=${price_max}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    } else if (price_min == 700000) {
      switch (target.id) {
        case "nameASC":
          //window.location.assign(`?price_min=${price_min}&sort=tensanpham`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=tensanpham` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=tensanpham`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "nameDESC":
          //window.location.assign(`?price_min=${price_min}&sort=tensanpham&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=tensanpham&sortBy=DESC` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=tensanpham&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceASC":
          //window.location.assign(`?price_min=${price_min}&sort=gia`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=gia` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=gia`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
        case "priceDESC":
          //window.location.assign(`?price_min=${price_min}&sort=gia&sortBy=DESC`)
          $("#page_products").load(
            `?price_min=${price_min}&sort=gia&sortBy=DESC` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=${price_min}&sort=gia&sortBy=DESC`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
          break;
      }
    }
  }
});

function sortChanged(obj) {
  const url = window.location.pathname;

  const urlParams = new URLSearchParams(window.location.search);
  const param = urlParams.get("sort");
  const sortBy = urlParams.get("sortBy");
  const trademake = urlParams.get("trademake");

  const urlPrice = new URLSearchParams(window.location.search);
  const price_min = urlPrice.get("price_min");
  const price_max = urlPrice.get("price_max");

  var value = obj.value;
  if (x === "" || trademake === null) {
    if (value === "") {
      //window.location.assign(url)
      $("#page_products").load(url + " #page_products");
      window.history.pushState("", "New Page Title", url);
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "1") {
      //window.location.assign('?sort=tensanpham')
      $("#page_products").load("?sort=tensanpham" + " #page_products");
      window.history.pushState("", "New Page Title", "?sort=tensanpham");
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "2") {
      //window.location.assign('?sort=tensanpham&sortBy=DESC')
      $("#page_products").load(
        "?sort=tensanpham&sortBy=DESC" + " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        "?sort=tensanpham&sortBy=DESC"
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "3") {
      //window.location.assign('?sort=gia')
      $("#page_products").load("?sort=gia" + " #page_products");
      window.history.pushState("", "New Page Title", "?sort=gia");
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "4") {
      //window.location.assign('?sort=gia&sortBy=DESC')
      $("#page_products").load("?sort=gia&sortBy=DESC" + " #page_products");
      window.history.pushState("", "New Page Title", "?sort=gia&sortBy=DESC");
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    }
  } else if (trademake != "") {
    if (value === "") {
      //window.location.assign(url + `?trademake=${trademake}`)
      $("#page_products").load(
        url + `?trademake=${trademake}` + " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        url + `?trademake=${trademake}`
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "1") {
      //window.location.assign(`?trademake=${trademake}&sort=tensanpham`)
      $("#page_products").load(
        `?trademake=${trademake}&sort=tensanpham` + " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        `?trademake=${trademake}&sort=tensanpham`
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "2") {
      //window.location.assign(`?trademake=${trademake}&sort=tensanpham&sortBy=DESC`)
      $("#page_products").load(
        `?trademake=${trademake}&sort=tensanpham&sortBy=DESC` +
          " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        `?trademake=${trademake}&sort=tensanpham&sortBy=DESC`
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "3") {
      //window.location.assign(`?trademake=${trademake}&sort=gia`)
      $("#page_products").load(
        `?trademake=${trademake}&sort=gia` + " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        `?trademake=${trademake}&sort=gia`
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    } else if (value === "4") {
      //window.location.assign(`?trademake=${trademake}&sort=gia&sortBy=DESC`)
      $("#page_products").load(
        `?trademake=${trademake}&sort=gia&sortBy=DESC` + " #page_products"
      );
      window.history.pushState(
        "",
        "New Page Title",
        `?trademake=${trademake}&sort=gia&sortBy=DESC`
      );
      $("#pageSortRefresh").load(window.location.href + " #pageSortRefresh");
      $("#page_sort").attr("style", "display: block !important");

      refreshPage();
    }
  }
}

function priceChanged(obj) {
  const url = window.location.pathname;

  const urlParams = new URLSearchParams(window.location.search);
  const param = urlParams.get("sort");
  const sortBy = urlParams.get("sortBy");
  const trademake = urlParams.get("trademake");

  const urlPrice = new URLSearchParams(window.location.search);
  const price_min = urlPrice.get("price_min");
  const price_max = urlPrice.get("price_max");

  var value = obj.value;

  if (x === "" || (trademake === null && param === null && sortBy === null)) {
    console.log("111");
    if(param === null && sortBy === null ) {
        if (value === ''){
            //window.location.assign(url)
            $("#page_products").load(url + " #page_products");
            window.history.pushState('', 'New Page Title', url);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
        }
        else if (value === '1'){
        //window.location.assign(`?price_max=100000`) 
        
            $("#page_products").load(`?price_max=100000` + " #page_products");
            window.history.pushState('', 'New Page Title', `?price_max=100000`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage(); 
        }
        else if (value === '2'){
            //window.location.assign(`?price_min=100000&price_max=300000`)
    
            $("#page_products").load(`?price_min=100000&price_max=300000` + " #page_products");
            window.history.pushState('', 'New Page Title', `?price_min=100000&price_max=300000`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
             
        }
        else if (value === '3'){
           // window.location.assign(`?price_min=300000&price_max=500000`) 
            $("#page_products").load(`?price_min=300000&price_max=500000` + " #page_products");
            window.history.pushState('', 'New Page Title', `?price_min=300000&price_max=500000`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
        }
        else if (value === '4'){
            //window.location.assign(`?price_min=500000&price_max=700000`) 
            $("#page_products").load(`?price_min=500000&price_max=700000` + " #page_products");
            window.history.pushState('', 'New Page Title', `?price_min=500000&price_max=700000`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
        }
        else if (value === '5'){
            //window.location.assign(url+`?price_min=700000`) 
            $("#page_products").load(url+`?price_min=700000` + " #page_products");
            window.history.pushState('', 'New Page Title', url+`?price_min=700000`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
        }
    
        console.log("sonne");
    }else if(param !='' || sortBy!='') {
       
            if (param != '') {
                if (sortBy == 'DESC') {
                    if (value === ''){
                        //window.location.assign(url+`?sort=${param}&sortBy=${sortBy}`)
                        $("#page_products").load(url+`?sort=${param}&sortBy=${sortBy}` + " #page_products");
            window.history.pushState('', 'New Page Title', url+`?sort=${param}&sortBy=${sortBy}`);
            $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
            $("#page_sort").attr('style', 'display: block !important');
            document.getElementById('page_default').remove();
            refreshPage();
                    }
                    else if (value === '1'){
                    //window.location.assign(`?price_max=100000&sort=${param}&sortBy=${sortBy}`) 
                    $("#page_products").load(`?price_max=100000&sort=${param}&sortBy=${sortBy}` + " #page_products");
                    window.history.pushState('', 'New Page Title', `?price_max=100000&sort=${param}&sortBy=${sortBy}`);
                    $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                    $("#page_sort").attr('style', 'display: block !important');
                    document.getElementById('page_default').remove();
                    refreshPage(); 
                    }
                    else if (value === '2'){
                        //window.location.assign(`?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`)
                        $("#page_products").load(`?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}` + " #page_products");
                    window.history.pushState('', 'New Page Title', `?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`);
                    $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                    $("#page_sort").attr('style', 'display: block !important');
                    document.getElementById('page_default').remove();
                    refreshPage();  
                    }
                    else if (value === '3'){
                        //window.location.assign(`?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`) 
                        $("#page_products").load(`?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage(); 
                    }
                    else if (value === '4'){
                        //window.location.assign(`?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`) 
                        $("#page_products").load(`?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();
                    }
                    else if (value === '5'){
                        //window.location.assign(url+`?price_min=700000&sort=${param}&sortBy=${sortBy}`) 
                        $("#page_products").load(url+`?price_min=700000&sort=${param}&sortBy=${sortBy}` + " #page_products");
                        window.history.pushState('', 'New Page Title', url+`?price_min=700000&sort=${param}&sortBy=${sortBy}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();
                    }
                }else if(param != '' && price_min != '' && price_max != '') {
                    if (value === ''){
                        //window.location.assign(url+`?sort=${param}`)
                        $("#page_products").load(url+`?sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', url+`?sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();
                    }
                    else if (value === '1'){
                    //window.location.assign(`?price_max=100000&sort=${param}`) 
                    $("#page_products").load(`?price_max=100000&sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_max=100000&sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage(); 
                    }
                    else if (value === '2'){
                        //window.location.assign(`?price_min=100000&price_max=300000&sort=${param}`)
                        $("#page_products").load(`?price_min=100000&price_max=300000&sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_min=100000&price_max=300000&sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();  
                    }
                    else if (value === '3'){
                        //window.location.assign(`?price_min=300000&price_max=500000&sort=${param}`)
                        $("#page_products").load(`?price_min=300000&price_max=500000&sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_min=300000&price_max=500000&sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();   
                    }
                    else if (value === '4'){
                        //window.location.assign(`?price_min=500000&price_max=700000&sort=${param}`)
                        $("#page_products").load(`?price_min=500000&price_max=700000&sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', `?price_min=500000&price_max=700000&sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage();   
                    }
                    else if (value === '5'){
                        //window.location.assign(url+`?price_min=700000&sort=${param}`)
                        $("#page_products").load(url+`?price_min=700000&sort=${param}` + " #page_products");
                        window.history.pushState('', 'New Page Title', url+`?price_min=700000&sort=${param}`);
                        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
                        $("#page_sort").attr('style', 'display: block !important');
                        document.getElementById('page_default').remove();
                        refreshPage(); 
                    }
                }
                
            
            }
    }
  } else if (param != "" || sortBy != "" && trademake != "") {
    console.log("112");
    console.log(sortBy);
    console.log(param);
    if (trademake === null) {
      if (param == "tensanpham" || param == "gia") {
        if (value === "") {
          console.log(url);
          //window.location.assign(url+`?sort=${param}`)
          $("#page_products").load(url + `?sort=${param}` + " #page_products");
          window.history.pushState(
            "",
            "New Page Title",
            url + `?sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?price_max=100000&sort=${param}`)
          $("#page_products").load(
            `?price_max=100000&sort=${param}` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=100000&sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          //window.location.assign(`?price_min=100000&price_max=300000&sort=${param}`)
          $("#page_products").load(
            `?price_min=100000&price_max=300000&sort=${param}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=100000&price_max=300000&sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
          //window.location.assign(`?price_min=300000&price_max=500000&sort=${param}`)
          $("#page_products").load(
            `?price_min=300000&price_max=500000&sort=${param}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=300000&price_max=500000&sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          //window.location.assign(`?price_min=500000&price_max=700000&sort=${param}`)
          $("#page_products").load(
            `?price_min=500000&price_max=700000&sort=${param}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=500000&price_max=700000&sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?price_min=700000&sort=${param}`)
          $("#page_products").load(
            url + `?price_min=700000&sort=${param}` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?price_min=700000&sort=${param}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      } else if (
        param == "tensanpham" ||
        (param == "gia" && sortBy == "DESC")
      ) {
        if (value === "") {
          //window.location.assign(url+`?sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            url + `?sort=${param}&sortBy=${sortBy}` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?price_max=100000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?price_max=100000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_max=100000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          // window.location.assign(`?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
          //window.location.assign(`?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          //window.location.assign(`?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?price_min=700000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            url +
              `?price_min=700000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?price_min=700000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      }
    }
    //end  trademake === null
    else if (trademake != "") {
      console.log("12345");
      if (param == "tensanpham" || param == "gia") {
        if (sortBy == "DESC") {
          if (value === "") {
            //window.location.assign(url+`?trademake=${trademake}&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              url +
                `?trademake=${trademake}&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              url + `?trademake=${trademake}&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "1") {
            //window.location.assign(`?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "2") {
            //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "3") {
            //window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "4") {
            // window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "5") {
            // window.location.assign(url+`?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}`)
            $("#page_products").load(
              url +
                `?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              url +
                `?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          }
        } else if (param != "") {
          if (value === "") {
            //window.location.assign(url+`?trademake=${trademake}&sort=${param}`)
            $("#page_products").load(
              url + `?trademake=${trademake}&sort=${param}` + " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              url + `?trademake=${trademake}&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "1") {
            //window.location.assign(`?trademake=${trademake}&price_max=100000&sort=${param}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_max=100000&sort=${param}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_max=100000&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "2") {
            //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "3") {
            // window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "4") {
            // window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}`)
            $("#page_products").load(
              `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          } else if (value === "5") {
            //window.location.assign(url+`?trademake=${trademake}&price_min=700000&sort=${param}`)
            $("#page_products").load(
              url +
                `?trademake=${trademake}&price_min=700000&sort=${param}` +
                " #page_products"
            );
            window.history.pushState(
              "",
              "New Page Title",
              url + `?trademake=${trademake}&price_min=700000&sort=${param}`
            );
            $("#pageSortRefresh").load(
              window.location.href + " #pageSortRefresh"
            );
            $("#page_sort").attr("style", "display: block !important");

            refreshPage();
          }
        }
      } 
      //end param == "tensanpham" || param == "gia"
      else if (
        (param == "tensanpham" && sortBy == "DESC") ||
        (param == "gia" && sortBy == "DESC")
      ) {
        if (value === "") {
          //window.location.assign(url+`?trademake=${trademake}&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            url +
              `?trademake=${trademake}&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?trademake=${trademake}&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_max=100000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=100000&price_max=300000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
          //window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=300000&price_max=500000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          //window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=500000&price_max=700000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}`)
          $("#page_products").load(
            url +
              `?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url +
              `?trademake=${trademake}&price_min=700000&sort=${param}&sortBy=${sortBy}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      }else if (trademake != "" && price_max === null && price_min === null) {
        if (value === "") {
          //window.location.assign(`?trademake=${trademake}`)
          $("#page_products").load(
            `?trademake=${trademake}` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?trademake=${trademake}&price_max=100000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_max=100000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_max=100000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=100000&price_max=300000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=100000&price_max=300000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
            console.log("1999988888");
          // window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=300000&price_max=500000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=300000&price_max=500000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          //window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=500000&price_max=700000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=500000&price_max=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?trademake=${trademake}&price_min=700000`)
          $("#page_products").load(
            url + `?trademake=${trademake}&price_min=700000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?trademake=${trademake}&price_min=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      }
    }
    //end trademake != ""
    else if (trademake != "" && price_max === null && price_min === null) {
      console.log("123");
      if (
        (trademake != "" && price_min != "",
        price_max != "" && sortBy === null && param === null)
      ) {

        console.log("147852");
        if (value === "") {
          //window.location.assign(`?trademake=${trademake}`)
          $("#page_products").load(
            `?trademake=${trademake}` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?trademake=${trademake}&price_max=100000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_max=100000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_max=100000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=100000&price_max=300000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=100000&price_max=300000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
            console.log("1999988888");
          // window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=300000&price_max=500000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=300000&price_max=500000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          //window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=500000&price_max=700000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=500000&price_max=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?trademake=${trademake}&price_min=700000`)
          $("#page_products").load(
            url + `?trademake=${trademake}&price_min=700000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?trademake=${trademake}&price_min=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      }
    } else {
      if (trademake != null && price_max != null && price_min != null) {
        if (param === null || sortBy === null) {
          console.log("11222");
          if (
            (trademake != "" && price_min != "",
            price_max != "" && sortBy === null && param === null)
          ) {

            console.log("999999999");
            if (value === "") {
              //window.location.assign(`?trademake=${trademake}`)
              $("#page_products").load(
                `?trademake=${trademake}` + " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                `?trademake=${trademake}`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            } else if (value === "1") {
                console.log("999999999");
              //window.location.assign(`?trademake=${trademake}&price_max=100000`)
              $("#page_products").load(
                `?trademake=${trademake}&price_max=100000` + " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                `?trademake=${trademake}&price_max=100000`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            } else if (value === "2") {
                console.log("999999999");
              //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000`)
              $("#page_products").load(
                `?trademake=${trademake}&price_min=100000&price_max=300000` +
                  " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                `?trademake=${trademake}&price_min=100000&price_max=300000`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            } else if (value === "3") {

                console.log('199999');
              //window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000`)
              $("#page_products").load(
                `?trademake=${trademake}&price_min=300000&price_max=500000` +
                  " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                `?trademake=${trademake}&price_min=300000&price_max=500000`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            } else if (value === "4") {
                console.log("999999999");
              //window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000`)
              $("#page_products").load(
                `?trademake=${trademake}&price_min=500000&price_max=700000` +
                  " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                `?trademake=${trademake}&price_min=500000&price_max=700000`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            } else if (value === "5") {
                console.log("999999999");
              //window.location.assign(url+`?trademake=${trademake}&price_min=700000`)
              $("#page_products").load(
                url +
                  `?trademake=${trademake}&price_min=700000` +
                  " #page_products"
              );
              window.history.pushState(
                "",
                "New Page Title",
                url + `?trademake=${trademake}&price_min=700000`
              );
              $("#pageSortRefresh").load(
                window.location.href + " #pageSortRefresh"
              );
              $("#page_sort").attr("style", "display: block !important");

              refreshPage();
            }
          }
          //end trademake != "" && price_min != "", price_max != "" && sortBy === null && param === null
        }

        //end param === null || sortBy === null
      } else if (price_min === null || price_max === null) {
        if (value === "") {
          //window.location.assign(url)
          $("#page_products").load(url + " #page_products");
          window.history.pushState("", "New Page Title", url);
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "1") {
          //window.location.assign(`?trademake=${trademake}&price_max=100000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_max=100000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_max=100000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "2") {
          //window.location.assign(`?trademake=${trademake}&price_min=100000&price_max=300000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=100000&price_max=300000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=100000&price_max=300000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "3") {
            console.log("1477777")
          //window.location.assign(`?trademake=${trademake}&price_min=300000&price_max=500000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=300000&price_max=500000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=300000&price_max=500000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "4") {
          // window.location.assign(`?trademake=${trademake}&price_min=500000&price_max=700000`)
          $("#page_products").load(
            `?trademake=${trademake}&price_min=500000&price_max=700000` +
              " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            `?trademake=${trademake}&price_min=500000&price_max=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        } else if (value === "5") {
          //window.location.assign(url+`?trademake=${trademake}&price_min=700000`)
          $("#page_products").load(
            url + `?trademake=${trademake}&price_min=700000` + " #page_products"
          );
          window.history.pushState(
            "",
            "New Page Title",
            url + `?trademake=${trademake}&price_min=700000`
          );
          $("#pageSortRefresh").load(
            window.location.href + " #pageSortRefresh"
          );
          $("#page_sort").attr("style", "display: block !important");

          refreshPage();
        }
      }
    }
  }
}
