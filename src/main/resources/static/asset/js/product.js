const urlParams = new URLSearchParams(window.location.search);
const param = urlParams.get('sort');
const sortBy = urlParams.get('sortBy');
console.log(sortBy);
console.log(param);

if (param == 'tensanpham') {
    document.getElementById('nameASC').checked = true;

}else if(param == 'gia'){
    document.getElementById('priceASC').checked = true;
} 



if(param == 'tensanpham' && sortBy == 'DESC'){
   
    document.getElementById('nameDESC').checked = true;
}else if(param == 'gia' && sortBy == 'DESC'){
    document.getElementById('priceDESC').checked = true;
}



document.body.addEventListener('change', function (e) {
    let target = e.target;
    let message;
    switch (target.id) {
        case 'nameASC':
            window.location.assign('/product?sort=tensanpham')
            
            break;
        case 'nameDESC':
            window.location.assign('/product?sort=tensanpham&sortBy=DESC')
            break;
        case 'priceASC':
            window.location.assign('/product?sort=gia')
            break;
        case 'priceDESC':
            window.location.assign('/product?sort=gia&sortBy=DESC')
            break;
    }
    
});