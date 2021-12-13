var prid = localStorage.getItem("prid");
var url = "/api/review/" + prid;


var reviewModal = new bootstrap.Modal(document.getElementById('reviewModal'), {
    keyboard: false
  });

function loadrew() {
	//getRew(renderRew);
	$.ajax({
        url: url,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
          	renderReview(res);
        }
    });
	
}
loadrew();

function renderReview(json) {
	var rews = document.querySelector("#loadRew");
    var rating = [];
	var loadRew = json.map(function(rew) {
		rating.push(rew.score)
		var html = `
			<div class="comment__item par" >

			<div class="item--top">
				<p class="txtname">${rew.name}</p>
				
			</div>
			
			<div class="item-rate">
				<div class="comment-star">
                    <div class="jstars" data-value="${rew.score}"></div>
				</div>
			</div>
			<div class="comment-content">
				<p class="cmt-txt">${rew.description}</p>
			</div>
			<div class="comment-content">
				<div class="cmt-img">
					<object class="lazyload" style="width: 100px" data="${rew.image}" type="image/jpg">
						
					</object>
				</div>
			</div>
			</div>
			`;
		return html;
	});
	
	rews.innerHTML = loadRew.join(" ");
	loadne();
	renderPercentRating(rating, json);
	
}

function renderPercentRating(rating, json) {
    /*Tính tổng review*/
	var sumRew = rating.length;
	$("#sumRew").text("("+sumRew+")");
	
	var avg = rating.reduce(function(p,c,i,a){return p + (c/a.length)},0);
    var sortRating = rating.reverse();


    var count = {};
    sortRating.forEach(function(i) { 
        count[i] = (count[i]||0) + 1 * 100 /rating.length ;  
    });
    $( "#percent" ).empty();

for (let x in count) {
    if (count.hasOwnProperty(x)) {
        var countFormat = count[x].toFixed(2);
        $("#percent").append(`
        <li id="listPercentRating">
        <div class="number-star">
            ${x} <i class="fa fa-star"></i>
        </div>
        <div class="timeline-star">
            <p class="timing" style="width: ${count[x]}%"></p>
        </div> <a href="#"
        class="number-percent">${countFormat}%</a>
    </li>
        `)

        
      }
      
};
   
	$("#avgScore").text(avg.toFixed(2));

	var avgRev = document.querySelector("#avgStar");
	var avgRew = json.map(function() {
		
		$(function () {

		 $("#rateYo1").rateYo({
		   rating: avg,
		   readOnly: true
		 });
		});
		
		var html = `
				<div id="rateYo1"></div>
			`;
		return html ;
	});
	$("#countRate").text(rating.length + " đánh giá");
	avgRev.innerHTML = avgRew.join(" ");
}


function loadne(){
	(function ($) {
    $.fn.jstars = function (options) {
        options = options || {};

        var defaults = {
            size: '15px',
            value: 5,
            stars: 5,
            color: '#F39C12',
            emptyColor: '#808080'
        };
        var settings = $.extend(defaults, options);

        var unselectable = function ($element) {
            $element.css('user-select', 'none')
                .css('-moz-user-select', 'none')
                .css('-khtml-user-select', 'none')
                .css('-webkit-user-select', 'none')
                .css('-o-user-select', 'none');
        };

        var repeat = function (str, total) {
            var final = '';
            for (var i = 0; i < total; i++) final += str;
            return final;
        };

        this.each(function () {
            var $container = $(this);
            var value = $container.data('value') || settings.value;
            var totalStars = $container.data('total-stars') || settings.stars;
            var color = $container.data('color') || settings.color;
            var emptyColor = $container.data('empty-color') || settings.emptyColor;
            var size = $container.data('size') || settings.size;

            var $emptyStars = $(document.createElement('div'))
                .addClass('jstars-empty')
                .css('position', 'relative')
                .css('display', 'inline-block')
                .css('font-size', size)
                .css('line-height', size)
                .css('color', emptyColor)
                .append(repeat("&starf;", totalStars));

            unselectable($emptyStars);

            var $filledStars = $(document.createElement('div'))
                .addClass('jstars-filled')
                .css('top', 0)
                .css('left', 0)
                .css('position', 'absolute')
                .css('display', 'inline-block')
                .css('font-size', size)
                .css('line-height', size)
                .css('width', ((value / totalStars) * 100) + '%')
                .css('overflow', 'hidden')
                .css('white-space', 'nowrap')
                .css('color', color)
                .append(repeat("&starf;", totalStars));

            unselectable($filledStars);

            $emptyStars.append($filledStars);
            $container.append($emptyStars);
        });
        return this;
    };
    $(function () {
        $('.jstars').jstars();
    });
})(jQuery);
}

for (let index = 1; index <= 5; index++) {
    console.log(index);
    
}