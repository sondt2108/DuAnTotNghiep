const url = window.location.pathname;
console.log('name' + url);

const subst = url.substring(0, 11);
console.log('name: ' + subst);


const urlPrice = new URLSearchParams(window.location.search);
const price_min = urlPrice.get('price_min');
const price_max = urlPrice.get('price_max');




function loadPage(value) {
    if(value == ''){
        console.log("1");
        $("#page_trademark").css("display: block");
    }else{
        console.log("2");
        $("#page_trademark").hide();
        $("#page_trademark_filter").show();
    }
}



function priceChanged(obj)
{
    const url = window.location.pathname;
    console.log('name' + url);

    const subst = url.substring(0, 11);
console.log('name: ' + subst);

const urlPrice = new URLSearchParams(window.location.search);
const price_min = urlPrice.get('price_min');
const price_max = urlPrice.get('price_max');
    var value = obj.value;
    if (value === ''){
        $("#page_products").load(url + " #page_products");
        window.history.pushState('', 'New Page Title', url);
        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
        loadPage(value);
    }
    else if (value === '1'){
        $("#page_products").load(`?price_max=100000` + " #page_products");
      window.history.pushState('', 'New Page Title', '?price_max=100000');
      $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
      loadPage(value);
    }
    else if (value === '2'){
        $("#page_products").load(`?price_min=100000&price_max=300000` + " #page_products");
        window.history.pushState('', 'New Page Title', '?price_min=100000&price_max=300000');
        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
        loadPage(value);
    }
    else if (value === '3'){
        $("#page_products").load(`?price_min=300000&price_max=500000` + " #page_products");
        window.history.pushState('', 'New Page Title', '?price_min=300000&price_max=500000');
        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
        loadPage(value);
    }
    else if (value === '4'){
        $("#page_products").load(`?price_min=500000&price_max=700000` + " #page_products");
        window.history.pushState('', 'New Page Title', '?price_min=500000&price_max=700000');
        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
        loadPage(value);
    }
    else if (value === '5'){
        $("#page_products").load(`?&price_min=700000` + " #page_products");
        window.history.pushState('', 'New Page Title', '?&price_min=700000');
        $( "#pageSortRefresh" ).load(window.location.href + " #pageSortRefresh" );
        loadPage(value);
    }
    
}





