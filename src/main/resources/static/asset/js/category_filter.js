

const url = window.location.pathname;
console.log('name' + url);




const urlParams = new URLSearchParams(window.location.search);
const param = urlParams.get('sort');
const sortBy = urlParams.get('sortBy');
const trademake = urlParams.get('trademake');








if (param == 'tensanpham') {
    document.getElementById('nameASC').checked = true;
    document.getElementById("filter_products").selectedIndex = "1";
    document.getElementById('page_default').remove();
    $("#page_sort").css("display", 'block');

}else if(param == 'gia'){
    document.getElementById('priceASC').checked = true;
    document.getElementById("filter_products").selectedIndex = "3";
    document.getElementById('page_default').remove();
    $("#page_sort").css("display", 'block');
} 



if(param == 'tensanpham' && sortBy == 'DESC'){
   
    document.getElementById('nameDESC').checked = true;
    document.getElementById("filter_products").selectedIndex = "2";
    $("#page_sort").css("display", 'block');
}else if(param == 'gia' && sortBy == 'DESC'){
    document.getElementById('priceDESC').checked = true;
    document.getElementById("filter_products").selectedIndex = "4";
    
    $("#page_sort").css("display", 'block');
}


var x = location.search;










document.body.addEventListener('change', function (e) {
    let target = e.target;

    if (x === '' || trademake === null ) {
        
        switch (target.id) {
            case 'nameASC':
                window.location.assign('?sort=tensanpham')
                
                break;
            case 'nameDESC':
                window.location.assign('?sort=tensanpham&sortBy=DESC')
                break;
            case 'priceASC':
                window.location.assign('?sort=gia')
                break;
            case 'priceDESC':
                window.location.assign('?sort=gia&sortBy=DESC')
                break;
        }
    }else if(trademake != '') {
        switch (target.id) {
            case 'nameASC':
                window.location.assign(`?trademake=${trademake}&sort=tensanpham`)
                
                break;
            case 'nameDESC':
                window.location.assign(`?trademake=${trademake}&sort=tensanpham&sortBy=DESC`)
                break;
            case 'priceASC':
                window.location.assign(`?trademake=${trademake}&sort=gia`)
                break;
            case 'priceDESC':
                window.location.assign(`?trademake=${trademake}&sort=gia&sortBy=DESC`)
                break;
        }
    }
        
    
        
        
    
    
    
});

function sortChanged(obj)
{

    var value = obj.value;
    if (x === '' || trademake === null ) {
        if (value === ''){
            window.location.assign(url)
        }
        else if (value === '1'){
        window.location.assign('?sort=tensanpham')  
        }
        else if (value === '2'){
            window.location.assign('?sort=tensanpham&sortBy=DESC') 
        }
        else if (value === '3'){
            window.location.assign('?sort=gia') 
        }
        else if (value === '4'){
            window.location.assign('?sort=gia&sortBy=DESC') 
        }
    }else if (trademake != '') {
        if (value === ''){
            window.location.assign(url + `?trademake=${trademake}`)
        }
        else if (value === '1'){
        window.location.assign(`?trademake=${trademake}&sort=tensanpham`)  
        }
        else if (value === '2'){
            window.location.assign(`?trademake=${trademake}&sort=tensanpham&sortBy=DESC`) 
        }
        else if (value === '3'){
            window.location.assign(`?trademake=${trademake}&sort=gia`) 
        }
        else if (value === '4'){
            window.location.assign(`?trademake=${trademake}&sort=gia&sortBy=DESC`) 
        }
    }
    
    
    
}




